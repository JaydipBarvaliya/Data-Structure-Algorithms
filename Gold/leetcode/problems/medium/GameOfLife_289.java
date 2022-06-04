package com.leetcode.problems.medium;

import com.util.LogUtil;

/**
 * @author neeraj on 12/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class GameOfLife_289 {

    static int[][] neighbours = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
    static int liveStatus = 2;
    static int dieStatus = 3;

    public static void main(String[] args) {
        gameOfLife(new int[][]{
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        });
    }

    public static void gameOfLife(int[][] board) {
        LogUtil.printMultiDimensionArray(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // We always calculate the liveNeighbours from the original state of board, as requested in Question
                // Not what happened after any modifications.
                int liveNeighbours = getLiveNeighbours(board, i, j);
                if (board[i][j] == 0 && liveNeighbours == 3) {
                    board[i][j] = liveStatus;
                }
                if (board[i][j] == 1) {
                    if (liveNeighbours < 2 || liveNeighbours > 3) {
                        board[i][j] = dieStatus;
                    }
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == liveStatus) {
                    board[i][j] = 1;
                }
                if (board[i][j] == dieStatus) {
                    board[i][j] = 0;
                }
            }
        }
        LogUtil.printMultiDimensionArray(board);
    }

    private static int getLiveNeighbours(int[][] board, int i, int j) {
        int count = 0;
        for (int k = 0; k < neighbours.length; k++) {
            int[] move = neighbours[k];
            int tempI = i + move[0];
            int tempJ = j + move[1];
            // Is Safe to Move
            if (tempI >= 0 && tempJ >= 0 && tempI < board.length && tempJ < board[0].length) {
                // Why are we checking the dieStatus because, due to neighbours, the cell tempI,tempJ must
                // have died, but we always want to find live neighbours based on original state of the board.
                if (board[tempI][tempJ] == 1 || board[tempI][tempJ] == dieStatus) {
                    count++;
                }
            }
        }
        return count;
    }
}
