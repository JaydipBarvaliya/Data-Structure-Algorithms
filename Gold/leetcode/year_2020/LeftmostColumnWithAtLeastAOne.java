package com.leetcode.year_2020;

import java.util.Arrays;
import java.util.List;

/**
 * @author neeraj on 21/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LeftmostColumnWithAtLeastAOne {
    static class BinaryMatrix {
        int[][] matrix;

        public BinaryMatrix(int[][] matrix) {
            this.matrix = matrix;
        }

        public int get(int x, int y) {
            return matrix[x][y];
        }

        public List<Integer> dimensions() {
            return Arrays.asList(matrix.length, matrix[0].length);
        }
    }

    public static void main(String[] args) {
        leftMostColumnWithOne(new BinaryMatrix(new int[][]{
                {0, 0, 0, 1},
                {0, 0, 1, 1},
                {0, 1, 1, 1}
        }));

        leftMostColumnWithOne(new BinaryMatrix(new int[][]{
                {0,0},
                {0,1}
        }));

        leftMostColumnWithOne(new BinaryMatrix(new int[][]{
                {0,0},
                {1,1}
        }));

        leftMostColumnWithOne(new BinaryMatrix(new int[][]{
                {0,0},
                {0,0}
        }));
    }

    public static int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimension = binaryMatrix.dimensions();
        int row = dimension.get(0);
        int col = dimension.get(1);
        int lastValueAt = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            int firstPositionOfOne = binarySearch(i, 0, col - 1, binaryMatrix);
            if (firstPositionOfOne != -1) {
                lastValueAt = Math.min(firstPositionOfOne, lastValueAt);
            }
        }
        System.out.println(lastValueAt == Integer.MAX_VALUE ? -1 : lastValueAt);
        return lastValueAt == Integer.MAX_VALUE ? -1 : lastValueAt;
    }

    public static int binarySearch(int row, int low, int high, BinaryMatrix binaryMatrix) {
        if (low <= high) {
            int mid = low + (high - low) / 2;

            // First check if mid is 1
            if (binaryMatrix.get(row, mid) == 1) {

                // Now check if this is the left most value of 1.
                if (mid == 0) { // If this is the first item in row
                    return mid;
                }
                if (binaryMatrix.get(row, mid - 1) == 0) { // if this is actually the left most 1
                    return mid;
                } else {
                    return binarySearch(row, low, mid - 1, binaryMatrix);
                }
            } else {
                if (mid == high) {
                    return -1;
                } else {
                    return binarySearch(row, mid + 1, high, binaryMatrix);
                }
            }
        }
        return -1;
    }
}
