package com.leetcode.year_2020.Greedy.interval_scheduling;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/my-calendar-ii/
 */
public class MyCalendar_2 {

    public static void main(String[] args) {

        /**
         * There are 2 ways to solve this problem, first by using {@link MyCalendar_1} O(N) approach
         * Traverse entire bookings wherever you find clash, traverse one more time, to check clash of those clashes.
         */
        testMyCalendar(new int[][]{{10, 20}, {50, 60}, {10, 40}, {5, 15}, {5, 10}, {25, 55}});

        /**
         * Other approach is basically increment start entry frequency by 1 and
         * decrease ending time frequency by 1, this way whenever new entry comes you iterate through all the frequencies
         * and you accumulate 3 then this booking is invalid
         */
        LogUtil.newLine();
        testMyCalendarOptimized(new int[][]{{10, 20}, {50, 60}, {10, 40}, {5, 15}, {5, 10}, {25, 55}});
    }

    private static void testMyCalendarOptimized(int[][] bookings) {
        MyCalendarTwoOptimized calendar = new MyCalendarTwoOptimized();
        for (int i = 0; i < bookings.length; i++) {
            System.out.println("Meeting [" + bookings[i][0] + "," + bookings[i][1] + "] ==> " + calendar.book(bookings[i][0], bookings[i][1]));
        }
    }

    static class MyCalendarTwoOptimized {

        private TreeMap<Integer, Integer> timeFrequency;

        public MyCalendarTwoOptimized() {
            timeFrequency = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            timeFrequency.put(start, timeFrequency.getOrDefault(start, 0) + 1);
            timeFrequency.put(end, timeFrequency.getOrDefault(end, 0) - 1);

            int count = 0;
            // System.out.println(timeFrequency);
            for (Map.Entry<Integer, Integer> entry : timeFrequency.entrySet()) {
                count += entry.getValue();

                if (count > 2) {
                    // Let's just remove this particular booking, but subtracting start freq and increasing end freq
                    timeFrequency.put(start, timeFrequency.get(start) - 1);
                    if (timeFrequency.get(start) == 0) {
                        timeFrequency.remove(start);
                    }
                    timeFrequency.put(end, timeFrequency.get(end) + 1);
                    if (timeFrequency.get(end) == 0) {
                        timeFrequency.remove(end);
                    }
                    return false;
                }
            }
            return true;
        }
    }

    private static void testMyCalendar(int[][] bookings) {
        MyCalendarTwo_ONSquare calendar = new MyCalendarTwo_ONSquare();
        for (int i = 0; i < bookings.length; i++) {
            System.out.println("Meeting [" + bookings[i][0] + "," + bookings[i][1] + "] ==> " + calendar.book(bookings[i][0], bookings[i][1]));
        }
    }

    static class MyCalendarTwo_ONSquare {

        private List<int[]> bookings;

        public MyCalendarTwo_ONSquare() {
            bookings = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            MyCalendar calendar1 = new MyCalendar();
            for (int[] booking : bookings) {
                int maxStart = Math.max(booking[0], start);
                int minEnd = Math.min(booking[1], end);
                if (maxStart < minEnd) {
                    if (!calendar1.book(maxStart, minEnd)) {
                        // Found clash in clash (triple booking)
                        return false;
                    }
                }
            }
            bookings.add(new int[]{start, end});
            return true;
        }

        static class MyCalendar {

            private List<int[]> bookings;

            public MyCalendar() {
                bookings = new ArrayList<>();
            }

            public boolean book(int start, int end) {
                for (int[] booking : bookings) {
                    if (Math.max(booking[0], start) < Math.min(booking[1], end)) return false;
                }
                bookings.add(new int[]{start, end});
                return true;
            }
        }
    }
}
