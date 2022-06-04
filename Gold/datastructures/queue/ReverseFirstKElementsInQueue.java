package com.datastructures.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ReverseFirstKElementsInQueue {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        System.out.println("Before Reversing k Elements");
        ReverseQueue.printQueue(queue);
        System.out.println("After Reversing K elements");
        reverseFirstKElements(queue, 3);
    }

    /**
     * 1) Simple add first k elements from queue to Stack
     * 2) Pop from stack and enqueue it back to queue
     * 3) Now just dequeue and enqueue total - k elements back
     * <p>
     * Original Queue
     * ----------------------
     * (Rear)5 4 3 2 1 (Front)
     * -----------------------
     * <p>
     * Let's k = 3
     * Push k elements to stack
     * [3,2,1]
     * <p>
     * <p>
     * Pop from Stack and enqueue it back to Queue
     * <p>
     * ----------
     * 1 2 3 5 4
     * ----------
     * <p>
     * Last Step just dequeue and enqueue total - K elements
     * <p>
     * ---------
     * 5 4 1 2 3
     * ---------
     *
     * @param queue
     * @param k
     */
    public static void reverseFirstKElements(Queue<Integer> queue, int k) {
        Stack<Integer> auxStack = new Stack<>();
        for (int i = 0; i < k; i++) {
            auxStack.add(queue.poll());
        }

        // Pop from Stack and enqueue again
        while (!auxStack.isEmpty()) {
            queue.add(auxStack.pop());
        }

        // Now just dequeue and enqueue it back
        for (int i = 0; i < queue.size() - k; i++) {
            queue.add(queue.poll());
        }
        ReverseQueue.printQueue(queue);
    }
}
