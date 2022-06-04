package com.leetcode.year_2020.Greedy.interval_scheduling;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/my-calendar-i/
 */
public class MyCalendar_1 {

    public static void main(String[] args) {
        int[][] bookings = new int[][]{
                {47, 50}, {33, 41}, {39, 45}, {33, 42}, {25, 32}, {26, 35}, {19, 25}, {3, 8}, {8, 13}, {18, 27}
        };
//        testMyCalendar(bookings);
        LogUtil.newLine();
        bookings = new int[][]{{37, 50}, {33, 50}, {4, 17}, {35, 48}, {8, 25}};
        testMyCalendar(bookings);
        // Solution 2: Keep existing books sorted and only check 2 bookings start right before & after the new book starts

    }

    private static void testMyCalendar(int[][] bookings) {
        MyCalendarWithSolutionOfKeepingInSortedOrder calendar = new MyCalendarWithSolutionOfKeepingInSortedOrder();
        for (int i = 0; i < bookings.length; i++) {
            System.out.println("Meeting [" + bookings[i][0] + "," + bookings[i][1] + "] ==> " + calendar.book(bookings[i][0], bookings[i][1]));
        }
    }

    /**
     * https://leetcode.com/problems/my-calendar-i/discuss/109475/JavaC%2B%2B-Clean-Code-with-Explanation
     */
    static class MyCalendarWithSolutionOfKeepingInSortedOrder {

        private TreeMap<Integer, Integer> sortedBookings;

        public MyCalendarWithSolutionOfKeepingInSortedOrder() {
            sortedBookings = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            Integer bookingJustBeforeThisNewBooking = sortedBookings.floorKey(start);

            /**
             * Assume having [10, 20] in sorted bookings
             * and new Booking came with {15, 20}, so booking just before {15,20} will be [10, 20]
             * and 20 > 15 hence a clash because 10 started before 15
             * 10 < 15 < 20
             */
            if (bookingJustBeforeThisNewBooking != null
                    && sortedBookings.get(bookingJustBeforeThisNewBooking) > start) {
                // Clash
                return false;
            }

            /**
             * Assume having [15, 30] in sorted bookings
             * and new Booking came with {10, 18}, so booking just after {10,18} will be [15, 30]
             * and 15 < 18 hence a clash because 10 started before 15 and ending after 18.
             * 15 < 18 < 30
             */
            Integer bookingImmediatelyAfterThisNewBooking = sortedBookings.ceilingKey(start);
            if (bookingImmediatelyAfterThisNewBooking != null
                    && bookingImmediatelyAfterThisNewBooking < end) {
                // Clash
                return false;
            }

            sortedBookings.put(start, end);
            return true;
        }
    }

    /**
     * How to calculate overlap of 2 intervals
     * Assume a start earlier than b, (if not reverse), there could be 3 case, but in any case, an overlap(either positive or negative) can always be represented as:
     * (max(a0, b0), min(a1, b1))
     * <p>
     * case 1: b ends before a ends:
     * a: a0 |-------------| a1
     * b:     b0 |-----| b1
     * <p>
     * case 2: b ends after a ends:
     * a: a0 |--------| a1
     * b:     b0 |--------| b1
     * <p>
     * case 3: b starts after a ends: (negative overlap)
     * a: a0 |----| a1
     * b:              b0 |----| b1
     * <p>
     * So the concept revolves around the maximum start, and minimum end
     */
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
