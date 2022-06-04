package com.datastructures.recursion;

import java.util.Stack;

import static com.datastructures.recursion.SortStackUsingRecursion.printStack;

/**
 * @author neeraj on 27/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class DeleteMiddleElement {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);

        deleteMiddleElement(stack, stack.size() / 2 + 1);

        printStack(stack);

        stack.clear();
        stack.push(1);
        deleteMiddleElement(stack, (stack.size() / 2) + 1);
        printStack(stack);
    }

    private static void deleteMiddleElement(Stack<Integer> stack, int k) {
        if (k == 0 || stack.isEmpty()) return;
        if (k == 1) stack.pop();

        if (!stack.isEmpty()) {
            int popped = stack.pop();
            deleteMiddleElement(stack, k - 1);
            stack.push(popped);
        }
    }
}
