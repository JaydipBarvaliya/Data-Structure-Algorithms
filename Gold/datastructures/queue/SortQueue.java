package com.datastructures.queue;

import java.util.LinkedList;
import java.util.Queue;

public class SortQueue {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(5);
        queue.add(1);
        queue.add(4);
        queue.add(2);
        queue.add(3);

        System.out.println("Before Sorting");
        ReverseQueue.printQueue(queue);
        System.out.println("After Sorting");
        sortQueue(queue);
        ReverseQueue.printQueue(queue);
    }

    public static void sortQueue(Queue<Integer> queue) {
        if (!queue.isEmpty()) {
            int temp = queue.poll();
            sortQueue(queue);
            insertAtBottom(queue, temp);
        }
    }

    private static void insertAtBottom(Queue<Integer> queue, int temp) {
        if (queue.isEmpty() || temp > queue.peek()) {
            queue.add(temp);
            return;
        }
        int polled = queue.poll();
        insertAtBottom(queue, temp);
        queue.add(polled);
    }
}
