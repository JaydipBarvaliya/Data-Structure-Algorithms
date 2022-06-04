package com.leetcode.year_2020.stack;

import com.util.LogUtil;

import java.util.Stack;

/**
 * @author neeraj on 14/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaximumAreaOfHistogram {

    public static void main(String[] args) {
        System.out.println(findMaxArea(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(findMaxArea(new int[]{1}));
        System.out.println(findMaxArea(new int[]{}));
        System.out.println(findMaxArea(new int[]{3, 6, 5, 7, 4, 8, 1, 0}));
        System.out.println(findMaxArea(new int[]{2, 1, 2}));
        System.out.println(findMaxArea(new int[]{6, 2, 5, 4, 5, 1, 6}));
        System.out.println(findMaxArea(new int[]{1, 1}));
        System.out.println(findMaxArea(new int[]{2, 2, 2, 2}));

        LogUtil.logIt("Hello .......Checking O(N) solution...");
        System.out.println(findMaxArea_O_N(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(findMaxArea_O_N(new int[]{1}));
        System.out.println(findMaxArea_O_N(new int[]{}));
        System.out.println(findMaxArea_O_N(new int[]{3, 6, 5, 7, 4, 8, 1, 0}));
        System.out.println(findMaxArea_O_N(new int[]{2, 1, 2}));
        System.out.println(findMaxArea_O_N(new int[]{6, 2, 5, 4, 5, 1, 6}));
        System.out.println(findMaxArea_O_N(new int[]{1, 1}));
        System.out.println(findMaxArea_O_N(new int[]{2, 2, 2, 2}));
    }


    public static int findMaxArea(int[] height) {
        /**
         * Input: [2,1,5,6,2,3]
         * Output: 10
         *
         * Now the area can grow/expand in both directions left and right, assume we start at index[1] where value is also 1.
         * So the area can be    1<- 1 ------>5 (Total 6) Area of Rectangle = height * width.
         *
         * So we have to essentially look out in both the directions and maintain the area.
         * How far i can go in right.....upto Nearest Smallest Element in Right
         * How far i can go in left .....upto Nearest Smallest Element in left
         */
        Stack<Integer> stack = new Stack<>();
        int[] right = new int[height.length]; // Store Nearest Smaller Element in Right (NSR)
        int[] left = new int[height.length]; // Store Nearest Smaller Element in the Left(NSL).

        // Note we are storing index in NSR, as it helps to calculate the SPAN.

        // Populate NSR
        for (int i = height.length - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                right[i] = height.length; // Since no element in the array seems to be smallest so we will take imaginary height after the array.
            } else {
                while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    right[i] = height.length;  // Since no element in the array seems to be smallest so we will take imaginary height after the array.
                } else {
                    right[i] = stack.peek();
                }
            }
            stack.push(i);
        }

        stack = new Stack<>();
        // Populate NSL
        for (int i = 0; i < height.length; i++) {
            if (stack.isEmpty()) {
                left[i] = -1;
            } else {
                while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    left[i] = -1;
                } else {
                    left[i] = stack.peek();
                }
            }
            stack.push(i);
        }

        /**
         * Now we have both left and right nearest smallest element stored in left and right array.
         *  *       /---------(-1 is also Imaginary Index)
         *  Index -1 0  1  2  3  4  5  6  7 ===> (7 is imaginary Index)
         *  Height   6  2  5  4  5  1  6
         *  NSR      1  5  3  5  5  7  7   ===> Storing Index
         *  NSL     -1 -1  1  1  3  -1 5   ===> Storing Index
         *  ------------------------------------
         *
         *  -------------------------------------
         *
         *
         *  Now assume we are standing at height 4... how far we can go in left... only upto height 5 if we
         *  go beyond that we can't expand our area.... similarly we can expand upto height 5 in right as well
         *  So what is the actual area covered.
         *
         *              5 4 5
         *              -----  (this much width i.e. width of 3 and height is 4 so area 12)
         *
         *   How to get the width... that will come (NSR - NSL - 1) : Why -1 since you can't include NSR in the area,
         *                                                          as that is smallest than the height and can't contribute to the Area.
         *   For 4 the NSR is  = 5
         *   For 4 the NSL is  = 1
         *
         *   So how many buildings in between ( 5 - 1 - 1) = 3. So Area 3 * 4 = 12.
         *
         */
        int MAX_AREA = Integer.MIN_VALUE;
        for (int i = 0; i < right.length; i++) {
            int widthAtI = (right[i] - left[i]) - 1;
            MAX_AREA = Math.max((widthAtI * height[i]), MAX_AREA);
        }
        return MAX_AREA == Integer.MIN_VALUE ? 0 : MAX_AREA;
    }

    public static int findMaxArea_O_N(int[] height) {
        if (height.length == 0) return 0;
        /**
         * We need to find nearest smallest element to the left
         * and nearest smallest element to the right, then width for any i will be just
         * width = (NEAREST_SMALLEST_RIGHT[i] - Nearest_SMALLEST_LEFT[i] - 1);
         *
         * Now let's calculate NSR and NSL efficiently without doing o(n^2) work and memorizing the previous work.
         */
        int[] NEAREST_SMALLEST_RIGHT = new int[height.length];
        int[] NEAREST_SMALLEST_LEFT = new int[height.length];

        NEAREST_SMALLEST_LEFT[0] = -1; // Since we know you have no smaller element for the first element
        NEAREST_SMALLEST_RIGHT[NEAREST_SMALLEST_RIGHT.length - 1] = height.length; // similarly for last element nothing on the right side.

        // First find smallest in left
        for (int i = 1; i < height.length; i++) {
            int j = i - 1;
            while (j >= 0 && height[j] >= height[i]) {
                // This is memorization , what we are saying here is that if for height[j] we already know,
                // what is the smallest element to the left of this guys, then we should directly jump on that instead of
                // decrementing it by 1 every-time.
                j = NEAREST_SMALLEST_LEFT[j];
            }
            NEAREST_SMALLEST_LEFT[i] = j;
        }

        // Now find smallest in right
        for (int i = height.length - 2; i >= 0; i--) {
            int j = i + 1;
            while (j < height.length && height[j] >= height[i]) {
                j = NEAREST_SMALLEST_RIGHT[j];
            }
            NEAREST_SMALLEST_RIGHT[i] = j;
        }

        int maxWidth = Integer.MIN_VALUE;
        for (int i = 0; i < height.length; i++) {
            maxWidth = Math.max(maxWidth, height[i] * (NEAREST_SMALLEST_RIGHT[i] - NEAREST_SMALLEST_LEFT[i] - 1));
        }
        return maxWidth;
    }
}
