package com.leetcode.covid19;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/happy-number/
 *
 * @author neeraj on 02/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class HappyNumber {

    public static void main(String[] args) {
        System.out.println(isHappy(19));
        System.out.println(isHappy(16));
        System.out.println(isHappy(2));
        System.out.println(isHappy(13));
    }

    static Map<Integer, Integer> preCalculated = new HashMap<>();

    public static boolean isHappy(int n) {
        preCalculated = new HashMap<>();
        int temp = n;
        while (true) {
            temp = getSumOfSquareOfDigits(temp);
//            System.out.println(temp);
            if (temp == 1) return true;
            if (preCalculated.containsKey(temp)) {
                return false;
            }
            preCalculated.put(temp, temp);
        }
    }

    public static int getSumOfSquareOfDigits(int num) {
        int rem = 0;
        while (num > 0) {
            rem += (int) Math.pow(num % 10, 2);
            num = num / 10;
        }
        return rem;
    }


}
