package com.company.facebook;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AboveAverageSubarrays {

    public static void main(String[] args) {
        final List<int[]> result = aboveAverageSubarrays(new int[]{3, 4, 2});
        for (int[] res : result) {
            LogUtil.printArray(res);
        }
    }

    static final List<int[]> aboveAverageSubarrays(int[] A) {
        // Calculate RightSum
        int totalSum = Arrays.stream(A).sum();
        final List<int[]> result = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int sum = 0;
            for (int j = i; j < A.length; j++) {
                sum += A[j];
                float averageOfUsed = (float) sum / (j - i + 1);
                float averageOfRemaining = (j - i + 1) == A.length ? Integer.MIN_VALUE : // This will only happen when entire array is being used.
                        (totalSum - sum) / (A.length - (j - i + 1));
                if (averageOfUsed > averageOfRemaining) {
                    result.add(new int[]{i, j});
                }
            }
        }
        return result;
    }

    private static int getSumOfSubArray(final int[] a, int i, final int j) {
        int sum = 0;
        while (i <= j) {
            sum += a[i];
            i++;
        }
        return sum;
    }
}
