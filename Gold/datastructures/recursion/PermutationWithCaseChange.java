package com.datastructures.recursion;

/**
 * In this only small-case input is mandatory.
 *
 * @author neeraj on 02/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PermutationWithCaseChange {

    public static void main(String[] args) {
        permuteWithCase("", "ab");
        permuteWithCase("", "abc");
        permuteWithCase("", "abcd");
    }

    public static void permuteWithCase(String output, String input) {
        if (input.isEmpty()) {
            System.out.println(output);
            return;
        }

        // Taking just small
        permuteWithCase(output + input.charAt(0), input.substring(1));

        // Taking capital
        permuteWithCase(output + (char) (input.charAt(0) - 32), input.substring(1));
    }
}
