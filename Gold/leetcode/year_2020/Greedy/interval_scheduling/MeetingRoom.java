package com.leetcode.year_2020.Greedy.interval_scheduling;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/meeting-rooms/
 * Given an array of meeting time intervals consisting of start and
 * end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could
 * attend all meetings.
 * <p>
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return false.
 *
 * @author neeraj on 17/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MeetingRoom {

    public static void main(String[] args) {
        solveProblem(new int[][]{
                {0, 30}, {5, 10}, {15, 20}
        });
        solveProblem(new int[][]{
                {7, 10}, {2, 4}
        });
        solveProblem(new int[][]{
                {2, 15}, {36, 45}, {9, 29}, {16, 23}, {4, 9}
        });
        solveProblem(new int[][]{
                {928, 5032}, {3072, 3741}, {3960, 4588}, {482, 2269}, {2030, 4360}, {150, 772}
        });
        solveProblem(new int[][]{
                {9, 50}, {30, 37}, {39, 45}, {4, 22}, {20, 43}, {1, 7}
        });
        solveProblem(new int[][]{
                {9, 50}, {30, 37}, {20, 43}, {1, 7}
        });

    }

    public static void solveProblem(int[][] arr) {
        List<Interval> intervals = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            intervals.add(new Interval(arr[i][0], arr[i][1]));
        }
        LogUtil.logIt("Can Attend All Meetings " + canAttendAllMeetings(intervals.toArray(new Interval[intervals.size()])));
    }

    static class Interval {
        int start;
        int end;

        public Interval() {
        }

        @Override
        public String toString() {
            return "{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static boolean canAttendAllMeetings(Interval[] intervals) {
        /**
         * If any overlaps is present then he can't attend any meeting.
         */
        Arrays.sort(intervals, Comparator.comparingInt(A -> A.start));
//        for(Interval i: intervals) {
//            System.out.print(i + " \t");
//        }
//        System.out.println();
        Interval baseMeeting = intervals[0];

        for(int i=1;i<intervals.length;i++) {
            Interval currentMeeting = intervals[1];
            if(currentMeeting.start < baseMeeting.end) {
                return false;
            } else {
                baseMeeting = currentMeeting;
            }
        }
        return true;
    }


}
