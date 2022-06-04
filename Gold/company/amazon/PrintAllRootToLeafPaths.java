package com.company.amazon;

import java.util.Stack;

import static com.company.amazon.BinaryTree.*;

public class PrintAllRootToLeafPaths {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        BinaryTree tree = new BinaryTree();
        BinaryTree.Node node = new BinaryTree.Node(10);
        node.left = new BinaryTree.Node(8);
        node.right = new BinaryTree.Node(2);
        node.right.left = new BinaryTree.Node(18);
        node.left.left = new BinaryTree.Node(3);
        node.left.right = new BinaryTree.Node(5);
        tree.root = node;

//        inorder(tree.root);
        System.out.println("--------------------------Print All Path With Stack--------------------------");
        printAllPath(tree.root, stack);
        System.out.println("----------------------Print All Path Without Stack--------------------------");
        printAllPathWithoutStack(tree.root, new int[100], -1);

    }

    public static void inorder(Node root) {
        if (root == null)
            return;
        inorder(root.left);
        System.out.println(root.data);
        inorder(root.right);
    }

    public static void printAllPathWithoutStack(Node root, int[] path, int elements) {
        if (root == null) {
            return;
        }

        if (isLeafNode(root)) {
            path[++elements] = root.data;
            printPath(path, elements);
            elements--;
            return;
        }
        path[++elements] = root.data;
        printAllPathWithoutStack(root.left, path, elements);
        printAllPathWithoutStack(root.right, path, elements);
    }

    private static void printPath(int[] path, int elements) {
        for (int i = 0; i <= elements; i++) {
            System.out.print(path[i] + ",");
        }
        System.out.println();
    }

    public static void printAllPath(BinaryTree.Node root, Stack<Integer> stack) {
        if (root == null)
            return;

        // Push The Element
        stack.push(root.data);

        // Traverse it's left
        printAllPath(root.left, stack);
        if (BinaryTree.isLeafNode(root)) {
            System.out.println(stack);
            stack.pop();
            return;
        }
        // Traverse it's right
        printAllPath(root.right, stack);

        // Once done remove it from the stack
        stack.pop();
    }
}
