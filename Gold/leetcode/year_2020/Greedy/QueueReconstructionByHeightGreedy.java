package com.leetcode.year_2020.Greedy;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author neeraj on 25/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class QueueReconstructionByHeightGreedy {

    public static void main(String[] args) {
        int[][] afterReconstruction = reconstructQueue(new int[][]{
                {7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}
        });
        LogUtil.printMultiDimensionArray(afterReconstruction);
    }

    public static int[][] reconstructQueue(int[][] people) {
        /**
         * So we'll sort the people based on their height in descending order..... (and if height is same sort ASC in K's part)
         * since we already know who all taller people are present already.... it becomes easier to place the smaller child
         * since we know exactly where to put so that placement matches k's policy.
         *
         * In case of same height it's obvious to place lower k first......
         *
         * Take an example:
         * [5,1| 5,2 | 5,0]  (h=height, k=no of equal height or greater height than k]
         *
         * How will you re-arrange
         * [5,0 | 5,1 | 5,2] ... So what is the observation here if all heights are same we just have to arrange them
         *                         in increasing order of their k value.
         *
         * Another example
         * [4,1 | 5,1 | 5,0]
         *
         * How we will arrange
         * [5,0 | 4,1 | 5,1] .......So what did we do here first sort the people based on their height and if same height
         *                          incremental k value.
         *
         * After sorting [5,0 | 5,1 | 4,1] ....Is 4 on the correct placement, No because it's k value = 1 but actual k value = 2 at this position
         *
         * So we have to place [4,1] at the 1st index. and why it won't affect 5's placement because a taller person can only see
         * a person of same height or greater height.
         *
         * Representation.
         *
         * ||                   ||
         * ||       ||          ||
         * ||       ||          ||
         * ||       ||          ||
         * ||       ||          ||
         * 5,0      4,1         5,1
         * -------------------------
         */
        // Sort in Decrementing height and incrementing K value.
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });

        List<int[]> reconstructedQueue = new ArrayList<>();
        for (int i = 0; i < people.length; i++) {
            reconstructedQueue.add(people[i][1], people[i]);
        }
        return reconstructedQueue.toArray(new int[reconstructedQueue.size()][2]);
    }
}
