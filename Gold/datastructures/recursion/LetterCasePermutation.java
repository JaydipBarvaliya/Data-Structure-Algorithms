package com.datastructures.recursion;

/**
 * @author neeraj on 02/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LetterCasePermutation {

    public static void main(String[] args) {
        permute("", "a1B2");
    }

    public static void permute(String output, String input) {
        if (input == null || input.equals("")) {
            System.out.println(output);
            return;
        }

        char itemAtFirst = input.charAt(0);

        if (isAlphabet(itemAtFirst)) {
            // Convert to Small
            permute(output + convertCase(itemAtFirst, false), input.substring(1));

            // Convert to Capital
            permute(output + convertCase(itemAtFirst, true), input.substring(1));
        } else {
            permute(output + itemAtFirst, input.substring(1));
        }
    }

    private static char convertCase(char itemAtFirst, boolean convertToCapital) {
        if (convertToCapital) {
            if (itemAtFirst >= 97 && itemAtFirst <= 122) {
                return (char) (itemAtFirst - 32);
            } else {
                return itemAtFirst;
            }
        } else {
            if (itemAtFirst >= 65 && itemAtFirst <= 90) {
                return (char) (itemAtFirst + 32);
            } else {
                return itemAtFirst;
            }
        }
    }

    private static boolean  isAlphabet(char itemAtFirst) {
        return (itemAtFirst >= 65 && itemAtFirst <= 90)
                || (itemAtFirst >= 97 && itemAtFirst <= 122);
    }
}
