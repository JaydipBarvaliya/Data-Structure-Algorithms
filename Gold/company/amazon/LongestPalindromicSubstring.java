package com.company.amazon;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        String input = "MADAM";
        System.out.println(longestPalindrome(input));
        input = "forgeeksskeegfor";
        System.out.println(longestPalindrome(input));
        input = "ccc";
        System.out.println(longestPalindrome(input));

        input = "abb";
        System.out.println(longestPalindrome(input));
    }

    static int startOfLongestPalindrome = 0;
    static int maxLength = 1;

    public static String longestPalindrome(String s) {
        startOfLongestPalindrome = 0;
        maxLength = 1;
        if (s.length() < 2) {
            return s;
        }

        // Why are we starting from 1 since we already know
        // single letter is already palindromic
        for (int i = 1; i < s.length(); i++) {
            extendPalindrome(s, i - 1, i); // For Even Length Palindrome
            extendPalindrome(s, i - 1, i + 1); // For Odd Length Palindrome
        }
        return s.substring(startOfLongestPalindrome, startOfLongestPalindrome + maxLength);
    }

    public static void extendPalindrome(String s, int low, int high) {
        while (low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high)) {
            low--;
            high++;
        }
        if (maxLength < high - low - 1) {
            startOfLongestPalindrome = low + 1; // Since while loop pushed it to a position where
            // s[low] != s[high]
            maxLength = high - low - 1;
        }
    }
}
