package com.interviewbit.string_parsing;

/**
 * https://www.interviewbit.com/problems/minimum-characters-required-to-make-a-string-palindromic/
 * <p>
 * Given an string A. The only operation allowed is to insert characters in the beginning of the string.
 * <p>
 * Find how many minimum characters are needed to be inserted to make the string a palindrome string.
 * <p>
 * <p>
 * <p>
 * Input Format
 * <p>
 * The only argument given is string A.
 * Output Format
 * <p>
 * Return the minimum characters that are needed to be inserted to make the string a palindrome string.
 * For Example
 * <p>
 * Input 1:
 * A = "ABC"
 * Output 1:
 * 2
 * Explanation 1:
 * Insert 'B' at beginning, string becomes: "BABC".
 * Insert 'C' at beginning, string becomes: "CBABC".
 * <p>
 * Input 2:
 * A = "AACECAAAA"
 * Output 2:
 * 2
 * Explanation 2:
 * Insert 'A' at beginning, string becomes: "AAACECAAAA".
 * Insert 'A' at beginning, string becomes: "AAAACECAAAA".
 *
 * @author neeraj on 2019-08-01
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumCharactersRequiredToMakeAStringPalindromic {

    public static void main(String[] args) {
        System.out.println(solve("ABC"));
        System.out.println(solve("AACECAAAA"));
        System.out.println(solve("banana"));
        System.out.println(solve("hqghumeaylnlfdxfi"));
        System.out.println(solve("AAAAA"));
    }

    public static int solve(String A) {
//        return getMinimumInsertions(A, 0, A.length() - 1);
        return getMinimumInsertionsIfInsertionIsAllowedOnlyInFront(A);
    }

    public static int getMinimumInsertions(String str, int low, int high) {
        str = str.toLowerCase();
        if (low > high)
            return -1;
        if (low == high) {
            return 0; // No Insertions required
        }

        Boolean isCharAtLowAndHighEqual = str.charAt(low) == str.charAt(high);
        if (low == high - 1) { // If only 2 characters are available
            return isCharAtLowAndHighEqual ? 0 : 1;
        }

        return isCharAtLowAndHighEqual ? getMinimumInsertions(str, low + 1, high - 1)
                :
                Math.max(getMinimumInsertions(str, low, high - 1), getMinimumInsertions(str, low + 1, high)
                ) + 1;
    }

    public static int getMinimumInsertionsIfInsertionIsAllowedOnlyInFront(String input) {
        StringBuffer temp = new StringBuffer(input);
        StringBuffer reversed;
        for (int i = input.length(); i > 0; i--) {
            temp = new StringBuffer(input.substring(0, i));

            reversed = new StringBuffer(temp).reverse();

            if (temp.toString().equalsIgnoreCase(reversed.toString())) {
                return input.length() - i;
            }
        }
        return 0;
    }
}
