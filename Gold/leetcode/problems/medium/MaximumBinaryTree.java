package com.leetcode.problems.medium;

/**
 * @author neeraj on 05/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaximumBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5});
    }

    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode root = maxBinaryTreeUtil(nums, 0, nums.length - 1);
        System.out.println(root);
        return root;
    }

    private static TreeNode maxBinaryTreeUtil(int[] nums, int low, int high) {
        if (low > high) return null;
        TreeNode root;
        int maxValIndex = getMaxValIndex(nums, low, high);
        root = new TreeNode(nums[maxValIndex]);
        root.left = maxBinaryTreeUtil(nums, low, maxValIndex - 1);
        root.right = maxBinaryTreeUtil(nums, maxValIndex + 1, high);
        return root;
    }

    private static int getMaxValIndex(int[] nums, int low, int high) {
        int max = Integer.MIN_VALUE;
        int maxValIndex = 0;
        for (int i = low; i <= high; i++) {
            if (max < nums[i]) {
                max = nums[i];
                maxValIndex = i;
            }
        }
        return maxValIndex;
    }
}
