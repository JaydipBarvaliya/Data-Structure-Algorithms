package com.geeksforgeeks.dynamicProgramming;

import com.geeksforgeeks.array.Rotate2DMatrix;

public class MinimumEditDistance {

    public static void main(String[] args) {
        System.out.println("Minimum Edit Distance for converting abcdef to azced is " + getMinimumEditDistance("abcdef", "azced"));
        System.out.println("Minimum Edit Distance for converting sunday to saturday is " + getMinimumEditDistance("sunday", "saturday"));
        System.out.println("Minimum Qualities for converting Neeraj to Ayushi is " + getMinimumEditDistance("Neeraj","Ayushi"));
        System.out.println("Minimum Qualities for converting Ayushi to Neeraj is " + getMinimumEditDistance("Ayushi","Neeraj"));
    }

    public static int getMinimumEditDistance(final String FROM, final String TO) {
        char[] from = FROM.toCharArray();
        char[] to = TO.toCharArray();

        int[][] OPERATIONS = new int[TO.length() + 1][FROM.length() + 1];

        for (int i = 0; i <= TO.length(); i++) {
            OPERATIONS[i][0] = i;
        }
        for (int i = 0; i <= FROM.length(); i++) {
            OPERATIONS[0][i] = i;
        }

        for (int i = 1; i < OPERATIONS.length; i++) {
            for (int j = 1; j < OPERATIONS[i].length; j++) {
                if (to[i - 1] == from[j - 1]) {
                    OPERATIONS[i][j] = OPERATIONS[i - 1][j - 1];
                } else {
                    OPERATIONS[i][j] = minimum(OPERATIONS[i - 1][j], OPERATIONS[i][j - 1], OPERATIONS[i - 1][j - 1]) + 1;
                }
            }
        }
        Rotate2DMatrix.print2DArray(OPERATIONS);
        return OPERATIONS[OPERATIONS.length - 1][OPERATIONS[0].length -1];
    }

    public static int minimum(int a, int b, int c) {
        int temp = Math.min(a, b);
        return Math.min(temp, c);
    }
}
