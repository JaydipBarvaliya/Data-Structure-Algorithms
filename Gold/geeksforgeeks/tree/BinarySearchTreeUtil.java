package com.geeksforgeeks.tree;

import java.util.*;

public class BinarySearchTreeUtil {

    public Node root;

    static class Node {
        int data;
        Node left;
        Node right;

        Node() {
        }

        Node(int data) {
            this.data = data;
        }
    }

    public BinarySearchTreeUtil() {
        root = null;
    }

    public static void main(String[] args) {
        BinarySearchTreeUtil util = new BinarySearchTreeUtil();

        letsDo("Insert the data");
        util.insert(100);
        util.insert(20);
        util.insert(10);
        util.insert(500);
        util.insert(30);
        newLine();
        letsDo("Inorder Traversal");
        util.inorderTraversal(util.root);
        newLine();

        letsDo("Insert the data 40");
        util.insert(40);
        util.inorderTraversal(util.root);
        newLine();

        letsDo("Insert data using recursion");
        util = new BinarySearchTreeUtil();
        util.insertRecursiveUtil(100);
        util.insertRecursiveUtil(20);
        util.insertRecursiveUtil(10);
        util.insertRecursiveUtil(500);
        util.insertRecursiveUtil(30);

        util.inorderTraversal(util.root);
        newLine();

        letsDo("Delete Node from BST");
        util = new BinarySearchTreeUtil();

        util.insert(5);
        util.insert(2);
        util.insert(1);
        util.insert(3);
        util.insert(12);
        util.insert(9);
        util.insert(21);
        util.insert(19);
        util.insert(25);
        newLine();
        letsDo("Before deleting");
        util.inorderTraversal(util.root);
        newLine();
        letsDo("After deleting 3");
        util.deleteNode(3);
        util.inorderTraversal(util.root);
        newLine();
        letsDo("After deleting 12");
        util.deleteNode(12);
        util.inorderTraversal(util.root);
        newLine();

        letsDo("Construct BST from PreOrder traversal");
        util.constructBSTFromPreOrder(new int[]{10, 5, 1, 7, 40, 50});
        newLine();

        letsDo("Construct BST from PreOrder Traversal Using Stack in O(N) time complexity");
        util.constructBSTFromPreOrderUsingStack(new int[]{10, 5, 1, 7, 40, 50});
        newLine();

        letsDo("Find Inorder Successor");
        util = new BinarySearchTreeUtil();
        util.insert(20);
        util.insert(8);
        util.insert(22);
        util.insert(4);
        util.insert(12);
        util.insert(10);
        util.insert(14);

        System.out.println(util.findInorderSuccessor(util.root, util.root.left.left));

        letsDo("Convert Binary Tree to Binary Search Tree");
        util = new BinarySearchTreeUtil();
        util.root = new Node(10);
        util.root.right = new Node(7);
        util.root.left = new Node(2);
        util.root.left.right = new Node(4);
        util.root.left.left = new Node(8);

        util.convertBinaryTreeToBinarySearchTree(util);

    }

    public void insertRecursiveUtil(int key) {
        root = insertUsingRecursion(root, key);
    }

    public void insert(int i) {
        if (root == null) {
            root = new Node(i);
            return;
        }
        Node curr = root;
        Node prev = null;

        while (curr != null) {
            prev = curr;

            if (curr.data < i)
                curr = curr.right;
            else if (curr.data > i)
                curr = curr.left;
        }

        if (prev.data > i)
            prev.left = new Node(i);
        else
            prev.right = new Node(i);
    }

    public Node insertUsingRecursion(Node root, int i) {
        if (root == null) {
            root = new Node(i);
            return root;
        }
        if (root.data > i)
            root.left = insertUsingRecursion(root.left, i);
        else if (root.data < i)
            root.right = insertUsingRecursion(root.right, i);

        return root;
    }

    public void inorderTraversal(Node root) {
        if (root == null)
            return;
        inorderTraversal(root.left);
        System.out.print(root.data + ",");
        inorderTraversal(root.right);
    }

    public static void letsDo(String task) {
        System.out.println("==================" + task + "======================");
    }

    public static void newLine() {
        System.out.println();
    }


    public void deleteNode(int value) {
        root = delete(root, value);
    }

