package com.leetcode.year_2020.all_premium.tags.google.map;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/alphabet-board-path/
 * Only works for lowercase letters, as given in question
 */
public class AlphabetBoardPath {

    public static void main(String[] args) {
        System.out.println(alphabetBoardPath("xdz"));
        System.out.println(alphabetBoardPath("leet"));
        System.out.println(alphabetBoardPath("leetcode"));
    }

    private static Map<Character, int[]> charPositionIn2d;

    static {
        charPositionIn2d = populateCharPositionIn2d();
    }

    public static String alphabetBoardPath(String target) {
        int[] currPlacement = {0, 0};
        final StringBuilder result = new StringBuilder();
        for (char ch : target.toCharArray()) {
            int[] nextCharPlacement = charPositionIn2d.get(ch);
            int rowDiff = nextCharPlacement[0] - currPlacement[0];
            int colDiff = nextCharPlacement[1] - currPlacement[1];

            if (ch == 'z') {
                // Tricky case just in case of Z, if someone is moving to z
                // they can only move via, 1st column, so hence we should
                // always move to left if required first than going down.
                append(result, colDiff < 0 ? 'L' : 'R', Math.abs(colDiff));
                append(result, rowDiff < 0 ? 'U' : 'D', Math.abs(rowDiff));
            } else {
                append(result, rowDiff < 0 ? 'U' : 'D', Math.abs(rowDiff));
                append(result, colDiff < 0 ? 'L' : 'R', Math.abs(colDiff));
            }
            result.append('!'); // to denote adding to result;
            currPlacement[0] = nextCharPlacement[0];
            currPlacement[1] = nextCharPlacement[1];
        }
        return result.toString();
    }

    private static void append(final StringBuilder res, final char ch, int count) {
        while (count-- > 0) {
            res.append(ch);
        }
    }

    private static Map<Character, int[]> populateCharPositionIn2d() {
        final Map<Character, int[]> charPositionIn2d = new HashMap<>();
        String[] board = {"abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                char charAtIAndJ = board[i].charAt(j);
                charPositionIn2d.put(charAtIAndJ, new int[]{i, j});
            }
        }
        return charPositionIn2d;
    }
}
