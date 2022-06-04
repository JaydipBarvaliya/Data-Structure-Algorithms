package com.leetcode.year_2020.stack;

import java.util.Stack;

/**
 * 678. Valid Parenthesis String
 * https://leetcode.com/problems/valid-parenthesis-string/
 *
 * @author neeraj on 16/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ValidParenthesisString {

    public static void main(String[] args) {
        System.out.println(checkValidString("()"));
        System.out.println(checkValidString("(*)"));
        System.out.println(checkValidString("(*))"));
        System.out.println(checkValidString("(())((())()()(*)(*()(())())())()()((()())((()))(*"));
    }

    public static boolean checkValidString(String s) {
        // We will push the index of items not the item.
        Stack<Integer> open = new Stack<>();
        Stack<Integer> asterisk = new Stack<>();

        // Step 1: Considering all Asterisk as '('
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                    open.push(i);
                    break;
                case '*':
                    asterisk.push(i);
                    break;
                case ')': {
                    if (open.isEmpty()) { // Matching '(' not found, so lets make '*' as a opening bracket.
                        if (asterisk.isEmpty()) {
                            return false; // In this case no matches are present.
                        } else {
                            asterisk.pop();
                        }
                    } else {
                        open.pop(); // We have a matching '(' so let's pop it.
                    }
                    break;
                }
            }
        }

        //Step 2: Considering all asterisk '*' as ')'
        while (!open.isEmpty() && !asterisk.isEmpty()) {
            // Why are we comparing the index of asterisk
            // Since there can be a scenario that * came before (
            // like this input [* (]
            // Here it's not a valid parenthesis string
            // hence you need to check for index("(") < index("*")
            if (open.peek() > asterisk.peek()) return false;
            open.pop();
            asterisk.pop();
        }
        return open.isEmpty();
    }
}
