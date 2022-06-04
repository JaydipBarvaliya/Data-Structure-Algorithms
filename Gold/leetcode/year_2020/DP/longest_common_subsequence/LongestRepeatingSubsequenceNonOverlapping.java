package com.leetcode.year_2020.DP.longest_common_subsequence;

/**
 * @author neeraj on 01/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LongestRepeatingSubsequenceNonOverlapping {

    public static void main(String[] args) {
//        System.out.println(findLengthOfLongestRepeatingSubsequenceNonOverlapping("BANANA"));
//        System.out.println(findLengthOfLongestRepeatingSubsequenceNonOverlapping("geeksforgeeks"));
        System.out.println(findLengthOfLongestRepeatingSubsequenceNonOverlapping("aaa"));
//        System.out.println(findLengthOfLongestRepeatingSubsequenceNonOverlapping("aabaabaaba"));
//        System.out.println(findLengthOfLongestRepeatingSubsequenceNonOverlapping("aaaaaaaaaaa"));
    }

    public static int findLengthOfLongestRepeatingSubsequenceNonOverlapping(String str) {
        /**
         * This is a variation to {@link LongestRepeatingSubsequence} with a twist of Non Overlapping
         * How we can avoid that
         * Example : B A N A N A
         * If we take the LCS
         *
         *      ||  0   1   2   3   4   5   =============> (j)
         *     (i)  B   A   N   A   N   A
         *      1  B       //      //
         *      2  A      //      //
         *      3  N-----//------//   -----------> Focus on these 2 points at both places N matched, but at first time there was no repetition
         *      4  A                                // and hence i-j < LCS[i-1][j-1];
         *      5  N
         *      6  A
         * Now you will notice ANA is the Longest Repeating Subsequence but it's overlapping, non-overlapping is just "AN" or "NA"
         * How do we avoid overlapping : Simply when  (j - i) > previously matched suffix.
         */
        int LCSRepeating[][] = new int[str.length() + 1][str.length() + 1];
        int LCS = Integer.MIN_VALUE;
        int endPoint = 0;
        for (int i = 1; i < LCSRepeating.length; i++) {
            for (int j = 1; j < LCSRepeating[i].length; j++) {
                if (str.charAt(i - 1) == str.charAt(j - 1) && (j - i) > LCSRepeating[i - 1][j - 1]) {
                    LCSRepeating[i][j] = 1 + LCSRepeating[i - 1][j - 1];
                } else {
                    LCSRepeating[i][j] = 0; // When they didn't match
                }
                if (LCS < LCSRepeating[i][j]) {
                    LCS = LCSRepeating[i][j];
                    endPoint = Math.max(endPoint, i);
                }
            }
        }

        if (LCS != Integer.MIN_VALUE) {
            StringBuilder result = new StringBuilder();
            for (int i = endPoint - LCS; i < endPoint; i++) {
                result.append(str.charAt(i));
            }
            System.out.println(result.toString());
        }
        return LCS;
    }
}
