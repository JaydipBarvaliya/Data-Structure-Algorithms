package com.leetcode.year_2020.DP.matrix_chain_multiplication;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.geeksforgeeks.org/boolean-parenthesization-problem-dp-37/
 * <p>
 * * Symbols
 * *    'T' ---> true
 * *    'F' ---> false
 * Operators
 * *    &   ---> boolean AND
 * *    |   ---> boolean OR
 * *    ^   ---> boolean XOR
 *
 * @author neeraj on 10/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class BooleanParenthesization {

    private static final String EMPTY_STRING = "";

    public static void main(String[] args) {
        System.out.println(countNumberOfWaysExpressionCanBeEvaluatedAsTrue("T^F&T"));
        System.out.println(countNumberOfWaysExpressionCanBeEvaluatedAsTrue("T^F|F"));
        System.out.println(countNumberOfWaysExpressionCanBeEvaluatedAsTrue("T|T&F^T"));
        System.out.println(countNumberOfWaysExpressionCanBeEvaluatedAsTrue("T|F^T"));
    }

    // Why map since in this question there are 3 variables which are changing
    //  and visualizing 3d matrix is kindaa not very intuitive.
    static Map<String, Integer> memorization;

    public static int countNumberOfWaysExpressionCanBeEvaluatedAsTrue(String input) {
        /**
         * So input will be like
         * [T, OR, F, AND, T, XOR, F]
         * Count the number of ways we can parenthesize the expression so that the value of expression evaluates to true.
         * ((T OR F) AND (T XOR F)) -----> this is how we can group them and it will return to true.
         * ((T OR F AND T) XOR (F)) -----> this is another way.
         *
         * This is somewhat similar to Matrix Chain Multiplication where we had to group the input dimensions in a certain manner.
         *
         * symbol[]    = {T, F, T}
         * operator[]  = {^, &}
         *
         * So our expression will become ==> [T ^ F & T] and we have to find number of ways.
         *
         * 4 step approach for Matrix Chain Multiplication related problems.
         * 1) Find i and j ====> it can be i=0; and j = len()-1;
         * 2) Find Base Condition :
         *          Before Base condition one important twist in this question.
         *
         *          Assume Question is :  [T ^ F]
         *          Now we know T ^ T = false
         *                      T ^ F = true
         *                      F ^ T = true
         *                      F ^ F = false.
         *                      So we can have false on either side.
         * Hence in sub-problem we might also want to find false records in both left and right because they all will contribute
         * to becoming true.
         *
         *                      2 (false) XOR (4 true).
         *
         *                      Now if i match any false with any true, i will get true, hence we need to solve for both
         *                      true and false in our sub-problems.
         *      solve(arr, i, j, isTrue)
         *       // Base Condition
         *       if(i > j) return 0;
         *       it(i==j) {
         *          if(isTrue) {
         *              if(arr[i] == true) return 1;
         *              return 0;
         *          } else {
         *              // VICE VERSA.
         *          }
         *       }
         *
         * 3) Choose K
         *          since operators are always sandwiched between T and False, so we will start k = 1
         *          and do k+=2 every iteration.
         *
         */
        memorization = new HashMap<>();
        return solve(input.toCharArray(), 0, input.length() - 1, true);
    }

    private static int solve(char[] input, int i, int j, Boolean isTrue) {
        String key = EMPTY_STRING + i + j + isTrue;
        if (memorization.containsKey(key)) return memorization.get(key);
        if (i > j) {
            memorization.put(key, 0);
            return 0; // Empty String there are no ways.
        }
        if (i == j) {
            if (isTrue) {
                memorization.put(key, input[i] == 'T' ? 1 : 0);
                return input[i] == 'T' ? 1 : 0;
            } else {
                memorization.put(key, input[i] == 'F' ? 1 : 0);
                return input[i] == 'F' ? 1 : 0;
            }
        }

        int answer = 0;
        for (int k = i + 1; k < j; k += 2) {
            String partialLeftKeyTrue = EMPTY_STRING + i + (k - 1) + "true";
            String partialLeftKeyFalse = EMPTY_STRING + i + (k - 1) + "false";
            String partialRightKeyTrue = EMPTY_STRING + (k + 1) + j + "true";
            String partialRightKeyFalse = EMPTY_STRING + (k + 1) + j + "false";
            int trueWaysInLeft = memorization.containsKey(partialLeftKeyTrue) ? memorization.get(partialLeftKeyTrue) : solve(input, i, k - 1, true);
            int falseWaysInLeft = memorization.containsKey(partialLeftKeyFalse) ? memorization.get(partialLeftKeyFalse) : solve(input, i, k - 1, false);
            int trueWaysInRight = memorization.containsKey(partialRightKeyTrue) ? memorization.get(partialRightKeyTrue) : solve(input, k + 1, j, true);
            int falseWaysInRight = memorization.containsKey(partialRightKeyFalse) ? memorization.get(partialRightKeyFalse) : solve(input, k + 1, j, false);

            // Now
            char operatorAtK = input[k];

            if (operatorAtK == '&') {
                if (isTrue) { // We have to find true with AND operator
                    // that's only possible when left is true and right is true
                    answer += trueWaysInLeft * trueWaysInRight;
                } else { // We have to find false with AND OPERATOR
                    answer += (trueWaysInLeft * falseWaysInRight) + (falseWaysInLeft * trueWaysInRight)
                            + (falseWaysInLeft * falseWaysInRight);
                }
            } else if (operatorAtK == '|') {
                if (isTrue) { // We have to find true with OR operator
                    // that's only possible when left is true and right is true
                    answer += (trueWaysInLeft * trueWaysInRight) +
                            (trueWaysInLeft * falseWaysInRight) +
                            (falseWaysInLeft * trueWaysInRight);
                } else { // We have to find false with OR OPERATOR
                    answer += falseWaysInLeft * falseWaysInRight;
                }
            } else if (operatorAtK == '^') {
                if (isTrue) { // We have to find true with XOR Operator
                    answer += (trueWaysInLeft * falseWaysInRight) + (falseWaysInLeft * trueWaysInRight);
                } else {
                    answer += (falseWaysInLeft * falseWaysInRight) + (trueWaysInLeft * trueWaysInRight);
                }
            }
        }
        memorization.put(key, answer);
        return answer;
    }
}
