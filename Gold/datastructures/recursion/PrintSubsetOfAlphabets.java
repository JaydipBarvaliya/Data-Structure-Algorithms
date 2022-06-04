package com.datastructures.recursion;

import java.util.HashSet;
import java.util.Set;

/**
 * @author neeraj on 28/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PrintSubsetOfAlphabets {

    public static void main(String[] args) {
        subset("AB", "", new HashSet<>());
        subset("ABC", "", new HashSet<>());
        subset("AAB", "", new HashSet<>());
        subset("AABBCC", "", new HashSet<>());
    }

    public static void subset(String input, String output, Set<String> allSubsets) {
        if (input.length() == 0) {
            if (allSubsets.add(output)) {
                System.out.println(output);
            }
            return;
        }

        subset(input.substring(1), output, allSubsets); // Not Choosing 1st Character
        subset(input.substring(1), output + input.charAt(0), allSubsets); // Choosing the 1st Character
    }
}
