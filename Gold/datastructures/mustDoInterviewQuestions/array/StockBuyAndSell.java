package com.datastructures.mustDoInterviewQuestions.array;

/**
 * Created by jaine03 on 23/07/17.
 */
public class StockBuyAndSell {

    public static void main(String[] args) {
//        getMaxProfit(new int[]{100, 180, 260, 310, 40, 535, 695});
//        getMaxProfit(new int[]{50, 100, 90, 500});
//        getMaxProfit(new int[]{1000, 10, 5000});
        getMaxProfit(new int[]{1, 1, 1000, 1, 1, 100000});
        getMaxProfit(new int[]{4, 3, 8, 10});
        getMaxProfit(new int[]{1, 3, 2, 8, 4, 9});
        getMaxProfit(new int[]{1, 2, 3, 4, 5});
        getMaxProfit(new int[]{7, 1, 5, 3, 6, 4});

        getMaxProfit(new int[]{1, 3, 2, 8, 4, 9});
//        getMaxProfitWithTransactionFees(new int[]{1, 3, 2, 8, 4, 9}, 2);

        getMaxProfit(new int[]{1, 3, 7, 5, 10, 3});
        getMaxProfitWithTransactionFees(new int[]{1, 3, 7, 5, 10, 3}, 3);
    }

    public static void getMaxProfitWithTransactionFees(int[] prices, int transactionFees) {
        boolean isPurchased = false;
        int TOTAL_PROFIT = 0;
        int purchasedAt = 0;

        for (int i = 0; i < prices.length - 1; i++) {
            if (!isPurchased && prices[i] < prices[i + 1]) {
                isPurchased = true; // Purchased 1 share and ready to sell it.
                purchasedAt = prices[i];
            }

            if (isPurchased && prices[i] > prices[i + 1] && (prices[i] - purchasedAt - transactionFees) > 0) {
                isPurchased = false; // Sold that 1 share and now ready to purchase new.
                TOTAL_PROFIT += prices[i] - purchasedAt - transactionFees;
                purchasedAt = Integer.MAX_VALUE;
            }
        }

        if (isPurchased && purchasedAt != Integer.MAX_VALUE && (prices[prices.length - 1] - purchasedAt - transactionFees) > 0) {
            TOTAL_PROFIT += prices[prices.length - 1] - purchasedAt - transactionFees;
        }
        System.out.println("TOTAL PROFIT with Transaction Fees is " + TOTAL_PROFIT);
    }

    public static void getMaxProfit(int[] stockRates) {
        System.out.println("===================NEW TEST CASE===============");
        Boolean isPurchased = false;
        int purchasedStockIndex = -1;
        int TOTAL_PROFIT = 0;
        for (int i = 0; i < stockRates.length - 1; i++) {

            // Check if any Stock already purchased
            if (!isPurchased && stockRates[i] < stockRates[i + 1]) {
                isPurchased = true;
                purchasedStockIndex = i;
            }

            if (isPurchased && stockRates[i] > stockRates[i + 1]) {
                System.out.println("Purchased --> " + purchasedStockIndex + " Sold -->" + i);
                TOTAL_PROFIT += stockRates[i] - stockRates[purchasedStockIndex];
                isPurchased = false;
            }
        }
        if (isPurchased) {
            System.out.println("Purchased --> " + purchasedStockIndex + " Sold -->" + (stockRates.length - 1));
            TOTAL_PROFIT += stockRates[stockRates.length - 1] - stockRates[purchasedStockIndex];
        }

        System.out.println("TOTAL Profit is " + TOTAL_PROFIT);
    }
}
