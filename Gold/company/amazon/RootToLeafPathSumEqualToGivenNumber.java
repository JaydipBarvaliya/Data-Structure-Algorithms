package com.company.amazon;

import com.company.amazon.BinaryTree.Node;

import java.util.Stack;

public class RootToLeafPathSumEqualToGivenNumber {

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(8);
        root.left.left = new Node(3);
        root.left.right = new Node(5);
        root.right = new Node(2);
        root.right.left = new Node(2);

        rootToLeafPathSum(root, new Stack<Integer>(), 0, 21);
        rootToLeafPathSum(root, new Stack<Integer>(), 0, 23);
        rootToLeafPathSum(root, new Stack<Integer>(), 0, 14);
    }

    public static void rootToLeafPathSum(Node root, Stack<Integer> stack, int currentPathSum, int totalSum) {
        if (root == null)
            return;
        stack.push(root.data);
        currentPathSum += root.data;

        if (BinaryTree.isLeafNode(root)) {
            if (currentPathSum == totalSum) {
                System.out.println(totalSum + "::" + stack);
            }
        }
        rootToLeafPathSum(root.left, stack, currentPathSum, totalSum);
        rootToLeafPathSum(root.right, stack, currentPathSum, totalSum);

        currentPathSum -= stack.peek();
        stack.pop();
    }
}
