package com.geeksforgeeks.dynamicProgramming;

import com.geeksforgeeks.array.Rotate2DMatrix;
import com.util.LogUtil;

/**
 * https://www.youtube.com/watch?v=ZaVM057DuzE
 * <p>
 * Algorithm
 * <p>
 * No of ways coin can be formed
 * <p>
 * 1) (Excluding the new coin)
 * 2) (Including the new coin)
 * 3) ( Sum step (1) + (2)
 * <p>
 * Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * <p>
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 */
public class CoinChangingNoOfWays {

    public static void main(String[] args) {
//        System.out.println(getNoOfWaysSumCanBeAchieved(new int[]{1, 2, 3}, 6));
//        System.out.println(getNoOfWaysSumCanBeAchieved(new int[]{1, 2, 3}, 5));
//        System.out.println(getNoOfWaysSumCanBeAchieved(new int[]{2, 3, 5, 6}, 10));
//        System.out.println(getNoOfWaysSumCanBeAchieved(new int[]{3, 5}, 17));

//        System.out.println(getNoOfWays(new int[]{1, 2, 5}, 5));

        /**
         *
         * NOTE this is the correct approach which gives correct answer.
         *
         */
        // This is the correct way.
        System.out.println(change(5, new int[]{1, 2, 5}));
    }

    public static int getNoOfWaysSumCanBeAchieved(int[] coins, int sum) {
        int[][] ways = new int[coins.length][sum + 1];
        //Rotate2DMatrix.print2DArray(ways);
        for (int i = 0; i <= sum; i++)
            ways[0][i] = 1;
        for (int i = 0; i < coins.length; i++) // For Sum = 0, there is only one way to arrange coins(i.e. not include any coin at all)
            ways[i][0] = 1;

        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j < sum + 1; j++) {
                if (coins[i] > j) {
                    ways[i][j] = ways[i - 1][j];
                } else {
                    ways[i][j] = ways[i][j - coins[i]] + ways[i - 1][j]; // Here ways[i-1][j] is the minimum no of ways with excluding the new coin and ways[i][j - coins[i]] is no of ways including the new coin
                }
                // Rotate2DMatrix.print2DArray(ways);
            }
        }
        Rotate2DMatrix.print2DArray(ways);
        return ways[ways.length - 1][sum];
    }

    public static int getNoOfWays(int[] coins, int sum) {
        int[][] ways = new int[coins.length + 1][sum + 1];

        for (int i = 0; i <= coins.length; i++) {
            for (int j = 0; j <= sum; j++) {
                if (i == 0 && j == 0) {
                    ways[i][j] = 1;
                } else if (i == 0) {
                    ways[i][j] = 0;
                } else {
                    if (i > j) {
                        ways[i][j] = ways[i - 1][j];
                    } else {
                        ways[i][j] = ways[i][j - i] + ways[i - 1][j]; // Exclusion + Inclusion
                    }
                }
            }
        }
        Rotate2DMatrix.print2DArray(ways);
        return ways[coins.length][sum];
    }

    /**
     * @authored on 09 May 2019
     * <p>
     * Solve by if I include the coin and if i exclude the coin
     */
    public static int change(int amount, int[] coins) {

        int[][] numberOfWays = new int[coins.length + 1][amount + 1];

        // Base Conditions

        // 1)  when Amount to be achieved is 0
        // So in that case there is only 1 way to achieve the sum by not including the coin.


        // 2) When we don't have any coins, then in that case, we cannot make any sum, hence 0 ways

        for (int i = 0; i < numberOfWays.length; i++) {
            for (int j = 0; j <= amount; j++) {

                // Base Condition 1
                if (i == 0 && j != 0) {  // When Sum is 0, since at 0th index, sum is 0
                    numberOfWays[i][j] = 0;
                    continue;
                }
                // Base Condition 2
                if (j == 0) { // When Coins is 0, since in 2D matrix we assumed we have 0 coins at 1st Row
                    numberOfWays[i][j] = 1;
                    continue;
                }

                // If new Coin face value is greater than the sum, so no point including the new value, let's just use previous value (i.e. without including this coin).
                if (coins[i - 1] > j) {
                    numberOfWays[i][j] = numberOfWays[i - 1][j];
                } else {


                    // Now let's calculate the number of ways by including the new coin and excluding it.
                    // numberOfWays[row][col] = By Excluding the coin (numberOfWays[row-1][col] (Since new coin is excluded so amount didn't decrease)
                    //                         + By Including the coin (numberOfWays[row][col - coin[i-1]]) (Since new coin is included, so the amount left is minus the coin face value
                    numberOfWays[i][j] = numberOfWays[i - 1][j] + numberOfWays[i][j - coins[i - 1]];
                }
            }
        }
        LogUtil.logIt("Number of ways for amount " + amount + " using coin ");
        LogUtil.printArray(coins);
        return numberOfWays[numberOfWays.length - 1][amount];
    }


}
