package com.leetcode.year_2020.sliding_window;

import com.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neeraj on 01/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class TotalSubStringWithKUniqueCharacters {

    public static void main(String[] args) {
        getTotalSubstringCount("pqpqs", 2);
        getTotalSubstringCount("abcabc", 3);
        getTotalSubstringCount("aaacb", 3);
        getTotalSubstringCount("abc", 3);


        getTotalSubStringsCountWithAtMostWay("pqpqs", 2);
        getTotalSubStringsCountWithAtMostWay("abcabc", 3);
        getTotalSubStringsCountWithAtMostWay("aaacb", 3);
        getTotalSubStringsCountWithAtMostWay("abc", 3);
    }

    public static void getTotalSubStringsCountWithAtMostWay(String str, int k) {
        /**
         * This is exactly the same problem {@link SubArrayWithKDifferentIntegers}
         */
        final int result = substringWithAtMostK(str.toCharArray(), k) - substringWithAtMostK(str.toCharArray(), k-1);

        LogUtil.logIt("Total SubString with K Unique Characters via AtMost way.... " + result);
    }

    private static int substringWithAtMostK(char[] arr, int K) {
        final Map<Character, Integer> countMap = new HashMap<>();
        int low = 0, high = 0;
        int totalSubStrings = 0;

        while (high < arr.length) {
            countMap.put(arr[high], countMap.getOrDefault(arr[high], 0) + 1);

            while (countMap.size() > K) {
                // Shrink the window
                countMap.put(arr[low], countMap.getOrDefault(arr[low], 0) - 1);
                if (countMap.get(arr[low]) == 0) {
                    countMap.remove(arr[low]);
                }
                low++;
            }

            totalSubStrings += high - low + 1;
            high++;
        }
        return totalSubStrings;
    }

    public static int getTotalSubstringCount(String str, int k) {
        int[] count; // Since 26 Alphabets

        int totalSubstring = 0;
        int distinctCount;
        for (int i = 0; i < str.length(); i++) {
            distinctCount = 0;
            count = new int[26];
            for (int j = i; j < str.length(); j++) {

                // Check if this item is unique.....
                if (count[str.charAt(j) - 'a'] == 0) distinctCount++;

                // Increment it's frequency
                count[str.charAt(j) - 'a']++;

                if (distinctCount == k) {
                    totalSubstring++;
                }
            }
        }
        LogUtil.logIt("Total SubString with K Unique Characters.... " + totalSubstring);
        return totalSubstring;
    }
}
