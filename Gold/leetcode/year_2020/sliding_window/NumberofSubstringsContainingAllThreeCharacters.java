package com.leetcode.year_2020.sliding_window;

/**
 * @author neeraj on 14/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NumberofSubstringsContainingAllThreeCharacters {

    public static void main(String[] args) {
        System.out.println(getTotalSubstringCount("abcabc"));
    }

    public static int getTotalSubstringCount(String str) {
        int[] count = new int[3];

        int totalSubstring = 0;
        int end = 0, start = 0;
        while (end < str.length()) {
            count[str.charAt(end) - 'a']++;
            end++;
            while (start < str.length() && count[0] > 0 && count[1] > 0 && count[2] > 0) {
                count[str.charAt(start) - 'a']--;
                start++;
            }
            // Why totalSubstring+=start ?
            // Since everything before start was already meeting the criteria of having all three characters
            // which is why everything before start contributes to it.
            totalSubstring += start;
        }
        return totalSubstring;
    }
}
