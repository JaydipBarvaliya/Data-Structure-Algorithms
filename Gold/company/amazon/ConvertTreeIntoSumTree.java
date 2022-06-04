package com.company.amazon;

import static com.company.amazon.BinaryTree.*;

public class ConvertTreeIntoSumTree {

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(-2);
        root.right = new Node(6);
        root.left.left = new Node(8);
        root.left.right = new Node(-4);
        root.right.left = new Node(7);
        root.right.right = new Node(5);

        System.out.println("Before converting into SUM Tree");
        inorder(root, true);
        ConvertTreeIntoSumTree(root);
        System.out.println("After converting into SUM Tree");
        inorder(root, true);
    }

    public static void ConvertTreeIntoSumTree(Node root) {
        if (root == null)
            return;
        ConvertTreeIntoSumTree(root.left);
        ConvertTreeIntoSumTree(root.right);
        if (isLeafNode(root)) {
            root.prevData = root.data;
            root.data = 0;
        } else {
            root.prevData = root.data;
            root.data = hasLeftChild(root) ? root.left.prevData + root.left.data : 0;
            root.data += hasRightChild(root) ? root.right.prevData + root.right.data : 0;
        }
    }

    public static int convertTreeToSumTree(Node root) {
        if (root == null)
            return 0;

        int prevValue = root.data;
        root.data = convertTreeToSumTree(root.left) + convertTreeToSumTree(root.right);
        return root.data + prevValue;
    }
}
