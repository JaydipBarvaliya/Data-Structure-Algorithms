package com.multithreading;

public class CriticalSectionRaceCondition {

    public void show() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - Number: " + i);
        }
    }

    public static void main(String[] args) {
        CriticalSectionRaceCondition raceCondition = new CriticalSectionRaceCondition();
        Runnable runner = new Runnable() {
            @Override
            public void run() {
                raceCondition.show();
            }
        };

        new Thread(runner, "Thread 1").start();
        new Thread(runner, "Thread 2").start();
    }
}
