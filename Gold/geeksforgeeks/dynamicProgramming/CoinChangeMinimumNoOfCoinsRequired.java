package com.geeksforgeeks.dynamicProgramming;

import com.geeksforgeeks.array.ArrayRotation;
import com.geeksforgeeks.array.Rotate2DMatrix;
import com.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1,2,5  = 6 ( minimum coins required to give you a sum)
 * <p>
 * <p>
 * 1,2,5,10 = 12345
 */
public class CoinChangeMinimumNoOfCoinsRequired {
    public static void main(String[] args) {
//        System.out.println("Minimum Coins required to form a sum of 11 with {1,5,6,8} is " + getMinimumNoOfCoinChangeRequired(new int[]{1, 5, 6, 8}, 11));
//        System.out.println("Minimum Coins required to form a sum of 13 with {7,2,3,6} is " + getMinimumNoOfCoinChangeRequired(new int[]{7, 2, 3, 6}, 13));
//        System.out.println("Minimum Coins required to form a sum of 30 with {25, 10, 5} is " + getMinimumNoOfCoinChangeRequired(new int[]{25, 10, 5}, 30));
//        System.out.println("Minimum Coins required to form a sum of 11 with {9, 6, 5, 1} is " + getMinimumNoOfCoinChangeRequired(new int[]{9, 6, 5, 1}, 11));


//        getMinimumNoOfCoinsRequiredToAchieve(13,new int[]{7,2,3,6});
//        getMinimumNoOfCoinsRequiredToAchieve(30,new int[]{25,10,5});
//        getMinimumNoOfCoinsRequiredToAchieve(11, new int[]{1, 5, 6, 9});
//        getMinimumNoOfCoinsRequiredToAchieve(11, new int[]{1, 2, 5});

        coinChange(new int[]{1, 2, 5}, 11);
    }

    public static int getMinimumNoOfCoinChangeRequired(int[] coins, int total) {
        Arrays.sort(coins);
        int[][] minChange = new int[coins.length][total + 1];


        for (int i = 0; i <= total; i++) {
            if (i % coins[0] == 0) {
                minChange[0][i] = i / coins[0];
            } else {
                minChange[0][i] = Integer.MAX_VALUE;
            }
        }
        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j < minChange[i].length; j++) {
                if (coins[i] > j) {
                    minChange[i][j] = minChange[i - 1][j];
                } else {
                    minChange[i][j] = minChange[i - 1][j] < minChange[i][j - coins[i]] ? minChange[i - 1][j] : minChange[i][j - coins[i]] + 1;
                }
            }
        }
        Rotate2DMatrix.print2DArray(minChange);
        return minChange[coins.length - 1][total];
    }

    /**
     * This solution works for all denomination
     *
     * @param sum
     */
    public static void getMinimumNoOfCoinsRequiredToAchieve(int sum, int[] denominations) {
        int[] minCoins = new int[sum + 1];
        int[] actualCoinUsed = new int[sum + 1];

        // For a Sum of 0 you need 0 coins
        minCoins[0] = 0;
        actualCoinUsed[0] = 0;

        for (int i = 1; i < minCoins.length; i++) {
            minCoins[i] = Integer.MAX_VALUE - 100;
        }

        for (int i = 0; i < denominations.length; i++) {
            for (int j = 1; j < minCoins.length; j++) {

                if (denominations[i] <= j) {
                    int sumWithCoin = 1 + minCoins[Math.abs(j - denominations[i])];
                    if (sumWithCoin < minCoins[j]) {
                        minCoins[j] = sumWithCoin;
                        actualCoinUsed[j] = denominations[i];
                    }
                }
            }
//            ArrayRotation.printArray(minCoins);
        }
        ArrayRotation.printArray(minCoins);

        System.out.println("Minimum Coins used to make sum " + sum + " is " + minCoins[sum]);
        ArrayRotation.printArray(actualCoinUsed);

        System.out.println("Coins Used are");
        List<Integer> coinsUsed = new ArrayList<>();
        int sumCopy = sum;
        for (int i = sum; i > 0; ) {
            coinsUsed.add(actualCoinUsed[i]);
            sumCopy = sumCopy - actualCoinUsed[i];
            i = sumCopy;
        }
        System.out.println(coinsUsed);
    }

    /**
     * https://leetcode.com/problems/coin-change/
     * <p>
     * This is the Bottom up approach where we know the min coins needed for the totalAmount "0" is always 0,
     * Now we will make up the solution, based on this assumption and simply check, that by using which coins(denominations)
     * we can make up the totalAmount.
     * <p>
     * For Example :
     * <p>
     * Input: coins = [1, 2, 5], totalAmount = 11
     * Output: 3
     * Explanation: 11 = 5 + 5 + 1
     *
     * @param coins
     * @param totalAmount
     */
    public static void coinChange(int[] coinsArr, int totalAmount) {
        int[] minCoinsNeeded = new int[totalAmount + 1]; // Giving size totalAmount+1, since we want to consider the base case when totalAmount is 0
        int[] coinsIncluded = new int[totalAmount + 1];

        // #Base Case 1 (For totalAmount 0 we don't have to use any coins)
        minCoinsNeeded[0] = 0;
        coinsIncluded[0] = 0;

        // Pre-filling all the minCoins placement as greater than totalAmount
        // Since java initializes the array with 0
        for (int i = 1; i < minCoinsNeeded.length; i++) {
            minCoinsNeeded[i] = totalAmount + 1;
        }

        // Let's try all the coins, for all the sum to find out the min coins needed at totalAmount.
        for (int coin = 0; coin < coinsArr.length; coin++) {
            for (int currentAmount = 0; currentAmount <= totalAmount; currentAmount++) {

                // First Check whether this coin, will contribute to the amount (i.e. coin at this index, is less than the currentAmount
                if (coinsArr[coin] <= currentAmount) {

                    if ((1 + minCoinsNeeded[currentAmount - coinsArr[coin]]) < minCoinsNeeded[currentAmount]) {
                        coinsIncluded[currentAmount] = coinsArr[coin];
                    }
                    // We have to find minimum coins, how we can check that is to find the min of
                    // either (the currentMinCoins at currentAmount, 1(this 1 coin is from coinsArr[coin] + minCoinNeeded[after this coin is used what is the amount left]);
                    minCoinsNeeded[currentAmount] = Math.min(minCoinsNeeded[currentAmount], 1 + minCoinsNeeded[currentAmount - coinsArr[coin]]);
                }
            }
        }


//        LogUtil.printArray(minCoinsNeeded);

        LogUtil.logIt("Minimum Coins needed to make the amount " + totalAmount + " from coins of denomination", false);
        LogUtil.printArray(coinsArr);
        LogUtil.logIt(" is " + minCoinsNeeded[totalAmount]);
        LogUtil.logIt("and the coins included is ");


        // Let's find out the actual coin used
        int sumCopy = 0;
        List<Integer> actualCoinUsed = new ArrayList<>();
        for (int i = totalAmount; i > 0; ) {
            sumCopy = coinsIncluded[i];
            actualCoinUsed.add(sumCopy);
            i = i - sumCopy;
        }
        System.out.println(actualCoinUsed);
    }
}





