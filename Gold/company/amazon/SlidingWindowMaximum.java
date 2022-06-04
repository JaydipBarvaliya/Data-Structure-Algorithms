package com.company.amazon;

/**
 * Problem 180 : Amazon Interview Questions
 * <p>
 * Sliding Window Maximum (Maximum of all subarrays of size k)
 * https://www.geeksforgeeks.org/sliding-window-maximum-maximum-of-all-subarrays-of-size-k/
 * <p>
 * Input :
 * arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6}
 * k = 3
 * Output :
 * 3 3 4 5 5 5 6
 */
public class SlidingWindowMaximum {

    public static void main(String[] args) {
        printMaximumOfAllWindowsOfSizeK(new int[]{1, 2, 3, 1, 4, 5, 2, 3, 6, 10}, 3);
        printMaximumOfAllWindowsOfSizeK(new int[]{8, 5, 10, 7, 9, 4, 15, 12, 90, 13}, 4);
    }

    public static void printMaximumOfAllWindowsOfSizeK(int[] arr, int k) {
        int wL = 0, wR = 0, windowSize = 1, maximumOfWindow = 0;

        while (wR < arr.length) {
            if (windowSize > k) {
                System.out.print(maximumOfWindow + ",");
                wL++;
                if (arr[wR] > maximumOfWindow) {
                    maximumOfWindow = arr[wR];
                }
                windowSize--;
            } else {
                if (arr[wR] > maximumOfWindow) {
                    maximumOfWindow = arr[wR];
                }
                wR++;
                if (wR >= arr.length) { // When we reached at last element
                    System.out.println(maximumOfWindow);
                }
                windowSize++;
            }
        }
    }
}
