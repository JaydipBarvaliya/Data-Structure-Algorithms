package com.leetcode.problems.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author neeraj on 12/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("DuplicatedCode")
public class IsValidSudoku_36 {

    /**
     * Valid box entry will be in a form of,
     * <"row,col", true>
     */
    static Map<String, Boolean> validBoxCheck = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(isValidSudokuSimpleApproach(new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        }));
        System.out.println(isValidSudokuSimpleApproach(new char[][]{
                {'.', '.', '.', '.', '5', '.', '.', '1', '.'},
                {'.', '4', '.', '3', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '3', '.', '.', '1'},
                {'8', '.', '.', '.', '.', '.', '.', '2', '.'},
                {'.', '.', '2', '.', '7', '.', '.', '.', '.'},
                {'.', '1', '5', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '2', '.', '.', '.'},
                {'.', '2', '.', '9', '.', '.', '.', '.', '.'},
                {'.', '.', '4', '.', '.', '.', '.', '.', '.'}
        }));
        System.out.println(isValidSudokuSimpleApproach(new char[][]{
                {'7', '.', '.', '.', '4', '.', '.', '.', '.'},
                {'.', '.', '.', '8', '6', '5', '.', '.', '.'},
                {'.', '1', '.', '2', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '9', '.', '.', '.'},
                {'.', '.', '.', '.', '5', '.', '5', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '2', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'}
        }));

        System.out.println(isValidSudokuSimpleApproach(new char[][]{
                {'.', '.', '4', '.', '.', '.', '6', '3', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'5', '.', '.', '.', '.', '.', '.', '9', '.'},
                {'.', '.', '.', '5', '6', '.', '.', '.', '.'},
                {'4', '.', '3', '.', '.', '.', '.', '.', '1'},
                {'.', '.', '.', '7', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '5', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'}
        }));

    }

    public static boolean isValidSudokuSimpleApproach(char[][] board) {
        // We will generate 3 keys for any non-empty cell
        /**
         * Rows: "row(VALUE)"
         * Cols: "(VALUE)col"
         * Box: "row(VALUE)col"
         *
         * and we will maintain a set to keep track of this and whenever we found an existing value in set
         * i.e we found a duplicate either in row, col or same box and sudoku is no longer valid
         */
        final Set<String> seen = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                final char value = board[i][j];
                if (value == '.') continue;
                final String rowKey = i + "(" + value + ")";
                final String colKey = "(" + value + ")" + j; // Why value before in colKey just to differentiate from row entry.
                // We know a box is of 3*3 in classic sudoku.
                // so we will actually have only 9 boxes and we can calculate the box number by
                // row/3 and col/3
                final String boxKey = i / 3 + "(" + value + ")" + j / 3;
                if (seen.contains(rowKey) || seen.contains(colKey) || seen.contains(boxKey)) {
                    return false;
                } else {
                    seen.addAll(Arrays.asList(rowKey, colKey, boxKey));
                }
            }
        }
        return true;
    }


    public static boolean isValidSudoku(char[][] board) {
        Map<Character, Character> map;
        validBoxCheck = new HashMap<>();

        // Checking all Rows
        for (int i = 0; i < board.length; i++) {
            // First Let's check if this row is valid
            // And also we check the row only once, to avoid recalculation
            map = new HashMap<>();
            // Checking all columns for respective rows
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;
                if (map.containsKey(board[i][j])) {
                    return false;
                } else {
                    map.put(board[i][j], board[i][j]);
                }
            }
            // Now let's check if the sub-box is valid
            for (int j = 0; j < board[i].length; j += 3) {
                if (!isValidSubBoard(board, i, j)) {
                    return false;
                }
            }
        }

        // Checking all Columns
        for (int i = 0; i < board.length; i++) {
            // Checking all columns for respective rows
            map = new HashMap<>();

            // Checking all columns for respective rows
            for (int j = 0; j < 9; j++) {
                if (board[j][i] == '.') continue;
                if (map.containsKey(board[j][i])) {
                    return false;
                } else {
                    map.put(board[j][i], board[j][i]);
                }
            }
        }

        return true;
    }

    /**
     * This method will check if the sub-boxes are valid.
     * So first we have to identify which box are we in based on co-ordinates
     * We can do this via 2 things,
     * 1) Scale down coordinates to the 3*3 sub-box (Note we can upscale this sub-box region)
     * a) We do this by dividing co-ordinates with the size of sub-box.
     * b) So that if the coordinates is 7,8, we down-scale it to box coordinates i.e 0 to 2
     * c) 7/3 and 8/3 ==> [2,2]
     * 2) Now we just have to find out the starting point of sudoku i.e
     * Scaled-down coordinates * Region Size
     * [2*3, 2*3] ==> [6,6]
     * So this is the starting of the box
     */
    private static boolean isValidSubBoard(char[][] board, int i_coordinate, int j_coordinate) {
        int scaledDown_I = i_coordinate / 3;
        int scaledDown_J = j_coordinate / 3;
        int leftTopRowOfSubBox = scaledDown_I * 3;
        int leftTopColOfSubBox = scaledDown_J * 3;
        String validBoxKey = leftTopRowOfSubBox + "" + leftTopColOfSubBox;
        List<Character> existingVal = new ArrayList<>();

        if (validBoxCheck.containsKey(validBoxKey))
            return validBoxCheck.get(validBoxKey);
        else {
            for (int k = 0; k < 3; k++) {
                for (int l = 0; l < 3; l++) {
                    if (board[leftTopRowOfSubBox + k][leftTopColOfSubBox + l] == '.') continue;
                    if (existingVal.contains(board[leftTopRowOfSubBox + k][leftTopColOfSubBox + l])) {
                        validBoxCheck.put(validBoxKey, false);
                        return false;
                    } else {
                        existingVal.add(board[leftTopRowOfSubBox + k][leftTopColOfSubBox + l]);
                    }
                }
            }
        }
        validBoxCheck.put(validBoxKey, true);
        return true;
    }
}
