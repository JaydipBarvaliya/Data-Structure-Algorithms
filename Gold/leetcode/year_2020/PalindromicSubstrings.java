package com.leetcode.year_2020;

/**
 * @author neeraj on 02/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PalindromicSubstrings {

    public static void main(String[] args) {
        System.out.println(countSubstrings("abc"));
        System.out.println(countSubstrings("aaa"));
    }

    static int totalCount = 0;

    public static int countSubstrings(String s) {
        totalCount = 0;
        for (int i = 0; i < s.length(); i++) {
            extendPalindrome(s, i, i);
            extendPalindrome(s, i, i + 1);
        }
        return totalCount;
    }

    public static void extendPalindrome(String s, int low, int high) {
        while (low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high)) {
            low--;
            high++;
            totalCount++;
        }
    }
}
