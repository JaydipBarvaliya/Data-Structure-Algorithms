package com.leetcode.year_2020.DP;

import com.geeksforgeeks.array.LongestIncreasingSubsequence;
import com.util.LogUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/jump-game-ii/
 *
 * @author neeraj on 21/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class JumpGame_2 {

    public static void main(String[] args) {
        LogUtil.logIt("Jump using BFS.....");
        System.out.println(jumpUsingBFS(new int[]{2, 3, 1, 1, 4}));
        System.out.println(jumpUsingBFS(new int[]{1, 2, 1, 1, 1}));
        System.out.println(jumpUsingBFS(new int[]{2, 3, 4, 1, 0, 5}));
        System.out.println(jumpUsingBFS(new int[]{1, 2, 1, 1, 1}));

        LogUtil.logIt("Jump using Ladder and Stair Analogy.....");
        System.out.println(jumpUsingLadderAndStairAnalogy(new int[]{2, 3, 1, 1, 4}));
        System.out.println(jumpUsingLadderAndStairAnalogy(new int[]{1, 2, 1, 1, 1}));
        System.out.println(jumpUsingLadderAndStairAnalogy(new int[]{2, 3, 4, 1, 0, 5}));
        System.out.println(jumpUsingLadderAndStairAnalogy(new int[]{1, 2, 1, 1, 1}));

        LogUtil.logIt("Jump using DP.....");
        System.out.println(jumpsUsingDP(new int[]{2, 3, 1, 1, 4}));
        System.out.println(jumpsUsingDP(new int[]{1, 2, 1, 1, 1}));
        System.out.println(jumpsUsingDP(new int[]{2, 3, 4, 1, 0, 5}));
        System.out.println(jumpsUsingDP(new int[]{1, 2, 1, 1, 1}));
    }


        public static int jumpUsingBFS ( int[] nums){
            if (nums.length <= 1) return 0;
            /**
             * Let's do the BFS
             */
            Queue<Integer> queue = new LinkedList<>();
            queue.add(0);
            boolean[] visited = new boolean[nums.length];
            visited[0] = true;
            int jump = 1;
            queue.add(null);

            while (!queue.isEmpty()) {
                Integer poppedIndex = queue.poll();
                if (poppedIndex == null) {
                    if (!queue.isEmpty()) {
                        jump++;
                        queue.add(null);
                    }
                    continue;
                }

                boolean hasReached = false;
                for (int i = 1; i <= nums[poppedIndex]; i++) {
                    int nextIndex = poppedIndex + i;
                    if (nextIndex >= nums.length - 1) {
                        hasReached = true;
                        break;
                    }
                    if (!visited[nextIndex]) {
                        queue.add(nextIndex);
                        visited[nextIndex] = true;
                    }
                }
                if (hasReached) break;
            }
            return jump;
        }

        /**
         * https://www.youtube.com/watch?v=vBdo7wtwlXs
         */
        public static int jumpUsingLadderAndStairAnalogy ( int[] nums){
            if (nums.length <= 1) return 0;

            int ladder = nums[0]; // Initial Ladder is of size how much 1st element can take us on top.
            int stairInCurrentLadder = nums[0]; // this will be same as the current selected ladder.
            int minJump = 1;

            // Since we have already selected the first ladder and the minJump to that is just one
            // so let's start with 1st level.
            for (int currentLevel = 1; currentLevel < nums.length; currentLevel++) {
                if (currentLevel == nums.length - 1) {
                    return minJump;
                }

                ladder = Math.max(ladder, currentLevel + nums[currentLevel]);
                stairInCurrentLadder--;

                if (stairInCurrentLadder == 0) { // We have completely consumed this ladder
                    // let's jump on a new ladder.
                    minJump++;
                    // Also let's reduce the stairs we have already covered in this new stair.
                    stairInCurrentLadder = ladder - currentLevel;
                }
            }
            return minJump;
        }

        public static int jumpsUsingDP ( int[] nums){
            /**
             * This is kinda similar to {@link LongestIncreasingSubsequence}
             */
            int[] minJupms = new int[nums.length];

            // Initially all minJumps are unknown hence set to Infinity
            Arrays.fill(minJupms, Integer.MAX_VALUE);

            minJupms[0] = 0; // Since you don't need to jump to reach to level 0.

            for (int i = 1; i < nums.length; i++) {
                // Now for each i we will check for all j < i
                // and find the min jump required.
                for (int j = 0; j < i; j++) {
                    if (j + nums[j] >= i) {
                        minJupms[i] = Math.min(minJupms[j] + 1, minJupms[i]);
                    }
                }
            }
            return minJupms[minJupms.length - 1];
        }
    }
