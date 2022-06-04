package com.leetcode.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author neeraj on 05/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FirstNegativeIntegerEveryWindowSizeK {

    public static void main(String[] args) {
        firstNegativeInWindow(new int[]{-8, 2, 3, -6, 10}, 2);
        firstNegativeInWindow(new int[]{12, -1, -7, 8, -15, 30, 16, 28}, 3);
    }

    public static void firstNegativeInWindow(int[] arr, int K) {
        Queue<Integer> negatives = new LinkedList<>();

        for (int i = 0; i < K; i++) {
            if (arr[i] < 0) {
                negatives.add(i);
            }
        }

        int left = 0, right = K - 1;
        while (right < arr.length) {
            System.out.print(negatives.isEmpty() ? 0 : arr[negatives.peek()]);
            System.out.print(", ");

            right++;
            if (right < arr.length) {
                if (arr[right] < 0) {
                    negatives.add(right);
                }
                if (left == negatives.peek()) {
                    negatives.poll();
                }
                left++;
            }
        }
        System.out.println();
    }
}
