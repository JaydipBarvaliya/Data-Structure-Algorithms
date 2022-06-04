package com.interviewbit.math;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.interviewbit.com/problems/grid-unique-paths/
 * <p>
 * This problem is similar to RatndMaze Problem
 *
 * @author neeraj on 2019-07-28
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class GridUniquePaths {


    public static void main(String[] args) {
        System.out.println(uniquePaths(2, 2));
        System.out.println(uniquePaths(3, 3));
    }

    static int totalUniquePaths = 0;
    static List<List<PathCoordinates>> allPaths = new ArrayList<>();

    public static int uniquePaths(int X, int Y) {
        LogUtil.logIt("Finding Grid Unique Paths for grid of [" + X + "," + Y + "]");
        totalUniquePaths = 0;
        allPaths = new ArrayList<>();
        traverseMaze(0, 0, X, Y, new ArrayList<>());
        System.out.println(allPaths);
        return totalUniquePaths;
    }

    public static void traverseMaze(int X, int Y, int N1, int N2, List<PathCoordinates> currentPath) {
        if (X == N1 - 1 && Y == N2 - 1) {
            totalUniquePaths++;
            List<PathCoordinates> clonedList = new ArrayList<>(currentPath);
            clonedList.add(new PathCoordinates(X, Y));
            allPaths.add(clonedList);
            return;
        }

        // Let's traverse the whole maze but just check if it is safe to traverse the maze
        if (isSafeToMove(X, Y, N1, N2)) {
            PathCoordinates pathCoordinates = new PathCoordinates(X, Y);
            currentPath.add(pathCoordinates);
            // So let's go down first.
            traverseMaze(X + 1, Y, N1, N2, currentPath);

            // Now let's go right side.
            traverseMaze(X, Y + 1, N1, N2, currentPath);

            // We traversed the path with this PathCoordinates let's remove it from path
            currentPath.remove(pathCoordinates);
        }

    }

    private static boolean isSafeToMove(int x, int y, int n1, int n2) {
        return x >= 0 && y >= 0 && x < n1 && y < n2;
    }
}

class PathCoordinates {
    int X;
    int Y;

    public PathCoordinates(int x, int y) {
        X = x;
        Y = y;
    }

    @Override
    public String toString() {
        return "(" + X + "," + Y + ")";
    }
}
