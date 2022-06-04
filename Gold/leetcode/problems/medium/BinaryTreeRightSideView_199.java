package com.leetcode.problems.medium;

import java.util.*;

/**
 * @author neeraj on 15/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class BinaryTreeRightSideView_199 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

        System.out.println(rightSideView(root));
        System.out.println(rightSideViewImp(root));
    }

    public static List<Integer> rightSideViewImp(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode polled = queue.poll();
                if (i == size - 1) {
                    result.add(polled.val);
                }
                if (polled.left != null) {
                    queue.add(polled.left);
                }
                if (polled.right != null) {
                    queue.add(polled.right);
                }
            }
        }
        return result;
    }

    static boolean levelTraversed = false;

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        int height = getHeight(root);
        if (height == 0)
            return result;
        Map<Integer, Integer> data = new TreeMap<>();
        for (int i = 0; i < height; i++) {
            levelTraversed = false;
            traverseLevel(root, i, i, data);
        }
        return new ArrayList<>(data.values());
    }

    private static void traverseLevel(TreeNode root, int currentLevel, int levelToTraverse,
                                      Map<Integer, Integer> data) {
        if (levelTraversed)
            return;
        if (root == null || levelToTraverse < 0)
            return;
        if (levelToTraverse == 0) {
            data.put(currentLevel, root.val);
            levelTraversed = true;
        }
        traverseLevel(root.right, currentLevel, levelToTraverse - 1, data);
        traverseLevel(root.left, currentLevel, levelToTraverse - 1, data);
    }

    private static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}
