package com.leetcode.year_2020;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neeraj on 03/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PalindromePairs {

    public static void main(String[] args) {
        System.out.println(palindromePairs(new String[]{
                "abcd", "dcba", "lls", "s", "sssll"
        }));
    }

    public static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> allPairs = new ArrayList<>();
        dfs(words, 0, new ArrayList<>(), allPairs);
        return allPairs;
    }

    public static void dfs(String[] words,
                           int index,
                           List<Integer> pair,
                           List<List<Integer>> allPairs) {
        if (pair.size() > 2) return;
        if (pair.size() == 2) {
            String str = words[pair.get(0)] + words[pair.get(1)];
            if (str.equalsIgnoreCase(new StringBuilder(str).reverse().toString())) {
                System.out.println("Palindrome " + str);
                allPairs.add(new ArrayList<>(pair));
            }
        }

        for (int i = 0; i < words.length; i++) {
            if(pair.contains(i)) continue;
            pair.add(i);
            dfs(words, i + 1, pair, allPairs);
            pair.remove(pair.size() - 1);
        }
    }
}
