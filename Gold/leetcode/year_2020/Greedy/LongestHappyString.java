package com.leetcode.year_2020.Greedy;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/longest-happy-string/
 *
 * @author neeraj on 25/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LongestHappyString {

    public static void main(String[] args) {
        System.out.println(longestDiverseString(1, 1, 7));
        System.out.println(longestDiverseString(2, 2, 1));
        System.out.println(longestDiverseString(7, 1, 0));
        System.out.println(longestDiverseString(9, 7, 5));
        System.out.println(longestDiverseString(2, 4, 1));
        System.out.println(longestDiverseString(0, 8, 11));
    }

    static class Pair {
        char val;
        int freq;

        public Pair(char val, int freq) {
            this.val = val;
            this.freq = freq;
        }
    }

    public static String longestDiverseString(int a, int b, int c) {
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(
                (pair1, pair2) -> pair2.freq - pair1.freq);
        if (a != 0)
            maxHeap.add(new Pair('a', a));
        if (b != 0)
            maxHeap.add(new Pair('b', b));
        if (c != 0)
            maxHeap.add(new Pair('c', c));

        StringBuilder output = new StringBuilder();

        while (!maxHeap.isEmpty()) {
            Pair maxItem = maxHeap.poll();
            if (output.length() > 0 && output.charAt(output.length() - 1) == maxItem.val) {
                if (maxHeap.isEmpty()) break;

                Pair secondMax = maxHeap.poll();
                output.append(secondMax.val);

                if (secondMax.freq > 1) {
                    secondMax.freq = secondMax.freq - 1;
                    maxHeap.add(secondMax);
                }
                maxHeap.add(maxItem);
            } else {
                int upto = Math.min(2, maxItem.freq); // Since we can only have 2 consecutive same chars.
                while (upto-- > 0) output.append(maxItem.val);// Append
                int freqLeftOfPolledItem = maxItem.freq - 2;
                if (freqLeftOfPolledItem > 0) {
                    maxHeap.add(new Pair(maxItem.val, freqLeftOfPolledItem));
                }
            }
        }
        return output.toString();
    }
}
