package com.geeksforgeeks.array;

import static java.lang.Math.abs;

/**
 * Find duplicates in O(n) time and O(1) extra space | Set 1
 */
public class FindDuplicateInO_N_Time_O_1_Space {

    public static void main(String[] args) {
        findDuplicates(new int[]{1, 2, 3, 1, 3, 6, 6});
    }

    public static void findDuplicates(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[abs(arr[i])] >= 0) {
                arr[abs(arr[i])] = -arr[abs(arr[i])];
            } else {
                System.out.print(abs(arr[i]) + ",");
            }
        }
    }

}
