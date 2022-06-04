package com.leetcode.year_2020.DP;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/
 * <p>
 * Same Program can  be vice-versa for
 * https://www.geeksforgeeks.org/divide-the-array-in-k-segments-such-that-the-sum-of-minimums-is-maximized/
 * In this GeeksForGeeks problem we have to find [Maximum Of Minimum] vs the original problem of [Minimum of Maximum]
 * So we can easily tweak for the same.
 *
 * @author neeraj on 16/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumDifficultyOfJobSchedule {

    public static void main(String[] args) {
        System.out.println(minDifficulty(new int[]{6, 5, 4, 3, 2, 1}, 2));
        System.out.println(minDifficulty(new int[]{9, 9, 9}, 4));
        System.out.println(minDifficulty(new int[]{1, 1, 1}, 3));
        System.out.println(minDifficulty(new int[]{7, 1, 7, 1, 7, 1}, 3));
        System.out.println(minDifficulty(new int[]{11, 111, 22, 222, 33, 333, 44, 444}, 6));
    }

    static int t[][]; // Memorization // Why 2d? since we have 2 parameters which are changing startDay and totalDays left

    public static int minDifficulty(int[] difficulty, int d) {
        t = new int[difficulty.length + 1][d + 1];
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }
        if (difficulty.length < d) return -1; // In this case not every day will get one job.
        int startDayPointer = 0;
        return findMinUsingDFS(difficulty, startDayPointer, d);
    }

    public static int findMinUsingDFS(int[] difficulty, int startDayPointer, int numDaysLeft) {
        if (t[startDayPointer][numDaysLeft] != -1) return t[startDayPointer][numDaysLeft];
        if (numDaysLeft == 1) {
            // If only 1 day is present, then the
            // minimum difficulty can't be smaller than the maximum of all days.
            int max = Integer.MIN_VALUE;
            for (int i = startDayPointer; i < difficulty.length; i++) {
                max = Math.max(difficulty[i], max);
            }
            return t[startDayPointer][numDaysLeft] = max;
        }

        int maximumForIthDay = 0, answer = Integer.MAX_VALUE;
        // From Start pointer you can go max upto (total diff - noOfDays) since every day has to get some task to do.
        for (int i = startDayPointer; i <= difficulty.length - numDaysLeft; i++) {
            maximumForIthDay = Math.max(maximumForIthDay, difficulty[i]);
            answer = Math.min(answer, maximumForIthDay +
                    // Finding Minimum of maximum difficulty for next days.
                    findMinUsingDFS(difficulty, i + 1, numDaysLeft - 1)); // subtracting the day.
        }
        return t[startDayPointer][numDaysLeft] = answer;
    }
}
