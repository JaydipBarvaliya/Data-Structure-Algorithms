package com.leetcode.backtracking;

import static com.util.LogUtil.logIt;
import static com.util.LogUtil.printMultiDimensionArray;

/**
 * https://leetcode.com/problems/sudoku-solver
 * <p>
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * <p>
 * A sudoku solution must satisfy all of the following rules:
 * <p>
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 *
 * @author neeraj on 2019-05-05
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SudokuSolver {

    private static int EMPTY_CELL = 0;
    private static int TRY_COUNTER = 0;
    private static boolean SHOULD_ADD_SLEEP_AND_INTERMEDIATE_LOGS = false;

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

        int[][] board_2 = new int[][]{
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };


        logIt("Solving Sudoku", true);
        printMultiDimensionArray(board_1);
        if (solveSudoku(0, 0, board_1)) {
            logIt("Sudoku Solved successfully", true);
            printMultiDimensionArray(board_1);

            logIt("Problem solved in " + TRY_COUNTER + "tries", true);
        } else {
            logIt("This Sudoku board can't be solved", true);
        }
    }

    /**
     * Util method to solve the sudoku board
     *
     * @param row   Row for which placements is to be done.
     * @param col   Column for which placement is to be done.
     * @param board 9*9 Sudoku board to be solved
     * @return True if this board can be solved else false.
     */
    public static boolean solveSudoku(int row, int col, int[][] board) {

        // We have reached to the last column in the board
        if (col == board[row].length) {

            // Reset the column and jump to next row
            col = 0;
            row++;

            // Check have we reached the last row successfully and the board is successfully solved.
            if (row == board.length) {
                return true; // Entire board has been filled without conflict.
            }
        }

        // Skip non-empty entries. They already have a value in them.
        if (board[row][col] != EMPTY_CELL) {
            return solveSudoku(row, col + 1, board);
        }

        // CHOICE We can choose the value from 1-9
        for (int value = 1; value <= 9; value++) {

            // So Check if this value breaks the board or not.
            if (isValidPlacement(row, col, value, board)) {
                board[row][col] = value;

//                // Let's log out the entry
//                logIt(">>>>>>>>>>>>>>>>> Trying Placing " + value + " at [" + row + "," + col + "] with try_count at " + (++TRY_COUNTER) +
//                        " <<<<<<<<<<<<<<<<<<<", false);

                if (SHOULD_ADD_SLEEP_AND_INTERMEDIATE_LOGS) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    printMultiDimensionArray(board);
                }

                if (solveSudoku(row, col + 1, board)) {
                    return true;
                }
                board[row][col] = EMPTY_CELL;
            }
        }
        // This choice didn't work, so let's backtrack
        return false;
    }

    /**
     * This method will tell us that any value ranging from [1-9] can be placed on the board
     * i.e. 1) In this row no other entry should have the same value we are trying to place {@param value}
     * * *  2) In this column no other entry should have the same value we are trying to place {@param value}
     * * *  3) In this sub-grid, no other entry should have the same value that we are trying to place {@param value}
     *
     * @param row
     * @param col
     * @param value
     * @param board
     * @return
     */
    private static boolean isValidPlacement(int row, int col, int value, int[][] board) {

        // Check all the values in this row
        for (int i = 0; i < 9; i++) {
            if (value == board[row][i]) {
                return false; // If match then we can't place the new value, since it will break the board
            }
        }

        // Check all the values in col
        for (int i = 0; i < 9; i++) {
            if (value == board[i][col]) {
                return false; // If match then we can't place the new value, since it will break the board
            }
        }


        /**
         * Check region constraints.
         *
         *     In a 9 x 9 board, we will have 9 sub-boxes (3 rows of 3 sub-boxes).
         *
         *     The "I" tells us that we are in the Ith sub-box row. (there are 3 sub-box rows)
         *     The "J" tells us that we are in the Jth sub-box column. (there are 3 sub-box columns)
         *
         */
        int regionSize = (int) Math.sqrt(board.length);

        int I = row / regionSize; // We are in Ith Sub-box counting row-wise  [Top-Bottom]
        int J = col / regionSize; // We are in Jth Sub-box counting col-wise  [Left --> Right]

        // Let's identify, sub-box first co-ordinate
        int topLeftOfSubBoxInRowWise = I * regionSize;
        int topLeftOfSubBoxInColWise = J * regionSize;

        // Now since we have starting co-ordinates of sub-box, so we can traverse that box in O(n^2) times.
        for (int i = 0; i < regionSize; i++) {
            for (int j = 0; j < regionSize; j++) {
                if (value == board[topLeftOfSubBoxInRowWise + i][topLeftOfSubBoxInColWise + j]) {
                    return false; // If match then we can't place the new value, since it will break the board
                }
            }
        }
        return true; // All constraints passed let's return success.
    }
}
