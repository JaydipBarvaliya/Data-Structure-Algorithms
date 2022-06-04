package com.leetcode.tree;

import java.util.*;

import static com.util.LogUtil.*;

/**
 * @author neeraj on 2019-06-09
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("Duplicates")
public class BinaryTreeUtil {

    Node root;
    Node head;
    Node prev;

    class Node {
        Node left;
        Node right;
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * * Sample Tree
     * *             1
     * *          /    \
     * *        2       3
     * *      /  \     /  \
     * *     4   5    14  15
     * *        /  \      / \
     * *       8   10    19 20
     * *             \
     * *            12
     */
    public Node getSampleTree() {
        Node root = new Node(1);

        // 2 sub tree
        root.left = new Node(2);
        root.left.left = new Node(4);

        // 5 sub tree
        root.left.right = new Node(5);
        root.left.right.left = new Node(8);
        root.left.right.right = new Node(10);
        root.left.right.right.right = new Node(12);


        // 3 node subtree
        root.right = new Node(3);
        root.right.left = new Node(14);
        root.right.right = new Node(15);
        root.right.right.left = new Node(19);
        root.right.right.left.right = new Node(20);

        return root;
    }

    public Node getSampleTreeForLeafTRaversal_1() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        return root;
    }

    public Node getSampleTreeForLeafTRaversal_2() {
        Node root = new Node(0);
        root.left = new Node(5);
        root.right = new Node(8);
        root.left.left = new Node(4);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        return root;
    }

    public Node getSampleTreeForBoundaryOrderTraversal() {
        Node root = new Node(20);
        root.left = new Node(8);
        root.right = new Node(22);
        root.left.left = new Node(4);
        root.left.right = new Node(12);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(14);
        root.right.right = new Node(25);
        return root;
    }

    public Node getSampleTreeForMaximumSumPathBetween_Two_Leaf() {
        Node root = new Node(-15);
        root.left = new Node(5);
        root.left.right = new Node(1);
        root.left.left = new Node(-8);
        root.left.left.left = new Node(2);
        root.left.left.right = new Node(6);

        root.right = new Node(6);
        root.right.left = new Node(3);

        root.right.right = new Node(9);
        root.right.right.right = new Node(0);
        root.right.right.right.left = new Node(4);

        root.right.right.right.right = new Node(-1);
        root.right.right.right.left = new Node(10);
        return root;
    }

    public void getSizeOfTree() {
        Node curr = root;
        logIt("Size of Tree is " + getSize(curr), true);
    }

    private int getSize(Node curr) {
        if (curr == null) {
            return 0;
        }
        return getSize(curr.left) + 1 + getSize(curr.right);
    }

    public void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.println(root.data);
        inorder(root.right);
    }

    public void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data);
    }

    public void inorderWithoutRecursionUsingStack(Node root) {
        logIt("Inorder Traversal Without Recursion using Stack is ....");
        Stack<Node> stack = new Stack<>();
        Node poppedItem;

        // While we have elements in the left, lets push them into stack
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        int indentCounter = 0;
        // Now let's pop item fromm stack until stack is empty
        while (!stack.isEmpty()) {
            poppedItem = stack.pop();
            System.out.println(poppedItem.data);
            // Checking if the poppedItem has any right subtree, if yes then we have to
            // explore that as well, since In-order Traversal is all about Left, Parent and then Right.
            if (poppedItem.right != null) {
                poppedItem = poppedItem.right;

                while (poppedItem != null) {
                    stack.push(poppedItem);
                    poppedItem = poppedItem.left;
                }
            }
//            indentOutput(poppedItem.data, indentCounter++);
        }
    }

    public void inorderTraversalWithoutRecursionWithoutStack(Node current) {
        logIt("Inorder Traversal Without Recursion without using Stack is well [Simply using Morris Traversal]....");

        int indentCounter = 0;
        while (current != null) { // Repeat
            // If Left subtree is Not present, then simply print the element and move to right
            if (current.left == null) {
                indentOutput(current.data, indentCounter++);
                current = current.right;
            } else {
                // If left subtree is present, then make the current the right child or inorder predecessor
                // In other words, make current the right child of rightmost node in the left subtree
                Node prev = current.left;

                // Now when we find the right-most node we have to basically check for 2 things
                // 1) That we actually reached the rightmost node (prev.right == null)
                // 2) That we have reached a rightmost node but it's right child is current node itself
                // This can only happen when we have previously associated a thread to the current node
                while (prev.right != null && prev.right != current) {
                    prev = prev.right;
                }

                if (prev.right == null) {
                    prev.right = current; // Make current as right child
                    current = current.left; // Let's move to the left node to repeat the same process.
                } else { // prev.right == current
                    prev.right = null; // Correcting the tree.
                    indentOutput(current.data, indentCounter++);
                    current = current.right;
                }

            }
        }
    }

    public static void indentOutput(int data, int space) {
        for (int i = 0; i < space; i++) {
            System.out.print(" ");
        }
        System.out.println(data);
    }

    private static int preOrderIndexCounter = 0;

    public Node constructBinaryTreeUsingInorderAndPreOrderTraversal(int[] inOrder, int[] preOrder, int startIndex, int endIndex) {

        if (startIndex > endIndex) {
            return null;
        }
        int preOrderItem = preOrder[preOrderIndexCounter++];
        Node tempNode = new Node(preOrderItem);

        // This is Leaf Node
        if (startIndex == endIndex) {
            return tempNode;
        }

        int preOrderItemIndexInsideInorderTraversal = findItemInInorderTraversal(inOrder, preOrderItem, startIndex, endIndex);

        tempNode.left = constructBinaryTreeUsingInorderAndPreOrderTraversal(inOrder, preOrder, startIndex, preOrderItemIndexInsideInorderTraversal - 1);
        tempNode.right = constructBinaryTreeUsingInorderAndPreOrderTraversal(inOrder, preOrder, preOrderItemIndexInsideInorderTraversal + 1, endIndex);
        return tempNode;
    }

    public void printPostOrderTraversalFromInorderAndPreOrderTraversal(int[] inOrder, int[] preOrder, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return;
        }

        int preOrderItem = preOrder[preOrderIndexCounter++];
        int preOrderItemIndexInsideInorderTraversal = findItemInInorderTraversal(inOrder, preOrderItem, startIndex, endIndex);
        printPostOrderTraversalFromInorderAndPreOrderTraversal(inOrder, preOrder, startIndex, preOrderItemIndexInsideInorderTraversal - 1);
        printPostOrderTraversalFromInorderAndPreOrderTraversal(inOrder, preOrder, preOrderItemIndexInsideInorderTraversal + 1, endIndex);

        System.out.print(inOrder[preOrderItemIndexInsideInorderTraversal] + "\t");
    }

    public int getHeightOfTree(Node root) {
        if (root == null)
            return 0;
        return 1 + Math.max(getHeightOfTree(root.left), getHeightOfTree(root.right));
    }

    public void levelOrderTraversal(Node root) {
        System.out.println();
        logIt("Doing Level Order Traversal of the Tree", true);
        int noOfLevels = getHeightOfTree(root);

        for (int levelToTraverseOn = 0; levelToTraverseOn < noOfLevels; levelToTraverseOn++) {
            int currentLevel = 0;
            traverseThisLevel(root, levelToTraverseOn, currentLevel);
            System.out.println();
        }
    }

    private void traverseThisLevel(Node root, int levelToTraverseOn, int currentLevel) {
        if (root == null)
            return;
        if (levelToTraverseOn == currentLevel) {
            System.out.print(root.data + " , ");
            return;
        }
        traverseThisLevel(root.left, levelToTraverseOn, currentLevel + 1);
        traverseThisLevel(root.right, levelToTraverseOn, currentLevel + 1);
    }

    private int findItemInInorderTraversal(int[] inOrder, int preOrderItem, int startIndex, int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            if (inOrder[i] == preOrderItem) {
                return i;
            }
        }
        return -1;
    }

    public int findMaximumInBinaryTree(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(root.data, Math.max(findMaximumInBinaryTree(root.left), findMaximumInBinaryTree(root.right)));
    }

    public void levelOrderTraversalUsing2Queues(Node root) {
        logIt("Doing Level Order Traversal using 2 Queues");
        Queue<Node> queue1 = new LinkedList();
        Queue<Node> queue2 = new LinkedList();
        Node dequeuedItem;
        Queue<Node> tempQueue;

        queue1.add(root);

        while (!queue1.isEmpty()) {
            dequeuedItem = queue1.poll();

            if (dequeuedItem.left != null)
                queue2.add(dequeuedItem.left);
            if (dequeuedItem.right != null)
                queue2.add(dequeuedItem.right);

            System.out.print(dequeuedItem.data + " , ");
            if (queue1.isEmpty()) {
                System.out.println();
                tempQueue = queue1;
                queue1 = queue2;
                queue2 = tempQueue;
            }
        }
    }

    public void levelOrderTraversalInReverseOrderUsingStackAndTheQueue(Node root) {
        logIt("Doing Reverse -=--==> Level Order Traversal using 1 Queue and 1 Stack");
        Queue<Node> queue = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        Node dequeuedItem;

        queue.add(root);
        queue.add(null);

        while (!queue.isEmpty()) {
            dequeuedItem = queue.poll();
            stack.push(dequeuedItem);
            if (dequeuedItem == null) {
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
                continue;
            }
            if (dequeuedItem.right != null) {
                queue.add(dequeuedItem.right);
            }
            if (dequeuedItem.left != null) {
                queue.add(dequeuedItem.left);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == null) {
                System.out.println();
                stack.pop();
                continue;
            }
            System.out.print(stack.pop().data + " , ");
        }
    }

    public void iterativePreOrderTraversal(Node root) {
        logIt("\nDoing Iterative Pre Order Traversal of the Tree");
        Node poppedItem = null;
        Stack<Node> stack = new Stack();
        stack.push(root);
        int identationCounter = 0;
        while (!stack.isEmpty()) {
            poppedItem = stack.pop();
            indentOutput(poppedItem.data, identationCounter++);

            // Since we are using stack which follows LIFO, hence we need to push right element first and then left element.
            if (poppedItem.right != null) {
                stack.push(poppedItem.right);
            }
            if (poppedItem.left != null) {
                stack.push(poppedItem.left);
            }
        }
    }

    Stack<Integer> pathToNode = new Stack<>();

    public void printPathToNode(Node root, Integer pathToWhichNode) {
        if (root != null) {
            pathToNode.add(root.data);
            if (root.data == pathToWhichNode) {
                System.out.println(pathToNode);
                return;
            }
            printPathToNode(root.left, pathToWhichNode);
            printPathToNode(root.right, pathToWhichNode);

            // Remove this node from the path;
            pathToNode.pop();
        }
    }

    public void printAllPathToLeafNode(Node root) {
        if (root != null) {
            pathToNode.add(root.data);
            // Leaf Node check
            if (root.left == null && root.right == null) {
                System.out.println(pathToNode);
            }
            printAllPathToLeafNode(root.left);
            printAllPathToLeafNode(root.right);

            // Remove this node from the path;
            pathToNode.pop();
        }
    }

    /**
     * This traversal is totally based on the hunch
     * *            1
     * *          /   \
     * *        2     3
     * *      /  \
     * *     4   5
     * *
     * * If we have above tree then PreOrder Traversal is : [1, 2, 4, 5, 3]
     * * and Post Order [Left, Right, Parent] Traversal is : [4, 5, 2, 3, 1]
     * *
     * * Now let's reverse the Post Order for time-being ==> [1, 3, 2, 5, 4]
     * * and if we observe closely it looks somewhat similar to Pre-Order traversal but in [Parent -> Right -> Left] fashion
     * * instead of [Parent -> Left -> Right] fashion, So let's solve this problem with following steps.
     * <p>
     * 1) Do preOrder traversal in O(N) time using stack, in P->R->L fashion, since we are using Stack (LIFO)
     * so we have to actually store left node before right node, so when we pop the node we get right first and left next which is exactly what we need.
     * 2) Simultaneously store then in another stack so which will due to it's LIFO nature help us to avoid reversing the traversal and give us post-order.
     *
     * @param root
     */
    public void iterativePostOrderTraversal(Node root) {
        logIt("Doing Iterative Post Order Traversal", true);

        Stack<Node> stack_1 = new Stack<>();
        Stack<Integer> stack_2 = new Stack<>();
        Node dequedItem = null;

        stack_1.push(root);

        while (!stack_1.isEmpty()) {
            dequedItem = stack_1.pop();

            stack_2.push(dequedItem.data);

            // We actually need Parent -> Right -> Left, but why left first, explained in Comment above
            if (dequedItem.left != null) {
                stack_1.push(dequedItem.left);
            }
            if (dequedItem.right != null) {
                stack_1.push(dequedItem.right);
            }
        }

        while (!stack_2.empty()) {
            System.out.print(stack_2.pop() + " , ");
        }
        System.out.println();
    }

    public void diagonalOrderTraversalInOn2Complexity(Node root) {
        logIt("Doing Diagonal Order Traversal in recursive order", true);
        Map<Integer, List<Integer>> levelDataMap = new HashMap<>();
        diagonalOrderTraversalHelper(root, 0, levelDataMap);
        System.out.println(levelDataMap);
    }

    private void diagonalOrderTraversalHelper(Node root, int level, Map<Integer, List<Integer>> levelDataMap) {
        if (root != null) {
            if (levelDataMap.containsKey(level)) {
                levelDataMap.get(level).add(root.data);
            } else {
                List<Integer> data = new ArrayList<>();
                data.add(root.data);
                levelDataMap.put(level, data);
            }

            diagonalOrderTraversalHelper(root.left, level - 1, levelDataMap);
            diagonalOrderTraversalHelper(root.right, level, levelDataMap);
        }
    }

    public void diagonalOrderTraversalIterativeSolution(Node root) {
        logIt("Doing Diagonal Order Traversal in iterative order", true);
        Stack<Node> stack = new Stack<>();
        Node poppedItem;
        stack.push(root);

        while (!stack.isEmpty()) {
            poppedItem = stack.pop();
            System.out.println();
            // So let's move diagonally and keep pushing any connection to left in the Stack as that will be used in the
            // next cycle to traverse diagonal below this.
            while (poppedItem != null) {
                System.out.print(poppedItem.data + " , ");
                if (poppedItem.left != null) {
                    stack.push(poppedItem.left);
                }
                poppedItem = poppedItem.right;
            }
        }
        System.out.println();

    }

    public void checkIfLeafTraversalIsSame(BinaryTreeUtil tree1, BinaryTreeUtil tree2) {
        Node head1;
        Node head2;
        Boolean isLeafTraversalSame = true;

        connectLeafNodesAndReturnHead(tree1.root, tree1);

        connectLeafNodesAndReturnHead(tree2.root, tree2);

        // Now we have 2 connected leaf Nodes we just have to traverse and check if both list are same
        head1 = tree1.head;
        head2 = tree2.head;

        while (head1 != null && head2 != null) {
            if (head1.data != head2.data) {
                isLeafTraversalSame = false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }

        if (head1 != null || head2 != null) {
            isLeafTraversalSame = false;
        }
        logIt(" Is Leaf Traversal same for both Nodes  ? " + isLeafTraversalSame, true);
    }

    private void connectLeafNodesAndReturnHead(Node root, BinaryTreeUtil tree) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                if (tree.head == null) {
                    tree.head = root;
                    tree.prev = tree.head;
                } else {
                    tree.prev.next = root;
                    tree.prev = root;
                }
            }
            connectLeafNodesAndReturnHead(root.left, tree);
            connectLeafNodesAndReturnHead(root.right, tree);
        }
    }

    public void printBinaryTreeInVerticalOrder(Node root) {

        logIt("Binary Tree in Vertical order traversal", true);
        // We are using tree map to sort map based on key.
        Map<Integer, List<Integer>> horizontalDistance_DataMap = new TreeMap<>();
        traverseTreeAndPopulateNodesInMapAccordingToTheirHorizontalDistance(root, 0, horizontalDistance_DataMap);
        System.out.println(horizontalDistance_DataMap);
    }

    private void traverseTreeAndPopulateNodesInMapAccordingToTheirHorizontalDistance(Node root, int level, Map<Integer, List<Integer>> horizontalDistance_dataMap) {

        if (root != null) {
            if (horizontalDistance_dataMap.containsKey(level)) {
                horizontalDistance_dataMap.get(level).add(root.data);
            } else {
                List<Integer> data = new ArrayList<>();
                data.add(root.data);
                horizontalDistance_dataMap.put(level, data);
            }
            traverseTreeAndPopulateNodesInMapAccordingToTheirHorizontalDistance(root.left, level - 1, horizontalDistance_dataMap);
            traverseTreeAndPopulateNodesInMapAccordingToTheirHorizontalDistance(root.right, level + 1, horizontalDistance_dataMap);
        }

    }

    class NodeWithHorizontalDistance {
        int horizontalDistance;
        Node node;


        public NodeWithHorizontalDistance(Node node, int horizontalDistance) {
            this.horizontalDistance = horizontalDistance;
            this.node = node;
        }
    }

    public void printBinaryTreeInVerticalOrderIteratively(Node root) {
        logIt("Binary Tree in Vertical order traversal iteratively", true);

        Queue<NodeWithHorizontalDistance> queue = new LinkedList();
        queue.add(new NodeWithHorizontalDistance(root, 0));
        NodeWithHorizontalDistance dequeuedNode;
        Map<Integer, List<Integer>> horizontalDistance_dataMap = new TreeMap<>();


        while (!queue.isEmpty()) {
            dequeuedNode = queue.poll();
            if (horizontalDistance_dataMap.containsKey(dequeuedNode.horizontalDistance)) {
                horizontalDistance_dataMap.get(dequeuedNode.horizontalDistance).add(dequeuedNode.node.data);
            } else {
                List<Integer> data = new ArrayList<>();
                data.add(dequeuedNode.node.data);
                horizontalDistance_dataMap.put(dequeuedNode.horizontalDistance, data);
            }

            if (dequeuedNode.node.left != null) {
                queue.add(new NodeWithHorizontalDistance(dequeuedNode.node.left, dequeuedNode.horizontalDistance - 1));
            }

            if (dequeuedNode.node.right != null) {
                queue.add(new NodeWithHorizontalDistance(dequeuedNode.node.right, dequeuedNode.horizontalDistance + 1));
            }
        }

        System.out.println(horizontalDistance_dataMap);
    }

    public void boundaryOrderTraversal(Node root) {

        logIt("Doing Boundary Order Traversal", true);
        /**
         * 1) Do Left Boundary Traversal
         * 2) Do Leaf Traversal
         * 3) Do Right Boundary Traversal.
         */

        leftBoundaryTraversal(root);
        leafTraversal(root);
        // This stack will be used to print the right boundary in reverse order so that we can print boundary Order Traversal in the correct order
        Stack<Integer> stack = new Stack();
        // Why we are starting with root.right, because the root is already included in the left boundary traversal.
        rightBoundaryTraversal(root.right, stack);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " , ");
        }

        newLine();

    }

    private void rightBoundaryTraversal(Node root, Stack stack) {
        if (root != null) {
            if (root.right != null) {
                stack.push(root.data);
                rightBoundaryTraversal(root.right, stack);
            } else if (root.left != null) {
                stack.push(root.data);
                rightBoundaryTraversal(root.left, stack);
            }
            // We are not printing the leaf nodes, here intentionally.
        }
    }

    private void leafTraversal(Node root) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                System.out.print(root.data + " , ");
            }
            leafTraversal(root.left);
            leafTraversal(root.right);
        }
    }

    private void leftBoundaryTraversal(Node root) {
        if (root != null) {
            if (root.left != null) {
                System.out.print(root.data + " , ");
                leftBoundaryTraversal(root.left);
            } else if (root.right != null) {
                System.out.print(root.data + " , ");
                leftBoundaryTraversal(root.right);
            }
            // We are not printing the leaf nodes, here intentionally.
        }
    }

    // Solution is simple, do level order traversal, and print only first node
    public void printLeftViewOfBinaryTree(Node root) {
        logIt("Left Side view of Binary Tree", true);
        Queue<Node> queue = new LinkedList();
        queue.add(root);
        queue.add(null);
        Boolean isFirstNodePrinted = false;
        Node dequedItem = null;

        while (!queue.isEmpty()) {
            dequedItem = queue.poll();

            if (dequedItem == null) {
                isFirstNodePrinted = false;
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
            } else {
                if (!isFirstNodePrinted) {
                    System.out.print(dequedItem.data + " , ");
                    isFirstNodePrinted = true;
                }
                if (dequedItem.left != null) {
                    queue.add(dequedItem.left);
                }
                if (dequedItem.right != null) {
                    queue.add(dequedItem.right);
                }
            }
        }
        newLine();
    }

    public void lowestCommonAncestor(Node root, Integer data1, Integer data2) {
        Node lca = findLCA(root, data1, data2);

        logIt("Lowest Common Ancestor of " + data1 + " and " + data2 + " is " + lca.data);
    }

    private Node findLCA(Node root, Integer data1, Integer data2) {
        if (root == null) {
            return null;
        }
        if (root.data == data1 || root.data == data2) {
            return root;
        }
        Node leftLCA = findLCA(root.left, data1, data2);
        Node rightLCA = findLCA(root.right, data1, data2);

        if (leftLCA != null && rightLCA != null) {
            return root;
        }
        if (leftLCA == null)
            return rightLCA;
        else
            return leftLCA;
    }

    public void distanceBetween_2NodesOfBinaryTree(Node root, int node1, int node2) {
        Node lcaOf2Nodes = findLCA(root, node1, node2);
        int level1 = findLevelFromLCAToNode(lcaOf2Nodes, 0, node1);
        int level2 = findLevelFromLCAToNode(lcaOf2Nodes, 0, node2);
        logIt("Finding Distance between Nodes " + node1 + " and " + node2 + " is " + (level1 + level2));
    }

    private int findLevelFromLCAToNode(Node lcaOf2Nodes, int level, int node) {
        if (lcaOf2Nodes == null) {
            return -1;
        }
        if (lcaOf2Nodes.data == node) {
            return level;
        }
        int levelOfNode = findLevelFromLCAToNode(lcaOf2Nodes.left, level + 1, node);

        if (levelOfNode == -1) {
            levelOfNode = findLevelFromLCAToNode(lcaOf2Nodes.right, level + 1, node);
        }
        return levelOfNode;
    }

    static int MAX_SUM = 0;

    public void maximumPathSumBetweenTwoLeavesOfTree(Node root) {
        logIt("Finding Maximum Path Sum Between Two Leaves Of Tree", true);
        MAX_SUM = 0;
        maxPathSumBetweenLeaf(root);
        logIt("Max sum is " + MAX_SUM, true);

    }

    private int maxPathSumBetweenLeaf(Node root) {
        if (root == null)
            return 0;

        // Leaf Node
        if (root.left == null && root.right == null) {
            return root.data;
        }

        int leftSum = maxPathSumBetweenLeaf(root.left);
        int rightSum = maxPathSumBetweenLeaf(root.right);

        // Now if this is a root node, with both the child, then let's calculate the sum including the parent node
        if (root.left != null && root.right != null) {
            // First check if we are greater than MAX_SUM

            MAX_SUM = Math.max(MAX_SUM, leftSum + rightSum + root.data);
            return leftSum + rightSum + root.data;
        }

        // Since the root node have only 1 child, so we have to add that child to the root.
        return root.data + ((root.left != null) ? leftSum : rightSum);
    }

    public static void main(String[] args) {

        BinaryTreeUtil treeUtil = new BinaryTreeUtil();
        treeUtil.root = treeUtil.getSampleTree();

        treeUtil.getSizeOfTree();

        logIt("Inorder Traversal ");
        treeUtil.inorder(treeUtil.root);

        treeUtil.inorderWithoutRecursionUsingStack(treeUtil.root);

        treeUtil.inorderTraversalWithoutRecursionWithoutStack(treeUtil.root);

        int[] inorderTraversal = new int[]{4, 2, 5, 1, 3};
        int[] preOrderTraversal = new int[]{1, 2, 4, 5, 3};

        logIt("Constructing Binary Tree from Inorder and PreOrder Traversal", true);
        printArray(inorderTraversal);
        printArray(preOrderTraversal);
        treeUtil.root = treeUtil.constructBinaryTreeUsingInorderAndPreOrderTraversal(inorderTraversal, preOrderTraversal, 0, preOrderTraversal.length - 1);

        logIt("Tree is constructed, let's print PostOrder for the same", true);
        treeUtil.postOrder(treeUtil.root);

        logIt("Post Order Traversal, from PreOrder and Inorder traversal", true);
        preOrderIndexCounter = 0;
        treeUtil.printPostOrderTraversalFromInorderAndPreOrderTraversal(inorderTraversal, preOrderTraversal, 0, preOrderTraversal.length - 1);


        treeUtil.levelOrderTraversal(treeUtil.root);

        logIt("Finding Maximum in Binary Tree", true);
        int maxValue = treeUtil.findMaximumInBinaryTree(treeUtil.root);
        logIt("Maximum value is [" + maxValue + "]", true);

        treeUtil.levelOrderTraversalUsing2Queues(treeUtil.root);

        treeUtil.levelOrderTraversalInReverseOrderUsingStackAndTheQueue(treeUtil.root);

        treeUtil.iterativePreOrderTraversal(treeUtil.root);

        treeUtil.root = treeUtil.getSampleTree();
        treeUtil.iterativePostOrderTraversal(treeUtil.root);


        /**
         * * Sample Tree
         * *            1
         * *          /   \
         * *        2     3
         * *      /  \
         * *     4   5
         * */

        treeUtil.pathToNode = new Stack<>();
        logIt("Printing Path to Node 3 from Root ", true);
        treeUtil.printPathToNode(treeUtil.root, 3);

        treeUtil.pathToNode = new Stack<>();
        logIt("Printing Path to All Leaf Nodes from Root ", true);
        treeUtil.printAllPathToLeafNode(treeUtil.root);


        treeUtil.diagonalOrderTraversalInOn2Complexity(treeUtil.root);

        treeUtil.diagonalOrderTraversalIterativeSolution(treeUtil.root);


        BinaryTreeUtil leafTraversal_1 = new BinaryTreeUtil();
        BinaryTreeUtil leafTraversal_2 = new BinaryTreeUtil();

        leafTraversal_1.root = leafTraversal_1.getSampleTreeForLeafTRaversal_1();
        leafTraversal_2.root = leafTraversal_2.getSampleTreeForLeafTRaversal_2();
        leafTraversal_1.checkIfLeafTraversalIsSame(leafTraversal_1, leafTraversal_2);

        treeUtil.printBinaryTreeInVerticalOrder(leafTraversal_1.root);
        treeUtil.printBinaryTreeInVerticalOrder(leafTraversal_2.root);

        treeUtil.printBinaryTreeInVerticalOrderIteratively(leafTraversal_1.root);

        treeUtil.root = treeUtil.getSampleTreeForBoundaryOrderTraversal();
        treeUtil.boundaryOrderTraversal(treeUtil.root);

        treeUtil.printLeftViewOfBinaryTree(treeUtil.root);

        treeUtil.lowestCommonAncestor(treeUtil.root, 4, 25);
        treeUtil.lowestCommonAncestor(treeUtil.root, 4, 12);
        treeUtil.lowestCommonAncestor(treeUtil.root, 4, 10);
        treeUtil.lowestCommonAncestor(treeUtil.root, 10, 14);
        treeUtil.lowestCommonAncestor(treeUtil.root, 10, 8);


        treeUtil.distanceBetween_2NodesOfBinaryTree(treeUtil.root, 4, 22);
        treeUtil.distanceBetween_2NodesOfBinaryTree(treeUtil.root, 4, 14);
        treeUtil.distanceBetween_2NodesOfBinaryTree(treeUtil.root, 14, 25);


        treeUtil.root = treeUtil.getSampleTreeForMaximumSumPathBetween_Two_Leaf();

        treeUtil.maximumPathSumBetweenTwoLeavesOfTree(treeUtil.root);
    }
}
