package com.leetcode.year_2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Pattern Matching
 * Given a set of strings words and a string pattern return a list of all of the strings in words that matches the pattern of the pattern string.
 * <p>
 * Example 1:
 * Input:
 * words = ["aa", "bb"]
 * pattern = "cc"
 * Output: ["aa", "bb"]
 * Explanation: Both strings repeat letters just as the pattern string does.
 * <p>
 * Example 2:
 * Input:
 * words = ["aac", "bbc", "bcb", "yzy"]
 * pattern = "ghg"
 * Output: ["bcb", "yzy"]
 * <p>
 * Example 3:
 * Input:
 * words = ["aa", "bb"]
 * pattern = "zy"
 * Output: []
 *
 * @author neeraj on 12/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PatternMatching {

    public static void main(String[] args) {
        System.out.println(findAndReplacePattern(new String[]{"aa", "bb"}, "cc"));
        System.out.println(findAndReplacePattern(new String[]{"aac", "bbc", "bcb", "yzy"}, "ghg"));
        System.out.println(findAndReplacePattern(new String[]{"aa", "bb"}, "zy"));
        System.out.println(findAndReplacePattern(new String[]{"abb", "abc", "xyz", "xyy"}, "foo"));
    }

    public static List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> results = new ArrayList<>();
        // Let's make the pattern output in terms of frequency
        StringBuilder numericPattern = populateCharFrequency(pattern);
        StringBuilder numericPatternForWord;
        for (String word : words) {
            numericPatternForWord = populateCharFrequency(word);
            if (numericPatternForWord.toString().equals(numericPattern.toString())) {
                results.add(word);
            }
        }
        return results;
    }

    private static StringBuilder populateCharFrequency(String pattern) {
        Map<Character, Integer> charFrequency = new HashMap<>();
        StringBuilder numericPattern = new StringBuilder();
        for (char letter : pattern.toCharArray()) {
            if (charFrequency.containsKey(letter)) {
                numericPattern.append(charFrequency.get(letter) + 1);
                charFrequency.put(letter, charFrequency.get(letter) + 1);
            } else {
                charFrequency.put(letter, 1);
                numericPattern.append("1");
            }
        }
        return numericPattern;
    }
}
