package com.company.amazon;

import com.company.amazon.BinaryTree.Node;

import static com.company.amazon.BinaryTree.isLeafNode;

public class PrintAllNodesThatDontHaveSibling {

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(2);
        root.left.right = new Node(4);
        root.right = new Node(3);
        root.right.left = new Node(5);
        root.right.left.left = new Node(6);

        printNodes(root);
    }

    public static boolean isLeafNode(Node root) {
        return root.left == null && root.right == null;
    }

    public static void printNodes(Node root) {
        if (root == null) {
            return;
        }
        if (!isLeafNode(root)) {
            int nodeChild = ifNodeHasOneChild(root);
            if (nodeChild != 0) {
                if (nodeChild == 1) {
                    System.out.println(root.right.data);
                } else {
                    System.out.println(root.left.data);
                }
            }
        }
        printNodes(root.left);
        printNodes(root.right);
    }

    public static int ifNodeHasOneChild(Node node) {
        if (node.left != null && node.right != null) {
            return 0;
        } else if (node.left != null && node.right == null) {
            return -1;
        } else {
            return 1;
        }
    }
}
