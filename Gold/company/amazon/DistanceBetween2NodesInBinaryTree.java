package com.company.amazon;

import com.company.amazon.BinaryTree.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DistanceBetween2NodesInBinaryTree {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.right = new Node(8);


        printPath(root, 2);
        printPath(root, 3);
        printPath(root, 4);
        printPath(root, 5);
        printPath(root, 6);
        printPath(root, 7);
        printPath(root, 8);

        distanceBetweenTwoNodes(root, 4, 5);

        distanceBetweenTwoNodesUsingLevel(root, 4, 5);
        distanceBetweenTwoNodesUsingLevel(root, 4, 8);
    }

    public static void distanceBetweenTwoNodesUsingLevel(Node root, int first, int second) {

        Node lcaOfBothNodes = LCA(root, first, second);

        int level1 = getLevelOfNode(lcaOfBothNodes, first, 0);
        int level2 = getLevelOfNode(lcaOfBothNodes, second, 0);

        if (level1 != -1 && level2 != -1)
            System.out.println("Distance between " + first + " and " + second + " is " + (level1 + level2));
        else
            System.out.println("Nodes doesn't exist");
    }

    public static int getLevelOfNode(Node root, int node, int level) {
        if (root == null)
            return -1;
        if (root.data == node)
            return level;

        int leftLevel = getLevelOfNode(root.left, node, level + 1);

        if (leftLevel == -1) // If Node is not in left subtree
            return getLevelOfNode(root.right, node, level + 1);

        return leftLevel;
    }


    /**
     * (Distance from Root to Node 1 + Distance from Root to Node 2) - 2 * (Distance from Root to LCA(Node1, Node2))
     *
     * @param first
     * @param second
     */
    public static void distanceBetweenTwoNodes(Node root, int first, int second) {
        List<Integer> result1 = new ArrayList<>();
        List<Integer> result2 = new ArrayList<>();

        // Once we have both the paths
        if (printPathFromRootToNode(root, first, new int[100], -1, result1) &&
                printPathFromRootToNode(root, second, new int[100], -1, result2)) {

            int distance1 = result1.size();
            int distance2 = result2.size();

            Node LCA = LCA(root, first, second);

            List<Integer> result3 = new ArrayList<>();
            printPathFromRootToNode(root, LCA.data, new int[100], -1, result3);

            int lcaDistance = result3.size();

            System.out.println("Distance between " + first + " and " + second + " is " + ((distance1 + distance2) - 2 * (lcaDistance)));
        } else {
            System.out.println("Nodes doesn't exist");
        }
    }

    public static void printPath(Node root, int toNode) {
        System.out.print("Path from " + root.data + "  to " + toNode + " ===> ");
        List<Integer> result = new ArrayList<>();
        if (printPathFromRootToNode(root, toNode, new int[100], -1, result)) {
            System.out.println(result);
        } else {
            System.out.println("[ NODE DOESN'T EXIST ]");
        }
    }

    private static boolean printPathFromRootToNode(Node root, int toNode, int[] path, int pathIndex, List<Integer> result) {
        if (root == null)
            return false;
        if (root.data == toNode) {
            path[++pathIndex] = root.data;
            addToResult(path, pathIndex, result);
            return true;
        } else {
            path[++pathIndex] = root.data;
        }

        if (printPathFromRootToNode(root.left, toNode, path, pathIndex, result) ||
                printPathFromRootToNode(root.right, toNode, path, pathIndex, result)) {
            return true;
        }
        return false;
    }

    private static void addToResult(int[] path, int pathIndex, List<Integer> result) {
        for (int i = 0; i <= pathIndex; i++) {
            result.add(path[i]);
        }
    }

    private static Node LCA(Node root, int N1, int N2) {
        if (root == null)
            return root;
        if (root.data == N1 || root.data == N2) {
            return root;
        }

        Node leftLCA = LCA(root.left, N1, N2);
        Node rightLCA = LCA(root.right, N1, N2);

        if (leftLCA != null && rightLCA != null) {
            return root;
        }
        return leftLCA == null ? rightLCA : leftLCA;
    }
}
