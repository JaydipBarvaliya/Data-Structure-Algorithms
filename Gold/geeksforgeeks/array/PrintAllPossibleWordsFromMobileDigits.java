package com.geeksforgeeks.array;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintAllPossibleWordsFromMobileDigits {

    private static Map<Character, String> digitStringMap = new HashMap<>();

    static {
        digitStringMap.put('1', "");
        digitStringMap.put('2', "abc");
        digitStringMap.put('3', "def");
        digitStringMap.put('4', "ghi");
        digitStringMap.put('5', "jkl");
        digitStringMap.put('6', "mno");
        digitStringMap.put('7', "pqrs");
        digitStringMap.put('8', "tuv");
        digitStringMap.put('9', "wxyz");
        digitStringMap.put('0', "");
    }

    public static void main(String[] args) {
        printAllPossibleWord("234");
    }

    public static void printAllPossibleWord(String digitInMobile) {
        List<String> RESULT = new ArrayList<>();
        List<String> PRE_RESULT = new ArrayList<>();
        RESULT.add(""); // Hypothetical requirement

        for (int i = 0; i < digitInMobile.length(); i++) { // Traverse the digit

            // Get all letters on this digit
            String letters = digitStringMap.get(digitInMobile.charAt(i));

            if (letters.length() == 0) { // We have to skip the keys in Mobile containing no letters, Such as 1,0,*,#
                continue;
            }
            // Let's append these letters into the words existing in RESULT
            for (String str : RESULT) {

                // Let's add these letter 1 by one
                for (char letter : letters.toCharArray()) {
                    PRE_RESULT.add(str + letter);
                }
            }
            RESULT = PRE_RESULT;
            PRE_RESULT = new ArrayList<>(); // We need fresh result for next digit
        }

        System.out.println(RESULT);
    }
}
