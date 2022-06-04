package com.leetcode.year_2020.DP;

/**
 * https://leetcode.com/problems/knight-dialer/
 * 935. Knight Dialer
 */
public class KnightDialer {

    public static void main(String[] args) {
        System.out.println(knightDialer(10));
    }

    static int MOD = 1000000000 + 7;
    static long M[][][];
    public static int knightDialer(int n) {
        /**
         ** Since we want to find out the distinct phone numbers of length n, we can dial
         ** we can try to place the knight in every cell of keypad(only numeric)
         **/

        // A 3D array to store the solutions to the subproblems
        M = new long[n + 1][4][3];

        // Lets try to place on keypad
        long uniquePlacements = 0;
        for(int i=0;i<4;i++) {
            for(int j=0;j<3;j++) {
                uniquePlacements = (uniquePlacements + paths(i, j, n)) % MOD;
            }
        }
        return (int) uniquePlacements;
    }

    public static long paths(int row, int col, int n) {
        // Safe guard
        if(row < 0 || col < 0 || row >= 4 || col >= 3 || (row == 3 && col != 1)) return 0;

        if(n == 1) return 1; // we have no more jumps left to explore
        if(M[n][row][col] > 0) return M[n][row][col];
        //A knight can go in 8 directions
        long uniquePathsFromHere = paths(row+1, col+2, n-1) % MOD +
                paths(row+2, col+1, n-1) % MOD +
                paths(row+2, col-1, n-1) % MOD +
                paths(row+1, col-2, n-1) % MOD +
                paths(row-1, col-2, n-1) % MOD +
                paths(row-2, col-1, n-1) % MOD +
                paths(row-2, col+1, n-1) % MOD +
                paths(row-1, col+2, n-1) % MOD;
        return M[n][row][col] = uniquePathsFromHere;

    }
}
