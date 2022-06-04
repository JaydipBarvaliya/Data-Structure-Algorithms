package com.leetcode.year_2020;

/**
 * @author neeraj on 14/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PerformStringShifts {

    public static void main(String[] args) {
        System.out.println(stringShift("abc", new int[][]{{0, 1}, {1, 2}}));
        System.out.println(stringShift("abcdefg", new int[][]{{1, 1}, {1, 1}, {0, 2}, {1, 3}}));
        System.out.println(stringShift("ABCD", new int[][]{{1, 1}, {1, 2}, {1, 3}, {1, 4}}));
    }

    public static String stringShift(String s, int[][] shift) {
        for (int[] currentShift : shift) {
            if (currentShift[1] > s.length()) {
                currentShift[1] = currentShift[1] % s.length();
            }
            if (currentShift[0] == 1) {
                s = s.substring(s.length() - currentShift[1]) + s.substring(0, s.length() - currentShift[1]);
            } else {
                s = s.substring(currentShift[1]) + s.substring(0, currentShift[1]);
            }
        }
        return s;
    }
}
