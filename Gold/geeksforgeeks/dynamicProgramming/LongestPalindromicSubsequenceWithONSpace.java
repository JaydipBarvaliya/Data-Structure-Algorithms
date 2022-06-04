package com.geeksforgeeks.dynamicProgramming;

public class LongestPalindromicSubsequenceWithONSpace {

    public static void main(String[] args) {
        System.out.println(getLengthOfLPS("GEEKSFORGEEKS".toCharArray()));
    }

    public static int getLengthOfLPS(char[] str) {
        int[] LPS = new int[str.length];

        for (int i = str.length - 1; i >= 0; i--) {
            int back_up = 0;

            //Picking up ending points to see if str[i] increases length of LPS ending with str[j];
            for (int j = i; j < str.length; j++) {

                // For Length one, LPS will always be 1
                if (i == j)
                    LPS[j] = 1;
                else if (str[i] == str[j]) { // When character matches
                    int temp = LPS[j];
                    LPS[j] = back_up + 2;
                    back_up = temp;
                } else {
                    back_up = LPS[j];
                    LPS[j] = Math.max(LPS[j - 1], LPS[j]);
                }
            }
        }
        return LPS[str.length - 1];
    }
}
