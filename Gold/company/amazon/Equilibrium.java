package com.company.amazon;

import java.util.Collections;
import java.util.stream.IntStream;

public class Equilibrium {

    public static void main(String[] args) {
        System.out.println(getEquilibriumIndexPart2(new int[]{-7, 1, 5, 2, -4, 3, 0}));
        System.out.println(getEquilibriumIndexPart2(new int[]{5, 2, 5}));
    }

    public static int getEquilibriumIndexPart2(int[] arr) {
        int totalSum = IntStream.of(arr).sum();
        int leftSum = 0;

        for (int i = 0; i < arr.length; i++) {
            totalSum -= arr[i]; // Removing current item from the total sum to get the right sum
            if (leftSum == totalSum) {
                return i;
            }
            leftSum += arr[i];
        }
        return -1;
    }
}
