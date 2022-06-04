package com.leetcode.year_2020;

import static com.leetcode.year_2020.tree.ConstructBinaryTreeUsingPreOrderAndInorderTraversal.buildTreeFromPreOrderAndInorder;

/**
 * @author neeraj on 25/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SecondMinimumNodeInBinaryTree {

    public static void main(String[] args) {
        TreeNode root = buildTreeFromPreOrderAndInorder(new int[]{5, 8, 5}, new int[]{8, 5, 5});
        System.out.println(findSecondMinimumValue(root));

        root = buildTreeFromPreOrderAndInorder(new int[]{5, 4, 11, 7, 2, 8, 13, 4, 1},
                new int[]{7, 11, 2, 4, 5, 13, 8, 4, 1});
        System.out.println(hasPathSum(root, 22));
    }

    static int min;
    static int second_min;
    static boolean second_min_set = false;
    static boolean minSet = false;

    public static int findSecondMinimumValue(TreeNode root) {
        min = Integer.MAX_VALUE;
        second_min = Integer.MAX_VALUE;
        second_min_set = false;
        minSet = false;
        inorder(root);
        return (second_min_set) ? second_min : -1;
    }

    public static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (root.val < min) {
            if (minSet) {
                second_min_set = true;
            }
            second_min = min;
            minSet = true;
            min = root.val;
        } else if (root.val < second_min && root.val != min) {
            second_min_set = true;
            second_min = root.val;
        }
        inorder(root.right);
    }

    public static boolean hasPathSum(TreeNode node, int targetSum) {
        if (node == null) return false;
        if (node.left == null && node.right == null && targetSum - node.val == 0) return true;

        return hasPathSum(node.left, targetSum - node.val)
                || hasPathSum(node.right, targetSum - node.val);
    }
}
