package com.geeksforgeeks.array;

import com.util.LogUtil;

@SuppressWarnings("Duplicates")
public class MaxSumContiguousSubArray {
    public static void main(String[] args) {
        getMaximumSumContiguousSubArray(new int[]{-2, -3, 4, -1, -2, 1, 5, -3});
        getMaximumSumContiguousSubArray(new int[]{-4, -1, -10, -5});
        getMaximumSumContiguousSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});

        System.out.println("=========================================================================");

        maxSubArraySum(new int[]{-2, -3, 4, -1, -2, 1, 5, -3});
        maxSubArraySum(new int[]{-4, -1, -10, -5});
        maxSubArraySum(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
    }

    @SuppressWarnings("Duplicates")
    public static int getMaximumSumContiguousSubArray(int[] arr) {

        int MAX_ENDING_HERE = 0;
        int MAX_TILL_NOW = arr[0];
        int start = 0, end = 0, s = 0;

        for (int i = 0; i < arr.length; i++) {
            MAX_ENDING_HERE += arr[i];

            if (MAX_TILL_NOW < MAX_ENDING_HERE) {
                MAX_TILL_NOW = MAX_ENDING_HERE;
                start = s;
                end = i;
            }

            if (MAX_ENDING_HERE < 0) {
                MAX_ENDING_HERE = 0;
                s = i + 1; // Since at i MAX_ENDING_HERE becomes 0 so i definitely can not be the start of maximum sum subarray
            }
        }
        System.out.println("=========================================================================");
        ArrayRotation.printArray(arr);
        System.out.println("Maximum contiguous sum is " + MAX_TILL_NOW);
        System.out.println(" Starts at " + (start + 1) + " and ends at " + (end + 1));
        return MAX_TILL_NOW;
    }

    /**
     * https://www.youtube.com/watch?v=2MmGzdiKR9Y&t=9s
     * https://github.com/bephrem1/backtobackswe/blob/master/Dynamic%20Programming%2C%20Recursion%2C%20%26%20Backtracking/maxContiguousSubarraySum.java
     * This algo is based on simple facts whether including the currentIndex item increases the maxSum, or it's better
     * to just select the current index item without adding the previous max.
     * <p>
     * i.e. MAX( ITEM_AT_CURRENT_INDEX, ITEM_AT_CURRENT_INDEX + MAX_ENDING_HERE).
     *
     * @param arr
     * @return
     */
    public static int maxSubArraySum(int[] arr) {
        int MAX_ENDING_HERE = arr[0];
        int MAX_TILL_NOW = arr[0];

        /**
         *         We are inspecting the item at index i.
         *         We want to answer the question:
         *         "What is the Max Contiguous Subarray Sum we can achieve ending at index i?"
         *         We have 2 choices:
         *         maxEndingHere + nums[i] (extend the previous subarray best whatever it was)
         *           1.) Let the item we are sitting at contribute to this best max we achieved
         *           ending at index i - 1.
         *         nums[i] (start and end at this index)
         *           2.) Just take the item we are sitting at's value.
         *         The max of these 2 choices will be the best answer to the Max Contiguous
         *         Subarray Sum we can achieve for sub-arrays ending at index i.
         *         Example:
         *         index     0  1   2  3   4  5  6   7  8
         *         Input: [ -2, 1, -3, 4, -1, 2, 1, -5, 4 ]
         *                  -2, 1, -2, 4,  3, 5, 6,  1, 5    'maxEndingHere' at each point
         *
         *         The best sub-arrays we would take if we took them:
         *           ending at index 0: [ -2 ]           (snippet from index 0 to index 0)
         *           ending at index 1: [ 1 ]            (snippet from index 1 to index 1) [we just took the item at index 1]
         *           ending at index 2: [ 1, -3 ]        (snippet from index 1 to index 2)
         *           ending at index 3: [ 4 ]            (snippet from index 3 to index 3) [we just took the item at index 3]
         *           ending at index 4: [ 4, -1 ]        (snippet from index 3 to index 4)
         *           ending at index 5: [ 4, -1, 2 ]     (snippet from index 3 to index 5)
         *           ending at index 6: [ 4, -1, 2, 1 ]  (snippet from index 3 to index 6)
         *           ending at index 7: [ 4, -1, 2, 1, -5 ]    (snippet from index 3 to index 7)
         *           ending at index 8: [ 4, -1, 2, 1, -5, 4 ] (snippet from index 3 to index 8)
         *         Notice how we are changing the end bound by 1 everytime.
         */

        int start = 0;
        int s = 0;
        int end = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > MAX_ENDING_HERE + arr[i]) {
                s = i;
            }
            MAX_ENDING_HERE = Math.max(arr[i], MAX_ENDING_HERE + arr[i]);

            if (MAX_ENDING_HERE > MAX_TILL_NOW) {
                start = s;
                end = i;
            }
            MAX_TILL_NOW = Math.max(MAX_ENDING_HERE, MAX_TILL_NOW);

        }
        LogUtil.logIt("Max SubArray sum is " + MAX_TILL_NOW);
        LogUtil.logIt("Starts at " + start + " and ends at index " + end);
        return MAX_TILL_NOW;
    }
}
