package com.leetcode.problems.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neeraj on 01/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LetterCombinationPhoneNumber_17 {

    private static String[] MAPPINGS = {
            "0",
            "1",
            "ABC",  // Represent Digit 2 in Phone Number
            "DEF",  // Represent Digit 3 in Phone Number
            "GHI",  // Represent Digit 4 in Phone Number
            "JKL",  // Represent Digit 5 in Phone Number
            "MNO",  // Represent Digit 6 in Phone Number
            "PQRS", // Represent Digit 7 in Phone Number
            "TUV",  // Represent Digit 8 in Phone Number
            "WXYZ"  // Represent Digit 9 in Phone Number
    };

    public static void main(String[] args) {
        letterCombinations("23");
    }

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        combination(0, digits, "", result);
        System.out.println(result);
        return result;
    }

    public static void combination(int counter,
                                   String digits,
                                   String currentCombination,
                                   List<String> result) {

        if (counter == digits.length()) {
            result.add(currentCombination);
            return;
        }

        String mappingAtCounter = MAPPINGS[digits.charAt(counter) - '0'];
        for (int i = 0; i < mappingAtCounter.length(); i++) {
            currentCombination = currentCombination.concat("" + mappingAtCounter.charAt(i));
            combination(counter + 1, digits, currentCombination, result);

            currentCombination = new StringBuffer(currentCombination)
                    .deleteCharAt(currentCombination.length() - 1).toString();
        }
    }


}
