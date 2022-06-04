package com.leetcode.year_2020.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 *
 * @author neeraj on 17/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(minWindow("aabcbcdbca", "abcd"));
        System.out.println(minWindow("XAYMBAZBDCE", "ABE"));
        System.out.println(minWindow("XZYMBEZBDCA", "ABE"));
    }

    public static String minWindow(String S, String T) {
        /**
         * This is again a variation of Sliding Window technique.
         * and is similar to {@link FindAllAnagramsInAString} problem.
         */
        String minWindow = "";
        if (T.length() > S.length()) return minWindow;
        Map<Character, Integer> charFreq = new HashMap<>();
        for (char c : T.toCharArray()) {
            charFreq.put(c, charFreq.getOrDefault(c, 0) + 1);
        }
        // This counter will tell us whether or not window contains
        // all characters.
        int counter = charFreq.size();
        int begin = 0, end = 0;
        int minWindowSize = Integer.MAX_VALUE;

        while (end < S.length()) {
            char charAtEndOfWindow = S.charAt(end);

            // Let's expand the window.
            if (charFreq.containsKey(charAtEndOfWindow)) {
                charFreq.put(charAtEndOfWindow, charFreq.get(charAtEndOfWindow) - 1);
                if (charFreq.get(charAtEndOfWindow) == 0) counter--;
            }
            end++;

            // We found a window where all elements of T exist.
            // So let's check if this is a min window and just invalidate the window.
            // by shrinking it. (i.e. Increment start).
            while (counter == 0) {
                char charAtStartOfWindow = S.charAt(begin);
                if (charFreq.containsKey(charAtStartOfWindow)) {
                    charFreq.put(charAtStartOfWindow, charFreq.get(charAtStartOfWindow) + 1);
                    if (charFreq.get(charAtStartOfWindow) > 0) counter++;
                }

                if (minWindowSize >= (end - begin)) {
                    minWindowSize = end - begin;
                    minWindow = S.substring(begin, end);
                }
                begin++;
            }
        }
        return minWindow;
    }
}
