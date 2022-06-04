package com.leetcode.year_2020.sliding_window;

import com.util.LogUtil;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.util.LogUtil.getArrayAsString;
import static com.util.LogUtil.logIt;

/**
 * https://www.geeksforgeeks.org/sliding-window-maximum-maximum-of-all-subarrays-of-size-k/
 * https://stackoverflow.com/a/17249084/3143670
 * <p>
 * https://leetcode.com/problems/sliding-window-maximum/
 * <p>
 * 239. Sliding Window Maximum
 *
 * @author neeraj on 05/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaximumOfAllSubArraysOfSizeK {

    public static void main(String[] args) {
        maximumOfAllSubArraysOfSizeK(new int[]{1, -2, 5, 6, 0, 9, 8, -1, 2, 0}, 3);
        maximumOfAllSubArraysOfSizeK(new int[]{1, 2, 3, 1, 4, 5, 2, 3, 6}, 3);
        maximumOfAllSubArraysOfSizeK(new int[]{2, 5, -1, 7, -3, -1, -2}, 4);
        maximumOfAllSubArraysOfSizeK(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);

        logIt("Maximum of Sliding window " + getArrayAsString(
                maxSlidingWindow(new int[]{1, -2, 5, 6, 0, 9, 8, -1, 2, 0}, 3)));
        logIt("Maximum of Sliding window " + getArrayAsString(
                maxSlidingWindow(new int[]{1, 2, 3, 1, 4, 5, 2, 3, 6}, 3)));
        logIt("Maximum of Sliding window " + getArrayAsString(
                maxSlidingWindow(new int[]{2, 5, -1, 7, -3, -1, -2}, 4)));
        logIt("Maximum of Sliding window " + getArrayAsString(
                maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }

    public static int[] maxSlidingWindow(int[] arr, int K) {
        /**
         * https://leetcode.com/problems/sliding-window-maximum/discuss/65884/Java-O(n)-solution-using-deque-with-explanation
         * So we will follow the Deque approach.
         * Couple of interesting facts, we will have total of n-k+1 such sub-arrays of k=3.
         * See below method to see why is that.
         *
         * Also my sliding window size is k so elements inside it should be only
         * [LeftEnd   Right End]
         * [i-k+1   ,  i]
         *
         * Also we will always store the index in the Deque. Now tell me one thing
         *
         * if we are at index "i" and there is one item in deque at index "x"
         * Now if arr[X]  < arr[i], and X < i we know for sure X-indexed item can never be greater than ith index item
         * so we kick him out.
         */
        Deque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[arr.length - K + 1];
        int resultCounter = 0;
        for (int i = 0; i < arr.length; i++) {

            // This is where we are checking that in our queue
            // we only have items ranging from [i-K+1 ----to----- i]
            if (!deque.isEmpty() && deque.peek() < i - K + 1) {
                deque.poll();
            }

            // Removing the smaller items from the Deque
            // and that too from the last, since on the peek
            // maxItem is present.
            while (!deque.isEmpty() && arr[deque.peekLast()] < arr[i]) {
                deque.pollLast();
            }

            // Adding this index which can contribute to being the maximum item.
            deque.offer(i);

            // Now if we have reached to the i that it can cover [i-k+1---to---i] distance
            if (i - K + 1 >= 0) {
                result[resultCounter++] = arr[deque.peek()];
            }
        }
        return result;
    }

    public static void maximumOfAllSubArraysOfSizeK(int[] arr, int K) {
        /**
         * If Length of Arr is N
         * then total sub-arrays of size K will be
         *
         * Total = N - K + 1;
         * Example : N(total length) == 10, and window size K = 3
         * So total sub-arrays of size k will be = (N - K + 1) i.e (10-3+1) = 8.
         *
         *Input:    1  -2  5  6  0  9  8  -1  2  0
         *
         * So let's divide the input into k windows.
         *
         *  1  -2  5 ||  6  0  9 ||  8  -1  2  ||  0
         *     K1           k2          k3        k4
         *
         *  For Each window calculate leftMax and Right Max
         *
         *  I/p:      1  -2  5 ||  6  0  9 ||  8  -1  2  || 0
         *  LeftMax:  1   1  5 ||  6  6  9 ||  8   8  8  || 0  (Left to Right)
         *  RightMax: 5   5  5 ||  9  9  9 ||  8   2  2  || 0  (Right to Left).
         *
         *  Now we have to calculate max in each subarray of size k
         *
         *  // Now we will compare last value of current subarray in LeftMax
         *  // and first value of current subarray in RightMax.
         *  for(int i=0;i<N-K+1;i++) {
         *     Max = Max(RM(i) , LM(i+K-1);
         *  }
         *
         *  // We can also extend this approach for Calculating Sum of Maximum and Minimum Elements
         *  // in sub-arrays of size K.
         *  https://www.geeksforgeeks.org/sum-minimum-maximum-elements-subarrays-size-k/
         */
        int leftMax[] = new int[arr.length];
        int rightMax[] = new int[arr.length];

        // To Solve  Sum of Maximum and Minimum Elements
        int leftMin[] = new int[arr.length];
        int rightMin[] = new int[arr.length];

        // Calculating LeftMax
        for (int i = 0; i < arr.length; i += K) {
            for (int j = i; j < i + K && j < arr.length; j++) {
                if (j == i || leftMax[j - 1] < arr[j]) {
                    leftMax[j] = arr[j];

                } else {
                    leftMax[j] = leftMax[j - 1];
                }

                if (j == i || leftMin[j - 1] > arr[j]) {
                    leftMin[j] = arr[j];
                } else {
                    leftMin[j] = leftMin[j - 1];
                }
            }
        }

//        logIt("Array is ");
//        LogUtil.printArray(arr);
//
//        logIt("Left Max of each subwindow.");
//        LogUtil.printArray(leftMax);
//
//        logIt("Left Min of each subwindow.");
//        LogUtil.printArray(leftMin);

        // Calculating RightMax
        for (int i = 0; i < arr.length; i += K) {
            for (int j = i + K - 1; j >= i && j < arr.length; j--) {
                if (j == i + K - 1 || rightMax[j + 1] < arr[j]) {
                    rightMax[j] = arr[j];
                } else {
                    rightMax[j] = rightMax[j + 1];
                }

                if (j == i + K - 1 || rightMin[j + 1] > arr[j]) {
                    rightMin[j] = arr[j];
                } else {
                    rightMin[j] = rightMin[j + 1];
                }
            }
        }
//        logIt("Right Max of each sub-window.");
//        LogUtil.printArray(rightMax);
//
//        logIt("Left Min of each sub-window.");
//        LogUtil.printArray(leftMin);
//
//        // Now we have both left and right sub-max.
//
        // Last Step is just to go over all sub-arrays of size k
        logIt("Maximum of All SubArrays of size k is ");

        int sumOfMaximumAndMinimum = 0;

        for (int i = 0; i < arr.length - K + 1; i++) {
            int MAXIMUM = Math.max(rightMax[i], leftMax[i + K - 1]);
            System.out.print(MAXIMUM + ", \t");
            sumOfMaximumAndMinimum += MAXIMUM + Math.min(rightMin[i], leftMin[i + K - 1]);

        }
        LogUtil.newLine();

//        logIt("Sum of Maximum and Minimum of All Sub-arrays of size K is " + sumOfMaximumAndMinimum);
    }
}
