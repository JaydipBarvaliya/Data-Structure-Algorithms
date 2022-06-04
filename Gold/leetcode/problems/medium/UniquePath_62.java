package com.leetcode.problems.medium;

/**
 * @author neeraj on 02/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class UniquePath_62 {

    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 2));
        System.out.println(uniquePaths(23, 12));
        System.out.println(uniquePathUsingDP(3, 2));
        System.out.println(uniquePathUsingDP(23, 12));
    }

    static int path = 0;
    public static int uniquePaths(int m, int n) {
        path = 0;
        boolean [][] visited = new boolean[m][n];
        doDFS(0,0,m,n,visited);
        return path;
    }

    public static int uniquePathUsingDP(int m, int n) {
        // If there is only 1 row
        // then there is only 1 way to reach the end in right

        // Similarly if there is only 1 column
        // there is only 1 way to reach the end in bottom
        int [][] uniquePaths = new int[m][n];

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(i ==0 || j==0) {
                    uniquePaths[i][j] = 1;
                } else {
                    uniquePaths[i][j] = uniquePaths[i-1][j] + uniquePaths[i][j-1];
                }
            }
        }
        return uniquePaths[m-1][n-1];
    }

    public static void doDFS(int x, int y, int m, int n, boolean [][] visited) {
        visited[x][y] = true;
        if(x == m-1 && y == n-1) {
            path++;
        }


        // We have to just go in 2 directions;
        if(isSafe(x+1, y, m , n, visited)) {
            doDFS(x+1, y, m, n,visited);
        }
        if(isSafe(x, y+1, m, n,visited)) {
            doDFS(x, y+1, m , n,visited);
        }

        visited[x][y] = false;
    }

    public static boolean isSafe(int x, int y, int m, int n, boolean [][] visited) {
        return x >= 0 && y >= 0 && x < m && y < n && visited[x][y] == false;
    }
}
