package com.career_cup;

/**
 * Given an array of sorted integers and find the closest value to the given number. Array may contain duplicate values and negative numbers.
 * <p>
 * Example : Array : 2,5,6,7,8,8,9
 * Target number : 5
 * Output : 5
 * <p>
 * Target number : 11
 * Output : 9
 * <p>
 * Target Number : 4
 * Output : 5
 */
public class ClosestValueInAnArray {

    public static void main(String[] args) {
        System.out.println("Closest Element to the target "+5+" is ::" + findClosestValueToTheTargetValue(new int[]{2, 5, 6, 7, 8, 8, 9}, 5));
        System.out.println("Closest Element to the target "+4+" is ::" + findClosestValueToTheTargetValue(new int[]{2, 5, 6, 7, 8, 8, 9}, 4));
    }

    public static int findClosestValueToTheTargetValue(int[] arr, int target) {
        int closestDifference = Integer.MAX_VALUE;
        int closestElement = 0;

        for (int i : arr) {
            if (Math.abs(target - i) < closestDifference) {
                closestDifference = Math.abs(target - i);
                closestElement = i;
            }
        }
        return closestElement;
    }
}
