package com.leetcode.problems.medium;

/**
 * @author neeraj on 31/08/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class WordSearch_79 {

    static int[] rowIndex = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] colIndex = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};

    static int[] specificallyForLeetCode_RowIndex = new int[]{-1, 0, 1, 0};
    static int[] specificallyForLeetCode_ColIndex = new int[]{0, 1, 0, -1};

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println(exist(board, "ABCCED"));
        System.out.println(exist(board, "SEE"));
        System.out.println(exist(board, "ABCB"));

        board = new char[][]{
                {'a', 'b'},
                {'c', 'd'}
        };

        System.out.println(exist(board, "abcd"));
    }

    public static boolean exist(char[][] board, String word) {
        int charIndex = 0;
        boolean[][] visited;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(charIndex)) {
                    visited = new boolean[board.length][board[0].length];
                    if (searchWord(i, j, charIndex + 1, word, board, visited)) {
                        return true;
                    }
                    charIndex = 0;
                }
            }
        }
        return false;
    }

    private static boolean searchWord(int row, int col, int charIndex, String word, char[][] board, boolean[][] visited) {
        if (charIndex == word.length()) {
            return true;
        } else {
            visited[row][col] = true;
        }

        // Since word can be available in all 4 direction
        for (int i = 0; i < 4; i++) {
            if (isSafe(row + specificallyForLeetCode_RowIndex[i], col + specificallyForLeetCode_ColIndex[i], charIndex, word, board, visited)) {
                if (searchWord(row + specificallyForLeetCode_RowIndex[i], col + specificallyForLeetCode_ColIndex[i], charIndex + 1, word, board, visited)) {
                    return true;
                }
            }
        }
        visited[row][col] = false;
        return false;
    }

    private static boolean isSafe(int row, int col, int charIndex, String word, char[][] board, boolean[][] visited) {
        return row >= 0 && col >= 0 && row < board.length && col < board[0].length
                && board[row][col] == word.charAt(charIndex) && visited[row][col] == false;
    }
}
