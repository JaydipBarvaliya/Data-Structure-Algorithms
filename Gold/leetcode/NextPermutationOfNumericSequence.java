package com.leetcode;

import com.util.LogUtil;

/**
 * https://leetcode.com/problems/next-permutation/
 * https://www.youtube.com/watch?v=quAS1iydq7U&list=PLiQ766zSC5jM2OKVr8sooOuGgZkvnOCTI&index=11
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * <p>
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * <p>
 * The replacement must be in-place and use only constant extra memory.
 * <p>
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * <p>
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * @author neeraj on 2019-05-11
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NextPermutationOfNumericSequence {
    public static void main(String[] args) {
        nextPermutation(6215430);
    }

    public static int nextPermutation(int number) {
        String[] numberArr = String.valueOf(number).split("");
        int indexOfFirstNonDecreasingNumber = 0;
        int indexOfSmallestNumberInStrictlyDecreasingSequence = 0;

        // Find 1st Non-Decreasing number
        for (int i = numberArr.length - 1; i > 0; i--) {
            if (Integer.parseInt(numberArr[i - 1]) < Integer.parseInt(numberArr[i])) {
                indexOfFirstNonDecreasingNumber = i - 1;
                break;
            }
        }

        for (int i = numberArr.length - 1; i > indexOfFirstNonDecreasingNumber; i--) {
            if (Integer.parseInt(numberArr[i]) > Integer.parseInt(numberArr[indexOfFirstNonDecreasingNumber])) {
                indexOfSmallestNumberInStrictlyDecreasingSequence = i;
                break;
            }
        }

        // Swap the nonDecreasing and smallest number greater than firstNonDecreasing Number in the strictly decreasing sequence
        String temp = numberArr[indexOfFirstNonDecreasingNumber];
        numberArr[indexOfFirstNonDecreasingNumber] = numberArr[indexOfSmallestNumberInStrictlyDecreasingSequence];
        numberArr[indexOfSmallestNumberInStrictlyDecreasingSequence] = temp;

        // Now even after swap we still have Strictly decreasing sequence
        // For Example
        // 6 2 1 5 4 3 0   ----Swapping 1 and 3-------> 6 2 3 5 4 1 0
        // But if you see 5 4 1 0 is still strictly decreasing, so this is the last permutation
        // What we want instead is just the next permutation.
        // So just reverse this strictly decreasing sequence 5 4 1 0 ----> 0 1 4 5
        // So final Answer = 6 2 3 0 1 4 5

        StringBuffer nextPermutation = new StringBuffer();
        // First Half
        for (int i = 0; i <= indexOfFirstNonDecreasingNumber; i++) {
            nextPermutation.append(numberArr[i]);
        }

        // Reversed half
        for (int i = numberArr.length - 1; i > indexOfFirstNonDecreasingNumber; i--) {
            nextPermutation.append(numberArr[i]);
        }

        LogUtil.logIt("Next permutation of " + number + " is " + nextPermutation.toString());
        return Integer.parseInt(nextPermutation.toString());
    }
}
