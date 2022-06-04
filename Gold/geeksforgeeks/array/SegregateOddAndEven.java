package com.geeksforgeeks.array;

public class SegregateOddAndEven {

    public static void main(String[] args) {
        int [] arr = {12, 34, 45, 9, 8, 90, 3};
        rearrangeInGeeksForGeeksWay(arr);
        ArrayRotation.printArray(arr);
    }

    public static void rearrange(int []arr) {
        int low = 0;
        int high = 0;

        for(int i=0;i<arr.length;i++) {
            if(arr[i] % 2 == 0) {
                QuickSort.swap(arr,low++,high++);
            } else {
                high++;
            }
        }
    }

    public static void rearrangeInGeeksForGeeksWay(int []arr) {
        int low = 0;
        int high = arr.length-1;

        while (low < high) {

            while (low<high && arr[low] % 2 ==0)
                low++;
            while (high>low && arr[high] % 2 == 1)
                high--;

            QuickSort.swap(arr,low,high);
        }
    }
}
