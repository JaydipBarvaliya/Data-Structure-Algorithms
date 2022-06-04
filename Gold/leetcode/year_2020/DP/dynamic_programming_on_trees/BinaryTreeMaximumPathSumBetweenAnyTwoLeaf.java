package com.leetcode.year_2020.DP.dynamic_programming_on_trees;

import com.leetcode.year_2020.TreeNode;
import com.util.LogUtil;

/**
 * @author neeraj on 13/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reSubarray Sum Equals Kserved.
 */
public class BinaryTreeMaximumPathSumBetweenAnyTwoLeaf {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1000);
        root.left = new TreeNode(10);
        root.left.left = new TreeNode(20);
        root.left.right = new TreeNode(30);

        root.right = new TreeNode(500);
        root.right.left = new TreeNode(2000);
        root.right.right = new TreeNode(500);
        root.right.right.left = new TreeNode(2500);


        LogUtil.logIt("Maximum Path Sum between 2 leafs " + maxPathSum(root));
    }

    static int MAX_SUM = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        MAX_SUM = Integer.MIN_VALUE;
        fetchMaxPathSumBetweenLeaf(root);
        return MAX_SUM == Integer.MIN_VALUE ? 0 : MAX_SUM;
    }

    public static int fetchMaxPathSumBetweenLeaf(TreeNode root) {
        if (root == null) return 0;

        if (root.left == null && root.right == null) {
            return root.val;
        }

        // Hypothesis
        // We have to check maxPathSum at each node
        int maxSumInLeft = fetchMaxPathSumBetweenLeaf(root.left);
        int maxSumInRight = fetchMaxPathSumBetweenLeaf(root.right);

        int temp = root.val + Math.max(maxSumInLeft, maxSumInRight);

        // You can take the answer only when you have both left and right child
        if (maxSumInLeft == 0 && maxSumInRight == 0) {
            MAX_SUM = Math.max(MAX_SUM, root.val + maxSumInLeft + maxSumInRight);
        }
        return temp;
    }
}
