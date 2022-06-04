package com.leetcode.year_2020.DP.minimum_maximum_path_to_reach_to_target;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-cost-for-tickets/
 * @author neeraj on 05/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumCostForTickets {

    public static void main(String[] args) {
        System.out.println(mincostTickets(
                new int[]{1, 4, 6, 7, 8, 20},
                new int[]{2, 7, 15}));

    }

    static int[] T;

    public static int mincostTickets(int[] days, int[] costs) {
        /**
         * So we can first do a DFS and try purchasing exhaustive combination
         * of passes
         */
        // Initially since i have not purchased any ticket hence there is no valid day in which i can travel without pass.
        T = new int[days[days.length - 1]];
        Arrays.fill(T, -1);
        return doDFS(days, costs, 0, 0);
    }

    private static int doDFS(int[] days, int[] costs, int ithDay, int travelCoveredUntil) {
        /**
         * Ok, at the days[i], how many possibilities that we can do?
         *
         * pay nothing. We have bought a week-pass, or a month-pass in the past, and up-to-now, the pass is still valid.
         * buy a day-pass to cover only this day
         * buy a week-pass to cover next 6 consecutive days.
         * buy a month-pass to cover next 29 consecutive days.
         */


        if (ithDay == days.length) return 0; // We don't have any days to travel.

        // Here pass is still valid.
        if (days[ithDay] <= travelCoveredUntil) {
            return doDFS(days, costs, ithDay + 1, travelCoveredUntil);
        }

        // If we have the data in cache.
        if (T[ithDay] != -1 && days[ithDay] > travelCoveredUntil) return T[ithDay];


        // Since we are not covered with any previous day.
        // so we can take any of these 3 passes.
        int costOf1DayPass = costs[0] + doDFS(days, costs, ithDay + 1, days[ithDay]);
        int costOf7DayPass = costs[1] + doDFS(days, costs, ithDay + 1, days[ithDay] + 6);
        int costOfMonthPass = costs[2] + doDFS(days, costs, ithDay + 1, days[ithDay] + 29);

        return T[ithDay] = Math.min(costOf1DayPass, Math.min(costOf7DayPass, costOfMonthPass));
    }
}
