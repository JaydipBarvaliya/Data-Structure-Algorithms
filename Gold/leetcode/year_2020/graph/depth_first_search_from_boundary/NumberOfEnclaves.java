package com.leetcode.year_2020.graph.depth_first_search_from_boundary;

/**
 * https://leetcode.com/problems/number-of-enclaves/
 *
 * @author neeraj on 30/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NumberOfEnclaves {

    static int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) {
        System.out.println(numEnclaves(new int[][]{
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        }));
        System.out.println(numEnclaves(new int[][]{
                {0, 1, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 0}
        }));
    }

    public static int numEnclaves(int[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0) return 0;
        /**
         * Step 1) We will check all the boundaries first
         *          if we found any 1, we will flip it to '-1' and it's related island to '-1'
         * Step 2) we will now count all left 1's and return it.
         */

        // First Column and Last Column
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 1) {
                doDFSForBoundary(board, i, 0);
            }
            if (board[i][board[0].length - 1] == 1) {
                doDFSForBoundary(board, i, board[0].length - 1);
            }
        }

        // First Row and Last Row
        for (int j = 0; j < board[0].length; j++) {
            if (board[0][j] == 1) {
                doDFSForBoundary(board, 0, j);
            }
            if (board[board.length - 1][j] == 1) {
                doDFSForBoundary(board, board.length - 1, j);
            }
        }

        int enclaves = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] == 1) enclaves++;
            }
        }
        return enclaves;
    }

    private static void doDFSForBoundary(int[][] board, int i, int j) {
        board[i][j] = -1;
        for (int[] direction : directions) {
            if (isSafeToMove(board, i + direction[0], j + direction[1])) {
                doDFSForBoundary(board, i + direction[0], j + direction[1]);
            }
        }
    }

    private static boolean isSafeToMove(int[][] board, int i, int j) {
        return i > 0 && i < board.length - 1
                && j > 0 && j < board[i].length - 1
                && board[i][j] == 1;
    }
}
