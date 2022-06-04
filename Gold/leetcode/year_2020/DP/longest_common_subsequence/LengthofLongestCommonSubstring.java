package com.leetcode.year_2020.DP.longest_common_subsequence;

import java.util.Arrays;

/**
 * @author neeraj on 07/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LengthofLongestCommonSubstring {

    public static void main(String[] args) {
        System.out.println(longestCommonSubString("abcde", "abfce"));
        System.out.println(longestCommonSubString("GeeksforGeeks", "GeeksQuiz"));
    }

    static int MAX_COMMON_LENGTH = 0;
    static int t[][]; // Memorization Matrix.

    public static int longestCommonSubString(String s1, String s2) {
        MAX_COMMON_LENGTH = 0;

        t = new int[s1.length() + 1][s2.length() + 1];
        for (int row[] : t) {
            Arrays.fill(row, -1);
        }
//        longestCommonSubString(s1.toCharArray(), s2.toCharArray(), s1.length(), s2.length());
//        return MAX_COMMON_LENGTH;
        return longestCommonSubstringTopDown(s1, s2);
    }

    /**
     * @param X S1 charArray representation
     * @param Y S2 charArray representation
     * @param m Length of S1
     * @param n Length of S2
     * @return Length of Longest Common SubString
     */
    private static int longestCommonSubString(char[] X, char[] Y, int m, int n) {
        if (m == 0 || n == 0) return 0; // if either of them is null we can't have anything in common.

        if (t[m][n] > -1) {
            return t[m][n]; // Return from Cache
        }

        int result;
        if (X[m - 1] == Y[n - 1]) { // Both characters matched.
            result = 1 + longestCommonSubString(X, Y, m - 1, n - 1);
        } else { // Characters didn't match and they break the continuity
            // hence we need to reset result
            longestCommonSubString(X, Y, m, n - 1);
            longestCommonSubString(X, Y, m - 1, n);
            result = 0;
        }
        // We need to keep track of the MAX_COMMON_LENGTH.
        MAX_COMMON_LENGTH = Math.max(result, MAX_COMMON_LENGTH);
        t[m][n] = result;
        return result;
    }

    /**
     * Top Down Approach.
     */
    public static int longestCommonSubstringTopDown(String X, String Y) {
        if (Y.length() > X.length()) {
            return longestCommonSubstringTopDown(Y, X);
        }

        int[][] lcs = new int[Y.length() + 1][X.length() + 1];
        int maxLength = Integer.MIN_VALUE;
        for (int i = 1; i < lcs.length; i++) {
            for (int j = 1; j < lcs[i].length; j++) {
                // Compare characters from both inputs
                if (Y.charAt(i - 1) == X.charAt(j - 1)) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                    maxLength = Math.max(maxLength, lcs[i][j]);
                } else {
                    lcs[i][j] = 0; // SubString break.
                    /**
                     *  text1 ==> a b c d e
                     *  Y ==> a b f d e
                     *
                     *  Longest Common Subsequence is [a,b,d,e]
                     *  Longest Common Substring is [a, b] because c and f doesn't match.
                     *
                     */
                }
            }
        }
        return maxLength;

    }
}
