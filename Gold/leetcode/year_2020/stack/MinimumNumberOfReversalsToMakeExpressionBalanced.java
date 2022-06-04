package com.leetcode.year_2020.stack;

import com.util.LogUtil;

import java.util.Stack;

/**
 * https://www.geeksforgeeks.org/minimum-number-of-bracket-reversals-needed-to-make-an-expression-balanced/
 *
 * @author neeraj on 02/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumNumberOfReversalsToMakeExpressionBalanced {

    public static void main(String[] args) {
        LogUtil.logIt(findMinimumReversals("}{}{") + "");
        LogUtil.logIt(findMinimumReversals("}{{}}{{{") + "");
    }


    public static int findMinimumReversals(String str) {
        /**
         * 1) Only even length string containing ( and ) will be balanced
         * 2) we will eliminate all the pre-balanced expression, since they will never contribute.
         * 3) if after elimination we have any imbalanced expression still present in the stack.
         * Assume :
         *
         *      }  {   {   }   }   {   {   {
         *        ||          ||
         *        ||          ||
         *        ||          ||
         *        -------------
         *        This part is already balanced.
         * So after elimination we have only "} { { {" left in stack, now if we carefully notice
         * we need to do 3 total reversals.
         * 2 of which are boundary ones which will make "{ { { }"
         * other single inside which will make " { { } } "
         *
         * So total of 3..
         * So if you have even opening or closing brackets...
         * {{{{
         * }}}}    ======> You exactly need [count/2] operations. In this case 4/2 == > 2 operation in both.
         *
         * When you have combination of both....like some m --> "}" some n ---> "{"
         * So we need total of ceil[m/2] + ceil[n/2]
         *
         * Logic of ceil, suppose we have 3 opening and 1 closing
         * like this "] [ [ [", if you notice carefully after we balance opening internally
         * "] [ ] ["...... We have 1 perfect balanced pair we will remove this
         * "]   [" ..... and now since both are in opposite direction we have to flip both
         * "[]".....
         *
         * Hence in open===3 case, we need total 2 reversal, 1 for making perfect pair and other left
         * to be combined with the closing case. but if i do 3/2 == 1.5 hence inorder to make it 2 we need ceil.
         */
        Stack<Character> stack = new Stack<>();
        int opening = 0, closing = 0;
        for (char c : str.toCharArray()) {
            if (c == '}') {
                if (opening > 0) {
                    stack.pop();
                    opening--;
                } else {
                    closing++;
                    stack.push('{');
                }
            } else {
                stack.push('{');
                opening++;
            }
        }
        return (int) (Math.ceil(opening / 2d) + Math.ceil(closing / 2d));
    }
}
