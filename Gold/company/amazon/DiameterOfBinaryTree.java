package com.company.amazon;

import java.util.Stack;

import static com.company.amazon.BinaryTree.*;

public class DiameterOfBinaryTree {

    private static class Path {
        Stack<Integer> stack = new Stack<>();

        public Stack<Integer> getStack() {
            return this.stack;
        }

        public void setStack(Stack<Integer> stack) {
            this.stack = stack;
        }
    }

    private static class Height {
        int height;
    }


    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        System.out.println("Get Diameter :: " + getDiameter(root));
        System.out.println("Get Diameter Optimized ::" + getDiameterOptimized(root, new Height()));
        System.out.print("getDiameterOptimizedWithStaticVariable :: ");
        getDiameterOptimizedWithStaticVariable(root);
        System.out.println(MAX_DIAMETER);
    }

    public static int getDiameter(Node root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        int heightIncludingRoot = leftHeight + rightHeight + 1;
        return Math.max(heightIncludingRoot, Math.max(getDiameter(root.left), getDiameter(root.right)));
    }

    public static int getHeight(Node root) {
        if (root == null)
            return 0;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    public static int getDiameterOptimized(Node root, Height height) {
        Height leftHeight = new Height();
        Height rightHeight = new Height();

        if (root == null)
            return 0;

        int leftDiameter = getDiameterOptimized(root.left, leftHeight);
        int rightDiameter = getDiameterOptimized(root.right, rightHeight);

        int heightOfCurrentNode = Math.max(leftHeight.height, rightHeight.height) + 1;
        height.height = heightOfCurrentNode;

        return Math.max((leftHeight.height + rightHeight.height + 1), Math.max(leftDiameter, rightDiameter));
    }

    private static int MAX_DIAMETER = 0;

    public static int getDiameterOptimizedWithStaticVariable(Node root) {
        if (root == null)
            return 0;

        int leftDiameter = getDiameterOptimizedWithStaticVariable(root.left);
        int rightDiameter = getDiameterOptimizedWithStaticVariable(root.right);

        MAX_DIAMETER = Math.max((leftDiameter + rightDiameter + 1), MAX_DIAMETER); // Plus 1 because we are including the root
        return Math.max(leftDiameter, rightDiameter) + 1;
    }
}
