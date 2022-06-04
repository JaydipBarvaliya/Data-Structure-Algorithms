package com.leetcode.year_2020;

import java.util.Stack;

/**
 * @author neeraj on 02/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ValidateBinarySearchTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(20);

//        iterativeInOrderTraversal(root);
        System.out.println(isValidBSTOptimal(root));
    }

    public static boolean isValidBSTOptimal(TreeNode treeNode) {
        return validate(treeNode, null, null);
    }

    /**
     * https://www.youtube.com/watch?v=Z_-h_mpDmeg
     * @param root Root of the Tree also the node which we are currently on.
     * @param max  Maximum allowed value in the left side of tree for the current root, if it surpasses that it's not a valid BST
     * @param min  Minimum allowed value in the right side of tree for the current root, if it surpasses that it's not a valid BST
     * @return
     */
    public static boolean validate(TreeNode root, TreeNode max, TreeNode min) {
        if (root == null) return true;
        else if ((max != null && root.val >= max.val) || (min != null && root.val <= min.val)) return false;
        else return validate(root.left, root, min) && validate(root.right, max, root);
    }

    public static boolean isValidBST(TreeNode treeNode) {
        if (treeNode == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode lastTraversedRecord = null;

        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            TreeNode popped = stack.pop();
            if (lastTraversedRecord != null && lastTraversedRecord.val >= popped.val) return false;
            lastTraversedRecord = popped;
            treeNode = popped.right;
        }
        return true;
    }
}