    public Node delete(Node root, int value) {
        if (root == null)
            return root;

        if (root.data > value) {
            root.left = delete(root.left, value);
        } else if (root.data < value) {
            root.right = delete(root.right, value);
        } else { // Found out that this is the node to be deleted

            // Nodes with only 1 child or no child
            if (root.left == null)
                return root.right;
            if (root.right == null)
                return root.left;

            // This means the node to be delete has more than 1 child
            // So find inorder successor, replce it with current node and delete the inorder successor
            root.data = findInorderSuccessor(root);

            root.right = delete(root.right, root.data);
        }

        return root;
    }

    public int findInorderSuccessor(Node root) {
        root = root.right;

        while (root.left != null)
            root = root.left;
        return root.data;
    }

    public void constructBSTFromPreOrder(int[] preorder) {
        root = constructBST(preorder);

        inorderTraversal(root);
    }

    public Node constructBST(int[] pre) {
        if (pre.length < 1)
            return null;

        Node root = new Node(pre[0]);
        int indexOfNextHigherNode = 0;

        for (; indexOfNextHigherNode < pre.length; indexOfNextHigherNode++) {
            if (root.data < pre[indexOfNextHigherNode]) {
                break;
            }
        }

        root.left = constructBST(Arrays.copyOfRange(pre, 1, indexOfNextHigherNode));
        root.right = constructBST(Arrays.copyOfRange(pre, indexOfNextHigherNode, pre.length));
        return root;
    }


    private void constructBSTFromPreOrderUsingStack(int[] preOrder) {
        root = constructBSTFromPreOrderUsingStackUtil(preOrder);
        inorderTraversal(root);
    }

    private Node constructBSTFromPreOrderUsingStackUtil(int[] preOrder) {
        Stack<Node> stack = new Stack<>();

        Node root = new Node(preOrder[0]);
        stack.push(root);

        for (int i = 1; i < preOrder.length; i++) {
            Node temp = null;

            while (!stack.isEmpty() && preOrder[i] > stack.peek().data) {
                temp = stack.pop();
            }

            if (temp != null) { // means there is a node which is smaller than preOrder[i], So make preOrder[i] as the right child
                temp.right = new Node(preOrder[i]);
                stack.push(temp.right); // Also now add this to the stack
            } else { // means there is no node which is smaller than preOrder[i], So make preOrder[i] as the left child
                temp = stack.peek();
                temp.left = new Node(preOrder[i]);
                stack.push(temp.left);
            }
        }
        return root;
    }


    public int findInorderSuccessor(Node root, Node node) {
        if (root == null)
            return -1;

        if (node.right != null) {
            return mininumValueNodeInBST(root.right);
        }

        Node successor = null;
        Node temp = root;
        while (temp != null) {
            if (temp.data > node.data) { // Node is Present in Left Side of the tree
                successor = temp;
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        return successor != null ? successor.data : -1;
    }

    public int mininumValueNodeInBST(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.data;
    }

    /**
     * 1) Store Inorder Traversal of Original Tree
     * 2) Sort this stored inorder traversal
     * 3) Convert binaryTreeToBST
     * *   * a) convertBToBST(root.left);
     * *   * b) root.data = sortedTraversedInorder[index++]; // here index is the static integer.
     * *   * c) convertBToBST(root.right);
     * *   * 4) Print the BST
     *
     * @param root
     */
    public static void convertBinaryTreeToBinarySearchTree(BinarySearchTreeUtil util) {
        Node root = util.root;
        List<Integer> inorderList = new ArrayList<>();
        storeInorder(root, inorderList);

        // Sort the Traversed Inorder
        Collections.sort(inorderList);

        //Before Converting
        letsDo("Inorder Before Converting");
        util.inorderTraversal(root);
        System.out.println();

        // Let's convert
        binaryTreeToBST(root, inorderList);

        // After Converting
        letsDo("Inorder after Converting");
        util.inorderTraversal(root);
    }

    private static void storeInorder(Node root, List<Integer> inorderList) {
        if (root == null)
            return;
        storeInorder(root.left, inorderList);
        inorderList.add(root.data);
        storeInorder(root.right, inorderList);
    }

    private static Integer index = 0;

    public static void binaryTreeToBST(Node root, List<Integer> inorderList) {
        if (root == null)
            return;
        binaryTreeToBST(root.left, inorderList);
        root.data = inorderList.get(index++);
        binaryTreeToBST(root.right, inorderList);
    }

}
