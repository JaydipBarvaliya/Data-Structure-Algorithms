package com.leetcode.problems.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author neeraj on 15/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumNumbersOfArrowsToBurstBallons_452 {

    public static void main(String[] args) {
        System.out.println(findMinArrowShots(new int[][]{
                {10, 16}, {2, 8}, {1, 6}, {7, 12}
        }));
        System.out.println(findMinArrowShots(new int[][]{
                {3, 9}, {7, 12}, {3, 8}, {6, 8}, {9, 10}, {2, 9}, {0, 9}, {3, 9}, {0, 6}, {2, 8}
        }));
        System.out.println(findMinArrowShots(new int[][]{
                {1, 2}, {2, 3}, {3, 4}, {4, 5}
        }));


    }

    public static int findMinArrowShots(int[][] points) {
        // Sort balloons based on their min height
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        minHeap.add(points[0]);

        /**
         * Found an Interesting problem while solving 2nd test case
         * We can't blindly check for just the high bound and low bound
         * this will give wrong assumption
         * Assume for point [0,9][0,6][9,10]
         * If we just compare the low of every point with the minPoint in heap
         * then with this example we only need 1 arrow, but think wisely
         * if you take an arrow at length 9 you can definitely hit [0,9] but now[9,10]
         * hence we need to update the high of current if it's less than the high of firstBalloonInLine
         */

        for (int i = 1; i < points.length; i++) {
            int[] current = points[i];
            int[] firstBalloonInLine = minHeap.remove();
            if (current[0] > firstBalloonInLine[1]) {
                minHeap.add(current);
            } else {
                if (current[1] < firstBalloonInLine[1]) {
                    firstBalloonInLine[1] = current[1];
                }
            }
            minHeap.add(firstBalloonInLine);
        }
        return minHeap.size();
    }
}
