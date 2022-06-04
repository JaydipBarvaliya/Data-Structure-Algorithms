package com.leetcode.year_2020.graph;

/**
 * @author neeraj on 26/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class GraphUser {

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(1, 2, false);
        graph.addEdge(1, 5, false);
        graph.addEdge(1, 4, false);
        graph.addEdge(2, 3, false);
        graph.addEdge(3, 4, false);

        graph.dfs(graph, 1);
        graph.bfs(graph, 1);
        graph.dfsUsingStack(graph, 1);
    }
}
