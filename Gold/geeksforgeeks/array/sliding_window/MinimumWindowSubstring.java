package com.geeksforgeeks.array.sliding_window;

import com.util.LogUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Find the smallest window in a string containing all characters of another string
 * <p>
 * https://www.geeksforgeeks.org/find-the-smallest-window-in-a-string-containing-all-characters-of-another-string/
 * https://leetcode.com/problems/minimum-window-substring/
 * <p>
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * <p>
 * Example:
 * <p>
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 *
 * @author neeraj on 2019-05-18
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("DuplicatedCode")
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        solveMinimumWindowSubStringUsingSlidingWindow("AZJSKFZTS", "SZ");
        solveMinimumWindowSubStringUsingSlidingWindow("geeksforgeeks", "ork");
        solveMinimumWindowSubStringUsingSlidingWindow("this is a test string", "tist");
        solveMinimumWindowSubStringUsingSlidingWindow("ADOBECODEBANC", "ABC");

        System.out.println("-%%%%%%%%%%%%%%%%%%%%% OPTIMAL SOLUITON %%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println(minWindowOptimal("AZJSKFZTS", "SZ"));
        System.out.println(minWindowOptimal("geeksforgeeks", "ork"));
        System.out.println(minWindowOptimal("this is a test string", "tist"));
        System.out.println(minWindowOptimal("ADOBECODEBANC", "ABC"));
    }

    public static String minWindowOptimal(String haystack, String needle) {
        // We can use Map but char [] is much more faster
        int[] toBeFoundNeedleCharFreq = new int[128]; // Total 128 Characters, which will keep count of all needle characters freq
        int minLength = Integer.MAX_VALUE;
        int minStart = 0;
        for (int i : needle.toCharArray()) {
            toBeFoundNeedleCharFreq[i]++;
        }
        int end = 0;
        int start = 0;
        int counter = needle.length(); // Length of characters to be search.

        while (end < haystack.length()) {
            if (toBeFoundNeedleCharFreq[haystack.charAt(end)] > 0) {
                counter--;
            }
            toBeFoundNeedleCharFreq[haystack.charAt(end)]--;
            end++;
            // Now if we have reached to a point when the needle is found in haystack
            // So let's minimize it, counter tells us that we reached to the end of our search
            while (counter == 0) { // i.e until counter is 0 we can shrink the window to find another minimum window

                if (minLength > (end - start)) {
                    minLength = end - start;
                    minStart = start;
                }

                // Now since we have to shrink the window, so we need to increment the freq of characters which were
                // visited but due to shrinking of window becomes unvisited from start pointer;
                toBeFoundNeedleCharFreq[haystack.charAt(start)]++;
                if (toBeFoundNeedleCharFreq[haystack.charAt(start)] > 0) {
                    counter++;
                }
                start++;
            }
        }
        return minLength == Integer.MAX_VALUE ? "" : haystack.substring(minStart, minStart + minLength);
    }

    public static void solveMinimumWindowSubStringUsingSlidingWindow(String haystack, String needle) {
        int leftPointer = 0;
        int rightPointer = 0;
        String minimumWindow = "lkdasjdlkasjdlaksdjal;ksjdalsk;djalskdjaskl;djaslKDJASLKD;JASDLKASJLDKASJDLKASJD";
        String window;

        Map<Character, Long> needleFrequency = needle.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        while (leftPointer < haystack.length() && rightPointer < haystack.length()) {
            window = haystack.substring(leftPointer, rightPointer + 1);
            if (needleFoundInHaystack(needleFrequency, window, needle)) {
                if (minimumWindow.length() > (rightPointer - leftPointer + 1)) {
                    minimumWindow = haystack.substring(leftPointer, rightPointer + 1);
//                    System.out.println(minimumWindow);
                }
                leftPointer++;
            } else {
                rightPointer++;
            }
        }
        LogUtil.logIt("Minimum Window Substring for needle[" + needle + "] in haystack[" + haystack + "]  is " + minimumWindow, true);
    }

    private static boolean needleFoundInHaystack(Map<Character, Long> needleFrequency, String window, String needle) {
        Map<Character, Long> tempFreq = new HashMap<>();

        for (char c : window.toCharArray()) {
            if (needle.contains(String.valueOf(c))) {
                if (tempFreq.containsKey(c)) {
                    tempFreq.put(c, needleFrequency.get(c) + 1L);
                } else {
                    tempFreq.put(c, 1L);
                }
            }
        }
        for (char c : needle.toCharArray()) {
            if (!tempFreq.containsKey(c) || needleFrequency.get(c) > tempFreq.get(c)) {
                return false;
            }
        }
        return true;
    }
}
