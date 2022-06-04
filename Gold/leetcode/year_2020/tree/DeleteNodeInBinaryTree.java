package com.leetcode.year_2020.tree;

import com.leetcode.year_2020.TreeNode;
import com.util.LogUtil;

import java.util.LinkedList;
import java.util.Queue;

public class DeleteNodeInBinaryTree {

    public static void main(String[] args) {
        /**
         *          1
         *         / \
         *        2   3
         *      /    /
         *     4    5
         *      \
         *       6
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.left.left.right = new TreeNode(6);

        TreeNode.inorder(root);
        for (int i = 1; i <= 6; i++) {
            root = deleteNode(root, i);
            TreeNode.inorder(root);
        }
    }

    static class Pair {
        TreeNode valToDelete;
        TreeNode lastNodeParent;
        TreeNode lastNodeInLastLevel;
    }

    public static TreeNode deleteNode(TreeNode root, int val) {
        LogUtil.logIt("After deleting " + val, true);
        // DO BFS and find both last-level last node
        // and the one node with val
        Pair pair = doBFSAndFindPair(root, val);

        if (pair.valToDelete != null) {
            pair.valToDelete.val = pair.lastNodeInLastLevel.val;
            if (pair.lastNodeParent != null) {
                if (pair.lastNodeParent.left == pair.lastNodeInLastLevel) {
                    pair.lastNodeParent.left = null;
                } else {
                    pair.lastNodeParent.right = null;
                }
            }
        }
        return root;
    }

    private static Pair doBFSAndFindPair(TreeNode root, final int val) {
        if (root == null) return null;
        final Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        Pair pair = new Pair();

        while (!queue.isEmpty()) {
            root = queue.poll();
            pair.lastNodeInLastLevel = root;
            if (val == root.val) {
                pair.valToDelete = root;
            }
            if (root.left != null) {
                pair.lastNodeParent = root;
                queue.add(root.left);
            }
            if (root.right != null) {
                pair.lastNodeParent = root;
                queue.add(root.right);
            }
        }
        return pair;
    }
}
