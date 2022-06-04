package com.interviewbit.binary_search;

import com.util.LogUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., 0 1 2 4 5 6 7  might become 4 5 6 7 0 1 2 ).
 * <p>
 * You are given a target value to search. If found in the array, return its index, otherwise return -1.
 * <p>
 * You may assume no duplicate exists in the array.
 * <p>
 * Input : [4 5 6 7 0 1 2] and target = 4
 * Output : 0
 *
 * @author neeraj on 2019-07-30
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RotatedSortedArraySearch {

    public static void main(String[] args) {
        search(Arrays.asList(4, 5, 6, 7, 0, 1, 2), 4);
        search(Arrays.asList(101, 103, 106, 109, 158, 164, 182, 187, 202, 205, 2, 3, 32, 57, 69, 74, 81, 99, 100), 202);
        search(Arrays.asList(180, 181, 182, 183, 184, 187, 188, 189, 191, 192, 193, 194, 195, 196, 201, 202, 203, 204, 3, 4, 5, 6, 7, 8, 9, 10, 14, 16, 17, 18, 19, 23, 26, 27, 28, 29, 32, 33, 36, 37, 38, 39, 41, 42, 43, 45, 48, 51, 52, 53, 54, 56, 62, 63, 64, 67, 69, 72, 73, 75, 77, 78, 79, 83, 85, 87, 90, 91, 92, 93, 96, 98, 99, 101, 102, 104, 105, 106, 107, 108, 109, 111, 113, 115, 116, 118, 119, 120, 122, 123, 124, 126, 127, 129, 130, 135, 137, 138, 139, 143, 144, 145, 147, 149, 152, 155, 156, 160, 162, 163, 164, 166, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177), 42);
    }

    public static int search(final List<Integer> a, int b) {
        LogUtil.logIt("Search " + b + " in Rotated Sorted Array " + a, true);
        int position = binarySearch(a, 0, a.size() - 1, b);
        System.out.println(position);
        return position;
    }

    public static int binarySearch(List<Integer> a, int low, int high, int data) {
        if (low <= high) {
            int mid = low + (high - low) / 2;

            if (a.get(mid) == data) {
                return mid;
            } else if (a.get(low) <= a.get(mid)) { // Left side of the array is sorted.
                if (data >= a.get(low) && data <= a.get(mid)) {
                    return binarySearch(a, low, mid - 1, data);
                } else {
                    return binarySearch(a, mid + 1, high, data);
                }
            } else { // Right side of the array is sorted.
                if (data <= a.get(high) && data >= a.get(mid)) {
                    return binarySearch(a, mid + 1, high, data);
                } else {
                    return binarySearch(a, low, mid - 1, data);
                }
            }
        }
        return -1;
    }
}
