package com.leetcode.year_2020.DP.matrix_chain_multiplication;

import java.util.Stack;

/**
 * Inspired from : https://www.youtube.com/watch?v=lYw86z7Astg&feature=youtu.be
 * <p>
 * https://leetcode.com/problems/parsing-a-boolean-expression/
 * <p>
 * Return the result of evaluating a given boolean expression, represented as a string.
 * <p>
 * An expression can either be:
 * <p>
 * "t", evaluating to True;
 * "f", evaluating to False;
 * "!(expr)", evaluating to the logical NOT of the inner expression expr;
 * "&(expr1,expr2,...)", evaluating to the logical AND of 2 or more inner expressions expr1, expr2, ...;
 * "|(expr1,expr2,...)", evaluating to the logical OR of 2 or more inner expressions expr1, expr2, ...
 *
 * @author neeraj on 11/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ParsingABooleanExpression {

    public static void main(String[] args) {
        System.out.println(parseBoolExpr("!(f)"));
        System.out.println(parseBoolExpr("|(f,t)"));
        System.out.println(parseBoolExpr("&(t,f)"));
        System.out.println(parseBoolExpr("|(&(t,f,t),!(t))"));
    }

    public static boolean parseBoolExpr(String expression) {
        Stack<Character> stack = new Stack<>();

        for (char c : expression.toCharArray()) {
            if(c == ',') continue;
            if (c != ')') {
                stack.push(c);
            } else { // If it's a closing bracket
                // we need to pop till opening bracket.
                StringBuilder innerExpression = new StringBuilder();
                while (stack.peek() != '(') {
                    char popped = stack.pop();
                    if (popped == 't' || popped == 'f') {
                        innerExpression.append(popped);
                    }
                }
                stack.pop(); // pop the opening bracket.
                // Now the top of stack must contain
                // the operator to be applied on this innerExpression
                char result = evalExpression(innerExpression, stack.pop());
                if (stack.isEmpty()) {
                    return result == 't' ? true : false;
                } else {
                    stack.push(result);
                }
            }
        }
        return false;
    }

    private static char evalExpression(StringBuilder innerExpression, Character operator) {
        if (operator == '&') {
            // if it's AND and if is there any false in the innerExpression the result will always be false.
            return innerExpression.toString().contains("f") ? 'f' : 't';
        } else if (operator == '|') {
            // For OR operator any true in the expression will always evaluate to true.
            return innerExpression.toString().contains("t") ? 't' : 'f';
        } else { // Not of any expression '!'
            return innerExpression.charAt(0) == 't' ? 'f' : 't';
        }
    }
}
