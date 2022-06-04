package com.leetcode.year_2020.DP;

/**
 * https://leetcode.com/problems/jump-game-iii/
 *
 * @author neeraj on 31/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class JumpGame3 {

    public static void main(String[] args) {
        System.out.println(canReach(new int[]{4, 2, 3, 0, 3, 1, 2}, 5));
        System.out.println(canReach(new int[]{4, 2, 3, 0, 3, 1, 2}, 0));
        System.out.println(canReach(new int[]{3, 0, 2, 1, 2}, 2));
    }

    static Boolean[] cache;

    public static boolean canReach(int[] arr, int start) {
        cache = new Boolean[arr.length];
        boolean[] visited = new boolean[arr.length];
        return canReach(arr, start, visited);
    }

    private static boolean canReach(int[] arr, int start, boolean[] visited) {
        if (cache[start] != null) {
            return cache[start];
        }
        if (arr[start] == 0) {
            return true;
        }
        visited[start] = true;
        if (isSafe(start + arr[start], arr, visited)) {
            if (canReach(arr, start + arr[start], visited)) {
                return true;
            }
        }
        if (isSafe(start - arr[start], arr, visited)) {
            if (canReach(arr, start - arr[start], visited)) {
                return true;
            }
        }
        cache[start] = false;
        return false;
    }

    private static boolean isSafe(int index, int[] arr, boolean[] visited) {
        return index >= 0 && index < arr.length &&
                !visited[index];
    }
}
