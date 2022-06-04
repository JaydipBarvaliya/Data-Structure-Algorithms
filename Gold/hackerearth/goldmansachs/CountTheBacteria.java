package com.hackerearth.goldmansachs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author neeraj on 13/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CountTheBacteria {

    static int[][] neighbours = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

    public static void main(String[] args) {
        System.out.println(computeCount(4, 3));
        System.out.println(computeCount(5, 4));
        System.out.println(computeCount(6, 5));
        System.out.println(computeCount(7, 6));
        System.out.println(computeCount(8, 7));
        System.out.println(computeCount(9, 8));
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static long computeCount(int m, int n) {
        int[][] board = new int[m][n];
        board[m - 1][0] = 1;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(m - 1, 0));
        queue.add(null);
//        computeCountUtil(stack, board, n - 1);

        int secondCounter = 0;
        //reproduce all existing bacteria
        while (!queue.isEmpty()) {
            Point coordinates = queue.poll();

            if (coordinates == null) {
                secondCounter++;
                if (secondCounter == n - 1) {
                    break;
                } else {
                    queue.add(null);
                }
                continue;
            }

            int x = coordinates.x;
            int y = coordinates.y;


            // Look into all around in 8 directions
            for (int i = 0; i < neighbours.length; i++) {
                int[] neighbour = neighbours[i];
                if (x + neighbour[0] >= 0 && x + neighbour[0] < board.length &&
                        y + neighbour[1] >= 0 && y + neighbour[1] < board[0].length) {
                    queue.add(new Point(x + neighbour[0], y + neighbour[1]));
                    board[x + neighbour[0]][y + neighbour[1]]++;
                }
            }
            board[x][y] = 0;
        }

        return board[m - 1][n - 1];
    }


    private static void computeCountUtil(Queue<Point> queue, int[][] board, int seconds) {
        // If there are no seconds left, so bacteria cannot reproduce more.
        if (seconds < 1) {
            return;
        }

        //bacteria after splitting
        List<Point> adjacentPoints = new ArrayList<>();

        //reproduce all existing bacteria
        while (!queue.isEmpty()) {
            Point coordinates = queue.poll();
            int x = coordinates.x;
            int y = coordinates.y;


            // Look into all around in 8 directions
            for (int i = 0; i < neighbours.length; i++) {
                int[] neighbour = neighbours[i];
                splitBacterias(board, x + neighbour[0], y + neighbour[1], adjacentPoints);
            }
            board[x][y] = 0;
        }

        //add all adjacent cell to stack
        for (Point adjacentCell : adjacentPoints) {
            queue.add(adjacentCell);
        }

        //reduce seconds by 1
        computeCountUtil(queue, board, seconds - 1);

    }

    private static void splitBacterias(int[][] board, int x, int y, List<Point> adjacentPoints) {
        int m = board.length;
        int n = board[0].length;

        if (x >= 0 && x < m && y >= 0 && y < n) {
            board[x][y]++;
            adjacentPoints.add(new Point(x, y));
        }
    }

}
