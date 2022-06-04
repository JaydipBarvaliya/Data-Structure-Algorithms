package com.company.amazon;

import java.util.LinkedHashSet;
import java.util.Set;

import static com.company.amazon.BinaryTree.Node;

public class BoundryTraversalOfBinaryTree {

    private static Set<Integer> result = new LinkedHashSet<>();
    private static boolean firstElementTraversed = false;
    private static boolean shouldApplyFirstElementCheck = true;

    public static void main(String[] args) {
        BinaryTree.Node root = new Node();
        root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        printBoundry(root);

        // New Sample

        root = new Node(20);
        root.left = new Node(8);
        root.left.left = new Node(4);
        root.left.right = new Node(12);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(14);
        root.right = new Node(22);
        root.right.right = new Node(25);
        result = new LinkedHashSet<>();
        printBoundry(root);
    }

    /**
     * 1) Level Order Traversal (L-R) excluding the last level
     *
     * @param root
     */
    public static void printBoundry(Node root) {
        int levelOfTree = getHeight(root);

        // A) First Add the Root to the Result as it is the first Node
        result.add(root.data);

        // 1) Traverse all level and add only 1st element from left to right
        for (int i = 1; i < levelOfTree - 1; i++) {
            traverseLevel(root, i, 0, true);
            firstElementTraversed = false;
        }

        // 2) Traverse the last level
        shouldApplyFirstElementCheck = false;
        traverseLevel(root, levelOfTree - 1, 0, true);
        shouldApplyFirstElementCheck = true;

        // 3) Traverse all level and add only 1st element from Right to left starting from bottom
        firstElementTraversed = false;
        for (int i = levelOfTree - 2; i > 0; i--) {
            traverseLevel(root, i, 0, false);
            firstElementTraversed = false;
        }

        System.out.println(result);
    }

    public static void traverseLevel(Node root, int levelToTraverse, int currentLevel, boolean leftToRight) {
        if (root == null)
            return;
        if (levelToTraverse == currentLevel && (!firstElementTraversed || !shouldApplyFirstElementCheck)) {
            firstElementTraversed = true;
            result.add(root.data);
            return;
        }

        if (leftToRight) {
            traverseLevel(root.left, levelToTraverse, currentLevel + 1, leftToRight);
            traverseLevel(root.right, levelToTraverse, currentLevel + 1, leftToRight);
        } else {
            traverseLevel(root.right, levelToTraverse, currentLevel + 1, leftToRight);
            traverseLevel(root.left, levelToTraverse, currentLevel + 1, leftToRight);
        }
    }

    public static int getHeight(Node root) {
        if (root == null)
            return 0;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
}
