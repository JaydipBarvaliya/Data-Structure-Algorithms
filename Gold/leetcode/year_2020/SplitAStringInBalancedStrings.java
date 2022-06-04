package com.leetcode.year_2020;

/**
 * @author neeraj on 01/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SplitAStringInBalancedStrings {

    public static void main(String[] args) {
        testIt("RLRRLLRLRL");
        testIt("RLLLLRRRLR");
        testIt("LLLLRRRR");
        testIt("RLRRRLLRLL");
    }

    public static void testIt(String s) {
        System.out.println(balancedStringSplit(s));
    }

    public static int balancedStringSplit(String s) {
        int balance = 0;
        int total = 0;

        for (char c : s.toCharArray()) {
            if (c == 'L') {
                balance += 1;
            } else {
                balance -= 1;
            }
            if (balance == 0) {
                total += 1;
            }
        }
        return total;
    }
}
