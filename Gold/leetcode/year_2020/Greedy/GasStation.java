package com.leetcode.year_2020.Greedy;

/**
 * https://leetcode.com/problems/gas-station/
 *
 * @author neeraj on 05/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class GasStation {

    public static void main(String[] args) {
        System.out.println(canCompleteCircuit(
                new int[]{1, 2, 3, 4, 5},
                new int[]{3, 4, 5, 1, 2}));

        System.out.println(canCompleteCircuit(
                new int[]{2, 3, 4},
                new int[]{3, 4, 3}));
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        /**
         * Intuition :
         * Two Pass
         * 1) if the total gas >= total cost, then we can definitely reach all Cities
         * 2) Now if we can reach to any petrol pump. we will start from 0th petrol city,
         * and keep our tankStatus = tank + (cost[i] - gas[i]).
         * Whenever our tank reaches negative state means, we started from a wrong city
         * and we shouldn't start from the city before the current city. So we will start from currentCity + 1;
         */

        // First Pass
        int fuelTank = 0;
        for (int i = 0; i < gas.length; i++) {
            fuelTank += gas[i] - cost[i];
        }

        if (fuelTank < 0) { // We can't reach to all cities and make circular tour.
            return -1;
        }

        // Second Pass
        fuelTank = 0;
        int startStation = 0;
        for (int i = 0; i < gas.length; i++) {
            fuelTank += gas[i] - cost[i];
            if (fuelTank < 0) {
                fuelTank = 0;
                startStation = i + 1;
            }
        }
        return startStation;
    }
}
