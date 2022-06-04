package com.leetcode.year_2020.tree;

import com.leetcode.year_2020.TreeNode;
import com.util.LogUtil;

/**
 * @author neeraj on 13/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FlattenBinaryTreeToLinkedList {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.inorder(root);
        flatten(root);
        LogUtil.logIt("\n After Flattening...");
        root.inorder(root);
        LogUtil.newLine();


        root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.inorder(root);
        flatten(root);
        LogUtil.logIt("\n After Flattening...");
        root.inorder(root);
    }

    public static void flatten(TreeNode root) {
        flattenTree(root);
    }

    private static TreeNode flattenTree(TreeNode root) {
        if (root == null ||
                (root.left == null) && (root.right) == null) {
            return root; // This is either the leaf node or null node
        }

        TreeNode rightPart = root.right;

        TreeNode headOfFlattenLeft = flattenTree(root.left);
        root.left = null;
        root.right = headOfFlattenLeft;
        TreeNode temp = root;
        while (temp.right != null) {
            temp = temp.right;
        }
        temp.right = flattenTree(rightPart);


        return root;
    }


}
