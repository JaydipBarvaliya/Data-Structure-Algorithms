package com.leetcode.year_2020.tree;

import com.leetcode.year_2020.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://www.lintcode.com/problem/650/
 * 650 · Find Leaves of Binary Tree
 */
public class FindLeavesOfBinaryTree {

    public static void main(String[] args) {
        /**
         *     1
         *    / \
         *   2   3
         *  / \
         * 4   5
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(findLeaves(root));

        /**
         *
         *     1
         *    / \
         *   2   3
         *  /
         * 4
         */
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        System.out.println(findLeaves(root));
    }

    public static List<List<Integer>> findLeaves(TreeNode root) {
        final List<List<Integer>> result = new ArrayList<>();
        final Set<TreeNode> visited = new HashSet<>();
        while (true) {
            final List<Integer> leaves = new ArrayList<>();
            findLeaves(root, leaves, visited);
            if (leaves.isEmpty()) {
                break;
            } else {
                result.add(leaves);
            }
        }
        return result;
    }

    public static boolean findLeaves(TreeNode root, final List<Integer> leaves,
                                     final Set<TreeNode> seen) {
        if (root == null || seen.contains(root)) return true;
        Boolean isLeftVisited = findLeaves(root.left, leaves, seen);
        Boolean isRightVisited = findLeaves(root.right, leaves, seen);
        if (isLeftVisited && isRightVisited) {
            leaves.add(root.val);
            seen.add(root);
        }
        return false;
    }
}
