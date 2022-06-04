package com.leetcode.year_2020.Greedy.interval_scheduling;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/merge-intervals/
 * <p>
 * <p>
 * Given a collection of intervals, merge all overlapping intervals.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 *
 * @author neeraj on 16/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MergeIntervals {

    public static void main(String[] args) {
        /**
         *
         * I/p : [[1,4],[2,3]]
         * o/P: [[1,4]]
         *
         * I/p: [[2,3],[4,5],[6,7],[8,9],[1,10]]
         * O/p: [[1,10]]
         *
         * I/p: [[1,4],[0,4]]
         * O/p: [[0,4]]
         */
        LogUtil.printMultiDimensionArray(merge(new int[][]{
                {1, 4}, {2, 3}
        }));

        LogUtil.printMultiDimensionArray(merge(new int[][]{
                {2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}
        }));

        LogUtil.printMultiDimensionArray(merge(new int[][]{
                {1, 4}, {0, 4}
        }));
        LogUtil.printMultiDimensionArray(merge(new int[][]{
                {1, 3}, {2, 6}, {8, 10}, {15, 18}
        }));
    }

    public static int[][] merge(int[][] intervals) {
        if(intervals.length == 0) return intervals;
        /**
         * In this Greedy problem we have to merge overlapping intervals, and the rule
         * of merge is startTime should be smallest b/w overlapping interval and endTime should be largest b/w over-lapping
         * intervals.
         *
         * 1) We'll sort the intervals based on Start Time
         *    1.a) So I/p [[2,3],[4,5],[6,7],[8,9],[1,10]] after sorting on startTime :  [[1,10],[2,3],[4,5],[6,7],[8,9]]
         * 2) Now we will take the 1st Interval as our base to do comparision with the subsequent intervals.
         * 3) As soon as we see an overlapping interval, we'll either eat that interval within the base
         *      (i.e. the newInterval start and end time can be merged into baseInterval example [[1,10][2,3]]... [2,3]
         *      can be entirely merged into [1, 10].
         *    OR extend the baseInterval (i.e. if we have I/p : [[2,4],[3,8]] So we'll extend the base [[2,8]]
         */
        Arrays.sort(intervals, Comparator.comparingInt(A -> A[0]));
        int[] baseInterval = intervals[0];
        List<int[]> result = new ArrayList<>();

        // Also add the baseInterval in the currentSet.
        result.add(baseInterval);

        for (int i = 1; i < intervals.length; i++) {
            int[] currInterval = intervals[i];

            // Either eat or extend the baseInterval
            if (currInterval[0] <= baseInterval[1]) {
                baseInterval[1] = Math.max(baseInterval[1], currInterval[1]);
            } else {
                // Now it's the chance to change the baseInterval
                baseInterval = currInterval;
                result.add(baseInterval);
            }
        }
        return result.toArray(new int[result.size()][2]);
    }
}
