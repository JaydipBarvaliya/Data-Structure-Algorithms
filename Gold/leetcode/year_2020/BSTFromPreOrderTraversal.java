package com.leetcode.year_2020;


import java.util.Arrays;

/**
 * @author neeraj on 20/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class BSTFromPreOrderTraversal {

    public static void main(String[] args) {
        int[] preOrder = new int[]{8, 5, 1, 7, 10, 12};
        inOrder(bstFromPreorder(preOrder));
        System.out.println("==============");
        preOrder = new int[]{4, 2};
        inOrder(bstFromPreorder(preOrder));
    }

    public static void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.val + "\t");
        inOrder(root.right);
    }

    public static TreeNode bstFromPreorder(int[] preorder) {
        if (preorder.length == 0) return null;
        if (preorder.length == 1) return new TreeNode(preorder[0]);
        TreeNode root = new TreeNode(preorder[0]); // In PreOrder head is always at 1st location.

        int indexOfNodeSmallerThanRoot = findLastIndexOfNodeSmallerThanRoot(preorder, 1, preorder.length - 1);

        if (indexOfNodeSmallerThanRoot != 0) {
            root.left = bstFromPreorder(Arrays.copyOfRange(preorder, 1, indexOfNodeSmallerThanRoot + 1));
        }
        root.right = bstFromPreorder(Arrays.copyOfRange(preorder, indexOfNodeSmallerThanRoot + 1, preorder.length));
        return root;
    }

    // We'll do a binary search.
    private static int findLastIndexOfNodeSmallerThanRoot(int[] preorder, int low, int high) {
        if (low == high && preorder[low] < preorder[0]) return low; // This is the case for 2 elements.
        if (low < high) {
            int mid = low + (high - low) / 2;
            if ((preorder[mid] < preorder[0] && preorder[mid + 1] > preorder[0])) {
                return mid;
            }
            if (preorder[mid] > preorder[0]) {
                return findLastIndexOfNodeSmallerThanRoot(preorder, low, mid - 1);
            } else {
                return findLastIndexOfNodeSmallerThanRoot(preorder, mid + 1, high);
            }
        }
        return 0;
    }
}
