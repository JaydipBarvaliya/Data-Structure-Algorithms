package com.datastructures.mustDoInterviewQuestions.tree;

import com.datastructures.tree.binaryTree.BinaryTreeUtil;

/**
 * http://practice.geeksforgeeks.org/problems/left-view-of-binary-tree/1
 * Created by jaine03 on 28/07/17.
 */
public class LeftViewOfBinaryTree {

    public static void main(String[] args) {

        BinaryTreeUtil util = new BinaryTreeUtil();
        BinaryTreeUtil.Node tree = util.getBSTData();

        //util.printLeftView(tree);

        //util.levelOrderTraversal(tree);
        //util.inOrder(tree);

        System.out.println(util.isBST(tree));

        util.spiralTraversal(tree);
    }
}
