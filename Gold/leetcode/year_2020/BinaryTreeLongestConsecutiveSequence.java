package com.leetcode.year_2020;

/**
 * @author neeraj on 29/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class BinaryTreeLongestConsecutiveSequence {

    public static void main(String[] args) {
        TreeNode root = SerializeAndDeserializeBinaryTree.deserialize("1,X,3,2,X,X,4,X,5,X,X,");
        System.out.println(longestConsecutive(root));
        root = SerializeAndDeserializeBinaryTree.deserialize("3,2,X,X,2,X,X");
        System.out.println(longestConsecutive(root));
    }

    static int LONGEST_SEQUENCE = 0;

    public static int longestConsecutive(TreeNode root) {
        LONGEST_SEQUENCE = 0;
        findConsecutiveSequence(root, 0, 0);
        return LONGEST_SEQUENCE;
    }

    private static void findConsecutiveSequence(TreeNode root, int count, int target) {
        if (root == null) return;
        if (root.val == target) {
            count++; // Incrementing the sequence.
        } else {
            count = 1; // Now we have to reset since at this moment we have just 1 item which is consecutive which is the item itself.
        }
        LONGEST_SEQUENCE = Math.max(LONGEST_SEQUENCE, count);
        findConsecutiveSequence(root.left, count, root.val + 1);
        findConsecutiveSequence(root.right, count, root.val + 1);
    }
}
