package com.leetcode.backtracking;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/
 * <p>
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * For example, given n = 3, a solution set is:
 * <p>
 * [
 * *  "((()))",
 * *  "(()())",
 * *  "(())()",
 * *  "()(())",
 * *  "()()()"
 * ]
 *
 * @author neeraj on 2019-05-11
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class GenerateBalancedParenthesis {

    public static void main(String[] args) {
        generateParenthesis(3);
        generateParenthesis(4);
    }

    public static void generateParenthesis(int numPairs) {
        LogUtil.logIt("All valid pairs Of Parenthesis which can be generated with " + numPairs + " open and close" +
                " brackets");

        List<String> allPairs = new ArrayList<>();
        generateParenthesisRecursively(numPairs, numPairs, "", allPairs);

        LogUtil.logIt(allPairs.toString());
    }

    private static void generateParenthesisRecursively(int leftParamNeeded, int rightParamNeeded, String currentParanInProcess,
                                                       List<String> allPairs) {

        // Base Case 1
        // When we have no left or right paren count left
        if (leftParamNeeded == 0 && rightParamNeeded == 0) {
            allPairs.add(currentParanInProcess);
            return;
        }

        // If we still have leftParenNeeded, lets traverse left side
        if (leftParamNeeded > 0) {
            generateParenthesisRecursively(leftParamNeeded - 1, rightParamNeeded,
                    currentParanInProcess + "(", allPairs);
        }

        // We can only close the parenthesis if we have any left paren open.
        if (rightParamNeeded > leftParamNeeded) {
            generateParenthesisRecursively(leftParamNeeded, rightParamNeeded - 1,
                    currentParanInProcess + ")", allPairs);
        }
    }
}
