package com.leetcode.year_2020;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neeraj on 17/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RomanToIntegers {

    public static void main(String[] args) {
        System.out.println(romanToInt("XIV"));
        System.out.println(romanToInt("XX"));
        System.out.println(romanToInt("XVV"));
        System.out.println(romanToInt("XV"));
        System.out.println(romanToInt("MDCL"));
        System.out.println(romanToInt("MMMCCL"));
        System.out.println(romanToInt("MMMCCLXIV"));
        System.out.println(romanToInt("MMMCCXLIV"));
        System.out.println(romanToInt("IX"));
    }

    public static int romanToInt(String s) {
        Map<Character, Integer> symbolToValue = new HashMap<>();
        symbolToValue.put('I', 1);
        symbolToValue.put('V', 5);
        symbolToValue.put('X', 10);
        symbolToValue.put('L', 50);
        symbolToValue.put('C', 100);
        symbolToValue.put('D', 500);
        symbolToValue.put('M', 1000);

        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = symbolToValue.get(s.charAt(i));

            if (i + 1 < s.length()) {
                int nextChar = symbolToValue.get(s.charAt(i + 1));

                if (c >= nextChar) {
                    res += c;
                } else {
                    res += nextChar - c;
                    i++;
                }

            } else {
                res += c;
            }
        }
        return res;
    }
}
