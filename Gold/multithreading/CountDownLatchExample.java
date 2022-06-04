package com.multithreading;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    public static class ProcessThread implements Runnable {
        CountDownLatch latch;
        long workDuration;
        String name;

        public ProcessThread(CountDownLatch latch, long workDuration, String name) {
            this.latch = latch;
            this.workDuration = workDuration;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(name + " Processing Something for " + workDuration / 1000 + " Seconds");
                Thread.sleep(this.workDuration);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println(name + " completed it's work.");
            //when task finished.. count down the latch count...

            // basically this is same as calling lock object notify(), and object here is latch
            this.latch.countDown();
        }
    }

    public static void main(String[] args) {

        // Main Thread creating latch object with given count
        CountDownLatch countDownLatch = new CountDownLatch(3);

        new Thread(new ProcessThread(countDownLatch, 2000, "Worker1")).start(); // Thread takes 2 seconds to complete
        new Thread(new ProcessThread(countDownLatch, 6000, "Worker2")).start(); // Thread takes 6 seconds to complete
        new Thread(new ProcessThread(countDownLatch, 4000, "Worker3")).start(); // Thread takes 4 seconds to complete

        System.out.println("waiting for Children processes to complete....");
        try {
            //current thread will get notified if all chidren's are done
            // and thread will resume from wait() mode.
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All Process Completed....");

        System.out.println("Parent Thread Resuming work....");
    }
}
