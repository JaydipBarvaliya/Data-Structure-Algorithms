package com.geeksforgeeks.dynamicProgramming;

import com.geeksforgeeks.array.Rotate2DMatrix;

import java.util.ArrayList;
import java.util.List;

public class CuttingTheRodProblem {

    public static void main(String[] args) {
        getTheMaximumProfit(new int[]{2, 5, 9, 8}, 5);
        getTheMaximumProfit(new int[]{1, 5, 8, 9, 10, 17, 17, 20}, 8);
        getTheMaximumProfit(new int[]{3, 5, 8, 9, 10, 17, 17, 20}, 8);
    }

    public static int getTheMaximumProfit(int[] prices, int totalLength) {

        int[][] priceMatrix = new int[prices.length][totalLength + 1];

        for (int i = 1; i < priceMatrix.length; i++) {
            for (int j = 1; j < priceMatrix[i].length; j++) {
                if (i <= j) {
                    priceMatrix[i][j] = Math.max(prices[i - 1] + priceMatrix[i][j - i], priceMatrix[i - 1][j]);
                } else {
                    priceMatrix[i][j] = priceMatrix[i - 1][j];
                }
            }
        }
        Rotate2DMatrix.print2DArray(priceMatrix);
        System.out.println("Maximum Profit which can be achieved is " + priceMatrix[priceMatrix.length - 1][totalLength]);
        getUsedPieces(priceMatrix);
        return 0;
    }

    public static List<Integer> getUsedPieces(int[][] priceMatrix) {
        List<Integer> usedPieces = new ArrayList<>();
        int i = priceMatrix.length - 1;
        for (int j = priceMatrix[0].length - 1; j > 0; ) {
            if (priceMatrix[i][j] != priceMatrix[i - 1][j]) {
                usedPieces.add(i);
                j = j - i;
            } else {
                i--;
            }
        }
        System.out.println("Used Pieces --->" + usedPieces);
        return usedPieces;
    }


    public static void solveCuttingTheRodProblem(int[] piece, int[] price, int lengthOfRod) {
        int[][] profit = new int[price.length + 1][lengthOfRod + 1];

        // 1st row and 1st column is zero, Dummy Row and column

        for (int i = 1; i < profit.length; i++) {
            for (int j = 1; j < profit[i].length; j++) {

                if (piece[i - 1] <= j) {
                    profit[i][j] = Math.max(price[i - 1] + profit[i][j - piece[i - 1]], profit[i - 1][j]);
                } else {
                    profit[i][j] = profit[i - 1][j];
                }
            }
        }

        System.out.println(profit[profit.length - 1][lengthOfRod]);
    }


}
