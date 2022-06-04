package com.leetcode.year_2020.sliding_window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 *
 * @author neeraj on 18/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SubstringWithConcatenationOfAllWords {

    public static void main(String[] args) {
        System.out.println(findSubstring("barfoothefoobarman", new String[]{
                "foo", "bar"
        }));
        System.out.println(findSubstring("wordgoodgoodgoodbestword", new String[]{
                "word", "good", "best", "word"
        }));

        System.out.println(findSubstring("wordgoodgoodgoodbestword", new String[]{
                "word", "good", "best", "good"
        }));
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        /**
         * Okay so this problem is a good variation of {@link FindAllAnagramsInAString} in a String
         * the interesting and the tricky part here is we have given list of words. and instead of taking anagram
         * of single word, we have to take anagram of words.
         *
         * Input:
         *   s = "barfoothefoobarman",
         *   words = ["foo","bar"]
         *
         *   Output: [0,9]
         *
         *   Here we can either choose foobar or barfoo.
         *
         *   So the basic idea of our approach is simply.... take substring from "S" of the size of concat(AllStringsInWords)
         *   once we have that substring we will split that substring by the size of word in words.... and then put the same in
         *   a Map.... <K,V> Key== word, V == Frequency of word</K,V>. Now just compare map and they will tell us whether or not
         *   it's a match of concatenation.
         */
        List<Integer> result = new ArrayList<>();
        if (s.length() == 0 || words.length == 0) return result;
        Map<String, Integer> wordFrequencyMap = new HashMap<>();

        for (String word : words) {
            wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
        }
        int wordLength = words[0].length(); // This length same for all words as given in question.
        int stringLength = s.length();
        int totalWords = words.length;

        for (int i = 0; i <= stringLength - (wordLength * totalWords); i++) {
            /**
             * This substring is of size = totalWords * individualWordLength.
             * and then we will take our substring of this substring of size just  individualWordLength.
             * and put it inside another map... once completes we will compare newMap with wordFrequencyMap
             */
            String subString = s.substring(i, i + wordLength * totalWords);
            if (isConcatenationValid(subString, wordFrequencyMap, wordLength)) {
                result.add(i);
            }
        }
        return result;
    }

    private static boolean isConcatenationValid(String subString, Map<String, Integer> wordFrequencyMap, int wordLength) {
        Map<String, Integer> subWordsFrequencyMap = new HashMap<>();
        for (int i = 0; i < subString.length(); i += wordLength) {
            String nestedSubString = subString.substring(i, i + wordLength);
            if(wordFrequencyMap.containsKey(nestedSubString)) {
                subWordsFrequencyMap.put(nestedSubString, subWordsFrequencyMap.getOrDefault(nestedSubString, 0) + 1);
            }
        }
        return wordFrequencyMap.equals(subWordsFrequencyMap);
    }
}
