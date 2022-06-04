package com.leetcode.year_2020.stack;

import com.util.LogUtil;

import java.util.Stack;

/**
 * @author neeraj on 13/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NearestSmallestElementInTheRight {

    public static void main(String[] args) {
        printAllNearestSmallestElementToTheRight(new int[]{1, 3, 2, 4});
        printAllNearestSmallestElementToTheRight(new int[]{1, 8, 2, 4});
        printAllNearestSmallestElementToTheRight(new int[]{1, 3, 0, 0, 1, 2, 4});
    }

    public static void printAllNearestSmallestElementToTheRight(int[] arr) {
        LogUtil.logIt("Printing All Nearest Greater Element to the Right", true);
        Stack<Integer> stack = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                System.out.println(arr[i] + "----> " + -1);
            } else {
                while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    System.out.println(arr[i] + "----> " + -1);
                } else {
                    System.out.println(arr[i] + "----> " + stack.peek());
                }
            }
            stack.push(arr[i]);
        }
    }
}
