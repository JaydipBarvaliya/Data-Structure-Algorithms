package com.geeksforgeeks.array;

public class CountNegativeIntegersInRowColumnSortedMatrix {

    public static void main(String[] args) {
        int mat[][] = { {-3, -2, -1, 1},
                        {-2, 2, 3, 4},
                        {4, 5, 7, 8}};

        System.out.println(getNegativeNumbersCount(mat));
    }

    public static int getNegativeNumbersCount(int[][] matrix) {
        int count = findIndexOfFirstPositiveNumberInTheRow(matrix[0], 0, matrix[0].length);
        int indexOfFirstPositiveNumber = count;
        for (int i = 1; i < matrix.length; i++) {
            indexOfFirstPositiveNumber = findIndexOfFirstPositiveNumberInTheRow(matrix[i], 0, indexOfFirstPositiveNumber - 1);
            count += indexOfFirstPositiveNumber;
        }
        return count;
    }

    public static int findIndexOfFirstPositiveNumberInTheRow(int[] row, int low, int high) {
        if (low <= high) {
            int mid = (low + high) / 2;

            if (mid == 0 || (row[mid] > 0 && row[mid - 1] < 0)) {
                return mid;
            } else if (row[mid] < 0) {
                return findIndexOfFirstPositiveNumberInTheRow(row, mid + 1, high);
            } else {
                return findIndexOfFirstPositiveNumberInTheRow(row, low, mid - 1);
            }
        }
        return -1;
    }

}
