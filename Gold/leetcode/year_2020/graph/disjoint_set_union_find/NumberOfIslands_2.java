package com.leetcode.year_2020.graph.disjoint_set_union_find;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/number-of-islands-ii/#/description
 * https://tenderleo.gitbooks.io/leetcode-solutions-/GoogleMedium/305.html
 * <p>
 * Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]. Initially, the 2d grid grid is filled with water.
 * (Assume 0 represents water and 1 represents land).
 * 0 0 0
 * 0 0 0
 * 0 0 0
 *
 * @author neeraj on 29/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NumberOfIslands_2 {

    public static void main(String[] args) {
        System.out.println(numIslands2(3, 3, new int[][]{
                {0, 0},
                {0, 1},
                {1, 0},
                {1, 2},
                {2, 1}
        }));
    }

    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        /**
         * Initially grid is completely filled with water i.e 0
         * we need to add land as prescribed in positions and then just tell number of islands after
         * every land construction from positions.
         *
         * We can use DFS with every operation but the time complexity will become O(operation * (|V| + |E|))
         * So we will leverage Disjoint Set's Union by rank and find operations to do it in O(kmn)
         */

        // We can turn a 2d matrix in 1d matrix of size [m*n]
        // and we every position in 2d matrix will be denoted in 1d matrix by
        // (Col * Row_i + Col_j).
        // So 2d matrix like this
        // (0,0) , (0,1), (0,2)
        // (1,0) , (1,1), (1,2)
        // (2,0) , (2,1), (2,2)
        // Here m = 3 and n = 3
        // n*(0)+0, n*(0)+1 ,n*(0)+2,n*(1)+0,n*(1)+1,n*(1)+2,n*(2)+0,n*(2)+1,n*(2)+2,
        // 0,1,2,3,4,5,6,7,8
        // This is how you represent 2d matrix in 1d.
        int[] lands = new int[m * n];

        Arrays.fill(lands, -1); // Initially nothing is present in lands array.

        // Also we know land is surrounded by water in 4 directions(Top, Right, Bottom, Left>)
        // So we will try for each land in all 4 directions and see if this land can become part of some existing
        // island or will it make it's own island
        int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //Top, Right, Bottom, Left

        int islandCount = 0;
        List<Integer> result = new ArrayList<>();

        for (int[] position : positions) {
            int land_position_x = position[0];
            int land_position_y = position[1];

            islandCount++;
            lands[n * land_position_x + land_position_y] = n * land_position_x + land_position_y;

            // Now lets check in all 4 directions
            for (int k = 0; k < directions.length; k++) {
                int new_land_position_x = land_position_x + directions[k][0];
                int new_land_position_y = land_position_y + directions[k][1];

                // if we have a safe check to explore kth direction
                if (new_land_position_x >= 0 && new_land_position_x < m &&
                        new_land_position_y >= 0 && new_land_position_y < n
                        && lands[n * new_land_position_x + new_land_position_y] != -1) { // Why != -1 because then only we know that the other element has already created it's island
                    // and new_land_position can club into it.

                    // this means we can club with some other existing island
                    islandCount--;
                    unionClubIsland(lands, lands[n * land_position_x + land_position_y], lands[n * new_land_position_x + new_land_position_y]);
                }
            }
            result.add(islandCount);
        }
        return result;
    }

    private static void unionClubIsland(int[] lands, int existingIsland, int newLandToBeClubbed) {
        for (int i = 0; i < lands.length; i++) {
            if (lands[i] == newLandToBeClubbed) {
                lands[i] = existingIsland;
            }
        }
    }

}
