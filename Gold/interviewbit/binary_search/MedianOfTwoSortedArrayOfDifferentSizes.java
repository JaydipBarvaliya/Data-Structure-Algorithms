package com.interviewbit.binary_search;

import static com.util.LogUtil.getArrayAsString;
import static com.util.LogUtil.logIt;

/**
 * https://www.youtube.com/watch?v=LPFhl65R7ww&t=885s
 *
 * @author neeraj on 2019-08-03
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MedianOfTwoSortedArrayOfDifferentSizes {

    public static void main(String[] args) {
        findMedian(new int[]{1, 3, 8, 9, 15}, new int[]{7, 11, 18, 19, 21, 25});
        findMedian(new int[]{23, 26, 31, 35}, new int[]{3, 5, 7, 9, 11, 16});
        findMedian(new int[]{1, 2, 3, 4, 5}, new int[]{10, 20, 30, 40, 50});
        findMedian(new int[]{1, 4, 5}, new int[]{2, 3});
        findMedian(new int[]{1, 2}, new int[]{3, 4});
        findMedian(new int[]{}, new int[]{20});
        findMedian(new int[]{-50, -41, -40, -19, 5, 21, 28}, new int[]{-50, -21, -10});
        findMedian(new int[]{1}, new int[]{1});
    }

    public static void findMedian(int[] arr1, int[] arr2) {
        logIt("Median of " + getArrayAsString(arr1) + " nd " + getArrayAsString(arr2) + " is " + findMedianOfTwoSortedArrays(arr1, arr2));
    }

    public static double findMedianOfTwoSortedArrays(int[] X, int[] Y) {

        // We want X to be the short in length, hence if that's not the case we are
        // invoking method again with changing interchanging arrays.
        if (X.length > Y.length) {
            return findMedianOfTwoSortedArrays(Y, X);
        }

        int start = 0;
        int end = X.length;
        int N1 = X.length;
        int N2 = Y.length;
        int partitionX = 0;
        int partitionY = 0;
        int maxLeftX = 0;
        int minRightX = 0;
        int maxLeftY = 0;
        int minRightY = 0;
        double median = 0;

        while (start <= end) {
            partitionX = start + ((end - start) / 2); // This is partition on nums1.;
            partitionY = (N1 + N2 + 1) / 2 - partitionX;

            maxLeftX = partitionX == 0 ? Integer.MIN_VALUE : X[partitionX - 1];
            minRightX = partitionX == N1 ? Integer.MAX_VALUE : X[partitionX];

            maxLeftY = partitionY == 0 ? Integer.MIN_VALUE : Y[partitionY - 1];
            minRightY = partitionY == N2 ? Integer.MAX_VALUE : Y[partitionY];

            // Now let's compare
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                // Now we have reached to the point where the partition is perfect,
                // So we have to just decide that whether we are taking median of Even or Odd sizes

                if ((N1 + N2) % 2 == 0) {
                    median = (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0d;
                } else {
                    median = Math.max(maxLeftX, maxLeftY);
                }
                break;
            }
            // If maxLeftX is greater then we are too far on the right hand side, we should partition on left
            // Reason : Since X is sorted so any number after partitionX at this moment will definitely be greater than
            // minRightY, since maxLeftX is already greater than  minRightY
            else if (maxLeftX > minRightY) {
                end = partitionX - 1;
            }
            // Same Reasoning as above but in reverse order.
            else if (maxLeftY > minRightX) {
                start = partitionX + 1;
            }
        }
        return median;
    }
}
