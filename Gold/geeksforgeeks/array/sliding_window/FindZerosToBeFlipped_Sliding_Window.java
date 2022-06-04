package com.geeksforgeeks.array.sliding_window;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given with an array of 1s and 0s. And you are given with an integer m, which signifies number of flips allowed.
 * <p>
 * find the position of zeros which when flipped will produce maximum continuous series of 1s.
 * <p>
 * e.g.
 * input:
 * arr={1 1 0 1 1 0 0 1 1 1 } m=1
 * output={1 1 1 1 1 0 0 1 1 1} position=2
 * <p>
 * arr={1 1 0 1 1 0 0 1 1 1 } m=2
 * output={1 1 0 1 1 1 1 1 1 1} position=5,6
 */
public class FindZerosToBeFlipped_Sliding_Window {

    public static void main(String[] args) {

        findZerosToBeFlippedToGetMaximumConsecutiveOnes(new int[]{1, 0, 0, 1, 1, 0, 1, 0, 1, 1}, 2);
        findZerosToBeFlippedToGetMaximumConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 0, 0, 1, 1, 1}, 1);

        findZerosToBeFlipped(new int[]{1, 1, 0, 1, 1, 0, 0, 1, 1, 1}, 1);
        findZerosToBeFlipped(new int[]{1, 0, 0, 1, 1, 0, 1, 0, 1, 1}, 2);
    }

    public static void findZerosToBeFlippedToGetMaximumConsecutiveOnes(int[] arr, int maxZerosToBeFlipped) {

        int wL = 0; // Left Side of Window
        int wR = 0; // Right Side of Window
        int nZero = 0; // Current Zeros under window, this should always be less than maxZerosToBeFlipped
        int BEST_WINDOW_WIDTH = -1; // Best window at any point of time
        int best_wL = -1;
        int best_wR = -1;

        while (wR < arr.length) {

            // Expand the Widow to the right, and update the '0' Count
            if (nZero <= maxZerosToBeFlipped) {
                if (arr[wR] == 0) {
                    nZero += 1;
                }
                wR++;
            }

            // Shrink the window from the left and update the '0' Count
            if (nZero > maxZerosToBeFlipped) {
                if (arr[wL] == 0) {
                    nZero -= 1;
                }
                wL++;
            }

            // Let's check out bestWindowWidth
            if (wR - wL > BEST_WINDOW_WIDTH) {
                BEST_WINDOW_WIDTH = wR - wL;
                best_wL = wL;
                best_wR = wR;
            }
        }

        for (int i = best_wL; i < best_wR; i++) {
            if (arr[i] == 0) {
                System.out.print(i + ",");
            }
        }
        System.out.println();
        System.out.println("And the max consecutive ones after flipping is " + BEST_WINDOW_WIDTH);
    }

    public static void findZerosToBeFlipped(int[] arr, int maxAllowedFlips) {
        LogUtil.logIt("New Method for Finding Zeros to be flipped using Sliding window to find maximum consecutive number of 1's for " + LogUtil.getArrayAsString(arr), true);
        int LeftWindow = 0;
        int rightWindow = 0;
        int MAX_CONSECUTIVE_ONES_IN_THIS_WINDOW = 0;
        List<Integer> zerosFlippedDuringThisWindow = new ArrayList<>();
        List<Integer> ZEROS_FLIPPED_DURING_MAX_WINDOW = new ArrayList<>();

        while (rightWindow < arr.length) {

            if (arr[rightWindow] == 1) {
                rightWindow++;
            } else if (arr[rightWindow] == 0) {
                if (maxAllowedFlips > 0) {
                    zerosFlippedDuringThisWindow.add(rightWindow);
                    maxAllowedFlips--;
                    rightWindow++;
                } else {
                    // First Calculate the MAX_CONSECUTIVE_ONES_IN_THIS_WINDOW
                    if (MAX_CONSECUTIVE_ONES_IN_THIS_WINDOW < (rightWindow - LeftWindow)) {
                        MAX_CONSECUTIVE_ONES_IN_THIS_WINDOW = (rightWindow - LeftWindow);
                        ZEROS_FLIPPED_DURING_MAX_WINDOW.clear();
                        ZEROS_FLIPPED_DURING_MAX_WINDOW.addAll(zerosFlippedDuringThisWindow);
                    }
                    while (arr[LeftWindow] != 0 && LeftWindow < rightWindow) { // Traverse upto the point where you encounter 0;
                        LeftWindow++;
                    }
                    // Looks like we reached the point where LeftWindow is on 0
                    if (arr[LeftWindow] == 0) {
                        zerosFlippedDuringThisWindow.remove((Object) LeftWindow);
                        maxAllowedFlips++; // Increment the allowedFlips.
                        LeftWindow++;
                    }
                }
            }
        }

        // It is possible that we found max in the last pass, so it's safe to check it once
        if (MAX_CONSECUTIVE_ONES_IN_THIS_WINDOW < (rightWindow - LeftWindow)) {
            MAX_CONSECUTIVE_ONES_IN_THIS_WINDOW = (rightWindow - LeftWindow);
            ZEROS_FLIPPED_DURING_MAX_WINDOW.clear();
            ZEROS_FLIPPED_DURING_MAX_WINDOW.addAll(zerosFlippedDuringThisWindow);
        }

        System.out.println("MAX_CONSECUTIVE_ONES_IN_THIS_WINDOW is " + MAX_CONSECUTIVE_ONES_IN_THIS_WINDOW);
        System.out.println("And the position from where zeros are flipped is " + ZEROS_FLIPPED_DURING_MAX_WINDOW);
    }
}
