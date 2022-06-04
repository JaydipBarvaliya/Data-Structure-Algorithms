package com.leetcode.year_2020.DP.longest_common_subsequence;

import com.util.LogUtil;

import java.util.Arrays;

/**
 * @author neeraj on 07/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LengthOfLongestCommonSubsequence {

    public static void main(String[] args) {
        System.out.println(findLengthOfLCS("abcdgh", "abedfhg"));
        System.out.println(findLengthOfLCS("oxcpqrsvwf", "shmtulqrypy"));
        System.out.println(findLengthOfLCS("GeeksForQuiz", "GeeksNj"));
        System.out.println(findLengthOfLCS("aabcc", "aadbbcbcac"));
    }

    static int t[][];

    public static int findLengthOfLCS(String s1, String s2) {
        if (s1.length() > s2.length()) { // Always keeping S1 small, this way
            // In bottom up approach we have to solve the problem to the less number of rows.
            // hence less comparision.
            return findLengthOfLCS(s2, s1);
        }
        t = new int[s1.length() + 1][s2.length() + 1];
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }
//        return findLengthOfLCS(s1.toCharArray(), s2.toCharArray(), s1.length(), s2.length());
        return findLCSTopDown(s1.toCharArray(), s2.toCharArray());
    }

    /**
     * @param s1 : String1 represented in it's char Array representation
     * @param s2 : String2 represented in it's char Array representation
     * @param m  : Length of S1
     * @param n  : Length of S2
     * @return Longest Common Subsequence in both String.
     */
    private static int findLengthOfLCS(char[] s1, char[] s2, int m, int n) {
        // Base Condition (Think of the Smallest possible valid input.)
        if (m == 0 || n == 0) return 0; // When either of 2 string is empty there can be nothing in common.

        if (t[m][n] > -1) return t[m][n]; // Return from cache.
        /**
         * Choice Diagram
         */
        if (s1[m - 1] == s2[n - 1]) { // When character is same in both the String
            // We are making the input Smaller in the Recursive call
            /**
             * We checked last character in both string,
             * now let's check last-1th to make the input Smaller.
             */
            return t[m][n] = 1 + findLengthOfLCS(s1, s2, m - 1, n - 1);
        } else { // if the character is not same, then we can skip either from any string
            return t[m][n] = Math.max(findLengthOfLCS(s1, s2, m, n - 1),
                    findLengthOfLCS(s1, s2, m - 1, n));
        }
    }

    private static int findLCSTopDown(char[] s1, char[] s2) {
        // We need a 2d matrix to store the LCS at any given length of S1 and S2
        int[][] dp = new int[s1.length + 1][s2.length + 1];

        // Similar to Base condition in Recursive approach as discussed above
        // we need to initialize our Matrix.
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i == 0 || j == 0) { // If Any String is null or empty, there is nothing in common.
                    dp[i][j] = 0;
                }
            }
        }

        // Now based on our choice diagram, lets populate rest of matrix and find out
        // Longest Common Subsequence
        for (int i = 1; i < dp.length; i++) { // I represent S1
            for (int j = 1; j < dp[i].length; j++) { // J represent S2
                if (s1[i - 1] == s2[j - 1]) { // Checking character by character in both string
                    dp[i][j] = 1 + dp[i - 1][j - 1]; // Reduce input in both.
                } else {
                    // Maximum of either reducing S1(i) or S2(j)
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Now let's also try to Print the Sequence.
        printLCS(s1, s2, dp);
        LogUtil.printMultiDimensionArray(dp);
        return dp[s1.length][s2.length];
    }

    private static void printLCS(char[] s1, char[] s2, int[][] dp) {
        int m = dp.length - 1;
        int n = dp[0].length - 1;
        StringBuilder result = new StringBuilder();
        while (m != 0 && n != 0) {
            if (s1[m - 1] == s2[n - 1]) {
                result.append(s1[m - 1]);
                m = m - 1;
                n = n - 1;  // Move to Left Diagonal
            } else {
                if (dp[m - 1][n] > dp[m][n - 1]) {
                    m = m - 1;
                } else {
                    n = n - 1;
                }
            }
        }
        System.out.println("LCS of " + new String(s1) + " and " + new String(s2) + " is " + result.reverse());
    }
}
