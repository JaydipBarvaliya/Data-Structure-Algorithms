package com.leetcode.year_2020.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BasicCalulator {

    static Map<Character, Integer> operatorsPriority = new HashMap<>();

    static {
        operatorsPriority = new HashMap<>();
        operatorsPriority.put('*', 1);
        operatorsPriority.put('/', 1);
        operatorsPriority.put('+', 2);
        operatorsPriority.put('-', 2);
    }

    public static void main(String[] args) {

        System.out.println(calculate("0-2147483647"));
        System.out.println(calculate("3+2*2"));
        System.out.println(calculate(" 3/2 "));
        System.out.println(calculate(" 3+5 / 2 "));
        System.out.println(calculate(" 3 + 2 * 3 * 4 + 9 / 3"));
        System.out.println(calculate("42"));
        System.out.println(calculate("1-1-1"));

        System.out.println(calculateOptimized("0-2147483647"));
        System.out.println(calculateOptimized("3+2*2"));
        System.out.println(calculateOptimized(" 3/2 "));
        System.out.println(calculateOptimized(" 3+5 / 2 "));
        System.out.println(calculateOptimized(" 3 + 2 * 3 * 4 + 9 / 3"));
        System.out.println(calculateOptimized("42"));
        System.out.println(calculateOptimized("1-1-1"));
    }

    public static int calculateOptimized(String str) {
        final Stack<Integer> stack = new Stack<>();
        int num = 0;
        char lastSign = '+';
        for (int i = 0; i < str.length(); i++) {
            final char ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
            }
            if ((!Character.isDigit(ch) && ch != ' ') || i == str.length() - 1) {
                switch (lastSign) {
                    case '+': {
                        stack.push(num);
                        break;
                    }
                    case '-': {
                        stack.push(-num);
                        break;
                    }
                    case '*': {
                        stack.push(stack.pop() * num);
                        break;
                    }
                    case '/': {
                        stack.push((stack.pop() / num));
                        break;
                    }
                }
                lastSign = ch;
                num = 0;
            }
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    public static int calculate(String str) {
        final Stack<Character> operatorsStack = new Stack();
        final Stack<Integer> operandsStack = new Stack();
        boolean wasLastPlacedItemDigit = false;
        for (char ch : str.toCharArray()) {
            if (ch == ' ') {
                continue;
            } else if (Character.isDigit(ch)) {
                if (!wasLastPlacedItemDigit) {
                    operandsStack.push(ch - '0');
                } else if (!operandsStack.isEmpty()) {
                    operandsStack.push(operandsStack.pop() * 10 + (ch - '0'));
                }
                wasLastPlacedItemDigit = true;
            } else {
                wasLastPlacedItemDigit = false;
                while (!operatorsStack.isEmpty()
                        && operatorsPriority.get(operatorsStack.peek()) <= operatorsPriority.get(ch)) {
                    final char operator = operatorsStack.pop();
                    final Integer operand2 = operandsStack.pop();
                    final Integer operand1 = operandsStack.pop();
                    final Integer result = calculateValue(operator, operand1, operand2);
                    operandsStack.push(result);
                }
                operatorsStack.push(ch);
            }
        }

        while (!operandsStack.isEmpty() && !operatorsStack.isEmpty()) {
            final char operator = operatorsStack.pop();
            final Integer operand2 = operandsStack.pop();
            final Integer operand1 = operandsStack.pop();
            final Integer result = calculateValue(operator, operand1, operand2);
            operandsStack.push(result);
        }
        return operandsStack.pop();
    }

    public static int calculateValue(final char operator, final Integer operand1, final Integer operand2) {
        Integer response = 0;
        switch (operator) {
            case '*': {
                response = operand1 * operand2;
                break;
            }
            case '/': {
                response = operand1 / operand2;
                break;
            }
            case '+': {
                response = operand1 + operand2;
                break;
            }
            case '-': {
                response = operand1 - operand2;
                break;
            }
        }
        return response;
    }
}
