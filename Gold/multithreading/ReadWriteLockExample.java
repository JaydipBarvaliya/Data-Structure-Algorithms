package com.multithreading;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 2 Users just want to book the seats while
 * the other 2 users wants to just looks at those seats..
 * <p>
 * So we can have multiple readers at any point in time but just 1 writer thread.
 * hence we will use {@link java.util.concurrent.locks.ReadWriteLock}
 *
 * @author neeraj on 25/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ReadWriteLockExample {

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


    private static void readResource() {
        readLock.lock();
        System.out.println("Reading the book....." + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
            System.out.println("Awake from Sleep....." + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    private static void bookTicket() {
        writeLock.lock();
        writeLock.lock();

        System.out.println("Lock Hold Count is " + writeLock.getHoldCount());
        try {
            System.out.println("Booking the Ticket..." + Thread.currentThread().getName());
            Thread.sleep(1000);
            System.out.println("Ticket Booked......." + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> readResource());
        t1.start();
        Thread t2 = new Thread(() -> readResource());
        t2.start();
        Thread t3 = new Thread(() -> bookTicket());
        t3.start();
        Thread t4 = new Thread(() -> bookTicket());
        t4.start();
    }
}
