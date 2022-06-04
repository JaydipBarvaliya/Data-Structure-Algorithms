package com.leetcode.year_2020.tree;

import com.leetcode.year_2020.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author neeraj on 25/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ConstructBinaryTreeUsingPreOrderAndInorderTraversal {

    public static int longestCommonSubsequence(String text1, String text2) {
        if(text1 == null || text2 == null) {
            return 0;
        }
        // Making sure text1 always bigger or equal to text2;
        // if(text1.length() < text2.length()) {
        //     String temp = text1;
        //     text1 = text2;
        //     text2 = temp;
        // }

        int [][] lcs = new int[text2.length()+1][text1.length()+1];

        for(int i=1;i<lcs.length;i++) {
            for(int j=1;j<lcs[i].length;j++) {
                // Compare characters from both inputs
                if(text2.charAt(i-1) == text1.charAt(j-1)) {
                    lcs[i][j] = lcs[i-1][j-1] + 1;
                } else {
                    lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
                }
            }
        }
        return lcs[lcs.length-1][lcs[0].length -1];
    }

    public static void main(String[] args) {

        System.out.println(longestCommonSubsequence("bsbininm","jmjkbkjkv"));
        System.out.println(longestCommonSubsequence("abc","abc"));

        /**
         * preorder = [3,9,20,15,7]
         * inorder = [9,3,15,20,7]
         *
         *    3
         *    / \
         *   9  20
         *     /  \
         *    15   7
         */
        int preorder[] = new int[]{3, 9, 20, 15, 7};
        int inorder[] = new int[]{9, 3, 15, 20, 7};
        TreeNode root = null;//buildTree(preorder, inorder);
        TreeNode.inorder(root);

        preorder= new int[]{-1};
        inorder = new int[]{-1};
        root = buildTree(preorder, inorder);
        TreeNode.inorder(root);

    }

    static int preOrderCounter = 0;

    /**
     * Public facing method which will build tree from preorder and inorder.
     */
    public static TreeNode buildTreeFromPreOrderAndInorder(int[] preorder, int[] inorder) {
        preOrderCounter = 0;
        return buildTree(preorder, inorder);
    }

    /**
     * Private method
     * @param preorder
     * @param inorder
     * @return
     */
    private static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) return null;
        TreeNode root = new TreeNode(preorder[preOrderCounter++]);
        int indexOfRootAtInorderTraversal = findRoot(inorder, root.val);

        root.left = buildTree(preorder, Arrays.copyOfRange(inorder, 0, indexOfRootAtInorderTraversal));
        root.right = buildTree(preorder, Arrays.copyOfRange(inorder, indexOfRootAtInorderTraversal + 1, inorder.length));

        return root;
    }

    private static int findRoot(int[] inorder, int val) {
       for(int i=0;i<inorder.length;i++) {
           if(inorder[i]== val) {
               return i;
           }
       }
       return -1;
    }
}
