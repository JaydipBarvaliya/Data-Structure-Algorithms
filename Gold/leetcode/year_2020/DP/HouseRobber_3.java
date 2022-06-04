package com.leetcode.year_2020.DP;

import com.leetcode.problems.medium.TreeNode;

public class HouseRobber_3 {

    static class Result {
        int sum = 0;
        int childSum = 0;

        public Result(int sum) {
            this.sum = sum;
        }

        public Result(int sum, int childSum) {
            this.sum = sum;
            this.childSum = childSum;
        }
    }

    public static int rob(TreeNode root) {
        Result result = helper(root);
        return result.sum;
    }

    public static Result helper(TreeNode root) {
        if (root == null) return new Result(0);
        if (root.left == null && root.right == null) return new Result(root.val);
        Result left = helper(root.left);
        Result right = helper(root.right);
        int maxSum = Math.max((root.val + left.childSum + right.childSum), (left.sum + right.sum));
        return new Result(maxSum, left.sum + right.sum);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);

        System.out.println(rob(root));
    }
}
