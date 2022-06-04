package com.leetcode.year_2020.Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/queens-that-can-attack-the-king/
 *
 * @author neeraj on 22/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class QueensThatCanAttacktheKing {

    public static void main(String[] args) {
        System.out.println(queensAttacktheKing(new int[][]{
                {0, 1}, {1, 0}, {4, 0}, {0, 4}, {3, 3}, {2, 4}
        }, new int[]{0, 0}));
    }

    public static List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> queensWhichCanAttack = new ArrayList<>();
        /**
         * First Place all the queens
         */
        boolean[][] board = new boolean[8][8];
        for (int[] queen : queens) {
            board[queen[0]][queen[1]] = true;
        }

        // Now we just have to move in 8 directions
        int[][] directions = new int[][]{{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

        for (int[] direction : directions) {
            int king_x = king[0];
            int king_y = king[1];

            // Now we will keep moving into 1 direction until we find any queen or just hit the boundary
            while (king_x + direction[0] >= 0 && king_x + direction[0] < 8
                    && king_y + direction[1] >= 0 && king_y + direction[1] < 8) {
                king_x += direction[0];
                king_y += direction[1];

                if (board[king_x][king_y] == true) {
                    queensWhichCanAttack.add(Arrays.asList(king_x, king_y));
                    break; // Because once we hit a queen we don't want to proceed with that direction
                    // as other queen in same direction cannot over-cross this queen.
                }
            }
        }
        return queensWhichCanAttack;
    }
}
