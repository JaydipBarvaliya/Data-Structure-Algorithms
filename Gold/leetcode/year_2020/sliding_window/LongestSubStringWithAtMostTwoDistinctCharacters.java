package com.leetcode.year_2020.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
 *
 * @author neeraj on 15/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LongestSubStringWithAtMostTwoDistinctCharacters {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringTwoDistinct("eceba"));
        System.out.println(lengthOfLongestSubstringTwoDistinct("KATAPPA"));
    }

    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> charFreq = new HashMap<>();
        /**
         * This problem is similar to {@link LongestSubstringWithoutRepeatingCharacters}
         * there also we didn't pre-populate charFreq, and used counter to disrupt the window.
         */
        int start = 0, end = 0, counter = 0;
        int longestSubStringLength = Integer.MIN_VALUE;
        while (end < s.length()) {
            char charAtThisEndPointer = s.charAt(end);
            charFreq.put(charAtThisEndPointer, charFreq.getOrDefault(charAtThisEndPointer, 0) + 1);
            if (charFreq.get(charAtThisEndPointer) == 1) {
                counter++; // Found a New Character.
            }
            end++;

            while (counter > 2) { // this means we have found more than 2 distinct characters
                // so we need to remove those many characters
                char charAtStart = s.charAt(start);
                charFreq.put(charAtStart, charFreq.get(charAtStart) - 1);
                if (charFreq.get(charAtStart) == 0) counter--;
                start++;
            }

            // Why calculating longest everytime, since a single character can also be longest
            longestSubStringLength = Math.max(longestSubStringLength, end - start);
        }
        return longestSubStringLength;
    }
}
