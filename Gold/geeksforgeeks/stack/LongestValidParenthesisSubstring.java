package com.geeksforgeeks.stack;

import com.util.LogUtil;

import java.util.Stack;

/**
 * https://leetcode.com/problems/longest-valid-parentheses/
 * <p>
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * <p>
 * Example 1:
 * <p>
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * Example 2:
 * <p>
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 *
 * @author neeraj on 2019-07-20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LongestValidParenthesisSubstring {

    public static void main(String[] args) {
        findLongestValidParenthesis("((()(()((()))");
        findLongestValidParenthesis("((()");
        findLongestValidParenthesis(")()())");
        findLongestValidParenthesis("()(()))))");
        findLongestValidParenthesis("()(()))())()");
    }

    public static void findLongestValidParenthesis(String str) {
        char[] arr = str.toCharArray();
        Stack<Integer> stack = new Stack<>();
        int MAX_LENGTH = 0;

        /**
         * For the base case we need (-1) to be the first top element in the Stack
         *
         * Reason : Suppose we have a String = ();
         * Now when "(" comes we push it's index to the Stack
         * and when ")" comes, we just pop out the closest "(" bracket index from Stack.
         *
         * Now after this pop operation we need to evaluate that how much this pair contributed to the final output
         * this can be done by subtracting the currentIndex - whateverIndexWasThereOnTop after popping out the recent, one.
         *
         * hence pushing (-1) so that for ( )
         * after popping, total = 1(currentIndex) - (-1) (Index on Top) = 2;
         *
         */
        stack.push(-1);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                stack.push(i); // Pushing the index instead of "(" since it helps to calculate max length
            } else {
                stack.pop();

                // If Stack is not empty
                if (!stack.isEmpty()) {
                    MAX_LENGTH = Math.max(MAX_LENGTH, i - stack.peek());
                } else {
                    stack.push(i);
                }
            }
        }
        LogUtil.logIt("Longest Valid Parenthesis Substring in " + str + " is of length " + MAX_LENGTH);
    }


}
