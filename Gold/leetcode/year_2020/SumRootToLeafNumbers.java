package com.leetcode.year_2020;


/**
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/
 *
 * @author neeraj on 23/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SumRootToLeafNumbers {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(sumNumbers(root));
    }

    /**
     * Input: [1,2,3]
     *  1
     * / \
     * 2   3
     * Output: 25
     * Explanation:
     * The root-to-leaf path 1->2 represents the number 12.
     * The root-to-leaf path 1->3 represents the number 13.
     * Therefore, sum = 12 + 13 = 25.
     *
     * @param root
     * @return
     */
    public static int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        return sumRootToLeafNumbers(root, 0);
    }

    public static int sumRootToLeafNumbers(TreeNode root, int currSum) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
            return currSum * 10 + root.val;
        }

        return sumRootToLeafNumbers(root.left, currSum * 10 + root.val)
                + sumRootToLeafNumbers(root.right, currSum * 10 + root.val);

    }
}
