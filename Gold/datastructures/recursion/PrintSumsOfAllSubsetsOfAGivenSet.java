package com.datastructures.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author neeraj on 11/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PrintSumsOfAllSubsetsOfAGivenSet {

    public static void main(String[] args) {
        sumOfAllSubset(new int[]{2, 3});
        sumOfAllSubset(new int[]{2, 4, 5});
    }

    static int invoked = 1;

    public static void sumOfAllSubset(int[] arr) {
        invoked = 1;
        List<Integer> allSum = new ArrayList<>();
        sumOfAllSubset(arr, allSum, 0);
        System.out.println("Invoked....." + invoked);
        System.out.println(allSum);
    }

    private static void sumOfAllSubset(int[] arr, List<Integer> sums, int current) {
        if (arr.length == 0) {
            sums.add(current);
            return;
        }
        invoked++;

        // Choosing it and not choosing it.
        sumOfAllSubset(Arrays.copyOfRange(arr, 1, arr.length), sums, current); // Not Choosing it
        sumOfAllSubset(Arrays.copyOfRange(arr, 1, arr.length), sums, current + arr[0]); // Choosing it.
    }
}
