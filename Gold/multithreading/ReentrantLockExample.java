package com.multithreading;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Example when multiple threads are trying to book the ticket at the same time
 * you need to use {@link ReentrantLock}
 *
 * @author neeraj on 25/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ReentrantLockExample {

    private static ReentrantLock lock = new ReentrantLock();

    private static void bookTicket() {
        lock.lock();
        try {
            System.out.println("Booking the Ticket..." + Thread.currentThread().getName());
            Thread.sleep(1000);
            System.out.println("Ticket Booked......." + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> bookTicket());
        t1.start();
        Thread t2 = new Thread(() -> bookTicket());
        t2.start();
        Thread t3 = new Thread(() -> bookTicket());
        t3.start();
        Thread t4 = new Thread(() -> bookTicket());
        t4.start();
    }

}
