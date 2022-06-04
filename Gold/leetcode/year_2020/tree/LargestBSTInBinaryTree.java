package com.leetcode.year_2020.tree;

import com.leetcode.year_2020.TreeNode;

/**
 * https://www.geeksforgeeks.org/largest-bst-binary-tree-set-2/
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/LargestBSTInBinaryTree.java
 *
 * @author neeraj on 24/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LargestBSTInBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(50);
        root.left = new TreeNode(30);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(20);

        root.right = new TreeNode(60);
        root.right.left = new TreeNode(45);
        root.right.right = new TreeNode(70);
        root.right.right.left = new TreeNode(65);
        root.right.right.right = new TreeNode(80);

        System.out.println(findMaxBSTInBinaryTree(root));
    }

    public static int findMaxBSTInBinaryTree(TreeNode root) {
        return findMaxBST(root).size;
    }

    static class MinMaxPair {
        int max;
        int min;
        int size;
        boolean isBST;

        public MinMaxPair() {
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            size = 0;
            isBST = true;
        }
    }

    private static MinMaxPair findMaxBST(TreeNode root) {
        if (root == null) {
            return new MinMaxPair(); // Empty tree is a BST with 0 size.
        }

        // Doing a POST Order Traversal
        MinMaxPair leftMinMax = findMaxBST(root.left);
        MinMaxPair rightMinMax = findMaxBST(root.right);

        // Now MinMaxPair for Root
        MinMaxPair minMaxPair = new MinMaxPair();

        // First checking for invalid case in both leftMinMax or rightMinMax side
        if (!leftMinMax.isBST || !rightMinMax.isBST || leftMinMax.max > root.val || rightMinMax.min < root.val) {
            minMaxPair.size = Math.max(rightMinMax.size, leftMinMax.size);
            minMaxPair.isBST = false;
            return minMaxPair;
        }

        // If we reach here means both our leftMinMax and rightMinMax is a valid BST including this current root
        minMaxPair.size = rightMinMax.size + leftMinMax.size + 1; // +1 for including the root.
        minMaxPair.isBST = true;
        minMaxPair.min = root.left != null ? leftMinMax.min : root.val;
        minMaxPair.max = root.right != null ? rightMinMax.max : root.val;
        return minMaxPair;
    }
}
