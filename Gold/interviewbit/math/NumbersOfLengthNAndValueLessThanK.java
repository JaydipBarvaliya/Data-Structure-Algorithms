package com.interviewbit.math;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.interviewbit.com/problems/numbers-of-length-n-and-value-less-than-k/
 * <p>
 * Given a set of digits (A) in sorted order, find how many numbers of length B are possible whose value is less than number C.
 * <p>
 * NOTE: All numbers can only have digits from the given set.
 * Examples:
 * <p>
 * Input:
 * 0 1 5
 * 1
 * 2
 * Output:
 * 2 (0 and 1 are possible)
 * <p>
 * Input:
 * 0 1 2 5
 * 2
 * 21
 * Output:
 * 5 (10, 11, 12, 15, 20 are possible)
 * Constraints:
 * <p>
 * 1 <= B <= 9, 0 <= C <= 1e9 & 0 <= A[i] <= 9
 *
 * @author neeraj on 2019-07-27
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("Duplicates")
public class NumbersOfLengthNAndValueLessThanK {

    public static void main(String[] args) {
        numbersOfLength_N_AndValuesLessThan_K(Arrays.asList(0, 1, 5), 1, 2);
        numbersOfLength_N_AndValuesLessThan_K(Arrays.asList(0, 1, 2, 5), 2, 21);
        numbersOfLength_N_AndValuesLessThan_K(Arrays.asList( 1,2,3), 5, 21063);
        numbersOfLength_N_AndValuesLessThan_K(Arrays.asList(2, 3, 5, 6, 7, 9), 5, 42950);
        numbersOfLength_N_AndValuesLessThan_K(Arrays.asList(0, 2, 3, 4, 5, 7, 8, 9), 5, 86587);

        numbersOfLength_N_AndValuesLessThan_K(Arrays.asList(0, 1, 2, 3, 4, 5, 7, 8, 9), 9, 51822);
    }

    public static void numbersOfLength_N_AndValuesLessThan_K(List<Integer> A, int N, int K) {
        LogUtil.logIt("Finding Numbers of Length N [" + N + "] And Values Less Than K [" + K + "]");
        LogUtil.newLine();
        List<Integer> result = new ArrayList<>();
        createPermutationOfLength(A, 0, N, new int[N], result, K);
        System.out.println(result.size());
    }

    public static void createPermutationOfLength(List<Integer> input, int currentPointer, int maxLengthOfNumber, int[] currentPermutationInProcess, List<Integer> result, int maxSumAllowed) {
        if (currentPointer == maxLengthOfNumber) {
            if (isNumberInPermutationThanMaxSumAllowed(currentPermutationInProcess, maxSumAllowed)) {
                result.add(100);
            }
            return;
        }

        for (int i = 0; i < input.size() && currentPointer <= maxLengthOfNumber; i++) {
            // Set new available option
            currentPermutationInProcess[currentPointer] = input.get(i);
            // Traverse with that new option in place
            createPermutationOfLength(input, currentPointer + 1, maxLengthOfNumber, currentPermutationInProcess, result, maxSumAllowed);

            // Reset that available option since we already traversed it
            currentPermutationInProcess[currentPointer] = Integer.MAX_VALUE - 10000;
        }

    }

    private static boolean isNumberInPermutationThanMaxSumAllowed(int[] currentPermutationInProcess, int maxSumAllowed) {
        StringBuffer inputNumbers = new StringBuffer();
        for (int a : currentPermutationInProcess) {
            inputNumbers.append(a);
        }
        int permutation = Integer.parseInt(inputNumbers.toString());
        if (String.valueOf(permutation).length() == currentPermutationInProcess.length && permutation < maxSumAllowed) {
//            System.out.println("The Permutation which is less is " + permutation);
            return permutation < maxSumAllowed;
        } else {
            return false;
        }
    }
}
