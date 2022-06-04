package com.leetcode.year_2020;

import com.leetcode.year_2020.DP.longest_common_subsequence.LengthOfLongestCommonSubsequence;
import com.util.LogUtil;

/**
 * https://leetcode.com/problems/shortest-palindrome/
 *
 * @author neeraj on 03/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ShortestPalindrome {

    public static void main(String[] args) {
        System.out.println(shortestPalindrome("ABAB"));
        System.out.println(shortestPalindrome("BANANA"));
        System.out.println(shortestPalindrome("aacecaaa"));
        System.out.println(shortestPalindrome("abcd"));
    }

    public static String shortestPalindrome(String s) {
        /**
         * The Idea is abab ===add 'b' to the start and it will become ===> babab. (palindrome)
         *
         * So what we can do is ====>    str = rev_str + str ==> palindrome
         *
         * abab ==> (rev[abab] + abab); ====> baba + abab ==> babaabab (this is a palindrome, but obviously not shortest palindrome)
         * when you append reverse of any string to it's start it makes it the palindrome.
         *
         * Now to get the shortest, let's see how we can use KMP prefix table to calculate it.
         *
         * Now instead of (rev[abab] + abab) we will do (abab + "#" + rev[abab])
         * So this becomes ===> abab#baba.   (Now if i can find any suffix which is also a prefix,
         *                                  which in this case is aba (in reversed string), i can
         *                                  just remove it and push the rest in front of string
         *                                  and it will be a shortest palindrome.
         *  A B A B # B A B A
         *  _____       _____   ===> this is proper suffix, which is also prefix
         *
         *  trimming that and appending rest in front.
         *  B      A B A B
         *  _      _______
         *  Rev    FROM ORIGINAL  ===> BABAB (Shortest Palindrome).
         */

        // Now let's do it.
        String reverseOfOriginalString = new StringBuilder(s).reverse().toString();
        String reversedAppendedInTheEnd = s + "#" + reverseOfOriginalString; // Hash is needed so that we only search the maximum suffix in just
        // Reverse part and don't include original part in it.

        // Let's build the KMP prefix table.
        int j = 0; // Start of prefix.
        int[] properSuffixWhichIsAlsoPrefix = new int[reversedAppendedInTheEnd.length()];

        for (int i = 1; i < properSuffixWhichIsAlsoPrefix.length; ) {
            if (reversedAppendedInTheEnd.charAt(i) == reversedAppendedInTheEnd.charAt(j)) {
                properSuffixWhichIsAlsoPrefix[i] = j + 1;
                j++;
                i++;
            } else { // Mismatch
                if(j == 0) { // We can't go backwards
                    properSuffixWhichIsAlsoPrefix[i] = 0;
                    i++;
                } else {
                    j = properSuffixWhichIsAlsoPrefix[j-1];
                }
            }
        }


        LogUtil.printArray(properSuffixWhichIsAlsoPrefix);
        // Table is ready.
        int pointWhereWeHaveToMakeTheCut = properSuffixWhichIsAlsoPrefix[properSuffixWhichIsAlsoPrefix.length - 1];
        return reverseOfOriginalString.substring(0, reverseOfOriginalString.length() - pointWhereWeHaveToMakeTheCut) + s;
    }

    // KMP get longest prefix and suffix count
    static int[] getLPS(String s) {
        int[] lps = new int[s.length()];
        int i = 1, len = 0;

        while (i < s.length()) {
            if (s.charAt(i) == s.charAt(len))
                lps[i++] = ++len;
            else if (len == 0)
                lps[i++] = 0;
            else
                len = lps[len - 1];
        }

        return lps;
    }
}
