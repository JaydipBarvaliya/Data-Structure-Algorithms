package com.leetcode.problems.medium;

/**
 * @author neeraj on 05/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class BinarySearchTreeToGreaterSumTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(3);

        root.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(8);

        root = bstToGst(root);

        System.out.println(root);
    }

    static int maxVal = 0;

    public static TreeNode bstToGst(TreeNode root) {
        maxVal = 0;
        bstToGstUtil(root);
        return root;
    }

    public static void bstToGstUtil(TreeNode root) {
        if (root == null)
            return;
        bstToGstUtil(root.right);
        root.val += maxVal;
        maxVal = root.val;
        bstToGstUtil(root.left);
    }
}
