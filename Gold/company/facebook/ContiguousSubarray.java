package com.company.facebook;

import com.util.LogUtil;

import java.util.Arrays;

public class ContiguousSubarray {

    public static void main(String[] args) {
        LogUtil.printArray(countSubarrays(new int[]{2, 4, 7, 1, 5, 3}));
        LogUtil.printArray(countSubarrays(new int[]{3, 4, 1, 6, 2}));
    }

    static int[] countSubarrays(int[] arr) {
        // Now sweep in left and sweep in right for every item
        int[] result = new int[arr.length];
        Arrays.fill(result, 1); // atleast 1 length long
        for (int i = 0; i < arr.length; i++) {

            // Left Sweep
            int left = i - 1;
            while (left >= 0 && arr[left] <= arr[i]) {
                result[i]++;
                left--;
            }

            // Right Sweep
            int right = i + 1;
            while (right <= arr.length - 1 && arr[right] <= arr[i]) {
                result[i]++;
                right++;
            }
        }
        return result;
    }
}
