package com.leetcode.year_2020;

import com.util.LogUtil;

/**
 * @author neeraj on 21/03/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CanPlaceFlowers {

    public static void main(String[] args) {
        testIt(new int[]{1, 0, 0, 0, 1}, 1);
        testIt(new int[]{1, 0, 0, 0, 1}, 2);
        testIt(new int[]{0}, 1);
        testIt(new int[]{0, 0}, 2);
        testIt(new int[]{1}, 0);
        testIt(new int[]{0, 1, 0}, 1);
    }

    public static void testIt(int[] flowerbed, int n) {
        System.out.println("Can we place {" + n + "} in flowerbed " +
                LogUtil.getArrayAsString(flowerbed) + " ? " + canPlaceFlowers(flowerbed, n));
    }

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length && count < n; i++) {
            if (flowerbed[i] == 0) {
                if (canPlaceFlowerAtThisLocation(i, flowerbed)) {
                    flowerbed[i] = 1;
                    count++;
                }
            }
        }
        return count == n;
    }

    private static boolean canPlaceFlowerAtThisLocation(int j, int[] flowerbed) {
        int next = (j + 1) == flowerbed.length ? 0 : flowerbed[j + 1];
        int prev = (j - 1) == -1 ? 0 : flowerbed[j - 1];
        return next == 0 && prev == 0;
    }
}
