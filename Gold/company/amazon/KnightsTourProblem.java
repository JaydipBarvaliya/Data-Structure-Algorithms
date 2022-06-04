package com.company.amazon;

import com.geeksforgeeks.array.Rotate2DMatrix;

public class KnightsTourProblem {

    // Board Size 8 * 8 = 64
    private static int N = 8;

    private static int[] xMoveOfKnight = {2, 1, -1, -2, -2, -1, 1, 2};
    private static int[] yMoveOfKnight = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) {
        solveKnightsTour();
    }

    public static void solveKnightsTour() {
        int[][] sol = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                sol[i][j] = -1;

        // Since the Knight is initially at the first block
        sol[0][0] = 0;

        // Since Knight is at 1st block so 1 move is done
        solveKTUtil(0, 0, 1, sol);

        Rotate2DMatrix.print2DArray(sol);
    }

    public static boolean isSafe(int nextXMove, int nextYMove, int[][] sol) {
        return nextXMove >= 0 && nextXMove < N
                && nextYMove >= 0 && nextYMove < N
                && sol[nextXMove][nextYMove] == -1;
    }

    public static boolean solveKTUtil(int x, int y, int move, int[][] sol) {

        int next_x_move, next_y_move;
        if (move == N * N) {
            return true; // All Moves completed
        }
        for (int i = 0; i < 8; i++) {
            next_x_move = x + xMoveOfKnight[i];
            next_y_move = y + yMoveOfKnight[i];
            if (isSafe(next_x_move, next_y_move, sol)) {
                sol[next_x_move][next_y_move] = move;
                if (solveKTUtil(next_x_move, next_y_move, move + 1, sol)) {
                    return true;
                } else {
                    sol[next_x_move][next_y_move] = -1; // Backtrack
                }
            }
        }
        return false;
    }
}


