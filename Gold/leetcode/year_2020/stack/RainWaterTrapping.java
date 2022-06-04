package com.leetcode.year_2020.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 *
 * @author neeraj on 14/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RainWaterTrapping {

    public static void main(String[] args) {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    public static int trap(int[] height) {
        /**
         * Below is our building.
         * Now if i have to calculate the water stored
         * between building of height 3 and 4.
         * It's not a plain land [0 0 0] instead it's [0 1 0]
         * i.e we can't store 3*3 = 9 units of water... it's actually 8.
         *
         * So we have to consider water above each building and them sum them together.
         *
         * Now how much water every building can contribute to .
         * If you take building 1 height.... it can take 2 units more.... since on it's left maximum height is 3 and
         * on it's right max-height is 4. it is goes beyond that water will spill. if it undershoots that's waste of space.
         *
         * Hence at each building we have to maintain leftMax and Right Max
         * waterBuildingAt"I"CanContribute = Min(LeftHeight, RightHeight) - height[i]
         *
         */

//                            _____
//            ____            |    |
//            |  |            |    |
//            |  |            |    |
//            |  |    ___     |    |
//          __|  |____|  |____|    |__
//           0  3  0   1   0    4
        int leftMax = Integer.MIN_VALUE;
        int rightMax = Integer.MIN_VALUE;
        int left = 0;
        int right = height.length - 1;
        int totalWaterCollected = 0;

        while (left < right) {
            if (height[left] <= height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                totalWaterCollected += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                totalWaterCollected += rightMax - height[right];
                right--;
            }
        }
        return totalWaterCollected;
    }
}
