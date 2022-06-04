package com.geeksforgeeks.stack;

import java.util.Stack;

public class ReverseStack {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println("Before Reversing " + stack);
        reverseStack(stack);
        System.out.println("After Reversing " + stack);

    }

    public static void reverseStack(Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            Integer popped = stack.pop();
            reverseStack(stack);
            insertAtBottom(stack, popped);
        }
    }

    private static void insertAtBottom(Stack<Integer> stack, Integer item) {
        if (stack.isEmpty()) {
            stack.push(item);
        } else {
            Integer popped = stack.pop();
            insertAtBottom(stack, item);
            stack.push(popped);
        }
    }


}
