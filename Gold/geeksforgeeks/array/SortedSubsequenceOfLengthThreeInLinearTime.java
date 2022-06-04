package com.geeksforgeeks.array;

public class SortedSubsequenceOfLengthThreeInLinearTime {

    public static void main(String[] args) {
        System.out.println(sequenceAvailable(new int[]{12, 11, 10, 5, 6, 2, 30}));
        System.out.println(sequenceAvailable(new int[]{1, 2, 3, 4}));
        System.out.println(sequenceAvailable(new int[]{4, 3, 2, 1}));
    }

    public static boolean sequenceAvailable(int[] arr) {
        int lowest = Integer.MAX_VALUE;
        int secondLowest = Integer.MAX_VALUE;
        int LOWEST_VAL_WHEN_SECOND_LOW_CHANGES = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < lowest) {
                lowest = arr[i];
            } else if (arr[i] < secondLowest) {
                LOWEST_VAL_WHEN_SECOND_LOW_CHANGES = lowest; // because lowest var can change later and second might not change
                secondLowest = arr[i];
            } else { // We find our last element which is greater than both
                System.out.print("{" + LOWEST_VAL_WHEN_SECOND_LOW_CHANGES + "," + secondLowest + "," + arr[i] + "} \t");
                return true;
            }
        }
        return false;
    }
}
