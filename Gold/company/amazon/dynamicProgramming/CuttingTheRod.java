package com.company.amazon.dynamicProgramming;

import com.geeksforgeeks.array.Rotate2DMatrix;

import java.util.ArrayList;
import java.util.List;

public class CuttingTheRod {

    public static void main(String[] args) {
        getMaximumProfit(new int[]{2, 5, 9, 8}, 5);
        getMaximumProfit(new int[]{1, 5, 8, 9, 10, 17, 17, 20}, 8);
        getMaximumProfit(new int[]{3, 5, 8, 9, 10, 17, 17, 20}, 8);
    }

    public static void getMaximumProfit(int[] price, int sizeOfRod) {
        // Using 1st row and 1st column as hypothetical situation
        // where we are checking the length of rod as 0 and also we can cut in size of 0
        int[][] profit = new int[price.length + 1][sizeOfRod + 1];

        // Since you cannot make any profit if you have to make rod of length 0 [So 1st column will be 0]
        // similarly you cannot give any profit if you don't have any profit size i.e. 0 [So 1st Row will be 0]

        // Now let's calculate the actual price
        for (int i = 1; i < profit.length; i++) {
            for (int j = 1; j < profit[i].length; j++) {

                if (i <= j) { // Size of cut is <= the size of rod to be constructed
                    // Maximum of Include the price, exclude the price
                    profit[i][j] = Math.max((price[i - 1] + profit[i][j - i]), profit[i - 1][j]);
                } else {
                    profit[i][j] = profit[i - 1][j];
                }
            }
        }
        Rotate2DMatrix.print2DArray(profit);
        System.out.println("Maximum Profit which can be achieved is " + profit[profit.length - 1][sizeOfRod]);
        findUsedPieces(profit);
    }

    private static void findUsedPieces(int[][] profit) {
        List<Integer> usedPieces = new ArrayList<>();
        int i = profit.length - 1;
        for (int j = profit[i].length - 1; j > 0; ) {
            if (profit[i][j] != profit[i - 1][j]) {
                usedPieces.add(i);
                j = j - i;
            } else {
                i--;
            }
        }
        System.out.println("And Used pieces is " + usedPieces);
    }
}
