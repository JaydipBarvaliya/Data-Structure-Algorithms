package com.leetcode.year_2020.DP.longest_common_subsequence;

/**
 * @author neeraj on 07/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ShortestCommonSuperSequence {

    public static void main(String[] args) {
        System.out.println("ShortestCommonSuperSequence Length is " + findLengthOfShortestCommonSuperSequence("GEEKE", "EKE"));
        System.out.println("ShortestCommonSuperSequence Length is " + findLengthOfShortestCommonSuperSequence("AGGTAB", "GXTXAYB"));
        System.out.println("ShortestCommonSuperSequence Length is " + findLengthOfShortestCommonSuperSequence("ABCDAF", "ACBCF"));
    }

    public static int findLengthOfShortestCommonSuperSequence(String str1, String str2) {
        /**
         * s1 ===> A G G T A B
         * s2 ==>  G X T X A Y B
         *
         * what will be the super sequence of s1 and s2 ==> s1 + s2
         * A G G T A B G X T X A Y B
         *
         * but question is asking for Shortest Common SuperSequence.
         * So let's include common items just once from both s1 and s2;
         *
         * A G G X T X A Y B ==> Shortest Common SuperSequence.
         *
         * Now what were those common items.==> G T A B (Now if we notice carefully what is this it's just the LCS).
         *
         * So what can we do to find the length of Shortest Common SuperSequence
         *
         *  length of ShortestCommonSuperSequence = Length(S1) + Length(S2) - LengthOfLCS(S1, S2)
         *
         */
        return str1.length() + str2.length() - LengthOfLongestCommonSubsequence.findLengthOfLCS(str1, str2);
    }
}
