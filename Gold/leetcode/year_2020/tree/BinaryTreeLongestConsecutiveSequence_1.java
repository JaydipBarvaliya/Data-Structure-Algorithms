package com.leetcode.year_2020.tree;

import com.leetcode.year_2020.TreeNode;

/**
 * https://www.lintcode.com/problem/595/
 * 595 · Binary Tree Longest Consecutive Sequence
 */
public class BinaryTreeLongestConsecutiveSequence_1 {

    public static void main(String[] args) {

    }

    static int LONGEST = 0;

    public int longestConsecutive(TreeNode root) {
        LONGEST = 0;
        longest(root, null, 1);
        return LONGEST;
    }

    private void longest(TreeNode root, TreeNode parent, int distance) {
        if (root == null) return;
        if (parent != null) {
            if (root.val - parent.val == 1) {
                distance++;
            } else {
                distance = 1;
            }
        }
        LONGEST = Math.max(distance, LONGEST);
        longest(root.left, root, distance);
        longest(root.right, root, distance);
    }
}
