package com.leetcode.year_2020;

import static java.lang.Math.max;

/**
 * @author neeraj on 03/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("DuplicatedCode")
public class MaximumLengthofRepeatedSubarray {

    public static void main(String[] args) {
        System.out.println(findLength(new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7}));
        System.out.println(findLength(new int[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 1, 0, 0}));
        System.out.println(findLength(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
    }


    static int MAX_LENGTH = Integer.MIN_VALUE;

    public static int findLength(int[] A, int[] B) {
//        /**
//         *  This can be solved using longest common substring pattern.
//         */
//        if (B.length > A.length) {
//            return findLength(B, A);
//        }
//        return repeatedSubArray(A, B);
//        return repeatedSubArrayRecursive(A, B, A.length - 1, B.length - 1, 0);

        Integer[][][] dp = new Integer[A.length + 1][B.length + 1][Math.max(A.length, B.length) + 1];
        return repeatedSubArrayWithMemorization(A, B, A.length, B.length, 0, dp);
    }

    public static int repeatedSubArrayWithMemorization(int[] X, int[] Y, int m, int n, int lcsCount, Integer[][][] dp) {
        if (m <= 0 || n <= 0)
            return lcsCount;

        if (dp[m][n][lcsCount] != null)
            return dp[m][n][lcsCount];

        int lcsCount1 = lcsCount;
        if (X[m - 1] == Y[n - 1])
            lcsCount1 = repeatedSubArrayWithMemorization(X, Y, m - 1, n - 1, lcsCount + 1, dp);

        int lcsCount2 = repeatedSubArrayWithMemorization(X, Y, m, n - 1, 0, dp);
        int lcsCount3 = repeatedSubArrayWithMemorization(X, Y, m - 1, n, 0, dp);

        return dp[m][n][lcsCount] = Math.max(lcsCount1, Math.max(lcsCount2, lcsCount3));
    }

    public static int repeatedSubArrayRecursive(int[] A,
                                                int[] B,
                                                int A_indexToCompare,
                                                int B_indexToCompare,
                                                int result) {
        // Base Case....... If any of the String is empty there is nothing common.
        if (A_indexToCompare < 0 || B_indexToCompare < 0) {
            return result;
        }
        if (A[A_indexToCompare] == B[B_indexToCompare]) {
            result = repeatedSubArrayRecursive(A, B, A_indexToCompare - 1,
                    B_indexToCompare - 1, result + 1); // Increasing the result variable by 1.


        }
        return max(result, max(repeatedSubArrayRecursive(A, B, A_indexToCompare - 1, B_indexToCompare, 0),
                repeatedSubArrayRecursive(A, B, A_indexToCompare, B_indexToCompare - 1, 0)));
    }

    public static int repeatedSubArray(int[] A, int[] B) {
        int[][] LCS = new int[A.length + 1][B.length + 1];
        int maxRepeatedLength = Integer.MIN_VALUE;

        for (int i = 1; i < LCS.length; i++) {
            for (int j = 1; j < LCS.length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    LCS[i][j] = 1 + LCS[i - 1][j - 1];
                    maxRepeatedLength = max(maxRepeatedLength, LCS[i][j]);
                } else {
                    // Not common and since we are looking for continuous
                    // hence let's break the longest chain
                    LCS[i][j] = 0;
                }
            }
        }
        return maxRepeatedLength == Integer.MIN_VALUE ? 0 : maxRepeatedLength;
    }
}
