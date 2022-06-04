package com.leetcode.year_2020.DP.matrix_chain_multiplication;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/palindrome-partitioning-ii/
 * <p>
 * Palindrome Partitioning using Recursion
 * Given a string, a partitioning of the string is a palindrome partitioning
 * if every substring of the partition is a palindrome.
 * Example:
 * “aba|b|bbabb|a|b|aba” is a palindrome partitioning of “ababbbabbababa”.
 * <p>  5 cuts
 * But we need to determine minimum cuts.
 * So that is a|babbbab|b|ababa ==> which is 3 cuts.
 *
 * @author neeraj on 10/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PalindromicPartitioningMinimumCuts {

    public static void main(String[] args) {
        System.out.println(findMinCuts("aab"));             // aa|b             1 cut.
        System.out.println(findMinCuts("ababbbabbababa")); // a|babbbab|b|ababa 3 cuts.
        System.out.println(findMinCuts("NITIN")); // 0 partition since it's already palindrome
        System.out.println(findMinCuts("NITIK")); // N|ITI|k   2 cuts
        System.out.println(findMinCuts("BANANA")); // B|ANANA 1 cuts
    }

    static int[][] t; // Memorization(Top-Down Approach)
    static int[][] isPalindrome; // Memorization(Top-Down Approach to store Palindrome result between i to j of input)

    public static int findMinCuts(String input) {
        /**
         * Why it's a DP problem.
         *
         * 1) Do we have to choose from something like KnapSack etc.
         * 2) Is Problem asking about something optimal.
         *
         * Yes Problem is asking for minimum cuts, Now lets see which parent problem this problem relates to.
         *
         * Pattern Matching Technique:----->
         *
         *                      I/p      Question            ReturnType
         * Matrix Chain         arr        Min Operation        int
         * Multiplication
         *
         * Palindromic          str        Min Cuts             int.
         * Partitioning cuts
         * ---------------------------------------------------------------------------
         *                       0           1                   1        ====> 2/3 criteria are matching so we can solve
         *                                                                      this problem using Matrix Chain Multiplication
         *                                                                      parent problem.
         *
         * Now how it's similar to MCM:
         * In MCM we multiply matrix in certain fashion : A B C D.... (AB)(CD) or (A)(BCD) or (A(BC))D ... etc.
         *
         * Here also we need to make cuts :
         *
         * [A A B] ==> we can do this partition
         *
         *              [A | A | B]  ==> 2 cuts
         *              [A A | B]    ==> 1 cut.        Our Answer.
         *  Sweeeeeet..........
         *
         *  Now how can we do this:
         *
         *  1) We have to basically try to cut/partition the string at every possible index and find out min.
         *  2) for(cut=i to cut < j).... why cut is kept at j-1 you may ask.... simple when you cut you want 2
         *                              partition of it. If we cut at last there will only be a left partition.
         */
        t = new int[input.length() + 1][input.length() + 1];
        isPalindrome = new int[input.length() + 1][input.length() + 1];
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }
        for (int[] row : isPalindrome) {
            Arrays.fill(row, -1);
        }
        int minCuts = solve(input, 0, input.length() - 1);
        return minCuts;
    }

    private static int solve(String input, int i, int j) {
        if (t[i][j] > -1) return t[i][j];
        if (isPalindrome[i][j] != -1 || isPalindrome(input, i, j)) {
            t[i][j] = 0;
            isPalindrome[i][j] = 0;
            return 0; // if the string is already a palindrome there is no need
        }
        // to partition it.


        // K is our cut point
        int MIN_CUTS = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {

            /**
             ** Taking Memorizatin to another level, by checking partial parts also in cache.
             *.  i.e checking if cache has result of [i][k] and [k+1][j]; if yes then use it and no need to further solve it.
             **/
            int totalCutsBeforeThisCut = t[i][k] > -1 ? t[i][k] : solve(input, i, k);
            int totalCutsAfterThisCut = t[k + 1][j] > -1 ? t[k + 1][j] : solve(input, k + 1, j);

            // Now since we have made this cut so that is to be accounted for, that's why the input is divided into
            // (i, k) and (k+1,j).
            int result = totalCutsBeforeThisCut + 1 + totalCutsAfterThisCut;
            if (MIN_CUTS > result) {
                MIN_CUTS = result;
            }
        }
        return t[i][j] = MIN_CUTS;
    }

    private static boolean isPalindrome(String input, int i, int j) {
        if (i == j) return true;
        if (i > j) return false;
        String substring = input.substring(i, j + 1);
        String reversed = new StringBuilder(substring).reverse().toString();
        return reversed.equals(substring);
    }
}
