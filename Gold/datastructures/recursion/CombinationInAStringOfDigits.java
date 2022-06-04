package com.datastructures.recursion;

/**
 * https://www.geeksforgeeks.org/combinations-string-digits/
 *
 * @author neeraj on 10/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CombinationInAStringOfDigits {

    public static void main(String[] args) {
        combination("1234");
    }

    static int invoked = 0;
    public static void combination(String digit) {
        permute(digit, 0, "");
        System.out.println("Invoked upto "+ invoked);
    }

    private static void permute(String digit, int pointer, String curr) {
        invoked++;
        if (pointer == digit.length()) {
            System.out.println(curr);
            return;
        }

        for (int i = pointer; i < digit.length(); i++) {
            String substring = digit.substring(pointer, i + 1);
            permute(digit, i + 1, curr + " " + substring);
        }
    }
}
