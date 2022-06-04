package com.leetcode.problems.medium;

import com.geeksforgeeks.array.Rotate2DMatrix;

/**
 * @author neeraj on 08/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RotateImage {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Rotate2DMatrix.print2DArray(matrix);
        rotateImage(matrix);
        Rotate2DMatrix.print2DArray(matrix);


        matrix = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Rotate2DMatrix.print2DArray(matrix);
        rotateImage(matrix);
        Rotate2DMatrix.print2DArray(matrix);

        matrix = new int[][]{
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };
        Rotate2DMatrix.print2DArray(matrix);
        rotateImage(matrix);
        Rotate2DMatrix.print2DArray(matrix);
    }

    static class Pair {
        int i;
        int j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void rotateImage(int[][] matrix) {
        Pair tl = new Pair(0, 0); // Top-Left
        Pair tr = new Pair(0, matrix[0].length - 1); // Top-Right
        Pair br = new Pair(matrix.length - 1, matrix[0].length - 1); // Bottom-Right
        Pair bl = new Pair(matrix.length - 1, 0);// Bottom -left
        int counter = 0;
        while (tl.i < br.i && bl.j < tr.j) {
            swapCorners(matrix, tl, tr, br, bl);
            // Increment.
            tl.j += 1;
            tr.i += 1;
            br.j -= 1;
            bl.i -= 1;

            // Reset if they reached the limit.
            if (tl.j == tr.j) {
                tl.i += 1;
                tl.j = counter + 1;

                tr.i = counter + 1;
                tr.j -= 1;

                br.i -= 1;
                br.j = matrix[0].length - 1 - counter - 1;

                bl.i = matrix.length - 1 - counter - 1;
                bl.j += 1;

                counter++;
            }
        }
    }

    private static void swapCorners(int[][] matrix, Pair tl, Pair tr, Pair br, Pair bl) {
        int tl_dash = matrix[tl.i][tl.j],
                tr_dash = matrix[tr.i][tr.j],
                br_dash = matrix[br.i][br.j],
                bl_dash = matrix[bl.i][bl.j];

        matrix[tr.i][tr.j] = tl_dash; // Moving Top-Left to TopRight
        matrix[br.i][br.j] = tr_dash; // Moving Top-Right to Bottom-Right
        matrix[bl.i][bl.j] = br_dash; // Moving Bottom-Right to Bottom-Left
        matrix[tl.i][tl.j] = bl_dash; // Moving Bottom-Left to Top-Left
    }

    /**
     * Simple Transpose the matrix
     * and just flip it
     * 1 2 3
     * 4 5 6
     * 7 8 9
     * <p>
     * So transpose of it
     * 1 4 7
     * 2 5 8
     * 3 6 9
     * <p>
     * // Now look at what we want to achieve is just the flip of above transpose output
     * <p>
     * 7 4 1
     * 8 5 2
     * 9 6 3
     *
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        transposeMatrix(matrix);

        // Now just flip it
        for (int i = 0; i < matrix.length; i++) {
            swap1DMatrix(matrix[i]);
        }
    }

    private static void swap1DMatrix(int[] matrix) {
        int left = 0;
        int right = matrix.length - 1;
        while (left < right) {
            int temp = matrix[left];
            matrix[left] = matrix[right];
            matrix[right] = temp;
            left++;
            right--;
        }
    }

    private static void transposeMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[i].length; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }

    private static void swap(int[][] matrix, int i, int j, int k, int l) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[k][l];
        matrix[k][l] = temp;
    }
}
