package com.cracking.coding.interview.graph;

import java.util.*;

//optimzed version of Node Graph
public class NodeGraph2 {
    public Map<String, GraphNode> adjList;

    public NodeGraph2() {
        this.adjList = new HashMap<>();
    }

    public void addVertex(String name) {
        GraphNode node = adjList.get(name);
        if (node == null) {
            this.adjList.putIfAbsent(name, new GraphNode(name));
        }
    }

    public void addEdge(String source, String target) {
        addVertex(source);
        addVertex(target);
        GraphNode sourceNode = adjList.get(source);
        GraphNode targetNode = adjList.get(target);
        if (!sourceNode.getNeighbours().contains(targetNode)) {
            sourceNode.getNeighbours().add(targetNode);
        }
        if (!targetNode.getNeighbours().contains(sourceNode)) {
            targetNode.getNeighbours().add(sourceNode);
        }
    }

    public void bfs(String start) {
        GraphNode node = adjList.get(start);

        if (node == null) {
            System.out.println("Node " + start + " not exist in the Graph");
            return;
        }

        Queue<GraphNode> queue = new LinkedList<>();
        Set<String> visit = new HashSet<>();

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

    public void printGraph() {
        for (Map.Entry<String, GraphNode> entry : adjList.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (GraphNode neighbour : entry.getValue().getNeighbours()) {
                System.out.print(neighbour.getName() + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        NodeGraph2 graph = new NodeGraph2();

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "E");

        System.out.println("Adjacency List of the Graph:");
        graph.printGraph();

        System.out.println("BFS starting from node A:");
        graph.bfs("A");
    }
}
