package com.company.amazon;

public class ExtractMaximumValueInString {

    public static void main(String[] args) {
        System.out.println(getMaximumValue("100klh564abc365bg"));
        System.out.println(getMaximumValue("abchsd0sdhs"));
    }

    public static int getMaximumValue(String str) {
        char[] input = str.toCharArray();
        int MAX_VALUE = Integer.MIN_VALUE;
        StringBuffer val = new StringBuffer();

        for (int i = 0; i < input.length; i++) {
            int asciiVal = input[i];
            if (asciiVal >= 48 && asciiVal <= 57) {
                val.append(input[i]);
            } else {
                int parsedVal  = 0;
                try {
                    parsedVal = Integer.parseInt(val.toString());
                } catch (Exception e) {

                }
                if (parsedVal > MAX_VALUE) {
                    MAX_VALUE = parsedVal;
                }
                val = new StringBuffer();
            }
        }
        return MAX_VALUE;
    }
}
