package com.leetcode.year_2020;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author neeraj on 28/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class KLargestElementsInAnImmutableMaxHeap {

    static int[] immutableHeap = null;

    public static void main(String[] args) {
        System.out.println(kLargestInImmutableHeap(new int[]{17, 7, 16, 2, 3, 15, 14},
                4));
    }

    public static List<Integer> kLargestInImmutableHeap(int[] immHeap, int k) {
        immutableHeap = immHeap;
        List<Integer> elements = new ArrayList<>();
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a, b) -> b.value.compareTo(a.value));
        maxHeap.add(new Pair(0, immHeap[0]));

        for (int i = 0; i < k; i++) {
            Pair polled = maxHeap.poll();
            elements.add(polled.value);

            if (hasLeftChild(polled.index)) {
                maxHeap.add(new Pair(getLeftChildIndex(polled.index), getLeftChild(polled.index)));
            }

            if (hasRightChild(polled.index)) {
                maxHeap.add(new Pair(getRightChildIndex(polled.index), getRightChild(polled.index)));
            }
        }
        return elements;
    }

    static class Pair {
        int index;
        Integer value;

        public Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    private static int getLeftChildIndex(int index) {
        return index * 2 + 1;
    }

    private static int getRightChildIndex(int index) {
        return index * 2 + 2;
    }

    private static boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < immutableHeap.length;
    }

    private static boolean hasRightChild(int index) {
        return getRightChildIndex(index) < immutableHeap.length;
    }

    private static int getLeftChild(int index) {
        return immutableHeap[getLeftChildIndex(index)];
    }

    private static int getRightChild(int index) {
        return immutableHeap[getRightChildIndex(index)];
    }
}
