package com.leetcode.problems.medium;

/**
 * @author neeraj on 15/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CoinChange_322 {

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(coinChange(new int[]{2}, 3));
    }

    public static int coinChange(int[] coins, int amount) {
        int[] minimumCoins = new int[amount + 1];

        // For Amount as 0, we don't need any coin
        minimumCoins[0] = 0;

        for (int i = 1; i <= amount; i++) {
            minimumCoins[i] = amount + 1;
        }

        for (int coin = 0; coin < coins.length; coin++) {
            for (int currentAmount = 0; currentAmount <= amount; currentAmount++) {
                if (coins[coin] <= currentAmount) {
                    minimumCoins[currentAmount] = Math.min(1 + minimumCoins[currentAmount - coins[coin]], minimumCoins[currentAmount]);
                }
            }
        }
        return minimumCoins[minimumCoins.length - 1] == (amount + 1) ? -1 : minimumCoins[minimumCoins.length - 1];
    }
}
