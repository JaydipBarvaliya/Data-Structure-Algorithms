package com.geeksforgeeks.array;

/**
 * {16, 17, 4, 3, 5, 2}, leaders are 17, 5 and 2.
 */
public class LeaderInArray {

    public static void main(String[] arg) {
        printLeaders(new int[]{16, 17, 4, 3, 5, 2});
    }

    public static void printLeaders(int[] arr) {
        int MAX_FROM_RIGHT = arr[arr.length - 1];

        System.out.println(MAX_FROM_RIGHT); // Rightmost element will always be the leader
        for (int i = arr.length - 2; i >= 0; i--) {
            if (MAX_FROM_RIGHT < arr[i]) {
                MAX_FROM_RIGHT = arr[i];
                System.out.println(MAX_FROM_RIGHT);
            }
        }
    }
}
