package com.leetcode.year_2020;

/**
 * @author neeraj on 30/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CheckIfaStringIsaValidSequencefromRoottoLeavesPathinaBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);

        root.left.left = new TreeNode(0);
        root.left.left.right = new TreeNode(1);

        root.left.right = new TreeNode(1);
        root.left.right.left = new TreeNode(0);
        root.left.right.right = new TreeNode(0);

        root.right = new TreeNode(0);
        root.right.left = new TreeNode(0);

        System.out.println(isValidSequence(root, new int[]{0, 1, 1, 0}));
    }

    public static boolean isValidSequence(TreeNode root, int[] arr) {
        return isValidSequence(root, arr, 0);
    }

    public static boolean isValidSequence(TreeNode root, int[] arr, int index) {
        // Invalid Case
        if (root == null) return false;

        // If at any point during iteration we feel we are not on right path
        // let's just return from that path instead of iterating complete.
        if (root.val != arr[index]) return false;

        if (index == arr.length - 1) return root.left == null && root.right == null;

        return isValidSequence(root.left, arr, index + 1) ||
                isValidSequence(root.right, arr, index + 1);

    }
}
