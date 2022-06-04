package com.geeksforgeeks.dynamicProgramming;

import com.geeksforgeeks.array.Rotate2DMatrix;

public class BuySellStockWithKTransactions {

//    public static void main(String[] args) {
//        getMaximumProfit(new int[]{2, 5, 7, 1, 4, 3, 1, 3}, 3);
//    }


    public static void main(String[] args) {
//        int prices[]={1, 3, 2, 8, 4, 9};
        int prices[] = new int[]{1, 3, 7, 5, 10, 3};
        int fee = 3;
        System.out.println(maxSell(prices, fee));
    }

    public static int maxSell(int[] A, int fee) {
        int buy = -1 * A[0];
        int sell = 0;
        for (int i = 1; i < A.length; i++) {
            buy = Math.max(buy, sell - A[i]);
            sell = Math.max(sell, buy + A[i] - fee);
        }
        return sell;
    }


    public static int getMaximumProfit(int[] dailyProfits, int totalNoOfTransactions) {
        int[][] MAX_PROFIT = new int[totalNoOfTransactions + 1][dailyProfits.length];
        int maxDiff;
        // Row 1 and Column 1 will be zero because with 0 transaction you cannot get any profit
        // If only 1 day is available you can not gain any profit bcozz buying and selling has to be done on different days

        for (int i = 0; i < dailyProfits.length; i++) {
            MAX_PROFIT[0][i] = 0;

            if (i <= totalNoOfTransactions) {
                MAX_PROFIT[i][0] = 0;
            }
        }

        // Now let's do the actual Calculation, with maxDiff logic

        for (int i = 1; i <= totalNoOfTransactions; i++) {

            // Initially maxDiff when i=1 and j = 0;
            maxDiff = getMaxDiff(Integer.MIN_VALUE, i, 0, MAX_PROFIT, dailyProfits);
            for (int j = 1; j < dailyProfits.length; j++) {

                MAX_PROFIT[i][j] = Math.max(MAX_PROFIT[i][j - 1], dailyProfits[j] + maxDiff); //Maximum of  Not doing any transaction on that day or doing the transaction on that day

                maxDiff = getMaxDiff(maxDiff, i, j, MAX_PROFIT, dailyProfits);
            }
        }
        Rotate2DMatrix.print2DArray(MAX_PROFIT);
        return 0;
    }

    private static int getMaxDiff(int maxDiff, int i, int j, int[][] MAX_PROFIT, int[] dailyProfits) {
        return Math.max(maxDiff, MAX_PROFIT[i - 1][j] - dailyProfits[j]);
    }
}
