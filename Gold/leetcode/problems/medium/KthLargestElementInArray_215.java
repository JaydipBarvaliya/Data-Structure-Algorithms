package com.leetcode.problems.medium;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author neeraj on 08/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class KthLargestElementInArray_215 {

    public static void main(String[] args) {
        System.out.println(findKthLargestOptimized(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        for (int i : nums) {
            maxHeap.add(i);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.poll();
    }

    public static int findKthLargestOptimized(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
