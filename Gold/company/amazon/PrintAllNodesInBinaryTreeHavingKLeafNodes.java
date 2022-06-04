package com.company.amazon;

import com.company.amazon.BinaryTree.Node;

public class PrintAllNodesInBinaryTreeHavingKLeafNodes {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(3);
        root.left.left = new Node(5);
        root.left.right = new Node(9);
        root.right = new Node(6);
        root.right.left = new Node(8);

        printNodes(root, 2);
        printNodes(root, 1);
    }

    /**
     * Do A Post order traversal and get the leaf count from left and right subtree based on which we have to decide
     * whether we should print the root (i.e. parent) or not.
     *
     * @param root
     * @param k
     * @return Return the leaf count
     */
    public static int printNodes(Node root, int k) {
        if (root == null)
            return 0;

        if (root.left == null && root.right == null) {
            return 1;
        }

        int leftLeafCount = printNodes(root.left, k);
        int rightLeafCount = printNodes(root.right, k);

        if (leftLeafCount + rightLeafCount == k) {
            System.out.println(root.data);
        }
        return leftLeafCount + rightLeafCount;
    }
}
