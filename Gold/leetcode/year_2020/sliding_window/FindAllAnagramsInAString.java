package com.leetcode.year_2020.sliding_window;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * <p>
 * Good Discussion :
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
 *
 * @author neeraj on 17/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FindAllAnagramsInAString {

    public static void main(String[] args) {
        System.out.println(findAnagrams("abab", "ab"));
        System.out.println(findAnagrams("cbaebabacd", "abc"));
        System.out.println(findAnagrams("baa", "aa"));
        System.out.println(findAnagrams("aaaaaaaaaa", "aa"));
    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new LinkedList<>();
        if (p.length() > s.length()) {
            return result; // Invalid condition we can't find anagram in s.
        }
        Map<Character, Integer> charFreqMap = new HashMap<>();
        for (char c : p.toCharArray()) {
            charFreqMap.put(c, charFreqMap.getOrDefault(c, 0) + 1);
        }

        // Counter which will tell us if we have seen all characters from P, once it reaches 0.
        // and this will store map.size() and not p.size() since p can contain duplicate like "bb".
        int counter = charFreqMap.size();

        // Our Sliding Window
        int start = 0, end = 0;

        // Expand the Sliding window till the end.
        while (end < s.length()) {

            // Validate last character of the Sliding window.
            char charAtEndOfSlidingWindow = s.charAt(end);
            if (charFreqMap.containsKey(charAtEndOfSlidingWindow)) {
                charFreqMap.put(charAtEndOfSlidingWindow, charFreqMap.get(charAtEndOfSlidingWindow) - 1);

                // Check if we have exhausted all occurrence of character at end of sliding window from p.
                if (charFreqMap.get(charAtEndOfSlidingWindow) == 0) counter--;
            }
            end++;

            // Now we have a window in which all characters exist, so let's invalidate that window by removing
            // char at start.
            while (counter == 0) {
                char charAtStartOfSlidingWindow = s.charAt(start);
                if (charFreqMap.containsKey(charAtStartOfSlidingWindow)) {
                    charFreqMap.put(charAtStartOfSlidingWindow, charFreqMap.get(charAtStartOfSlidingWindow) + 1);

                    // We are shrinking the window, so let's remove characters from the window.
                    // i.e Invalidate the window.
                    if (charFreqMap.get(charAtStartOfSlidingWindow) > 0) {
                        counter++;
                    }
                }

                // We found our answer.
                if (end - start == p.length()) {
                    result.add(start);
                }
                start++;
            }
        }
        return result;
    }
}
