package com.leetcode.array;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neeraj on 16/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class GetSubSet {

    static class Meeting {
        int hours;

        public Meeting(int hours) {
            this.hours = hours;
        }

        @Override
        public String toString() {
            return "[Meeting : hours -=> " + this.hours + " ] ";
        }
    }

    public static void main(String[] args) {
        Meeting[] meetings = new Meeting[]{
                new Meeting(1),
                new Meeting(3),
                new Meeting(5),
                new Meeting(6)
        };
        printAllSubsets(meetings, 0, new ArrayList<>());
        System.out.println("===============New Input============================================");
        printAllSubsets(meetings, 0, new ArrayList<>(), 0, 4);

        System.out.println("===============New Input============================================");
        printAllStringSubsets("ABCD");
    }

    public static void printAllStringSubsets(String str) {
        LogUtil.logIt("Permutation of this Input ====>  " + str);
        permuteString(str, 0, new StringBuffer());
    }

    private static void permuteString(String str, int pointer, StringBuffer combination) {
        System.out.println(combination.length() == 0 ? "[]" : combination.toString());
        for (int i = pointer; i < str.length(); i++) {
            combination.append(str.charAt(i));
            permuteString(str, i + 1, combination);
            combination.deleteCharAt(combination.length() - 1);
        }
    }

    private static void printAllSubsets(Meeting[] meetings, int pointer,
                                        List<Meeting> meetingsCombination, int currentSum, int totalSum) {
        if (currentSum <= totalSum) {
            System.out.println(meetingsCombination);
        }
        for (int i = pointer; i < meetings.length; i++) {
            meetingsCombination.add(meetings[i]);
            printAllSubsets(meetings, i + 1, meetingsCombination, currentSum + meetings[i].hours, totalSum);
            meetingsCombination.remove(meetingsCombination.size() - 1);
        }
    }

    private static void printAllSubsets(Meeting[] meetings, int pointer, List<Meeting> meetingsCombination) {
        System.out.println(meetingsCombination);
        for (int i = pointer; i < meetings.length; i++) {
            meetingsCombination.add(meetings[i]);
            printAllSubsets(meetings, i + 1, meetingsCombination);
            meetingsCombination.remove(meetingsCombination.size() - 1);
        }
    }
}
