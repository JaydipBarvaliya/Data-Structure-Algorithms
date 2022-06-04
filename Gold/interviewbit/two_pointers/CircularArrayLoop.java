package com.interviewbit.two_pointers;

/**
 * @author neeraj on 17/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CircularArrayLoop {

    public static void main(String[] args) {
        System.out.println(circularArrayLoop(new int[]{2, -1, 1, 2, 2}));
        System.out.println(circularArrayLoop(new int[]{-1, 2}));
        System.out.println(circularArrayLoop(new int[]{-2, 1, -1, -2, -2}));
        System.out.println(circularArrayLoop(new int[]{2, 2, -1}));
        System.out.println(circularArrayLoop(new int[]{2, 3, 1, -4, -4, 2}));
    }

    public static boolean circularArrayLoop(int[] nums) {
        if (nums.length == 0) return false;
        boolean[] visited = new boolean[nums.length];
        int N = nums.length;
        boolean isForward = nums[0] > 0;
        int i = getIndex(0, nums[0], N);
        while (!visited[i]) {
//            if ((isForward && nums[i] < 0) || (!isForward && nums[i] > 0)) return false;
            visited[i] = true; // Visit the Node.
            if (i == 0) {
                return true;
            }
            i = getIndex(i, nums[i], N);
        }
        return false;
    }

    private static int getIndex(int currentIndex, int moveSteps, int N) {
        if (moveSteps > 0) { // Positive
            return (currentIndex + moveSteps) % N;
        } else { // Negative Steps.
            int finalStep = currentIndex + moveSteps;
            if (finalStep < 0) {
                return N + finalStep;
            }
            return finalStep;
        }
    }
}
