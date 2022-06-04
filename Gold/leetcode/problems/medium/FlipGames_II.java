package com.leetcode.problems.medium;

/**
 * @author neeraj on 12/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FlipGames_II {

    public static void main(String[] args) {
        System.out.println(canWin("++++"));
    }

    public static boolean canWin(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                String nextState = s.substring(0, i) + "--" + s.substring(i + 2);
                if (!canWin(nextState)) {
                    return true;
                }
            }
        }
        return false;
    }
}
