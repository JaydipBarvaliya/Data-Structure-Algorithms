package com.geeksforgeeks.array.sliding_window;

import com.util.LogUtil;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Given a binary array, find the maximum number zeros in an array with one flip of a subarray allowed.
 * A flip operation switches all 0s to 1s and 1s to 0s.
 * <p>
 * Input :  arr[] = {0, 1, 0, 0, 1, 1, 0}
 * Output : 6
 * We can get 6 zeros by flipping the subarray {1, 1}
 * <p>
 * Input :  arr[] = {0, 0, 0, 1, 0, 1}
 * Output : 5
 */
public class MaximumNoOfZerosByFlippingOne {

    public static void main(String[] args) {
        findMaximumNumOfZerosPossibleByFlippingOne(new Integer[]{0, 1, 0, 0, 1, 1, 0});
        findMaximumNumOfZerosPossibleByFlippingOne(new Integer[]{0, 0, 0, 1, 0, 1});
        findMaximumNumOfZerosPossibleByFlippingOne(new Integer[]{1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1});

        getMaximumZeros(new Integer[]{0, 1, 0, 0, 1, 1, 0});
        getMaximumZeros(new Integer[]{0, 0, 0, 1, 0, 1});
        getMaximumZeros(new Integer[]{1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1});

        getMaxConsecutiveZerosByOneFlip(new int[]{0, 1, 0, 0, 1, 1, 0});
        getMaxConsecutiveZerosByOneFlip(new int[]{0, 0, 0, 1, 0, 1});
        getMaxConsecutiveZerosByOneFlip(new int[]{1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1});

        solveViaKadanes(new int[]{0, 1, 0, 0, 1, 1, 0});
        solveViaKadanes(new int[]{0, 0, 0, 1, 0, 1});
        solveViaKadanes(new int[]{1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1});
    }

    public static int solveViaKadanes(int[] arr) {
        /**
         * We'll keep a tab on no of zeros while traversing
         * 1. consider 0 as -1 and 1 as 1, calculate max sum
         * 2. NoOfZeros + MaxTillNow == MaxNumberOfZeros
         */
        int MEH = 0;
        int MTN = Integer.MIN_VALUE;
        int NO_OF_ZEROS = 0;
        for (int i : arr) {
            if (i == 0) {
                NO_OF_ZEROS++;
                i = -1;
            }
            MEH += i;
            MTN = Math.max(MTN, MEH);
            if (MEH < 0) MEH = 0;
        }
        System.out.println("Maximum Zeros By Flipping via Kadanes :: " + (NO_OF_ZEROS + MTN));
        return NO_OF_ZEROS - MTN;
    }

    public static int getMaxConsecutiveZerosByOneFlip(final int[] arr) {
        int low = 0, high = 0, flip = 1;
        int maxZeros = Integer.MIN_VALUE;
        while (high < arr.length) {
            if (arr[high] == 1) {
                flip--;
                while (high + 1 < arr.length && arr[high + 1] == 1) {
                    high++;
                }
            }
            while (flip < 0) {
                if (arr[low] == 1) {
                    flip++;
                    while (low + 1 < arr.length && arr[low + 1] == 1) {
                        low++;
                    }
                }
                low++;
            }
            maxZeros = Math.max(maxZeros, high - low + 1);
            high++;
        }
        System.out.println("Maximum continuous Zeros By Flipping via Sliding Window :: " + maxZeros);
        return maxZeros;
    }

    public static void findMaximumNumOfZerosPossibleByFlippingOne(Integer[] arr) {
        // Get the frequency
        Map<Integer, Long> freqMap = Stream.of(arr)
                .collect(Collectors.groupingBy(o -> o, Collectors.counting()));

        getMaximumZeros(arr, freqMap);
    }

    public static long getMaximumZeros(Integer[] arr, Map<Integer, Long> freqMap) {
        long MAX_NUM_ZEROS = freqMap.get(0);
        int wL = 0, wR = 0;
        int NUM_ZEROS_IN_CURRENT_SUB_ARR = 0;
        int start = 0, end = 0;

        while (wR < arr.length) {
            if (arr[wR] == 0) {
                wR++;
                wL = wR;
            } else {
                NUM_ZEROS_IN_CURRENT_SUB_ARR = 0;
                while (wR < arr.length && arr[wR] != 0) {
                    NUM_ZEROS_IN_CURRENT_SUB_ARR++;
                    wR++;
                }
                if (MAX_NUM_ZEROS < freqMap.get(0) + NUM_ZEROS_IN_CURRENT_SUB_ARR) {
                    MAX_NUM_ZEROS = freqMap.get(0) + NUM_ZEROS_IN_CURRENT_SUB_ARR;
                    start = wL;
                    end = wR - 1;
                }
            }
        }
        System.out.println("Maximum Zeros By Flipping :: " + MAX_NUM_ZEROS + " which start and ends at [" + start + "," + end + "]");
        return MAX_NUM_ZEROS;
    }

    public static long getMaximumZeros(Integer[] arr) {

        LogUtil.logIt("Getting Maximum Number of Zeros By Flipping Once ", true);
        long TOTAL_ZEROS_IN_ARR = Arrays.asList(arr).stream().filter(item -> item == 0).count();
        int left = 0;
        int right = 0;
        int ZEROS_IN_WINDOW = arr[right] == 0 ? 1 : 0;
        int ONES_IN_WINDOW = arr[right] == 1 ? 1 : 0;
        int countOfZerosWithThisFlip = 0;
        int MAX_NUM_ZEROS = 0;
        int start = 0;
        int end = 0;

        while (right < arr.length) {

            if (left > right) {
                right = left;
                // Resetting the zeros and ones for this window.
                ZEROS_IN_WINDOW = arr[left] == 0 ? 1 : 0;
                ONES_IN_WINDOW = ZEROS_IN_WINDOW > 0 ? 0 : 1;
            } else {
                countOfZerosWithThisFlip = getCountOfZerosAfterThisFlip((int) TOTAL_ZEROS_IN_ARR, ZEROS_IN_WINDOW, ONES_IN_WINDOW);
                if (TOTAL_ZEROS_IN_ARR <= countOfZerosWithThisFlip) {
                    if (MAX_NUM_ZEROS < countOfZerosWithThisFlip) {
                        MAX_NUM_ZEROS = countOfZerosWithThisFlip;
                        start = left;
                        end = right;
                    }
                    right++;
                    if (right < arr.length) {
                        ZEROS_IN_WINDOW += arr[right] == 0 ? 1 : 0;
                        ONES_IN_WINDOW += arr[right] == 1 ? 1 : 0;
                    }
                } else {
                    if (arr[left] == 1) {
                        --ONES_IN_WINDOW;
                    } else {
                        --ZEROS_IN_WINDOW;
                    }
                    left++;
                }
            }
        }
        System.out.println("Maximum Zeros By Flipping :: " + MAX_NUM_ZEROS + " which start and ends at [" + start + "," + end + "]");
        return MAX_NUM_ZEROS;
    }

    private static int getCountOfZerosAfterThisFlip(int TOTAL_ZEROS_IN_ARR, int ZEROS_IN_WINDOW, int ONES_IN_WINDOW) {
        return TOTAL_ZEROS_IN_ARR + ONES_IN_WINDOW - ZEROS_IN_WINDOW;
    }
}
