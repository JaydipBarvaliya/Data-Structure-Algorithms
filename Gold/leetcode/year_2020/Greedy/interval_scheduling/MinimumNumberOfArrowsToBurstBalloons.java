package com.leetcode.year_2020.Greedy.interval_scheduling;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author neeraj on 17/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumNumberOfArrowsToBurstBalloons {

    public static void main(String[] args) {
        System.out.println(findMinArrowShots(new int[][]{
                {10, 16}, {2, 8}, {1, 6}, {7, 12}
        }));
        System.out.println(findMinArrowShots(new int[][]{
                {1, 2}, {3, 4}, {5, 6}, {7, 8}
        }));
        System.out.println(findMinArrowShots(new int[][]{
                {0, 10}, {1, 2}, {3, 5}
        }));


    }

    public static int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        /**
         * This is again a variation of interval_scheduling Greedy problem
         * In this we have to find min number of arrows so that we can shoot all balloons.
         *
         * [[10,16], [2,8], [1,6], [7,12]]
         *
         * Here we need 2 arrows : 1 at height 6. to shoot [1,6],[2,8]
         * Second arrow at 11 to shoot [7,12] [10,16]
         *
         * Another example :
         * [[1,2][3,4][5,6][7,8]]
         *
         * Here none of these are over lapping hence we need 4 arrows to shot.
         *
         * 1. Sort them with the startPoint/Height so that we know from how low we have to shoot the arrow in order
         *      to cover that balloon.
         * 2. If we find any overlapping balloon..... then we should shoot at a highest possible height which
         *      will cover both.
         *      i.e. baseBalloon[upperHeight] = Math.min(baseBalloon[upperHeight], currBalloon[upperHeight]);
         */
        Arrays.sort(points, Comparator.comparingInt(A -> A[0]));

        int[] baseBalloon = points[0];
        int MIN_ARROW_SHORTS = 1;

        for (int i = 1; i < points.length; i++) {
            int[] currBalloon = points[i];

            // CurrBalloon startsPoint <= baseBalloon endPoint
            // then this currBalloon can be burst with the same arrow used for BaseBalloon
            if (currBalloon[0] <= baseBalloon[1]) {
                // Now the interesting part is at which height we should fire the arrow so that both can be burst
                // together
                baseBalloon[1] = Math.min(baseBalloon[1], currBalloon[1]);
            } else {
                MIN_ARROW_SHORTS++;
                baseBalloon = currBalloon;
            }
        }
        return MIN_ARROW_SHORTS;
    }
}
