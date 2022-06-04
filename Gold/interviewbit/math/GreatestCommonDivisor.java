package com.interviewbit.math;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neeraj on 2019-07-27
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class GreatestCommonDivisor {

    public static void main(String[] args) {
        System.out.println(gcd(36, 60));
        System.out.println(gcd(20, 28));
        System.out.println(gcd(98, 56));

        findGCDUsingEuclideanAlgorithm(36,60);
        findGCDUsingEuclideanAlgorithm(20,28);
        findGCDUsingEuclideanAlgorithm(98, 56);
    }

    public static int gcd(int number1, int number2) {
        LogUtil.logIt("GCD of " + number1 + " and " + number2);
        List<Integer> factors1 = new ArrayList<>();
        List<Integer> factors2 = new ArrayList<>();

        addFactorsOfNumber(number1, factors1);
        addFactorsOfNumber(number2, factors2);

        int counter = 0;
        int gcd = 1;

        List<Integer> moreFactors = factors1.size() > factors2.size() ? factors1 : factors2;
        List<Integer> fewOrEqualFactors = factors1.size() <= factors2.size() ? factors1 : factors2;

        while (counter < fewOrEqualFactors.size()) {
            if (moreFactors.contains(fewOrEqualFactors.get(counter))) {
                moreFactors.remove(fewOrEqualFactors.get(counter));
                gcd *= fewOrEqualFactors.get(counter);
            }
            counter++;
        }
        return gcd;
    }

    public static void addFactorsOfNumber(int number, List<Integer> factors) {
        int temp = number;
        for (int i = 2; i < Math.sqrt(temp); i++) {
            while (number > 0) {
                // Completely Divisible
                if (number % i == 0) {
                    factors.add(i);
                    number = number / i;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * The algorithm is based on below facts.
     * <p>
     * If we subtract smaller number from larger (we reduce larger number), GCD doesn’t change.
     * So if we keep subtracting repeatedly the larger of two, we end up with GCD.
     *
     * @param number1
     * @param number2
     */
    public static void findGCDUsingEuclideanAlgorithm(int number1, int number2) {
        LogUtil.logIt("GCD of " + number1 + " and " + number2 + " is " + gcdWithEuclied(number1, number2));
    }

    private static int gcdWithEuclied(int number1, int number2) {
        if (number1 == 0)
            return number2;
        if (number2 == 0)
            return number1;
        if (number1 == number2)
            return number1;

        if (number1 > number2)
            return gcdWithEuclied(number1 - number2, number2);
        else
            return gcdWithEuclied(number1, number2 - number1);
    }
}
