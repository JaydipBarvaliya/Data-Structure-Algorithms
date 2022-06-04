package com.company.amazon;

/**
 * Input:  k = 12
 * mat[][] = { {1, 2, 3},
 * {4, 6, 5},
 * {3, 2, 1}
 * };
 * Output:  2
 * There are two paths with 12 coins
 * 1 -> 2 -> 6 -> 2 -> 1
 * 1 -> 2 -> 3 -> 5 -> 1
 */
public class TotalNumberOfPathsToReachFromTopLeftToBottomRightWithExactlyKCoins {

    public static void main(String[] args) {
        int mat[][] = {{1, 2, 3},
                {4, 6, 5},
                {3, 2, 1}
        };

        System.out.println(getTotalNumberOfPaths(mat, mat.length - 1, mat[0].length - 1, 12));
    }

    /**
     * We will play the game from Bottom right and check if we can reach to top left without exhausting coins
     */
    public static int getTotalNumberOfPaths(int[][] arr, int ROW, int COL, int kCoins) {
        if (ROW < 0 || COL < 0) // Base Condition
            return 0;

        if (ROW == 0 && COL == 0) {
            return kCoins == arr[ROW][COL] ? 1 : 0;
        }

        // Since we can only move right and bottom
        // but we are playing the game from bottom right
        // So we will move only left and top
        return getTotalNumberOfPaths(arr, ROW - 1, COL, kCoins - arr[ROW][COL]) +
                getTotalNumberOfPaths(arr, ROW, COL - 1, kCoins - arr[ROW][COL]);
    }
}
