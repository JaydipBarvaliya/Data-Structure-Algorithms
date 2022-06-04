package com.geeksforgeeks.stack;

import com.geeksforgeeks.array.ArrayRotation;

import java.util.Stack;

public class StockSpanProblem {

    public static void main(String[] args) {
        int price[] = {100, 80, 60, 70, 60, 75, 85};
        int[] result = new int[price.length];
        initializeResult(result);
        System.out.println("Calculating Span for " + price.length + " days");
        calculateSpanBruteForce(price, price.length, result);
        ArrayRotation.printArray(result);

        initializeResult(result);
        System.out.println("Calculating Span in optimized manner for " + price.length + " days");
        calculateSpanOptimized(price, price.length, result);
        ArrayRotation.printArray(result);
    }

    private static void initializeResult(int[] result) {
        // For Each Stock Price have at-least 1 stock Span
        for (int i = 0; i < result.length; i++) {
            result[i] = 1;
        }
    }

    private static void calculateSpanBruteForce(int[] price, int length, int[] result) {

        for (int i = length - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (price[i] > price[j]) {
                    result[i] += 1;
                }
            }
        }
    }

    /**
     * 1) Push 1st Price into the Stack
     * 2) Span_Till_Now = 1
     * 3) for i = 0 to length
             if(arr[i] < top(stack))
                push(arr[i]) into Stack
             else
                while(peek[stack] < arr[i]) {
                    spanTillNow += 1
                }
            push(arr[i]) into Stack
     *
     * @param price
     * @param length
     * @param result
     */
    private static void calculateSpanOptimized(int[] price, int length, int[] result) {
        Stack<Integer> stack = new Stack<>();
        stack.push(price[0]);
        int MAX_STOCK_SPAN = 1;

        for (int i = 1; i < length; i++) {
            if (price[i] < stack.peek()) {
                stack.push(price[i]);
            } else {
                while (stack.peek() < price[i]) {
                    MAX_STOCK_SPAN += 1;
                    stack.pop();
                }
                stack.push(price[i]);
                result[i] = MAX_STOCK_SPAN;
            }
        }
    }
}
