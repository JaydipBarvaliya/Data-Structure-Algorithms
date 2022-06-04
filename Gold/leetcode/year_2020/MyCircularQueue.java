package com.leetcode.year_2020;

/**
 * @author neeraj on 22/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MyCircularQueue {

    int[] arr;
    int[] maxArr;
    int front = -1;
    int rear = -1;
    int size = 0;

    /**
     * Initialize your data structure here. Set the size of the queue to be k.
     */
    public MyCircularQueue(int k) {
        arr = new int[k];
    }

    /**
     * Insert an element into the circular queue. Return true if the operation is successful.
     */
    public boolean enQueue(int value) {
        if (isFull()) return false;
        if (isEmpty()) front = front + 1;
        rear = (rear + 1) % arr.length;
        arr[rear] = value;
        size++;
        return true;
    }

    /**
     * Delete an element from the circular queue. Return true if the operation is successful.
     */
    public boolean deQueue() {
        if (isEmpty()) return false;
        front = (front + 1) % arr.length;
        size--;
        if (size == 0) {
            front = -1;
            rear = -1;
        }
        return true;
    }

    /**
     * Get the front item from the queue.
     */
    public int Front() {
        if (front == -1) return -1;
        return arr[front];
    }

    /**
     * Get the last item from the queue.
     */
    public int Rear() {
        if (rear == -1) return -1;
        return arr[rear];
    }

    /**
     * Checks whether the circular queue is empty or not.
     */
    public boolean isEmpty() {
        return size <= 0;
    }

    /**
     * Checks whether the circular queue is full or not.
     */
    public boolean isFull() {
        return size == arr.length;
    }

    public static void main(String[] args) {

        //["MyCircularQueue","enQueue","Rear","Rear","deQueue","enQueue",
        // "Rear","deQueue","Front","deQueue","deQueue","deQueue"]
        //[[6],[6],[],[],[],[5],[],[],[],[],[],[]]
        MyCircularQueue queue = new MyCircularQueue(6);
        System.out.println(queue.enQueue(6));
        System.out.println(queue.Rear());
        System.out.println(queue.Rear());
        System.out.println(queue.deQueue());
        System.out.println(queue.enQueue(5));

        System.out.println(queue.Rear());
        System.out.println(queue.deQueue());
        System.out.println(queue.Front());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
    }
}
