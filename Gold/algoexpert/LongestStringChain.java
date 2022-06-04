package com.algoexpert;

import java.util.*;

/**
 * @author neeraj on 04/08/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LongestStringChain {

    public static void main(String[] args) {
        System.out.println(longestStrChain(new String[]{"a", "b", "ba", "bca", "bda", "bdca"}));
        System.out.println(longestStrChain(new String[]{"ksqvsyq", "ks", "kss", "czvh", "zczpzvdhx", "zczpzvh", "zczpzvhx", "zcpzvh", "zczvh", "gr", "grukmj", "ksqvsq", "gruj", "kssq", "ksqsq", "grukkmj", "grukj", "zczpzfvdhx", "gru"}));
    }

    public static int longestStrChain(String[] words) {
        /**
         * First Sort the words based on their length.
         */
        Arrays.sort(words, Comparator.comparingInt(String::length));

        Set<String> lookup = new HashSet<>();
        Map<String, Integer> cache = new HashMap<>();
        for (String word : words) {
            lookup.add(word);
            cache.put(word, 1); // Every word in itself is at-least 1 chain long;
        }


        int max = Integer.MIN_VALUE;

        for (String word : words) {
            // Remove 1 character at a time from the input
            /**
             * {"a", "b", "ba", "bca", "bda", "bdca"}
             *
             * Assume "bdca"
             *
             * Remove "b"  --> "dca" ["dca" doesn't existing in our result, so skip it]
             * Remove "d" --> "bca" ["bca" already exist so definitely bdca is an extension in chain to bca]
             * This is how we increment the chain.
             */
            for (int i = 0; i < word.length(); i++) {
                String newSubstr = word.substring(0, i) + word.substring(i + 1);

                if (lookup.contains(newSubstr)) {
                    // So removing a character from current is actually pointing to an existing word, hence
                    // this current word is an increment in the chain

                    if (cache.get(newSubstr) + 1 > cache.get(word)) {
                        // Updating the length chain only if it increments.
                        cache.put(word, cache.get(newSubstr) + 1);
                    }
                }
            }
            max = Math.max(cache.get(word), max);
        }
        return max;
    }
}
