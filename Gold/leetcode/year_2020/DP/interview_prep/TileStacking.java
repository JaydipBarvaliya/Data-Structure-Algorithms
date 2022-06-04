package com.leetcode.year_2020.DP.interview_prep;

/**
 * @author neeraj on 08/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class TileStacking {

    public static void main(String[] args) {
        System.out.println(findWaysWeCanStackTile(3, 3, 1));
        System.out.println(findWaysWeCanStackTile(3, 3, 2));
    }

    public static int findWaysWeCanStackTile(int heightOfTower, int tiles, int maxAllowedTileOfParticularHeight) {
        if (heightOfTower < 0 || tiles < 0) {
            return 0;
        }

        if (heightOfTower == 0) {
            return 1; // we just have 1 way, but not choosing any more tile
        }

        // We will try for each tile upto maxAllowedTileOfParticularHeight times
        // either by choosing it or not choosing it.
        int totalWays = 0;
        for (int i = 0; i <= maxAllowedTileOfParticularHeight; i++) {
            totalWays += findWaysWeCanStackTile(heightOfTower - i, tiles - 1, maxAllowedTileOfParticularHeight);
        }
        return totalWays;
    }
}
