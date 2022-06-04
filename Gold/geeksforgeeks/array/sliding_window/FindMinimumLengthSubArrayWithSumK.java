package com.geeksforgeeks.array.sliding_window;

import com.util.LogUtil;

import static com.util.LogUtil.getArrayAsString;

/**
 * Given an array A having positive and negative integers and a number k, find the minimum length sub array of A with sum = k.
 * <p>
 * arr[] = {2,3,1,1,-1,6,4,3,8} k = 7
 * <p>
 * Output is : 2
 * and subarray is [4,3]
 *
 * @author neeraj on 2019-07-20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FindMinimumLengthSubArrayWithSumK {

    public static void main(String[] args) {
        findMinimumLengthSubArray(new int[]{2, 3, 1, 1, -1, 6, 4, 3, 8}, 7);
    }

    public static void findMinimumLengthSubArray(int[] arr, int k) {
        LogUtil.logIt("Finding Minimum Length Subarray from array ==>  " + getArrayAsString(arr) + " of size " + k, true);

        int START_INDEX_OF_SUBARRAY = 0;

        int Left_Start_of_Window = 0;
        int Right_End_of_Window = 0;
        int SUM_OF_WINDOW = arr[Left_Start_of_Window];
        int MINIMUM_WINDOW_SIZE = Integer.MAX_VALUE;

        while (Left_Start_of_Window <= Right_End_of_Window && Right_End_of_Window < arr.length) {

            if (SUM_OF_WINDOW < k) { // Since Sum of window is less than k, so let's increment window size by Right with 1.

                SUM_OF_WINDOW += arr[++Right_End_of_Window];

            } else if (SUM_OF_WINDOW > k) { // Sum of window is greater than k, so reduce our window size to check if smaller window exist.

                SUM_OF_WINDOW -= arr[Left_Start_of_Window++];

            } else { //SUM_OF_WINDOW == k

                // Let's check if this is the minimum window.
                if (MINIMUM_WINDOW_SIZE > (Right_End_of_Window - Left_Start_of_Window) + 1) {

                    START_INDEX_OF_SUBARRAY = Left_Start_of_Window;

                    MINIMUM_WINDOW_SIZE = (Right_End_of_Window - Left_Start_of_Window) + 1;

                }

                // This window sum is k, let's find out any small window.
                SUM_OF_WINDOW -= arr[Left_Start_of_Window++];
            }
        }

        int[] subarray = new int[MINIMUM_WINDOW_SIZE];
        for (int i = START_INDEX_OF_SUBARRAY, index = 0; i < START_INDEX_OF_SUBARRAY + MINIMUM_WINDOW_SIZE; i++, index++) {
            subarray[index] = arr[i];
        }
        LogUtil.logIt("Minimum Length is ==> " + MINIMUM_WINDOW_SIZE + " and subarray is ==> " + getArrayAsString(subarray));
    }
}
