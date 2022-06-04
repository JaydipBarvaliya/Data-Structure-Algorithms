package com.competitive.coding.hackerearth;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by jaine03 on 09/08/17.
 *
 *          2
            2
            2 1
            1 2
            2 1
            1 2
            .*
            *.
            2
            9 9
            9 9
            1 1
            1 1
            .*
            *.
 *
 */
public class MarbleGame {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int totalTestCase = Integer.parseInt(br.readLine());
            int counter = 0;

            while (counter < totalTestCase) {
                int N = Integer.parseInt(br.readLine());
            int[][] costMatrix = new int[N][N];
            int[][] benefitMatrix = new int[N][N];
            char[][] placementMatrix = new char[N][N];
                // Taking Cost Input
                for (int i = 0; i < N; i++) {
                    String[] costInput = br.readLine().split(" ");
                    for (int j = 0; j < costInput.length; j++) {
                        costMatrix[i][j] = Integer.parseInt(costInput[j]);
                    }
                }

                // Taking Benefit Input
                for (int i = 0; i < N; i++) {
                    String[] costInput = br.readLine().split(" ");
                    for (int j = 0; j < costInput.length; j++) {
                        benefitMatrix[i][j] = Integer.parseInt(costInput[j]);
                    }
                }

                // Taking Placement Input
                for (int i = 0; i < N; i++) {
                    String costInput = br.readLine();
                    for (int j = 0; j < costInput.length(); j++) {
                        placementMatrix[i][j] = costInput.charAt(j);
                    }
                }

//                printIntegerMatrix(costMatrix);
//                printIntegerMatrix(benefitMatrix);
//                printCharacterMatrix(placementMatrix);

                int totalPlacementScore = totalPlacementScore(placementMatrix, costMatrix);
//                System.out.println("Total Placement Score " + totalPlacementScore);
                int totalBenefitScore = totalBenefitScore(placementMatrix, benefitMatrix);
//                System.out.println("Total Benefit Score " + totalBenefitScore);

                System.out.println(totalBenefitScore - totalPlacementScore);

                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int totalPlacementScore(char[][] placementMatrix, int[][] costMatrix) {
        int totalCost = 0;
        for (int i = 0; i < placementMatrix.length; i++) {
            for (int j = 0; j < placementMatrix.length; j++) {
                if (placementMatrix[i][j] == '*') {
                    totalCost += costMatrix[i][j];
                }
            }
        }
        return totalCost;
    }

    public static int totalBenefitScore(char[][] placementMatrix, int[][] benefitMatrix) {
        int totalCost = 0;
        for (int i = 0; i < placementMatrix.length; i++) {
            for (int j = 0; j < placementMatrix.length; j++) {
                if (isDominatedCell(placementMatrix, i, j, placementMatrix.length)) {
                    totalCost += benefitMatrix[i][j];
                }
            }
        }
        return totalCost;
    }

    public static void printIntegerMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void printCharacterMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }


    public static Boolean isDominatedCell(char[][] placementMatrix, int row, int col, int N) {
        if (placementMatrix[row][col] == '*') {
            return true;
        }

        // Cell having 4 adjacent
        if (row - 1 >= 0 && row + 1 < N && col - 1 >= 0 && col + 1 < N) {
            return placementMatrix[row - 1][col] == '*' &&
                    placementMatrix[row + 1][col] == '*' &&
                    placementMatrix[row][col + 1] == '*' &&
                    placementMatrix[row][col - 1] == '*';
        }

        //Left Upper Corner Cell
        if (row - 1 < 0 && col - 1 < 0) {
            return placementMatrix[row + 1][col] == '*' &&
                    placementMatrix[row][col + 1] == '*';
        }

        //Right Upper Corner Cell
        if (row - 1 < 0 && col + 1 >= N) {
            return placementMatrix[row + 1][col] == '*' &&
                    placementMatrix[row][col - 1] == '*';
        }

        // Left Bottom Corner Cell
        if (row + 1 >= N && col - 1 < 0) {
            return placementMatrix[row - 1][col] == '*' &&
                    placementMatrix[row][col + 1] == '*';
        }

        //Right bottom Corner Cell
        if (row + 1 >= N && col + 1 >= N) {
            return placementMatrix[row - 1][col] == '*' &&
                    placementMatrix[row][col - 1] == '*';
        }

        //first Top Lane
        if (row - 1 < 0) {
            return placementMatrix[row + 1][col] == '*' &&
                    placementMatrix[row][col + 1] == '*' &&
                    placementMatrix[row][col - 1] == '*';
        }

        // First Left Lane
        if (col - 1 < 0) {
            return placementMatrix[row][col + 1] == '*' &&
                    placementMatrix[row - 1][col] == '*' &&
                    placementMatrix[row + 1][col] == '*';
        }

        // First Right Lane
        if (col + 1 >= N) {
            return placementMatrix[row][col - 1] == '*' &&
                    placementMatrix[row + 1][col] == '*' &&
                    placementMatrix[row - 1][col] == '*';
        }

        // First Bottom Lane
        if (row + 1 >= N) {
            return placementMatrix[row - 1][col] == '*' &&
                    placementMatrix[row][col + 1] == '*' &&
                    placementMatrix[row][col - 1] == '*';
        }
        return false;
    }
}
