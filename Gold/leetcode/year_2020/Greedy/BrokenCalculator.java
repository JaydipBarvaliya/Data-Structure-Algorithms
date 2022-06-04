package com.leetcode.year_2020.Greedy;

/**
 * https://leetcode.com/problems/broken-calculator/
 * @author neeraj on 24/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class BrokenCalculator {

    public static void main(String[] args) {
        System.out.println(brokenCalc(2, 3));
        System.out.println(brokenCalc(5, 8));
        System.out.println(brokenCalc(5, 6));
        System.out.println(brokenCalc(3, 10));
        System.out.println(brokenCalc(1024, 1));
        System.out.println(brokenCalc(1, 1000000000));
    }

    public static int brokenCalc(int X, int Y) {
        /**
         * Intuition behind modifying Y instead of X.
         *
         * If we start from X we don't have any clue whether to subtract 1 from X or to Multiply X.
         * where as we know 1 thing for sure if X > Y...we have to always subtract... so instead of
         * modifying X to become Y...if we can modify Y and make it less than or equal to X.
         *
         * we for sure can calculate the minimum number of operations needed.
         *
         * but from Y we definitely know.... if we have to reach a real number we have to be even to be divided into half.
         * So if Y is odd... we first increment it and then divide into Half. to Reach upto X.
         *
         * So Example :
         *
         * X = 4, Y = 8
         *
         * Since Y is even we can quickly turn into half.
         *
         * X = 4, Y = 9
         * we know Y is not even so we first convert it into even by adding 1
         * Now y = 10... turn into half 10/2 = 5, again 5 is odd so 5+1 = 6
         * then 6 / 2= 3 < 4.
         *
         * So finally we have converted Y < X... now all we need is those many subtractions
         * from X.
         */
        int operation = 0;
        while (X < Y) {
            Y = Y % 2 == 0 ? Y / 2 : Y + 1;
            operation++;
        }
        // We are doing X - Y since, we have decremented Y to a level less than X
        // so we should subtract those many 1's from X.
        return operation + (X - Y);
    }
}
