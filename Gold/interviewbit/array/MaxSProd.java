package com.interviewbit.array;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * https://www.interviewbit.com/problems/maxspprod/
 * <p>
 * You are given an array A containing N integers. The special product of each ith integer in this array is defined as the product of the following:
 * <p>
 * LeftSpecialValue: For an index i, it is defined as the index j such that A[j]>A[i] (i>j). If multiple A[j]'s are present in multiple positions,
 * the LeftSpecialValue is the maximum value of j.
 * <p>
 * RightSpecialValue: For an index i, it is defined as the index j such that A[j]>A[i] (j>i). If multiple A[j]s are present in multiple positions,
 * the RightSpecialValue is the minimum value of j.
 * Write a program to find the maximum special product of any integer in the array.
 * <p>
 * Input: You will receive array of integers as argument to function.
 * <p>
 * Return: Maximum special product of any integer in the array modulo 1000000007.
 * <p>
 * Note: If j does not exist, the LeftSpecialValue and RightSpecialValue are considered to be 0.
 * <p>
 * Constraints
 * 1 <= N <= 10^5
 * 1 <= A[i] <= 10^9
 * <p>
 * <p>
 * <p>
 * Some Explanation :
 * <p>
 * what will be the right value and final answer in arr[8, 5, 7, 8, 1, 9] for value 7?
 * <p>
 * D
 * For the RightSpecialValue you’re supposed to look to the right side of the index i.
 * In your case give array is [8, 5, 7, 8, 1, 9] and you want to get the RightSpecialValue of 7 which is at index 2 (i)
 * now if we look right side of the index 2 there are 2 values for j such that A[j]>A[i] (8 at the index 3 and 9 at the index 5)
 * but as stated in question we have to choose the minimum j so it would be index 3.
 *
 * @author neeraj on 2019-07-26
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaxSProd {

    public static void main(String[] args) {
        MaxSProd maxSProd = new MaxSProd();
        maxSProd.calculateMaxSProd(Arrays.asList(8, 5, 7, 8, 1, 9));
        maxSProd.calculateMaxSProd(Arrays.asList(5, 9, 6, 8, 6, 4, 6, 9, 5, 4, 9));
        maxSProd.calculateMaxSProd(Arrays.asList(7, 5, 7, 9, 8, 7));
    }

    public void calculateMaxSProd(List<Integer> values) {
        LogUtil.logIt("Calculating MaxSProd  of " + values);
        ArrayList<Integer> items = new ArrayList<>();
        values.forEach(item -> items.add(item));
        System.out.println(maxSpecialProduct(items));
    }

    public int maxSpecialProduct(ArrayList<Integer> A) {
        int[] arr = A.stream().mapToInt(i -> i).toArray();
        int MAX_PROD = 0;
        int CURR_PROD = 0;
        List<Integer> leftSpecialValues = getLeftSpecialValues(A);
        List<Integer> rightSpecialValues = getRightSpecialValues(A);

        for (int i = 0; i < arr.length; i++) {
            CURR_PROD = leftSpecialValues.get(i) * rightSpecialValues.get(i);
            if (CURR_PROD > MAX_PROD) {
                MAX_PROD = CURR_PROD;
            }
        }
        return (MAX_PROD % 1000000007);
    }

    public List<Integer> getLeftSpecialValues(List<Integer> allItems) {
        List<Integer> leftSpecialValues = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < allItems.size(); i++) {

            // Pop All items from the stack which are either less than or equal to the i'th item.
            while (!stack.isEmpty() && stack.peek() <= allItems.get(i)) {
                stack.pop();
            }

            // If Stack is not filled till now, then 0th index is our LSV
            if (stack.isEmpty()) {
                leftSpecialValues.add(0);
            } else {
                // Whatever is on the top of the stack is the greatest value than i
                leftSpecialValues.add(stack.peek());
            }
            stack.add(i);
        }
        return leftSpecialValues;
    }

    public List<Integer> getRightSpecialValues(List<Integer> allItems) {
        List<Integer> rightSpecialValues = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = allItems.size() - 1; i >= 0; i--) {

            // Pop All items from the stack which are either less than or equal to the i'th item.
            while (!stack.isEmpty() && stack.peek() <= allItems.get(i)) {
                stack.pop();
            }

            // You will notice that we are always adding on the 0th index, because
            // the Question says if there are multiple contenders for the RSV then the one with the minimum index wins.

            // If Stack is not filled till now, then 0th index is our LSV
            if (stack.isEmpty()) {
                rightSpecialValues.add(0, 0);
            } else {
                // Whatever is on the top of the stack is the greatest value than i
                rightSpecialValues.add(0, stack.peek());
            }
            stack.add(i);
        }
        return rightSpecialValues;
    }

}
