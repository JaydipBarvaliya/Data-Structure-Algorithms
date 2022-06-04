package com.datastructures.queue;

/**
 * Created by jaine03 on 13/07/17.
 */
public class QueueUtil {

    public int front, rear, capacity, size;
    public int[] array;

    public QueueUtil(int capacity) {
        this.capacity = capacity;
        this.front = 0;
        this.rear = capacity - 1;
        array = new int[this.capacity];
    }

    public Boolean isFull() {
        return this.size == this.capacity;
    }

    public Boolean isEmpty() {
        return this.size == 0;
    }

    public void enque(int n) {
        if (isFull())
            return;
        this.rear = (this.rear + 1) % this.capacity;
        array[this.rear] = n;
        this.size = this.size + 1;
        System.out.println(n + "Enque to Queue");
    }

    public void deque() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        int data = array[front];
        this.front = (this.front + 1) % this.capacity;
        this.size = this.size - 1;
        System.out.println(data + "deque from queue");
    }

    public static void main(String[] args) {
        QueueUtil util = new QueueUtil(1000);
        util.enque(10);
        util.enque(20);
        util.enque(30);
        util.enque(40);
        util.enque(50);

        util.deque();
        util.deque();
        util.deque();
        util.deque();
        util.deque();
        util.deque();
        util.deque();
        util.deque();
        util.deque();
        util.deque();
        util.enque(100);
        util.enque(23200);
        util.deque();
    }
}
