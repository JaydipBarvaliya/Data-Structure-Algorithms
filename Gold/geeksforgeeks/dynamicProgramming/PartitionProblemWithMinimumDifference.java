package com.geeksforgeeks.dynamicProgramming;

/**
 * https://www.geeksforgeeks.org/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/
 */
public class PartitionProblemWithMinimumDifference {

    public static void main(String[] args) {
        System.out.println("Minimum Difference is "+getMinimumSum(new int[]{1,5,6}));
    }

    public static int getMinimumSum(int[] arr) {

        int SUM_OF_ELEMENTS_OF_ARR = PartitionProblem.getSum(arr);
        Boolean [][]TRUTHY_VALUE = new Boolean[arr.length + 1][SUM_OF_ELEMENTS_OF_ARR + 1];

        TRUTHY_VALUE[0][0] = Boolean.TRUE; // In order to make sum 0 with given set value as 0 it is possible

        for (int i = 1; i <= SUM_OF_ELEMENTS_OF_ARR; i++) {
            TRUTHY_VALUE[0][i] = Boolean.FALSE;
        }

        for (int i = 1; i <= arr.length; i++) {
            for (int j = 0; j <= SUM_OF_ELEMENTS_OF_ARR; j++) {
                if(arr[i-1] <= j) {
                    TRUTHY_VALUE[i][j] = TRUTHY_VALUE[i-1][j] || TRUTHY_VALUE[i][j - arr[i-1]];
                } else {
                    TRUTHY_VALUE[i][j] = TRUTHY_VALUE[i - 1][j];
                }
            }
        }
        SubsetSumProblem.print2DArray(TRUTHY_VALUE);


        // Initialize difference of two sums.
        int diff = Integer.MAX_VALUE;

        // Find the largest j such that dp[n][j]
        // is true where j loops from sum/2 t0 0
        for (int j = SUM_OF_ELEMENTS_OF_ARR / 2; j >= 0; j--) {
            // Find the
            if (TRUTHY_VALUE[arr.length][j] == true) {
                diff = SUM_OF_ELEMENTS_OF_ARR - 2 * j;
                break;
            }
        }
        return diff;
    }

}
