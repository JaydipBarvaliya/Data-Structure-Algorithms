package com.leetcode.year_2020.graph.eulerian_path_circuit;

import com.leetcode.year_2020.graph.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.youtube.com/watch?v=8MpoO2zA2l4&t=604s
 *
 * @author neeraj on 28/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FindEulerianPathInDirectedGraph {

    public static void main(String[] args) {
        printEulerianPath(new int[][]{
                {1, 2},
                {1, 3},
                {2, 2},
                {2, 4},
                {2, 4},
                {3, 2},
                {3, 1},
                {3, 5},
                {4, 6},
                {4, 3},
                {5, 6},
                {6, 3}
        }, 7);
    }

    public static void printEulerianPath(int[][] edges, int N) {
        Graph graph = new Graph(N);
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1], true);
        }
        int[] indegree = graph.indegree;
        int[] outdegree = graph.outdegree;

        if (!hasEulerianPath(indegree, outdegree)) {
            System.out.println("There is No Eulerian Path.......");
            return;
        }

        // Now we know Eulerian Path Exist..... so let's print them
        // Now we need the startNode which is the only node which has
        int startNode = findStartNode(indegree, outdegree);

        List<Integer> result = new ArrayList<>();
        dfs(startNode, outdegree, graph, result);

        System.out.println(result);
    }

    private static void dfs(int startNode, int[] outdegree, Graph graph, List<Integer> result) {

        // While Current Node has any outgoing edges
        while (outdegree[startNode] != 0) {
            // Decrement the outgoing edge since we are visiting this edge
            outdegree[startNode] -= 1;
            for (Graph.GraphVertex adjacentVertex : graph.adjacencyListArr[startNode]) {
                if (outdegree[adjacentVertex.value] != 0)
                    dfs(adjacentVertex.value, outdegree, graph, result);
            }
        }
        // # All outgoing edges have been visited for this startNode, add it to start.
        result.add(0, startNode);
    }

    public static int findStartNode(int[] indegree, int[] outdegree) {
        int start = 0;
        for (int i = 0; i < indegree.length; i++) {
            // Unique start node
            if (outdegree[i] - indegree[i] == 1) {
                return i;
            }
            // Any node which has outdegree
            if (outdegree[i] > 0) {
                start = i;
            }
        }
        return start;
    }

    public static boolean hasEulerianPath(int[] indegree, int[] outdegree) {
        /**
         * A Directed graph can only has a Eulerian Path when at-most 1 vertex has
         * (indegree) - (outdegree) = 1 and at-most 1 vertex with (outdegree) - (indegree) = 1.
         * All other vertex have equal in-degree and out-degree.
         */
        int startNodes = 0;
        int endNodes = 0;
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] - outdegree[i] > 1 || outdegree[i] - indegree[i] > 1) {
                return false; // Not Satisfying required clause for Euler path.
            } else if (outdegree[i] - indegree[i] == 1) { // This can be out starting node.
                startNodes++;
            } else if (indegree[i] - outdegree[i] == 1) {
                endNodes++;
            }
        }
        return (startNodes == 0 && endNodes == 0) // When all Vertex have equal indegree and out-degree
                || (startNodes == 1 && endNodes == 1); // When there is 1 startNode and 1 endNode all other nodes are
        // just intermediate nodes.
    }
}
