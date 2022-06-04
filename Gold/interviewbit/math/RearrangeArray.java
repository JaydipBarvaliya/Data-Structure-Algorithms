package com.interviewbit.math;

import com.util.LogUtil;

import static com.util.LogUtil.getArrayAsString;

/**
 * https://www.interviewbit.com/problems/rearrange-array/
 * <p>
 * Rearrange a given array so that Arr[i] becomes Arr[Arr[i]] with O(1) extra space.
 * <p>
 * Example:
 * <p>
 * Input : [1, 0]
 * Return : [0, 1]
 * Lets say N = size of the array. Then, following holds true :
 * All elements in the array are in the range [0, N-1]
 * N * N does not overflow for a signed integer
 *
 * @author neeraj on 2019-07-28
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RearrangeArray {

    public static void main(String[] args) {

        rearrangeArray(new int[]{0, 1});
        rearrangeArray(new int[]{4, 0, 2, 1, 3});
        rearrangeArray(new int[]{0, 0, 1, 0, 0});
    }

    public static void rearrangeArray(int[] arr) {
        LogUtil.logIt("Rearranging array " + getArrayAsString(arr));
        arrange(arr);
        LogUtil.logIt("After rearranging " + getArrayAsString(arr));
    }

    /**
     * Before we solve this problem first talk about constrains
     * <p>
     * 1) You can’t use another Array, the solution has to be independent on the Array size
     * <p>
     * 2) Because you can’t use another Array, all the modifications have to be in the given Array only
     * <p>
     * 3) The given sequence is very important, and so is the new sequence. We have to accommodate two values in every element of our Array while solving
     * <p>
     * Now we know that every number in this universe can be represented as
     * <p>
     * ===========> Number = n * (Quotient) + Remainder  : Number = n * Q + R;
     * i.e if we divide Number by n then we get Q as quotient and R as remainder
     * <p>
     * So now let's apply this example in our problem statement, where we have to replace Arr[i] with Arr[Arr[i]]
     * <p>
     * So New Value of Arr[i] = N * (Arr[Arr[i]] % N) + Old Value of  Arr[i].
     * <p>
     * Why Mod % : because it represents Quotient
     *
     * @param a
     */
    public static void arrange(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = N * (arr[arr[i]] % N) + arr[i];
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] / N;
        }

    }
}

