package com.geeksforgeeks.dynamicProgramming;

import com.geeksforgeeks.array.Rotate2DMatrix;

public class BinomialCoefficient {

    public static void main(String[] args) {
        System.out.println("Binomial Coeff of 5,2 is :: " + getBinomialCoeffInBottomUpManner(5,2));
        System.out.println("Binomial Coeff of 5,2 is :: " + getBinomialCoeffInBottomUpManner(5,2));
    }

    public static int getBinomialCoeff(int a, int b) {

        if(a == b || b == 0)
            return 1;

        return getBinomialCoeff(a-1,b-1) + getBinomialCoeff(a-1,b);
    }

    public static int getBinomialCoeffInBottomUpManner(int N, int K) {

        int [][] C = new int[N+1][K+1];

        for(int i=0;i<=N;i++) {

            for(int j=0;j<=Math.min(i,K);j++) { // We are traversing only upto min(i,k) since only these transactions are required to calculate the original NcK.

                if(j==0 || j==i) {
                    C[i][j] = 1;
                } else {
                    C[i][j] = C[i-1][j-1] + C[i-1][j];
                }
            }
        }
        Rotate2DMatrix.print2DArray(C);
        return C[N][K];
    }
}
