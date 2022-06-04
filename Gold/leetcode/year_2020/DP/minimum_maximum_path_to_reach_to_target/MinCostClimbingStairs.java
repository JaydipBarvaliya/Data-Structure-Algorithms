package com.leetcode.year_2020.DP.minimum_maximum_path_to_reach_to_target;

import java.util.Arrays;

/**
 * @author neeraj on 05/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinCostClimbingStairs {

    public static void main(String[] args) {
        System.out.println(minCostClimbingStairs(new int[]{10, 15, 20}));
        System.out.println(minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }

    static int[] T; // Memorization

    public static int minCostClimbingStairs(int[] cost) {
        /**
         * So we can either take 2 steps. or 1 step and have to reach to top
         * So we will try both options and used memorization to solve pre-computed problems.
         *
         * So Stair case looks like this :
         *
         * 						      __________
         * 				         ___ | Final step
         *                  ___ | 20
         *             ___ | 15
         * _________ | 10
         * First step
         *
         */
        T = new int[cost.length];
        Arrays.fill(T, -1);
//        return Math.min(findMinCostClimbingStairs(cost, 0),
//                findMinCostClimbingStairs(cost, 1));

        /**
         * Now we can also solve this problem staring from the top..
         * The problem essentially says we have to reach either the last step
         * or the 2nd last step whoever is minimum....since once we reach there we don't
         * have to pay any other cost.
         *
         * So now if we start from last stair of 2nd last stair.
         * we can go all the way to bottom and figure out the min path to these stairs.
         * and we know question says we can directly jump on 1st or 2nd step[or 0th or 1st
         * stair if 0 indexed]
         */
        int totalStairs = cost.length - 1;
        // Min of reaching to last stair or 2nd last stair.
        return Math.min(findCostOfClimbingInTopDownWithMemorization(totalStairs, cost), findCostOfClimbingInTopDownWithMemorization(totalStairs - 1, cost));
    }

    private static int findCostOfClimbingInTopDownWithMemorization(int stairNumber, int[] cost) {
        if (stairNumber < 0) return 0; // if there are no stairs you don't need any cost.
        if (stairNumber == 0 || stairNumber == 1) {
            return cost[stairNumber]; // Since question says we can directly jump on any stair.
        }

        // Memorization
        if(T[stairNumber] != -1) return T[stairNumber];

        return T[stairNumber] =  cost[stairNumber] +
                Math.min(findCostOfClimbingInTopDownWithMemorization(stairNumber - 1, cost),
                        findCostOfClimbingInTopDownWithMemorization(stairNumber - 2, cost));
    }

    /**
     * We are doing bottom up approach.
     */
    private static int findMinCostClimbingStairsWithBottomUp(int[] cost, int stairNumber) {
        // If we reached or already on top... Min cost is just 0.
        if (stairNumber >= cost.length) return 0;

        if (T[stairNumber] != -1) return T[stairNumber];

        // Either we can take 2 steps or 1 step
        int costFromThisStairIfITake_1_Step = findMinCostClimbingStairsWithBottomUp(cost, stairNumber + 1);
        int costFromThisStairIfITake_2_Step = findMinCostClimbingStairsWithBottomUp(cost, stairNumber + 2);

        // We will obviously choose min cost to reach to top from this stair case.
        int costRequiredFromThisStairCaseToReachTop = cost[stairNumber] +
                Math.min(costFromThisStairIfITake_1_Step,
                        costFromThisStairIfITake_2_Step);
        return T[stairNumber] = costRequiredFromThisStairCaseToReachTop;
    }
}
