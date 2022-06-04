package com.interviewbit.binary_search;

import com.util.LogUtil;

/**
 * Implement int sqrt(int x).
 * <p>
 * Compute and return the square root of x.
 * <p>
 * If x is not a perfect square, return floor(sqrt(x))
 * <p>
 * Example :
 * <p>
 * Input : 11
 * Output : 3
 *
 * @author neeraj on 2019-07-29
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SquareRootOfInteger {

    public static void main(String[] args) {
//        for(int number=1;number<=1000;number++) {
//            nearestSqrt = number;
//            LogUtil.logIt("Square Root of " + number + " from \n library function ==>  " +
//                    (int) Math.floor(Math.sqrt(number)) + " and from our custom function ==> " + sqrt(number));
//        }

        int number = 2147483647;
//        System.out.println(Math.sqrt(number));
        LogUtil.logIt("Square Root of " + number + " from \n library function ==>  " +
                (int) Math.floor(Math.sqrt(number)) + " and from our custom function ==> " + sqrt(number));


    }

    public static int sqrt(int a) {
        return binarySearch(a, 1, a);
    }

    static int nearestSqrt = -1;

    /**
     * Here instead of doing mid*mid == numberWeAreSearchingFor
     * we are doing mid = number/mid (Mathematically both are equivalent, but first one will cause StackOverflow error).
     */
    public static int binarySearch(int numberWhoseSquareRootIsBeingSearched, int low, int high) {
        if (low <= high) {
            int mid = low + (high - low) / 2;

            if (mid == numberWhoseSquareRootIsBeingSearched / mid) {
                return mid;
            } else if (mid > numberWhoseSquareRootIsBeingSearched / mid) {
                return binarySearch(numberWhoseSquareRootIsBeingSearched, low, mid - 1);
            } else {
                nearestSqrt = mid;
                return binarySearch(numberWhoseSquareRootIsBeingSearched, mid + 1, high);
            }
        }
        return nearestSqrt;
    }
}
