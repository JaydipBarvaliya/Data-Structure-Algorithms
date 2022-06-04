package com.company.amazon;

import com.company.amazon.BinaryTree.Node;
import com.geeksforgeeks.tree.BinaryTreeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LowestCommonAncestor {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.right.right = new Node(7);
        root.left.right = new Node(5);
        root.right.left = new Node(6);

//        System.out.println(getLCARecursive(root, 5, 6).data);
//        System.out.println(getLCA(root, 4, 7).data);

        root = new Node(100);
        root.left = new Node(50);
        root.right = new Node(150);
        root.left.left = new Node(30);
        root.left.right = new Node(60);
        root.left.right.left = new Node(55);
        root.left.right.right = new Node(70);
        root.right.left = new Node(120);
        root.right.right = new Node(200);
        System.out.println(LCAOfBinarySearchTreeIteratively(root, 55, 70).data);

    }


    public static Node getLCARecursive(Node root, int n1, int n2) {
        if (root == null) {
            return null;
        }

        // if node matches with any of n1 or n2 we return that node
        if (root.data == n1 || root.data == n2) {
            return root;
        }

        Node leftLCA = getLCARecursive(root.left, n1, n2);
        Node rightLCA = getLCARecursive(root.right, n1, n2);

        if (leftLCA != null && rightLCA != null) { // If both nodes found in left and right respectively, then root is LCA
            return root;
        }
        return leftLCA != null ? leftLCA : rightLCA;
    }

    private static Stack<Integer> path1 = new Stack<>();
    private static Stack<Integer> path2 = new Stack<>();

    public static Node getLCA(Node root, int n1, int n2) {
        List<Integer> pathToNodeN1 = new ArrayList<>();
        List<Integer> pathToNodeN2 = new ArrayList<>();

        storeAndPrintPathToNode(root, n1, pathToNodeN1);
        storeAndPrintPathToNode(root, n2, pathToNodeN2);

        System.out.println(pathToNodeN1);
        System.out.println(pathToNodeN2);
        int l1 = 0;
        int l2 = 0;

        while (l1 < pathToNodeN1.size() && l2 < pathToNodeN2.size()) {
            if (pathToNodeN1.get(l1) != pathToNodeN2.get(l2)) {
                l1--;
                l2--;
                break;
            }
            l1++;
            l2++;
        }
        return new Node(pathToNodeN1.get(l1));
    }

    public static Node getLCAOfBinarySearchTree(Node root, int n1, int n2) {
        if (root == null) {
            return null;
        }
        if (root.data > n1 && root.data > n2)
            return getLCAOfBinarySearchTree(root.left, n1, n2);
        if (root.data < n1 && root.data < n2)
            return getLCAOfBinarySearchTree(root.right, n1, n2);
        return root;
    }

    public static boolean storeAndPrintPathToNode(Node root, int toNode, List<Integer> list) {
        if (root == null) {
            return false;
        }
        list.add(root.data);

        if (root.data == toNode) {
            return true;
        }
        if (root.left != null && storeAndPrintPathToNode(root.left, toNode, list)) {
            return true;
        }
        if (root.right != null && storeAndPrintPathToNode(root.right, toNode, list)) {
            return true;
        }

        list.remove(list.size() - 1); // If this particular node is not contributing in the path at all remove it.
        return false;
    }

    public static Node LCAOfBinarySearchTreeIteratively(Node root, Integer n1, Integer n2) {
        while (root != null) {
            if (root.data > Math.max(n1, n2)) {
                root = root.left;
            } else if (root.data < Math.min(n1, n2)) {
                root = root.right;
            } else
                break;
        }
        return root;
    }
}
