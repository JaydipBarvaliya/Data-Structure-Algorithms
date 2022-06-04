package com.geeksforgeeks.stack;

import java.util.Stack;

public class SpecialMinStackO_1_SpaceComplexity {

    private static Stack<Integer> stack = new Stack<>();
    private static Integer MIN_ELEMENT;

    public static void main(String[] args) {
//        push(20);
//        push(10);
//        push(30);

        push(-2);
        push(0);
        push(-3);

        System.out.println("Minimum Element in the Stack is " + MIN_ELEMENT);
        System.out.println("After Removing " + pop());
        System.out.println("Minimum Element in the Stack is " + MIN_ELEMENT);
        System.out.println("After Removing " + pop());
        System.out.println("Minimum Element in the Stack is " + MIN_ELEMENT);
    }


    public static void push(int x) {
        if (stack.isEmpty()) {
            stack.push(x);
            MIN_ELEMENT = x;
        } else {
            if (x < MIN_ELEMENT) { // We found a new MIN_ELEMENT
                stack.push(2 * x - MIN_ELEMENT);
                MIN_ELEMENT = x;
            } else {
                stack.push(x);
            }
        }
        System.out.println("Number Inserted: " + x);
    }

    public static Integer pop() {
        Integer temp = stack.pop();

        // i.e this element has a role in creating MIN_ELEMENT, since it's going now we have to say goodbye and revert
        // to the previous MIN_ELEMENT, before operation (2*X - MIN_ELEMENT) = Y (element to be pushed into the Stack)
        // So now our new MIN_ELEMENT will be = 2*Y - MIN_ELEMENT
        if (temp < MIN_ELEMENT) {
            MIN_ELEMENT = 2 * MIN_ELEMENT - temp;
        }
        return temp;
    }
}
