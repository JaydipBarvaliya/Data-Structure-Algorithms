package com.company.amazon;

public class LongestPalindromicParenthesesSubstring {

    public static void main(String[] args) {
        String input = "((()(()((()))";
        System.out.println(getLongestPalindromicSubstring(input));
        input = "((()";
        System.out.println(getLongestPalindromicSubstring(input));
        input = ")()())";
        System.out.println(getLongestPalindromicSubstring(input));
        input = "()(()))))";
        System.out.println(getLongestPalindromicSubstring(input));
    }

    @SuppressWarnings("Duplicates")
    public static int getLongestPalindromicSubstring(String str) {
        System.out.println("============================");
        char[] input = str.toCharArray();
        int longestParenthesesLength = 0;
        int startPoint = 0;

        for (int i = 1; i < str.length(); i++) {

            // For Even Length String
            int low = i - 1;
            int high = i;
            while (low >= 0 && high < input.length && input[low] == '(' && input[high] == ')') {
                int currentLongestLength = (high - low) + 1;
                if (longestParenthesesLength < currentLongestLength) {
                    startPoint = low;
                    longestParenthesesLength = currentLongestLength;
                }
                low--;
                high++;
            }
            // Odd Length String testing is not required because we want a perfect parentheses which can only be formed
            // with Even Length Strings
        }
        System.out.println(str.substring(startPoint, startPoint + longestParenthesesLength));
        return longestParenthesesLength;
    }
}
