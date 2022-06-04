package com.leetcode.year_2020.sliding_window;

/**
 * https://www.lintcode.com/en/old/problem/minimum-window-subsequence/
 * https://www.youtube.com/watch?v=W2DvQcDPD9A
 * <p>
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
 * <p>
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there are multiple such minimum-length windows, return the one with the smallest starting index.
 *
 * @author neeraj on 22/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumWindowSequence {

    public static void main(String[] args) {
//        System.out.println(minWindow("XAYMBAZBDCE", "ABE"));
        System.out.println(minWindow("abcdebddesdkashdjkasdbde", "bde"));
    }

    public static String minWindow(String S, String T) {
        /**
         * This problem could have been similar to {@link MinimumWindowSubstring}, but the twist here is the order of the
         * substring needs to be maintained.
         */
        int i = 0; // Pointer which will move on S.
        int j = 0; // Pointer which will move on T.
        String minWindow = null;
        int minWindowLength = Integer.MAX_VALUE;
        for (i = 0; i < S.length(); i++) {
            if (S.charAt(i) == T.charAt(j)) {
                j++;
            }
            // Case when we have found our window.
            if (j >= T.length()) {
                int end = i;
                j--;
                while (j >= 0) {
                    if (T.charAt(j) == S.charAt(i)) {
                        j--;
                    }
                    i--; // Also decrementing the i with it.
                }
                i++; // Since i has went back 1 position.
                if (minWindowLength > end - i - 1) {
                    minWindowLength = end - i - 1;
                    minWindow = S.substring(i, end + 1);
                }
                j = 0; // Resetting the j to 0 to identify another smallest window if exist.
                i = end;
            }
        }
        return minWindow;
    }
}
