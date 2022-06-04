package com.leetcode.year_2020.tree;

import com.leetcode.year_2020.ValidateBinarySearchTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author neeraj on 21/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CheckForIdenticalBSTWithoutBuildingTheTree {

    public static void main(String[] args) {
        System.out.println(sameBSTUsingO_N_Space(
                new int[]{3, 5, 4, 6, 1, 0, 2},
                new int[]{3, 1, 5, 2, 4, 6, 0}
        ));

        System.out.println(sameBSTUsingO_N_Space(
                new int[]{6, 8, 9},
                new int[]{6, 9, 8}
        ));



    }

    public static boolean sameBSTUsingO_N_Space(int[] arr1, int[] arr2) {
//        List<Integer> tree1 = Arrays.asList(arr1);
//        List<Integer> tree2 = Arrays.asList(arr2);
//
//        return sameBSTUsingO_N_Space(tree1, tree2);
        return sameBSTUsingO_1_Space(arr1, arr2, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean sameBSTUsingO_N_Space(List<Integer> tree1, List<Integer> tree2) {
        // If length not same you can't be a same representation of BST
        if (tree1.size() != tree2.size()) return false;

        // If both are empty then definitely same.
        if (tree1.size() == 0 && tree2.size() == 0) return true;

        if (tree1.get(0) == tree2.get(0)) {

            // If Both first Root node is same then let's check for
            // left tree and right tree simultaneously in both tree
            List<Integer> leftSideOfRootTree1 = new ArrayList<>();
            List<Integer> rightSideOfRootTree1 = new ArrayList<>();
            List<Integer> leftSideOfRootTree2 = new ArrayList<>();
            List<Integer> rightSideOfRootTree2 = new ArrayList<>();

            for (int i = 1; i < tree1.size(); i++) {
                if (tree1.get(i) < tree1.get(0)) {
                    leftSideOfRootTree1.add(tree1.get(i));
                } else {
                    rightSideOfRootTree1.add(tree1.get(i));
                }
            }

            for (int i = 1; i < tree2.size(); i++) {
                if (tree2.get(i) < tree2.get(0)) {
                    leftSideOfRootTree2.add(tree2.get(i));
                } else {
                    rightSideOfRootTree2.add(tree2.get(i));
                }
            }
            return sameBSTUsingO_N_Space(leftSideOfRootTree1, leftSideOfRootTree2)
                    && sameBSTUsingO_N_Space(rightSideOfRootTree1, rightSideOfRootTree2);
        } else {
            return false;
        }
    }

    private static boolean sameBSTUsingO_1_Space(int[] tree1, int[] tree2, int index1, int index2, int min, int max) {
        /**
         * This is somewhat based on concept of {@link ValidateBinarySearchTree}
         * where we have a concept of min and max value on left and right subtree
         */
        int i,j;

        // Find first value satisfying min < "thatValue" < max; in tree1
        for (i = index1; i < tree1.length; i++) {
            if (tree1[i] > min && tree1[i] < max) {
                break;
            }
        }

        // Find first value satisfying min < "thatValue" < max in tree2
        for (j = index2; j < tree2.length; j++) {
            if (tree2[j] > min && tree2[j] < max) {
                break;
            }
        }

        if (i == tree1.length && j == tree2.length) {
            return true; // no node found for both trees.
        }

        if (i == tree1.length || j == tree2.length) {
            return false; // Not a similar BST
        }

        if (tree1[i] == tree2[j]) {
            return sameBSTUsingO_1_Space(tree1, tree2, i + 1, j + 1, min, tree1[i]) // Check for Left Subtree
                    && sameBSTUsingO_1_Space(tree1, tree2, i + 1, j + 1, tree1[i], max); // Check for Right subtree
        }
        return false;
    }
}
