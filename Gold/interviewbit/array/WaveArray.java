package com.interviewbit.array;

import com.util.LogUtil;

import java.util.Arrays;

/**
 * https://www.interviewbit.com/problems/wave-array/
 *
 * @author neeraj on 2019-07-25
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class WaveArray {

    public static void main(String[] args) {
        LogUtil.printArray(waveArray(new int[]{1, 2, 3, 4}));
        LogUtil.printArray(waveArray(new int[]{5, 1, 3, 2, 4}));
    }

    private static int[] waveArray(int[] arr) {
        Arrays.sort(arr);
        Boolean uptrend = false;

        for (int i = 0; i < arr.length - 1; i++) {
            if (uptrend) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
                uptrend = false;
            } else {
                if (arr[i] < arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
                uptrend = true;
            }
        }
        return arr;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
