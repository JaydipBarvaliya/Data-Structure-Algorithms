package com.datastructures.recursion;

/**
 * @author neeraj on 01/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PermutationWithSpaces {

    public static void main(String[] args) {
        permuteWithSpace("", "ABC");
    }

    public static void permuteWithSpace(String output, String input) {
        if (input.isEmpty()) {
            System.out.println(output);
            return;
        }

        // Not taking the space.
        permuteWithSpace(output + input.charAt(0), input.substring(1));

        if (!output.isEmpty()) {
            // Choosing the Space
            permuteWithSpace(output + "_" + input.charAt(0), input.substring(1));
        }
    }
}
