package com.interviewbit.array;

import com.util.LogUtil;

import java.util.Arrays;

/**
 * https://www.interviewbit.com/problems/max-non-negative-subarray/
 *
 * @author neeraj on 2019-07-25
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaxNonNegativeSubArray {

    public static void main(String[] args) {
        LogUtil.printArray(maxset(new int[]{1, 2, 5, -7, 2, 3}));
        LogUtil.printArray(maxset(new int[]{-846930886, -1714636915, 424238335, -1649760492}));
        LogUtil.printArray(maxset(new int[]{0, 0, -1, 0}));
        LogUtil.printArray(maxset(new int[]{1967513926, 1540383426, -1303455736, -521595368}));
        LogUtil.printArray(maxset(new int[]{336465782, -278722862, -2145174067, 1101513929, 1315634022, -1369133069, 1059961393, 628175011, -1131176229, -859484421}));
        LogUtil.printArray(maxset(new int[]{756898537, -1973594324, -2038664370, -184803526, 1424268980}));
        LogUtil.printArray(maxset(new int[]{25150, 1412, 82797, 48381, 7065, -47699, -25129, -65483, -64607, -45322, -55176, 27224, 80366, 60444, 70285, -93898, -41133, 96576, -58266, 21711, -20614, -95737, 20591, -48762, -76197, -38588, -54873, 37304, 61200, -68960, 93947}));
    }

    public static int[] maxset(int[] arr) {

        // Sliding window approach.
        int L = 0;
        int R = 0;
        long maxWindowSum = 0;
        int maxWindowStart = 0;
        int maxWindowEnd = 0;
        long currentWindowSum = 0;

        while (true) {
            while (R < arr.length && arr[R] >= 0) {
                currentWindowSum += arr[R];
                R++;
            }

            if (R <= arr.length) {
                if (currentWindowSum > maxWindowSum) {
                    maxWindowSum = currentWindowSum;
                    maxWindowStart = L;
                    maxWindowEnd = R;
                } else if (maxWindowSum == currentWindowSum) {

                    // Two Conditions
                    /**
                     * NOTE: If there is a tie, then compare with segment's length and return segment which has maximum length
                     * NOTE 2: If there is still a tie, then return the segment with minimum starting index
                     */
                    if ((R - L) > (maxWindowEnd - maxWindowStart)) {
                        maxWindowStart = L;
                        maxWindowEnd = R;
                    } else if ((R - L) == (maxWindowEnd - maxWindowStart)) {
                        maxWindowStart = L;
                        maxWindowEnd = R;
                    }
                }

                R = R + 1;
                L = R;
                currentWindowSum = 0;
            } else {
                break;
            }
        }
        return Arrays.copyOfRange(arr, maxWindowStart, maxWindowEnd);
    }
}
