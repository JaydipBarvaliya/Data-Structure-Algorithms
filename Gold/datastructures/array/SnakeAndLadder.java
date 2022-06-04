package com.datastructures.array;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by jaine03 on 03/09/17.
 */
public class SnakeAndLadder {

    static class BoardEntry {
        int vertex;
        int distFromSource;
    }

    public static void main(String[] args) {
        int[] boardMoves = new int[30];

        for (int i = 0; i < boardMoves.length; i++) {
            boardMoves[i] = -1;
        }

        // Ladders
        boardMoves[2] = 21;
        boardMoves[4] = 7;
        boardMoves[10] = 25;
        boardMoves[19] = 28;

        // Snakes
        boardMoves[26] = 0;
        boardMoves[20] = 8;
        boardMoves[16] = 3;
        boardMoves[18] = 6;

        System.out.println(getMinNumberOfDiceThrows(boardMoves, 30));

    }

    public static int getMinNumberOfDiceThrows(int[] boardMoves, int N) {
        Queue<BoardEntry> queue = new LinkedList<>();
        int [] visited = new int[N];
        BoardEntry entry = new BoardEntry();
        entry.vertex = 0;
        entry.distFromSource = 0;

        visited[0] = 1;
        queue.add(entry);

        while (!queue.isEmpty()) {

            entry = queue.remove();
            int vertex = entry.vertex;

            if (vertex == N - 1)
                break;
            for (int i = vertex + 1; i < N && i <= vertex + 6; i++) {
                if (visited[i] != 1) {
                    BoardEntry be = new BoardEntry();
                    be.distFromSource = entry.distFromSource + 1;
                    visited[i] = 1;
                    if (boardMoves[i] != -1) {
                        be.vertex = boardMoves[i];
                    } else {
                        be.vertex = i;
                    }
                    queue.add(be);
                }
            }
        }
        return entry.distFromSource;
    }
}
