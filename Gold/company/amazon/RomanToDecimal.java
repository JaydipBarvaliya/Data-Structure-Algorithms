package com.company.amazon;

import java.util.HashMap;
import java.util.Map;

public class RomanToDecimal {

    private static Map<Character, Integer> romanValues = new HashMap<>();

    static {
        romanValues.put('I', 1);
        romanValues.put('V', 5);
        romanValues.put('X', 10);
        romanValues.put('L', 50);
        romanValues.put('C', 100);
        romanValues.put('D', 500);
        romanValues.put('M', 1000);
    }

    public static void main(String[] args) {
        System.out.println("Convert MCMIV into decimal " + convertRomanToDecimal("MCMIV"));
        System.out.println("Convert MMCV into decimal " + convertRomanToDecimal("MMCV"));
    }

    public static int convertRomanToDecimal(String roman) {
        Integer s1, s2, result = 0;
        int romanChars = roman.length();
        for (int i = 0; i < romanChars; i++) {
            s1 = romanValues.get(roman.charAt(i));

            if (i + 1 < romanChars) { // Check if there is next char available.
                s2 = romanValues.get(roman.charAt(i + 1));

                if (s1 >= s2) { // if 1st char is greater then we will simply add 1st one to the result
                    result += s1;
                } else { // Since our first value is less so it must be der to decrease the value of second variable
                    result += s2 - s1;
                    i++; // Since we have already included s2 in calculation so let's skip it
                }

            } else { // If not then just simply add this to the result set
                result += s1;
            }
        }
        return result;
    }
}
