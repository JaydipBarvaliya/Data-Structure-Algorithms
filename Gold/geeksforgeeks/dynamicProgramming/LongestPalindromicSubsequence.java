package com.geeksforgeeks.dynamicProgramming;

import com.geeksforgeeks.array.Rotate2DMatrix;

/**
 * Input : GEEKSFORGEEKS
 * <p>
 * Output : 5
 * <p>
 * The longest palindromic subsequence
 * we can get is of length 5. There are
 * more than palindromic subsequences
 * of length 5, for example, EEKEE,
 * EESEE. EEFEE.
 */
public class LongestPalindromicSubsequence {

    public static void main(String[] args) {
        getLongestPalindromicSubsequence("adbbca".toCharArray());
    }

    public static int getLongestPalindromicSubsequence(char[] input) {
        int N = input.length;
        int[][] LPS = new int[N][N];

        // Every letter has only 1 as LPS
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j)
                    LPS[i][j] = 1;
            }
        }

        // Now let's compare other letters to calculate Longest Palindromic Sub-sequence
        // We have only one rule that we have to move diagonal because every cell is dependent upon it's diagonally previous element
        // So In order to calculate that we need to prefill the diagonal data first before proceeding

        // Now let's compare each letter with the letter at distance 1 ..... So [a d b b c a] we will compare a with d then

        int distance = 1;
        while (distance < N) {

            int row = 0;

            // Why row + distance and not only row <= N-1, because we are doing comparision in 1D Array
            // [a,d,b,b,c,a] ==> If i will only put row<=N-1, we might end up comparing 2nd (d) with char after last (a)
            // hence this safe check
            while (row + distance < N) {

                int column = row + distance;

                if (input[row] == input[column]) { // If Both characters match then LPS = 2 + Diagonally Previous Value
                    LPS[row][column] = 2 + LPS[row + 1][column - 1]; // Why 2 ? because 2 characters matched and then checking for the remaining Longest Palindromic Sub-sequence
                } else { // If doesn't match then it will be the max of previous adjacent diagonals
                    LPS[row][column] = Math.max(LPS[row][column - 1], LPS[row + 1][column]);
                }
                row += 1;
            }
            distance += 1;
        }

        Rotate2DMatrix.print2DArray(LPS);
        return 0;
    }
}
