package com.geeksforgeeks.array.sub_array_problem;

import com.geeksforgeeks.array.ArrayRotation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem 112
 * https://www.geeksforgeeks.org/find-if-there-is-a-subarray-with-0-sum/
 */
public class SubArrayWithZeroSum {

    public static void main(String[] args) {
        findSubArrayWithZeroSum(new int[]{4, 2, -3, 1, 6});
        findSubArrayWithZeroSum(new int[]{4, 2, 0, 1, 6});
        findSubArrayWithZeroSum(new int[]{-3, 2, 3, 1, 6});
        findSubArrayWithZeroSum(new int[]{15, -2, 2, -8, 1, 7, 10, 23});
    }

    /**
     * 1)calculate SumArray which represent sum of elements form 0 to i
     * 2) Now just simply traverse this array and push val and it's index in map if not already present,
     * if val is already there in Map then this means this is a subarray with sum 0;
     *
     * @param arr
     */
    public static void findSubArrayWithZeroSum(int[] arr) {
        Map<Integer, Integer> valIndexMap = new HashMap<>();

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
                break;
            } else {
                valIndexMap.put(sumArr[i], i);
            }
        }

        if (subArrayFound) {
            int[] tempArr = Arrays.copyOfRange(arr, subArrStart, subArrEnd + 1);
            ArrayRotation.printArray(tempArr);
            return;
        }
        System.out.println("No SUB Array found with zero sum");
    }
}
