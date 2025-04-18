package com.cracking.coding.interview.graph;

import java.util.*;

public class Graph {
    public Map<Integer, List<Integer>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(int vertex) {
        adjacencyList.putIfAbsent(vertex, new LinkedList<>());
    }

    public void addEdge(int source, int destination) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).add(destination);//add() also similar to addLast() which create link to last element
        adjacencyList.get(destination).add(source);
    }

    public void print() {
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            int vertex = entry.getKey();
            System.out.println(vertex + " -->");
            for (int neighbour : entry.getValue()) {
                System.out.println(neighbour + " ");
            }
            System.out.println();
        }
    }

    public void breadthFirstSearch(int startVertex) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visit = new HashSet<>();

        queue.add(startVertex);
        visit.add(startVertex);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();//remove Old/First element
            System.out.println(currentVertex + " ");
            for (int neighbourVertex : adjacencyList.getOrDefault(currentVertex, new ArrayList<>())) {
                if (!visit.contains(neighbourVertex)) {
                    queue.add(neighbourVertex);
                    visit.add(neighbourVertex);
                }
            }
        }
    }

}
