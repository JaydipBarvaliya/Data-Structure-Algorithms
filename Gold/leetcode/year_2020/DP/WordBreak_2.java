package com.leetcode.year_2020.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author neeraj on 30/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class WordBreak_2 {

    public static void main(String[] args) {
        System.out.println(wordBreak("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")));
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        return wordBreakHelper(s, wordDict, new HashMap<>());
    }

    private static List<String> wordBreakHelper(String s, List<String> wordDict, Map<String, List<String>> cache) {
        if (cache.containsKey(s)) {
            return cache.get(s);
        }
        List<String> result = new ArrayList<>();

        if (s.isEmpty()) {
            result.add("");
            cache.put(s, result);
            return cache.get(s);
        }

        /**
         * We will check for each word in the dictionary do our s starts with
         * that word
         * So if our word is [pineapplepenapple]
         * and wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
         *
         * and we found that pine is the starting word, then we will solve the same problem with smaller input.
         *
         * word : "pine"  +  wordBreakHelper([applepenapple], ["apple", "pen", "applepen", "pine", "pineapple"])
         * then it matches "apple"
         *
         * word: "apple" + wordBreakHelper([penapple], ["apple", "pen", "applepen", "pine", "pineapple"])
         *
         * word: "pen" + wordBreakHelper([apple], ["apple", "pen", "applepen", "pine", "pineapple"])
         *
         * word: "apple" + wordBreakHelper([""], ["apple", "pen", "applepen", "pine", "pineapple"]) -====> Hits Base Case
         *
         * and finally our word becomes : "pine apple pen apple"
         *
         * And similarly when you go on "apple" you will also find dictionary contains "applepen" which should also be used
         */
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                String remainingSubstring = s.substring(word.length());
                List<String> allRemainingMatches = wordBreakHelper(remainingSubstring, wordDict, cache);

                // Now let's append the word to this allRemainingMatches
                for (String remaining : allRemainingMatches) {
                    String optionalSpace = remaining.isEmpty() ? "" : " ";
                    result.add(word + optionalSpace + remaining);
                }
            }
        }
        cache.put(s, result);
        return cache.get(s);
    }
}
