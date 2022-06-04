package com.leetcode.year_2020;

/**
 * https://www.youtube.com/watch?v=evesA3gr9BE&t=30s
 *
 * @author neeraj on 22/03/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ShortestWayToFormString {

    public static void main(String[] args) {
        solveIt("abc", "abcbc");
        solveIt("abc", "acdbc");
        solveIt("xyz", "xzyxz");
        solveIt("morgunstanley", "morgunstanley");
    }

    public static void solveIt(String source, String target) {
        System.out.println("Minimum SubSequence needed to form target from sequence [" + target + " , " + source + " ] is " + shortestWay(source, target));
    }

    public static int shortestWay(String source, String target) {
        // Store positions from Source.
        int[] positions = new int[26];
        int minSubsequence = 0;
        int count = 0;
        for (char c : source.toCharArray()) {
            positions[c - 'a'] = ++count;
        }

        // Verify any invalid case.
        for (char c : target.toCharArray()) {
            if (positions[c - 'a'] == 0)
                return -1; // No way we can make a string since target has char which source doesn't.
        }

        // Now let's check with target
        StringBuilder subString;
        int j = 0;
        for (int i = 0; i < target.length() - 1; ) {
            subString = new StringBuilder();
            for (j = 0; i + j < target.length(); j++) {
                subString.append(target.charAt(i + j));
                if (!validSubstring(subString.toString(), positions)) {
                    i += j;
                    break;
                }
            }
            minSubsequence++;
            if (i + j == target.length()) break;
        }
        return minSubsequence;
    }

    private static boolean validSubstring(String subString, int[] positions) {
        // We will verify by just checking the position of every next character in the subString should be in
        // increment order in positions array.
        int lastPosition = -1;
        for (char c : subString.toCharArray()) {
            if (positions[c - 'a'] < lastPosition) {
                return false;
            } else {
                lastPosition = positions[c - 'a'];
            }
        }
        return true;
    }
}
