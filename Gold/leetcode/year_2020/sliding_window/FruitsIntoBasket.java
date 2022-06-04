package com.leetcode.year_2020.sliding_window;

import com.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/fruit-into-baskets/
 *
 * @author neeraj on 14/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FruitsIntoBasket {

    public static void main(String[] args) {
        System.out.println(totalFruit(new int[]{1, 2, 1}));
        System.out.println(totalFruit(new int[]{0, 1, 2, 2}));
        System.out.println(totalFruit(new int[]{1, 2, 3, 2, 2}));
        System.out.println(totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}));
        System.out.println(totalFruit(new int[]{5, 9, 0, 9, 6, 9, 6, 9, 9, 9}));

        LogUtil.logIt("With Sliding Window O(N) approach...");
        System.out.println(lengthOfLongestSubstringTwoDistinct(new int[]{1, 2, 1}));
        System.out.println(lengthOfLongestSubstringTwoDistinct(new int[]{0, 1, 2, 2}));
        System.out.println(lengthOfLongestSubstringTwoDistinct(new int[]{1, 2, 3, 2, 2}));
        System.out.println(lengthOfLongestSubstringTwoDistinct(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}));
        System.out.println(lengthOfLongestSubstringTwoDistinct(new int[]{5, 9, 0, 9, 6, 9, 6, 9, 9, 9}));
    }

    /**
     * This fruit problem is nothing but asking longest subarray with 2 distinct items.
     */
    public static int lengthOfLongestSubstringTwoDistinct(int[] arr) {
        Map<Integer, Integer> charFreq = new HashMap<>();
        /**
         * This problem is similar to {@link LongestSubstringWithoutRepeatingCharacters}
         * there also we didn't pre-populate charFreq, and used counter to disrupt the window.
         */
        int start = 0, end = 0, counter = 0;
        int longestSubStringLength = Integer.MIN_VALUE;
        while (end < arr.length) {
            Integer itemAtEndPointer = arr[end];
            charFreq.put(itemAtEndPointer, charFreq.getOrDefault(itemAtEndPointer, 0) + 1);
            if (charFreq.get(itemAtEndPointer) == 1) {
                counter++; // Found a New Character.
            }
            end++;

            while (counter > 2) { // this means we have found more than 2 distinct characters
                // so we need to remove those many characters
                Integer itemAtStartPointer = arr[start];
                charFreq.put(itemAtStartPointer, charFreq.get(itemAtStartPointer) - 1);
                if (charFreq.get(itemAtStartPointer) == 0) counter--;
                start++;
            }
            longestSubStringLength = Math.max(longestSubStringLength, end - start);
        }
        return longestSubStringLength;
    }

    public static int totalFruit(int[] trees) {
        /**
         * This problem is exactly similar to {@link LongestSubStringWithMUniqueCharacters}
         * In this case we just have M always 2, but it will throw TLE since we are putting these items
         * again and again in the set and then calculating it, it would be better if we could just use HashMap
         * to for the same.
         */
        int start = 0, end = 0;
        int maxTotalFruit = 0;
        Map<Integer, Integer> bucket = new HashMap<>();
        while (end < trees.length && start <= end) {
            bucket.put(trees[end], bucket.getOrDefault(trees[end], 0) + 1);
            if (bucket.size() <= 2) {
                maxTotalFruit = Math.max(maxTotalFruit, end - start + 1);
            } else {
                // we found more fruits than our bucket can accommodate.
                // so let's throw some fruit from our bucket.
                Integer count = bucket.get(trees[start]);
                if (count > 1) {
                    bucket.put(trees[start], count - 1);
                } else {
                    bucket.remove(trees[start]);
                }
                start++;
            }
            end++;
        }
        return maxTotalFruit;
    }
}
