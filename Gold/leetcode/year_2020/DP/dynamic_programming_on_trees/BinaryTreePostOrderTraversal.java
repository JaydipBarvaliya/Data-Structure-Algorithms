package com.leetcode.year_2020.DP.dynamic_programming_on_trees;

import com.leetcode.year_2020.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author neeraj on 13/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class BinaryTreePostOrderTraversal {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);
        treeNode.right.left = new TreeNode(6);
        treeNode.right.right = new TreeNode(7);

        treeNode.postOrder(treeNode);

        System.out.println(postOrderTraversal(treeNode));
    }

    public static List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            if (root == null) {
                root = stack.pop();
            }
            result.add(0, root.val);
            if (root.left != null) {
                stack.push(root.left);
            }
            root = root.right;
        }
        return result;
    }
}
