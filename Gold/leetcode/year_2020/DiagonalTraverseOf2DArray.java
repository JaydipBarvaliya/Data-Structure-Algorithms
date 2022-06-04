package com.leetcode.year_2020;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neeraj on 19/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class DiagonalTraverseOf2DArray {

    public static void main(String[] args) {
        LogUtil.printArray(findDiagonalOrder(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }));
        LogUtil.printArray(findDiagonalOrder(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        }));
        LogUtil.printArray(findDiagonalOrder(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},
        }));
        LogUtil.printArray(findDiagonalOrder(new int[][]{
                {3}, {2}
        }));
        LogUtil.printArray(findDiagonalOrder(new int[][]{
                {1, 2, 3, 4, 5}
        }));

        System.out.println(findDiagonalOrderEasy(new int[][]{
                {1, 3, 4, 10},
                {2, 5, 9, 11},
                {6, 8, 12, 15},
                {7, 13, 14, 16}
        }));

        System.out.println(findDiagonalOrderEasy(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }));

        System.out.println(findDiagonalOrderEasy(new int[][]{
                {2,3}
        }));

    }

    public static List<Integer> findDiagonalOrderEasy(int[][] matrix) {
        /**
         * This is a different version of problem from Algo Expert.
         * 1 3 4 10
         * 2 5 9 11
         * 6 8 12 15
         * 7 13 14 16
         *
         * So Output should be
         * [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]
         *
         */
        boolean isGoingDown = false;
        int row = 0, col = 0, m = matrix.length - 1, n = matrix[0].length - 1;
        List<Integer> result = new ArrayList<>();
        while (!isOutOfBounds(row, col, m, n)) {
            result.add(matrix[row][col]);
            if (isGoingDown) {
                if (col == 0 || row == m) { // First Column or Last Row
                    isGoingDown = false;
                    if (row == m)
                        col += 1;
                    else
                        row += 1;
                } else {
                    row++;
                    col--;
                }
            } else {
                if (row == 0 || col == n) { // First Column or Last Row, so switch the direction
                    isGoingDown = true;
                    if (col == n)
                        row += 1;
                    else
                        col += 1;
                } else {
                    row--;
                    col++;
                }
            }
        }
        return result;
    }

    private static boolean isOutOfBounds(int row, int col, int m, int n) {
        return row < 0 || col < 0 || row > m || col > n;
    }

    public static int[] findDiagonalOrder(int[][] matrix) {
        int i = 0;
        int j = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> layer;
        boolean up = true;
        while (i < m) {
            layer = new ArrayList<>();
            int row = i;
            int col = 0;
            while (row >= 0 && col < n) {
                if (up) {
                    layer.add(matrix[row][col]);
                } else {
                    layer.add(0, matrix[row][col]);
                }
                row--;
                col++;
            }
            i++;
            result.add(layer);
            up = !up;
        }

        i--; // Set to last row.
        j = 1;
        while (j < n) {
            layer = new ArrayList<>();
            int row = m - 1;
            int col = j;
            while (col < n && row >= 0) {
                if (up) {
                    layer.add(matrix[row][col]);
                } else {
                    layer.add(0, matrix[row][col]);
                }
                row--;
                col++;
            }
            j++;
            result.add(layer);
            up = !up;
        }
        int[] diagonal = new int[m * n];
        int index = 0;
        for (List<Integer> layr : result) {
            for (int item : layr) {
                diagonal[index++] = item;
            }
        }
        return diagonal;

    }
}
