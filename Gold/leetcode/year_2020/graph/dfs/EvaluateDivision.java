package com.leetcode.year_2020.graph.dfs;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author neeraj on 02/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class EvaluateDivision {

    public static void main(String[] args) {
//        List<List<String>> equations = Arrays.asList(
//                Arrays.asList("a", "b"),
//                Arrays.asList("b", "c")
//        );
//        List<List<String>> queries = Arrays.asList(
//                Arrays.asList("a", "c"),
//                Arrays.asList("b", "a"),
//                Arrays.asList("a", "e"),
//                Arrays.asList("a", "a"),
//                Arrays.asList("x", "x")
//        );
//        double[] result = calcEquation(equations, new double[]{2.0, 3.0}, queries);
//        LogUtil.printArray(result);

//        LogUtil.printArray(solve(
//                new String[][]{{"x1", "x2"}, {"x2", "x3"}, {"x3", "x4"}, {"x4", "x5"}},
//                new double[]{3.0, 4.0, 5.0, 6.0},
//                new String[][]{{"x1", "x5"}, {"x5", "x2"}, {"x2", "x4"}, {"x2", "x2"}, {"x2", "x9"}, {"x9", "x9"}}
//        ));

        LogUtil.printArray(solve(
                new String[][]{{"a", "b"}, {"b", "c"}, {"bc", "cd"}},
                new double[]{1.5, 2.5, 5.0},
                new String[][]{{"a", "c"}, {"c", "b"}, {"bc", "cd"}, {"cd", "bc"}}
        ));
    }

    private static double[] solve(String[][] equations, double[] values, String[][] queries) {
        List<List<String>> equationsList = new ArrayList<>();
        for (String[] equation : equations) {
            equationsList.add(Arrays.asList(equation[0], equation[1]));
        }
        List<List<String>> queriesList = new ArrayList<>();
        for (String[] query : queries) {
            queriesList.add(Arrays.asList(query[0], query[1]));
        }

        return calcEquation(equationsList, values, queriesList);
    }

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        /**
         * Given a / b = 2.0, b / c = 3.0.
         * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
         * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
         *
         * Reasoning :
         *  This problem is essentially asking us whether or not there is a path between any 2 nodes.
         *  So to elaborate a bit let me draw.
         *
         *  a ------ 2.0 --------> b  --------- 3.0 ---------------->c  [So cost of going from a to b is 2.0/- INR
         *                                                              and cost of going from b to c is 3.0/- INR
         *                                                              So what will be the cost of going from a to c]
         * Well well if you are travelling in delhi Metro that's 5.0 rupees but this equation is modelled in such a way
         * that cost of reaching from a to c [a/c] ==> 6.0
         *
         * Proof:
         * a/b = 2, b/c = 3.
         *
         * b = 3c.
         * replacing b in 1st equation : a/3c == 2 , So a/c ==> 6. That's what we discussed before... So essentially we
         * have to multiply the cost associated in the path whenever we have to reach from Node A to any other node.
         *
         * One more important thing to notice.... since we are multiplying the cost while going from "A" point to "B" point.
         * so we should divide the cost when coming from "B" point to "A" point.
         *
         *
         * c ------- 1/3 ----------> b ------ 1/2 -----------> a
         *
         * Proof:
         * a/b = 2, b/c = 3.
         *
         * a = 2*b
         * So b/a = 1/2.
         *
         * b = 3c
         * So c/b == 1/3.
         *
         * So while adding edge for a ---> b we should also add inverse edge b ---> a
         *
         * Also we need to worry about some edge cases need to reject them straight away.
         * When some unknown variable came into picture.
         * And when query is about divide by self.
         * a/a or b/b or c/c ===> Answer for these is always 1.0
         * and for unknown -1.0
         */

        // Now let's build the graph.
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);
        Set<String> visited = new HashSet<>();

        double[] result = new double[queries.size()];
        int resultCounter = 0;
        for (List<String> query : queries) {
            String u = query.get(0), v = query.get(1);
            if (graph.containsKey(u) && graph.containsKey(v)) {
                visited = new HashSet<>();
                result[resultCounter] = doDFSAndCalculateCost(u, v, 1.0, visited, graph);
            } else { // Invalid query
                result[resultCounter] = -1.0;
            }
            resultCounter++;
        }
        return result;
    }

    private static double doDFSAndCalculateCost(String source, String destination,
                                                double cost,
                                                Set<String> visited,
                                                Map<String, Map<String, Double>> graph) {
        if (source.equals(destination)) {
            return cost;
        }
        visited.add(source);
        Map<String, Double> adjacentNeighbours = graph.get(source);
        for (String adjacent : adjacentNeighbours.keySet()) {
            if (!visited.contains(adjacent)) {
                double result = doDFSAndCalculateCost(adjacent, destination, cost * adjacentNeighbours.get(adjacent), visited, graph);
                if (result != -1) {
                    return result;
                }
            }
        }
        return -1;
    }

    public static Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        String u, v;

        int counter = 0;
        for (List<String> equation : equations) {
            u = equation.get(0);
            v = equation.get(1);

            graph.putIfAbsent(u, new HashMap<>()); // Directed Edge from U to V
            graph.putIfAbsent(v, new HashMap<>()); // Inverse Edge from V to U

            graph.get(u).put(v, values[counter]);
            graph.get(v).put(u, 1 / values[counter]); // Inverse edge....
            counter++;
        }
        return graph;
    }
}
