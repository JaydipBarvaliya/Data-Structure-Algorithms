package com.geeksforgeeks.dynamicProgramming;

/**
 * https://www.geeksforgeeks.org/dynamic-programming-subset-sum-problem/
 * Examples: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
 * Output:  True  //There is a subset (4, 5) with sum 9.
 */
public class SubsetSumProblem {

    public static void main(String[] args) {
        System.out.println("Is Subset Present "+isSubsetPresent(new int[]{1,2,5,7},8));
        System.out.println("Is Subset Present "+isSubsetPresent(new int[]{3, 34, 4, 12, 5, 2},9));
        System.out.println("Is Subset Present "+isSubsetPresent(new int[]{1,3,4,6},2));
    }

    public static Boolean isSubsetPresent(int[] inputSet, int sum) {
        Boolean[][] TruthyValue = new Boolean[inputSet.length + 1][sum + 1];

        // For Hypothetical Row (i.e 0 value from Subset you can only make 0 sum not others)
        TruthyValue[0][0] = Boolean.TRUE;

        for (int i = 1; i <= sum; i++) {
            TruthyValue[0][i] = Boolean.FALSE;
        }

        // Now actual Calculation
        for (int i = 1; i < TruthyValue.length; i++) {
            for (int j = 0; j <= sum; j++) {
                if (inputSet[i-1] > j) {
                    TruthyValue[i][j] = TruthyValue[i - 1][j];
                } else {
                    TruthyValue[i][j] = TruthyValue[i - 1][j] || TruthyValue[i - 1][j - inputSet[i-1]];
                }
            }
        }
        print2DArray(TruthyValue);
        return TruthyValue[inputSet.length][sum];
    }

    public static void print2DArray(Boolean[][] matrix) {
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
