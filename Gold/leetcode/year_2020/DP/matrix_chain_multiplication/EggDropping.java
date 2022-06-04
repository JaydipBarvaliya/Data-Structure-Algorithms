package com.leetcode.year_2020.DP.matrix_chain_multiplication;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/egg-dropping-puzzle-dp-11/
 *
 * @author neeraj on 12/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 * <p>
 * Find Minimum Number of Attempts using Best Strategy to find ThreshHold floor in the worst case.
 */
public class EggDropping {

    public static void main(String[] args) {
        System.out.println(findMinAttemptsInWhichWeCanFigureOutOnWhichFloorTheEggWillBreak(1, 3));
        System.out.println(findMinAttemptsInWhichWeCanFigureOutOnWhichFloorTheEggWillBreak(1, 2));
        System.out.println(findMinAttemptsInWhichWeCanFigureOutOnWhichFloorTheEggWillBreak(2, 6));
        System.out.println(findMinAttemptsInWhichWeCanFigureOutOnWhichFloorTheEggWillBreak(3, 26));
        System.out.println(findMinAttemptsInWhichWeCanFigureOutOnWhichFloorTheEggWillBreak(8, 1000));
    }

    static int[][] memorization;

    public static int findMinAttemptsInWhichWeCanFigureOutOnWhichFloorTheEggWillBreak(int eggs, int floors) {
        if (eggs == 0) return Integer.MAX_VALUE; // if there are 0 eggs there is no way you can solve this problem.
        memorization = new int[eggs + 1][floors + 1];
        for (int[] row : memorization) {
            Arrays.fill(row, -1);
        }
        return dropEgg(eggs, floors);
    }

    private static int dropEgg(int eggs, int floors) {
        if (floors == 0 || floors == 1) return floors;
        if (eggs == 1) return floors;

        if (memorization[eggs][floors] > -1) return memorization[eggs][floors];

        int MIN_ATTEMPTS = Integer.MAX_VALUE;

        // Why k = 1 since it's not array which can start from 0th floor/index
        // hence we start from floor 1 and go till end of floor.
        for (int k = 1; k <= floors; k++) {
            // if egg breaks at k'th floor
            // At this k we tried so 1 attempt is consumed.
            // Now at this either a egg can break or can't break and since we want worst case so we choose Max or either 2

            int floorBelowK = 0;
            if (memorization[eggs - 1][k - 1] > -1) {
                floorBelowK = memorization[eggs - 1][k - 1];
            } else {
                floorBelowK = dropEgg(eggs - 1, k - 1);
                memorization[eggs - 1][k - 1] = floorBelowK;
            }

            int floorAboveK = 0;
            if (memorization[eggs][floors - k] > -1) {
                floorAboveK = memorization[eggs][floors - k];
            } else {
                floorAboveK = dropEgg(eggs, floors - k);
                memorization[eggs][floors - k] = floorAboveK;
            }

            // why +1 because we are attempting at k.
            int worstCase = 1 + Math.max(floorBelowK, floorAboveK);
            MIN_ATTEMPTS = Math.min(MIN_ATTEMPTS, worstCase);
        }
        return memorization[eggs][floors] = MIN_ATTEMPTS;
    }
}
