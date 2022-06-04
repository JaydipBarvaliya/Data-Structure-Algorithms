package com.leetcode.year_2020;

/**
 * https://leetcode.com/problems/maximal-square/discuss/600149/Python-Thinking-Process-Diagrams-DP-Approach
 * https://leetcode.com/problems/maximal-square/
 * @author neeraj on 27/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaximalSquare {

    public static void main(String[] args) {
        System.out.println(maximalSquare(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        }));
    }

    /**
     * This is a DP problem, since once we calculate solution of 1*1 square
     * which is obvious when any cell is [1] it is a 1*1 Square.
     * we can now use the result of it to find the largest square in the matrix.
     * <p>
     * Input :
     * <p>
     * 1 0 1 0 0
     * 1 1 1 1 1
     * 1 0 1 1 1
     * 1 0 0 1 0
     * <p>
     * Now we can maintain the states in another matrix let's call maxSquare
     * We can count 1st row and 1st Col since it's all just 1*1  matrix;
     * <p>
     * So maxSquare[]
     * 1 0 1 0 0
     * 1 ? ? ? ?
     * 1 ? ? ? ?
     * 1 ? ? ? ?
     * <p>
     * We will calculate all (?).... based on this strategy.
     * <p>
     * 1 0
     * 1 ?   // Assume (?) in original matrix was 1, so we know this guy can contribute
     * // to the maxSquare.
     * But when we checked 3 directions [top, left, diagonally-up] we noticed 0 in top direction, so hence
     * we can make a max of [1] sized square in this matrix.
     * <p>
     * // Why plus 1, since it's contributing to maxSquare.
     * maxSquare[i][j] = Math.min(maxSquare[i-1][j], maxSquare[i][j-1], maxSquare[i-1][j-1]) + 1;
     */
    public static int maximalSquare(char[][] matrix) {
        int[][] maxSquare = new int[matrix.length][matrix[0].length];
        Long maximalSquare = Long.MIN_VALUE;


        // Now lets find out remaining values.
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                // If 1st Row and 1st Column.
                if (i == 0 || j == 0) {
                    maxSquare[i][j] = matrix[i][j] == '1' ? 1 : 0;
                    maximalSquare = Math.max(maximalSquare, maxSquare[i][j]);
                } else if (matrix[i][j] == '0') {
                    maxSquare[i][j] = 0;
                } else {
                    maxSquare[i][j] = Math.min(Math.min(maxSquare[i - 1][j],
                            maxSquare[i][j - 1]), maxSquare[i - 1][j - 1]) + 1;
                    maximalSquare = Math.max(maximalSquare, maxSquare[i][j]);
                }
            }
        }
        return maximalSquare.intValue() * maximalSquare.intValue();
    }
}
