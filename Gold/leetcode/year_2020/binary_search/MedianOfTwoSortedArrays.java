package com.leetcode.year_2020.binary_search;

import static com.util.LogUtil.getArrayAsString;
import static com.util.LogUtil.logIt;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * @author neeraj on 12/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        findMedianSortedArrays(new int[]{1, 3, 8, 9, 15}, new int[]{7, 11, 18, 19, 21, 25});
        findMedianSortedArrays(new int[]{23, 26, 31, 35}, new int[]{3, 5, 7, 9, 11, 16});
        findMedianSortedArrays(new int[]{1, 2, 3, 4, 5}, new int[]{10, 20, 30, 40, 50});
        findMedianSortedArrays(new int[]{1, 4, 5}, new int[]{2, 3});
        findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4});
        findMedianSortedArrays(new int[]{}, new int[]{20});
        findMedianSortedArrays(new int[]{-50, -41, -40, -19, 5, 21, 28}, new int[]{-50, -21, -10});
        findMedianSortedArrays(new int[]{1}, new int[]{1});
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        /**
         * We can do the same with merge method of Merge sort in O(N) time,
         * but there is O(LOG_N) method available for the same, lets try that
         */
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        /**
         * We want to cut both sorted arrays in such a place so that there are equal members in both left and right side
         * of that cut and all members on left side will be smaller than all the elements on the right side.
         *
         * Now think, In sorted element, do we really need to check if all elements are smaller can't we simply check the
         * maxItem of any particular side with the smallest element on the right side.
         *
         *
         * nums1: 1 , 3 , 8 , 9 , 15
         *          1   2   3   4      ========> Notice partition is happening on comma and not on numbers
         * nums2: 7, 11, 18, 19, 21, 25             // hence we will take nums1 length and not nums1.length -1;
         *
         * If i just compare 9 we know for sure 1,3, 8 will always be smaller than those numbers from which 9 is smaller
         *          * like : now if we notice elements
         *          * 9     15
         *          *
         *          * 11    18    -----> These 4 elements are enough to give me the median in sorted array.
         *
         */
        int start = 0;
        int end = nums1.length;
        int len_N1 = nums1.length;
        int len_N2 = nums2.length;
        int partition_nums1 = 0; // Partition on nums1 line.
        int partition_nums2 = 0;
        int max_left_nums1 =0, max_left_nums2 = 0;
        int min_right_nums1 = 0, min_right_nums2 = 0;
        double median = 0;

        while (start <= end) {
            partition_nums1 = start + ((end - start) / 2); // This is partition on nums1.

            // Now since we want to divide each half in equal numbers
            // so if in that half nums1 partition is taking X elements then
            // from nums2 we have to take (len(N1) + len(N2))/2 - elements in partition1 of nums1
            partition_nums2 = (len_N2 + len_N1 + 1) / 2 - partition_nums1;

            // Nums1....
            max_left_nums1 = partition_nums1 == 0 ? Integer.MIN_VALUE : nums1[partition_nums1 - 1];
            min_right_nums1 = partition_nums1 == len_N1 ? Integer.MAX_VALUE : nums1[partition_nums1];

            // Nums2
            max_left_nums2 = partition_nums2 == 0 ? Integer.MIN_VALUE : nums2[partition_nums2 - 1];
            min_right_nums2 = partition_nums2 == len_N2 ? Integer.MAX_VALUE : nums2[partition_nums2];

            // Now check if we reach to a valid partition
            if (max_left_nums1 <= min_right_nums2 && max_left_nums2 <= min_right_nums1) {
                // Now we know for even length we have 2 items contributing to the medium
                if ((len_N1 + len_N2) % 2 == 0) {
                    median = Math.max(max_left_nums1, max_left_nums2) + Math.min(min_right_nums1, min_right_nums2);
                    median /= 2d;
                }
                // and in odd case we have just 1 item which will be in left half
                else {
                    median = Math.max(max_left_nums1, max_left_nums2);
                }
                break;
            }

            // if we are here that means it's not the right partition
            if (max_left_nums1 > min_right_nums2) {
                end = partition_nums1 - 1;
            } else {
                start = partition_nums1 + 1;
            }
        }
        logIt("Median of " + getArrayAsString(nums1) + " nd " + getArrayAsString(nums2) + " is " + median);
        return median;
    }
}
