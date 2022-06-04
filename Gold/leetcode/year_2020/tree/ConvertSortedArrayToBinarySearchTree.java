package com.leetcode.year_2020.tree;

import com.leetcode.year_2020.TreeNode;

public class ConvertSortedArrayToBinarySearchTree {

    public static void main(String[] args) {
        TreeNode root = sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        TreeNode.inorder(root);
        System.out.println();
        root = sortedArrayToBST(new int[]{1, 2, 3, 4, 5, 6});
        TreeNode.inorder(root);
        System.out.println();
        root = sortedArrayToBST(new int[]{1,3});
        TreeNode.inorder(root);
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public static TreeNode helper(int[] nums, int low, int high) {
        if (low <= high) {
            int mid = low + (high - low) / 2;
            TreeNode root = new TreeNode(nums[mid]);
            root.left = helper(nums, low, mid - 1);
            root.right = helper(nums, mid + 1, high);
            return root;
        }
        return null;
    }
}
