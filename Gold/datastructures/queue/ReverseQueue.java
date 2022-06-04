package com.datastructures.queue;

import java.util.LinkedList;
import java.util.Queue;

public class ReverseQueue {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        System.out.println("Before Reversing");
        printQueue(queue);
        System.out.println("After Reversing");
        reverseQueueUsingRecursion(queue);
        printQueue(queue);
    }

    public static void reverseQueueUsingRecursion(Queue<Integer> queue) {
        if (!queue.isEmpty()) {
            int temp = queue.poll();
            reverseQueueUsingRecursion(queue);

            queue.add(temp);
        }
    }

    public static void reverseQueue(Queue<Integer> queue) {
        if (!queue.isEmpty()) {
            int popped = queue.poll();
            reverseQueue(queue);
            insertAtTop(queue, popped);
        }
    }

    private static void insertAtTop(Queue<Integer> queue, int temp) {
        queue.add(temp);
    }

    public static void printQueue(Queue<Integer> queue) {
        for (Integer item : queue) {
            System.out.print(item + ",");
        }
        System.out.println();
    }
}
