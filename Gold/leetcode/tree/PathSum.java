package com.leetcode.tree;

import com.leetcode.problems.medium.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/path-sum/
 * https://leetcode.com/problems/path-sum-ii/
 *
 * @author neeraj on 05/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PathSum {

    static TreeNode root;

    public static void main(String[] args) {
        root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);

        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);

        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        System.out.println(pathSum(root, 22));
    }

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root != null) {
            traverseTree(root, sum, 0, new ArrayList<>(), result);
        }
        return result;
    }

    public static void traverseTree(TreeNode root, int sum, int currentSum,
                                    List<Integer> path, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        if (isLeaf(root)) {
            if (sum == currentSum + root.val) {
                // Cloning it.
                path.add(root.val);
                result.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
            }
            return;
        }
        path.add(root.val);
        traverseTree(root.left, sum, currentSum + root.val, path, result);
        traverseTree(root.right, sum, currentSum + root.val, path, result);
        path.remove(path.size() - 1);
    }

    public static boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }
}

