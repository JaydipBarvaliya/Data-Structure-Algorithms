package com.leetcode.year_2020.stack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author neeraj on 05/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SortQueueIntoAnotherQueueUsingStack {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(5, 1, 2, 3, 4));
        System.out.println(checkIfOneQueueCanBeSortedIntoAnotherQueueUsingStack(queue));

        queue.clear();
        queue.addAll(Arrays.asList(5, 1, 2, 6, 3, 4));
        System.out.println(checkIfOneQueueCanBeSortedIntoAnotherQueueUsingStack(queue));

        queue.clear();
        queue.addAll(Arrays.asList(5, 1, 2, 4, 3));
        System.out.println(checkIfOneQueueCanBeSortedIntoAnotherQueueUsingStack(queue));
    }

    public static boolean checkIfOneQueueCanBeSortedIntoAnotherQueueUsingStack(Queue<Integer> queue) {
        /**
         * We have been given first "n" natural numbers. So we can assume inorder
         * for them to be sorted they will come in order, and if not in order
         * we might use Stack help.
         */
        Stack<Integer> stack = new Stack<>();
        Integer nextItemExpected = 1; // Since we know the sorted will start from 1. in first N natural numbers.
        int totalItemsInQueue = queue.size();
        while (!queue.isEmpty()) {
            Integer poppedItem = queue.poll();

            if (poppedItem == nextItemExpected) {
                nextItemExpected++;
            } else {

                if (stack.isEmpty()) {
                    stack.push(poppedItem);
                } else {

                    if (stack.peek() < poppedItem) {
                        return false; // Since the item on stack is smaller so there is no way we can sort the queue
                        // using just push & pop on stack and enqueue and dequeue on queue1.
                    } else {
                        stack.push(poppedItem);
                    }
                }
            }
        }

        /**
         * Now we might have some items left in the sort
         */
        while (!stack.isEmpty()) {
            if (stack.peek() == nextItemExpected) {
                stack.pop();
                nextItemExpected++;
            } else {
                return false;
            }
        }

        return totalItemsInQueue == nextItemExpected - 1;
    }
}
