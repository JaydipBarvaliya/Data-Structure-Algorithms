package com.geeksforgeeks.array;

import java.util.Arrays;

import static com.util.LogUtil.logIt;
import static com.util.LogUtil.printArray;
import static java.lang.Math.abs;

@SuppressWarnings("Duplicates")
public class FindSmallestPositiveNumberMissingFromUnsortedArray {

    public static void main(String[] args) {
//        findSmallestPositiveUtil(new int[]{2, 3, 7, 6, 8, -1, -10, 15});

        // Another approach.
        /**
         * This is taking in assumption that all elements in the array are < size of array.
         * Traverse array from Left to Right
         * and ensure every positive value is at it's original index
         *
         * Assuming Array index start from 1 instead of 0
         * So for
         * ---------------
         * 3 | 4 | -1 | 1
         * ---------------
         * (1) (2) (3)  (4)
         * after ensuring the output should be
         * ---------------
         * 1 | -1 | 3 | 4
         * ---------------
         * (1) (2) (3)  (4)
         *
         * Now just traverse the array again and compare `index != arr[index]` that's our answer.
         *
         * Note: We can also handle the numbers greater than the size of array, by simply ignoring them for processing.
         *
         */
        findSmallestPosiiveMissingNumber(new int[]{3, 4, -1, 1});
        findSmallestPosiiveMissingNumber(new int[]{1, 2, 0});
        findSmallestPosiiveMissingNumber(new int[]{4, 4, -1, 4});

        // Testing Note
        // Note: We can also handle the numbers greater than the size of array, by simply ignoring them for processing.
        findSmallestPosiiveMissingNumber(new int[]{1, 3, 7, 6, 8, -1, -10, 15});
        findSmallestPosiiveMissingNumber(new int[]{7,8,9,11,12});
    }

    public static int findSmallestPosiiveMissingNumber(int[] arr) {
        logIt("Smallest Positive Missing Number in ", true);
        printArray(arr);

        for (int i = 0; i < arr.length; ) {
            // We have to take care of special case when we have duplicates
            // For Example with Input :  4	4	-1	4
            if (arr[i]-1 < arr.length && arr[i] > 0 && arr[i] != i + 1 && arr[i] != arr[arr[i] - 1]) {
                swap(arr, i, arr[i] - 1);
            } else {
                i++;
            }
        }

        // Now let's traverse and find the answer
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i + 1) {
                System.out.println(i + 1);
                return i + 1;
            }
        }
        System.out.println("No missing number");
        return -1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void findSmallestPositiveUtil(int[] arr) {
//        int positiveNumStart = SegregatePositiveAndNegativeNumbers.segregatePositiveAndNegativeNumbers(arr);
        int positiveNumStart = segregate(arr, arr.length);
        int[] onlyPositiveArr = Arrays.copyOfRange(arr, positiveNumStart, arr.length);

        int smallestPositiveNum = findSmallestPositiveUtil(onlyPositiveArr, onlyPositiveArr.length);
        System.out.println(smallestPositiveNum);
    }

    static int segregate(int arr[], int size) {
        int j = 0, i;
        for (i = 0; i < size; i++) {
            if (arr[i] <= 0) {
                int temp;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                // increment count of non-positive
                // integers
                j++;
            }
        }

        return j;
    }

    // Method local to util
    private static int findSmallestPositiveUtil(int[] onlyPositiveArr, int length) {
        int smallestNum = length + 1;

        for (int i = 0; i < length; i++) {

            // If the index value is actually under the size of array
            // and the Value at the found index is positive then mark it negative
            // We are subtracting 1 from the calculation because array index start from 0
            // and we are assuming positive numbers start from 1 not from 0;
            if (abs(onlyPositiveArr[i]) - 1 < length && onlyPositiveArr[onlyPositiveArr[abs(i)] - 1] > 0) {
                onlyPositiveArr[onlyPositiveArr[abs(i)] - 1] = -onlyPositiveArr[onlyPositiveArr[abs(i)] - 1];
            }
        }

        // Now Simply traverse the array and check for 1st positive number and return the index+1
        // Why this logic works is because
        // We are checking for value in the array and if 1 is missing so no one will be able to mark 0th index as -ve
        for (int i = 0; i < length; i++) {
            if (onlyPositiveArr[abs(i)] > 0) {
                smallestNum = i + 1;
                break;
            }
        }
        return smallestNum;
    }
}
