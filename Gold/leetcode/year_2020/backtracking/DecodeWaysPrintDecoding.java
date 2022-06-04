package com.leetcode.year_2020.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/decode-ways/
 *
 * @author neeraj on 07/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class DecodeWaysPrintDecoding {

    public static void main(String[] args) {
        System.out.println(numDecodings("123"));
        System.out.println(numDecodings("01"));
        System.out.println(numDecodings("223"));
    }

    /**
     * With Backtracking it will give TLE.... since we have to explore exhaustive search space solution.
     *
     * @param s
     * @return
     */
    public static int numDecodings(String s) {
        /**
         * Let's explore via backtracking and then we'll jump to the
         * DP.
         */
        List<List<String>> decoding = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), decoding);
        System.out.println(decoding);
        return decoding.size();
    }

    private static void backtrack(String s, int pointer, ArrayList<String> currentDecoding, List<List<String>> decoding) {
        if (pointer == s.length()) {
            decoding.add(new ArrayList<>(currentDecoding));
        }

        for (int i = pointer; i < s.length(); i++) {
            if (canBeUsedToDecode(s.substring(pointer, i + 1))) {
                char c = ((char) (Integer.parseInt(s.substring(pointer, i + 1)) + 64));
                currentDecoding.add(String.valueOf(c));
                backtrack(s, i + 1, currentDecoding, decoding);
                currentDecoding.remove(currentDecoding.size() - 1);
            }
        }
    }

    private static boolean canBeUsedToDecode(String substring) {
        int value = Integer.parseInt(substring);
        return !substring.startsWith("0") && value >= 1 && value <= 26;
    }
}
