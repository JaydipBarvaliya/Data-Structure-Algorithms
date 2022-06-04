package com.leetcode.year_2020.tree;

import com.leetcode.year_2020.TreeNode;

/**
 * https://www.lintcode.com/problem/614/description
 */
public class BinaryTreeLongestConsecutiveSequence_2 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(3);

        System.out.println(longestConsecutive2(root));
    }

    static class Node {
        int increasingLength;
        int decreasingLength;

        public Node(final int increasingLength, final int decreasingLength) {
            this.increasingLength = increasingLength;
            this.decreasingLength = decreasingLength;
        }
    }

    public static int longestConsecutive2(TreeNode root) {
        /**
         *      2
         *     / \
         *    1   3
         *
         * At every node we check 2 values [Increasing Length, Decreasing Length] from the parent node
         * https://www.youtube.com/watch?v=T_jsUBqQjq4
         *              2
         *          /       \
         *        /          \
         *      1             3
         *  [inc=1,dec=0]   [inc=0,dec=1]
         *   /     \         /     \
         *  NULL - NULL  -   NULL - NULL
         * [0,0]  [0,0]     [0,0]  [0,0]
         *
         * Initially all null nodes will have [0,0] as increasingLength and decreasing length
         * Now with leaf node 1, we check with parent(2) and noticed that 2 > 1 (and 2-1 = 1) hence increasing length = 1;
         * and with leaf node 3, we check with parent(2) and noticed that 2 < 3 (and 2-3 = -1) hence decreasing length = 1;
         *
         * Now when we are standing at 2, we compare left[increasing] + right[decreasing] + 1(this one is added for the root itself)
         *
         */
        int[] result = new int[1];
        result[0] = 0;
        helper(root, null, result);
        return result[0];
    }

    private static Node helper(final TreeNode root, final TreeNode parent, final int[] result) {
        if (root == null) {
            return new Node(0, 0);
        }
        Node left = helper(root.left, root, result);
        Node right = helper(root.right, root, result);

        result[0] = Math.max(result[0],
                Math.max(left.increasingLength + right.decreasingLength + 1, left.decreasingLength + right.increasingLength + 1));

        if (parent != null && root.val + 1 == parent.val) {
            return new Node(Math.max(left.increasingLength, right.increasingLength) + 1, 0);
        } else if (parent != null && root.val - 1 == parent.val) {
            return new Node(0, Math.max(left.decreasingLength, right.decreasingLength) + 1);
        } else {
            return new Node(0, 0);
        }
    }

}
