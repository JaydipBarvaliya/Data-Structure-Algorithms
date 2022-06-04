package com.multithreading;

/**
 * In this example it was expected to change the number field only once
 * but due to Race condition it end up changing any number of times
 */
public class UnsafeCheckThenAct {
    private int number;

    public void changeNumber() {
        if (number == 0) {
            System.out.println(Thread.currentThread().getName() + " | Changed");
            number = -1;
        } else {
            System.out.println(Thread.currentThread().getName() + " | Not Changed");
        }
    }

    public static void main(String[] args) {
        UnsafeCheckThenAct unsafeCheckThenAct = new UnsafeCheckThenAct();

        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    unsafeCheckThenAct.changeNumber();
                }
            }, "T " + i).start();
        }
    }
}
