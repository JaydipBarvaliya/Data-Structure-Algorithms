package com.geeksforgeeks.array;

import com.util.LogUtil;

import static com.util.LogUtil.getArrayAsString;

/**
 * For example, if the given sequence is 1,2,4,5,6,7,8 then the missing number is 3
 * If the given sequence is 1,3,4,5 then the missing number is 2.
 * For the input 2,3,4,5 output returned should be 1 as it is the missing number.
 *
 * @author neeraj on 2019-07-20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FindMissingNumberInIncreasingSequence {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 4, 5, 6, 7, 8};
        LogUtil.logIt("Missing Number from " + getArrayAsString(arr) + " is " + findMissingNumber(arr, 0, arr.length - 1));

        arr = new int[]{2, 3, 4, 5};
        LogUtil.logIt("Missing Number from " + getArrayAsString(arr) + " is " + findMissingNumber(arr, 0, arr.length - 1));

        arr = new int[]{1, 3, 4, 5};
        LogUtil.logIt("Missing Number from " + getArrayAsString(arr) + " is " + findMissingNumber(arr, 0, arr.length - 1));
    }

    public static int findMissingNumber(int[] arr, int start, int end) {
        if (start <= end) {
            int mid = start + (end - start) / 2;

            // If only single element is present
            if (end - start == 0) {
                if (arr[mid] == mid + 1) {
                    System.out.println("==============================");
                    System.out.println("No number is missing at index : " + mid + " but can be missing from the next index if size of array  is greater than 1");
                    if (arr.length > 1) {
                        return mid + 2;
                    } else {
                        return -1; // No Number is  missing
                    }
                } else {
                    return mid + 1;
                }
            }

            if (mid + 1 == arr[mid]) { // Nothing is missing from left, just search the right
                return findMissingNumber(arr, mid + 1, end);
            } else {
                return findMissingNumber(arr, start, mid - 1);
            }
        } else {
            return -1;
        }
    }
}
