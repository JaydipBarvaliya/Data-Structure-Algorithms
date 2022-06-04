package com.leetcode.year_2020.DP.dynamic_programming_on_trees;

import com.leetcode.year_2020.TreeNode;

/**
 * https://leetcode.com/problems/diameter-of-binary-tree/
 * @author neeraj on 13/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class DiameterOfBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println(diameterOfBinaryTree(root));
    }

    static int DIAMETER;

    public static int diameterOfBinaryTree(TreeNode root) {
        DIAMETER = Integer.MIN_VALUE;
        fetchDiameter(root);

        // Why -1? Since question is interested in number of edges not the number of nodes.
        // which is definitely 1 less than number of Nodes.
        return DIAMETER == Integer.MIN_VALUE ? 0 : DIAMETER - 1;
    }

    public static int fetchDiameter(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int nodesInLeft = fetchDiameter(root.left);
        int nodesInRight = fetchDiameter(root.right);

        // If this node is not the actual root.
        // then we have to send to root, what is the max
        // number of nodes i can calculate from here.
        int temp = 1 + Math.max(nodesInLeft, nodesInRight);

        // Now let's check if this "node" can actually contribute
        // to the diameter.
        int answer = Math.max(temp, 1 + nodesInLeft + nodesInRight);

        // We have to check this at every node
        // since max diameter can go through any node in the tree.
        DIAMETER = Math.max(DIAMETER, answer);

        return temp;
    }
}
