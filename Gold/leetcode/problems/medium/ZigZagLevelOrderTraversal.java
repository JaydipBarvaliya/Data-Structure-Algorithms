package com.leetcode.problems.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 *
 * @author neeraj on 13/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("DuplicatedCode")
public class ZigZagLevelOrderTraversal {

    public static void main(String[] args) {
        TreeNode zigZagRoot = new TreeNode(3);
        zigZagRoot.left = new TreeNode(9);
        zigZagRoot.right = new TreeNode(20);
        zigZagRoot.right.left = new TreeNode(15);
        zigZagRoot.right.right = new TreeNode(7);

        System.out.println(zigzagLevelOrder(zigZagRoot));

        zigZagRoot = new TreeNode(1);
        zigZagRoot.left = new TreeNode(2);
        zigZagRoot.right = new TreeNode(3);
        zigZagRoot.left.left = new TreeNode(4);
        zigZagRoot.left.right = new TreeNode(5);

        System.out.println(zigzagLevelOrder(zigZagRoot));

        zigZagRoot = new TreeNode(1);
        zigZagRoot.left = new TreeNode(2);
        zigZagRoot.right = new TreeNode(3);
        zigZagRoot.left.left = new TreeNode(4);
        zigZagRoot.right.right = new TreeNode(5);

        System.out.println(zigzagLevelOrderNew(zigZagRoot));
    }

    public static List<List<Integer>> zigzagLevelOrderNew(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();

        // For Empty Tree
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Boolean switchLevel = true;
        queue.offer(root);
        queue.offer(null);

        TreeNode temp;
        ArrayList<Integer> levelNodes = new ArrayList<>();
        while (!queue.isEmpty()) {
            temp = queue.poll();
            if (temp == null) {
                if (!queue.isEmpty()) {
                    result.add(levelNodes);
                    levelNodes = new ArrayList<>();
                    switchLevel = !switchLevel;
                    queue.add(null);
                } else {
                    result.add(levelNodes);
                }
                continue;
            }

            if (switchLevel) {
                levelNodes.add(temp.val);
            } else {
                levelNodes.add(0, temp.val);
            }

            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
        return result;
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();

        // For Empty Tree
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Boolean flag = true;
        queue.offer(root);

        TreeNode temp;

        while (!queue.isEmpty()) {
            ArrayList<Integer> levelNodes = new ArrayList<>();
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                temp = queue.poll();
                if (flag) {
                    levelNodes.add(temp.val);
                } else {
                    levelNodes.add(0, temp.val);
                }
                if (temp.left != null)
                    queue.offer(temp.left);
                if (temp.right != null)
                    queue.offer(temp.right);
            }
            result.add(levelNodes);
            flag = !flag;
        }
        return result;
    }
}
