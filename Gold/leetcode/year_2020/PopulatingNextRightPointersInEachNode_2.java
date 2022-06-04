package com.leetcode.year_2020;

import static com.leetcode.year_2020.tree.ConstructBinaryTreeUsingPreOrderAndInorderTraversal.buildTreeFromPreOrderAndInorder;

/**
 * @author neeraj on 26/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PopulatingNextRightPointersInEachNode_2 {

    public static void main(String[] args) {
        TreeNode connected = connect(buildTreeFromPreOrderAndInorder(new int[]{1, 2, 4, 5, 3, 7}, new int[]{4, 2, 5, 1, 3, 7}));
        connected.inorder(connected);
    }

    /**
     * Tree is Not prefect in this case.
     */
    public static TreeNode connect(TreeNode root) {
        if (root == null) return null;

        if (root.left != null) {
            if (root.right != null) {
                root.left.next = root.right;
            } else { // When Root's right is null
                // we have to travel to root --> next --> next [till we find a left or a right child]
                root.left.next = findNext(root.next);
            }
        }
        if (root.right != null && root.next != null) {
            root.right.next = findNext(root.next);
        }


        /**
         * Since tree is not prefect we want to make sure that next of every level is populated before we start iterating
         * the left Subtree
         *                      1
         *                     / \
         *                    2   3
         *                   / \  /\
         *                  4  5 6  7
         *                 /       / \
         *               10       8   9
         * In this scenario when we have to connect 10 to 8.
         * 4 -next-> 5 -next-> 6 -next-> 7 (Connection has to be made)
         * If we start iterating left subtree first, when you reach to 4
         * 6 -next-> 7 link is not set.
         *
         * Hence we are choosing to iterate right subtree first.
         */
        root.right = connect(root.right);
        root.left = connect(root.left);

        return root;
    }

    public static TreeNode findNext(TreeNode root) {
        while (root != null) {
            if (root.left != null || root.right != null) {
                return root.left != null ? root.left : root.right;
            }
            root = root.next;
        }
        return null;
    }
}
