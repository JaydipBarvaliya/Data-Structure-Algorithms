package com.leetcode.year_2020.stack;

import com.util.LogUtil;

import java.util.Stack;

/**
 * https://leetcode.com/problems/online-stock-span/
 *
 * @author neeraj on 13/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class StockSpanProblem {

    public static void main(String[] args) {
        printStockSpan(new int[]{100, 80, 60, 70, 60, 75, 85});
        printStockSpan(new int[]{1, 2, 3, 4, 5});
        printStockSpan(new int[]{31, 41, 48, 59, 79});

    }

    /**
     * For example, if the price of a stock over the next 7 days were
     * [100, 80, 60, 70, 60, 75, 85],
     * then the stock spans would be [1, 1, 1, 2, 1, 4, 6].
     * <p>
     * So for 75 price (consecutive smaller prices before this and including today is 4)
     * [60, 70, 60, 75].... so essentially we are looking the NearestGreaterElement in the left
     * once we have the index of that element . currentIndex - nextGreaterElementInTheLeft'sIndex is our answer.
     *
     * @param stockPrices
     */
    public static void printStockSpan(int[] stockPrices) {
        LogUtil.logIt("Stock Span of the Stock Prices " + LogUtil.getArrayAsString(stockPrices) + " is ", true);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < stockPrices.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
                System.out.println(stockPrices[i] + "=========>" + 1);
            } else {
                while (!stack.isEmpty() && stockPrices[stack.peek()] <= stockPrices[i]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    System.out.println(stockPrices[i] + "=========>" + (i - stack.peek()));
                } else {
                    System.out.println(stockPrices[i] + "=========>" + ((i + 1)));
                }
                stack.push(i);
            }
        }
    }
}
