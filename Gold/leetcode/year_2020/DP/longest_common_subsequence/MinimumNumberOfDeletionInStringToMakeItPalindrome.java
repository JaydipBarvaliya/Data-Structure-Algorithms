package com.leetcode.year_2020.DP.longest_common_subsequence;

import static com.leetcode.year_2020.DP.longest_common_subsequence.LongestPalindromicSubsequence.longestPalindromeSubseqUsingLCS;

/**
 * @author neeraj on 08/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumNumberOfDeletionInStringToMakeItPalindrome {

    public static void main(String[] args) {
        System.out.println(findMinDeletionsToMakePalindrome("aebcbda"));
        System.out.println(findMinDeletionsToMakePalindrome("geeksforgeeks"));
    }

    /**
     * Input : aebcbda
     * Output : 2
     * Remove characters 'e' and 'd'
     * Resultant string will be 'abcba'
     * which is a palindromic string
     * <p>
     * Input : geeksforgeeks
     * Output : 8
     */
    public static int findMinDeletionsToMakePalindrome(String str) {
        /**
         * Okay so this problem is kindaa need some help from
         * Longest Palindromic Subsequence, why ?
         *
         * I/p : a e b c b d a    ===> What is the LPS here : a b c b a
         * , what's left is just junk for us and can be deleted, to make the complete string
         * a Palindrome.
         *
         * Now based on {@link LongestPalindromicSubsequence} we know it can be solved using
         * LCS(Longest Common Sequence) approach. By taking reverseOf(Input1) as input2.
         *
         * Length of Longest Palindromic Subsequence (inversely proportional to ) 1 / (# of Deletion)
         * So our solution will be
         * MIN_DELETIONS = str.length - LCS(str1, reverse(str2));
         */
        int MIN_DELETIONS = str.length() - longestPalindromeSubseqUsingLCS(str);
        return MIN_DELETIONS;
    }

}
