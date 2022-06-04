package com.leetcode.year_2020.graph.dfs;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SumOfDistanceInTrees {

    public static void main(String[] args) {
        LogUtil.printArray(sumOfDistancesInTree(6, new int[][]{
                {0, 1},
                {0, 2},
                {2, 3},
                {2, 4},
                {2, 5}
        }));

        LogUtil.printArray(sumOfDistancesInTree(6, new int[][]{
                {0, 1},
                {1, 2},
                {2, 3},
                {3, 4}
        }));
    }

    /**
     * Definitely a very hard problem and a very, very tricky solution.
     * Inspired by https://www.youtube.com/watch?v=dkPYrvq5EmY
     * <p>
     * # Parent_SUM - NO OF CLOSER NODES + NO OF FARTHER NODES
     *
     * @param n
     * @param edges
     * @return
     */
    static int distance = 0;
    static Map<Integer, Integer> subtreeNodes;

    public static int[] sumOfDistancesInTree(int n, int[][] edges) {
        final Map<Integer, List<Integer>> graph = buildGraph(edges);
        int[] distanceSum = new int[n];
        if(edges.length == 0) return distanceSum;
        Set<Integer> visited = new HashSet<>();
        subtreeNodes = new HashMap<>();
        distance = 0;

        dfs(0, visited, graph, 0);
        distanceSum[0] = distance;
        visited = new HashSet<>();
        dfs2(0, visited, graph, subtreeNodes, distanceSum);
        return distanceSum;
    }

    public static void dfs2(int source, Set<Integer> visited,
                            Map<Integer, List<Integer>> graph,
                            Map<Integer, Integer> subtreeNodes, int[] distanceSum) {
        visited.add(source);
        for (Integer adjacent : graph.get(source)) {
            if (visited.contains(adjacent)) continue;
            distanceSum[adjacent] = distanceSum[source] - subtreeNodes.get(adjacent) + (subtreeNodes.size() - subtreeNodes.get(adjacent));
            dfs2(adjacent, visited, graph, subtreeNodes, distanceSum);
        }
    }

    public static int dfs(int source, Set<Integer> visited,
                          Map<Integer, List<Integer>> graph, int currentDistance) {
        visited.add(source);
        distance += currentDistance;
        int nodes = 1;
        if (graph.containsKey(source)) {
            for (Integer adjacent : graph.get(source)) {
                if (!visited.contains(adjacent)) {
                    nodes += dfs(adjacent, visited, graph, currentDistance + 1);
                }
            }
        }
        subtreeNodes.put(source, nodes);
        return nodes;
    }

    private static Map<Integer, List<Integer>> buildGraph(int[][] edges) {
        final Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }
}
