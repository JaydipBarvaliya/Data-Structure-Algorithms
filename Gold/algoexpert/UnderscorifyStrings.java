package com.algoexpert;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author neeraj on 03/08/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class UnderscorifyStrings {

    public static void main(String[] args) {
        under_scorify_Strings("testthis is a testtest to see if testestest it works", "test");
    }

    public static void under_scorify_Strings(final String str, final String pattern) {
        List<int[]> startingIndex = findIndexes(str, pattern);
        Set<Integer> whereToPutUnderscore = collapseIndex(startingIndex);

        // Now we just have to put underscore.
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (whereToPutUnderscore.contains(i)) {
                stringBuilder.append("_");
            }
            stringBuilder.append(str.charAt(i));
        }
        System.out.println(stringBuilder.toString());
    }

    public static List<int[]> findIndexes(final String str, final String pattern) {
        List<int[]> result = new ArrayList<>();
        char[] arr = str.toCharArray();

        for (int i = 0; i < str.length(); ) {
            int index = findStartingIndex(arr, i, pattern);
            if (index == -1) {
                break;
            } else {
                result.add(new int[]{index, index + pattern.length()});
                i = index + 1;
            }
        }
        return result;
    }

    public static Set<Integer> collapseIndex(List<int[]> intervals) {
        // Now let's merge overlapping intervals.
        int[] baseInterval = intervals.get(0);
        List<int[]> nonOverlappingIntervals = new ArrayList<>();
        for (int i = 1; i < intervals.size(); i++) {
            int[] currentInterval = intervals.get(i);
            if (currentInterval[0] > baseInterval[1]) {
                nonOverlappingIntervals.add(baseInterval);
                baseInterval = currentInterval;
            } else {
                baseInterval[1] = Math.max(baseInterval[1], currentInterval[1]);
            }
        }

        nonOverlappingIntervals.add(baseInterval);
        LogUtil.logIt("New Interval where we have to put underscore", true);
        Set<Integer> whereToPutUnderscore = new HashSet<>();
        for (int[] res : nonOverlappingIntervals) {
            whereToPutUnderscore.add(res[0]);
            whereToPutUnderscore.add(res[1]);
        }
        return whereToPutUnderscore;
    }

    public static int findStartingIndex(char[] arr, int index, String wordToMatch) {
        int i = index, j = 0, start = index;

        while (j < wordToMatch.length() && i < arr.length) {
            if (arr[i] == wordToMatch.charAt(j)) {
                j++;
                i++;
            } else {
                if (j == 0) {
                    i++;
                    j++;
                } else {
                    j = 0;
                }
                start = i;
            }

        }

        if (j == wordToMatch.length()) {
            return start;
        }
        return -1; // Didn't match at all
    }
}
