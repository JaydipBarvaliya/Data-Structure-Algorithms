package com.leetcode.tree;

import com.leetcode.problems.medium.TreeNode;
import com.leetcode.year_2020.prefix_sum_technique.SubArraySumEqualsK_UsingPrefixSum;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/path-sum-iii/
 * <p>
 * 437. Path Sum III
 * *             10
 * *            /  \
 * *           5    -3
 * *         /  \     \
 * *        3    2    11
 * *       /  \   \
 * *      3   -2   1
 * *
 * * Given the root of a binary tree and an integer targetSum,
 * * return the number of paths where the sum of the values along the path equals targetSum.
 */
public class PathSum3 {

    public static void main(String[] args) {
        /**
         * How this problem is different from {@link PathSum} is in those problem we have to take
         * root to leaf paths so we just have to do DFS from the root, but here sum can start from any node.
         * So a brute force approach is basically to do the DFS on every node.
         */
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(1);

        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);

        root.right.right = new TreeNode(11);

        System.out.println("Total Path Sum with target " + pathSum3_version1(root, 8));

        /**
         * The Optimized approach is not to recalculate the sum from node to another node,
         * rather utilized already pre calculated sum, this can be easily achieved by
         * prefix sum technique as explained in {@link SubArraySumEqualsK_UsingPrefixSum}
         */
        System.out.println("Total PathSum with target using Prefix Sum " + pathSum3_usingPrefixSum(root, 8));
    }

    /**
     * This will take o(n^2) time
     */
    public static int pathSum3_version1(TreeNode root, int targetSum) {
        if (root == null) return 0;

        return totalPathSumFromThisRoot(root, targetSum) +
                pathSum3_version1(root.left, targetSum) +
                pathSum3_version1(root.right, targetSum);
    }

    private static int totalPathSumFromThisRoot(final TreeNode root, final int targetSum) {
        if (root == null) {
            return 0;
        }
        int totalPathSumFromThisRoot = targetSum - root.val == 0 ? 1 : 0;
        return totalPathSumFromThisRoot +
                totalPathSumFromThisRoot(root.left, targetSum - root.val) +
                totalPathSumFromThisRoot(root.right, targetSum - root.val);
    }

    public static int pathSum3_usingPrefixSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        final Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1); // How many ways you can make a sum of 0 without any element, simply empty set{}
        return pathSumHelper(root, targetSum, 0, prefixSum);
    }

    private static int pathSumHelper(final TreeNode root, final int targetSum, int runningSum,
                                     final Map<Integer, Integer> prefixSum) {
        if (root == null) {
            return 0;
        }
        runningSum += root.val;
        int totalPath = prefixSum.getOrDefault(runningSum - targetSum, 0);
        prefixSum.put(runningSum, prefixSum.getOrDefault(runningSum, 0) + 1);
        totalPath += pathSumHelper(root.left, targetSum, runningSum, prefixSum)
                + pathSumHelper(root.right, targetSum, runningSum, prefixSum);
        prefixSum.put(runningSum, prefixSum.get(runningSum) - 1); // Since we have explored this running sum, let's remove it
        return totalPath;
    }
}
