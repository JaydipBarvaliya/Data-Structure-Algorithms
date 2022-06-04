package com.leetcode.year_2020.DP;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/decode-ways/
 *
 * @author neeraj on 07/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class DecodeWaysCountNumberOfWays {

    public static void main(String[] args) {
        System.out.println(numDecodings("123"));
        System.out.println(numDecodings("01"));
        System.out.println(numDecodings(""));
    }

    static int T[]; // memorization;

    public static int numDecodings(String s) {
        /**
         * Input: "12"
         * Output: 2
         * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
         * By looking at question, we clearly know one thing for sure the max
         * characters we can combine is 2 because ... Alphabets are only 26.
         *
         * So at each character :
         * --> we have select it just alone.
         * --> we can club it with one more character.
         */
        T = new int[s.length() + 1];
        Arrays.fill(T, -1);
//        return findNumberOfDecodeWays(s, 0);
        return numDecodings(0, s);
    }

    public static int numDecodings(int pointer, String s) {
        int len = s.length();
        if (pointer == len) {
            return 1; // Just 1 way of encoding it
        }
        if (T[pointer] > -1) return T[pointer];
        final char charAtPointer = s.charAt(pointer);
        if (charAtPointer == '0') return 0; // A substring starting with '0' is not allowed/valid

        int result = numDecodings(pointer + 1, s); // 1 length substring

        if (pointer < len - 1 && (charAtPointer == '1' || (s.charAt(pointer) == '2' && s.charAt(pointer + 1) < '7'))) {
            result += numDecodings(pointer + 2, s);
        }
        return T[pointer] = result;
    }

    private static int findNumberOfDecodeWays(String s, int decodePointer) {
        if (decodePointer == s.length()) {
            // we reached to the end this means we found 1 decoding.
            return 1;
        }

        // Return from cache.
        if (T[decodePointer] != -1) return T[decodePointer];

        // Now from decodePointer we will club upto 2 characters
        int totalDecompositions = 0;
        for (int i = 1; i <= 2; i++) { // Try using the character alone and then combining with the next character.
            if (decodePointer + i <= s.length()) { // So that we don't go out of bounds.
                if (isValidDecoding(s.substring(decodePointer, decodePointer + i))) {
                    totalDecompositions += findNumberOfDecodeWays(s, decodePointer + i);
                }
            }
        }
        return T[decodePointer] = totalDecompositions;
    }

    private static boolean isValidDecoding(String substring) {
        int value = Integer.parseInt(substring);
        return !substring.startsWith("0") && value >= 1 && value <= 26;
    }
}
