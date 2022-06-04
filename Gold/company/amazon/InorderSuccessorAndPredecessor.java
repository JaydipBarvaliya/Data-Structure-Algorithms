package com.company.amazon;

import com.company.amazon.BinaryTree.Node;

public class InorderSuccessorAndPredecessor {
    public static void main(String[] args) {
        Node root = new Node(15);

        // Left Subtree
        root.left = new Node(6);
        root.left.left = new Node(3);
        root.left.left.left = new Node(2);
        root.left.left.right = new Node(4);

        // Left subtree right part
        root.left.right = new Node(7);
        root.left.right.right = new Node(13);
        root.left.right.right.left = new Node(9);


        // Right Subtree
        root.right = new Node(18);
        root.right.left = new Node(17);
        root.right.right = new Node(20);

        BinaryTree.inorder(root);
        System.out.println();
        inorderPredecessorUtil(root, root);

        System.out.println("=============Inorder Successor==============");
        inorderSuccessorUtil(root, root);

    }

    public static void inorderPredecessorUtil(Node root, Node mainRoot) {
        if (root == null) {
            return;
        }
        inorderPredecessorUtil(root.left, mainRoot);
        printInorderPredecessor(mainRoot, root.data);
        inorderPredecessorUtil(root.right, mainRoot);
    }

    public static void inorderSuccessorUtil(Node root, Node mainRoot) {
        if (root == null) {
            return;
        }
        inorderSuccessorUtil(root.left, mainRoot);
        printInorderSuccessor(mainRoot, root.data);
        inorderSuccessorUtil(root.right, mainRoot);
    }

    public static void printInorderPredecessor(Node root, int ofNode) {
        Node temp = root;
        Integer predecessor = -1;
        while (temp != null && temp.data != ofNode) {
            if (temp.data < ofNode) {
                predecessor = temp.data;
                temp = temp.right;
            } else {
                temp = temp.left;
            }
        }

        if (temp != null && temp.data == ofNode) {
            if (temp.left != null) {
                temp = temp.left;
                while (temp.right != null) {
                    temp = temp.right;
                }
                System.out.println("Predecessor of " + ofNode + " is ==> " + temp.data);
            } else { // When you don't have left child
                System.out.println("Predecessor of " + ofNode + " is ==> " + predecessor);
            }
        } else {
            System.out.println(ofNode + " is invalid");
        }
    }

    public static void printInorderSuccessor(Node root, int ofNode) {
        Node temp = root;
        Integer successor = -1;
        while (temp != null && temp.data != ofNode) {
            if (temp.data > ofNode) {
                successor = temp.data;
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }

        if (temp != null && temp.data == ofNode) {
            if (temp.right != null) {
                temp = temp.right;
                while (temp.left != null) {
                    temp = temp.left;
                }
                System.out.println("Successor of " + ofNode + " is ==> " + temp.data);
            } else { // When you don't have left child
                System.out.println("Successor of " + ofNode + " is ==> " + successor);
            }
        } else {
            System.out.println(ofNode + " is invalid");
        }
    }
}
