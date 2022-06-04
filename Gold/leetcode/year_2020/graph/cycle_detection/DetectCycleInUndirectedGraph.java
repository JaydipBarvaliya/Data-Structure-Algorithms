package com.leetcode.year_2020.graph.cycle_detection;

import com.leetcode.year_2020.graph.Graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author neeraj on 01/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class DetectCycleInUndirectedGraph {

    public static void main(String[] args) {
        /**
         *    1------2--------3
         *          ||        ||
         *          5---------4
         *
         * Here we have a cycle
         */
        Graph graph = new Graph(6); // Why 6 since adjacentListArr is 0 basedIndex.
        graph.addEdge(1, 2, false);
        graph.addEdge(2, 3, false);
        graph.addEdge(3, 4, false);
        graph.addEdge(4, 5, false);
        graph.addEdge(5, 2, false);

        System.out.println(ifUndirectedGraphHasCycle(graph));
    }

    public static boolean ifUndirectedGraphHasCycle(Graph graph) {
        Set<Integer> visited = new HashSet<>();
        int parentForSource;
        for (int sourceVertex = 1; sourceVertex < graph.totalVertices; sourceVertex++) {
            parentForSource = -1; // Initially there is no parent since we are jumping on the sourceVertex for the very first time.
            if (!visited.contains(sourceVertex)) {
                if (doDFS(graph.adjacencyListArr, sourceVertex, parentForSource, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean doDFS(List<Graph.GraphVertex>[] adjacencyListArr, int sourceVertex, int parent, Set<Integer> visited) {
        visited.add(sourceVertex);

        for (Graph.GraphVertex adjacent : adjacencyListArr[sourceVertex]) {
            if (adjacent.value == parent) continue; // if we have come from parent it's not a cycle.

            if (visited.contains(adjacent.value)) { // We found a cycle
                return true;
            }
            if (doDFS(adjacencyListArr, adjacent.value, sourceVertex, visited)) {
                return true;
            }
        }
        return false;
    }
}
