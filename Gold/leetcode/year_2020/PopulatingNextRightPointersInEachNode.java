package com.leetcode.year_2020;

import static com.leetcode.year_2020.tree.ConstructBinaryTreeUsingPreOrderAndInorderTraversal.buildTreeFromPreOrderAndInorder;
import static com.leetcode.year_2020.TreeNode.inorder;

/**
 * @author neeraj on 26/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PopulatingNextRightPointersInEachNode {

    public static void main(String[] args) {
        TreeNode tree = buildTreeFromPreOrderAndInorder(
                new int[]{1,2,4,5,3,6,7},
                new int[]{4,2,5,1,6,3,7}
                );

        TreeNode connectedRoot = connect(tree);
        inorder(connectedRoot);
    }

    public static TreeNode connect(TreeNode root) {
        if(root == null) return null;
        if(root.left != null) {
            root.left.next = root.right;
        }
        if(root.right != null && root.next != null) {
            root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }
}
