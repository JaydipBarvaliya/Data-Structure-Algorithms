package com.leetcode.problems.easy;

import com.util.LogUtil;

/**
 * @author neeraj on 19/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SortedLinkedListToBalancedBST {

    static TreeNode root;

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " , ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        convertSortedArrayToBalancedBST(new int[]{1, 2, 3});
        convertSortedArrayToBalancedBST(new int[]{1, 2, 3, 4});
        convertSortedArrayToBalancedBST(new int[]{-10,-3,0,5,9});
    }

    public static void convertSortedArrayToBalancedBST(int[] arr) {
        root = null;
        sortedArrayToBalancedBST(arr, 0, arr.length - 1);
        inorder(root);
        LogUtil.newLine();

    }

    private static TreeNode sortedArrayToBST(int [] arr, int low, int high) {
        if(low > high)
            return null;
        int mid = low + (high - low) / 2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = sortedArrayToBST(arr, low, mid-1);
        root.right = sortedArrayToBST(arr, mid+1, high);
        return root;
    }

    private static void sortedArrayToBalancedBST(int[] arr, int low, int high) {
        if (low <= high) {
            int mid = low + (high - low) / 2;

            if (root == null) {
                root = new TreeNode(arr[mid]);
            } else {
                insertIntoBST(root, arr[mid]);
            }
            sortedArrayToBalancedBST(arr, low, mid - 1);
            sortedArrayToBalancedBST(arr, mid + 1, high);
        }
    }

    private static TreeNode insertIntoBST(TreeNode root, int data) {
        if (root == null) {
            return new TreeNode(data);
        }
        if (root.val > data) {
            root.left = insertIntoBST(root.left, data);
        } else {
            root.right = insertIntoBST(root.right, data);
        }
        return root;
    }
}
