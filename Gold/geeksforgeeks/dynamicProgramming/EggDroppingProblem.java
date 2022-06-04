package com.geeksforgeeks.dynamicProgramming;

import com.geeksforgeeks.array.Rotate2DMatrix;
import com.util.LogUtil;

/**
 * https://www.youtube.com/watch?v=iOaRjDT0vjc&list=PLiQ766zSC5jM2OKVr8sooOuGgZkvnOCTI&index=2
 *
 * https://leetcode.com/problems/super-egg-drop/
 *
 *
 */
public class EggDroppingProblem {

    public static void main(String[] args) {
        System.out.println("Minimum No of Tries is " + getMinimumNoOfTries(2, 100));
        System.out.println("Minimum No of Tries is " + getMinimumNoOfTries(2, 36));
        System.out.println("Minimum No of Tries is " + getMinimumNoOfTries(2, 50));

        // AmazonPrimeOrdersSorting using second approach
        LogUtil.logIt("Testing Egg Dropping using second approach, recurse the way", true);
        dropTheEgg(3, 6);
        dropTheEgg(2, 100);
        dropTheEgg(2, 50);
        dropTheEgg(3, 14);
    }

    public static int getMinimumNoOfTries(int eggs, int floors) {

        int[][] eggFloor = new int[eggs][floors + 1];

        // With 1 egg it will take N tries to find out the thresh-hold floor
        for (int i = 1; i <= floors; i++) {
            eggFloor[0][i] = i;
        }
        //Rotate2DMatrix.print2DArray(eggFloor);
        for (int i = 1; i < eggs; i++) { // Eggs starting from 2 as for 1 egg we have already calculated

            for (int j = 1; j <= floors; j++) { // Start of the section

                eggFloor[i][j] = Integer.MAX_VALUE - 100;
                // Now we have to test all floors up to  jth floor
                for (int x = 1; x <= j; x++) {

                    int minTries = 1 + Math.max(eggFloor[i - 1][x - 1], eggFloor[i][j - x]); // 1 + Max( if egg breaks , if egg doesn't break)

                    if (minTries < eggFloor[i][j]) {
                        eggFloor[i][j] = minTries;
                    }
                }
            }
        }

        Rotate2DMatrix.print2DArray(eggFloor);
        return eggFloor[eggs - 1][floors];
    }

    public static void dropTheEgg(int totalEggs, int totalFloors) {
        int[][] cache = new int[totalEggs + 1][totalFloors + 1];
        initializeCache(cache);
        LogUtil.logIt("Minimum number of tries for [" + totalEggs + "," + totalFloors + "] is " +
                dropTheEgg(totalEggs, totalFloors, cache), true);
    }

    // This is another approach, using Dynamic Programming with
    // Break the egg and not breaking the egg  way.
    private static int dropTheEgg(int totalEggs, int totalFloors, int[][] cache) {

//        LogUtil.printMultiDimensionArray(cache);

        // First thing first handle the base cases

        // Base case 1
        // Simple if you have 1 or less floors, you can just do a least of the amount of floors available
        if (totalFloors == 0 || totalFloors == 1) {
            return totalFloors;
        }

        // Base case 2
        // When you have 0 eggs, then you cannot do any drop test
        if (totalEggs == 0) {
            return 0;
        }

        // Base case 3
        // When you have 1 eggs, so the least amount of drop test you can do in the worse case is the number of floors available.
        if (totalEggs == 1) {
            return totalFloors;
        }

        // Check if we have the solution
        if (cache[totalEggs][totalFloors] != Integer.MAX_VALUE) {
            return cache[totalEggs][totalFloors];
        }

        // Now we will traverse all floors starting from bottom
        for (int floor = 1; floor <= totalFloors; floor++) {

            // Worst Outcome cost will be Max(Egg Broke, Egg Did Not Broke)
            int costOfWorstOutcome = Math.max(dropTheEgg(totalEggs - 1, floor - 1, cache),
                    dropTheEgg(totalEggs, totalFloors - floor, cache));

            int accountingForDropTestAtThisSubproblem = 1 + costOfWorstOutcome;

            cache[totalEggs][totalFloors] = Math.min(cache[totalEggs][totalFloors], accountingForDropTestAtThisSubproblem);
        }
        return cache[totalEggs][totalFloors];
    }

    public static void initializeCache(int[][] cache) {
        for (int i = 0; i < cache.length; i++)
            for (int j = 0; j < cache[i].length; j++)
                cache[i][j] = Integer.MAX_VALUE;
    }

}
