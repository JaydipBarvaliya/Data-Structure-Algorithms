package com.leetcode.year_2020.Greedy.interval_scheduling;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/non-overlapping-intervals/
 * <p>
 * Given a collection of intervals, find the minimum number of intervals you need to remove
 * to make the rest of the intervals non-overlapping.
 *
 * @author neeraj on 16/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NonOverLappingIntervals {

    public static void main(String[] args) {
        System.out.println(eraseOverlapIntervals(new int[][]{
                {1, 2}, {2, 3}, {3, 4}, {1, 3}
        }));

        System.out.println(eraseOverlapIntervals(new int[][]{
                {1, 2}, {1, 2}, {1, 2}
        }));
        System.out.println(eraseOverlapIntervals(new int[][]{
                {1, 2}, {2, 3}
        }));
        System.out.println(eraseOverlapIntervals(new int[][]{
                {1, 100}, {11, 22}, {1, 11}, {2, 12}
        }));

        System.out.println(eraseOverlapIntervals(new int[][]{
                {10, 16}, {2, 8}, {1, 6}, {7, 12}
        }));
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        /**
         * This problem is the variation of {@link MergeIntervals}
         * In this problem we are asked to remove minimum number of interval
         * to make the whole intervals non-overlapping.
         *
         * {1, 2}, {2, 3}, {3, 4}, {1, 3}
         *
         * We have to just remove 1 interval which is {1,3}. to make it non-overlapping.
         *
         * 1. We have to sort the intervals based on their Finish Time in Ascending Order....
         *    Reason: Intervals with earliest endTime gives us more space to accommodate extra intervals, v/s if we choose the interval
         *    with End Time as largest we will have no space to accommodate more intervals.
         * 2. Here also we will take baseInterval and do the comparision with other intervals. Now in this variation we have to reject
         *    the overlapping interval.
         *    So if(currInterval[startTime] < baseInterval[endTime]) currentInterval has to be rejected....
         *    we will only consider those interval where currInterval[start] > baseInterval.
         */
        Arrays.sort(intervals, Comparator.comparingInt(A -> A[1]));
        int[] baseInterval = intervals[0];
        int totalNonOverlappingInterval = 1;

        for (int i = 1; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];

            // If currentInterval start > baseInterval[end])
            if (currentInterval[0] >= baseInterval[1]) {
                // Here we are extending the baseInterval.
                baseInterval[1] = Math.max(currentInterval[1], baseInterval[1]);
                totalNonOverlappingInterval++;
            }
        }
        // intervals.length - totalNonOverlappingInterval; == Total Overlapping Intervals.
        return intervals.length - totalNonOverlappingInterval;
    }
}
