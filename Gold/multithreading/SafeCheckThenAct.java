package com.multithreading;

/**
 * Since we have synchronized the change number method, hence race condition will not happen
 * and The number will change only once.
 */
public class SafeCheckThenAct {
    private int number;

    synchronized public void changeNumber() {
        if (number == 0) {
            System.out.println(Thread.currentThread().getName() + " | Changed");
            number = -1;
        } else {
            System.out.println(Thread.currentThread().getName() + " | Not Changed");
        }
    }

    public static void main(String[] args) {
        SafeCheckThenAct safeCheckThenAct = new SafeCheckThenAct();

        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    safeCheckThenAct.changeNumber();
                }
            }, "T " + i).start();
        }
    }
}
