package com.leetcode.year_2020;

import static com.leetcode.year_2020.tree.ConstructBinaryTreeUsingPreOrderAndInorderTraversal.buildTreeFromPreOrderAndInorder;

/**
 * @author neeraj on 26/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class TestIfABinaryTreeIsHeightBalanced {

    public static void main(String[] args) {
        System.out.println(isBalanced(buildTreeFromPreOrderAndInorder(
                new int[]{1, 2, 3, 4, 2, 3, 4},
                new int[]{4, 3, 2, 1, 2, 3, 4})));
        System.out.println(isBalanced(buildTreeFromPreOrderAndInorder(
                new int[]{3, 9, 20, 15, 7},
                new int[]{9, 3, 15, 20, 7})));

    }

    static class HeightAndBalanced {
        int height;
        boolean isBalanced;

        HeightAndBalanced(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }

    public static boolean isBalanced(TreeNode root) {
        HeightAndBalanced heightAndBalanced = isBalancedUtil(root);
        return heightAndBalanced.isBalanced;
    }

    public static HeightAndBalanced isBalancedUtil(TreeNode root) {
        if (root == null) return new HeightAndBalanced(-1, true);

        HeightAndBalanced left = isBalancedUtil(root.left);
        if (!left.isBalanced) {
            return left;
        }

        HeightAndBalanced right = isBalancedUtil(root.right);
        if (!right.isBalanced) {
            return right;
        }


        HeightAndBalanced curr = new HeightAndBalanced(
                Math.max(left.height, right.height) + 1,
                left.isBalanced && right.isBalanced && Math.abs(left.height - right.height) <= 1);
        return curr;
    }
}
