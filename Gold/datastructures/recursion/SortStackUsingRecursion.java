package com.datastructures.recursion;

import com.util.LogUtil;

import java.util.Stack;

/**
 * @author neeraj on 27/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SortStackUsingRecursion {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(4);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(5);

        sort(stack);
        printStack(stack);
    }

    public static void printStack(Stack<Integer> stack) {
        while (!stack.isEmpty()) {
            LogUtil.logIt(""+stack.pop());
        }
        LogUtil.logIt("Done", true);
    }

    public static void sort(Stack<Integer> stack) {
        if(stack.isEmpty())
            return;

        // Hypothesis
        int popped = stack.pop();
        sort(stack);

        // induction
        insertAtCorrectPosition(stack, popped);
    }

    private static void insertAtCorrectPosition(Stack<Integer> stack, int popped) {
        if(stack.isEmpty() || stack.peek() > popped) {
            stack.push(popped);
            return;
        }

        // Hypothesis
        int item = stack.pop();
        sort(stack);

        insertAtCorrectPosition(stack, popped);
        stack.push(item);
    }
}
