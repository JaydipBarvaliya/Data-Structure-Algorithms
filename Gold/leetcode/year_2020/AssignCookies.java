package com.leetcode.year_2020;

import java.util.Arrays;

/**
 * @author neeraj on 26/03/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class AssignCookies {

    public static void main(String[] args) {
        testIt(new int[]{1, 2, 3}, new int[]{1, 1});
        testIt(new int[]{1, 2}, new int[]{1, 2, 3});
    }

    public static void testIt(int[] greed, int[] size) {
        System.out.println(findContentChildren(greed, size));
    }

    public static int findContentChildren(int[] greed, int[] size) {

        // Sort Children Greed Factor
        Arrays.sort(greed);
        // Sort Cookie Size
        Arrays.sort(size);
        int i = 0;

        for (int j = 0; j < size.length && i < greed.length; j++) {
            if (greed[i] <= size[j]) i++;
        }
        return i;
    }

}
