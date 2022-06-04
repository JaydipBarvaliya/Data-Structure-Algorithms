package com.leetcode.year_2020.tree;

import com.leetcode.year_2020.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class EqualTreePartition {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(10);
        root.right = new TreeNode(-10);
        root.right.left = new TreeNode(-2);
        root.right.right = new TreeNode(-3);

        System.out.println(checkEqualTree(root));

    }

    static TreeNode rootNode = null;

    public static boolean checkEqualTree(TreeNode root) {
        rootNode = root;
        Set<Long> existingSum = new HashSet<>();
        long total = getSum(root, existingSum);
        if (total % 2 != 0) return false; // Odd sum can't be divided into half
        return existingSum.contains(total / 2);
    }

    public static long getSum(TreeNode root, Set<Long> set) {
        if (root == null) {
            return 0;
        }

        long result = root.val + getSum(root.left, set) + getSum(root.right, set);
        if (rootNode != root) {
            /**
             *
             *              5
             *             / \
             *           10  -10
             *                / \
             *              -2  -3
             *
             * In this case the summ(root) will come to 0 and hence if i try to check set.contains(0/2) will be true
             * but in this case we didn't partition tree and just took the whole tree
             */
            set.add(result); // This check is added to ensure that we are not considering the entire tree as the sum
        }
        return result;
    }
}
