package com.leetcode.tree;

import com.leetcode.year_2020.TreeNode;
import com.util.LogUtil;

/**
 * https://leetcode.com/problems/recover-binary-search-tree/
 *
 * @author neeraj on 13/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RecoverBinarySearchTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);

//        root.inorder(root);
//
//        recoverTree(root);
//
//        LogUtil.logIt("\n After Recovering BST");
//        root.inorder(root);

        root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.inorder(root);
        recoverTree(root);
        LogUtil.logIt("\n After Recovering BST");
        root.inorder(root);
    }


    public static void recoverTree(TreeNode root) {
        /*
         * we'll recover this tree with Morris traversal
         * In Morris traversal we do the printing in 2 places
         * 1 when there is nothing in the left
         *
         * 2nd when we are fixing the tree(after destroying it :p =D)
         *
         *
         * Also as we know only 2 nodes are displaced, so we'll fix it via constant space
         *
         * */
        TreeNode first = null;
        TreeNode second = null;
        TreeNode prev = null;

        while (root != null) {
            if (root.left == null) {
                // This is the business logic, where we'll set the values
                if (prev != null && prev.val > root.val) {
                    // Means, the bst property is not maintained in inorder traversal
                    if (first == null) {
                        first = prev;
                    }
                    /**
                     *  Why first = prev where as second = root;
                     *  TAKE example inorder of destroyed BST [3,2,1]
                     *  Here the very first destroyed encounter will be
                     *  3(which is prev) > 2(which is root);
                     *  Now we know 3 is not in the right position, hence firstElement is 3.
                     *  we also know that to maintain BST inorder, the only element which
                     *  can replace 3 is (something) < 2.
                     *  hence after setting first, when we see again pre > root.val
                     *  we know for sure root element is the culprit this time and not
                     *  pre, since only 2 elements are non wrong position
                     */
                    second = root;
                }
                prev = root;
                root = root.right;
            } else {
                // when root.left != null
                TreeNode leftOfRoot = root.left;
                while (leftOfRoot.right != null && leftOfRoot.right != root) {
                    leftOfRoot = leftOfRoot.right;
                }

                if (leftOfRoot.right == null) {
                    // Make that thread
                    leftOfRoot.right = root;
                    root = root.left;
                } else {
                    // There was existing thread so let's just fix it
                    // this is where we check for invalid node again
                    leftOfRoot.right = null;

                    if (prev != null && prev.val > root.val) {
                        if (first == null) {
                            first = prev;
                        }
                        second = root;
                    }
                    prev = root;
                    root = root.right;
                }
            }
        }
        if (first != null && second != null) {
            int secondVal = second.val;
            second.val = first.val;
            first.val = secondVal;
        }
    }
//
//    static TreeNode firstElement = null; // The Element which is not in the correct order
//    static TreeNode secondElement = null; // This is the pair for firstElement since these 2 are swapped incorrectly
//    static TreeNode previousElement = null;
//
//    public static void recoverTree(TreeNode root) {
//        firstElement = null;
//        secondElement = null;
//        previousElement = new TreeNode(Integer.MIN_VALUE); // This is set to minimum so that first node doesn't looks like out of order, since you cannot compare just 1 node we have to start with the second node only
//
//        // Do Inorder traversal and set these items.
//        inorder(root);
//
//        int temp = firstElement.val;
//        firstElement.val = secondElement.val;
//        secondElement.val = temp;
//    }
//
//    public static void inorder(TreeNode root) {
//        if (root == null) return;
//
//        inorder(root.left);
//
//        // Here we will do our business
//        // i.e to find out first and second elements which were not in correct order
//
//        if (firstElement == null && previousElement.val > root.val) {
//            firstElement = previousElement;
//        }
//
//        // Immediately after setting the first element we will assign root as the
//        // second element since root is also not in the correct order
//        if (firstElement != null && previousElement.val > root.val) {
//            secondElement = root;
//        }
//
//
//        // Now Since we are moving forward root will become the previous Element
//        previousElement = root;
//
//
//        inorder(root.right);
//
//    }
}
