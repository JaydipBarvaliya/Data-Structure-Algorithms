package com.geeksforgeeks.stack;

import java.util.*;

public class BalancedParentheses {
    private static List<Character> OpeningParentheses = Arrays.asList('(', '{', '[');
    private static Map<Character, Character> matchingParentheses = new HashMap<>();

    static {
        matchingParentheses.put(')', '(');
        matchingParentheses.put(']', '[');
        matchingParentheses.put('}', '{');
    }

    public static void main(String[] args) {
        String[] testCases = new String[]{"{[()]}", "{[(])}", "{{[[(())]]}}", "[()]{}{[()()]()}", "[(])", "[(]()"};
        for (String s : testCases) {
            System.out.println(s + " <<::::>> " + areParenthesesBalanced(s));
        }
    }

    public static boolean isOpeningParentheses(char c) {
        return OpeningParentheses.contains(c);
    }

    public static Character getMatchingParentheses(Character c) {
        return matchingParentheses.get(c);
    }

    public static boolean areParenthesesBalanced(String str) {
        boolean _result = true;
        Stack<Character> stack = new Stack<>();
        char[] input = str.toCharArray();

        for (char c : input) {
            if (isOpeningParentheses(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty() || stack.pop() != getMatchingParentheses(c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty() ? true : false;
    }
}
