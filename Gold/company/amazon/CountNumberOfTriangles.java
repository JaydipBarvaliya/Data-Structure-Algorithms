package com.company.amazon;

import java.util.Arrays;

public class CountNumberOfTriangles {

    public static void main(String[] args) {
        System.out.println(getNumberOfTriangles(new int[]{4, 6, 3, 7}));
        System.out.println(getNumberOfTriangles(new int[]{10, 21, 22, 100, 101, 200, 300}));
    }

    public static int getNumberOfTriangles(int[] arr) {
        Arrays.sort(arr);
        int count = 0;

        for (int i = 0; i < arr.length - 2; i++) {

            int k = i + 2;
            for (int j = i + 1; j < arr.length - 1; j++) {

                // We are finding the rightmost element in this sorted array
                // which is smaller than the sum of these 2 elements
                while (k < arr.length && (arr[i] + arr[j] > arr[k])) {
                    ++k;
                }
                // reducing 1 from k because k already reached to a state where sum of 2 elements is not greater than k from while loop
                count += k - j - 1;
            }
        }
        return count;
    }
}
