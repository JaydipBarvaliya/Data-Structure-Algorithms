package com.company.google;

/**
 * Google Interview Problem.
 * <p>
 * Wildcards
 * Have the function wildcards(str) read str which will contain two strings separated by a space
 * The first string will consist of the following sets of characters: +, $, and {N} which is optional
 * '+' character represents a single alphabetic character,
 * '$' character represents a number between 1-9,
 * asterisk '*' represents a sequence of the same character of length 3 unless it is followed by
 * ({N} which represents how many characters should appear in the sequence where N will be at least 1.
 * <p>
 * Your goal is to determine if the second string exactly matches the pattern of the first string in the input
 * <p>
 * For example if str is '++*{5} jtggggg" then the second string in this case does match the pattern,
 * so your program should return the string true If the second string does not match the pattern your program should
 * return the string false.
 * Examples
 * <p>
 * input: "+++++* abcdehhhhh" Output: false
 * Input: "$**+*{2} 9mmmrrrkbb" Output: true
 * Browse Resources powered by Google
 *
 * @author neeraj on 12/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class WildCards {

    public static void main(String[] args) {
        System.out.println(isValidWildcard("++*{5} jtggggg"));
        System.out.println(isValidWildcard("$**+*{2} 9mmmrrrkbb"));
        System.out.println(isValidWildcard("+++++* abcdehhhhh"));
    }

    public static boolean isValidWildcard(String input) {
        String[] splitInput = input.split(" ");
        String s1 = splitInput[0];
        String s2 = splitInput[1];

        int t1 = 0;
        int t2 = 0;
        char charAtT1;
        char charAtT2;

        while (t1 < s1.length() && t2 < s2.length()) {
            charAtT1 = s1.charAt(t1);
            charAtT2 = s2.charAt(t2);
            if (charAtT1 == '+') {
                if (charAtT2 >= 91 && charAtT2 <= 122) {
                    t1++;
                    t2++;
                } else {
                    return false; // Not a alphabet match.
                }
            } else if (charAtT1 == '$') {
                if (charAtT2 - '0' >= 1 && charAtT2 - '0' <= 9) {
                    t1++;
                    t2++;
                } else {
                    return false; // Not a numeric match.
                }
            } else if (charAtT1 == '*') {
                Character previousCharacter = null;
                int loopUpto;
                if (t1 + 1 == s1.length()) {
                    loopUpto = 3;
                    t1 = t1 + 1;
                } else if (s1.charAt(t1 + 1) == '{') {
                    loopUpto = s1.charAt(t1 + 2) - '0';
                    t1 = t1 + 4;
                } else {
                    loopUpto = 3;
                    t1 = t1 + 1;
                }

                // Check if s2 has that much length
                if (t2 + loopUpto > s2.length()) return false;
                while (loopUpto-- > 0) {
                    if (previousCharacter == null) {
                        previousCharacter = s2.charAt(++t2);
                    } else {
                        char charAtNewT2 = s2.charAt(t2);
                        if (previousCharacter != charAtNewT2) return false;
                        t2++;
                    }
                }
            }
        }
        if (t1 == s1.length() && t2 == s2.length()) return true;
        return false;
    }
}
