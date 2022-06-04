package com.geeksforgeeks.array;

public class SegregateZeroAndOnes {

    public static void main(String[] args) {
        int [] arr = {0, 1, 0, 1, 0, 0, 1, 1, 1, 0};
        rearrange(arr);
        ArrayRotation.printArray(arr);
    }

    public static void rearrange(int []arr) {
        int low = 0;
        int high = 0;

        for(int i=0;i<arr.length;i++) {
            if(arr[i] == 0) {
                QuickSort.swap(arr,low++,high++);
            } else {
                high++;
            }
        }
    }
}
