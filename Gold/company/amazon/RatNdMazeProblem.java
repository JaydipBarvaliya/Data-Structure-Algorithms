package com.company.amazon;

import com.geeksforgeeks.array.ArrayRotation;
import com.geeksforgeeks.array.Rotate2DMatrix;

public class RatNdMazeProblem {

    public static void main(String[] args) {
        int maze[][] = {{1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}};

        solveRatInAMazeProblem(maze);
    }

    public static void solveRatInAMazeProblem(int[][] maze) {
        int[][] solution = new int[maze.length][maze[0].length];
        if (solvePuzzle(maze, 0, 0, solution)) {
            Rotate2DMatrix.print2DArray(solution);
        } else {
            System.out.println("RAT can't go to end");
        }

    }

    private static boolean solvePuzzle(int[][] maze, int x, int y, int[][] solution) {
        // Check if you reached to the end of puzzle
        if (x == maze.length - 1 && y == maze[0].length) {
            return true;
        }

        if (isSafe(x, y, maze)) {
            solution[x][y] = 1; // Using this cell in the solution

            if (solvePuzzle(maze, x, y + 1, solution)) {
                return true;
            }
            if (solvePuzzle(maze, x + 1, y, solution)) {
                return true;
            }

            solution[x][y] = 0; // Since we couldn't achieve solution using this cell so remove it from the solution
            return false;
        }
        return false;
    }

    private static boolean isSafe(int x, int y, int[][] maze) {
        return x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 1;
    }
}
