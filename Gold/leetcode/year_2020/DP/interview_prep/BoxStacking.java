package com.leetcode.year_2020.DP.interview_prep;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://www.geeksforgeeks.org/box-stacking-problem-dp-22/
 * https://www.youtube.com/watch?v=9mod_xRB-O0
 *
 * @author neeraj on 08/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class BoxStacking {

    public static void main(String[] args) {
        Box A = new Box(2, 1, 4);
        Box B = new Box(3, 2, 5);
        findMaxHeightWhichCanBeAchieved(Arrays.asList(A, B));


        findMaxHeightWhichCanBeAchieved(Arrays.asList(new Box(4, 6, 7),
                new Box(1, 2, 3),
                new Box(4, 5, 6),
                new Box(10, 12, 32)));
    }

    static class Box {
        int length;
        int width;
        int height;

        public Box(int length, int width, int height) {
            this.length = length;
            this.width = width;
            this.height = height;
        }
    }

    public static int findMaxHeightWhichCanBeAchieved(List<Box> boxDimensions) {
        /**
         * We first need to create all different permutation of box dimension,
         * why since, we can rotate the box in any direction to put them on top
         * of each other but as a general rule we will try not to make length smaller than the width of box.
         */
        List<List<Integer>> combinedPermutations = new ArrayList<>();
        for (Box box : boxDimensions) {
            combinedPermutations.addAll(createAllPermutations(new int[]{box.length, box.width, box.height}));
        }

        /**
         * Now lets sort them based on their base-Area (length*width) in descending order
         */
        Collections.sort(combinedPermutations, (a, b) ->
                (b.get(0) * b.get(1)) - (a.get(0) * a.get(1))
        );

        /**
         * Now all is left is just finding max height using Longest Increasing Subsequence approach.
         */
        int[] LIH = new int[combinedPermutations.size()]; // Longest Increasing height
        int[] source = new int[LIH.length]; // This will tell which box was sitting on top of which box.

        // Initially all boxes can have their own individual height as the max height
        for (int i = 0; i < LIH.length; i++) {
            LIH[i] = combinedPermutations.get(i).get(2);
            source[i] = i;
        }

        /**
         * Now we will try that can we make ith Box sit on top of jth box
         */
        for (int i = 1; i < LIH.length; i++) {
            List<Integer> currentDimensionsOf_i = combinedPermutations.get(i);
            Box Box_i = new Box(currentDimensionsOf_i.get(0), currentDimensionsOf_i.get(1), currentDimensionsOf_i.get(2));
            for (int j = 0; j < i; j++) {
                List<Integer> currentDimensionsOf_j = combinedPermutations.get(j);
                Box Box_j = new Box(currentDimensionsOf_j.get(0), currentDimensionsOf_j.get(1), currentDimensionsOf_j.get(2));

                // Check if we can place Box_i on top of Box_j
                if (Box_i.length < Box_j.length && Box_i.width < Box_j.width // Checking if we can stack on top
                        && LIH[i] < LIH[j] + Box_i.height) { // Also checking if this is actually increasing the height.
                    LIH[i] = LIH[j] + Box_i.height;
                }
            }
        }
        LogUtil.logIt("Maximum height which can be achieved is " + LIH[LIH.length - 1]);
        return LIH[LIH.length - 1];
    }

    private static List<List<Integer>> createAllPermutations(int[] dimensions) {
        List<List<Integer>> allDimensions = new ArrayList<>();
        permute(dimensions, new ArrayList<>(), allDimensions);
        return allDimensions;
    }

    private static void permute(int[] dimensions, ArrayList<Integer> currentPermutation, List<List<Integer>> allDimensions) {
        if (currentPermutation.size() == dimensions.length) {
            allDimensions.add(new ArrayList<>(currentPermutation));
            return;
        }
        for (int i = 0; i < dimensions.length; i++) {
            if (currentPermutation.contains(dimensions[i]) // To Avoid duplicate
                    || (currentPermutation.size() == 1 && currentPermutation.get(0) < dimensions[i])) { // To Avoid having length < width
                continue;
            }
            currentPermutation.add(dimensions[i]); // Choose
            permute(dimensions, currentPermutation, allDimensions); // Explore
            currentPermutation.remove(currentPermutation.size() - 1); // UnChoose
        }
    }
}
