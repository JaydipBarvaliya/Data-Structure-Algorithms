package com.leetcode.year_2020.DP.matrix_chain_multiplication;

import com.util.LogUtil;

import java.util.Arrays;

/**
 * @author neeraj on 09/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MatrixChainMultiplication {

    public static void main(String[] args) {
        System.out.println(findMinimumMultiplicationOperation(new int[]{1, 2, 3, 4}));
        System.out.println(findMinimumMultiplicationOperation(new int[]{2, 3, 4, 5, 6}));
        System.out.println(findMinimumMultiplicationOperation(new int[]{10, 20, 30}));
        System.out.println(findMinimumMultiplicationOperation(new int[]{40, 20, 30, 10, 30}));
        System.out.println(findMinimumMultiplicationOperation(new int[]{10, 20, 30, 40, 30}));
    }

    static int t[][]; // Memorization
    static int brackets[][]; // TO put the optimal Brackets.

    public static void printBrackets(int[][] brackets, int i, int j) {
        // If we have just 1 matrix, let's just print that.
        if (i == j) {
            System.out.print("A" + i);
        } else {
            // We have multiple matrix in place, let's enclose them in brackets.
            System.out.print("(");
            printBrackets(brackets, i, brackets[i][j]); // Call for i to k
            printBrackets(brackets, brackets[i][j] + 1, j); // Call for K to j
            System.out.print(")");
        }
    }

    public static int findMinimumMultiplicationOperation(int[] dimensions) {
        /**
         * Assume we have dimensions as [40, 20, 30, 10, 30]  with length as n
         * So we have n-1 matrix .
         * A = 40*20;
         * B = 20*30;
         * C = 30*10;
         * D = 10*30
         *
         * So how should we multiply them to achieve minimum multiplication operation.
         *
         * (AB)(CD), (A)(BCD), (ABC)(D).....
         *
         * The Optimal Answer for this problem is.
         * (A(BC))D --> 20*30*10 + 40*20*10 + 40*10*30
         *
         * [40  20  30  10  30]
         *  0   1   2   3   4
         *      i            j
         *  Now why i starts from 1 and not from 0.
         *  since the dimension is a pair (m * n).
         *  if we start i == 0, we won't be having m in m*n
         *
         *  Now k will run from i to j-1, why till j-1, since we k act as a cut to our matrix,
         *  and we put cut in  matrix to multiply, Now if we keep k==j... we can do solve(arr, i, k), but not solve(arr, k+1, j).
         */
        t = new int[dimensions.length][dimensions.length];
        brackets = new int[t.length][t[0].length];
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }
        int minOperation = solveMatrixChain(dimensions, 1, dimensions.length - 1);
        LogUtil.printMultiDimensionArray(brackets);
        printBrackets(brackets, 1, brackets.length - 1);
        LogUtil.newLine();
        LogUtil.logIt("Top Down  :: " + minOperation);
        minOperation = solveMatrixChainMultiplicationBottomUp(dimensions);
        LogUtil.logIt("Bottom UP :: " + minOperation);
        return minOperation;
    }

    private static int solveMatrixChain(int[] dimensions, int i, int j) {
        if (i >= j) {
            t[i][j] = 0;
            return 0; // This case represent when we have matrix of length 1, if that's the case
        }
        // we can never multiply since we need at least 2 to do multiplication.

        if (t[i][j] > -1) return t[i][j];

        // K will run from i till j(exclusive), and try each section to multiply from i to k, and k+1 to j-1
        Integer MIN_OPERATIONS = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int operationsInLeft = solveMatrixChain(dimensions, i, k);
            int operationsInRight = solveMatrixChain(dimensions, k + 1, j);

            // Assume Matrix, A1, A2, A3, A4, if i put bracket like this
            // (A1 A2) (A3, A4)  ====> Now we also need the cost of final multiplying left and right part
            int operationsAtThisPoint = dimensions[i - 1] * dimensions[k] * dimensions[j];

            // Thia is the summation of Cost(A1 * A2) + Cost (A3 * A4) + Cost (Cost(A1*A2) * Cost(A3*A4))
            int operationsIfWeChooseThisK = operationsInLeft + operationsAtThisPoint + operationsInRight;
            if (operationsIfWeChooseThisK < MIN_OPERATIONS) {
                MIN_OPERATIONS = operationsIfWeChooseThisK;

                // Update the brackets wherever we have put optimal cuts.
                brackets[i][j] = k;
            }
        }
        return t[i][j] = MIN_OPERATIONS;
    }

    private static int solveMatrixChainMultiplicationBottomUp(int[] dimensions) {
        /**
         *
         * Better Understanding :
         *
         * https://www.youtube.com/watch?v=JMql7zF87aE&vl=en-US
         *
         * We have matrix dimensions, Now lets assume dimensions we
         * [2,3,6,4,5]
         * We have len(dimension) -1 matrices.
         * Matrix ==> (m*n) where m = rows, and n = columns in matrix.
         * A      ==> 2*3
         * B      ==> 3*6
         * C      ==> 6*4
         * D      ==> 4*5
         *
         * What base condition do we have to calculate minOperation/cost
         * we already know if there is just 1 matrix then the cost will always be 0.
         *
         * So cost[i][i] = 0;
         *
         * Now let's try to multiply 2 matrix at a time
         *     A*B    |    B*C    |    C*D
         *    2*3*6   |   3*6*4   |   6*4*5
         *
         * Now let's try to multiply 3 matrix.
         * A*B*C ===> how can we multiply them
         *        1) A * (BC)
         *        2) (AB) * C.   and cost will be min(alternative_1, alternative_2)
         * If you see from step above we have already calculated:
         * BC and AB   ---> so we need minCost(AB, BC) + cost of multiplying current A.
         *             ---> Cost(AB) = 2*3*6 = 36 + (2 * 6 * 4)=48 ==> 84...
         *                                               ||===============> This is the cost of Multiplying C into AB
         *
         * Now the tricky part is how do you get 2 * 6 * 4 ===> So 2 is m of ith Matrix.
         *                                                         6 is k where we are standing
         *                                                         4 is n of jth matrix
         * which will be
         *      dimensions[i][0] * dimensions[k][1] * dimensions[j][1]
         *
         *      0 --> rows
         *      1 --> cols in dimensions.
         */
        int cost[][] = new int[dimensions.length - 1][dimensions.length - 1];
        int N = dimensions.length;
        // For Level 0 (i.e Single Matrix)
        for (int i = 0; i < dimensions.length - 1; i++) {
            cost[i][i] = 0;
        }

        // Now for Level 2 to N-1
        // i.e. multiplying AB , BC CD  ==> level 1
        //                  ABC BCD     ==> level 2
        // We will move diagonally.
        /**
         *    A  B  C  D
         * A  0
         * B     0
         * C        0
         * D           0
         *
         * Now we will multiply 2 matrices at a time
         * AB BC CD and if you notice carefully, that's exactly sitting in diagonal.
         */
        int j = 0;
        int min; // temp variable
        for (int level = 1; level < dimensions.length; level++) {

            // Represent starting point of matrix multiplication in this level.
            for (int i = 0; i < N - 1 - level; i++) {

                // j is the end point in this level of multiplication
                // represent extreme right as per Recursive
                j = i + level;

                // At i, j initial cost is maximum and we have to reduce it by
                // dividing solve(i,k) + solve(k+1, j) + arr[i] + arr[k] + arr[j];
                cost[i][j] = Integer.MAX_VALUE;
                // K represents cut.
                for (int k = i; k < j; k++) {
                    min = cost[i][k] + cost[k + 1][j] + (dimensions[i] * dimensions[k + 1] * dimensions[j + 1]);

                    if (cost[i][j] > min) {
                        cost[i][j] = min;
                    }
                }
            }
        }
        return cost[0][cost[0].length - 1];
    }
}
