package com.company.amazon;

import static com.company.amazon.BinaryTree.*;

public class ExtractLeavesFromBinaryTreeToDoublyLinkedList {

    private static Node head;
    private static Node prev;
    private static Node root;

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.left.left = new Node(7);
        root.left.left.right = new Node(8);

        root.right.right = new Node(6);
        root.right.right.left = new Node(9);
        root.right.right.right = new Node(10);

        System.out.println("Before Extracting");
        inorder(root, true);

        root = extractLeaves(root);
        System.out.println("After Extracting");
        inorder(root, true);
    }

    private static Node extractLeaves(Node root) {
        if (root == null)
            return null;
        if (isLeafNode(root)) {
            if (head == null) {
                head = root;
                prev = root;
            } else {
                prev.right = root;
                root.left = prev;
                prev = root;
            }
            return null; // Why Null ? because we have to remove the leafNode from tree and add into only DLL
        }
        root.left = extractLeaves(root.left);
        root.right = extractLeaves(root.right);
        return root;
    }


}
