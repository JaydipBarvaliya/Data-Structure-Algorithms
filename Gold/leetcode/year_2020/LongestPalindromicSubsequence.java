package com.leetcode.year_2020;

/**
 * Solution inspired from MIT Lecture
 * https://www.youtube.com/watch?v=Tw1k46ywN6E&list=PLUl4u3cNGP6317WaSNfmCvGym2ucw3oGp&index=15
 *
 * @author neeraj on 03/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LongestPalindromicSubsequence {

    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq("bbbab"));
        System.out.println(longestPalindromeSubseq("cbbd"));
        System.out.println(longestPalindromeSubseq("agbdba"));
    }

    public static int longestPalindromeSubseq(String s) {
        return LPS(s, 0, s.length() - 1, new int[s.length()][s.length()]);
    }

    public static int LPS(String s, int i, int j, int[][] cache) {
        if (cache[i][j] != 0) { // Let's cache some answers.
            return cache[i][j];
        }
        if (i == j) return 1; // Since single letter is always palindrome.
        if (s.charAt(i) == s.charAt(j)) {
            if (i + 1 == j) {
                return 2; // 2 letters and both are equal, hence length wil be 2
            } else { // There are more items in the input string,
                // so let's move inward with contribution of 2 from this i and j
                int answer = 2 + LPS(s, i + 1, j - 1, cache);
                cache[i][j] = answer;
                return answer;
            }
        } else { // Both chars are not equal, hence we have to drop either one of them
            int answer = Math.max(
                    LPS(s, i, j - 1, cache),
                    LPS(s, i + 1, j, cache));
            cache[i][j] = answer;
            return answer;
        }
    }
}
