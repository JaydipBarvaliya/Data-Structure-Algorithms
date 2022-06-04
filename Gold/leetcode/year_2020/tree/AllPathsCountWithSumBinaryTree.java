package com.leetcode.year_2020.tree;


import com.leetcode.year_2020.TreeNode;

/**
 * You are given a binary tree in which each node contains an integer value.
 * <p>
 * Find the number of paths that sum to a given value.
 * <p>
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 * <p>
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 * https://leetcode.com/problems/path-sum-iii/
 *
 * @author neeraj on 23/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class AllPathsCountWithSumBinaryTree {

    public static void main(String[] args) {
        /**
         *       10
         *      /  \
         *     5   -3
         *    / \    \
         *   3   2   11
         *  / \   \
         * 3  -2   1
         */
        TreeNode root = new TreeNode(10);
        // Whole Left Side
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(3);

        // Right Side of left subtree
        root.left.left.right = new TreeNode(-2);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(1);

        // Right SubTree from Node
        root.right = new TreeNode(-3);
        root.right.right = new TreeNode(11);

        System.out.println(pathSum(root, 8));

    }

    public static int pathSum(TreeNode root, int sum) {
        if (root == null) return 0; // If Tree is not present we can't create any sum

        return getTotalPathSumFromThisNode(root, sum)
                + pathSum(root.left, sum) // Check the totalPathSum which tree#left can contribute.
                + pathSum(root.right, sum); // Check the totalPathSum which tree#right can contribute.
    }

    public static int getTotalPathSumFromThisNode(TreeNode root, int sum) {
        if (root == null) return 0;

        // We are subtracting at this node because assume we have just 1 node in the tree
        // Now how do i know without going to left and right of tree
        // (which is anyways not possible since only 1 node in the tree)
        // I can subtract the currentNode(singleNode) value from the totalSum to verify.
        int totalPathSumFromThisNode = (sum - root.val) == 0 ? 1 : 0;


        return totalPathSumFromThisNode
                // We are subtracting the currentNode value since we have traversed it.
                + getTotalPathSumFromThisNode(root.left, sum - root.val)
                // We are subtracting the currentNode value since we have traversed it.
                + getTotalPathSumFromThisNode(root.right, sum - root.val);
    }
}
