package com.leetcode.year_2020;

import java.util.Arrays;

/**
 * @author neeraj on 28/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CountPrimes {

    public static void main(String[] args) {
        System.out.println(countPrimes(10));
        System.out.println(sieveOfEratosthenes(10));
    }

    public static int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    public static int sieveOfEratosthenes(int n) {
        boolean[] allPrimes = new boolean[n];
        int count = 0;
        for(int i=2;i<n;i++) {
            if(!allPrimes[i]) {
                count++;
                for(int j=2;i*j<allPrimes.length;j++) {
                    allPrimes[i*j] = true;
                }
            }
        }
        return count;
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
