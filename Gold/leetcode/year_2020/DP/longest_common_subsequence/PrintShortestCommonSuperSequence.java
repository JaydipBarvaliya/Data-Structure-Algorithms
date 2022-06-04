package com.leetcode.year_2020.DP.longest_common_subsequence;

import com.util.LogUtil;

/**
 * @author neeraj on 08/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PrintShortestCommonSuperSequence {

    public static void main(String[] args) {
//        printShortestCommonSuperSequence("acbcf", "abcdaf");
//        printShortestCommonSuperSequence("ac", "");
        printShortestCommonSuperSequence("GEEK", "EKE");
    }

    public static void printShortestCommonSuperSequence(String X, String Y) {
        /**
         * We know we can calculate length of ShortestCommonSuperSequence
         * via length(X) + length(Y) - length(LCS) : (Since LCS is present
         *                                  in both String and we should use just once)
         *
         * Now we will use the same LCS matrix to print the ShortestCommonSuperSequence
         */
        int[][] lcs = getLCSMatrix(X, Y);

        LogUtil.printMultiDimensionArray(lcs);

        /**
         * For X = "a c b c f" and Y = "a b c d a f"
         * and LCS matrix for this is :
         *      #   a   b   c   d   a   f  <=== [Y]
         * #    0   0	0	0	0	0	0
         * a    0	1	1	1	1	1	1
         * c    0	1	1	2	2	2	2
         * b    0	1	2	2	2	2	2
         * c    0	1	2	3	3	3	3
         * f    0	1	2	3	3	3	4
         * /\
         * [X]
         *
         * Now within this matrix we know if both characters are matching we have
         * to include it's value just once.
         *
         * I am starting from matrix[X.length()][Y.length()] to print the Shortest
         * Common SuperSequence.
         *
         * So in this matrix[f][f] = 4.
         * if you notice [f] == [f] hence this value must have came from left diagonal
         * include this f in our result and move to leftDiagonal.
         *
         * Now compare matrix[c][a] = 3. [c] != [a]
         * So we have to move to the max of top or left value, that's the only 2 choice.
         * and we have to put the looser in the result.
         *
         * Why putting the looser ? (Simple because winner value must have came from a place
         *                          (where it must have find a match, so if it has then we will
         *                          include it in the result set there not here to avoid duplicates)
         *
         *  matrix[f][f] = 4   : Result[f]
         *  matrix[c][a] = 3   : Result[a f]
         *  matrix[c][d] = 3   : Result[d a f]
         *  matrix[c][c] = 3   : Result[c d a f]
         *  matrix[b][b] = 2   : Result[b c d a f]
         *  matrix[c][a] = 1   : Result[c b c d a f]
         *  matrix[a][a] = 1   : Result[a c d c d a f]  ===> this is our ShortestCommonSuperSequence.
         *
         */
        // Starting from top right corner
        int m = lcs.length - 1;
        int n = lcs[0].length - 1;
        char[] str1 = X.toCharArray();
        char[] str2 = Y.toCharArray();
        StringBuilder result = new StringBuilder();

        while (m > 0 && n > 0) {
            if (str1[m - 1] == str2[n - 1]) {
                result.append(str1[m - 1]).append(" ");
                m--;
                n--;
            } else {
                if (lcs[m - 1][n] > lcs[m][n - 1]) { // Include looser in the result and move to the winner.
                    result.append(str1[m - 1]).append(" ");
                    m--;
                } else {
                    result.append(str2[n - 1]).append(" ");
                    n--;
                }
            }
        }

        /**
         * These 2 loops are necessary in case when 1 of the string finishes before the other
         */
        while (m > 0) {
            result.append(str1[m-1]).append(" ");
            m--;
        }

        while (n > 0) {
            result.append(str2[n-1]).append(" ");
            n--;
        }
        String SCS = result.reverse().toString();
        // We will divide the length by 2 since we are adding whitespace.
        LogUtil.logIt("Shortest Common SuperSequence of " + X + " and " + Y + " is " + SCS + " and of length " + SCS.length() / 2);
    }

    public static int[][] getLCSMatrix(String X, String Y) {
        int[][] dp = new int[X.length() + 1][Y.length() + 1];

        // Initialization Matrix.
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i == 0 || j == 0) { // We don't have anything common
                    dp[i][j] = 0;      // when either of the String is empty.
                }
            }
        }

        // Now let's populate rest of LCS
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp;
    }
}
