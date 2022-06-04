package com.datastructures.tree.binaryTree;

/**
 * Created by jaine03 on 05/07/17.
 */
public class BinaryTreeUtilUser {

    public static void main(String[] args)throws Exception {

        BinaryTreeUtil util = new BinaryTreeUtil();
        BinaryTreeUtil.Node tree = util.getSampleData();

//        util.printAllPathFromRootToLeaf(tree, new int[5],-1);
        util.printVerticalOrder(tree);


        util.printBottomView(tree);
            System.exit(0);

        Boolean doTraversal = false;

        if(doTraversal) {
            System.out.print("Inorder----------->");
            util.inOrder(tree);
            System.out.println();
            System.out.print("PreOrder---------->");
            util.preOrder(tree);
            System.out.println();
            System.out.print("PostOrder--------->");
            util.postOrder(tree);
            System.out.println();
        }

        System.out.println("Height of Tree "+util.getTreeHeight(tree));

        //util.levelOrderTraversal(tree);

        //util.levelOrderTraversalWithQueue(tree);

        //util.levelOrderTraversalWithQueueInSeparateLine(tree);

        //util.iterativeInorder(tree);

        util.morrisTraversal(tree);
//
//        System.out.println("Size of tree is "+ util.getTreeSize(tree));

       // util.inOrder(tree);

//        System.out.println();
//        tree = util.Mirror(tree);
//
        //util.inOrder(tree);

//        util.printAllPaths(tree, new int[5],-1);

        //util.convertBinaryTreeIntoCircularDoublyLinkedList(tree);
//
//        util.inplaceBinaryTreeToCircularDLL(tree);
//
//        BinaryTreeUtil.head.left = BinaryTreeUtil.prev;
//        BinaryTreeUtil.prev.right = BinaryTreeUtil.head;
//
//
//        util.traverseInorder();

        //util.addAllGreaterValuesToAllNode(tree);

        //util.inOrder(tree);

        util.levelOrderTraversal(tree);

        //util.childrenSumProperty(tree);
        //System.out.println(BinaryTreeUtil.isChildrenSumProperty);

//        System.out.println();
//        System.out.println(util.getDiameterOfTree(tree));
//
//        System.out.println("Is Tree Height Balanced :"+util.isTreeHeightBalanced(tree));
//
//        System.out.println(" Print Ancestor \n"+util.printAncestor(tree,5));
//
//
//        int [] inorder = new int[]{4,2,5,1,3};
//        int [] preOrder = new int[]{1,2,4,5,3};
//
//        tree = util.buildBinaryTreeFromInorderAndPreorder(preOrder,inorder,0,4);
//
//        util.inOrder(tree);



        System.out.println(util.lowestCommonAncestor(tree,9,10).data);


//        util.inOrder(tree);
//        System.out.println();
//        System.out.println("After Deleting 4");
//        tree = util.deleteFromBST(tree,4);
//        util.inOrder(tree);
//        System.out.println();
//        System.out.println("After Deleting 2");
//        tree = util.deleteFromBST(tree,2);
        util.inOrder(tree);
        System.out.println();
        System.out.println("After Deleting 1");
        tree = util.deleteFromBST(tree,1);
        util.inOrder(tree);



    }
}
