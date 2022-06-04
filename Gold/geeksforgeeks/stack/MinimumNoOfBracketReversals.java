package com.geeksforgeeks.stack;

import java.util.Stack;

public class MinimumNoOfBracketReversals {

    public static void main(String[] args) {
        // Optimized version
        System.out.println("Optimized Version");
        System.out.println(findMinimumNoOfBracketsReversalsOptimized("{{{{"));
        System.out.println(findMinimumNoOfBracketsReversalsOptimized("{{{{}}"));
        System.out.println(findMinimumNoOfBracketsReversalsOptimized("}{{}}{{{"));
    }

    /**
     * Initialize Result as 0
     * <p>
     * 1) If traversed character is "{" then simply push into stack
     * 2) If traversed character is "}", pop from stack and if now stack is empty then result++ and push "{" into stack.
     * 3) When traversal is completed, result += stack.size() / 2;
     *
     * @param input
     * @return
     */
    public static int findMinimumNoOfBracketsReversalsOptimized(String input) {
        int result = 0;
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) {
            if (c == '{') {
                stack.push(c);
            } else {
                if (stack.size() > 0) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    result++;
                    stack.push('{');
                }
            }
        }
        return result + stack.size() / 2;
    }
}
