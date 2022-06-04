package com.leetcode.year_2020.DP.longest_common_subsequence;

/**
 * @author neeraj on 07/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PrintLongestCommonSubSequence {

    public static void main(String[] args) {
        printLCS("abcdgh", "abedfhg");
        printLCS("oxcpqrsvwf", "shmtulqrypy");
        printLCS("GeeksForQuiz", "GeeksNj");
    }

    public static void printLCS(String s1, String s2) {
        printLCS(s1.toCharArray(), s2.toCharArray());
    }

    public static void printLCS(char[] s1, char[] s2) {
        int[][] dp = findLCSCacheInTopDown(s1, s2);
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
        System.out.println("LCS of " + new String(s1) + " and " + new String(s2) + " is " + result.reverse().toString());
    }

    private static int[][] findLCSCacheInTopDown(char[] s1, char[] s2) {
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
        return dp;
    }
}
