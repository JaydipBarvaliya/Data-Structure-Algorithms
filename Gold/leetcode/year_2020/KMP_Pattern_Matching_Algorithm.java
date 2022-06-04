package com.leetcode.year_2020;

import com.util.LogUtil;

/**
 * @author neeraj on 03/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class KMP_Pattern_Matching_Algorithm {

    public static void main(String[] args) {
        int[] properSuffixWhichIsAlsoPrefix = calculateProperSuffixWhichIsAlsoPrefix("ABCXXXABCY");
        LogUtil.printArray(properSuffixWhichIsAlsoPrefix);

        properSuffixWhichIsAlsoPrefix = calculateProperSuffixWhichIsAlsoPrefix("ACBACDABCY");
        LogUtil.printArray(properSuffixWhichIsAlsoPrefix);

        properSuffixWhichIsAlsoPrefix = calculateProperSuffixWhichIsAlsoPrefix("AACECAAA#AAACECAA");
        LogUtil.printArray(properSuffixWhichIsAlsoPrefix);
    }

    /**
     * Explanation of populating the suffix.
     *
     * @param pattern
     * @return
     */
    private static int[] calculateProperSuffixWhichIsAlsoPrefix(String pattern) {
        int j = 0;
        int patternLength = pattern.length();
        int[] properSuffixWhichIsAlsoPrefix = new int[patternLength];
        for (int i = 1; i < patternLength;) { // We will do the loop increment inside.
            if (pattern.charAt(i) == pattern.charAt(j)) {
                properSuffixWhichIsAlsoPrefix[i] = j + 1; // Since both matches so we look upto the max prefix len represented by j and add 1.
                j++;
                i++; // Now let's increment both.
            } else { // there is a mismatch.
                if(j != 0) { // If we are not a the first index, so we can jump backwards where we can find our maximum prefix
                    j = properSuffixWhichIsAlsoPrefix[j-1];
                } else {
                    properSuffixWhichIsAlsoPrefix[j] = 0; // we are at 0th index and if this doesn't match with i then
                                                        // there is no such prefix which is also a suffix
                    i++;
                }
            }
        }
        return properSuffixWhichIsAlsoPrefix;
    }
}
