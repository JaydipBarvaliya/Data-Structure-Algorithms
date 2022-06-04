package com.leetcode.year_2020;


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author neeraj on 10/03/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ReorganizeString {

    public static void main(String[] args) {
        System.out.println(reorganizeString("aab"));
        System.out.println(reorganizeString("aaab"));
        System.out.println(reorganizeString("abcabc"));
        System.out.println(reorganizeString("vvvlo"));
    }

    public static String reorganizeString(String S) {
        Map<Character, Integer> charFreq = new HashMap<>();
        for (char c : S.toCharArray()) {
            charFreq.put(c, charFreq.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> charFreq.get(b) - charFreq.get(a));
        maxHeap.addAll(charFreq.keySet());
        StringBuilder result = new StringBuilder();

        while (!maxHeap.isEmpty()) {
            char item = maxHeap.poll();
            if (result.length() > 0 && result.charAt(result.length() - 1) == item) {
                if (maxHeap.isEmpty()) {
                    return "";
                } else {
                    char nextItem = maxHeap.poll();
                    result.append(nextItem);
                    addItemToHeapAndMapIfNecessary(maxHeap, charFreq, nextItem);
                    maxHeap.add(item);
                }
            } else {
                result.append(item);
                addItemToHeapAndMapIfNecessary(maxHeap, charFreq, item);
            }
        }
        return result.toString();
    }

    private static void addItemToHeapAndMapIfNecessary(PriorityQueue<Character> maxHeap,
                                                       Map<Character, Integer> charFreq,
                                                       char item) {
        if (charFreq.get(item) > 1) {
            charFreq.put(item, charFreq.get(item) - 1);
            maxHeap.add(item);
        }
    }
}
