package com.company.amazon;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem 159: Amazon Interview Question
 */
public class JumpingNumbersSmallerThanOrEqualToGivenValue {

    public static void main(String[] args) {
        printAllJumpingNumbers(20);
        printAllJumpingNumbers(105);
    }

    /**
     * @param x represent the given value to which we have to print Jumping Numbers
     */
    public static void printAllJumpingNumbers(int x) {
        System.out.print(0 + ","); // 0 is always a jumping number

        for (int i = 1; i <= 9 && i <= x; i++) {
            doBFS(x, i);
        }
        System.out.println();
    }

    /**
     * @param x
     * @param source Starting Node for BFS in graph
     */
    private static void doBFS(int x, int source) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);

        while (!queue.isEmpty()) {

            Integer number = queue.poll();
            if (number <= x) {
                System.out.print(number + ",");
                Integer remainder = number % 10;

                if (remainder == 0) { // Then Add Only the last digit to the Queue
                    queue.add((number * 10) + (remainder + 1));
                } else if (remainder == 9) { // Then Add only the previous digit to the Queue
                    queue.add((number * 10) + (remainder - 1));
                } else { // Else add both digit next and the previous 1
                    queue.add((number * 10) + (remainder + 1));
                    queue.add((number * 10) + (remainder - 1));
                }
            }
        }
    }

}
