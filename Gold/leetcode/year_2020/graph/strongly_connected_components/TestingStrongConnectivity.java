package com.leetcode.year_2020.graph.strongly_connected_components;

import com.util.LogUtil;

import java.util.*;

/**
 * @author neeraj on 03/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class TestingStrongConnectivity {

    public static void main(String[] args) {
//        check(new Integer[][]{
//                {0, 1},
//                {1, 2},
//                {2, 3},
//                {3, 0},
//                {2, 4},
//                {4, 2},
//        }, 5);

        /**
         *       1
         *      /\
         *     //
         *    //
         *   0
         *   \\
         *    \\
         *     \/
         *      2
         */
        check(new Integer[][]{
                {0, 1},
                {0, 2}
        }, 3);

        check(new Integer[][]{
                {0, 1},
                {2, 0}
        }, 3);
//
//        check(new Integer[][]{
//                {0, 1},
//                {1, 2},
//                {2, 0}
//        }, 3);

        check(new Integer[][]{
                {1, 0},
                {0, 2},
                {2, 1},
                {0, 3},
                {3, 4}
        }, 5);
    }

    public static void check(Integer[][] edges, int totalVertex) {
        List<List<Integer>> directedEdges = new ArrayList<>();
        for (Integer[] edge : edges) {
            directedEdges.add(Arrays.asList(edge[0], edge[1]));
        }
        LogUtil.logIt("Is Graph Strongly Connected......" + isGraphStronglyConnected(directedEdges, totalVertex));
        printStronglyConnectedComponents(directedEdges, totalVertex);
    }

    public static boolean isGraphStronglyConnected(List<List<Integer>> directedEdges, int totalVertex) {
        /**
         *
         *                        ---------
         *                       ||       ||
         * 0 -----> 1 -----> 2 --||       \/
         *  /\              //   /\        4
         *   \\            //    ||       ||
         *    \\          //     ---------
         *     \\        //
         *      \\      \/
         *          3
         * Here if we notice we can reach from any vertex to any other vertex.
         * So this is a strongly connected graph, Now let's try to solve it.
         */
        // We will first prepare adjacency list map.
        Map<Integer, List<Integer>> graph = buildGraph(directedEdges);


        /**
         * To check if Graph is strongly connected we need to check if every node is visited from every other node
         * i.e. do BFS or DFS from every node.
         * Time Complexity (O|V|*(|V|+|E|)) can we reduce this, yes of-course if we can just do a DFS from 1 node
         * Assume that node is 0.
         *
         * If we are able to reach all nodes from 0 and then if all nodes are able to reach 0 then we can say graph is strongly connected.
         * How,let me show you.
         *
         *   All Nodes which can reach "0"---------------- 0 --------------All Nodes which 0 can reach.
         *                            (0 is A C T I N G - A S - B R I D G E)
         *
         * So if we have to check a path between let's say 3 to 4..
         * what we have to essential check that can I reach
         * 0---to---> 4
         * and can i also reach 3 ---> 0
         *
         *              0  ==================> 4
         *              /\
         *               \\
         *               \\
         *                \\
         *                  3
         * if both are yes then essentially we can travel from 3 to 4.
         *
         */

        // let's do the DFS from 0 to check if 0 can reach every one.
        Set<Integer> visited = new HashSet<>();
        doDFS(graph, 0, visited);

        if (visited.size() != totalVertex) { // Means 0 can't reach to all nodes
            // So this graph can't be strongly connected.
            return false;
        }

        // Now we know at this stage that i can reach everywhere from 0, so lets check if everyone else can reach 0 or not.
        // for this we need to reverse the directed edges
        /**
         * So graph is now
         *
         *                   ==============
         *                  \/            ||
         * 0 <----- 1 <-----2----         4
         *  \\             /\   ||       /\
         *   \\           //    ||       ||
         *    \\         //     ---------
         *     \\       //
         *      \/     //
         *          3
         */
        graph = reverseGraph(graph);

        // Reset the visited set
        visited = new HashSet<>();
        doDFS(graph, 0, visited);
        return visited.size() == totalVertex;
    }

    /**
     * Method which will reverse the graph edges.
     */
    private static Map<Integer, List<Integer>> reverseGraph(Map<Integer, List<Integer>> graph) {
        Map<Integer, List<Integer>> reverseGraph = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            for (Integer vertex : entry.getValue()) {
                reverseGraph.putIfAbsent(vertex, new ArrayList<>());
                reverseGraph.get(vertex).add(entry.getKey());
            }
        }
        return reverseGraph;
    }

    private static Map<Integer, List<Integer>> buildGraph(List<List<Integer>> directedEdges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (List<Integer> edge : directedEdges) {
            graph.putIfAbsent(edge.get(0), new ArrayList<>());
            graph.get(edge.get(0)).add(edge.get(1));
        }
        return graph;
    }

    public static void printStronglyConnectedComponents(List<List<Integer>> directedEdges, int totalVertex) {
        /**
         * Refer Graph from here.
         * https://www.geeksforgeeks.org/strongly-connected-components/
         */
        Map<Integer, List<Integer>> graph = buildGraph(directedEdges);

        Stack<Integer> vertexSortedInFinishOrderOfTheirTime = new Stack<>();
        boolean[] visited = new boolean[totalVertex];
        doDFSAndStoreIntoStack(graph, 0, vertexSortedInFinishOrderOfTheirTime, visited, false);


        // Now reverse the graph.
        graph = reverseGraph(graph);
        visited = new boolean[totalVertex];
        while (!vertexSortedInFinishOrderOfTheirTime.isEmpty()) {
            if (!visited[vertexSortedInFinishOrderOfTheirTime.peek()]) {
                doDFSAndStoreIntoStack(graph, vertexSortedInFinishOrderOfTheirTime.pop(),
                        null, visited, true);
                LogUtil.newLine();
            }
        }
    }

    private static void doDFSAndStoreIntoStack(Map<Integer, List<Integer>> graph, int sourceVertex,
                                               Stack<Integer> vertexSortedInFinishOrderOfTheirTime, boolean[] visited, boolean shouldPrint) {
        if (shouldPrint) {
            System.out.print(sourceVertex + "--->");
        }
        visited[sourceVertex] = true;
        if (graph.get(sourceVertex) != null) {
            for (Integer adjacentVertex : graph.get(sourceVertex)) {
                if (!visited[adjacentVertex]) {
                    doDFSAndStoreIntoStack(graph, adjacentVertex, vertexSortedInFinishOrderOfTheirTime, visited, shouldPrint);
                }
            }
        }
        if (!shouldPrint) {
            vertexSortedInFinishOrderOfTheirTime.add(sourceVertex);
        }
    }

    private static void doDFS(Map<Integer, List<Integer>> graph, int sourceVertex, Set<Integer> visited) {
        visited.add(sourceVertex);

        if (graph.get(sourceVertex) != null) {
            for (Integer adjacentVertex : graph.get(sourceVertex)) {
                if (!visited.contains(adjacentVertex)) {
                    doDFS(graph, adjacentVertex, visited);
                }
            }
        }
    }
}
