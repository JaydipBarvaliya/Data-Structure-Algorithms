package com.leetcode.year_2020.DP.zero_one_knapsack;

import com.datastructures.mustDoInterviewQuestions.DP.ZeroOneKnapSack;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-number-of-consecutive-values-you-can-make/
 */
public class MaximumNumberOfConsecutiveValuesYouCanMake {

    public static void main(String[] args) {
        System.out.println(getMaximumConsecutive(new int[]{1, 3}));
        System.out.println(getMaximumConsecutive(new int[]{1, 1, 1, 4}));
        System.out.println(getMaximumConsecutive(new int[]{1, 1, 1, 4}));
    }

    public static int getMaximumConsecutiveInO_N(int[] coins) {
        /**
         * Inspiration https://www.youtube.com/watch?v=7U6eli4jQ74
         * Okay so assume you have number [1,4,10,3,1]
         * in sorted form it's [1,1,3,4,10]
         *
         * Can you make 0 ? yes by choosing nothing
         * Can you make 1 ? yes by choosing 1st index 1
         *
         * [1,1,3,4,10]
         *  0 1 2 3 4
         *
         * Now you reached at index 1, so what all sums you can make from here
         *
         * 1 + 0 == 1, 1 + 1 ==> 2 [So total you can make 3 consecutive numbers(0, 1, 2)] by choosing [1,1]
         *
         * Now when you reached at index 2, value = 3
         * How many numbers you can make now
         *
         * 3+0 == 3, 3+1 == 4, 3 + 2 == 5 [ so total you can make (0, 1, 2 ,3, 4, 5)] by choosing [1,1,3] and so on
         *
         *
         * Now assume where this will break if we have numbers [1,1,4,10]
         *
         * Now with this you can make numbers [0, 1, 2, 4, 5, 6, 10, 11, 12, 14, 15, 16]
         *
         * See we have missing consecutive numbers such as 3 and 7 and so on.
         *
         * So while calculating our reachableSums, you need to check if the next available number is > than reachableSums
         * then in that scenario we can never make consecutive sum including the next available number, In our case 4 is such example
         */

        int MAX_REACHABLE_SUM = 1; // you can make 0 sum by choosing nothing, and we have to return count of sums
        Arrays.sort(coins);
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] > MAX_REACHABLE_SUM) {
                break;
            }
            MAX_REACHABLE_SUM += coins[i];
        }
        return MAX_REACHABLE_SUM;
    }

    public static int getMaximumConsecutive(int[] coins) {
        /**
         ** This is a classic {@link ZeroOneKnapSack} problem
         * we first calculate the total sum of coins array can make (sum(all elements in coins arr)
         * Now we will apply {@link SubSetSumProblem} to find out if it's possible to make the sum
         * by doing so we will have the array of DP which tells us that how many consecutive sums we can make
         **/
        int total = Arrays.stream(coins).sum();
        boolean[][] subsetSumMatrix = subsetSum(coins, total);

        for (int i = 0; i < subsetSumMatrix.length; i++) {
            for (int j = 0; j < subsetSumMatrix[i].length; j++) {
                System.out.print(subsetSumMatrix[i][j] + "\t");
            }
            System.out.println();
        }
        // Now just check the last row, to find out how many consecutive items are possible
        int count = 0;
        for (int col = 0; col < subsetSumMatrix[0].length; col++) {
            if (subsetSumMatrix[subsetSumMatrix.length - 1][col]) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    private static boolean[][] subsetSum(final int[] coins, final int total) {
        boolean[][] dp = new boolean[coins.length + 1][total + 1];

        // Initialization
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {

                // First Row (when we have 0 items, we can't make any sum
                if (i == 0) {
                    dp[i][j] = false;
                }

                // we cAN always make 0 sum, when
                if (j == 0) {
                    dp[i][j] = true;
                }
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (coins[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - coins[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j] && dp[i][j - 1];
                }
            }
        }
        return dp;
    }

}
