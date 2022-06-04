package com.leetcode.year_2020.DP.zero_one_knapsack;

/**
 * Also known as
 * Partition a set into two subsets such that the difference of subset sums is minimum
 * <p>
 * Input:  arr[] = {1, 6, 11, 5}
 * Output: 1
 * Explanation:
 * Subset1 = {1, 5, 6}, sum of Subset1 = 12
 * Subset2 = {11}, sum of Subset2 = 11
 *
 * @author neeraj on 05/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("DuplicatedCode")
public class MinimumSubsetDifference {
    public static void main(String[] args) {
        System.out.println(minimumSubsetDifference(new int[]{1, 2, 7}));
        System.out.println(minimumSubsetDifference(new int[]{1, 6, 11, 5}));
        System.out.println(minimumSubsetDifference(new int[]{1, 5, 11, 5}));
        System.out.println(minimumSubsetDifference(new int[]{1, 5, 3}));
        System.out.println(minimumSubsetDifference(new int[]{4, 100, 1, 23, 20}));
    }

    public static int minimumSubsetDifference(int[] set) {
        /**
         * We have to divide the set into 2 such parts such that the difference of the sum of those 2 sub-set is minimum.
         * Let's say S1 represent sum(set1)
         * and S2 represent sum(set2)
         *
         * |S1 - S2| = Minimum
         * OR
         * |S2 - S1| = Minimum
         *
         * But we don't know how to choose S1 and S2.... the only possible solution is to try and error, but we can do one thing
         * to optimize, we can try to find only S1 and that to with just half of the SUM(ARRAY). WHY ?
         *
         * Reason:
         *
         * We know that S1 + S2 == SUM(Set).
         * S2 = SUM(SET) - S1; <===============================
         *                                                   ||
         *  AIM : |S2 - S1| == Minimum                       ||
         *        | SUM(SET) - S1 - S1| = Minimum. (Replace S2 from)
         *
         *  Hence we have to minimize = SUM(SET) - 2S1.
         *  Now problem came down to find just S1... with bunch of values, so it's kind of similar to SubSet Sum problem
         *  with bunch of possible values.
         *
         *  Now what are those possible values, we know that the extreme values of S1 can be, either { EMPTY_SET } So sum = 0;
         *  or { ALL ITEMS FROM SET} So sum = SUM(SET).... Now our values lies between 0 to SUM(SET).
         *
         *  0---------------------------------------SUM(SET)
         *
         *  For Example [1 2 7]  ==> Minimum Difference is 4.  |Sum([1,2]) - Sum([7])| (Absolute Diff)
         *
         *  0 - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 (Range) ..
         *
         *  Now if i choose S1 anything ... S2 will be (Range - S1)
         *  If i choose S1 = 1 so S2 = 10 -1 = 9
         *              S1 = 3 so S2 = 10 - 3 = 7.... and so on...
         *
         *  Now the max i can choose for S1 is SUM(SET)/2; because we intentionally assume s1 to be smaller than s2.
         *
         * Now we have the ground knowledge, so let's solve it.
         */
        int SUM = 0;
        for (int i = 0; i < set.length; i++) {
            SUM += set[i];
        }

        int MIN_DIFFERENCE = Integer.MAX_VALUE;
        // Now we have to try subset sum for 1 to SUM/2;

        // Now instead of trying for all the sum 1 by 1, if i calculate the subSet for SUM(SET)...
        // i.e if i calculate the isSubSetPresent(set, sum of array)
        // then if we were using TopDown approach our matrix already stored all values
        /**
         * So For [1, 2, 7] and SUM 10 == SUM(ARR).
         *
         * If i calculate SubSet sum using Bottom Up Approach, we will get something like this at the end:
         *
         *          ==================== (SUM) ===================================>
         *        0       1        2      3       4        5        6       7      8        9     10        ||
         * 0     true	false	false	false	false	false	false	false	false	false	false       ||
         * 1     true	true	false	false	false	false	false	false	false	false	false      (SET)
         * 2     true	true	true	true	false	false	false	false	false	false	false       ||
         * 7     true	true	true	true	false	false	false	true	true	true	true        \/
         *
         * Now if you look at the last row, what does it represent, it represent that if
         * all items are there in set can i make the sum ... 1,2,3,4 .... ? and so on.
         *
         * So we have to just calculate isSubSetSum once and we are done.
         *
         *
         */
        boolean dp[][] = getSubSetSUMCache(set, SUM);

        int sumOfOneSet_S1 = 0;
        for (int S1 = 1; S1 <= SUM / 2; S1++) {
            if (dp[dp.length - 1][S1]) { // If an only if a subset is present for the following sum
                // we can include it for Min difference calculation
                if (MIN_DIFFERENCE > SUM - (2 * S1)) {
                    sumOfOneSet_S1 = S1;
                }
                MIN_DIFFERENCE = Math.min(MIN_DIFFERENCE, SUM - (2 * S1));

            }
        }

        // Go through all the rows.
        System.out.println("Sum of 1 set is " + sumOfOneSet_S1 + " and another set is " + (SUM - sumOfOneSet_S1));
        return MIN_DIFFERENCE;
    }

    public static boolean[][] getSubSetSUMCache(int[] set, int sum) {
        boolean dp[][] = new boolean[set.length + 1][sum + 1];

        // Initialize
        // So in Bottom up we have to do the initialization of the matrix.
        // As per Recursive. if(n < 0 || sum < 0) return false;

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i == 0) {
                    dp[i][j] = false;
                }
                if (j == 0) {
                    dp[i][j] = true;
                }
            }
        }

        // Now fill the rest of matrix.
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (set[i - 1] <= j) { // Item is <= sum
                    dp[i][j] = dp[i - 1][j - set[i - 1]] || dp[i - 1][j];
                } else { // Item is > sum, so definitely not choosing it.
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp;
    }
}
