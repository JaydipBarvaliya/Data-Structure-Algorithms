package com.leetcode.year_2020;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/maximum-width-of-binary-tree/
 *
 * @author neeraj on 09/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaximumWidthOfBinaryTree {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(2);

        treeNode.left.left = new TreeNode(5);
        treeNode.left.left.left = new TreeNode(6);

        treeNode.right.right = new TreeNode(9);
        treeNode.right.right.right = new TreeNode(7);

        System.out.println(widthOfBinaryTree(treeNode));

        treeNode = new TreeNode(2);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(3);

        treeNode.left.right = new TreeNode(5);
        treeNode.right.right = new TreeNode(4);

        System.out.println(widthOfBinaryTree(treeNode));
    }

    static class TreeNodeWithItsIndex {
        TreeNode node;
        int indexInTheTree;

        public TreeNodeWithItsIndex(TreeNode node, int indexInTheTree) {
            this.node = node;
            this.indexInTheTree = indexInTheTree;
        }
    }

    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        /**
         * Okay so we know how to do a level order traversal, whats interesting in this is that we need index of nodes
         * at a particular level to know how many nodes can be there in the extreme left and right level of a particular node.
         *
         * "The binary tree has the same structure as a full binary tree, but some nodes are null" what this means is
         * that our first node will be the leftmost node in that level and the last node will be extreme right node.
         *
         * Left Child of a parent resides at : (2*i) + 1;
         * Right Child of a parent resides at : (2*i) + 2;
         */
        // We will do a level order traversal
        Queue<TreeNodeWithItsIndex> queue = new LinkedList<>();
        queue.add(new TreeNodeWithItsIndex(root, 0));
        int width = 1;

        while (!queue.isEmpty()) {
            int count = queue.size();
            int start = queue.peek().indexInTheTree;

            TreeNodeWithItsIndex popped = null;
            // Iterating through this level...... similar to pushing null and iterating through it.
            for (int i = 0; i < count; i++) {
                popped = queue.poll();
                if (popped.node.left != null) {
                    queue.add(new TreeNodeWithItsIndex(popped.node.left, 2 * popped.indexInTheTree + 1));
                }
                if (popped.node.right != null) {
                    queue.add(new TreeNodeWithItsIndex(popped.node.right, 2 * popped.indexInTheTree + 2));
                }
            }
            // Here popped is representing the last index of a particular level.
            int end = popped.indexInTheTree;
            width = Math.max(width, end - start + 1);
        }
        return width;
    }
}
