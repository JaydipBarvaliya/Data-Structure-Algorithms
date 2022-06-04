package com.interviewbit.array;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author neeraj on 2019-07-26
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MergeOverlappingIntervals {

    public static void main(String[] args) {
        mergeOverlappingIntervals(new int[]{1, 3, 2, 6, 8, 10, 15, 18});
        mergeOverlappingIntervals(new int[]{1, 10, 2, 9, 3, 8, 4, 7, 5, 6, 6, 6});
        mergeOverlappingIntervals(new int[]{4, 100, 48, 94, 16, 21, 58, 71, 36, 53, 49, 68, 18, 42, 37, 90, 68, 75, 6, 57, 25, 78, 58, 79, 18, 29, 69, 94, 5, 31, 10, 87, 21, 35, 1, 32, 7, 24, 17, 85, 30, 95, 5, 63, 1, 17, 67, 100, 53, 55, 30, 63, 7, 76, 33, 51, 62, 68, 78, 83, 12, 24, 31, 73, 64, 74, 33, 40, 51, 63, 17, 31, 14, 29, 9, 15, 39, 70, 13, 67, 27, 100, 10, 71, 18, 47, 48, 79, 70, 73, 44, 59, 68, 78, 24, 67, 32, 70, 29, 94, 45, 90, 10, 76, 12, 28, 31, 78, 9, 44, 29, 83, 21, 35, 46, 93, 66, 83, 21, 72, 29, 37, 6, 11, 56, 87, 10, 26, 11, 12, 15, 88, 3, 13, 56, 70, 40, 73, 25, 62, 63, 73, 47, 74, 8, 36});


        mergeNewOverlappingIntervals(new int[]{1, 3, 6, 9}, 2, 5);
        mergeNewOverlappingIntervals(new int[]{1, 2, 3, 5, 6, 7, 8, 10, 12, 16}, 4, 9);
    }

    public static void mergeOverlappingIntervals(int[] arr) {
        List<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i += 2) {
            intervals.add(new Interval(arr[i], arr[i + 1]));
        }
        LogUtil.logIt("Merge Overlap intervals of " + intervals, true);
        LogUtil.logIt("" + new MergeOverlappingIntervals().merge(intervals));
    }

    public static void mergeNewOverlappingIntervals(int[] arr, int startOfNewInterval, int endOfNewInterval) {
        List<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i += 2) {
            intervals.add(new Interval(arr[i], arr[i + 1]));
        }
        Interval newInterval = new Interval(startOfNewInterval, endOfNewInterval);

        LogUtil.logIt("Merge NonOverlapping intervals " + intervals + "  with new Interval " + newInterval, true);
        LogUtil.logIt("" + new MergeOverlappingIntervals().insertAndMergeNewInterval(intervals, newInterval));
    }

    public ArrayList<Interval> insertAndMergeNewInterval(List<Interval> intervals, Interval newInterval) {
        /**
         * 0) Base case it's given that intervals are sorted based on start, so let's add newInterval in this list
         * 1) And Sort the Intervals with new list
         * 2) Use Sliding window technique to merge the overlapping intervals
         */
        intervals.add(newInterval);
        Collections.sort(intervals, new IntervalSorter());
        return merge(intervals);
    }

    public ArrayList<Interval> merge(List<Interval> intervals) {
        ArrayList<Interval> mergedIntervals = new ArrayList<>();
        /**
         * 1) Sort the Intervals
         * 2) Use Sliding window technique to merge the overlapping intervals
         */
        Collections.sort(intervals, new IntervalSorter());

        LogUtil.logIt("After Sorting" + intervals);

        // Now use Sliding window
        int L = 0;
        int R = 0;
        int maxEnd = Integer.MIN_VALUE;

        for (int i = 0; i <= intervals.size() - 1; i++) {
            if (intervals.get(R).end > maxEnd) {
                maxEnd = intervals.get(R).end;
            }
            if (R + 1 < intervals.size() && maxEnd >= intervals.get(R + 1).start) {
                R++;
            } else {
                mergedIntervals.add(new Interval(intervals.get(L).start, maxEnd));
                R = R + 1;
                L = R;
            }
        }
        return mergedIntervals;
    }
}

class IntervalSorter implements Comparator<Interval> {
    @Override
    public int compare(Interval o1, Interval o2) {
        return o1.start - o2.start;
    }
}

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }

    @Override
    public String toString() {
        return "[" + start + " , " + end + "]";
    }
}
