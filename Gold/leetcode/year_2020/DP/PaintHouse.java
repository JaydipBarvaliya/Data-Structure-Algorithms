package com.leetcode.year_2020.DP;

/**
 * https://www.lintcode.com/problem/515/
 * 515 · Paint House
 * <p>
 * Again inspiration from https://www.youtube.com/watch?v=kh48JLieeW8
 * <p>
 * I/p [[14,2,11],[11,14,5],[14,3,10]]
 * <p>
 * We have 3 houses and cost of painting the color
 * <p>
 * Red[14], Blue[2], and green[11]
 */
public class PaintHouse {

    public static void main(String[] args) {
        System.out.println(minCost(new int[][]{
                {14, 2, 11},
                {11, 14, 5},
                {14, 3, 10}
        }));
    }

    public static int minCost(int[][] costs) {
        /**
         * This problem is somewhat similar to {@link HouseRobber#robWithIncludeExcludeStrategy(int[])}
         * here also we can't color 2 adjacent houses with same color, and we need to paint in the min cost
         *
         * Again, at every index start from 1[0-based Index], we need to make check if we color this house RED
         * then i need to get the min Cost of painting either Blue/Green on the i-1th house
         *
         * I/p [[14,2,11],[11,14,5],[14,3,10]]
         *
         *          House_1   |  House_2                             |           House_3                |
         *--------------------------------------------------------------------------------------------------------------
         * RED        14      |  Min(House_1[Blue or Green]) + 11    | Min(House_2[Blue or Green]) + 14
         *--------------------------------------------------------------------------------------------------------------
         * BLUE       2      |  Min(House_1[Blue or Green]) + 14    | Min(House_2[RED or Green])  + 3
         *--------------------------------------------------------------------------------------------------------------
         * GREEN     11      |  Min(House_1[Blue or Green]) + 5     | Min(House_2[Blue or RED])  + 10
         */

        int red = costs[0][0];
        int blue = costs[0][1];
        int green = costs[0][2];

        for (int house = 1; house < costs.length; house++) {
            int newRed = Math.min(blue, green) + costs[house][0];
            int newBlue = Math.min(red, green) + costs[house][1];
            int newGreen = Math.min(red, blue) + costs[house][2];

            red = newRed;
            blue = newBlue;
            green = newGreen;
        }
        return Math.min(red, Math.min(blue, green));
    }
}
