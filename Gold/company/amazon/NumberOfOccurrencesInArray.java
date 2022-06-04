package com.company.amazon;

import com.geeksforgeeks.array.ArrayRotation;

public class NumberOfOccurrencesInArray {

    public static void main(String[] args) {
        int arr[] = {1, 1, 2, 2, 2, 2, 3};
        ArrayRotation.printArray(arr);
        System.out.println("No Of Occurrences of 2 is " + countNoOfOccurrences(arr, 2));
        System.out.println("No Of Occurrences of 1 is " + countNoOfOccurrences(arr, 1));
        System.out.println("No Of Occurrences of 3 is " + countNoOfOccurrences(arr, 3));
        System.out.println("No Of Occurrences of 5 is " + countNoOfOccurrences(arr, 5));

        System.out.println("=================================================");
        arr = new int[]{1, 2, 3, 3, 3, 3, 3};
        ArrayRotation.printArray(arr);
        System.out.println("No Of Occurrences of 2 is " + countNoOfOccurrences(arr, 2));
        System.out.println("No Of Occurrences of 1 is " + countNoOfOccurrences(arr, 1));
        System.out.println("No Of Occurrences of 3 is " + countNoOfOccurrences(arr, 3));
        System.out.println("No Of Occurrences of 5 is " + countNoOfOccurrences(arr, 5));
    }

    public static int countNoOfOccurrences(int[] arr, int occurrenceOf) {
        int left = 0;
        int right = arr.length - 1;
        boolean leftFound = false;
        boolean rightFound = false;
        int leftIndex = -1;
        int rightIndex = -1;

        while (left <= right && !leftFound) {
            int mid = (left + right) / 2;
            if (arr[mid] == occurrenceOf) {
                if (mid == 0 || arr[mid - 1] != arr[mid]) {
                    leftIndex = mid;
                    leftFound = true;
//                    System.out.println("First Occurrence found at " + leftIndex);
                } else {
                    right = mid - 1;
                }
            } else if (arr[mid] < occurrenceOf) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        left = 0;
        right = arr.length - 1;
        while (left <= right && !rightFound) {
            int mid = (left + right) / 2;
            if (arr[mid] == occurrenceOf) {
                if (mid == arr.length - 1 || arr[mid + 1] != arr[mid]) {
                    rightIndex = mid;
                    rightFound = true;
//                    System.out.println("Last Occurrence found at " + rightIndex);
                } else {
                    left = mid + 1;
                }
            } else if (arr[mid] < occurrenceOf) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return leftFound && rightFound ? rightIndex - leftIndex + 1 : -1;
    }

}
