package com.datastructures.recursion;

import com.util.LogUtil;

import java.util.Stack;

import static com.datastructures.recursion.SortStackUsingRecursion.printStack;

/**
 * @author neeraj on 27/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ReverseAStack {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);

        printStack((Stack<Integer>) stack.clone());
        reverseStack(stack);
        printStack((Stack<Integer>) stack.clone());

        // Revert to original
        reverseStack(stack);
        printStack((Stack<Integer>) stack.clone());
    }

    public static void reverseStack(Stack<Integer> stack) {
        if(stack.isEmpty())
            return;
        int popped = stack.pop();
        reverseStack(stack);

        insertAtBottom(stack, popped);
    }

    private static void insertAtBottom(Stack<Integer> stack, int popped) {
        if(stack.isEmpty()) {
            stack.push(popped);
            return;
        }

        // Hypothesis
        int item = stack.pop();
        insertAtBottom(stack, popped);
        stack.push(item);
    }
}
