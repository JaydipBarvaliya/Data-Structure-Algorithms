package com.leetcode.year_2020.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author neeraj on 02/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class WordBreak {

    public static void main(String[] args) {
        List<String> dictionary = Arrays.asList("leet", "code");
        System.out.println(canBreakWordUsingBFS("leetcode", dictionary));

        dictionary = Arrays.asList("apple", "pen");
        System.out.println(canBreakWordUsingBFS("applepenapple", dictionary));

        dictionary = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println(canBreakWordUsingBFS("catsandog", dictionary));

        dictionary = Arrays.asList("cats", "cat", "sand", "and", "dog");
        System.out.println(canBreakWordUsingBFS("catsanddog", dictionary));

        dictionary = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
        System.out.println(wordBreak("pineapplepenapple", dictionary));
    }

    public static boolean canBreakWordUsingBFS(String s, List<String> wordDictionary) {
        /**
         * Why using BFS, since it will give you result faster than DFS,as we don't have to go deep into 1 route
         * and then backtrack.
         * Now how can we apply BFS, so we can assume the index of the input String to be the starting vertex
         * and we have to reach the end.
         *
         * Watch demo of how solution will span out https://leetcode.com/problems/word-break/solution/
         *
         * Word "CATSANDDOG"  Dictionary: ["cats","dog","sand","and","cat"]
         *
         *                  C A T S A N D D O G
         *                0 1 2 3 4 5 6 7 8 9 10
         *
         * Now our BFS start by pushing 0 in the queue, and exploring all it's neighbour vertex at max of 4 since the biggest
         * word in dictionary is sand
         *
         *                  C A T S A N D D O G
         *                0 1 2 3 4 5 6 7 8 9 10
         *                /   Queue \
         *               / Remove 0  \
         *              /             \
         *            C A T        C A T S (these both got added and we killed the loop and in next iteration)
         *            /  Queue(4, 5)    \
         *           /    Remove 4       \
         *          /    //      Remove 5 \
         *        S A N D               A N D
         *        /     Queue(8)            \
         *       /     //       \\           \
         *      D O G                       D O G (and we reached destination)
         */
        int max_len = -1;
        for (String word : wordDictionary)
            max_len = Math.max(max_len, word.length());
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0); // Starting from the 0th index of the input string.
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        while (!queue.isEmpty()) {
            int currentIndex = queue.poll();
            // we should only be exploring neighbours till the max length of word, if
            // we will take substring beyond that it will never match and all our efforts is wasted.
            for (int i = currentIndex + 1; i <= s.length() && i - currentIndex <= max_len; i++) {
                if (visited.contains(i)) continue;
                if (wordDictionary.contains(s.substring(currentIndex, i))) {
                    if (i == s.length()) return true; // You have reached to the end.
                    visited.add(i);
                    queue.offer(i);
                }
            }
        }
        return false; // We cannot break the word.
    }

    static Map<String, Boolean> dp;
    static Map<Integer, Boolean> dpForPointer;
    static Map<String, String> dpPrintAll;

    public static boolean wordBreak(String s, List<String> wordDict) {
        dp = new HashMap<>();
        dpForPointer = new HashMap<>();
        List<String> sentences = new ArrayList<>();
        boolean result = solve(s, "", sentences, new HashSet<>(wordDict));
        System.out.println("Result is " + result);

        result = solve(s, 0, new HashSet<>(wordDict));
        sentences = new ArrayList<>();
        printAll(s, 0, "", sentences, new HashSet<>(wordDict));
        return result;
    }

    public static boolean solve(String s, int pointer, Set<String> wordDict) {
        if (dpForPointer.containsKey(pointer)) {
            return dpForPointer.get(pointer);
        }
        if (pointer == s.length()) {
            dpForPointer.put(pointer, true);
            return true;
        }

        for (int i = pointer; i < s.length(); i++) {
            if (wordDict.contains(s.substring(pointer, i + 1))) {
                if (solve(s, i + 1, wordDict)) {
                    dpForPointer.put(i + 1, true);
                    return true;
                }
            }
        }
        dpForPointer.put(pointer, false);
        return false; // We can't break this word
    }

    public static boolean solve(String s, String current, List<String> sentences, Set<String> wordDict) {
        if (dp.containsKey(s)) return dp.get(s);
        if (s.length() == 0) {
            sentences.add(current);
            return true;
        }
        for (int i = 1; i <= s.length(); i++) {
            String substring = s.substring(0, i);
            if (((dp.containsKey(substring) && dp.get(substring)) ||
                    wordDict.contains(s.substring(0, i))) && solve(s.substring(i), current + " " + substring, sentences, wordDict)) {
                dp.put(s, true);
                return true;
            }
        }
        dp.put(s, false);
        return false;
    }


    public static void printAll(String s, int pointer, String current, List<String> all, Set<String> wordDict) {
        if (s.length() == pointer) {
            System.out.println(current);
            all.add(current);
            return;
        }
        for (int i = pointer; i < s.length(); i++) {
            String substring = s.substring(pointer, i + 1);
            if (wordDict.contains(substring)) {
                String nextWord = current.equals("") ? (current + substring) : current + " " + substring;
                printAll(s, i + 1, nextWord, all, wordDict);
            }
        }
    }


    public static boolean solveWordBreakUsingPrefix(String s, Set<String> wordDict) {
        boolean[] possible = new boolean[s.length() + 1];
        possible[0] = true; // we can definitely decompose blank string.

        /**
         * Now the basic idea is in order to decompose a string we must be able to
         * decompose some prefix of our String and the remaining suffix must be present in word dictionary.
         * For Example : dictionary==> ["h", "e", "llo"] and String is "hello", then we are able to successfully decompose
         * "he" prefix(using "h" and "e" from dict) and the remaining "llo" suffix already available in dict.
         *
         * Now since we have to check the prefix decomposition for every index i, hence we can store the calculations
         * and memorize it using 1d array. possible[] in which possible[i] is set to true only if we can decompose [1....i]
         *
         */
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (possible[j] && wordDict.contains(s.substring(j, i))) {
                    possible[i] = true;
                    break;
                }
            }
        }
        return possible[possible.length - 1];
    }
}


