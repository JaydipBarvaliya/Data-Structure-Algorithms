package com.leetcode.year_2020.DP.combinatorics;

import com.util.LogUtil;

/**
 * https://www.geeksforgeeks.org/painting-fence-algorithm/
 * https://www.youtube.com/watch?v=deh7UpSRaEY
 *
 * @author neeraj on 06/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PaintFencingAlgorithm {

    public static void main(String[] args) {
        getNumberOfWaysToPaintFence(2, 3);
        getNumberOfWaysToPaintFence(2, 4);
        getNumberOfWaysToPaintFence(3, 2);

        LogUtil.logIt("New approach combinatorics");

        numWays(2, 3);
        numWays(5, 3);
        numWays(2, 4);
        numWays(3, 2);
    }

    /**
     * Okay so the simplest explanation to this problem is available at https://www.youtube.com/watch?v=ju8vrEAsa3Q
     * Lets try to summarize my understanding,
     * <p>
     * What we have been asked is how many total such ways are present to paint the fence in such a way that
     * "no more" than 2 consecutive fences/walls are of same color, what that means is you should calculate
     * how many ways each wall is painted and return the output.
     * <p>
     * Now we have 2 choices based on which color of 3rd wall will be decided
     * <p>
     * -> If we paint last 2 walls with the same color(Let's call that) [ii]
     * <p>
     * -> if we paint last 2 walls with different color(Let's call that) [ij]
     * <p>
     * Based on these 2 conditions we decide total ways
     * <p>
     * N=5 and K=3 [RGB]
     * <p>
     * So we have 2 take 2 cases for each wall/fence,
     * <p>
     * II(last 2 walls should be of same color) and IJ(and last 2 walls should be of different colors)
     * <p>
     * So on day 2:
     * *      II ===> should be: whatever color we chose a day before we can choose the same
     * *                So if we had 3 colors to paint on day 1, we can again use those same 3 colors on day 2 hence II on day2 ==> 3
     * <p>
     * *     IJ ===>  should be: exactly different from what we choose a day before, so if you choose Red on day1 you can't choose Red on day2
     * *                hence we can only select "K-1" colors on day 2 if we have "K" colors to choose on day 1.
     * <p>
     * * Now critical piece is on day 3 and rest day can simply follow this advice
     * * On day 3
     * * II ==> On day 2 walls will look like
     * -----> RedRed BlueBlue or GreenGreen    | Now tell me can I choose the same color as day before
     * *                                        No right else we will be breaking the rule of no more than 2 consecutive days should have same colors
     * *                                        It can't be RED_RED_RED|BLUE_BLUE_BLUE|GREEN_GREEN_GREEN
     * So what options do we have how about if we take all pairs which were on [IJ] pattern
     * *                            something like RED_BLUE | RED_GREEN | BLUE_RED | BLUE_GREEN | GREEN_BLUE | GREEN_RED
     * <p>
     * Now in order to achieve [II] pattern we can choose all the ones from day before of [IJ] patten and choose a similar color what was on day before.
     * *                     So on day 2 we have 6 ways to generate [IJ]
     * *                     and hence on day 3 we can have 6 * 1 (because we have just 1 way of choosing the color and it should be whatever was on day 2 from those IJ pattern)
     * <p>
     * * Whereas in day 3 [IJ] pattern, we can essentially take up all the colors from day 2 and just simply choose
     * *  a color which wasn't selected on day 2 and that's how we will get
     * * Total ways on day 2 = 9 So on day 3 our IJ will be (9 * 2) ---> what is 2, it's 1 less color
     * * Just remember from our problem was for N=5 and colors=3
     */
    public static int numWays(int walls, int colors) {
        if (walls == 0) return 0; // can't paint since no walls available
        if (walls == 1) return colors; // we can choose all colors just once

        // STARTING FROM WALL 2.
        int II_PATTERN_WAYS = colors; // YOU CAN CHOOSE SIMILAR COLOR AS A DAY BEFORE
        int IJ_PATTERN_WAYS = colors * (colors - 1); // You CAN only choose (color-1) as a different color on day 2.
        int TOTAL_WAYS_ON_WALL = II_PATTERN_WAYS + IJ_PATTERN_WAYS;

        for (int i = 3; i <= walls; i++) {
            II_PATTERN_WAYS = IJ_PATTERN_WAYS;
            IJ_PATTERN_WAYS = TOTAL_WAYS_ON_WALL * (colors - 1);
            TOTAL_WAYS_ON_WALL = II_PATTERN_WAYS + IJ_PATTERN_WAYS;
        }
        LogUtil.logIt("Total Number of ways when N = " + walls + " K = " + colors + " is " + IJ_PATTERN_WAYS);
        return TOTAL_WAYS_ON_WALL;
    }

    public static int getNumberOfWaysToPaintFence(int noOfPosts, int differentColors) {
        if (noOfPosts == 0) return 0; // We can't paint if there is no post.
        if (noOfPosts == 1)
            return differentColors; // you can take any of the colors, so we have k differentColors choices.

        /**
         * // Now when we have 2 posts, we can either paint the 2nd POST as same as the previous ones
         *         // or to make it different.
         *
         * A    B   and colors(Red, Blue, Green and Yellow)
         *
         * if we take the same color on Bth POST we have k choices. (since 2 adjacent can be of same colors.)
         *
         * Same = differentColors;
         *
         * and if we take a different color on POST B than  Ath. we have 1 less color to paint on B
         *
         * Different = differentColors * (differentColors-1) choices
         */

        int SAME = differentColors;
        int DIFFERENT = differentColors * (differentColors - 1);

        for (int i = 3; i <= noOfPosts; i++) {

            /**
             * Now also we have 2 choices to make either make the color same as the i-1th POST
             * or to make it different.
             */
            // When making different color
            /**
             * we have 2 more thing to consider here, when previous 2 were same or different.
             */
            int previous_diff = DIFFERENT;
            int previous_same = SAME;

            // when previous 2 were same to current different we can only choose from k-1.
            // or when previous 2 were different then we have all k different color choices.
            DIFFERENT = (previous_same * (differentColors - 1)) + (previous_diff * (differentColors - 1));

            // Now when we decide that we will do the current color as same.
            // it is only possible when the previous 2 colors were different else, if previous 2 colors were
            // same we can't make this one same since it will break the constraint.
            SAME = (previous_diff) * 1; // Only 1 choice which is whatever i-1th post choose.
        }
        LogUtil.logIt("Total Number of ways when N = " + noOfPosts + " K = " + differentColors + " is " + (SAME + DIFFERENT));
        return SAME + DIFFERENT;
    }
}
