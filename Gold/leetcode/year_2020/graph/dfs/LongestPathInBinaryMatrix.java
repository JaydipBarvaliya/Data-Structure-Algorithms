package com.leetcode.year_2020.graph.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 * <p>
 * We will print the longest path as well.
 */
public class LongestPathInBinaryMatrix {

    public static void main(String[] args) {
        System.out.println(longestIncreasingPath(new int[][]{
                        {9, 9, 4},
                        {6, 6, 8},
                        {2, 1, 1}
                }
        ));

        System.out.println(longestIncreasingPath(new int[][]{
                {19, 2, 8, 6, 4, 14, 1, 0, 17},
                {0, 1, 9, 10, 11, 4, 12, 14, 5},
                {14, 12, 16, 0, 15, 8, 5, 2, 8},
                {5, 4, 1, 17, 9, 18, 8, 5, 2},
                {9, 5, 4, 8, 16, 7, 11, 5, 0},
                {5, 7, 14, 18, 10, 0, 14, 14, 0},
                {9, 14, 4, 13, 18, 16, 9, 12, 10},
                {18, 13, 9, 18, 11, 4, 12, 10, 10},
                {7, 14, 16, 19, 10, 19, 11, 6, 4},
                {16, 2, 3, 7, 15, 9, 7, 1, 1},
                {1, 6, 16, 15, 18, 6, 6, 1, 14},
                {9, 5, 2, 9, 8, 3, 2, 3, 10},
                {2, 3, 16, 8, 7, 7, 0, 18, 16},
                {11, 0, 16, 8, 13, 13, 11, 3, 8},
                {17, 11, 0, 12, 11, 15, 12, 17, 0}}
        ));
    }

    static int LONGEST_PATH = 0;

    /**
     * 1. Do DFS from every matrix
     * 2. Use cache wherever possible
     * <p>
     * *     [
     * *         [9, 9, 4]
     * *         [6, 6, 0]
     * *        [2, 1, 1]
     * *    ]
     * <p>
     * If our matrix is this we can build the cache like this, Initially it will look like this
     * (We also know that each cell in itself is at-least 1 length long, hence you see MAX=1 initialized in DFS
     * [0, 0, 0]
     * [0, 0, 0]
     * [0, 0, 0]
     * <p>
     * When I am at (0,0) [i.e 9] I can't go to any other place greater since 9 is the biggest so the only length possible is 1
     * <p>
     * [1, 1, 0]
     * [0, 0, 0]
     * [0, 0, 0]
     * <p>
     * Now when i am at (0,2) [i.e. 4] where can I move now only to (0,1).... but we have already calculated longest distance from (0,1)
     * So with this approach we fill our cache, also make sure to add 1 since 4 got added.
     */
    public static int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] cache = new int[m][n];
        LONGEST_PATH = 0;
        List<Integer> LONGEST_PATH_VAL = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                List<Integer> path = new ArrayList<>();
                int len = doDFS(matrix, i, j, m, n, cache, path);
                LONGEST_PATH = Math.max(LONGEST_PATH, len);
                LONGEST_PATH_VAL = maxPath;
            }
        }
        System.out.println(LONGEST_PATH_VAL);
        return LONGEST_PATH;
    }

    static List<Integer> maxPath = new ArrayList<>();
    // Right, Bottom, Left, Top
    static int[][] allDirections = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int doDFS(final int[][] matrix, final int i, final int j, int m, int n, int[][] cache,
                             final List<Integer> path) {
//        if (cache[i][j] != 0) return cache[i][j];
        int max = 1; // Why 1 we know single cell is 1 length long
        path.add(matrix[i][j]);
        for (int[] dir : allDirections) {
            final int nextRow = i + dir[0];
            final int nextCol = j + dir[1];
            if (nextRow < 0 || nextCol < 0 || nextRow >= m || nextCol >= n || matrix[i][j] >= matrix[nextRow][nextCol])
                continue;
            int res = 1 + doDFS(matrix, nextRow, nextCol, m, n, cache, path); // Since current is being added so the path increases by 1.
            max = Math.max(max, res);
        }
        if (path.size() > maxPath.size()) {
            maxPath = new ArrayList<>(path);
        }
        path.remove(path.size() - 1);
        return cache[i][j] = max;
    }
}
