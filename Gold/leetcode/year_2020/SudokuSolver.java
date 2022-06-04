package com.leetcode.year_2020;

import static com.util.LogUtil.logIt;
import static com.util.LogUtil.printMultiDimensionArray;

/**
 * @author neeraj on 07/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SudokuSolver {

    public static void main(String[] args) {
        int[][] board_1 = new int[][]{
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        logIt("Solving Sudoku", true);
        printMultiDimensionArray(board_1);
        if (solveSudoku(board_1)) {
            logIt("Sudoku Solved successfully", true);
            printMultiDimensionArray(board_1);
        } else {
            logIt("This Sudoku board can't be solved", true);
        }
    }

    private static int EMPTY_CELL = 0;

    public static boolean solveSudoku(int[][] board) {
        return solveSudokuCell(0, 0, board);
    }

    private static boolean solveSudokuCell(int row, int col, int[][] board) {

        // This is our Goal.
        if (col == board[row].length) { // We have reached to the last column
            col = 0;
            row++;
            if (row == board.length) {
                return true; // We have solved the sudoku for all rows.
            }
        }

        if (board[row][col] != EMPTY_CELL) { // Since we can only put on empty cell, so let's just skip this.
            return solveSudokuCell(row, col + 1, board);
        }

        // Now Lets explore our decision space;
        for (int i = 1; i <= 9; i++) {
            if (isValidPlacement(row, col, i, board)) { // Constraint for the Backtracking problem
                board[row][col] = i; // Choose this decision from decision space.

                if (solveSudokuCell(row, col + 1, board)) { // Explore with the selected decision.
                    return true;
                }
            }
        }
        board[row][col] = EMPTY_CELL; // UnChoose our decision.
        return false;
    }

    /**
     * Check for 3 things.
     * 1) if I place this decision will it break the current row.
     * 2) if I place this decision will it break the current col.
     * 3) if it will break the current sub-grid;
     *
     * @param row
     * @param col
     * @param board
     * @return
     */
    private static boolean isValidPlacement(int row, int col, int decision, int[][] board) {
        // Let's validate current row
        for (int i = 0; i < board[row].length; i++) {
            if (board[row][i] == decision) return false;
        }

        // let's check current col
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == decision) return false;
        }

        // Now let's check the sub-grid;
        // This is interesting because we need to know in which grid are we present.
        // So first we need to know in which SubBox's row and column are we standing
        int subGridLength = (int) Math.sqrt(board.length);

        int I = row / subGridLength; // I denotes in which Subbox's row i am standing
        int J = col / subGridLength; // J denotes in which Sub-box's col i am standing.

        // Now we have to reach to the start of this sub-box
        // which is simple.
        int startOfSubGridRow = I * subGridLength;
        int startOfSubGridCol = J * subGridLength;

        for (int i = 0; i < subGridLength; i++) {
            for (int j = 0; j < subGridLength; j++) {
                if (board[i + startOfSubGridRow][j + startOfSubGridCol] == decision) {
                    return false;
                }
            }
        }
        return true;
    }
}
