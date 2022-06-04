package com.leetcode.year_2020.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://www.lintcode.com/problem/917
 */
public class PalindromePermutation_2 {

    public static void main(String[] args) {
//        System.out.println(generatePalindromes("aabb"));
//        System.out.println(generatePalindromes(""));
////        System.out.println(generatePalindromes("aabbhijkkjih")); // Will timeout with legacy approach of generating all permutations
//
//        System.out.println(generatePalindromesOptimized("aabb"));
//        System.out.println(generatePalindromesOptimized(""));
//        System.out.println(generatePalindromesOptimized("aabbhijkkjih"));
//        System.out.println(generatePalindromesOptimized("aabbccc"));

        System.out.println(canBePermutedToPalindrome("daccccdd"));
    }


    public static List<String> generatePalindromesOptimized(String s) {
        /**
         * We start by calculating all frequencies of character
         * M A D A M | M-2, D - 1, A -2
         *
         * We know that in a palindrome only 1 odd character is allowed that too in the middle, if we found odd Count after taking
         * mod(%2) we know it's not going to generate the palindrome and we break out, else we do following
         *
         * Step 0: Calculate char frequency, and then make sure odd count is 1
         *          if(oddCount>1) return EMPTY
         *
         * Step 1: With Backtracking
         *      a) Add Odd Char in the middle
         *      b) attach both even on both ends
         */

        final int[] freq = new int[256];
        int oddCount = 0;
        for (char ch : s.toCharArray()) {
            freq[ch]++;

            if (freq[ch] % 2 == 1) {
                oddCount++;
            } else {
                oddCount--;
            }
        }
        if (oddCount > 1) return Collections.EMPTY_LIST;

        // Now let's just put the odd item in middle
        String curr = "";
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] % 2 == 1) {
                curr += (char) i;
                freq[i]--;
                break;
            }
        }
        final List<String> result = new ArrayList<>();
        permuteHelper(s, curr, result, freq);
        return result;
    }

    private static void permuteHelper(final String s, final String curr, final List<String> result, final int[] freq) {
        if (curr.length() == s.length()) {
            result.add(curr);
        }

        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) {
                freq[i] -= 2;
                permuteHelper(s, (char) i + curr + (char) i, result, freq);
                freq[i] += 2;
            }
        }
    }

    public static List<String> generatePalindromes(String s) {
        if (!canBePermutedToPalindrome(s)) return Collections.emptyList();
        final List<String> result = new ArrayList<>();
        final char[] sorted = s.toCharArray();
        Arrays.sort(sorted);
        generatePalindromes(sorted, new StringBuilder(""), new boolean[s.length()], result);
        return result;
    }

    private static boolean canBePermutedToPalindrome(String s) {
        int[] char_counts = new int[128];
        for (char ch : s.toCharArray()) {
            char_counts[ch]++;
        }

        int count = 0;
        for (int i = 0; i < char_counts.length; i++) {
            count += char_counts[i] % 2; // To cancel out all matching params
        }
        return count <= 1;
    }

    private static void generatePalindromes(final char[] sorted, final StringBuilder current,
                                            boolean used[], final List<String> result) {
        if (current.length() == sorted.length && !result.contains(current.toString()) && isPalindrome(current)) {
            result.add(current.toString());
        }

        for (int i = 0; i < sorted.length; i++) {
            if (used[i] || i > 0 && sorted[i - 1] == sorted[i] && !used[i - 1]) continue;
            used[i] = true;
            current.append(sorted[i]);
            generatePalindromes(sorted, current, used, result);
            used[i] = false;
            current.deleteCharAt(current.length() - 1);
        }
    }

    private static boolean isPalindrome(final StringBuilder current) {
        int beg = 0, end = current.length() - 1;
        while (beg < end) {
            if (current.charAt(beg) != current.charAt(end)) {
                return false;
            }
            beg++;
            end--;
        }
        return true;
    }

}
