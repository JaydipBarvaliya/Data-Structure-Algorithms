package com.geeksforgeeks.stack;

import java.util.Stack;

public class SpecialMinStack extends Stack<Integer> {

    Stack<Integer> minStack = new Stack<>();

    public void push(int item) {
        if (isEmpty()) {
            super.push(item);
            minStack.push(item);
        } else {
            super.push(item);
            if (minStack.peek() > item) {
                minStack.push(item);
            } else {
                minStack.push(minStack.peek());
            }
        }
    }

    public Integer pop() {
        minStack.pop();
        return super.pop();
    }

    public Integer getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        SpecialMinStack specialMinStack = new SpecialMinStack();

        specialMinStack.push(10);
        specialMinStack.push(20);
        specialMinStack.push(30);

        System.out.println("Minimum Element in the Stack is "+specialMinStack.getMin());
        specialMinStack.push(5);
        System.out.println("Minimum Element in the Stack is "+specialMinStack.getMin());
    }
}
