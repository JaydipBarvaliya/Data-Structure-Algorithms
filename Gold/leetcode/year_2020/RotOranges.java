package com.leetcode.year_2020;

import java.util.HashSet;
import java.util.Set;

/**
 * @author neeraj on 25/03/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RotOranges {

    public static void main(String[] args) {
        int arr[][] = {{2, 1, 0, 2, 1},
                {1, 0, 1, 2, 1},
                {1, 0, 0, 2, 1}};
        System.out.println(orangesRotting(arr));
    }

    private static int orangesRotting(int[][] grid) {
        Set<String> freshOranges = new HashSet<>();
        Set<String> rottenOranges = new HashSet<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    freshOranges.add("" + i + j);
                } else if (grid[i][j] == 2) {
                    rottenOranges.add("" + i + j);
                }
            }
        }

        int minMinutes = 0;
        // Right, Bottom, Left, Top
        int[][] allDirections = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (freshOranges.size() > 0) {
            Set<String> infectedOranges = new HashSet<>(); // This is the intermediary stage which gets affected by actual rotten oranges

            for (String s : rottenOranges) {
                // Get the Coordinates of next
                int i = s.charAt(0) - '0';
                int j = s.charAt(1) - '0';

                for (int[] direction : allDirections) {
                    int nextI = i + direction[0];
                    int nextJ = j + direction[1];

                    if (freshOranges.contains("" + nextI + nextJ)) {
                        // Remove them from Fresh Oranges lot;
                        freshOranges.remove("" + nextI + nextJ);
                        infectedOranges.add("" + nextI + nextJ);
                    }
                }
            }
            if (infectedOranges.size() == 0) {
                return -1;
            }
            rottenOranges = infectedOranges;
            minMinutes++;
        }
        return minMinutes;
    }

}
