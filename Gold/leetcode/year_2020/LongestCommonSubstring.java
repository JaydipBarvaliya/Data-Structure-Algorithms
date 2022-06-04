package com.leetcode.year_2020;

/**
 * @author neeraj on 03/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LongestCommonSubstring {

    public static void main(String[] args) {
        /**
         * This problem is a variation of Longest Common Subsequence
         * , here continuity matters.
         */
        System.out.println(longestCommonSubstring("abcde", "abfce"));
        System.out.println(longestCommonSubstringRecursively("abcde", "abfce"));
    }

    static Integer LONGEST_LENGTH = 0;

    public static int longestCommonSubstringRecursively(String A, String B) {
        LONGEST_LENGTH = 0;
        longestCommonSubstringRecursively(A, B, A.length() - 1, B.length() - 1);
        return LONGEST_LENGTH;
    }

    public static int longestCommonSubstringRecursively(String A, String B, int A_indexToCompare, int B_indexToCompare) {
        // Base Case....... If any of the String is empty there is nothing common.
        if (A_indexToCompare < 0 || B_indexToCompare < 0) return 0;

        // We will compare the last character
        if (A.charAt(A_indexToCompare) == B.charAt(B_indexToCompare)) {
            int answer = 1 + longestCommonSubstringRecursively(A, B,
                    A_indexToCompare - 1, B_indexToCompare - 1); // Since both are same, hence counted as 1 common.
            LONGEST_LENGTH = Math.max(LONGEST_LENGTH, answer);
            return answer;
        } else {
            // Either leave lastChar from A or B
            longestCommonSubstringRecursively(A, B, A_indexToCompare, B_indexToCompare - 1);
            longestCommonSubstringRecursively(A, B, A_indexToCompare - 1, B_indexToCompare);
            return 0;
        }
    }

    /**
     * Top Down Approach.
     */
    public static int longestCommonSubstring(String text1, String text2) {
        if (text2.length() > text1.length()) {
            return longestCommonSubstring(text2, text1);
        }

        int[][] lcs = new int[text2.length() + 1][text1.length() + 1];
        int maxLength = Integer.MIN_VALUE;
        for (int i = 1; i < lcs.length; i++) {
            for (int j = 1; j < lcs[i].length; j++) {
                // Compare characters from both inputs
                if (text2.charAt(i - 1) == text1.charAt(j - 1)) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                    maxLength = Math.max(maxLength, lcs[i][j]);
                } else {
                    // Uncomment this for Subsequence
                    //lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);

                    lcs[i][j] = 0; // this is for common substring.
                    /**
                     *  text1 ==> a b c d e
                     *  text2 ==> a b f d e
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
