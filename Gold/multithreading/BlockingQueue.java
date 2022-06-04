package com.multithreading;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author neeraj on 25/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class BlockingQueue<E> {

    private Queue<E> queue;
    private int capacity;
    private ReentrantLock reentrantLock = new ReentrantLock(true);
    private Condition notEmpty = reentrantLock.newCondition();
    private Condition notFull = reentrantLock.newCondition();

    public BlockingQueue(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public void put(E e) {
        reentrantLock.lock();
        try {
            if (queue.size() == capacity) {
                notFull.await(); // Waiting for someone to say queue is not full anymore
            }
            System.out.println(Thread.currentThread().getName() + " is adding " + e + " to Queue....");
            Thread.sleep(1000);
            queue.add(e);
            notEmpty.signalAll();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public E takeOut() {
        reentrantLock.lock();
        try {
            while (queue.size() == 0) {
                notEmpty.await(); // Waiting for someone to say queue is not empty anymore
            }
            notFull.signalAll();
            E e = queue.poll();
            System.out.println(Thread.currentThread().getName() + " polled " + e);
//            Thread.sleep(1000);
            return queue.poll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        throw new RuntimeException("This should not happen...");
    }

    public static void main(String[] args) {
        BlockingQueue<Date> blockingQueue = new BlockingQueue<Date>(3);
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        executorService.submit(() -> {
            while (true) {
                blockingQueue.put(new Date());
            }
        });

        executorService.submit(() -> {
            while (true) {
                blockingQueue.takeOut();
            }
        });
    }
}
