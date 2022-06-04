package com.leetcode.year_2020;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neeraj on 09/03/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class VerifyingAnAlienDictionary {

    public static void main(String[] args) {
        System.out.println(isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
        System.out.println(isAlienSorted(new String[]{"word", "world", "row"}, "worldabcefghijkmnpqstuvxyz"));
        System.out.println(isAlienSorted(new String[]{"apple", "app"}, "abcdefghijklmnopqrstuvwxyz"));
    }

    public static boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> alphabetsSequence = new HashMap<>();

        for (int i = 0; i < order.length(); i++) {
            alphabetsSequence.put(order.charAt(i), i);
        }

        for (int i = 0; i < words.length - 1; i++) {
            if (!isSorted(words[i], words[i + 1], alphabetsSequence)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSorted(String word1, String word2, Map<Character, Integer> order) {
        for (int i = 0; i < word1.length() && i < word2.length(); i++) {
            if(word1.charAt(i) == word2.charAt(i)) {
                continue;
            } else {
                return order.get(word1.charAt(i)) < order.get(word2.charAt(i));
            }
        }

        if(word1.length() > word2.length()) {
            return false;
        }

        return true;
    }
}
