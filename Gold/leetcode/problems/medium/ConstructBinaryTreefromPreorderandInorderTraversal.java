package com.leetcode.problems.medium;

import java.util.Arrays;

/**
 * @author neeraj on 12/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ConstructBinaryTreefromPreorderandInorderTraversal {

    public static void main(String[] args) {
        TreeNode node = buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        System.out.println(node);

        preOrderCounter = 0;
        node = buildTree(new int[]{1, 2}, new int[]{1, 2});
        System.out.println(node);

        node = buildTree(new int[]{-1}, new int[]{-1});
        System.out.println(node);
    }

    static int preOrderCounter = 0;

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        preOrderCounter = 0;
        return buildTreeUtil(preorder, inorder);
    }

    private static TreeNode buildTreeUtil(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) return null;
        TreeNode root = new TreeNode(preorder[preOrderCounter++]);

        int indexOfRootIn_Inorder = searchVal(inorder, root.val);

        if (indexOfRootIn_Inorder > 0) {
            root.left = buildTreeUtil(preorder,
                    Arrays.copyOfRange(inorder, 0, indexOfRootIn_Inorder));
        }
        if (indexOfRootIn_Inorder < inorder.length - 1) {
            root.right = buildTreeUtil(preorder,
                    Arrays.copyOfRange(inorder, indexOfRootIn_Inorder + 1, inorder.length));
        }
        return root;
    }

    private static int searchVal(int[] arr, int search) {
        int counter = 0;
        for (int val : arr) {
            if (val == search) {
                return counter;
            }
            counter++;
        }
        return -1;
    }
}
