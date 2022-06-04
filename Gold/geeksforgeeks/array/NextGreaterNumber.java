package com.geeksforgeeks.array;

import java.util.Stack;

/**
 * [4, 5, 2, 25}
 * Element       NGE
 * 4      -->   5
 * 5      -->   25
 * 2      -->   25
 * 25     -->   -1
 */
public class NextGreaterNumber {

    public static void main(String[] args) {
//        printNextgreaterNumber(new int[]{4, 5, 2, 25});
        printNextGreaterElement(new int[]{13, 7, 6, 12});
    }

    public static void printNextgreaterNumber(int[] arr) {
        Stack<Integer> stack = new Stack<>();

        // Push first item
        stack.push(arr[0]);

        int nextElement = arr[1];
        int counter = 1;
        while (!stack.isEmpty()) {

            while (stack.peek() < nextElement) {
                System.out.println(stack.pop() + " :: " + nextElement);
                if (stack.isEmpty())
                    break;
            }
            counter = counter + 1;
            stack.push(nextElement);
            if (counter >= arr.length) {
                break;
            }
            nextElement = arr[counter];

        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop() + " :: " + -1);
        }
    }

    public static void printNextGreaterElement(int[] arr) {

        Stack<Integer> stack = new Stack<>();
        stack.push(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] > stack.peek()) {
                System.out.println(stack.pop() + " :: " + arr[i]);
            }
            stack.push(arr[i]);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop() + " :: " + -1);
        }
    }
}
