package com.multithreading;

public class CreatingStartingThread {

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I am from Runnable");
        }
    }

    public static class MyThread extends Thread {
        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println("Hello Thread from Extending Thread" + getName());
        }
    }

    public static void mainThreadExample() {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 100000; i++) {
            new Thread("Thread :: " + i) {
                @Override
                public void run() {
                    System.out.println("Thread ::" + getName() + "running");
                }
            }.start();
        }

    }

    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable(), "Runnable Thread");
        Thread thread1 = new MyThread(":: Thread Extends");
        thread.start();
        thread1.start();

        System.out.println(thread.getName());


        mainThreadExample();
    }
}
