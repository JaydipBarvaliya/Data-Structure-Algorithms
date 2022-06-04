package com.company.amazon;

public class IsBinaryTreeASumTree {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        BinaryTree.Node node = new BinaryTree.Node(26);

        node.left = new BinaryTree.Node(11);
        node.right = new BinaryTree.Node(3);
        node.right.right = new BinaryTree.Node(3);

        node.left.left = new BinaryTree.Node(4);
        node.left.right = new BinaryTree.Node(6);

        tree.root = node;

        System.out.println("Is Sum Tree ?" + isSumTree(tree.root));
    }

    private static boolean isSumTree(BinaryTree.Node root) {
        if (root == null || (root.left == null && root.right == null))
            return true;

        sum = 0;
        int leftHeight = getHeight(root.left);
        sum = 0;
        int rightHeight = getHeight(root.right);

        return (root.data == leftHeight + rightHeight) && isSumTree(root.left) && isSumTree(root.right);
    }

    static int sum = 0;

    private static int getHeight(BinaryTree.Node root) {
        if (root == null)
            return sum;

        getHeight(root.left);
        sum += root.data;
        getHeight(root.right);
        return sum;
    }
}
