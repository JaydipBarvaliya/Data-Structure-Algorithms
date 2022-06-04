package com.leetcode.year_2020.graph.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author neeraj on 24/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SnakeAndLadder {

    public static void main(String[] args) {
        int[] moves = new int[30];
        Arrays.fill(moves, -1);

        // Ladders
        moves[2] = 21;
        moves[4] = 7;
        moves[10] = 25;
        moves[19] = 28;

        // Snakes
        moves[26] = 0;
        moves[20] = 8;
        moves[16] = 3;
        moves[18] = 6;

        System.out.println(getMinDiceThrows(moves, moves.length));
    }

    static class DiceThrow {
        int boardCell;
        int throwCount;

        public DiceThrow(int boardCell, int throwCount) {
            this.boardCell = boardCell;
            this.throwCount = throwCount;
        }
    }

    /**
     * Moves contain snake and ladder and -1 for rest of Saanp Sidhi game :)
     * Also it's not really a 2d board. you can simply solve this using 1d Matrix.
     */
    public static int getMinDiceThrows(int move[], int n) {
        /**
         * We will do BFS in this since question is asking for Minimum Number of Dice Throws.
         */
        Queue<DiceThrow> queue = new LinkedList<>();
        queue.add(new DiceThrow(0, 0)); // Since we can reach to 1st Cell without any dice throw.
        boolean[] visited = new boolean[n + 1];
        DiceThrow diceThrow = null;
        while (!queue.isEmpty()) {
            diceThrow = queue.poll();

            // Did we reach end.
            if (diceThrow.boardCell == n - 1) {
                break;
            }

            // We can go upto 6 cells from start. (Dice has 6 faces.)
            for (int i = diceThrow.boardCell + 1; i <= diceThrow.boardCell + 6 && i < n; i++) {
                if (!visited[i]) {
                    // First check are we standing on snake or ladder
                    int nextCellToLand = (move[i] != -1) ? move[i] : i;
                    visited[nextCellToLand] = true;
                    DiceThrow nextDiceThrow = new DiceThrow(nextCellToLand,
                            diceThrow.throwCount + 1);
                    queue.add(nextDiceThrow);
                }
            }
        }
        // This is the minimum throw count you need.
        return diceThrow.throwCount;
    }
}
