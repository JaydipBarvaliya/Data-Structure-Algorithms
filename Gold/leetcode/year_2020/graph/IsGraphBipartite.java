package com.leetcode.year_2020.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author neeraj on 26/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class IsGraphBipartite {

    public static void main(String[] args) {
        /**
         * The graph looks like this:
         * 0----1
         * |    |
         * |    |
         * 3----2
         * We can divide the vertices into two groups: {0, 2} and {1, 3}.
         */
        System.out.println(isBipartite(new int[][]{
                {1, 3},
                {0, 2},
                {1, 3},
                {0, 2}
        }));

        /**
         * The graph looks like this:
         * 0----1
         * | \  |
         * |  \ |
         * 3----2
         * We cannot find a way to divide the set of nodes into two independent subsets
         */
        System.out.println(isBipartite(new int[][]{
                {1, 2, 3},
                {0, 2},
                {0, 1, 3},
                {0, 2}
        }));
    }

    enum COLOR {RED, BLUE, UNCOLORED}

    ;

    public static boolean isBipartite(int[][] graph) {
        /**
         * So this problem is basically to find out that can we put two vertex of same edge
         * in different set.
         * We can also think of coloring the vertex of edges such that no 2 vertex of same edge have the same color.
         * Set(A) will contain all vertex painted as RED
         * Set(B) will contain all vertex painted as BLUE.
         *
         * So can we divide the graph in SET(A) and SET(B) is the question.
         * We will simply do a BFS and also compare the color of adjacent vertex, they shouldn't be the same.
         */
        // Let's first mark all vertex as UNCOLORED.
        COLOR[] vertexColor = setAllVertexesToUncolored(graph);
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();

        // Since graph can be disjoint we need to do BFS from each node.
        for (int i = 0; i < graph.length; i++) {
            if (!seen.contains(i)) {
                int startVertex = i;

                vertexColor[startVertex] = COLOR.BLUE; // We mark the color of startVertex with BLUE
                queue.add(startVertex);
                seen.add(startVertex);

                while (!queue.isEmpty()) {
                    int polledVertex = queue.poll();

                    for (int adjacentVertex : graph[polledVertex]) {
                        COLOR adjacentCOLOR = vertexColor[adjacentVertex];

                        if (adjacentCOLOR == COLOR.UNCOLORED) {
                            vertexColor[adjacentVertex] = getOppositeColor(vertexColor[polledVertex]);
                        } else if (adjacentCOLOR == vertexColor[polledVertex]) { // We found vertex of the same edge with same color
                            // this means it can't be divided into 2 subsets.
                            return false;
                        }

                        if (!seen.contains(adjacentVertex)) {
                            queue.add(adjacentVertex);
                            seen.add(adjacentVertex);
                        }
                    }
                }
            }
        }
        return true;
    }

    private static COLOR getOppositeColor(COLOR adjacentCOLOR) {
        return adjacentCOLOR == COLOR.RED ? COLOR.BLUE : COLOR.RED;
    }

    private static COLOR[] setAllVertexesToUncolored(int[][] graph) {
        COLOR[] vertexes = new COLOR[graph.length];
        for (int i = 0; i < vertexes.length; i++) {
            vertexes[i] = COLOR.UNCOLORED;
        }
        return vertexes;
    }
}
