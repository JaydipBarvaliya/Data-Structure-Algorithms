package com.leetcode.year_2020.graph;

/**
 * @author neeraj on 28/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumSwapsRequiredToSortArray {

    public static void main(String[] args) {
//        System.out.println(findMinSwaps(new int[]{4, 3, 2, 1}));
        System.out.println(findMinSwaps(new int[]{1, 4, 3, 2}));
        System.out.println(findMinSwaps(new int[]{1, 5, 4, 3, 2}));
        System.out.println(findMinSwaps(new int[]{1, 2, 3, 4, 5}));
        System.out.println(findMinSwaps(new int[]{3, 5, 2, 1, 4}));
    }

    public static int findMinSwaps(int[] unsortedArray) {
        int swapCount = 0;
        boolean[] visited = new boolean[unsortedArray.length];

        for (int i = 0; i < unsortedArray.length && !visited[i]; i++) {
            int elementAtIthPosition = unsortedArray[i];
            visited[i] = true;
            if (elementAtIthPosition - 1 == i) { // why -1? since elements are from 1...to....N
                continue;
            } else { // This item is not at it's correct position, so let's find it's correct positions.
                while (!visited[elementAtIthPosition - 1]) {
                    visited[elementAtIthPosition - 1] = true;
                    swapCount++;
                    elementAtIthPosition = unsortedArray[elementAtIthPosition - 1];
                }
            }
        }
        return swapCount;
    }
}
