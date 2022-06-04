package com.interviewbit.string_parsing;

import static com.util.LogUtil.logIt;

/**
 * https://www.interviewbit.com/problems/integer-to-roman/
 *
 * @author neeraj on 2019-08-03
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NumeralToRoman {


    // Since maximum it goes upto 3999, hence we are specifying
    // 1000s upto 3000 range
    // 100s upto 900 range
    // 10s upto 90 range
    // 1s upto 9 range
    final static String[] M = {"", "M", "MM", "MMM"}; // 1000s
    final static String[] C = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"}; // 100s
    final static String[] X = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"}; // 10s
    final static String[] I = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}; // 1s

    public static void main(String[] args) {

        convert(1650);
        convert(3250);
        convert(3254);
        convert(3244);
        convert(3264);

        convert(44);
        convert(45);
    }

    public static void convert(int number) {
        logIt("[" + number + "] ==> " + numeralToRoman(number));
    }

    public static String numeralToRoman(int number) {
        String thousand = M[number / 1000];
        String hundreds = C[(number % 1000) / 100];
        String tens = X[(number % 100) / 10];
        String ones = I[(number % 10)];
        return thousand + hundreds + tens + ones;
    }
}
