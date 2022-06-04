package com.datastructures.mustDoInterviewQuestions.array;

import com.geeksforgeeks.array.ArrayRotation;
import com.geeksforgeeks.array.MergeSort;

import java.util.Collections;

public class RearrangeMaximumMinimum {

    public static void main(String[] args) {
//        int arr[] = {1, 2, 3, 4, 5, 6, 7};
        //int[] arr = {1,2,4,8};
        int []arr = {7, 1, 2, 3, 4, 5, 6};
        MergeSort.mergeSort(arr,0,6);
        rearrangeInMaxMinOrderWithoutExtraSpace(arr);
    }

    public static void rearrangeInMaximumAndMinimum(int[] arr) {
        int[] temp = new int[arr.length];
        int low = 0;
        int high = arr.length - 1;

        Boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            if (flag) {
                temp[i] = arr[low++];
            } else {
                temp[i] = arr[high--];
            }
            flag = !flag;
        }
        ArrayRotation.printArray(temp);

    }

    public static void rearrangeInMaxMinOrderWithoutExtraSpace(int[] arr) {
        int length = arr.length;
        int minIndex = 0;
        int maxIndex = length - 1;
        int maxElement = arr[length-1] + 1;

        for (int i = 0; i < length; i++) {

            if (i % 2 == 0) {
                arr[i] += (arr[maxIndex] % maxElement) * maxElement;
                maxIndex--;
            } else {
                arr[i] += (arr[minIndex] % maxElement) * maxElement;
                minIndex++;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] / maxElement + "\t");
        }
    }
}
