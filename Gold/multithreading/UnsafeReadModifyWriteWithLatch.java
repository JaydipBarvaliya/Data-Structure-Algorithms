package com.multithreading;

import java.util.concurrent.CountDownLatch;

/**
 * Using Countdown latch to synchronize start of all threads, So that we can reproduce Race Condition
 *
 *
 * Here you can see We might get Output like this [Final number (should be 1_000): 999] only for non-synchronized version
 * where as for synchronized version we will always get  [Final number2 (should be 1_000): 1000]
 */
public class UnsafeReadModifyWriteWithLatch {
    private static Integer NO_OF_THREADS = 1000;
    private int number;
    private int number2;
    private final CountDownLatch startSignal = new CountDownLatch(1);
    private final CountDownLatch endSignal = new CountDownLatch(NO_OF_THREADS);

    private void incrementNumber() {
        number++;
    }

    synchronized private void incrementNumberInSynchronizedManner() {
        number2++;
    }

    private int getNumber() {
        return this.number;
    }

    private int getNumber2() {
        return this.number2;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            test();
        }
    }

    private static void test() throws InterruptedException {
        UnsafeReadModifyWriteWithLatch unsafeReadModifyWriteWithLatch = new UnsafeReadModifyWriteWithLatch();

        for (int i = 0; i < NO_OF_THREADS; i++) {
            new Thread(() -> {
                try {
                    unsafeReadModifyWriteWithLatch.startSignal.await();
                    unsafeReadModifyWriteWithLatch.incrementNumber();
                    unsafeReadModifyWriteWithLatch.incrementNumberInSynchronizedManner();
                } catch (InterruptedException e) {

                } finally {
                    unsafeReadModifyWriteWithLatch.endSignal.countDown();
                }

            }, "T" + i).start();
        }

        Thread.sleep(10);
        unsafeReadModifyWriteWithLatch.startSignal.countDown();
        unsafeReadModifyWriteWithLatch.endSignal.await();
        System.out.println("Final number (should be 1_000): " + unsafeReadModifyWriteWithLatch.getNumber());
        System.out.println("Final number2 (should be 1_000): " + unsafeReadModifyWriteWithLatch.getNumber2());
    }
}
