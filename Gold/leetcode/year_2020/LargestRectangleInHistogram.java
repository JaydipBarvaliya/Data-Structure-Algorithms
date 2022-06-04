package com.leetcode.year_2020;

import java.util.Stack;

/**
 * @author neeraj on 27/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class  LargestRectangleInHistogram {

    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(largestRectangleArea(new int[]{1, 2, 3, 4, 5, 3, 3, 2}));
        System.out.println(largestRectangleArea(new int[]{2,1,2}));
    }

    public static int largestRectangleArea(int[] heights) {
        /**
         * We will keep on pushing items index in the stack until the next item in iteration
         * is smaller than the top item in stack.
         */
        Stack<Integer> stack = new Stack<>();
        int i = 0, popped = 0, MAX_AREA = 0;
        int area = 0;

        // For Each height in Histogram
        for (i = 0; i < heights.length; i++) {
            // If the current Item > top(stack)
            // Push the currentItem's index in the stack.
            if (stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
                stack.push(i);
            } else {
                /**
                 * we have reached to a height which is smaller than the top[stack]
                 * so, top[stack] can't grow beyond this point, so let's calculate the area till now.
                 *
                 * // after Popping;
                 * popped = stack.pop();
                 *
                 *  [2,1,2]
                 *  1 can span it's area to it's left and right as well. so total area of rectangle = 3.
                 *
                 * if(stack.isEmpty()) {
                 *  area = height[popped] * i;
                 * } else {
                 *  area = height[popped] * (i - stack.peek() - 1); // Why minus 1 because we are calculating the area of region
                 *                                                  // and since currentIndex item is smaller than the previous ones
                 *                                                  // hence it can't contribute.
                 * }
                 */
                while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                    popped = stack.pop();
                    if (stack.isEmpty()) {
                        area = heights[popped] * i;
                    } else {
                        area = heights[popped] * (i - 1 - stack.peek());
                    }
                    MAX_AREA = Math.max(MAX_AREA, area);
                }
                stack.push(i);
            }
        }

        while (!stack.isEmpty()) {
            popped = stack.pop();
            if (stack.isEmpty()) {
                area = heights[popped] * i;
            } else {
                area = heights[popped] * (i - 1 - stack.peek());
            }
            MAX_AREA = Math.max(MAX_AREA, area);
        }
        return MAX_AREA;
    }
}
