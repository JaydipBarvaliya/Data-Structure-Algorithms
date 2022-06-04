package com.leetcode.year_2020.array;

/**
 * @author neeraj on 29/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ValidSquare {

    public static void main(String[] args) {
        System.out.println(validSquare(
                new int[]{10, 10},
                new int[]{10, 20},
                new int[]{20, 20},
                new int[]{20, 10}
        ));

        System.out.println(validSquare(
                new int[]{0, 0},
                new int[]{1, 1},
                new int[]{1, 0},
                new int[]{0, 1}
        ));
    }

    public static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        /**
         * So for points to be a square we know that all sides are equal
         * and also left-diagonal and right-diagonal are equal.
         *
         * Distance = √(x2-x1)^2 + (y2-y1)^2 (We will calculate the same for both left and right diagonal and compare them
         *
         */
        int distance1 = distanceBetweenPoints(p1, p2);
        if (distance1 == 0 || distance1 != distanceBetweenPoints(p3, p4)) return false;

        int distance2 = distanceBetweenPoints(p1, p3);
        if (distance2 == 0 || distance2 != distanceBetweenPoints(p2, p4)) return false;

        int distance3 = distanceBetweenPoints(p1, p4);
        if (distance3 == 0 || distance3 != distanceBetweenPoints(p2, p3)) return false;

        if (distance1 == distance2 || distance1 == distance3 || distance2 == distance3) return true;

        return false;
    }

    private static int distanceBetweenPoints(int[] T, int[] R) {
        /**
         *
         *    Q//==================R
         *    //                 //
         *   //                 //
         *  //                 //
         * T===================S
         */
        return ((R[0] - T[0]) * (R[0] - T[0])) + ((R[1] - T[1]) * (R[1] - T[1]));
    }

}
