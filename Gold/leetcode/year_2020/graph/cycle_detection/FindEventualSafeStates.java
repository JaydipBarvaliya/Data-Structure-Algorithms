package com.leetcode.year_2020.graph.cycle_detection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/find-eventual-safe-states/
 *
 * @author neeraj on 31/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FindEventualSafeStates {

    public static void main(String[] args) {
        System.out.println(eventualSafeNodes(new int[][]{
                {1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}
        }));
    }

    enum Color {
        WHITE, BLACK, GREY
    }

    public static List<Integer> eventualSafeNodes(int[][] input) {
        /**
         * So essentially this question is saying
         * "so that for any choice of where to walk, we must have stopped at a terminal node in less than K steps."
         * which basically means we should not get stuck in a cycle.
         */
        List<Integer> eventualSafeNodes = new ArrayList<>();
        if (input == null || input.length == 0) return eventualSafeNodes;

        Color[] vertexColors = new Color[input.length];
        Arrays.fill(vertexColors, Color.WHITE);

        for (int i = 0; i < vertexColors.length; i++) {
            if (!dfsHasCycle(i, input, vertexColors)) {
                eventualSafeNodes.add(i);
            }
        }
        return eventualSafeNodes;
    }

    private static boolean dfsHasCycle(int vertex, int[][] input, Color[] vertexColors) {
        vertexColors[vertex] = Color.GREY;

        for(int adjacent: input[vertex]) {
            if(vertexColors[adjacent] == Color.GREY) return true; // It's a cycle.
            if(vertexColors[adjacent] == Color.WHITE && dfsHasCycle(adjacent, input, vertexColors)) return true;
        }

        vertexColors[vertex] = Color.BLACK;
        return false;
    }
}
