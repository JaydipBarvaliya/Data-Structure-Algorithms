package com.datastructures.mustDoInterviewQuestions.array;

import com.geeksforgeeks.array.MergeSort;

public class MaximizeSumOfConsecutiveDifferenceInCircularArray {

    public static void main(String[] args) {
//        int arr[] = { 4, 2, 1, 8 };
        int arr[] =  { 10, 12, 15 };
        System.out.println(getMaxSum(arr));

    }

    public static int getMaxSum(int []arr) {
        rearrangeInMinMaxOrder(arr);
        int sum = 0;
        for(int i=0;i<arr.length-1;i++) {
            sum += Math.abs(arr[i]-arr[i+1]);
        }

        sum += Math.abs(arr[arr.length-1]-arr[0]);
        return sum;
    }

    public static void rearrangeInMinMaxOrder(int[] arr) {
        MergeSort.mergeSort(arr, 0, arr.length-1);

        int maxIndex = arr.length - 1;
        int minIndex = 0;
        int maxValue = arr[arr.length - 1] + 1;

        for (int i = 0; i < arr.length; i++) {

            // Now on Even Position we want small value
            if (i % 2 == 0) {
                arr[i] += (arr[minIndex] % maxValue) * maxValue;
                minIndex++;
            } else {
                arr[i] += (arr[maxIndex] % maxValue) * maxValue;
                maxIndex--;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] / maxValue;
        }

    }
}
