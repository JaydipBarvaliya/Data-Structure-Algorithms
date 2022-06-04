package com.geeksforgeeks.dynamicProgramming;

public class MatrixChainMultiplication {

    public static void main(String[] args) {
        System.out.println("Minimum Operations are " + getMinimumNoOfOperations(new int[]{1, 2, 3, 4}));
        System.out.println("Minimum Operations are " + getMinimumNoOfOperations(new int[]{3, 4, 5, 6, 2, 5}));
        System.out.println("Minimum Operations are " + getMinimumNoOfOperations(new int[]{40, 20, 30, 10, 30}));
        System.out.println("Minimum Operations are " + getMinimumNoOfOperations(new int[]{10, 20, 30, 40, 30}));
        System.out.println("Minimum Operations are " + getMinimumNoOfOperations(new int[]{10, 20, 30}));
    }

    public static int getMinimumNoOfOperations(int[] arr) {
        int N = arr.length;
        int[][] operations = new int[N - 1][N - 1];
        int[][] brackets = new int[N - 1][N - 1];

        // Operations are 0 when multiplying only single matrix
        for (int i = 0; i < arr.length - 1; i++) {
            operations[i][i] = 0;
        }

        int j; // End Index;
        int k; // Partition;
        int min; // Temporary variable to store the minimum value of each partition used

        // Let's start from 2 matrix multiplication (i.e. level 1) at reach till the end
        for (int level = 1; level < arr.length; level++) {

            for (int i = 0; i < N - 1 - level; i++) {

                j = i + level;
                operations[i][j] = Integer.MAX_VALUE;

                for (k = i; k < j; k++) {
                    min = operations[i][k] + operations[k + 1][j] + arr[i] * arr[k + 1] * arr[j + 1];

                    if (min < operations[i][j]) {
                        operations[i][j] = min;
                        brackets[i][j] = k;
                    }
                }

            }
        }
        System.out.print("AmazonPrimeOrdersSorting Case ==>");
        printArray(arr);
        print2DArray(operations);
//        print2DArray(brackets);
        return operations[0][N - 2];
    }

    public static void printArray(int[] input) {
        System.out.print("[");
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i] + ",");
        }
        System.out.println("]");
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
}

