package com.geeksforgeeks.array;

/**
 * Input: arr[] = {11, 15, 6, 8, 9, 10}, x = 16
 * Output: true
 * <p>
 * This is same as finding a pair in sorted array but the min and max element are not in first and last position
 * but rotated we need to find it and then apply same logic to find pair
 */
public class PairInSortedRotatedArray {

    public static void main(String[] args) {
        int arr[] = {5,5,6,6,1,1,2,2,3,3,4,4}, x = 8;
        System.out.println("Is Pair Exist " + isPairExist(arr, x));
    }

    public static Boolean isPairExist(int[] rotatedArray, int sample) {
        Boolean isExist = false;
        int min = 0, max = 0;

        // Find Max and min position
        for (int i = 1; i < rotatedArray.length; i++) {
            if (rotatedArray[max] <= rotatedArray[i]) {
                max = i;
            }
        }
        min = (max + 1) % rotatedArray.length;

        while (min != max) {
            if (rotatedArray[min] + rotatedArray[max] == sample) {
                System.out.println("Pair found for sample " + sample + " is : (" + rotatedArray[min] + "," + rotatedArray[max] + ")");
                isExist = true;
                break;
            } else if (rotatedArray[min] + rotatedArray[max] < sample) {
                min = (min + 1) % rotatedArray.length;
            } else {
                max = (max - 1 + rotatedArray.length) % rotatedArray.length;
            }
        }
        return isExist;
    }


}
