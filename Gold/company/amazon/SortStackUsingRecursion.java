package com.company.amazon;

import java.util.Stack;

public class SortStackUsingRecursion {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.add(30);
        stack.add(-5);
        stack.add(18);
        stack.add(14);
        stack.add(-3);

        System.out.println(stack);
        sortStack(stack);
        System.out.println(stack);
    }

    public static void sortStack(Stack<Integer> stack) {
        if (stack.isEmpty())
            return;
        int item = stack.pop();
        sortStack(stack);
        insertAtBottom(stack, item);
    }

    public static void insertAtBottom(Stack<Integer> stack, int item) {
        if (stack.isEmpty() || stack.peek() < item) {
            stack.add(item);
            return;
        }
        int temp = stack.pop();
        insertAtBottom(stack, item);
        stack.push(temp);
    }
}
