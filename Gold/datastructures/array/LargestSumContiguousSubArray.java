package com.datastructures.array;

/**
 * Created by jaine03 on 02/05/17.
 * <p>
 * http://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
 */
public class LargestSumContiguousSubArray {

    public static void main(String[] args) {
        int[] sample = new int[]{-2, -3, 4, -1, -2, 1, 5, -3};

//        System.out.println(getMaxSumWithDP(sample));
        sample = new int[]{1, -8, 3, -4};
        System.out.println(getMaxSum(sample));

        sample = new int[]{-3, -4, -1, -2};
        System.out.println(getMaxSum(sample));

        sample = new int[]{-5, 3, 4, 9};
        System.out.println(getMaxSum(sample));

    }

    public static int getMaxSum(int[] sample) {
        int maxTillNow = Integer.MIN_VALUE;
        int maxEndingHere = 0;
        int tempStart = 0, end = 0, start = 0;

        for (int i = 0; i < sample.length; i++) {
            maxEndingHere += sample[i];

            if (maxTillNow < maxEndingHere) {
                maxTillNow = maxEndingHere;
                end = i;
                start = tempStart;
            }
            if (maxEndingHere < 0) {
                maxEndingHere = 0;
                tempStart = i + 1;
            }
        }
        System.out.println("Starts at " + start);
        System.out.println("Ends at " + end);

        return maxTillNow;
    }

    public static int getMaxSumWithDP(int[] sample) {
        int maxEndingHere = sample[0];
        int maxTillNow = sample[0];

        for (int i = 0; i < sample.length; i++) {
            maxEndingHere = Math.max(sample[i], maxEndingHere + sample[i]);
            maxTillNow = Math.max(maxEndingHere, maxTillNow);

            System.out.println("Max Ending Here is " + maxEndingHere);
            System.out.println("Max Till Now  Here is " + maxTillNow);
        }
        return maxTillNow;
    }
}
