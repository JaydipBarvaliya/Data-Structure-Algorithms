package com.leetcode.year_2020.stack;

import java.util.Stack;

/**
 * @author neeraj on 23/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumRemoveToMakeValidParentheses {

    public static void main(String[] args) {
        System.out.println(minRemoveToMakeValid("lee(t(c)o)de)"));
        System.out.println(minRemoveToMakeValid("a)b(c)d"));
        System.out.println(minRemoveToMakeValid("))(("));
        System.out.println(minRemoveToMakeValid("(a(b(c)d)"));
    }

    public static String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        char[] input = s.toCharArray();

        for (int i = 0; i < input.length; i++) {
            if (input[i] == '(') {
                stack.push(i);
            } else if(input[i] == ')') {
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    if (input[stack.peek()] == '(') {
                        stack.pop();
                    } else {
                        stack.push(i);
                    }
                }
            }
        }
        // Now in our Stack we have index of only invalid brackets
        while (!stack.isEmpty()) {
            int index = stack.pop();
            input[index] = '-'; // mark flag to not choose
        }

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            if (input[i] != '-') {
                str.append(input[i]);
            }
        }

        return str.toString();
    }
}
