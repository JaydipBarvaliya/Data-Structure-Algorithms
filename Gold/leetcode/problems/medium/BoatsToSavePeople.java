package com.leetcode.problems.medium;

import com.util.LogUtil;

import java.util.Arrays;

/**
 * @author neeraj on 12/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class BoatsToSavePeople {

    public static void main(String[] args) {
        solve(new int[]{1, 2}, 3);
        solve(new int[]{3, 2, 2, 1}, 3);
        solve(new int[]{3, 5, 3, 4}, 5);
        solve(new int[]{1, 2, 4, 5}, 6);
    }

    private static void solve(int[] people, int limit) {
        System.out.println("Minimum Number of Rescue Boats required for people of weight " +
                LogUtil.getArrayAsString(people) + " is " + numRescueBoats(people, limit));
    }

    public static int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int minBoats = 0;
        int L = 0;
        int R = people.length - 1;
        while (L <= R) {
            if (people[L] + people[R] <= limit) {
                L++;
                R--;
            } else {
                R--;
            }
            minBoats++;
        }
        return minBoats;
    }
}
