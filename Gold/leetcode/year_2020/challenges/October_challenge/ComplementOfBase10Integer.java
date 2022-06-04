package com.leetcode.year_2020.challenges.October_challenge;

/**
 * @author neeraj on 05/10/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ComplementOfBase10Integer {

    public static void main(String[] args) {
        bitwiseComplement(5);
    }

    public static int bitwiseComplement(int N) {
        StringBuilder str = convertDecimalToBinary(N);
        System.out.println(str.toString());
        int bitwiseComplement = swapAndReturnBitwiseComplement(str);
        System.out.println(bitwiseComplement);
        return bitwiseComplement;
    }

    private static int swapAndReturnBitwiseComplement(StringBuilder str) {
        int power = 0;
        int result = 0;

        for (int i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i) == '0' ? (1 * Math.pow(2, power)) : 0;
            power++;
        }
        return result;
    }

    public static StringBuilder convertDecimalToBinary(int N) {
        StringBuilder str = new StringBuilder();
        int counter = 2;
        while (N > 0) {
            str.append(N % counter);
            N = N / counter;
        }
        return str.reverse();
    }
}
