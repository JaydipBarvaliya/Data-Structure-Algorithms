package com.leetcode.problems.hard;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/minimum-window-substring/submissions/
 *
 * @author neeraj on 20/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(minWindowOptimal("ADOBECODEBANC", "ABC"));
        System.out.println(minWindowOptimal("ADOBECODEBANC", "ABC"));
    }


    public static String minWindowOptimal(String haystack, String needle) {
        // We can use Map but char [] is much more faster
        int[] freq = new int[128]; // Total 128 Characters, which will keep count of all needle characters freq
        int minLength = Integer.MAX_VALUE;
        int minStart = 0;
        for (int i : needle.toCharArray()) {
            freq[i]++;
        }
        int end = 0;
        int start = 0;
        int counter = needle.length(); // Length of characters to be search.

        while (end < haystack.length()) {
            if (freq[haystack.charAt(end)] > 0) {
                counter--;
            }
            freq[haystack.charAt(end)]--;
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
                freq[haystack.charAt(start)] = freq[haystack.charAt(start)] + 1;
                if (freq[haystack.charAt(start)] > 0) {
                    counter++;

                }
                start++;
            }
        }
        return minLength == Integer.MAX_VALUE ? "" : haystack.substring(minStart, minStart + minLength);
    }

    /**
     * This solution will give time-limit exceed, because we are over-doing the work
     * We can reduce the work by smartly calculating if window satisfies the needle
     * Writing optimal solution above.
     *
     * @param haystack
     * @param needle
     * @return
     */
    public static String minWindow(String haystack, String needle) {
        if (haystack.length() <= 1) { // Edge case
            if (haystack.equalsIgnoreCase(needle)) {
                return needle;
            } else {
                return "";
            }
        }
        int left = 0;
        int right = 0;

        Map<Character, Long> needleCharFreq = needle.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        String window = null;
        String minimumWindow = "";

        while (left < haystack.length() && right < haystack.length()) {
            window = haystack.substring(left, right + 1);
            if (isNeedlePresentInWindow(needleCharFreq, window)) {
                if (minimumWindow == "" || (minimumWindow.length() > (right - left + 1))) {
                    minimumWindow = haystack.substring(left, right + 1);
//                    System.out.println("Minimum Window is " + minimumWindow);
                }
                left++;
            } else {
                right++;
            }
        }
        return minimumWindow;
    }

    private static boolean isNeedlePresentInWindow(Map<Character, Long> needleCharFreq, String window) {
        Map<Character, Long> windowCharFreq = window.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        for (Character c : needleCharFreq.keySet()) {
            if (!windowCharFreq.containsKey(c) || (windowCharFreq.get(c) < needleCharFreq.get(c))) {
                return false;
            }
        }
        return true;
    }
}
