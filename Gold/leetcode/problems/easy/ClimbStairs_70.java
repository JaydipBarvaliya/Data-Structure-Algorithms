package com.leetcode.problems.easy;

/**
 * @author neeraj on 01/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ClimbStairs_70 {

    public static void main(String[] args) {
        System.out.println(climbStairs(2));
    }

    public static int climbStairs(int n) {
        int totalDistinctWays = 0;

        int [] ways = new int[n+1];
        ways[0] = 1;
        for(int i=1;i<ways.length;i++) {
            for(int j=0;j<i;j++) {
                if(i - j <=2) {
                    ways[i] += ways[j];
                }
            }
        }
        return ways[ways.length - 1];
    }
}
