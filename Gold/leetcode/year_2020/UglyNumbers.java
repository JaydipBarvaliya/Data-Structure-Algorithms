package com.leetcode.year_2020;

import java.util.PriorityQueue;

/**
 * @author neeraj on 28/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class UglyNumbers {

    public static void main(String[] args) {
//        Arrays.asList(8, 15, 6, 35, 7, 4, 32, 24)
//                .forEach(UglyNumbers::isUgly);

        System.out.println(nthUglyNumber(10));
    }

    public static boolean isUgly(int num) {
        int originalNum = num;
        for (int i = 2; i < 6 && num > 0; i++) {
            while (num % i == 0) {
                num = num / i;
            }
        }
        System.out.println("Is " + originalNum + " ugly ? " + (num == 1));
        return num == 1;
    }

    static class NUglyNumberEntry {
        int number;
        int category;

        public NUglyNumberEntry(int number, int category) {
            this.number = number;
            this.category = category;
        }
    }

    public static int nthUglyNumber(int n) {
        PriorityQueue<Long> queue = new PriorityQueue<>();
        queue.add(1L);

        for (int i = 1; i < n; i++) {
            long polled = queue.poll();
            while (!queue.isEmpty() && queue.peek() == polled) {
                queue.poll();
            }
            queue.add(polled * 2);
            queue.add(polled * 3);
            queue.add(polled * 5);
        }
        return queue.poll().intValue();
    }
}
