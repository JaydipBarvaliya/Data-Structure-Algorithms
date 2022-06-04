package com.leetcode.heap;

import com.util.LogUtil;

import java.util.*;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * <p>
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * *   1->4->5,
 * *  1->3->4,
 * *  2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 *
 * @author neeraj on 2019-05-19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MergeKSortedArrays {

    private static class MinHeapEntry {
        private Integer value;
        // This is the Id which will be used when we fetch the minItem and then we have to add that new entry.
        private Integer arrayId;

        public MinHeapEntry(Integer value, Integer arrayId) {
            this.value = value;
            this.arrayId = arrayId;
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> sortedLists = new ArrayList<>();
        sortedLists.add(Arrays.asList(1, 4, 5));
        sortedLists.add(Arrays.asList(1, 3, 4));
        sortedLists.add(Arrays.asList(2, 6));

        mergeKSortedArrays(sortedLists);
    }

    private static void mergeKSortedArrays(List<List<Integer>> sortedLists) {

        // Let's have Iterators for each List
        List<Iterator<Integer>> iterators = new ArrayList<>(sortedLists.size());

        // Setting all the iterators
        for (int i = 0; i < sortedLists.size(); i++) {
            iterators.add(sortedLists.get(i).iterator());
        }


        // Create a Min Heap
        Queue<MinHeapEntry> minHeap = new PriorityQueue<>(sortedLists.size(), Comparator.comparingInt(o -> o.value));

        for (int i = 0; i < iterators.size(); i++) {
            if (iterators.get(i).hasNext()) {
                minHeap.add(new MinHeapEntry(iterators.get(i).next(), i));
            }
        }

        List<Integer> result = new ArrayList<>();
        // Now we will fetch until heap is empty
        while (!minHeap.isEmpty()) {
            MinHeapEntry smallestItem = minHeap.poll();

            if (iterators.get(smallestItem.arrayId).hasNext()) {
                minHeap.add(new MinHeapEntry(iterators.get(smallestItem.arrayId).next(), smallestItem.arrayId));
            }
            result.add(smallestItem.value);
        }
        LogUtil.logIt("Merged Sorted List for ");
        LogUtil.printList(sortedLists);
        LogUtil.logIt(" is ");
        LogUtil.printList(result);
    }
}
