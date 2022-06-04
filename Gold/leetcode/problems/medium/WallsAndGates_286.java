package com.leetcode.problems.medium;

import static com.geeksforgeeks.array.Rotate2DMatrix.print2DArray;

/**
 * @author neeraj on 07/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class WallsAndGates_286 {

    private static Integer INFINITY = Integer.MAX_VALUE;
    private static Integer GATE = 0;

    public static void main(String[] args) {
        int[][] rooms = new int[][]{
                {INFINITY, -1, 0, INFINITY},
                {INFINITY, INFINITY, INFINITY, -1},
                {INFINITY, -1, INFINITY, -1},
                {0, -1, INFINITY, INFINITY},
        };
        wallAndGates(rooms);
        print2DArray(rooms);
    }

    public static void wallAndGates(int[][] rooms) {
        int DISTANCE_FROM_GATE = 0;
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                // If this is the gate let's more around
                DISTANCE_FROM_GATE = 0;
                if (GATE == rooms[i][j]) {
                    doDFS(rooms, i, j, DISTANCE_FROM_GATE);
                }
            }
        }
    }

    public static void doDFS(int[][] rooms, int i, int j, int DISTANCE_FROM_GATE) {
        rooms[i][j] = Math.min(rooms[i][j], DISTANCE_FROM_GATE);

        // From this GATE we can go only in 4 directions
        if (isSafe(rooms, i - 1, j, DISTANCE_FROM_GATE + 1)) {
            doDFS(rooms, i - 1, j, DISTANCE_FROM_GATE + 1);
        }
        if (isSafe(rooms, i, j + 1, DISTANCE_FROM_GATE + 1)) {
            doDFS(rooms, i, j + 1, DISTANCE_FROM_GATE + 1);
        }
        if (isSafe(rooms, i + 1, j, DISTANCE_FROM_GATE + 1)) {
            doDFS(rooms, i + 1, j, DISTANCE_FROM_GATE + 1);
        }
        if (isSafe(rooms, i, j - 1, DISTANCE_FROM_GATE + 1)) {
            doDFS(rooms, i, j - 1, DISTANCE_FROM_GATE + 1);
        }
    }

    private static boolean isSafe(int[][] rooms, int i, int j, int DISTANCE_FROM_GATE) {
        return i >= 0 && j >= 0 && i < rooms.length && j < rooms[0].length
                && rooms[i][j] != -1 && rooms[i][j] != 0
                && rooms[i][j] > DISTANCE_FROM_GATE;
    }
}
