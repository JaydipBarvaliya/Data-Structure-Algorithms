package com.leetcode.year_2020;

import static com.leetcode.year_2020.tree.ConstructBinaryTreeUsingPreOrderAndInorderTraversal.buildTreeFromPreOrderAndInorder;

/**
 * @author neeraj on 26/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaximumPathSumInBinaryTree {

    public static void main(String[] args) {
        maxPathSumUtil(new int[]{-10, 9, 20, 15, 7}, new int[]{9, -10, 15, 20, 7});
        maxPathSumUtil(new int[]{1, 2, 3}, new int[]{2, 1, 3});
    }

    /**
     * Util function which will build the tree from preorder and inorer
     * and in-turn invoke maxPathSum
     */
    public static void maxPathSumUtil(int[] preorder, int[] inorder) {
        TreeNode root = buildTreeFromPreOrderAndInorder(preorder, inorder);
        System.out.println(maxPathSum(root));
    }

    static int MAX_PATH_SUM = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        MAX_PATH_SUM = Integer.MIN_VALUE;
        getMaxPathSumBetweenAnyTwoNodes(root);
        return MAX_PATH_SUM;
    }

    public static int getMaxPathSumBetweenAnyTwoNodes(TreeNode root) {
        /**
         * Okay so this problem is different from finding maxPathSumBetween any 2 leafs.
         * Since in that we have a mandate to take 2 leafs.
         * But in this we can take difference between any two nodes.
         *
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
         *
         *  Now at each subtree we can't just consider that alone, we need to account that there might
         *  be another subtree in addition to which the sub-path(node + left/right) of this subtree can produce
         *  a bigger Max SubPath Sum.
         */
        if (root == null) return 0; // For Empty tree we can get a maxPathSum of 0;

        int leftPathSum = getMaxPathSumBetweenAnyTwoNodes(root.left);
        int rightPathSum = getMaxPathSumBetweenAnyTwoNodes(root.right);


        // Here for each subtree we are keeping track of what max this subtree can produce.
        MAX_PATH_SUM = Math.max(MAX_PATH_SUM, root.val);
        MAX_PATH_SUM = Math.max(MAX_PATH_SUM, root.val + leftPathSum);
        MAX_PATH_SUM = Math.max(MAX_PATH_SUM, root.val + rightPathSum);
        MAX_PATH_SUM = Math.max(MAX_PATH_SUM, root.val + leftPathSum + rightPathSum);


        // Now since we have to move to other part of tree as well
        // So with this subtree we just need to get maxSubPath
        // which can be if we take this subPath with Root OR just take the root.
        return Math.max(root.val, root.val + Math.max(leftPathSum, rightPathSum));
    }
}
