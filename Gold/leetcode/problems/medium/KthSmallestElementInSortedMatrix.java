package com.leetcode.problems.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author neeraj on 08/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class KthSmallestElementInSortedMatrix {

    public static void main(String[] args) {
        System.out.println(kthSmallestUsingSortedColumns(new int[][]{
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        }, 8));

        System.out.println(kthSmallestUsingSortedColumns(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}

        }, 5));
    }

    /**
     * This solution is similar to MergeKSortedList, but here Question tells us we have both rows and column sorted.
     */
    public static int kthSmallest(int[][] matrix, int k) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<MinHeapEntry> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (int i = 0; i < matrix.length; i++) {
            minHeap.add(new MinHeapEntry(i, matrix[i][0], 0));
        }
        MinHeapEntry smallest;
        while (!minHeap.isEmpty()) {
            smallest = minHeap.poll();
            result.add(smallest.val);
            if (smallest.index + 1 != matrix[0].length) {
                minHeap.add(new MinHeapEntry(smallest.arrayId, matrix[smallest.arrayId][smallest.index + 1], smallest.index + 1));
            }
        }
        return result.get(k - 1);
    }

    /**
     * Lets try utilizing sortedColumns
     */
    public static int kthSmallestUsingSortedColumns(int[][] matrix, int k) {
        PriorityQueue<Tupple> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        for (int i = 0; i < matrix[0].length; i++) {
            minHeap.add(new Tupple(0, i, matrix[0][i]));
        }

        // Now lets run the heap, till just k
        Tupple smallest;
        for (int j = 0; j < k - 1; j++) {
            smallest = minHeap.poll();
            if (smallest.x == matrix.length - 1) continue;
            minHeap.add(new Tupple(smallest.x + 1, smallest.y, matrix[smallest.x + 1][smallest.y]));
        }
        return minHeap.poll().val;
    }

    static class Tupple {
        int x;
        int y;
        int val;

        public Tupple(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    static class MinHeapEntry {
        int arrayId;
        int val;
        int index;

        public MinHeapEntry(int arrayId, int val, int index) {
            this.arrayId = arrayId;
            this.val = val;
            this.index = index;
        }
    }
}
