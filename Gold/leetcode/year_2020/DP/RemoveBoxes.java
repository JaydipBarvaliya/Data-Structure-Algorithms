package com.leetcode.year_2020.DP;

/**
 * https://leetcode.com/problems/remove-boxes/
 * Best explanation by Tanishq Chaudhary https://www.youtube.com/watch?v=_8hSyaxVRZ8
 */
public class RemoveBoxes {

    public static void main(String[] args) {
        System.out.println(removeBoxes(new int[]{2, 2, 2, 3, 3, 4, 4, 4, 4}));
        System.out.println(removeBoxes(new int[]{2, 2, 2, 3, 3, 4, 4, 4, 4, 2, 2, 2}));
    }

    static int[][][] dp;

    public static int removeBoxes(int[] boxes) {
        /**
         * Let's start with simple case of
         * I/p==================================>   2 2 2 | 3 3 | 4 4 4 4
         *                                         -----------------------
         * what will be the answer in this case,     3*3  + 2*2 + 4*4
         *
         * we should start from left to right and calculate how many similar elements we have
         *
         * Now the tricky part comes when you don't have this beautiful input, and the numbers are scattered
         *
         *   ========================> 2 2 2 | 3 3 | 4 4 4 4 | 2 2 2
         *  with our usual approach     3*3  + 2*3 +   4*4   + 3*3
         *
         *  But the best answer is 6*6 + 2*3 + 4*4 ... How ???
         *
         *               <====if we can make this jump===>
         *              //                              \\
         *             2 2 2                           2 2 2
         *              =====\                       /======
         *                    \                     /
         *                     \    (Valley)       /
         *                      \  3 3 | 4 4 4 4 /
         *
         * Now if we can remove 3 and 4 boxes before 2 that way we can enjoy 2s frequency as 6, so basically we have to
         * find out if we have any box after boxes[l] == boxes[l+1] loop ends, that do we have any item similar to boxes[l]
         * then we can simply have our answer.
         */
        int count = 0;
        dp = new int[101][101][101];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                for (int k = 0; k < dp[i][j].length; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return calculatePointsCollected(boxes, 0, boxes.length - 1, count);
    }

    public static int calculatePointsCollected(int[] boxes, int left, int right, int count) {
        if (left > right) return 0;
        if (dp[left][right][count] != -1) return dp[left][right][count];

        int leftCopy = left;
        int rightCopy = right;
        int countCopy = count; // since the below loop will modify left and right, hence we need to store the copy
        while (left + 1 <= right && boxes[left + 1] == boxes[left]) {
            left += 1;
            count++;
        }
        // Since left and left+1 must have broken at 2 | 3 point, so count only got incremented twice,
        // hence let's increment the count + 1 to consider last item when the loop break.
        count += 1;
        // Since whichever box we remove [we compress the all same value boxes and get k*k points in favor of removing k boxes]
        int answer = (count * count) + calculatePointsCollected(boxes, left + 1, right, 0); // since for a new value starting count shoule be 0

        for (int m = left + 1; m <= right; m++) {
            if (boxes[m] == boxes[left]) {
                answer = Math.max(answer, calculatePointsCollected(boxes, m, right, count)  // This is actually for the jump I showed in diagram
                        + calculatePointsCollected(boxes, left + 1, m - 1, 0)); // This is whatever left in between the valley [left+1---------m-1]
            }
        }
        return dp[leftCopy][rightCopy][countCopy] = answer;
    }
}
