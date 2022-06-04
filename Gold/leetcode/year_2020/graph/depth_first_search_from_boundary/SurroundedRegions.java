package com.leetcode.year_2020.graph.depth_first_search_from_boundary;

import com.util.LogUtil;

/**
 * https://leetcode.com/problems/surrounded-regions/submissions/
 *
 * @author neeraj on 30/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SurroundedRegions {

    static int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) {
        solve(new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        });
        solve(new char[][]{
                {'O', 'O', 'O'},
                {'O', 'O', 'O'},
                {'O', 'O', 'O'}
        });
        solve(new char[][]{
                {'X', 'O', 'X'},
                {'X', 'O', 'X'},
                {'X', 'O', 'X'}
        });
        solve(new char[][]{
                {'O', 'X', 'X', 'O', 'X'},
                {'X', 'O', 'O', 'X', 'O'},
                {'X', 'O', 'X', 'O', 'X'},
                {'O', 'X', 'O', 'O', 'O'},
                {'X', 'X', 'O', 'X', 'O'}
        });
    }

    public static void solve(char[][] board) {
        LogUtil.logIt("Solving", true);
        /**
         * Step 1) We will check all the boundaries first
         *          if we found any O, we will flip it to '*' and it's related island to '*'
         * Step 2) we will now update all other's left O to X
         * Step 3) convert '*' back to O
         */
        // First Column and Last Column
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O') {
                doDFSForBoundary(board, i, 0);
            }
            if (board[i][board[0].length - 1] == 'O') {
                doDFSForBoundary(board, i, board[0].length - 1);
            }
        }

        // First Row and Last Row
        for (int j = 0; j < board[0].length; j++) {
            if (board[0][j] == 'O') {
                doDFSForBoundary(board, 0, j);
            }
            if (board[board.length - 1][j] == 'O') {
                doDFSForBoundary(board, board.length - 1, j);
            }
        }

        // Now revert '*' to 'O'
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '*') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
        LogUtil.printMultiDimensionArray(board);
    }

    private static void doDFSForBoundary(char[][] board, int i, int j) {
        board[i][j] = '*';
        for (int[] direction : directions) {
            if (isSafeToMove(board, i + direction[0], j + direction[1])) {
                doDFSForBoundary(board, i + direction[0], j + direction[1]);
            }
        }
    }

    private static boolean isSafeToMove(char[][] board, int i, int j) {
        return i > 0 && i < board.length - 1
                && j > 0 && j < board[i].length - 1
                && board[i][j] == 'O';
    }
}
