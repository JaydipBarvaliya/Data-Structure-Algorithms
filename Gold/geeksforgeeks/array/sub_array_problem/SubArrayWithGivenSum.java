package com.geeksforgeeks.array.sub_array_problem;

public class SubArrayWithGivenSum {

    public static void main(String[] args) {
        findSubArrayWithGivenSum(new int[]{1, 4, 20, 3, 10, 5}, 33);
        findSubArrayWithGivenSum(new int[]{1, 4, 0, 0, 3, 10, 5}, 4);
        findSubArrayWithGivenSum(new int[]{2, 4}, 1);
    }

    private static void findSubArrayWithGivenSum(int[] arr, int SUM) {
        int start = 0, end = 0, CURR_SUM = 0;
        int counter = 0;
        while (start < arr.length) {
            if (CURR_SUM < SUM) {
                CURR_SUM += arr[end++];
            } else if (SUM != 0 && CURR_SUM == SUM) {
                System.out.println("Found SubArray ");
                printSubArray(arr, start, end - 1);
                break;
            } else {
                CURR_SUM -= arr[start++];
            }
        }
    }

    private static void printSubArray(int[] arr, int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println();
    }
}
