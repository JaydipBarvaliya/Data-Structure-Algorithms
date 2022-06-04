package com.leetcode.year_2020.graph.topologicalSort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://www.lintcode.com/problem/892/
 * https://leetcode.com/problems/alien-dictionary/
 */
public class AlienDictionary {

    public static final String EMPTY_STRING = "";
    public static final String ALL_GOOD_NOTHING_TO_BE_MODIFIED = "allGood";
    public static final String INVALID_STRING = "INVALID";

    public static void main(String[] args) {
        System.out.println(alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));
        System.out.println(alienOrder(new String[]{"z", "x"}));
        System.out.println(alienOrder(new String[]{"zy", "zx"}));
        System.out.println(alienOrder(new String[]{"zx", "zy"}));
        System.out.println(alienOrder(new String[]{"ab", "abc"}));
    }

    /**
     * Okay so my approach,
     * From Input：["wrt","wrf","er","ett","rftt"]
     * I can compare 2 words and figure out what is the order of 2 letters
     * <p>
     * from "wrt"and"wrf" ,we can get 't'<'f'
     * from "wrt"and"er" ,we can get 'w'<'e'
     * from "er"and"ett" ,we can get 'r'<'t'
     * from "ett"and"rftt" ,we can get 'e'<'r
     * <p>
     * So now we can use our knowledge of topological sorting and use indegree solution to solve this.
     * <p>
     * T---->F (F's indegree is 1)
     * W---->E (E's indegree is 1)
     * R---->T (T's indegree is 1)
     * E---->R (R's indegree is 1)
     * <p>
     * ---------------------
     * 0  1  1  1  1  <=============================== Indegrees.
     * ---------------------
     * W  E  R  T  F
     */
    public static String alienOrder(String[] words) {
        final Map<Character, List<Character>> ADJACENCY_LIST = new HashMap<>();
        int[] indegree = new int[26];

        for (String word : words) {
            for (char c : word.toCharArray()) {
                ADJACENCY_LIST.putIfAbsent(c, new ArrayList<>());
            }
        }

        for (int i = 1; i < words.length; i++) {
            final String firstWord = words[i - 1];
            final String secondWord = words[i];

            /** ORDER will look like A<B so we will make directed edge from A to B and increase indegree of B **/
            final String order = compareAndReturnOrderOfLetter(firstWord, secondWord);
            if (order.equals(INVALID_STRING)) return EMPTY_STRING;

            if (!order.equals(ALL_GOOD_NOTHING_TO_BE_MODIFIED)) {
                // If we have some modifications to be done on in-degrees
                final String lettersInOrder[] = order.split("<");
                ADJACENCY_LIST.putIfAbsent(lettersInOrder[0].charAt(0), new ArrayList<>());
                ADJACENCY_LIST.get(lettersInOrder[0].charAt(0)).add(lettersInOrder[1].charAt(0));
                indegree[lettersInOrder[1].charAt(0) - 'a']++;
            }
        }

        // Now apply topological sort
        PriorityQueue<Character> minHeapLexicographic = new PriorityQueue<>();

        // Traverse the array and find indegree[0] elements
        for (Character node : ADJACENCY_LIST.keySet()) {
            if (indegree[node - 'a'] == 0) {
                // Put all 0 indegree to Queue and then we'll start topological sort
                minHeapLexicographic.add(node);
            }
        }

        final StringBuilder result = new StringBuilder();
        while (!minHeapLexicographic.isEmpty()) {
            Character zeroIndegreeTask = minHeapLexicographic.remove();
            result.append(zeroIndegreeTask);

            if (ADJACENCY_LIST.containsKey(zeroIndegreeTask)) {
                for (Character adjacent : ADJACENCY_LIST.get(zeroIndegreeTask)) {
                    indegree[adjacent - 'a']--;
                    if (indegree[adjacent - 'a'] == 0) {
                        minHeapLexicographic.add(adjacent);
                    }
                }
            }
        }
        return result.length() == ADJACENCY_LIST.size() ? result.toString() : EMPTY_STRING;
    }

    private static String compareAndReturnOrderOfLetter(final String firstWord, final String secondWord) {
        int t1 = 0;
        int t2 = 0;

        while (t1 < firstWord.length() && t2 < secondWord.length()) {
            if (firstWord.charAt(t1) != secondWord.charAt(t2)) {
                return firstWord.charAt(t1) + "<" + secondWord.charAt(t2);
            } else {
                t1++;
                t2++;
            }
        }
        if (firstWord.length() > secondWord.length()) {
            // because LEXICOGRAPHICALLY
            // ABY < AB hence if we got some input like ["ABY", "AB], that's invalid
            return INVALID_STRING;
        }

        // If we are getting string ["AB", "AB"], there is nothing wrong in this order and no indegree have to be modified
        return ALL_GOOD_NOTHING_TO_BE_MODIFIED;
    }
}
