package com.geeksforgeeks.stack;

import java.util.Stack;

public class NextGreaterElement {

    public static void main(String[] args) {
        printNextGreaterElement(new int[]{4, 5, 2, 25});
    }

    public static void printNextGreaterElement(int[] input) {
        Stack<Integer> stack = new Stack<>();
        stack.push(input[0]);

        for (int i = 1; i < input.length; i++) {
            while (!stack.isEmpty() && stack.peek() < input[i]) {
                System.out.println(stack.pop() + " ::-->" + input[i]);
            }
            stack.push(input[i]);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop() + " ::-->" + -1);
        }
    }
}
