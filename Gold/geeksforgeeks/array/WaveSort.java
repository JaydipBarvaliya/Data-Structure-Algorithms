package com.geeksforgeeks.array;

public class WaveSort {

    public static void main(String[] args) {
        int arr[] = {10, 5, 6, 3, 2, 20, 100, 80};
//        int  arr[] = {20, 10, 8, 6, 4, 2};
//        int  arr[] = {2, 4, 6, 8, 10, 20};
        sort(arr);
        ArrayRotation.printArray(arr);
    }

    public static void sort(int[] sample) {

        boolean upTrend = false;
        for (int j = 0; j < sample.length - 1; j++) {
            if (!upTrend) {
                if (sample[j] < sample[j + 1]) {
                    int temp = sample[j];
                    sample[j] = sample[j + 1];
                    sample[j + 1] = temp;
                }
                upTrend = !upTrend;
            } else {
                if (sample[j] > sample[j + 1]) {
                    int temp = sample[j];
                    sample[j] = sample[j + 1];
                    sample[j + 1] = temp;
                }
                upTrend = !upTrend;
            }
        }
    }
}
