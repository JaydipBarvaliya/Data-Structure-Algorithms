package com.multithreading;

/**
 * https://stackoverflow.com/questions/18479771/java-multithreading-concept-and-join-method
 *
 * You must understand , threads scheduling is controlled by thread scheduler.So, you cannot guarantee the order of execution of threads under normal circumstances.

However, you can use join() to wait for a thread to complete its work.

For example, in your case

ob1.t.join();
This statement will not return until thread t has finished running.

Try this,

class Demo {
   Thread t = new Thread(
                 new Runnable() {
                     public void run () {
                         //do something
                     }
                  }
    );
    Thread t1 = new Thread(
                 new Runnable() {
                     public void run () {
                         //do something
                     }
                  }
    );
    t.start(); // Line 15
    t.join();  // Line 16
    t1.start();
}
In the above example, your main thread is executing. When it encounters line 15, thread t is available at thread scheduler. As soon as main thread comes to line 16, it will wait for thread t to finish.

NOTE that t.join did not do anything to thread t or to thread t1. It only affected the thread that called it (i.e., the main() thread).
 *
 */
public class JoinExample {
    public static void main(String[] args) throws InterruptedException {
        MyThread1 thread1 = new MyThread1();
        MyThread2 thread2 = new MyThread2();
        MyThread3 thread3 = new MyThread3();
        MyThread4 thread4 = new MyThread4();

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();

        System.out.println("Byeeee from Main Method");
    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread 1" + " is running");
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("=====================MyThread 1" + " is completed=====================");
    }
}

class MyThread2 extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread 2" + " is running");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("=====================MyThread 2" + " is completed=====================");
    }
}

class MyThread3 extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread 3" + " is running");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("=====================MyThread 3" + " is completed=====================");
    }
}

class MyThread4 extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread 4" + " is running");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("=====================MyThread 4" + " is completed=====================");
    }
}
