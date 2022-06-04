package com.leetcode.problems.medium;

/**
 * @author neeraj on 08/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class BattleshipInABoard_419 {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'X', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', 'X'}
        };
//        System.out.println(countBattleships(board));
        System.out.println(countBattleshipsOptimized(board));
    }

    public static int countBattleshipsOptimized(char[][] board) {
        int TOTAL_BATTLESHIPS = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // We are on battleship
                if(board[i][j] == '.') {
                    continue;
                }
                if( i > 0 && board[i-1][j] == 'X') {
                    continue;
                }
                if( j > 0 && board[i][j-1] == 'X') {
                    continue;
                }
                TOTAL_BATTLESHIPS++;
            }
        }
        return TOTAL_BATTLESHIPS;
    }

    private static boolean isStartOfShip(char[][] board, int i, int j) {

        if (i == 0) { // When in 1st Row, we can only check by looking into left that we are start of ship or not
            return j == 0 || board[i][j - 1] != 'X' ? true : false;
        }
        if (j == 0) { // When in 1st Column but not in 1st Row, so we have to check just the top
            return board[i - 1][j] != 'X' ? true : false;
        }
        return board[i][j - 1] != 'X' && board[i - 1][j] != 'X' ? true : false;
    }

    public static int countBattleships(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'X') {
                    dfs(board, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public static void dfs(char[][] board, int i, int j) {
        board[i][j] = 'V';

        if (isSafe(board, i - 1, j))
            dfs(board, i - 1, j);
        if (isSafe(board, i, j + 1))
            dfs(board, i, j + 1);
        if (isSafe(board, i + 1, j))
            dfs(board, i + 1, j);
        if (isSafe(board, i, j - 1))
            dfs(board, i, j - 1);
    }

    public static boolean isSafe(char[][] board, int i, int j) {
        return i >= 0 && j >= 0 && i < board.length && j < board[0].length && board[i][j] == 'X';
    }
}
