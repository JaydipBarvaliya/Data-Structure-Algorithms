package com.geeksforgeeks.array;

public class QuickSort {

    public static void main(String[] args) {
//        int arr[] = {10, 80, 30, 90, 40, 50, 70};
//        int arr[] = {5, 3, 2, 4, 1};
        int arr[] = {3, 4, 5, 8, 9, 7};
        quickSort(arr, 0, 5);
        ArrayRotation.printArray(arr);
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) { 
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    public static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int partitionIndex = start; // Initially Start will be our partition index

        for (int i = start; i < end; i++) {
            if (arr[i] <= pivot) {
                swap(arr, i, partitionIndex);
                partitionIndex++;
            }
        }

        swap(arr, partitionIndex, end);
        return partitionIndex;
    }

//    public static int partition(int[] arr, int low, int high) {
//        int pivot = arr[high];
//
//        int j = low - 1;
//        for (int i = low; i < high; i++) {
//            if (arr[i] < pivot) {
//                j++;
//                swap(arr, j, i);
//            }
//        }
//        swap(arr, j + 1, high);
//        return j + 1;
//    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
