package com.leetcode.year_2020.DP.zero_one_knapsack;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author neeraj on 03/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class  ZeroOneKnapSackRecursively {

    public static void main(String[] args) {
        System.out.println(getMaxProfit(
                new int[]{1, 3, 4, 5},
                new int[]{1, 4, 5, 7},
                7
        ));

        System.out.println(getMaxProfit(
                new int[]{1, 2, 3, 5},
                new int[]{30, 70, 50, 60},
                5
        ));
    }

    static int MAX_PROFIT = Integer.MIN_VALUE;

    static int t[][]; // Memorization matrix.

    public static int getMaxProfit(int[] weight, int[] val, int capacity) {
//        MAX_PROFIT = Integer.MIN_VALUE;
//        getMaxProfit(weight, val, 0, new ArrayList<>(), 0, capacity);
//        return MAX_PROFIT;

        // Recursive Approach.
        t = new int[weight.length + 1][capacity + 1]; // Memorization matrix
        /**
         * So we are making memorization matrix with Rows as the weights
         * and capacity as the columns
         *  ------CAPACITY----->
         *  |
         *  |
         *  Weights.
         *  |
         *  |
         * \/
         */
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }
//        return getMaxProfitRecursively(weight, val, weight.length - 1, capacity);
        return getMaxProfitRecursivelyBottomUp(weight, val);
    }

    private static void getMaxProfit(int[] weight, int[] val, int index,
                                     ArrayList<Integer> includedWeights, int currentWeight,
                                     int capacity) {
        if (includedWeights.size() != 0) {
            int profitSum = 0;
            for (int i : includedWeights) {
                profitSum += val[i];
            }
            MAX_PROFIT = Math.max(MAX_PROFIT, profitSum);
        }

        for (int i = index; i < weight.length; i++) {
            if (currentWeight + weight[i] <= capacity) {
                includedWeights.add(i);
                getMaxProfit(weight, val, i + 1, includedWeights, currentWeight + weight[i], capacity);
                includedWeights.remove(includedWeights.size() - 1);
            }
        }
    }


    public static int getMaxProfitRecursively(int[] weight, int[] value, int n, int capacity) {

        // Base Condition.
        if (n < 0 || capacity == 0) return 0;

        // If result is pre-calculated, let's use it.
        if (t[n][capacity] > -1) return t[n][capacity];

        // Choice.
        if (weight[n] <= capacity) {
            // Now we can either choose this or don't and we have to check the maximum.
            return t[n][capacity] = Math.max(
                    // Choose the weight at index = n; hence capacity is reduced.
                    (value[n] + getMaxProfitRecursively(weight, value, n - 1, capacity - weight[n])),
                    // Don't choose the weight at index = n, hence capacity is untouched.
                    getMaxProfitRecursively(weight, value, n - 1, capacity));
        } else { // Since weight of item is > capacity so there is no chance we can choose it.
            return t[n][capacity] = getMaxProfitRecursively(weight, value, n - 1, capacity);
        }
    }

    /**
     * Kuch log ise DP smjhte hai real DP ====> Recursion hai mere bhai.
     * Recursion sabka baap hai. ===> DP to bas Recursion + Memorization hai.
     * <p>
     * In Bottom Up approach we will remove the recursive call and use Memorization matrix, to answer result
     * of specific subproblem
     */
    public static int getMaxProfitRecursivelyBottomUp(int[] weight, int[] value) {

        // Base Condition will be converted to Initialization matrix.
        /**
         * Base Condition from Recursive Solution
         * // Base Condition.
         * if (n < 0 || capacity == 0) return 0;, Same here.
         * So our 1st Row in T represents, we have 0 item(n)
         * and our 1st Col in T represents, we have 0 capacity.
         *
         * So Initialize the table respectively.
         *
         * Also i and j is nothing but n and capacity respectively from recursive solution.
         *
         * So t[n][capacity] = t[i][j];
         */
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[0].length; j++) {
                if (i == 0 || j == 0) {
                    t[i][j] = 0;
                    continue;
                }
            }
        }

        // Now for Rest of the Matrix we will populate the result for the sub-problem
        for (int i = 1; i < t.length; i++) {
            for (int j = 1; j < t[i].length; j++) {
                if (weight[i - 1] <= j) {
                    // Now we have choice, either we can choose this or just leave it, based on whatever is maximum
                    // This is similar to recusrive solution
                    /**
                     * Math.max(value[n] + getMaxProfit(wt, val, n-1 ,capacity - weight[n])
                     * Same
                     * Math.max(value[i-1] + t[i-1][j - weight[i]). i == n, j == capacity.
                     */
                    t[i][j] = Math.max(
                            (value[i - 1] + t[i - 1][j - weight[i - 1]]), // Choose
                            t[i - 1][j] // Don't choose
                    );
                } else { // Weight is greater so we will not choose the item.
                    t[i][j] = t[i - 1][j];
                }
            }
        }
        return t[t.length - 1][t[0].length - 1];
    }
}
