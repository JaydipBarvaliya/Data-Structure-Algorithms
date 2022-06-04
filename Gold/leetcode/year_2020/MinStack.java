package com.leetcode.year_2020;

import java.util.Stack;

/**
 * @author neeraj on 10/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinStack {

    Stack<Long> stack;
    Long MIN_ELEMENT = Long.MAX_VALUE;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int x) {
        if (MIN_ELEMENT == Integer.MAX_VALUE && stack.isEmpty()) {
            stack.push(Long.valueOf(x));
            MIN_ELEMENT = Long.valueOf(x);
            return;
        }

        if (x < MIN_ELEMENT) {
            stack.push(2l * x - MIN_ELEMENT);
            MIN_ELEMENT = Long.valueOf(x);
        } else {
            stack.push(Long.valueOf(x));
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            if (stack.peek() < MIN_ELEMENT) { //i.e. this was modified
                Long previousMinElement = 2l * MIN_ELEMENT - stack.peek();
                MIN_ELEMENT = previousMinElement;
                System.out.println("Popping " + stack.pop());
            } else {
                stack.pop();
            }
            if (stack.isEmpty()) {
                MIN_ELEMENT = Long.MAX_VALUE;
            }
        }
    }

    public int top() {
        if (!stack.isEmpty()) {
            if (stack.peek() < MIN_ELEMENT) { //i.e. this was modified
                return (int) (long) (MIN_ELEMENT);
            } else {
                return (int) (long) (stack.peek());
            }
        }
        return -1;
    }

    public int getMin() {
        if (!stack.isEmpty()) {
            return (int) (long) (MIN_ELEMENT);
        }
        return -1;
    }

    public static void main(String[] args) {
        /**
         * Your MinStack object will be instantiated and called as such:
         * **/
        /**
         * ["MinStack","push","push","push","top","pop","getMin","pop","getMin","pop","push","top","getMin","push","top","getMin","pop","getMin"]
         * [[],[2147483646],[2147483646],[2147483647],[],[],[],[],[],[],[2147483647],[],[],[-2147483648],[],[],[],[]]
         */
        MinStack obj = new MinStack();
        obj.push(2147483646);
        obj.push(2147483646);
        obj.push(2147483647);

        System.out.println(obj.top());
        obj.pop();
        System.out.println(obj.getMin());
        obj.pop();
        System.out.println(obj.getMin());
        obj.pop();
        obj.push(2147483647);
        System.out.println(obj.top());
        System.out.println(obj.getMin());
        obj.push(-2147483648);
        System.out.println(obj.top());
        System.out.println(obj.getMin());
        obj.pop();
        System.out.println(obj.getMin());

//        obj = new MinStack();
//        obj.push(-1);
//        obj.top();
//        System.out.println(obj.getMin());
    }
}
