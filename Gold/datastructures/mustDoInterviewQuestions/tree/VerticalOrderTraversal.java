package com.datastructures.mustDoInterviewQuestions.tree;

import com.datastructures.tree.binaryTree.BinaryTreeUtil;

/**
 * Created by jaine03 on 28/07/17.
 */
public class VerticalOrderTraversal {

    public static void main(String[] args) {

        BinaryTreeUtil util = new BinaryTreeUtil();
        util = util.getVerticalOrderSampleData();
        System.out.println("vertical order traversal is :");
        util.printVerticalOrder(util.root);

        util.verticalOrderTraversal(util.root);
    }
}
