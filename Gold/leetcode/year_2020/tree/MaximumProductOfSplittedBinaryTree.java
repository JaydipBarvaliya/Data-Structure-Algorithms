package com.leetcode.year_2020.tree;

import com.leetcode.year_2020.TreeNode;

/**
 * https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/
 * 1339. Maximum Product of Splitted Binary Tree
 */
public class MaximumProductOfSplittedBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        System.out.println(maxProduct(root));
    }

    static long MAX_PRODUCT = 0;
    static long totalSum = 0;

    public static int maxProduct(TreeNode root) {
        MAX_PRODUCT = 0;

        // Get complete tree sum
        totalSum = subTreeSum(root);

        // Now we have whole tree sum, Now let's calculate
        subTreeSum(root);

        return (int) (MAX_PRODUCT % (int) (1e9 + 7));
    }

    public static long subTreeSum(TreeNode root) {
        if (root == null) return 0;
        long result = root.val + subTreeSum(root.left) + subTreeSum(root.right);

        // Since result is our subtree Sum we can get the product.
        MAX_PRODUCT = Math.max(MAX_PRODUCT, result * (totalSum - result));
        return result;
    }
}
