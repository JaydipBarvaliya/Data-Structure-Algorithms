package com.multithreading;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class UnsafeReadModifyWrite {
    private int number;

    public void incrementNumber() {
        number++;
    }

    public int getNumber() {
        return this.number;
    }

    public static void main(String[] args) throws InterruptedException {
        final UnsafeReadModifyWrite unsafeReadModifyWrite = new UnsafeReadModifyWrite();
        int noOfThreads = 1000;
        Thread[] threads = new Thread[noOfThreads];

        for (int i = 0; i < noOfThreads; i++) {
            new Thread(() -> unsafeReadModifyWrite.incrementNumber(), "T " + i).start();
        }

        Thread.sleep(6000);
        System.out.println("Final number (should be 1000): " + unsafeReadModifyWrite.getNumber());

    }
}
