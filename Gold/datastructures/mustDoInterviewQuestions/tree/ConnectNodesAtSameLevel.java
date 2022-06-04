package com.datastructures.mustDoInterviewQuestions.tree;

import com.datastructures.tree.binaryTree.BinaryTreeUtil;

/**
 * Created by jaine03 on 28/07/17.
 */
public class ConnectNodesAtSameLevel {

    public static void main(String[] args) {
        BinaryTreeUtil util = new BinaryTreeUtil();
        util = util.getConnectNodesAtSameLevelSampleData();

        //util.connectNodesAtSameLevel(util.root);

        util.printAllPaths(util.root,new int[1000],-1);

        util.printAllPathFromRootToLeaf(util.root,new int[1000],-1);
    }
}
