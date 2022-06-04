package com.leetcode.year_2020.backtracking;

import java.util.*;

/**
 * https://leetcode.com/problems/remove-invalid-parentheses/
 *
 * @author neeraj on 08/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RemoveInvalidParentheses {

    public static void main(String[] args) {
        System.out.println(removeInvalidParentheses("()())()"));
        System.out.println(removeInvalidParentheses(")("));
    }


    public static List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        /**
         * We have to remove minimum parentheses to make the string valid, so we will try
         * removing only brackets 1 by 1. Also since minimum parentheses is to be removed,
         * BFS guarantees shortest path.
         */
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        boolean foundAValidString = false;

        while (!queue.isEmpty()) {
            String polled = queue.poll();

            if (isValid(polled)) {// Checking if we have a balanced parentheses.
                result.add(polled);
                foundAValidString = true;
            }

            // This check ensures that once we have found any valid parentheses pattern
            // we don't do any further BFS using items pending in the queue, since it will yield
            // String of smaller strings.
            // Whereas we will not break here since items of the same length might still be present and if some of them
            // are valid we need to add them.
            if (foundAValidString) continue;

            // Now this means we did not find a valid string
            // and we have to try to remove 1 bracket.
            for (int i = 0; i < polled.length(); i++) {
                if (polled.charAt(i) != '(' && polled.charAt(i) != ')') continue;
                String temp = polled.substring(0, i) + polled.substring(i + 1);
                if (!visited.contains(temp)) {
                    queue.add(temp);
                    visited.add(temp);
                }
            }
        }
        return result;
    }

    private static boolean isValid(String s) {
        int counter = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') counter++;
            if (c == ')') {
                // Trick catch if you have already reached to a 0 state then a closing bracket is coming before opening bracket
                // then that's invalid state.
                if (counter == 0) return false;
                counter--;
            }
        }
        return counter == 0;
    }
}
