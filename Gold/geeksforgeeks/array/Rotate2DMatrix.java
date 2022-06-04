package com.geeksforgeeks.array;

/**
 * int mat[N][N] = {
 * { 1, 2, 3 },
 * { 4, 5, 6 },
 * { 7, 8, 9 }
 * };
 */
public class Rotate2DMatrix {

    private static int N = 3;

    public static void main(String[] args) {

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        rotateMatrixBy180(matrix);
//
//        print2DArray(matrix);
//        inplaceRotate180Degree(matrix);
//        print2DArray(matrix);

        print2DArray(matrix);
        transpose(matrix);
        print2DArray(matrix);

    }

    public static void rotateMatrixBy180(int[][] matrix) {

        for (int i = N - 1; i >= 0; i--) {

            for (int j = N - 1; j >= 0; j--) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Transpose
     * Reverse Column
     * Transpose
     * Reverse Column and Boom matrix is rotated by 180 degree
     *
     * @param matrix
     */
    public static void inplaceRotate180Degree(int[][] matrix) {
        transpose(matrix);
        reverseColumnsInMatrix(matrix);
        transpose(matrix);
        reverseColumnsInMatrix(matrix);
    }

    public static void reverseColumnsInMatrix(int[][] matrix) {
        int temp = 0;
        for (int j = 0; j < matrix.length; j++) {
            for (int i = 0, k = matrix.length - 1; i < matrix.length / 2; i++, k--) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[k][j];
                matrix[k][j] = temp;
            }
        }
    }

    public static void print2DArray(int[][] matrix) {
        System.out.println("<============Start=======================>");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("<============END=======================>");
    }

    public static void transpose(int[][] matrix) {
        int row = 3, column = 3;

        for (int i = 0; i < row; i++) {
            for (int j = i; j < column; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }

    public static void swap(int[][] matrix, int i, int j, int k, int l) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[k][l];
        matrix[k][l] = temp;
    }
}
