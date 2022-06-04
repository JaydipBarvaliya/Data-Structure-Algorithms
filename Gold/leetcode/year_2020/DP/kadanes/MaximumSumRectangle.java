package com.leetcode.year_2020.DP.kadanes;

import com.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Maximum Sum Rectangle In A 2D Matrix - Kadane's Algorithm Applications
 * https://www.youtube.com/watch?v=-FgseNO-6Gk
 * <p>
 * https://www.geeksforgeeks.org/maximum-sum-rectangle-in-a-2d-matrix-dp-27/
 *
 * @author neeraj on 2019-05-09
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaximumSumRectangle {

    public static void main(String[] args) {
        int[][] inputs = new int[][]{
                {1, 2, -1, -4, -20},
                {-8, -3, 4, 2, 1},
                {3, 8, 10, 1, 3},
                {-4, -1, 1, 7, -6}
        };
        maximumSumRectangleIn2DArray(inputs);
    }

    /**
     * Method to find the maximum-Sum Rectangle.
     *
     * @param input
     * @return
     */
    public static int maximumSumRectangleIn2DArray(int[][] input) {
        int MAXIMUM_RECTANGLE_SUM = 0;
        int MAXIMUM_RECT_LEFT = 0;
        int MAXIMUM_RECT_RIGHT = 0;
        int MAXIMUM_RECT_TOP = 0;
        int MAXIMUM_RECT_BOTTOM = 0;

        int[] maximum_running_sum_for_col_between_L_and_R;

        // Let's move Left and Right Pointer
        // Left pointer will only move from 0 to end of column of 1st row
        // and for each left pointer, right pointer will move forward with it.
        for (int L = 0; L < input[0].length; L++) {
            // Whenever L is being increased reset the maximum_running_sum_for_col_between_L_and_R, as Right pointer will help to fill it.
            maximum_running_sum_for_col_between_L_and_R = new int[input.length];

            for (int R = L; R < input[0].length; R++) { // We will always start Right pointer from Left Pointer as that is the top left corner of a rectangle.

                // Let's populate maximum_running_sum_for_col_between_L_and_R
                for (int row = 0; row < maximum_running_sum_for_col_between_L_and_R.length; row++) {
                    maximum_running_sum_for_col_between_L_and_R[row] += input[row][R];
                }

                // Let's find out maximum_sum_sub_contiguous_subarray.
                Map<String, Integer> kadanesOutput = maximumSumSubContiguousSubarray(maximum_running_sum_for_col_between_L_and_R);

                if (kadanesOutput.get("maxSum") > MAXIMUM_RECTANGLE_SUM) {
                    MAXIMUM_RECTANGLE_SUM = kadanesOutput.get("maxSum");
                    MAXIMUM_RECT_LEFT = L;
                    MAXIMUM_RECT_RIGHT = R;
                    MAXIMUM_RECT_TOP = kadanesOutput.get("start");
                    MAXIMUM_RECT_BOTTOM = kadanesOutput.get("end");
                }
            }
        }
        LogUtil.logIt("Calculating maximum sum rectangle");
        LogUtil.printMultiDimensionArray(input);
        LogUtil.logIt("Maximum Sum of a Rectangle is " + MAXIMUM_RECTANGLE_SUM, true);
        LogUtil.logIt("And the coordinates are Top Left[" + MAXIMUM_RECT_LEFT + " , " + MAXIMUM_RECT_TOP + "] and bottom right [" + MAXIMUM_RECT_RIGHT + " , " + MAXIMUM_RECT_BOTTOM + " ] ");

        for (int i = MAXIMUM_RECT_TOP; i <= MAXIMUM_RECT_BOTTOM; i++) {
            for (int j = MAXIMUM_RECT_LEFT; j <= MAXIMUM_RECT_RIGHT; j++) {
                System.out.print(input[i][j] + "\t");
            }
            System.out.println();
        }

        return MAXIMUM_RECTANGLE_SUM;
    }

    @SuppressWarnings("Duplicates")
    private static Map<String, Integer> maximumSumSubContiguousSubarray(int[] maximum_row_sum) {
        int maxTillNow = Integer.MIN_VALUE;
        int maxEndingHere = 0;
        int tempStart = 0, end = 0, start = 0;

        for (int i = 0; i < maximum_row_sum.length; i++) {
            maxEndingHere += maximum_row_sum[i];

            if (maxTillNow < maxEndingHere) {
                maxTillNow = maxEndingHere;
                end = i;
                start = tempStart;
            }
            if (maxEndingHere < 0) {
                maxEndingHere = 0;
                tempStart = i + 1;
            }
        }
        Map<String, Integer> kadanesResult = new HashMap<>();
        kadanesResult.put("maxSum", maxTillNow);
        kadanesResult.put("start", start);
        kadanesResult.put("end", end);
        return kadanesResult;
    }

}
