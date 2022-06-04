package com.company.amazon;

public class BinaryTree {
    public Node root;

    static class Node {
        int data;
        Node left;
        Node right;
        int prevData;

        Node() {

        }

        Node(int data) {
            this.data = data;
        }
    }

    public static void inorder(Node root) {
        if (root == null)
            return;
        inorder(root.left);
        System.out.print(root.data + ",");
        inorder(root.right);
    }

    public static void inorder(Node root, Boolean printLogs) {
        if (printLogs) {
            System.out.println("=============Inorder =============");
        }
        inorder(root);
        if (printLogs) {
            System.out.println("\n=============Inorder END=============");
        }
    }

    public static boolean isLeafNode(Node root) {
        return root.left == null && root.right == null;
    }

    public static boolean hasBothChild(Node root) {
        return root.left != null && root.right != null;
    }

    public static boolean hasRightChild(Node root) {
        return root.right != null;
    }

    public static boolean hasLeftChild(Node root) {
        return root.left != null;
    }
}
