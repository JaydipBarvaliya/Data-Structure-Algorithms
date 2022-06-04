package com.geeksforgeeks.stack;

import java.util.Stack;

public class SortStack {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(30);
        stack.push(-5);
        stack.push(18);
        stack.push(14);
        stack.push(-3);

        System.out.println("Before Sorting" + stack);
        sortStack(stack);
        System.out.println("After Sorting" + stack);

        Stack<Integer> input = new Stack<>();
        input.push(34);
        input.push(3);
        input.push(31);
        input.push(98);
        input.push(92);
        input.push(23);
        sortStackUsingTempStack(input);
    }

    public static void sortStackUsingTempStack(Stack<Integer> stack) {
        System.out.println("Before Sorting using temp stack " + stack);
        Stack<Integer> tempStack = new Stack<>();
        Integer popped = null;
        while (!stack.isEmpty()) {
            popped = stack.pop();
            if (tempStack.isEmpty()) {
                tempStack.push(popped);
            } else {
                while (!tempStack.isEmpty() && tempStack.peek() > popped) {
                    stack.push(tempStack.pop());
                }
                tempStack.push(popped);
            }
        }
        System.out.println("After Sorting using temporary Stack " + tempStack);
    }

    public static void sortStack(Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            Integer popped = stack.pop();
            sortStack(stack);
            insertAtBottom(stack, popped);
        }
    }

    private static void insertAtBottom(Stack<Integer> stack, Integer item) {
        if (stack.isEmpty() || stack.peek() < item) {
            stack.push(item);
        } else {
            Integer popped = stack.pop();
            insertAtBottom(stack, item);
            stack.push(popped);
        }
    }

}
