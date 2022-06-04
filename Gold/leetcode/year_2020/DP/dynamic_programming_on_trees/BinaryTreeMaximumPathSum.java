package com.leetcode.year_2020.DP.dynamic_programming_on_trees;

import com.leetcode.year_2020.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * 124. Binary Tree Maximum Path Sum
 * @author neeraj on 13/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class BinaryTreeMaximumPathSum {

    public static void main(String[] args) {

    }

    static int MAX_SUM = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        MAX_SUM = Integer.MIN_VALUE;
        fetchMaxPathSum(root);
        return MAX_SUM == Integer.MIN_VALUE ? 0 : MAX_SUM;
    }

    public static int fetchMaxPathSum(TreeNode root) {
        if (root == null) return 0;

        // Hypothesis
        // We have to check maxPathSum at each node
        int maxSumInLeft = fetchMaxPathSum(root.left);
        int maxSumInRight = fetchMaxPathSum(root.right);

        // Induction

        /**
         * Now at this node assume this node is not the root of tree
         * but the root of a subtree.
         *                             10
         *                            /   \
         * Assume we are here ===>   2     10
         *                          / \     \
         *                        20   1     10
         *                                    \
         *                                    -25
         *                                    /  \
         *                                   5    4
         * So we need to calculate what maximum sum we can return to our caller.
         * i.e either just 2 or  2   (2+20)   or 2    (2+1).
         *                      /                 \
         *                     20                  1
         *
         * Now you will ask when is this possible that only node is needed and not left and right.
         * When we have negative values in our node.
         * So our maxPath Sum can go in these 4 ways :
         *  a) MaxPath is contributed by just the Node
         *  b) MaxPath is contributed by Node plus left
         *  c) MaxPath is contributed by Node plus right
         *  d) MaxPath is contributed by Node + left + right
         *
         *       25                       25
         *     /   \                     /  \
         *    -30 -40                   30 -40
         *      (a)                       (b)
         *
         *       25                       25
         *     /   \                     /  \
         *    -30  40                   30  40
         *      (c)                       (d)
         */

        int temp = root.val; // Assume initially the root of subtree has max Sum

        // It's not necessary that the below 2 statement change the original answer
        // that can happen when both left and right child is negative.
        /**
         * a) in above figure.
         */
        temp = Math.max(temp, root.val + maxSumInLeft); // Either node + left
        temp = Math.max(temp, root.val + maxSumInRight);// Either node + right

        /**
         * Why in temp we are not doing root.val + maxSumInLeft + maxSumInRight
         *
         * Reason is simple Path to Root can only go like this
         *
         *                      10       or             10          or just 10.
         *                     /                        /
         *                    2                        2
         *                   /                          \
         *                  20                           1
         *
         * It cannot be go in both left and right.
         */

        // Now check whether or not this particular subtree contribute to
        // the maxSum
        int answer = Math.max(temp, root.val + maxSumInLeft + maxSumInRight);

        MAX_SUM = Math.max(MAX_SUM, answer);
        return temp;
    }
}
