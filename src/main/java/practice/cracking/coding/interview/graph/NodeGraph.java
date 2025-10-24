package practice.cracking.coding.interview.graph;

import java.util.*;

class GraphNode {//Node
    private String name;
    private List<GraphNode> neighbours;

    public GraphNode(String name) {
        this.name = name;
        this.neighbours = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<GraphNode> getNeighbours() {
        return neighbours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GraphNode)) return false;
        GraphNode that = (GraphNode) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}

public class NodeGraph {
    public List<GraphNode> adjList;

    public NodeGraph() {
        this.adjList = new ArrayList<>();
    }

    public void addVertex(String name) {
        GraphNode node = getNodeByName(name);
        if (node == null) {
            this.adjList.add(new GraphNode(name));
        }
    }

    public void addEdge(String source, String target) {
        addVertex(source);
        addVertex(target);
        GraphNode sourceNode = getNodeByName(source);
        GraphNode targetNode = getNodeByName(target);
        if (!sourceNode.getNeighbours().contains(targetNode)) {
            sourceNode.getNeighbours().add(targetNode);
        }
        if (!targetNode.getNeighbours().contains(sourceNode)) {
            targetNode.getNeighbours().add(sourceNode);
        }
    }

    public void bfs(String start) {
        GraphNode node = getNodeByName(start);

        if (node == null) {
            System.out.println("Node " + start + " not exist in the Graph");
            return;
        }

        Queue<GraphNode> queue = new LinkedList<>();
        boolean[] visit = new boolean[adjList.size()];//highly not recommended ,can use List or Set

        int startIndex = adjList.indexOf(node);
        if (startIndex != -1) {
            queue.add(node);
            visit[startIndex] = true;
        }

        while (!queue.isEmpty()) {
            GraphNode currentNode = queue.poll();
            System.out.print(currentNode.getName() + " ");
            for (GraphNode neighbour : currentNode.getNeighbours()) {
                int index = adjList.indexOf(neighbour);
                if (index != -1 && !visit[index]) {
                    queue.add(neighbour);
                    visit[index] = true;
                }
            }
        }
    }

    public void optimizedBFS(String start) {
        GraphNode node = getNodeByName(start);

        if (node == null) {
            System.out.println("Node " + start + " not exist in the Graph");
            return;
        }

        Queue<GraphNode> queue = new LinkedList<>();
        //Use Set<String> Instead of List<String> & Using a Set avoids the O(n) cost of .contains() on a List.
        //Time complexity: O(1) on average & HashSet uses a hash table under the hood & Checking for existence (or adding/removing) is very fast
        List<String> visit = new ArrayList<>();

        queue.add(node);
        visit.add(start);

        while (!queue.isEmpty()) {
            GraphNode currentNode = queue.poll();
            System.out.print(currentNode.getName() + " ");
            for (GraphNode neighbour : currentNode.getNeighbours()) {
                if (!visit.contains(neighbour.getName())) {
                    queue.add(neighbour);
                    visit.add(neighbour.getName());
                }
            }
        }
    }

    private GraphNode getNodeByName(String name) {
        for (GraphNode node : adjList) {
            if (node.getName().equals(name)) {
                return node;
            }
        }
        return null;
    }

    public void printGraph() {
        for (GraphNode node : adjList) {
            System.out.print(node.getName() + " -> ");
            for (GraphNode neighbour : node.getNeighbours()) {
                System.out.print(neighbour.getName() + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        NodeGraph graph = new NodeGraph();

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "E");

        System.out.println("Adjacency List of the Graph:");
        graph.printGraph();

        System.out.println("BFS starting from node A:");
        graph.bfs("A");

        System.out.println("\nBFS starting from node B:");
        graph.optimizedBFS("A");
    }
}

