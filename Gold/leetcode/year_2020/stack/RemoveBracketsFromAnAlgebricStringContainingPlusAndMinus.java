package com.leetcode.year_2020.stack;

import com.util.LogUtil;

import java.util.Stack;

/**
 * @author neeraj on 04/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RemoveBracketsFromAnAlgebricStringContainingPlusAndMinus {

    public static void main(String[] args) {
        removeBrackets("a-(b+c)");
        removeBrackets("a+(b+c)");
        removeBrackets("-a+b+c");
        removeBrackets("(a+b)-(c+d)");
        removeBrackets("(a+b)+(c+d)");
        removeBrackets("a-(b-c-(d+e))-f");
    }

    public static void removeBrackets(String str) {
        Stack<Integer> stack = new Stack<>();

        /**
         * 0 ----> represents "NOT TOGGLE"
         * 1 ----> represents "TOGGLE"
         */
        stack.push(0); // Means initially we don't want to toggle the sign
        StringBuilder result = new StringBuilder();
        int index = 0;
        for (char c : str.toCharArray()) {

            if (c == '-') {
                result.append(stack.peek() == 1 ? '+' : '-');
            } else if (c == '+') {
                result.append(stack.peek() == 1 ? '-' : '+');
            } else if (c == '(') {
                if (index > 0 && str.charAt(index - 1) == '-') {
                    stack.push(stack.peek() == 1 ? 0 : 1);
                } else {
                    stack.push(stack.peek()); // Pushing the same operator again, to get this reflected in the inner bracket.
                }
            } else if (c == ')') {
                stack.pop(); // Remove the last toggle operator added for this particular bracket
            } else {
                result.append(c);
            }
            index++;
        }
        LogUtil.logIt("Expression : " + str + " is now " + result);
    }
}
