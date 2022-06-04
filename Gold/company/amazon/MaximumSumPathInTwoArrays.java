package com.company.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Problem 145 : Amazon Interview
 * https://www.geeksforgeeks.org/maximum-sum-path-across-two-arrays/
 */
public class MaximumSumPathInTwoArrays {

    public static void main(String[] args) {
        getMaximumSumPath(new int[]{2, 3, 7, 10, 12, 15, 30, 34}, new int[]{1, 5, 7, 8, 10, 15, 16, 19});
        getMaximumSumPath(new int[]{2, 3, 7, 10, 12}, new int[]{1, 5, 7, 8});
        getMaximumSumPath(new int[]{10, 12}, new int[]{5, 7, 9});
    }

    /**
     * 1) Traverse both array and prepare Sum Map where each key's value is the SUM of current key + SUM_TILL_NOW in the right
     * 2) Traverse both Array 1 by 1 and check that if a value is present in Map
     * if YES than SUM the currentSUM + Respective Value in Map and if it's greater than MAX_SUM update it.
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static void getMaximumSumPath(int[] arr1, int[] arr2) {
        int SUM = 0;
        int MAX_SUM = Integer.MIN_VALUE;
        int CURR_SUM = 0;
        Map<Integer, Integer> sumMap1 = new HashMap<>();
        Map<Integer, Integer> sumMap2 = new HashMap<>();

        //Prepare SumMap1 for array 1
        for (int i = arr1.length - 1; i >= 0; i--) {
            SUM += arr1[i];
            sumMap1.put(arr1[i], SUM);
        }

        // Reset SUM
        SUM = 0;

        //Prepare SumMap1 for array 2
        for (int i = arr2.length - 1; i >= 0; i--) {
            SUM += arr2[i];
            sumMap2.put(arr2[i], SUM);
        }

        // Assuming we will find nothing in common
        MAX_SUM = Math.max(sumMap1.get(arr1[0]), sumMap2.get(arr2[0]));

        // Traverse Arr1
        MAX_SUM = calculateMaxSUM(arr1, sumMap2, MAX_SUM);

        // Traverse Arr2
        MAX_SUM = calculateMaxSUM(arr2, sumMap1, MAX_SUM);

        System.out.println("MAX SUM IS :: " + MAX_SUM);
    }

    public static int calculateMaxSUM(int[] arr, Map<Integer, Integer> sumMap, int MAX_SUM) {
        int CURR_SUM = 0;
        int MAX_SUM_RES = MAX_SUM;
        int TEMP_SUM = 0;
        for (int i = 0; i < arr.length; i++) {
            if (sumMap.containsKey(arr[i])) { // Check if we change the path can we get MAX_SUM
                TEMP_SUM = CURR_SUM + sumMap.get(arr[i]);
                if (MAX_SUM_RES < TEMP_SUM) {
                    MAX_SUM_RES = TEMP_SUM;
                }
            }
            CURR_SUM += arr[i];
            if (CURR_SUM > MAX_SUM_RES) {
                MAX_SUM_RES = CURR_SUM;
            }
        }
        return MAX_SUM_RES;
    }
}
