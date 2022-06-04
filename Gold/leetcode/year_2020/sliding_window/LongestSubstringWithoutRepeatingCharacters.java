package com.leetcode.year_2020.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * @author neeraj on 17/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("ggububgvfk"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int begin = 0, end = 0;
        int counter = 0;
        Map<Character, Integer> charFreq = new HashMap<>();
        int LONGEST_NON_REPEATING_SUBSTRING = Integer.MIN_VALUE;

        while (end < s.length()) {
            char charAtEndOfSlidingWindow = s.charAt(end);
            charFreq.put(charAtEndOfSlidingWindow, charFreq.getOrDefault(charAtEndOfSlidingWindow, 0) + 1);

            // If We found any duplicate character, increment the counter so that
            // we can shrink the window and remove that duplicate below.
            if (charFreq.get(charAtEndOfSlidingWindow) > 1) counter++;
            end++;

            while (counter > 0) {
                // We will shrink the window.
                char charAtStartOfSlidingWindow = s.charAt(begin);
                if (charFreq.get(charAtStartOfSlidingWindow) > 1) counter--;
                charFreq.put(charAtStartOfSlidingWindow, charFreq.get(charAtStartOfSlidingWindow) - 1);

                begin++;
            }
            LONGEST_NON_REPEATING_SUBSTRING = Math.max(LONGEST_NON_REPEATING_SUBSTRING, (end - begin));
        }
        return LONGEST_NON_REPEATING_SUBSTRING == Integer.MIN_VALUE ? 0 : LONGEST_NON_REPEATING_SUBSTRING;
    }
}
