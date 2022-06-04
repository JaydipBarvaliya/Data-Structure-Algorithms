package com.leetcode.problems.medium;

import com.util.LogUtil;

/**
 * @author neeraj on 23/11/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class BuyAndSellStockWithTransactionFee {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
        System.out.println(maxProfitOptimized(new int[]{1, 3, 2, 8, 4, 9}, 2));
        System.out.println(maxProfitSpaceOptimized(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }

    public static int maxProfitSpaceOptimized(int[] prices, int fee) {
        int buy = -prices[0]; // On day 0, we don't have any profit so whatever we purchase we are going in -ive.
        int sell = 0; // On day 0, since you don't have any stock you can't sell, hence 0 profit.

        for (int i = 1; i < prices.length; i++) {
            // On ith day we can make profit in buy status, either by not doing anything
            // since we have already purchased stock on previous day
            // or whatever profit we make by
            // (selling the previous day[so ultimately whatever is in our pocket] - current day stock price)
            // i.e below equation.
            buy = Math.max(buy, sell - prices[i]);

            // Now on i'th day we can make profit in selling status by not doing anything
            // since we have already sold on previous day
            // Or whatever (profit is left in our pocket by previous day purchase + current day price - transaction fee).
            // Why Add operation because  (profit = sellingPrice - costPrice.)
            // So we purchased yesterday in let's say X-Rupees(and we have after that 2 rupees in out pocket) and today cost is X+Y rupees.
            // So total PROFIT after selling which will be in our pocket will be
            // (2 + X + Y - transaction_fee)
            sell = Math.max(sell, buy + prices[i] - fee);
        }
        return sell;
    }

    public static int maxProfitOptimized(int[] prices, int transactionFees) {
        int buy_prev, noAction_prev, sell_prev, rest_prev, max_profit;
        int buy, noAction, sell, rest;

        buy = -prices[0] - transactionFees;
        noAction = Integer.MIN_VALUE; // Since we don't own any stock on day 1
        sell = Integer.MIN_VALUE; // Since we can't sell before we buy
        rest = 0; // This is possible since we don't own any stock we can choose to rest today.

        for (int i = 1; i < prices.length; i++) {
            // Update all with today's profit
            buy_prev = buy;
            noAction_prev = noAction;
            sell_prev = sell;
            rest_prev = rest;

            buy = Math.max(sell_prev, rest_prev) - prices[i] - transactionFees;
            sell = Math.max(buy_prev, noAction_prev) + prices[i];
            noAction = Math.max(noAction_prev, buy_prev);
            rest = Math.max(rest_prev, sell_prev);
        }
        max_profit = Math.max(Math.max(Math.max(buy, sell), rest), noAction);
        return max_profit;
    }

    public static int maxProfit(int[] prices, int transactionFees) {
        // Since on each day we can have just 4 allowed actions
        // 1) Buy
        // 2) No Action [You Own the Stock but not selling it]
        // 3) Sell
        // 4) Rest [You don't own any stock]
        int[] buy = new int[prices.length];
        int[] noAction = new int[prices.length];
        int[] sell = new int[prices.length];
        int[] rest = new int[prices.length];
        // Now on very first day, this will be the profit at end of day

        buy[0] = -prices[0] - transactionFees;
        noAction[0] = Integer.MIN_VALUE; // Since we don't own any stock on day 1
        sell[0] = Integer.MIN_VALUE; // Since we can't sell before we buy
        rest[0] = 0; // This is possible since we don't own any stock we can choose to rest today.

        // Now let's calculate for all other days and at the end of prices's day we
        // will know the max profit we were able to make
        for (int i = 1; i < prices.length; i++) {
            buy[i] = Math.max(rest[i - 1], sell[i - 1]) - prices[i] - transactionFees; // you can only buy if you have sold the stock on previous day or you were resting the previous day.
            sell[i] = Math.max(noAction[i - 1], buy[i - 1]) + prices[i]; // you can only sell if you have bought on previous day or you already had stock but choose to take no action on previous day.
            noAction[i] = Math.max(noAction[i - 1], buy[i - 1]);
            rest[i] = Math.max(rest[i - 1], sell[i - 1]);
        }

        LogUtil.printArray(buy);
        LogUtil.printArray(sell);
        LogUtil.printArray(noAction);
        LogUtil.printArray(rest);

        int lastDay = prices.length - 1;
        return Math.max(Math.max(Math.max(buy[lastDay], sell[lastDay]), rest[lastDay]), noAction[lastDay]);
    }
}
