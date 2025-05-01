package com.cracking.coding.interview.graph;

import java.util.*;

public class Graph {
    //Using List<Integer> means contains() is O(n). If your graph is large and dense, using Set<Integer> for neighbors makes edge-checking faster (O(1))
    public Map<Integer, Set<Integer>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    // add() inserts into the set; duplicates are ignored
    public void addVertex(int vertex) {
        adjacencyList.putIfAbsent(vertex, new HashSet<>());
    }

    // Adds an undirected edge between source and destination
    public void addEdge(int source, int destination) {
        addVertex(source);
        addVertex(destination);
        // if (!adjacencyList.get(source).contains(destination)) {//un necessary if it got changed from List to Set
        adjacencyList.get(source).add(destination);//add() also similar to addLast() which create link to last element
        //}
        // if (!adjacencyList.get(destination).contains(source)) {
        adjacencyList.get(destination).add(source);
        //}
    }

    public void print() {
        for (Map.Entry<Integer, Set<Integer>> entry : adjacencyList.entrySet()) {
            int vertex = entry.getKey();
            System.out.print(vertex + " --> ");
            for (int neighbour : entry.getValue()) {
                System.out.print(neighbour + " ");
            }
            System.out.println();
        }
    }

    public void breadthFirstSearch(int startVertex) {
        if (!adjacencyList.containsKey(startVertex)) {
            System.out.println("Start vertex " + startVertex + " does not exist in the graph.");
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visit = new HashSet<>();

        queue.add(startVertex);
        visit.add(startVertex);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();//remove Old/First element
            System.out.print(currentVertex + " ");
            for (int neighbourVertex : adjacencyList.get(currentVertex)) {
                if (!visit.contains(neighbourVertex)) {
                    queue.add(neighbourVertex);
                    visit.add(neighbourVertex);
                }
            }
        }
        System.out.println(); // Final line break
    }

    public boolean isConnected(int sourceVertex, int targetVertex) {
        if (sourceVertex == targetVertex) return true;
        if (!adjacencyList.containsKey(sourceVertex) || !adjacencyList.containsKey(targetVertex)) {
            System.out.println(sourceVertex + " or " + targetVertex + " does not exist in the graph.");
            return false;
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visit = new HashSet<>();

        queue.add(sourceVertex);
        visit.add(sourceVertex);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();//remove Old/First element
            for (int neighbourVertex : adjacencyList.get(currentVertex)) {
                if (neighbourVertex == targetVertex) {
                    return true;
                } else if (!visit.contains(neighbourVertex)) {
                    queue.add(neighbourVertex);
                    visit.add(neighbourVertex);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 5);
        g.print(); // Prints the adjacency list
        g.breadthFirstSearch(1); // Outputs: 1 2 3 4 5
        System.out.println("Is 1 connected to 5? " + g.isConnected(1, 5)); // true
        System.out.println("Is 2 connected to 6? " + g.isConnected(2, 6)); // false
        g.addVertex(6);
        g.print(); // Prints the adjacency list
        System.out.println("Is 2 connected to 6? " + g.isConnected(2, 6)); // false
    }
}
