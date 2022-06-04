package com.geeksforgeeks.dynamicProgramming;

import com.datastructures.array.ArrayUtil;

import java.util.HashSet;
import java.util.Set;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        getLongestCommonSubsequence("abcdaf".toCharArray(), "acbcf".toCharArray());

        System.out.println("LCS of aab and azb is " +
                getLongestCommonSubsequenceRecursively("aab", "azb") + " and common sub-sequence is " + LCS.reverse());
        LCS = new StringBuffer();
        System.out.println("LCS of abcdaf and acbcf is " +
                getLongestCommonSubsequenceRecursively("abcdaf", "acbcf") + " and common sub-sequence is " + LCS.reverse());
    }

    /**
     * a b c d a f
     * a b c f
     * <p>
     * Longest common sub sequence is a b c f whose length is 4 remember sub sequence is not continuous but
     * has to be incremental which means in a b c and a c b the longest common sub sequence is 2 not 3 .
     */
    public static void getLongestCommonSubsequence(char[] first, char[] second) {
        int[][] LCS = new int[first.length + 1][second.length + 1];
        int maxLength = 0;
        Set<Character> commonSequence = new HashSet<>();
        for (int i = 1; i < LCS.length; i++) {
            for (int j = 1; j < LCS[i].length; j++) {
                if (first[i - 1] == second[j - 1]) {
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                } else {
                    LCS[i][j] = Math.max(LCS[i][j - 1], LCS[i - 1][j]);
                }
                if (LCS[i][j] > maxLength) {
                    maxLength = LCS[i][j];
                    commonSequence.add(first[i - 1]);
                }
            }
        }
        ArrayUtil.print2DArray(LCS, first.length + 1, second.length + 1);
        System.out.println("And Max Length is " + (maxLength));
        System.out.println("and common sequence is " + commonSequence);
    }

    private static StringBuffer LCS = new StringBuffer();

    public static int getLongestCommonSubsequenceRecursively(String s1, String s2) {
        // Base Case 1
        // There will be no common sub-sequence with EMPTY string
        if (s1.length() == 0 || s2.length() == 0) {
            return 0;
        }

        String s1WithoutLastCharacter = s1.substring(0, s1.length() - 1);
        String s2WithoutLastCharacter = s2.substring(0, s2.length() - 1);

        char lastCharInS1 = s1.charAt(s1.length() - 1);
        char lastCharInS2 = s2.charAt(s2.length() - 1);

        if (lastCharInS1 == lastCharInS2) {
            return 1 + getLongestCommonSubsequenceRecursively(s1WithoutLastCharacter, s2WithoutLastCharacter);
        } else {
            return Math.max(getLongestCommonSubsequenceRecursively(s1WithoutLastCharacter, s2),
                    getLongestCommonSubsequenceRecursively(s1, s2WithoutLastCharacter));
        }
    }
}
