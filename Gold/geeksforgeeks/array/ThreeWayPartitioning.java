package com.geeksforgeeks.array;

public class ThreeWayPartitioning {

    public static void main(String[] args) {
        int arr[] = {1, 14, 5, 20, 4, 2, 54, 20, 87, 98, 3, 1, 32};
        rearrangeArray(arr, 10, 20);

        rearrangeArrayUsingDutchFlagAlgo(arr, 10, 20);
    }

    public static void rearrangeArray(int[] arr, int lowVal, int highVal) {
        int[] temp = new int[arr.length];
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < lowVal) {
                temp[counter++] = arr[i];
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= lowVal && arr[i] <= highVal) {
                temp[counter++] = arr[i];
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > highVal) {
                temp[counter++] = arr[i];
            }
        }
        ArrayRotation.printArray(temp);
    }

    public static void rearrangeArrayUsingDutchFlagAlgo(int[] arr, int lowVal, int highVal) {

        int start = 0;
        int end = arr.length - 1;

        for(int i=0;i<end;) {

            if(arr[i] < lowVal) {
                QuickSort.swap(arr,i,start);
                start++;
                i++;
            }
            else if(arr[i] > highVal) {
                QuickSort.swap(arr,i,end);
                end--;
            } else {
                i++;
            }
        }

        ArrayRotation.printArray(arr);
    }
}
