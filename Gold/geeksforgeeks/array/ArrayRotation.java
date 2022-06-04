package com.geeksforgeeks.array;

import com.util.LogUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * int arr[] = {1, 2, 3, 4, 5, 6, 7};
 * leftRotate(arr, 2, 7);
 * Output --> 3 4 5 6 7 1 2
 */
public class ArrayRotation {

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7};
        printArray(arr);
        rotateArray(arr, 2, 7);

//        cyclicRotate(arr);
//        printArray(arr);
    }

    public static void printArray(int[] input) {
        System.out.println(Arrays.stream(input).boxed().collect(Collectors.toList()));
    }

    public static void printArray(Integer[] input) {
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i] + ",");
        }
        System.out.println();
    }

    public static void leftRotateArray(int[] input, int d, int n) {
        int[] temp = new int[d];
        for (int i = 0; i < d; i++) {
            temp[i] = input[i];
        }

        for (int i = 0; i < n - d; i++) {
            input[i] = input[i + d];
        }

        for (int i = n - d, t = 0; t < temp.length; i++, t++) {
            input[i] = temp[t];
        }
    }

    /**
     * Using Reversal Algorithm
     *
     * @param input
     * @param d
     * @param n
     */
    public static void rotateArray(int[] input, int d, int n) {
        reverseArray(input, 0, d - 1);
        LogUtil.logIt("After Reversing 1st Half", true);
        printArray(input);
        reverseArray(input, d, n - 1);
        LogUtil.logIt("After Reversing 2nd Half", true);
        printArray(input);
        reverseArray(input, 0, n - 1);
        LogUtil.logIt("Solution : Left Rotate Array by  " + d, true);
        printArray(input);
    }

    public static void reverseArray(int[] data, int start, int end) {
        int temp;
//        for (int i = start, tempEnd = end - 1; i < (start + end) / 2; i++, tempEnd--) {
//            temp = data[i];
//            data[i] = data[tempEnd];
//            data[tempEnd] = temp;
//        }
        while (start < end) {
            temp = data[end];
            data[end] = data[start];
            data[start] = temp;
            start++;
            end--;
        }
    }

    /**
     * Input:  arr[] = {1, 2, 3, 4, 5}
     * Output: arr[] = {5, 1, 2, 3, 4}
     */
    public static void cyclicRotate(int[] data) {
        int n = data.length;
        int temp = data[n - 1];
        for (int i = n - 1; i > 0; i--) {
            data[i] = data[i - 1];
        }
        data[0] = temp;
    }
}
