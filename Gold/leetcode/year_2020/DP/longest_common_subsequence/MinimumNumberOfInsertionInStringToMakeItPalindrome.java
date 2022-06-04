package com.leetcode.year_2020.DP.longest_common_subsequence;

import com.util.LogUtil;

/**
 * @author neeraj on 09/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumNumberOfInsertionInStringToMakeItPalindrome {

    public static void main(String[] args) {
        minInsertions("zzazz");
        minInsertions("mbadm");
        minInsertions("leetcode");
        minInsertions("g");
    }

    public static int minInsertions(String s) {
        int minInsertions = findMinInsertionsToMakePalindrome(s);
        LogUtil.logIt("Minimum Insertions to make " + s + " a palindrome is " + minInsertions);
        return minInsertions;
    }

    private static int findMinInsertionsToMakePalindrome(String s) {
        /**
         * Okay so this problem is kindaa exact similar to {@link MinimumNumberOfDeletionInStringToMakeItPalindrome}
         * here we have been asked to give minInsertions.
         *
         * Let's see with Example : [m b a d m]
         * We have to do 2 insertions "d" and "b" so that it become Palindrome
         * [m b (d) a d (b) m] ===> Now this is a palindrome with 2 min insertions.
         *
         * What pattern can we see here, if we take LPS(Longest Palindromic Subsequence) of input,
         * [m a m]..... now what is left just have 1 occurrence.
         * So we have to do those many insertions to get out minInsertions.
         * In the above case "b" and "d" we not in LPS and exactly these we
         * have to add to make it Palindrome.
         */
        int lps = LongestPalindromicSubsequence.longestPalindromeSubseqUsingLCS(s);
        return s.length() - lps;
    }
}
