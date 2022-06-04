package com.leetcode.year_2020;

import java.util.Arrays;

/**
 * @author neeraj on 19/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class Candy {

    public static void main(String[] args) {
        System.out.println(candy(new int[]{1, 2, 3}));
        System.out.println(candy(new int[]{5, 4, 3}));
        System.out.println(candy(new int[]{1, 3, 4, 5, 2}));
        System.out.println(candy(new int[]{1, 6, 10, 8, 7, 3, 2}));
    }

    public static int candy(int[] ratings) {
        if (ratings.length == 1)
            return 1;

        // We can do this in 2 pass
        int[] count = new int[ratings.length];
        Arrays.fill(count, 1); // At-least we need to give 1 candy
        for (int i = 1; i < ratings.length; i++) {
            count[i] = ratings[i] > ratings[i - 1] ? 1 + count[i - 1] : count[i];
        }

        // Second pass
        for (int i = ratings.length - 2; i >= 0; i--) {
            count[i] = ratings[i] > ratings[i + 1] ? Math.max(1 + count[i + 1], count[i]) : count[i];
        }

        int sum = 0;
        for (int i : count) {
            sum += i;
        }
        return sum;
    }
}
