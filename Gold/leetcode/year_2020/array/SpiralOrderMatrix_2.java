package com.leetcode.year_2020.array;

import com.util.LogUtil;

public class SpiralOrderMatrix_2 {

    public static void main(String[] args) {
        generateMatrix(3);
    }

    public static int[][] generateMatrix(int A) {
        int[][] result = new int[A][A];
        int K = 0, L = 0, M = A, N = A, counter = 1;

        while (K < M && L < N) {

            // First row
            for (int i = L; i < N; i++) {
                result[K][i] = counter++;
            }
            K++;

            // Last Column
            for (int i = K; i < M; i++) {
                result[i][N-1] = counter++;
            }
            N--;

            if (M > K) {
                // last row
                for (int i = N-1; i >= L; i--) {
                    result[M-1][i] = counter++;
                }
                M--;
            }

            if (N > L) {
                // First Column
                for (int i = M-1; i >= K; i--) {
                    result[i][L] = counter++;
                }
                L++;
            }
        }
        LogUtil.printMultiDimensionArray(result);
        return result;
    }
}
