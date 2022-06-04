package com.leetcode.year_2020.Greedy.interval_scheduling;


import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/my-calendar-iii/
 */
public class MyCalendar_3 {

    class MyCalendarThree {

        private TreeMap<Integer, Integer> timeFrequency;

        public MyCalendarThree() {
            timeFrequency = new TreeMap<>();
        }

        public int book(int start, int end) {
            timeFrequency.put(start, timeFrequency.getOrDefault(start, 0) + 1);
            timeFrequency.put(end, timeFrequency.getOrDefault(end, 0) - 1);

            int count = 0;
            int MAX = 0;
            for (Map.Entry<Integer, Integer> entry : timeFrequency.entrySet()) {
                count += entry.getValue();
                MAX = Math.max(count, MAX);
            }
            return MAX;
        }
    }
}
