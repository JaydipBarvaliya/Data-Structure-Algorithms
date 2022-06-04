package com.leetcode.year_2020.Greedy.interval_scheduling;

import com.leetcode.year_2020.Greedy.interval_scheduling.MeetingRoom.Interval;
import com.util.LogUtil;

import java.util.*;

/**
 * Given an array of meeting time intervals consisting of start and
 * end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of
 * conference rooms required.
 * <p>
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return 2.
 *
 * @author neeraj on 17/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MeetingRoom2 {

    public static void main(String[] args) {
        solveMeeting2Problem(new int[][]{
                {0, 30}, {5, 10}, {15, 20}
        });
        solveMeeting2Problem(new int[][]{
                {7, 10}, {2, 4}
        });
        solveMeeting2Problem(new int[][]{
                {2, 15}, {36, 45}, {9, 29}, {16, 23}, {4, 9}
        });
        solveMeeting2Problem(new int[][]{
                {928, 5032}, {3072, 3741}, {3960, 4588}, {482, 2269}, {2030, 4360}, {150, 772}
        });
        solveMeeting2Problem(new int[][]{
                {9, 50}, {30, 37}, {39, 45}, {4, 22}, {20, 43}, {1, 7}
        });
        solveMeeting2Problem(new int[][]{
                {9, 50}, {30, 37}, {20, 43}, {1, 7}
        });
    }

    public static void solveMeeting2Problem(int[][] arr) {
        List<Interval> intervals = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            intervals.add(new Interval(arr[i][0], arr[i][1]));
        }
        LogUtil.logIt("Minimum number of meeting room required " + findMinimumNumberOfMeetingRoomRequired(intervals.toArray(new Interval[intervals.size()])));
    }

    public static int findMinimumNumberOfMeetingRoomRequired(Interval[] meetings) {
        /**
         * This problem is a slight variation of {@link NonOverLappingIntervals} problem
         * there we had to find minimum number of intervals to remove so that whole
         * process of intervals become non-Overlapping. whereas in this problem we have to find out
         * minimum number of meeting rooms required to finish all the meetings.
         *
         * which means we only need new room when there is already meeting going on during that time-frame
         * i.e overlapping meetings.
         *
         * We will be sorting meetings based on their start time and then we will maintain minHeap which will keep
         * the least ending meeting time on top. And then we'll compare if there is any conflicting meeting which needs
         * new room.
         */
        Arrays.sort(meetings, Comparator.comparingInt(A -> A.start));
        // Min Heap based On EndTime
        PriorityQueue<Interval> minHeap = new PriorityQueue<>(Comparator.comparingInt(A -> A.end));
        minHeap.add(meetings[0]);

        for (int i = 1; i < meetings.length; i++) {
            Interval currentMeeting = meetings[i];
            Interval earliestEndingMeeting = minHeap.poll();

            if (currentMeeting.start >= earliestEndingMeeting.end) {
                earliestEndingMeeting.end = Math.max(currentMeeting.end, earliestEndingMeeting.end);
            } else {
                minHeap.add(currentMeeting);
            }
            minHeap.add(earliestEndingMeeting);
        }
        return minHeap.size();
    }
}
