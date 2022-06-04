package com.geeksforgeeks.string;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        System.out.println(getLongestPalindromicSubstring("forgeeksskeegfor"));
    }

    public static String getLongestPalindromicSubstring(String str) {
        char[] charArr = str.toCharArray();
        StringBuilder builder = new StringBuilder();
        int lowIndex = 0, highIndex = 1;
        int maxLength = 0, start = 0;
        for (int i = 1; i < charArr.length; i++) {

            lowIndex = i - 1;
            highIndex = i;
            // For Even String
            while (lowIndex >= 0 && highIndex < charArr.length && (charArr[lowIndex] == charArr[highIndex])) {
                if (highIndex - lowIndex + 1 > maxLength) {
                    start = lowIndex;
                    maxLength = highIndex - lowIndex + 1;
                }
                --lowIndex;
                ++highIndex;
            }

            lowIndex = i - 1;
            highIndex = i + 1;
            // For Odd String
            while (lowIndex >= 0 && highIndex < charArr.length && (charArr[lowIndex] == charArr[highIndex])) {
                if (highIndex - lowIndex + 1 > maxLength) {
                    start = lowIndex;
                    maxLength = highIndex - lowIndex + 1;
                }
                --lowIndex;
                ++highIndex;
            }
        }

        return str.substring(start, start + maxLength);
    }
}
