package com.leetcode.problems.easy;

import java.util.Arrays;

/**
 * @author neeraj on 18/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FindTheDifference_389 {

    public static void main(String[] args) {
        System.out.println(findTheDifference("abcd", "abcde"));
    }

    public static char findTheDifference(String s, String t) {
        char [] small = s.toCharArray();
        char [] big = t.toCharArray();
        Arrays.sort(small);
        Arrays.sort(big);

        for(int i=0;i<big.length;i++) {
            if(i >= small.length) {
                return big[i];
            }
            if(small[i] != big[i]) {
                return big[i];
            }
        }
        return Character.MIN_VALUE;
    }
}
