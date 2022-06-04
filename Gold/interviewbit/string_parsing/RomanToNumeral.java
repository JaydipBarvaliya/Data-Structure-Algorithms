package com.interviewbit.string_parsing;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.interviewbit.com/problems/roman-to-integer/
 * <p>
 * Given a string A representing a roman numeral.
 * Convert A into integer.
 * <p>
 * A is guaranteed to be within the range from 1 to 3999.
 * <p>
 * NOTE: Read more
 * details about roman numerals at Roman Numeric System
 * <p>
 * <p>
 * <p>
 * Input Format
 * <p>
 * The only argument given is string A.
 * Output Format
 * <p>
 * Return an integer which is the integer verison of roman numeral string.
 * For Example
 * <p>
 * Input 1:
 * A = "XIV"
 * Output 1:
 * 14
 * <p>
 * Input 2:
 * A = "XX"
 * Output 2:
 * 20
 *
 * @author neeraj on 2019-08-02
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RomanToNumeral {

    final static Map<Character, Integer> romanValue = new HashMap<>();

    static {
        romanValue.put('I', 1);
        romanValue.put('V', 5);
        romanValue.put('X', 10);
        romanValue.put('L', 50);
        romanValue.put('C', 100);
        romanValue.put('D', 500);
        romanValue.put('M', 1000);
    }

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

    public static int romanToInt(String A) {
        int result = 0;
        int current;
        for (int i = 0; i < A.length() - 1; i++) {
            current = romanValue.get(A.charAt(i));

            // If Next Value is greater than the current, then we have to subtract
            if (current < romanValue.get(A.charAt(i + 1))) {
                current = -current; // Since this smaller value has to be subtracted
            }
            result += current;
        }

        // Since in the above loop we didn't consider last digit, let's include that as well.
        result += romanValue.get(A.charAt(A.length() - 1));
        return result;
    }
}
