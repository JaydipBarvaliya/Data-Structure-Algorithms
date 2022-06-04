package com.leetcode.year_2020.DP.zero_one_knapsack;

/**
 * https://leetcode.com/problems/target-sum/
 *
 * @author neeraj on 06/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class TargetSum {

    public static void main(String[] args) {
        System.out.println(findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
        System.out.println(findTargetSumWays(new int[]{1, 1, 2, 3}, 1));
        /**
         * For these corner cases, where input elements contains 0, we have to take important care for such items.
         *
         * Let's assume you have to make Sum [0], how many ways are there if all numbers are > 0, just one Empty Set{}
         * but if input contains 0 as well, then we have 2 choices
         *
         *   Sum [0] | provided input [0] ===> {} or {0} and input has [0]
         *
         *  Now let's assume input has one more [0, 0]
         *  then the choices are
         *  Sum[0] | provided input [0, 0] ====> {}, {0}, {0,0}, {0(the last 0)}
         *
         *  Now you see a pattern here, as soon as we increase 0, the number of ways increments by (2), hence when we
         *  fill in the first column where we have to make the sum[0], there based on input entry
         *  if(nums[i] == 0) then dp[i][j] = 2 * dp[i-1][j]
         *
         */
        System.out.println(findTargetSumWays(new int[]{0,0,0,0,0,0,0,0,1}, 1));
    }


    public static int findTargetSumWays(int[] nums, int S) {
        /**
         * This problem is similar to {@link CountTheNumberOfSubsetWithGivenDifference}
         * just leet-code has phrased it differently.
         *
         * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
         *
         * Find out how many ways to assign symbols to make sum of integers equal to target S.
         *
         * Input: nums is [1, 1, 1, 1, 1], S is 3.
         * Output: 5
         * Explanation:
         *
         * -1+1+1+1+1 = 3
         * +1-1+1+1+1 = 3
         * +1+1-1+1+1 = 3
         * +1+1+1-1+1 = 3
         * +1+1+1+1-1 = 3
         *
         * There are 5 ways to assign symbols to make the sum of nums be target 3.
         *
         * Now if you Notice all it says is S1(Sum Of Set1) - S2(Sum of Set2) should be 3.
         *
         * Since S1 - S2 = difference or S
         *       S1 + S2 = Total_Sum
         *      ---------------------
         *      2S1 = diff(S) + Total_Sum
         *
         *      S1 = (diff(S) + Total_Sum)/2
         */
        if (nums.length == 0) return 0;
        if (nums.length == 1) return (S == nums[0] || S == -nums[0] ? 1 : 0);
        return getCountOfNumberOfSubSetWithGivenDifference(nums, S);
    }

    public static int getCountOfNumberOfSubSetWithGivenDifference(int[] set, int difference) {
        /**
         * This problem is similar to {@link MinimumSubsetDifference}, the only difference
         * is in that problem we wanted to divide the set into 2 subset such that
         * there difference of the sum is minimum
         *
         * |S1 - S2| = Minimum
         * this problem is asking count the subset where
         * |S1 - S2| = Given Difference,
         *
         * So essentially if we have to divide into S1 and S2
         * S1 = Sum(Set1)
         * S2 = Sum(Set2)
         *
         * where |S1 - S2| = difference
         *
         * Now , S1 - S2 = difference   --> Eq 1
         *       S1 + S2 = (Total_SUM)  --> Eq 2
         *    -----------------------------------
         *       2S1 + 0 = difference + Total_SUM
         *    ----------------------------------
         *
         *    So S1 = (difference + Total_SUM) / 2;
         *
         * So our Sub-problem divided into find any Subset whose sum is  (difference + Total_SUM) / 2;
         *
         * For Example : Set[] =  1, 1, 2, 3, difference = 1
         *
         * Total_SUM(Set) = 7
         * So S1 = (1 + 7)/2 = 4.
         *
         * This is now just the {@link CountOfSubsetSumWithAGivenSum} Problem, with
         * int countOfSubSetSum(set, 4).....
         */

        int TOTAL_SUM = 0;
        for (int i : set) {
            TOTAL_SUM += i;
        }
        if (TOTAL_SUM < difference) return 0;
        if ((TOTAL_SUM + difference) % 2 != 0) return 0;
        return countOfSubSetSum(set, (difference + TOTAL_SUM) / 2);
    }

    private static int countOfSubSetSum(int[] set, int total_sum) {
        int t[][] = new int[set.length + 1][total_sum + 1];

        // Initialize the matrix
        // Now the difference here is
        // Since 0 can come in the input [0,0,0,0,1]
        // Now when you have item with value > 0
        // How can you make sum = 0, by just choosing {EMPTY_SET}
        // But in test case we also have 0 in the item
        // So now we have 2 options to make SUM = 0;
        // which is {EMPTY}, {0}. So we can't put 1 in all the colums
        // we have to put 2 * whatver subset we have make till now
        // So 2 * t[i-1][j];

        t[0][0] = 1;
        for (int i = 1; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                if (i == 0) { // No item in the cart, hence we can't make subset
                    t[i][j] = 0;
                }
                if (j == 0) { // When we have to make sum 0, we can always take {} empty set.
                    if (set[i - 1] == 0) {
                        t[i][j] = 2 * t[i - 1][j];
                    } else {
                        t[i][j] = t[i - 1][j];
                    }
                }
            }
        }

        // Now to populate rest of matrix.
        // i represent item in the set
        // j represent the totalSum.
        for (int i = 1; i < t.length; i++) {
            for (int j = 1; j < t[i].length; j++) {

                if (set[i - 1] <= j) { // When item is less than sumToAchieve, so it can contribute.
                    t[i][j] = t[i - 1][j - set[i - 1]] + // Choose to contribute
                            t[i - 1][j]; // Not Choose to contribute.
                } else { // when item > the sum to achieve
                    t[i][j] = t[i - 1][j]; // This will never contribute to make the subsetSum
                }
            }
        }
        return t[t.length - 1][t[0].length - 1];
    }
}
