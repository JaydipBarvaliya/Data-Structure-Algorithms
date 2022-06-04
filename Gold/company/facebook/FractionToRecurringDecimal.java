package com.company.facebook;

import java.util.HashMap;
import java.util.Map;

public class FractionToRecurringDecimal {

    public static void main(String[] args) {
        System.out.println(fractionToDecimal(4, 333));
        System.out.println(fractionToDecimal(1, 6));
        System.out.println(fractionToDecimal(-1, 6));
        System.out.println(fractionToDecimal(-22, -11));
    }

    public static String fractionToDecimal(int numerator, int denominator) {
        // We know one thing
        // when number is not perfectly divisible we introduce a decimal point and do numerator * 10, this is exactly what we will do now.
        if (numerator == 0) return "0";

        // Check for sign
        final String sign = (numerator > 0) ^ (denominator > 0) ? "-" : ""; // X-OR operator only when one is negative we use negative sign
        final long num = Math.abs((long) numerator), den = Math.abs((long) denominator);

        return sign + num / den + fraction(num % den, den);
    }

    private static String fraction(long remainder, long denominator) {
        if (remainder == 0) return ""; // when number is perfectly divisible

        StringBuilder result = new StringBuilder();
        result.append(".");
        final Map<Long, Integer> remainderIndexMap = new HashMap<>();
        remainderIndexMap.put(remainder, result.length());

        while (remainder != 0) {
            remainder *= 10; // We spoke about it if number is not divisible introduce decimal and multiply by 10

            result.append(remainder / denominator);
            remainder %= denominator;

            if (remainderIndexMap.containsKey(remainder)) {
                // So finally we found a repeating remainder
                // Insert brackets
                result.insert(remainderIndexMap.get(remainder), "(");
                result.append(")");
                break;
            }
            remainderIndexMap.put(remainder, result.length());
        }
        return result.toString();
    }

}
