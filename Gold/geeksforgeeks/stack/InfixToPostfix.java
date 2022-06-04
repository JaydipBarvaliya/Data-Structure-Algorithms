package com.geeksforgeeks.stack;

import java.util.*;

public class InfixToPostfix {

    static List<Character> operators = Arrays.asList('+', '*', '/', '-', '(', ')', '^');
    static Map<Character, Integer> precedenceMap = new HashMap<>();

    static {
        precedenceMap.put('^', 3);
        precedenceMap.put('*', 2);
        precedenceMap.put('/', 2);
        precedenceMap.put('+', 1);
        precedenceMap.put('-', 1);
        precedenceMap.put('(',0);
    }

    public static void main(String[] args) {
        infixToPostfix("a+b*(c^d-e)^(f+g*h)-i");
    }

    public static void infixToPostfix(String str) {
        char[] input = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        char temp = 'c';

        for (char c : input) {
            if (!isOperator(c)) {
                System.out.print(c);
            } else {
                if (c == '(') {
                    stack.push('(');
                } else if (c == ')') {
                    while (!stack.isEmpty() && (temp = stack.peek()) != '(') {
                        System.out.print(stack.pop());
                    }
                    if(temp == '(') {
                        stack.pop();
                    } else {
                        if(!stack.isEmpty()) {
                            System.out.println("Invalid Expression");
                        }
                    }
                } else {
                    while (!stack.isEmpty() && !inputHasGreaterPrecedence(stack.peek(), c)) {
                        System.out.print(stack.pop());
                    }
                    stack.push(c);
                }
            }
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
    }

    public static boolean inputHasGreaterPrecedence(Character stackTop, Character input) {
        int inputPriority = precedenceMap.get(input);
        int stackTopPriority = precedenceMap.get(stackTop);

        if (stackTopPriority < inputPriority) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOperator(Character c) {
        return operators.contains(c);
    }
}
