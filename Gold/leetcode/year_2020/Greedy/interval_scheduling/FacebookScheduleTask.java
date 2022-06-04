package com.leetcode.year_2020.Greedy.interval_scheduling;

import java.util.LinkedList;
import java.util.List;

/**
 * You are given a shcedule of tasks to work on. Each tasks has a start and an end time [start, end] where end > start. Find out for the given schedule:
 * <p>
 * in what intervals you are working (at least 1 task ongoing)
 * in what intervals you are multitasking (at least 2 tasks ongoing)
 * In other words, find union and intersection of a list of intervals. The input is sorted by start time.
 * <p>
 * Example:
 * Input: [[1, 10], [2, 6], [9, 12], [14, 16], [16, 17]]
 * <p>
 * Output union: [[1, 12], [14, 17]]
 * Explanation: We just need to merge overlapping intervals https://leetcode.com/problems/merge-intervals
 * <p>
 * Output intersection: [[2, 6], [9, 10]]
 * Explanation: Check here https://leetcode.com/discuss/interview-question/338948/Facebook-or-Onsite-or-Schedule-of-Tasks
 * <p>
 * More:
 * <p>
 * [[1,10], [2,6], [9,12], [14,16], [16,17]] multiTasking=[[2,6], [9,10]]}
 * [[1,10], [2,6], [3,7], [4,8]] multiTasking=[[2,8]]}
 * [[1,2], [3,4], [5,6], [7,8]] multiTasking=[]}
 */
public class FacebookScheduleTask {

    public static void main(String[] args) {
        print(findMultitasking(new int[][]{
                {1, 10}, {2, 6}, {9, 12}, {14, 16}, {16, 17}}));
        print(findMultitasking(new int[][]{
                {1, 10}, {2, 6}, {3, 7}, {4, 8}}));
        print(findMultitasking(new int[][]{
                {1, 2}, {3, 4}, {5, 6}, {7, 8}}));
    }

    public static void print(List<int[]> multitasking) {
        System.out.print("[");
        for (int[] task : multitasking) {
            System.out.print("[");
            System.out.print(task[0] + "," + task[1]);
            System.out.print("],");
        }
        System.out.println("]");
    }

    public static List<int[]> findMultitasking(int[][] intervals) {
        LinkedList<int[]> multitasking = new LinkedList<>();

        // Since question says intervals are sorted in their start time, else we have to do that as well.
        int[] baseInterval = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];

            if (currentInterval[0] < baseInterval[1]) {
                // Found Intersections
                int[] intersection = {
                        Math.max(baseInterval[0], currentInterval[0]), Math.min(baseInterval[1], currentInterval[1])};

                // ******merge intersection with previous intersection if needed*****
                if (!multitasking.isEmpty() && intersection[0] < multitasking.peekFirst()[1]) {
                    int[] last = multitasking.pollFirst();
                    intersection = new int[]{Math.min(intersection[0], last[0]), Math.max(intersection[1], last[1])};
                }
                multitasking.add(intersection);
            }

            baseInterval = new int[]{
                    Math.max(baseInterval[0], currentInterval[0]), Math.max(baseInterval[1], currentInterval[1])};
        }
        return multitasking;
    }
}
