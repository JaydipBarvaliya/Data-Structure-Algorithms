package com.leetcode.year_2020.sliding_window;

import com.util.LogUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author neeraj on 01/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LongestSubStringWithMUniqueCharacters {

    public static void main(String[] args) {
        longestSubStringWithMUniqueCharacters("KATAPPA", 2);
        longestSubStringWithMUniqueCharacters("KATAPPA", 3);
        longestSubStringWithMUniqueCharacters("WORLD", 4);

        longestSubStringWithMUniqueCharactersSlidingWindow("KATAPPA", 2);
        longestSubStringWithMUniqueCharactersSlidingWindow("KATAPPA", 3);
    }

    public static int longestSubStringWithMUniqueCharactersSlidingWindow(String s, int M) {
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

            while (counter > M) { // this means we have found more than 2 distinct characters
                // so we need to remove those many characters
                char charAtStart = s.charAt(start);
                charFreq.put(charAtStart, charFreq.get(charAtStart) - 1);
                if (charFreq.get(charAtStart) == 0) counter--;
                start++;
            }
            longestSubStringLength = Math.max(longestSubStringLength, end - start);
        }
        LogUtil.logIt("Total Substring with M Distinct ==> " + longestSubStringLength);
        return longestSubStringLength;
    }

    public static int longestSubStringWithMUniqueCharacters(String str, int M) {
        int beg = 0;
        int end = 0;
        int res = 0;
        String LSS = "";
        while (end < str.length()) {
            int unique = unique(str, beg, end, M);
            if (unique <= M) {
                if (unique == M) {
                    if (res < end - beg + 1) {
                        res = end - beg + 1;
                        LSS = str.substring(beg, end + 1);
                    }
                }
                end++;
            } else {
                beg++;
            }
        }
        LogUtil.logIt("Total Substring with M Distinct ==> " + res + " and substring is " + LSS);
        return res;
    }

    public static int unique(String str, int beg, int end, int M) {
        Set<Character> set = new HashSet<>();
        for (int i = beg; i <= end; i++) {
            set.add(str.charAt(i));
        }
        return set.size();
    }
}
