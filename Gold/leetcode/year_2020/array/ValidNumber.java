package com.leetcode.year_2020.array;

/**
 * https://leetcode.com/problems/valid-number/
 * 65. Valid Number
 */
public class ValidNumber {

    public static void main(String[] args) {

    }

    public static boolean isNumber(String s) {
        boolean numberSeen = false;
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberAfterESeen = false;
        s.trim();
        for (int i = 0; i < s.length(); i++) {
            final char charAtI = s.charAt(i);
            if (charAtI >= 0 && charAtI <= 9) {
                numberSeen = true;
                numberAfterESeen = true;
            } else if (charAtI == '.') {
                if (pointSeen || eSeen) {
                    return false;
                }
                pointSeen = true;
            } else if (charAtI == 'e') {
                if (eSeen || !numberSeen) {
                    return false;
                }
                eSeen = true;
                numberAfterESeen = false;
            } else if (charAtI == '+' || charAtI == '-') {
                if (i != 0 || s.charAt(i - 1) != 'e') {
                    return false;
                }
            }
        }
        return numberAfterESeen && numberSeen;
    }
}
