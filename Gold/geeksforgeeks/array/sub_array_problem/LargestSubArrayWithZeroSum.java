package com.geeksforgeeks.array.sub_array_problem;

import com.geeksforgeeks.array.ArrayRotation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LargestSubArrayWithZeroSum {

    public static void main(String[] args) {
        findLargestSubArrayWithZeroSum(new int[]{15, -2, 2, -8, 1, 7, 10, 23});
    }

    /**
     * 1) Calculate SumArray which represent sum of elements form 0 to i
     * 2) Now just simply traverse this array and push val and it's index in map if not already present,
     * if val is already there in Map then this means this is a SubArray with sum 0;
     * 3) But we will not stop here we keep on searching until we find the largest subarray
     *
     * @param arr
     */
    public static int[] findLargestSubArrayWithZeroSum(int[] arr) {
        Map<Integer, Integer> valIndexMap = new HashMap<>();

        Integer LARGEST_SUB_ARR_LENGTH = 0;
        Integer LARGEST_SUB_ARR_START = 0;
        Integer LARGEST_SUB_ARR_END = 0;

        int[] sumArr = new int[arr.length];
        sumArr[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i];
        }

        // Let's traverse again and find if SubArray exists
        int subArrStart = 0;
        int subArrEnd = 0;
        boolean subArrayFound = false;
        for (int i = 0; i < sumArr.length; i++) {
            if (valIndexMap.containsKey(sumArr[i])) { // this is the end of that SubArray with 0 sum
                subArrEnd = i;
                subArrStart = valIndexMap.get(sumArr[i]) + 1;
                subArrayFound = true;
                if (subArrEnd - subArrStart > LARGEST_SUB_ARR_LENGTH) {
                    LARGEST_SUB_ARR_LENGTH = subArrEnd - subArrStart;
                    LARGEST_SUB_ARR_END = subArrEnd;
                    LARGEST_SUB_ARR_START = subArrStart;
                }
            } else {
                valIndexMap.put(sumArr[i], i);
            }
        }

        int[] tempArr = new int[LARGEST_SUB_ARR_END - LARGEST_SUB_ARR_START];
        if (subArrayFound) {
            tempArr = Arrays.copyOfRange(arr, LARGEST_SUB_ARR_START, LARGEST_SUB_ARR_END + 1);
            ArrayRotation.printArray(tempArr);
            return tempArr;
        }
        System.out.println("No SUB Array found with zero sum");
        return tempArr;
    }
}
