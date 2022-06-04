package com.company.amazon;

import static com.geeksforgeeks.array.ArrayRotation.printArray;

/**
 * Example:
 * Input:  arr[] = {4, 3, 7, 8, 6, 2, 1}
 * Output: arr[] = {3, 7, 4, 8, 2, 6, 1}
 * <p>
 * Input:  arr[] =  {1, 4, 3, 2}
 * Output: arr[] =  {1, 4, 2, 3}
 */
public class ConvertArrayIntoZigZagFashion {

    public static void main(String[] args) {
        int[] arr = {4, 3, 7, 8, 6, 2, 1};
        printArray(arr);
        convertIntoZigZagFashion(arr);
        printArray(arr);

        arr = new int[]{1, 4, 3, 2};
        printArray(arr);
        convertIntoZigZagFashion(arr);
        printArray(arr);


        arr = new int[]{3, 7, 4, 5, 2, 9, 12, 6};
        printArray(arr);
        convertIntoZigZagFashion(arr);
        printArray(arr);
    }

    public static void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void convertIntoZigZagFashion(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (i % 2 == 0) { // Even Numbers
                if (arr[i] > arr[i + 1]) {
                    swap(i, i + 1, arr);
                }
            } else { // Odd Numbers
                if (arr[i] < arr[i + 1]) {
                    swap(i, i + 1, arr);
                }
            }
        }
    }
}
