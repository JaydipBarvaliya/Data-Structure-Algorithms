package com.leetcode.year_2020.DP;

/**
 * https://www.lintcode.com/problem/516/
 * 516 · Paint House II
 */
public class PaintHouse2 {

    public static void main(String[] args) {
        System.out.println(minCost(new int[][]{
                {14, 2, 11},
                {11, 14, 5},
                {14, 3, 10}
        }));

        System.out.println(minCost(new int[][]{
                {5}
        }));
    }

    public static int minCost(int[][] costs) {
        if(costs.length == 0) return 0;
        /**
         * This problem is somewhat similar to {@link HouseRobber#robWithIncludeExcludeStrategy(int[])}
         * and {@link PaintHouse}
         * here also we can't color 2 adjacent houses with same color, and we need to paint in the min cost
         *
         * The only catch here is we have k colors to choose from and not red/blue/green, so we can take array instead of
         * defining 3 variables
         */

        int[] colorsCost = new int[costs[0].length];
        for (int i = 0; i < costs[0].length; i++) {
            colorsCost[i] = costs[0][i];
        }

        for (int house = 1; house < costs.length; house++) {
            int[] newColorCost = new int[costs[0].length];
            for (int color = 0; color < costs[0].length; color++) {
                newColorCost[color] = findMin(colorsCost, color) + costs[house][color];
            }
            colorsCost = newColorCost;
        }
        return findMin(colorsCost, -1);
    }

    public static int findMin(int[] arr, int skip) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (i == skip) continue;
            min = Math.min(min, arr[i]);
        }
        return min;
    }
}
