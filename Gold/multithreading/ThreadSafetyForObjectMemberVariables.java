package com.multithreading;

public class ThreadSafetyForObjectMemberVariables {

    public static void main(String[] args) throws InterruptedException {
        NotThreadSafe notThreadSafe1 = new NotThreadSafe();
        NotThreadSafe notThreadSafe2 = new NotThreadSafe();

        new Thread(new Runner(notThreadSafe1)).start();
        new Thread(new Runner(notThreadSafe2)).start();

        Thread.sleep(5000);
        System.out.println(notThreadSafe1.builder.toString());
        System.out.println(notThreadSafe2.builder.toString());
    }
}

class Runner implements Runnable {
    private NotThreadSafe notThreadSafe;

    public Runner(NotThreadSafe notThreadSafe) {
        this.notThreadSafe = notThreadSafe;
    }

    @Override
    public void run() {
        this.notThreadSafe.add(Thread.currentThread().getName());
    }
}


class NotThreadSafe {
    StringBuilder builder = new StringBuilder();

    public void add(String text) {
        this.builder.append(text);
    }
}