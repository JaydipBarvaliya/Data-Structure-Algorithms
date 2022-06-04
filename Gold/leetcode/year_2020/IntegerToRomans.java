package com.leetcode.year_2020;

/**
 * https://leetcode.com/problems/integer-to-roman/
 * https://www.youtube.com/watch?v=LBsvAwQbVdw
 *
 * @author neeraj on 17/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class IntegerToRomans {

    public static void main(String[] args) {
        System.out.println(intToRoman(3999));
        System.out.println(intToRoman(3));
        System.out.println(intToRoman(4));
        System.out.println(intToRoman(9));
        System.out.println(intToRoman(58));
        System.out.println(intToRoman(1994));
    }


    public static String intToRoman(int num) {
        if (num > 3999 || num <= 0) return null;
        /**
         * So we know apart from normal roman numbers we have 6 special cases where we have to do the subtraction to
         * represent numbers.
         *
         * which are {IV,IX, XL, XC, CD, CM}
         *           {4, 9,  40, 90, 400, 900}
         *
         * Now we can put these subtracted mappings also with the actual mapping to make our life easier.
         *
         * Also we can't put more than 3 consecutive romans together so the max we can achieve is just 3999 which is "MMMCMXCIX"
         *
         */
        // Putting the numerals from the highest order
        String[] numerals = new String[]
                {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = new int[]
                {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        int counter = 0; // This counter will run on these values starting from highest value(but least index) and keep growing
        StringBuilder result = new StringBuilder();
        while (num > 0) {
            if (num - values[counter] >= 0) {
                num -= values[counter];
                result.append(numerals[counter]);
            } else {
                counter++; // Increment the counter and subtract a less value
            }
        }
        return result.toString();
    }
}
