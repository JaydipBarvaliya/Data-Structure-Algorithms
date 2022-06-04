package com.company.amazon;

import java.util.LinkedList;
import java.util.Queue;

public class SnakeNdLadder {

    static class SnakeNLadder {
        int V; // current Vertex
        int dist; // Distance of current vertex from Source Vertex

        public SnakeNLadder() {
        }

        public SnakeNLadder(int v, int dist) {
            V = v;
            this.dist = dist;
        }
    }

    public static void main(String[] args) {
        int N = 30;
        int moves[] = new int[30];
        for (int i = 0; i < N; i++)
            moves[i] = -1;

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

        solveSnakeAndLadder(moves);
    }

    public static void solveSnakeAndLadder(int[] moves) {
        System.out.println(getMinimumNoOfThrows(moves));
    }

    private static int getMinimumNoOfThrows(int[] moves) {
        Queue<SnakeNLadder> queue = new LinkedList<>();
        SnakeNLadder first = new SnakeNLadder(0, 0);
        queue.add(first);
        boolean[] visited = new boolean[moves.length];
        visited[0] = true; // We are starting from this node

        while (!queue.isEmpty()) {
            first = queue.poll();
            int vertex = first.V;
            if (first.V == moves.length - 1) {
                break; // Reached at the end
            }

            // Now we will check next 6 dice position
            for (int i = (vertex + 1); i < moves.length && i < (vertex + 6); i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    SnakeNLadder newDiceThrow = new SnakeNLadder();
                    newDiceThrow.dist = first.dist + 1;

                    if (moves[i] != -1) { //  Either snake or Ladder
                        newDiceThrow.V = moves[i];
                    } else {
                        newDiceThrow.V = i;
                    }
                    queue.add(newDiceThrow);
                }
            }
        }
        return first.dist;
    }

}
