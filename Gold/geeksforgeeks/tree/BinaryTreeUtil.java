package com.geeksforgeeks.tree;

import java.util.*;

public class BinaryTreeUtil {

    public Node root;
    public static String MAX = "MAX";
    public static String MIN = "MIN";

    static class Node {
        int data;
        Node left;
        Node right;
        Node head; // Used when converting Tree into DLL or Circular DLL
        Node prev;
        Node next; // To Populate Inorder Successor
        int horizontalDistance;

        Node() {

        }

        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        BinaryTreeUtil sizeUtil = new BinaryTreeUtil();
        sizeUtil.root = sizeUtil.getSampleTree();

        System.out.println("Size of the tree is " + sizeUtil.getSize(sizeUtil.root));

        letsDo("Inorder Traversal");
        sizeUtil.inorderTraversal(sizeUtil.root);
        newLine();

        letsDo("PreOrder Traversal");
        sizeUtil.preOrderTraversal(sizeUtil.root);
        newLine();

        letsDo("PostOrder Traversal");
        sizeUtil.postOrderTraversal(sizeUtil.root);
        newLine();

        letsDo("Height of the tree is :: " + sizeUtil.getHeightOfTree(sizeUtil.root) + " ");

        letsDo("Level Order Traversal");
        sizeUtil.levelOrderTraversal(sizeUtil.root, true);
        newLine();

        letsDo("Level Order Traversal using Queue");
        sizeUtil.levelOrderTraversalUsingQueue(sizeUtil.root);
        newLine();

        letsDo("Find Maximum and Minumum");
        Map<String, Integer> maxAndMinMap = new HashMap<>();
        sizeUtil.getMaxAndMin(sizeUtil.root, maxAndMinMap);
        System.out.println("The Maximum value is " + maxAndMinMap.get(BinaryTreeUtil.MAX));
        System.out.println("The Minimum value is " + maxAndMinMap.get(BinaryTreeUtil.MIN));


        letsDo("Level Order Traversal using 2 Queues");
        sizeUtil.levelOrderTraversalUsing2Queues(sizeUtil.root);
        newLine();

        letsDo("Level Order Traversal in Reverse Order using Stack and  2 Queues");
        sizeUtil.levelOrderTraversalInReverseOrderUsingStackAnd2Queue(sizeUtil.root);
        newLine();

        letsDo("Level Order Traversal in Reverse Order using Stack and 1 Queue");
        sizeUtil.levelOrderTraversalInReverseOrderUsingStackAndQueue(sizeUtil.root);
        newLine();

        letsDo("Inorder Traversal without Recursion");
        sizeUtil.inorderTraversalWithoutRecursion(sizeUtil.root);
        newLine();


        letsDo("inorderTraversalWithoutRecursionWithoutStackMorrisTraversal");
        sizeUtil.inorderTraversalWithoutRecursionWithoutStackMorrisTraversal(sizeUtil.root);
        newLine();


        letsDo("PreOrder Traversal Without Recursion");
        sizeUtil.preOrderTraversalWithoutRecursion(sizeUtil.root);
        newLine();


        letsDo("PreOrder Traversal Without Recursion without Stack as well");
        sizeUtil.preOrderTraversalWithoutRecursionWithoutStackMorrisTraversal(sizeUtil.root);
        newLine();


        letsDo("Iterative PostOrder Traversal");
        sizeUtil.iterativePostOrder(sizeUtil.root);
        newLine();


        letsDo("Print Post order with Inorder and pre order");
        sizeUtil.printPostOrderWithInorderAndPreOrder(new int[]{4, 2, 5, 1, 3, 6}, new int[]{1, 2, 4, 5, 3, 6}, 6);
        newLine();

        letsDo("Diagonal Order Traversal");
        sizeUtil.diagonalOrderTraversal(sizeUtil.root);
        newLine();

        letsDo("Iterative Diagonal Order Traversal");
        sizeUtil.iterativeDiagonalOrderTraversal(sizeUtil.root);
        newLine();

        letsDo("Check if leaf traversal of two Binary Trees is same ? start ");
        BinaryTreeUtil util1 = new BinaryTreeUtil();
        BinaryTreeUtil util2 = new BinaryTreeUtil();

        util1.root = new Node(1);
        util1.root.left = new Node(2);
        util1.root.right = new Node(3);
        util1.root.left.left = new Node(4);

        util1.root.right.left = new Node(6);
        util1.root.right.right = new Node(7);

        util1.levelOrderTraversalUsingQueue(util1.root);

        util2.root = new Node(0);
        util2.root.left = new Node(5);
        util2.root.right = new Node(8);
        util2.root.left.right = new Node(4);

        util2.root.right.left = new Node(6);
        util2.root.right.right = new Node(7);

        util1.levelOrderTraversalUsingQueue(util2.root);


        letsDo("Check if leaf traversal of two Binary Trees is same ? END " + util1.checkIfLeafTraversalOfBinaryTreeIsSame(util1.root, util2.root));
        newLine();

        letsDo("Print Tree in Vertical Order ");
        sizeUtil.printVerticalViewOfTree(sizeUtil.root);
        newLine();


        letsDo("Boundry Traversal ");
        sizeUtil.boundryTraversal(sizeUtil.root);
        newLine();

        letsDo("Top View of Binary Tree is ");
        sizeUtil.topViewOfBinaryTree(sizeUtil.root);
        newLine();

        letsDo("Left View Of Binary Tree ");
        sizeUtil.printLeftView(sizeUtil.root);
        newLine();

        letsDo("Bottom View of Binary Tree");
        sizeUtil.printBottomView(sizeUtil.root);
        newLine();

        letsDo("Lowest Common Ancestor of Binary Search Tree ");
        BinaryTreeUtil lcaUtil = new BinaryTreeUtil();
        lcaUtil.root = getSampleBinarySearchTree();

        System.out.println("LCA of 10 and 14 is " + lcaUtil.LCAOfBinarySearchTree(lcaUtil.root, 10, 14).data);
        System.out.println("LCA of 4 and 25 is " + lcaUtil.LCAOfBinarySearchTree(lcaUtil.root, 4, 25).data);
        System.out.println("LCA of 4 and 12 is " + lcaUtil.LCAOfBinarySearchTree(lcaUtil.root, 4, 12).data);

        letsDo("Lowest Common Ancestor of Binary Tree ");
        Node lca = sizeUtil.LCAOfBinaryTree(sizeUtil.root, 4, 6);
        if (n1Found && n2Found) {
            System.out.println("LCA of 4 and 6 is " + lca.data);
        } else {
            System.out.println("LCA of 4 and 6 is  " + null);
        }
        n1Found = false;
        n2Found = false;
        newLine();

        letsDo("Print Path from Root to Node");
        letsDo("Path from Root To Node 5 is ");
        sizeUtil.printPathToNode(sizeUtil.root, 5);
        newLine();
        letsDo("Path from Root To Node 6 is ");
        sizeUtil.printPathToNode(sizeUtil.root, 6);
        letsDo("Path from Root To Node 7 is ");
        sizeUtil.printPathToNode(sizeUtil.root, 7);


        letsDo("Print All Paths from Root to Leaf Node ");
        pathLen = -1;
        sizeUtil.printAllPathsFromRootToLeafNode(sizeUtil.root, new int[100], -1);

        letsDo("Get Distance between 2 nodes");
        System.out.println("Distance between Node 2 and Node 3 is " + sizeUtil.getDistanceBetweenTwoNodes(sizeUtil.root, 2, 3));
        System.out.println("Distance between Node 4 and Node 3 is " + sizeUtil.getDistanceBetweenTwoNodes(sizeUtil.root, 4, 3));
        System.out.println("Distance between Node 5 and Node 6 is " + sizeUtil.getDistanceBetweenTwoNodes(sizeUtil.root, 5, 6));
        newLine();


        letsDo(" Get Distance between 2 Nodes Version 2");
        System.out.println("Distance between Node 2 and Node 3 is " + sizeUtil.getDistanceBetweenTwoNodesVersion2(sizeUtil.root, 2, 3));
        System.out.println("Distance between Node 4 and Node 3 is " + sizeUtil.getDistanceBetweenTwoNodesVersion2(sizeUtil.root, 4, 3));
        System.out.println("Distance between Node 5 and Node 6 is " + sizeUtil.getDistanceBetweenTwoNodesVersion2(sizeUtil.root, 5, 6));
        newLine();

        letsDo("Check if Tree is Height Balanced ? " + sizeUtil.isTreeHeightBalanced(sizeUtil.root));
        newLine();

        BinaryTreeUtil treeBalancedUtil = new BinaryTreeUtil();

        treeBalancedUtil.root = getSampleTreeForHeightBalanced();

        letsDo("Check if Tree is Height Balanced (optimized calculating height on the fly) " + sizeUtil.isTreeHeightBalanced(sizeUtil.root, new HeightOfTree()));
        newLine();


        letsDo("Find Diameter of a Tree ? " + sizeUtil.getDiameterOfTree(sizeUtil.root, new HeightOfTree()));
        newLine();

        letsDo("Has Children Sum Property ?" + sizeUtil.hasChildrenSumProperty(sizeUtil.root));

        letsDo(" Convert Tree into Doubly Linked List");
        sizeUtil.convertTreeToDLL(sizeUtil.root);
        newLine();

        letsDo("Convert Tree into Doubly Linked List Version 2");
        sizeUtil.root = getSampleTree();
        sizeUtil.convertTreeToDLLVersion2(sizeUtil.root);
        printDoublyLinkedListConvertedFromTree(head);
        newLine();

        letsDo("Convert Tree into DLL using Level Order Traversal");
        sizeUtil.root = getSampleTree();
        sizeUtil.convertTreeToDLLLevelOrderTraversalVersion(sizeUtil.root);


        letsDo("Convert Tree into Circular DLL");
        sizeUtil.root = getSampleTree();
        sizeUtil.convertTreeIntoCircularDLL(sizeUtil.root);
        newLine();

        letsDo("Populate Inorder Successor ");
        sizeUtil.root = getSampleTree();
        sizeUtil.populateInorderSuccessorUtil(sizeUtil.root);
        newLine();


        letsDo("Print Path for a given sum");
        sizeUtil.root = getSampleTree();
        sizeUtil.printPathForAGivenSum(sizeUtil.root, 10);
        sizeUtil.printPathForAGivenSum(sizeUtil.root, 8);
        newLine();


        letsDo("addAllGreaterNodesToBinaryTree");
        sizeUtil.root = getSampleTree();
        sizeUtil.levelOrderTraversalUsingQueue(sizeUtil.root);
        sizeUtil.addAllGreaterNodesToBinaryTree(sizeUtil.root);
        System.out.println("After Adding all Greater Nodes");
        sizeUtil.levelOrderTraversalUsingQueue(sizeUtil.root);
        newLine();


        letsDo("Find Maximum Path Sum Between 2 leaves of a binary tree");
        sizeUtil.root = getSampleTree();
        sizeUtil.printMaximumPathSumBetween2Leaves(sizeUtil.root);
        newLine();


        letsDo(" Find Maximum Path Sum between any 2 nodes of a binary tree");
        sizeUtil.root = getSampleTreeForMaximumPathSumProblem();
        sizeUtil.findMaximumPathSumBetweenAnyNode(sizeUtil.root);
        newLine();

        letsDo("Find if Tree is Complete or Not");
        sizeUtil.root = getSampleTreeForCompleteTreeProblem();
        System.out.println(isCompleteTree(sizeUtil.root));


        letsDo("Find if tree is complete or Not Recursively....");
        System.out.println(isCompleteTreeRecursively(sizeUtil.root));

        letsDo("Find if Tree is BINARY HEAP");

        Node root = new Node(100);
        root.left = new Node(25);
        root.right = new Node(20);
        root.left.right = new Node(17);

        sizeUtil.root = root;

        System.out.println(binaryTreeIsMaxHeap(sizeUtil.root));

    }


    public static Node getSampleTreeForCompleteTreeProblem() {
//        Node root = new Node(1);
//        root.left = new Node(2);
//        root.right = new Node(3);
//        root.left.left = new Node(4);
//        root.left.right = new Node(5);
//        root.right.left = new Node(6);

        Node root = new Node(10);
        root.left = new Node(9);
        root.right = new Node(8);
        root.left.left = new Node(7);
        root.left.right = new Node(6);
        root.right.left = new Node(5);
        root.right.right = new Node(4);
        root.left.left.left = new Node(3);
        root.left.left.right = new Node(2);
        root.left.right.left = new Node(1);

        return root;
    }

    public static Node getSampleTreeForMaximumPathSumProblem() {
        Node root = new Node(10);
        root.left = new Node(2);
        root.right = new Node(10);
        root.left.left = new Node(20);
        root.left.right = new Node(1);
        root.right.right = new Node(-25);
        root.right.right.left = new Node(3);
        root.right.right.right = new Node(4);
        return root;
    }

    public static void printDoublyLinkedListConvertedFromTree(Node root) {
        while (root != null) {
            System.out.print(root.data + " -->");
            root = root.right;
        }
        System.out.println();
    }

    public static void printCircularDoublyLinkedListConvertedFromTree() {
        Node temp = head;
        System.out.println("Printing the CDLL!");
        while (temp.right != head) {
            System.out.print(temp.data + " --> ");
            temp = temp.right;
        }
    }

    public static void letsDo(String task) {
        System.out.println("==================" + task + "======================");
    }

    public static void newLine() {
        System.out.println();
    }

    public static Node getSampleBinarySearchTree() {
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

    public static Node getSampleTreeForHeightBalanced() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        return root;
    }

    public static Node getSampleTree() {
        Node root = new Node(1);

        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
//        root.left.right.left = new Node(8);
//        root.left.right.right = new Node(9);


        root.right = new Node(3);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
//        root.right.right.left = new Node(10);


        return root;
    }

    public int getSize(Node root) {
        if (root == null)
            return 0;
        return getSize(root.left) + 1 + getSize(root.right);
    }

    public void inorderTraversal(Node root) {
        if (root == null)
            return;
        inorderTraversal(root.left);
        System.out.print(root.data + ",");
        inorderTraversal(root.right);
    }

    public void preOrderTraversal(Node root) {
        if (root == null)
            return;
        System.out.print(root.data + ",");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    public void postOrderTraversal(Node root) {
        if (root == null)
            return;
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.data + ",");
    }

    public int getHeightOfTree(Node root) {
        if (root == null)
            return 0;
        int leftHeight = getHeightOfTree(root.left);
        int rightHeight = getHeightOfTree(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public void levelOrderTraversal(Node root, Boolean doReverseLevelOrderTraversal) {
        int totalLevels = getHeightOfTree(root);
        for (int i = 0; i < totalLevels; i++) {
            traverseLevel(root, i, doReverseLevelOrderTraversal ? totalLevels - 1 : 0, doReverseLevelOrderTraversal);
            System.out.println(":: Level " + i + "\n-=-=-=-=-=-=-=-=-");
        }
    }

    private void traverseLevel(Node root, int levelToTraverse, int currentLevel, Boolean doReverseLevelOrderTraversal) {
        if (root == null)
            return;
        if (levelToTraverse == currentLevel) {
            System.out.print(root.data + "\t");
            return;
        }
        traverseLevel(root.left, levelToTraverse, doReverseLevelOrderTraversal ? currentLevel - 1 : currentLevel + 1, doReverseLevelOrderTraversal);
        traverseLevel(root.right, levelToTraverse, doReverseLevelOrderTraversal ? currentLevel - 1 : currentLevel + 1, doReverseLevelOrderTraversal);
    }

    public void levelOrderTraversalUsingQueue(Node root) {
        Queue<Node> queue = new LinkedList<>();
        Node temp;
        queue.add(root);
        queue.add(null); // This act as a delimiter to add print new level on new line
        while (!queue.isEmpty()) {

            temp = queue.poll();

            if (temp == null) {
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
                System.out.println();
            } else {
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
                System.out.print(temp.data + "\t");
            }
        }
    }

    public void levelOrderTraversalUsing2Queues(Node root) {
        Queue<Node> queue1 = new LinkedList<>();
        Queue<Node> queue2 = new LinkedList<>();
        Node temp;

        queue1.add(root);

        while (!queue1.isEmpty() || !queue2.isEmpty()) {

            //noinspection Duplicates
            while (!queue1.isEmpty()) {
                temp = queue1.poll();
                System.out.print(temp.data + "\t");
                if (temp.left != null)
                    queue2.add(temp.left);
                if (temp.right != null)
                    queue2.add(temp.right);

                if (queue1.isEmpty())
                    System.out.println();
            }

            //noinspection Duplicates
            while (!queue2.isEmpty()) {
                temp = queue2.poll();
                System.out.print(temp.data + "\t");
                if (temp.left != null)
                    queue1.add(temp.left);
                if (temp.right != null)
                    queue1.add(temp.right);

                if (queue2.isEmpty())
                    System.out.println();
            }
        }
    }


    public void levelOrderTraversalInReverseOrderUsingStackAnd2Queue(Node root) {
        Stack<Node> stack = new Stack<>();
        Queue<Node> queue1 = new LinkedList<>();
        Queue<Node> queue2 = new LinkedList<>();
        Node temp;

        queue1.add(root);
        while (!queue1.isEmpty() || !queue2.isEmpty()) {

            //noinspection Duplicates
            while (!queue1.isEmpty()) {
                temp = queue1.poll();
                stack.push(temp);
                if (temp.right != null)
                    queue2.add(temp.right);
                if (temp.left != null)
                    queue2.add(temp.left);
            }

            stack.push(null);
            //noinspection Duplicates
            while (!queue2.isEmpty()) {
                temp = queue2.poll();
                stack.push(temp);
                if (temp.right != null)
                    queue1.add(temp.right);
                if (temp.left != null)
                    queue1.add(temp.left);
            }
            stack.push(null);
        }
        printStackValue(stack);
    }

    public void levelOrderTraversalInReverseOrderUsingStackAndQueue(Node root) {
        Stack<Node> stack = new Stack<>();
        Queue<Node> queue = new LinkedList<>();
        Node temp;

        queue.add(root);
        queue.add(null);
//        while (!queue.isEmpty()) {
//            if (stack.size() > 0) {
//                stack.push(null);
//            }
//            int sz = queue.size();
//
//            for (int i = 0; i < sz; ++i) {  // Processing Each Level, So for first level this loop will run upto 1 time, for level 2 it will run upto 2, level 3 it will run upto 4
//                temp = queue.poll();
//                stack.push(temp);
//                // Enqueue first Right then left because we are storing the end result in stack which is actually LIFO
//                if (temp.right != null) {
//                    queue.add(temp.right);
//                }
//                if (temp.left != null) {
//                    queue.add(temp.left);
//                }
//            }
//        }

        // New Technique similar to having new line in Level Order Traversal using Queue
        while (!queue.isEmpty()) {
            temp = queue.poll();

            if (temp == null) {
                if (queue.isEmpty()) {
                    break;
                } else {
                    queue.add(null);
                    stack.push(null);
                }
            } else {
                stack.push(temp);
                if (temp.right != null) {
                    queue.add(temp.right);
                }
                if (temp.left != null) {
                    queue.add(temp.left);
                }
            }
        }
        printStackValue(stack);
    }

    private static void printStackValue(Stack<Node> stack) {
        Node temp;
        while (!stack.isEmpty()) {
            temp = stack.pop();
            if (temp == null) {
                System.out.println();
                continue;
            }
            System.out.print(temp.data + "\t");
        }
    }

    public void getMaxAndMin(Node root, Map<String, Integer> maxAndMinMap) {
        if (root == null) {
            return;
        }
        getMaxAndMin(root.left, maxAndMinMap);
        Integer max = maxAndMinMap.get(BinaryTreeUtil.MAX);
        Integer min = maxAndMinMap.get(BinaryTreeUtil.MIN);

        if (max == null || max < root.data) {
            maxAndMinMap.put(BinaryTreeUtil.MAX, root.data);
        }
        if (min == null || min > root.data) {
            maxAndMinMap.put(BinaryTreeUtil.MIN, root.data);
        }

        getMaxAndMin(root.right, maxAndMinMap);
    }


    public void inorderTraversalWithoutRecursion(Node root) {
        Node current = root;
        Stack<Node> stack = new Stack<>();
        Node popped;

        while (current != null) {
            stack.push(current);
            current = current.left;
        }

        while (!stack.isEmpty()) {
            popped = stack.pop();
            System.out.print(popped.data + "\t");
            if (popped.right != null) {
                current = popped.right;
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
            }
        }
    }

    public void inorderTraversalWithoutRecursionWithoutStackMorrisTraversal(Node root) {
        Node current = root;

        while (current != null) {

            if (current.left == null) {
                System.out.print(current.data + "\t");
                current = current.right;
            } else {
                Node previous = current.left;

                while (previous.right != null && previous.right != current) {
                    previous = previous.right;
                }

                if (previous.right == null) {
                    previous.right = current;
                    current = current.left;
                } else {
                    previous.right = null;
                    System.out.print(current.data + "\t");
                    current = current.right;
                }
            }
        }
    }

    public void preOrderTraversalWithoutRecursionWithoutStackMorrisTraversal(Node root) {
        Node current = root;

        while (current != null) {

            if (current.left == null) {
                System.out.print(current.data + "\t");
                current = current.right;
            } else {
                Node previous = current.left;

                while (previous.right != null && previous.right != current) {
                    previous = previous.right;
                }

                if (previous.right == null) {
                    System.out.print(current.data + "\t");
                    previous.right = current;
                    current = current.left;
                } else {
                    previous.right = null;
                    current = current.right;
                }
            }
        }
    }

    public void preOrderTraversalWithoutRecursion(Node root) {
        Node current = root;
        Stack<Node> stack = new Stack<>();
        Node popped = null;
        stack.push(root);
        while (!stack.isEmpty()) {
            popped = stack.pop();
            System.out.print(popped.data + "\t");
            if (popped.right != null) {
                stack.push(popped.right);
            }
            if (popped.left != null) {
                stack.push(popped.left);
            }
        }
    }

    /**
     * This will use iterativePreOrder but in Parent Right Left fashion instead of Parent Left Right fashion.
     * <p>
     * Use 2 Stack, 1 to do PreOrder traversal in Parent->Right->Left order and other to store
     * reverse PreOrder data which is actually the Post Order traversal if pushed into Stack and then popped.
     *
     * @param root
     */
    public void iterativePostOrder(Node root) {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        Node temp;
        stack1.push(root);
        while (!stack1.isEmpty()) {
            temp = stack1.pop();

            stack2.push(temp);  // Storing the PostOrder Traversal

            if (temp.left != null) {  // Notice we are actually checking the left first and right afterwards, where as in preOrder case we did exact opposite because we are using Stack datastructure.
                stack1.push(temp.left);
            }
            if (temp.right != null) {
                stack1.push(temp.right);
            }
        }

        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().data + "\t");
        }
    }

    private int getIndexOfItem(int[] sample, int itemToBeSearched, int size) {
        for (int i = 0; i < size; i++) {
            if (sample[i] == itemToBeSearched) {
                return i;
            }
        }
        return -1;
    }

    public void printPostOrderWithInorderAndPreOrder(int[] inorder, int[] preorder, int size) {

        int indexOfRoot = getIndexOfItem(inorder, preorder[0], size);

        // If left subtree is not empty, print left subtree
        if (indexOfRoot != 0)
            printPostOrderWithInorderAndPreOrder(inorder, Arrays.copyOfRange(preorder, 1, preorder.length), indexOfRoot);

        // If right subtree is not empty, print right subtree
        if (indexOfRoot != size - 1)
            printPostOrderWithInorderAndPreOrder(Arrays.copyOfRange(inorder, indexOfRoot + 1, inorder.length),
                    Arrays.copyOfRange(preorder, indexOfRoot + 1, preorder.length), size - indexOfRoot - 1);

        System.out.print(preorder[0] + "\t");
    }

    private static void diagonalOrderTraversal(Node root) {
        Map<Integer, List<Integer>> nodesAtParticularHorizontalDistance = new HashMap<>();
        setNodesAtParticularHorizontalDistance(root, nodesAtParticularHorizontalDistance, 0, true);
        System.out.println(nodesAtParticularHorizontalDistance);
    }

    /**
     * We will use queue to only store any left child and we move our temp to only on the right node.
     * Using null as a delimiter to add new line
     *
     * @param root
     */
    private static void iterativeDiagonalOrderTraversal(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null); // Delimiter
        Node temp;

        while (!queue.isEmpty()) {
            temp = queue.poll();

            if (temp == null) {
                if (queue.isEmpty()) {
                    break;
                }
                System.out.println(); // New Line
                queue.add(null);
            } else {
                while (temp != null) {
                    System.out.print(temp.data + "\t");
                    if (temp.left != null) {
                        queue.add(temp.left);
                    }
                    temp = temp.right;
                }
            }
        }
    }

    private static void setNodesAtParticularHorizontalDistance(Node root, Map<Integer, List<Integer>> nodesAtParticularHorizontalDistance, int horizontalDistance, Boolean isDiagonalTraversal) {
        if (root == null)
            return;
        List<Integer> nodesAtThisHorizontalDistance =
                nodesAtParticularHorizontalDistance.getOrDefault(horizontalDistance, new ArrayList<>());
        nodesAtThisHorizontalDistance.add(root.data);
        nodesAtParticularHorizontalDistance.put(horizontalDistance, nodesAtThisHorizontalDistance);
        setNodesAtParticularHorizontalDistance(root.left, nodesAtParticularHorizontalDistance, isDiagonalTraversal ? horizontalDistance + 1 : horizontalDistance - 1, isDiagonalTraversal);
        setNodesAtParticularHorizontalDistance(root.right, nodesAtParticularHorizontalDistance, isDiagonalTraversal ? horizontalDistance : horizontalDistance + 1, isDiagonalTraversal);
    }

    /**
     * https://www.geeksforgeeks.org/check-if-leaf-traversal-of-two-binary-trees-is-same/
     * Leaf traversal is sequence of leaves traversed from left to right. The problem is to check if leaf traversals of two
     * given Binary Trees are same or not.
     *
     * @param root1
     * @param root2
     * @return
     */
    public static Boolean checkIfLeafTraversalOfBinaryTreeIsSame(Node root1, Node root2) {
        Node head1 = root1;
        Node head2 = root2;
        Node prev = root1;

        connectLeafNodes(root1, head1, prev);
        prev.prev = null;
        connectLeafNodes(root2, head2, prev);

        head1 = head1.head;
        head2 = head2.head;

        // Now just compare both of them

        while (head1 != null && head2 != null) {
            if (head1.data != head2.data)
                return false;
            head1 = head1.right;
            head2 = head2.right;
        }

        if (head1 != null || head2 != null)
            return false;

        return true;
    }

    private static void connectLeafNodes(Node root, Node head, Node prev) {
        if (root == null) return;
        if (isLeafNode(root)) {
            if (prev.prev == null)
                head.head = root;
            else
                prev.prev.right = root;
            prev.prev = root;
        }
        connectLeafNodes(root.left, head, prev);
        connectLeafNodes(root.right, head, prev);
    }

    private static Boolean isLeafNode(Node root) {
        return root != null && root.left == null && root.right == null;
    }


    static Integer minimumHorizontalDistance = 0;
    static Integer maximumHorizontalDistance = 0;

    public static void getMinAndMaxHorizontalDistance(Node root, int horizontalDistance) {
        if (root == null)
            return;
        if (minimumHorizontalDistance > horizontalDistance)
            minimumHorizontalDistance = horizontalDistance;
        if (maximumHorizontalDistance < horizontalDistance)
            maximumHorizontalDistance = horizontalDistance;

        getMinAndMaxHorizontalDistance(root.left, horizontalDistance - 1);
        getMinAndMaxHorizontalDistance(root.right, horizontalDistance + 1);
    }

    public void printVerticalViewOfTree(Node root) {
        getMinAndMaxHorizontalDistance(root, 0);

        // Print Each nodes at same horizontal distance
        for (int i = minimumHorizontalDistance; i <= maximumHorizontalDistance; i++) {
            printNodesAtSameHorizontalDistance(root, i, 0);
            System.out.println();
        }
    }

    public void printNodesAtSameHorizontalDistance(Node root, Integer horizontalDistance, Integer currentHorizontalDistance) {
        if (root == null) return;

        if (horizontalDistance == currentHorizontalDistance) {
            System.out.print(root.data);
        }
        printNodesAtSameHorizontalDistance(root.left, horizontalDistance, currentHorizontalDistance - 1);
        printNodesAtSameHorizontalDistance(root.right, horizontalDistance, currentHorizontalDistance + 1);
    }

    private static void printBoundryLeft(Node root) {
        if (root != null) {
            if (root.left != null) {
                System.out.print(root.data + "\t");
                printBoundryLeft(root.left);
            } else if (root.right != null) {
                System.out.print(root.data + "\t");
                printBoundryRight(root.right);
            }
        }
    }

    private static void printBoundryRight(Node root) {
        if (root != null) {
            if (root.right != null) {
                printBoundryRight(root.right);
                System.out.print(root.data + "\t");
            } else if (root.left != null) {
                printBoundryLeft(root.left);
                System.out.print(root.data + "\t");
            }
        }
    }

    private static void printLeaf(Node root) {
        if (root != null) {
            printLeaf(root.left);

            if (isLeafNode(root)) {
                System.out.print(root.data + "\t");
            }

            printLeaf(root.right);
        }
    }

    public void boundryTraversal(Node root) {

        if (root != null) {

            System.out.print(root.data + "\t");
            // Step 1
            printBoundryLeft(root.left);

            // Step 2
            printLeaf(root.left);
            printLeaf(root.right);

            // Step 3
            printBoundryRight(root.right);
        }

    }

    private void topViewOfBinaryTree(Node root) {
        root.horizontalDistance = 0;
        Queue<Node> queue = new LinkedList<>();
        Set<Integer> visitedDistance = new HashSet<>();
        queue.add(root);
        Node temp;
        while (!queue.isEmpty()) {
            temp = queue.poll();

            if (!visitedDistance.contains(temp.horizontalDistance)) {
                visitedDistance.add(temp.horizontalDistance);
                System.out.print(temp.data + "\t");
            }

            if (temp.left != null) {
                Node left = temp.left;
                left.horizontalDistance = temp.horizontalDistance - 1;
                queue.add(left);
            }
            if (temp.right != null) {
                Node right = temp.right;
                right.horizontalDistance = temp.horizontalDistance + 1;
                queue.add(right);
            }
        }
    }

    private void printLeftView(Node root) {
        Queue<Node> queue = new LinkedList<>();
        Node temp;
        queue.add(root);
        queue.add(null);
        Boolean isFirstElementOfLevelPrinted = false;

        while (!queue.isEmpty()) {
            temp = queue.poll();

            if (temp == null) {
                if (queue.isEmpty())
                    return;

                isFirstElementOfLevelPrinted = false;
                queue.add(null);
            } else {
                if (!isFirstElementOfLevelPrinted) {
                    isFirstElementOfLevelPrinted = true;
                    System.out.print(temp.data + "\t");
                }
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
        }
    }


    public void printBottomView(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Map<Integer, Integer> visitedNodes = new TreeMap<>();
        Node temp;

        while (!queue.isEmpty()) {
            temp = queue.poll();

            visitedNodes.put(temp.horizontalDistance, temp.data);
            if (temp.left != null) {
                Node left = temp.left;
                left.horizontalDistance = temp.horizontalDistance - 1;
                queue.add(left);
            }
            if (temp.right != null) {
                Node right = temp.right;
                right.horizontalDistance = temp.horizontalDistance + 1;
                queue.add(right);
            }
        }

        System.out.println(visitedNodes);
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

    public static Node LCAOfBinarySearchTree(Node node, Integer n1, Integer n2) {

        if (node == null)
            return null;
        if (node.data > n1 && node.data > n2)
            return LCAOfBinarySearchTree(node.left, n1, n2);
        if (node.data < n1 && node.data < n2)
            return LCAOfBinarySearchTree(node.right, n1, n2);

        return node;
    }

    public static Boolean n1Found = false;
    public static Boolean n2Found = false;

    public static Node LCAOfBinaryTree(Node node, Integer n1, Integer n2) {
        if (node == null)
            return null;

        if (node.data == n1) {
            n1Found = true;
            return node;
        }
        if (node.data == n2) {
            n2Found = true;
            return node;
        }

        Node leftLCA = LCAOfBinaryTree(node.left, n1, n2);
        Node rightLCA = LCAOfBinaryTree(node.right, n1, n2);

        if (leftLCA != null && rightLCA != null)
            return node;

        return leftLCA == null ? rightLCA : leftLCA;
    }

    static Integer pathLen = -1;

    public static Boolean hasPath(Node root, int nodeForWhichPathIsPrinted, int[] path) {
        if (root == null)
            return false;

        path[++pathLen] = root.data;
        if (root.data == nodeForWhichPathIsPrinted)
            return true;

        if (hasPath(root.left, nodeForWhichPathIsPrinted, path) || hasPath(root.right, nodeForWhichPathIsPrinted, path))
            return true;

        --pathLen;
        return false;
    }

    public static void printPathToNode(Node root, int nodeForWhichPathIsPrinted) {
        int[] path = new int[100];
        pathLen = -1;
        if (hasPath(root, nodeForWhichPathIsPrinted, path)) {
            printPath(path, pathLen);
        }
    }

    public static void printPath(int[] path, int pathLen) {
        for (int i = 0; i <= pathLen; i++) {
            System.out.print(path[i] + " -> ");
        }
        System.out.println();
    }

    public void printAllPathsFromRootToLeafNode(Node root, int[] paths, int pathLen) {
        if (root == null)
            return;
        paths[++pathLen] = root.data;
        if (isLeafNode(root)) {
            printPath(paths, pathLen);
            pathLen--;
        }
        printAllPathsFromRootToLeafNode(root.left, paths, pathLen);
        printAllPathsFromRootToLeafNode(root.right, paths, pathLen);
    }

    private static int getDistanceFromRootToNode(Node root, int num) {
        pathLen = -1;
        int[] path = new int[100];
        int distance = -1;
        if (hasPath(root, num, path)) {
            distance = pathLen;
        }
        return distance;
    }

    /**
     * Distance from Root to N1 + Distance from Root to N2 - 2 * (Distance from Root to LCA)
     *
     * @param root
     * @param n1
     * @param n2
     */
    public int getDistanceBetweenTwoNodes(Node root, Integer n1, Integer n2) {
        int distanceFromRootToN1 = getDistanceFromRootToNode(root, n1);
        int distanceFromRootToN2 = getDistanceFromRootToNode(root, n2);

        Node lca = LCAOfBinaryTree(root, n1, n2);

        int distanceFromRootToLCA = getDistanceFromRootToNode(root, lca.data);

        return (distanceFromRootToN1) + (distanceFromRootToN2) - 2 * (distanceFromRootToLCA);
    }

    public static int getLevelOfNode(Node root, Integer node, Integer level) {
        if (root == null)
            return -1; // Level when there is no node

        if (root.data == node) {
            return level;
        }
        Integer leftLevel = getLevelOfNode(root.left, node, level + 1);
        if (leftLevel == -1) {
            return getLevelOfNode(root.right, node, level + 1);
        }
        return leftLevel;
    }

    public int getDistanceBetweenTwoNodesVersion2(Node root, Integer n1, Integer n2) {
        Node lca = LCAOfBinaryTree(root, n1, n2);

        Integer level1 = getLevelOfNode(lca, n1, 0);
        Integer level2 = getLevelOfNode(lca, n2, 0);
        return level1 + level2;
    }

    public Boolean isTreeHeightBalanced(Node root, HeightOfTree heightOfTree) {
        if (root == null) {
            heightOfTree.height = 0;
            return true; // Empty tree is always height balanced
        } else {
            HeightOfTree leftHeight = new HeightOfTree(0);
            HeightOfTree rightHeight = new HeightOfTree(0);

            Boolean isLeftBalanced = isTreeHeightBalanced(root.left, leftHeight);
            Boolean isRightBalanced = isTreeHeightBalanced(root.right, rightHeight);

            heightOfTree.height = Math.max(leftHeight.height, rightHeight.height) + 1;
            Boolean isDifferenceCorrect = Math.abs(rightHeight.height - leftHeight.height) <= 1;

            return isLeftBalanced && isRightBalanced && isDifferenceCorrect;
        }
    }

    public Boolean isTreeHeightBalanced(Node root) {
        if (root == null)
            return true;

        int leftHeight = getHeightOfTree(root.left);
        int rightHeight = getHeightOfTree(root.right);

        if (Math.abs(leftHeight - rightHeight) <= 1
                && isTreeHeightBalanced(root.left)
                && isTreeHeightBalanced(root.right))
            return true;
        return false;
    }

    static class HeightOfTree {
        int height;

        HeightOfTree() {

        }

        HeightOfTree(int height) {

        }
    }

    public int getDiameterOfTree(Node root, HeightOfTree heightOfTree) {
        HeightOfTree lh = new HeightOfTree();
        HeightOfTree rh = new HeightOfTree();

        if (root == null) {
            heightOfTree.height = 0;
            return 0;
        }

        int leftDiameter = getDiameterOfTree(root.left, lh);
        int rightDiameter = getDiameterOfTree(root.right, rh);

        heightOfTree.height = Math.max(lh.height, rh.height) + 1;

        return Math.max(lh.height + rh.height + 1, Math.max(leftDiameter, rightDiameter));
    }

    public boolean hasChildrenSumProperty(Node root) {
        if (root == null || isLeafNode(root))
            return true;

        int leftData = 0;
        int rightData = 0;
        if (root.left != null)
            leftData = root.left.data;
        if (root.right != null)
            rightData = root.right.data;

        return (leftData + rightData) == root.data && hasChildrenSumProperty(root.left) && hasChildrenSumProperty(root.right);
    }

    /**
     * This is the third step to convert tree into DLL, where we find out the left most node, and then traverse
     *
     * @param root
     */
    public void convertTreeToDLL(Node root) {
        Node DLL = convertTreeToDoublyLinkedList(root);

        while (DLL.left != null) {
            DLL = DLL.left;
        }

        System.out.println("Let's Traverse DLL converted from Tree ");
        while (DLL != null) {
            System.out.print(DLL.data + "\t");
            DLL = DLL.right;
        }
        System.out.println();
    }

    /**
     * These are the 1'st 2 steps to convert Tree into Doubly Linked List
     *
     * @param root
     * @return
     */
    public Node convertTreeToDoublyLinkedList(Node root) {
        if (root == null)
            return null;

        if (root.left != null) {
            //Recursively convert Tree to DLL
            Node left = convertTreeToDoublyLinkedList(root.left);

            // Find Inorder Predecessor
            for (; left.right != null; left = left.right) ;

            // Make Root the Next of Inorder Predecessor
            left.right = root;

            // Make Inorder Predecessor the previous of root
            root.left = left;

        }
        if (root.right != null) {
            // Recursively convert tree to DLL
            Node right = convertTreeToDoublyLinkedList(root.right);

            // Find Inorder Successor
            for (; right.left != null; right = right.left) ;

            // Make Inorder successor as the Next of Root
            root.right = right;

            // Make Root the previous of Inorder
            right.left = root;
        }
        return root;
    }


    static Node previous = null;
    static Node head = null;

    public static void convertTreeToDLLVersion2(Node root) {
        if (root == null)
            return;

        convertTreeToDLLVersion2(root.left);

        if (previous == null) {
            head = root;
        } else {
            root.left = previous;
            previous.right = root;
        }
        previous = root;
        convertTreeToDLLVersion2(root.right);
    }

    public static void convertTreeToDLLLevelOrderTraversalVersion(Node root) {
        if (root == null)
            return;

        Node head = null;
        Node prev = null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node polled = null;

        while (!queue.isEmpty()) {
            polled = queue.poll();
            Node curr = new Node(polled.data);
            if (prev == null)
                head = curr;
            else {
                prev.right = curr;
                curr.left = prev;
            }
            prev = curr;

            if (polled.left != null)
                queue.add(polled.left);
            if (polled.right != null)
                queue.add(polled.right);
        }

        printDoublyLinkedListConvertedFromTree(head);
    }


    public static void convertTreeIntoCircularDLL(Node root) {
        head = null;
        previous = null;
        convertTreeToDLLVersion2(root);

        head.prev = previous;
        previous.right = head;

        printCircularDoublyLinkedListConvertedFromTree();
    }

    public static void populateInorderSuccessorUtil(Node root) {
        previous = null; // Initial data setup
        populateInorderSuccessor(root);

        System.out.println("Let's print the next value ");
        root = root.left.left;
        while (root != null) {
            System.out.print("Next of " + root.data + " is ");
            if (root.next != null) {
                System.out.println(root.next.data);
            } else {
                System.out.println(-1);
            }
            root = root.next;
        }
    }

    public static void populateInorderSuccessor(Node root) {
        if (root == null) {
            return;
        }
        populateInorderSuccessor(root.left);
        if (previous != null) {
            previous.next = root;
        }
        previous = root;
        populateInorderSuccessor(root.right);
    }

    static Stack<Integer> stackForPrintPathForAGivenSum = new Stack<>();
    static int sum = 0;

    public static void printPathForAGivenSum(Node root, int k) {
        if (root == null)
            return;
        sum += root.data;
        stackForPrintPathForAGivenSum.push(root.data);
        if (sum == k) {
            System.out.println(stackForPrintPathForAGivenSum);
        }
        printPathForAGivenSum(root.left, k);
        printPathForAGivenSum(root.right, k);

        stackForPrintPathForAGivenSum.pop();
        sum -= root.data;
    }

    public static int maxTillNow = 0;

    public static void addAllGreaterNodesToBinaryTree(Node root) {
        if (root == null)
            return;
        addAllGreaterNodesToBinaryTree(root.right);
        root.data += maxTillNow;
        maxTillNow = root.data;
        addAllGreaterNodesToBinaryTree(root.left);
    }

    private static Integer maximumPathSum = 0;

    public void printMaximumPathSumBetween2Leaves(Node root) {
        maximumPathSum = 0;
        getMaximumSumFromRootToLeafInBinaryTree(root);

        System.out.println(" Maximum Path Sum between 2 leaves is " + maximumPathSum);
    }

    public int getMaximumSumFromRootToLeafInBinaryTree(Node root) {
        if (root == null)
            return 0;

        if (isLeafNode(root))
            return root.data;

        int leftSum = getMaximumSumFromRootToLeafInBinaryTree(root.left);
        int rightSum = getMaximumSumFromRootToLeafInBinaryTree(root.right);

        // If Left and Right subtree is not empty
        // Then maximumSumFromRootToLeaf can be either left sum + root.data OR rightSum + root.data
        // i.e Math.max(leftSum, rightSum) + root.data
        if (root.left != null && root.right != null) {

            // THis line is used for printing the Maximum Path Sum between 2 leaves
            maximumPathSum = Math.max(maximumPathSum, leftSum + rightSum + root.data);

            return Math.max(leftSum, rightSum) + root.data;
        }

        return root.left != null ? leftSum + root.data : rightSum + root.data;
    }


    public void findMaximumPathSumBetweenAnyNode(Node root) {
        maximumPathSum = 0;
        maximumPathSumBetweenAnyNodeUtil(root);

        System.out.println(" Maximum Path Sum between 2 nodes is " + maximumPathSum);
    }

    public int maximumPathSumBetweenAnyNodeUtil(Node root) {
        if (root == null)
            return 0;

        // l and r store maximum path sum going through left and
        // right child of root respectively
        int leftSum = maximumPathSumBetweenAnyNodeUtil(root.left);
        int rightSum = maximumPathSumBetweenAnyNodeUtil(root.right);

        //For each node there can be four ways that the max path goes through the node:
        //1. Node only
        //2. Max path through Left Child + Node
        //3. Max path through Right Child + Node
        //4. Max path through Left Child + Node + Max path through Right Child
        maximumPathSum = Math.max(maximumPathSum, root.data);
        maximumPathSum = Math.max(maximumPathSum, root.data + leftSum);
        maximumPathSum = Math.max(maximumPathSum, root.data + rightSum);
        maximumPathSum = Math.max(maximumPathSum, root.data + rightSum + leftSum);
        return Math.max(root.data, Math.max(leftSum, rightSum) + root.data);
    }

    /**
     * A complete binary tree is a binary tree in which every level, except possibly the last, is completely
     * filled, and all nodes are as far left as possible.
     * 1
     * / \
     * 2   3
     * <p>
     * 1
     * / \
     * 2   3
     * /
     * 4
     * <p>
     * the easiest way seems to be a breadth first (left to right) traversal of the tree.
     * At each node push both left and right onto the list be traversed (even if they are NULL).
     * After you hit the first NULL there should only be NULL objects left to find.
     * If you find a non NULL object after this it is not a complete tree.
     *
     * @param root
     * @return
     */
    private static boolean isCompleteTree(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node dequed = queue.poll();

            if (dequed == null)
                break;
            queue.add(dequed.left);
            queue.add(dequed.right);
        }

        while (!queue.isEmpty()) {
            if (queue.poll() != null) {
                return false;
            }
        }
        return true;
    }

    public static boolean isCompleteTreeRecursively(Node root) {
        int count = countNodesInTree(root);
        return isCompleteTreeUtil(root, 0, count);
    }

    public static int countNodesInTree(Node root) {
        if (root == null)
            return 0;

        return 1 + countNodesInTree(root.left) + countNodesInTree(root.right);
    }

    public static boolean isCompleteTreeUtil(Node root, int index, int count) {
        if (root == null)
            return true;

        if (index >= count)
            return false;

        return isCompleteTreeUtil(root.left, 2 * index + 1, count) &&
                isCompleteTreeUtil(root.right, 2 * index + 2, count);
    }


    /**
     * Simply do a Level Order Traversal and while doing that make sure that children to be enqued in the queue
     * is smaller than the parent.
     * <p>
     * Also once a null is encountered into the queue, after that any non null will result into
     * not a complete tree
     * <p>
     * if both property satisfies then it's a MIN_HEAP
     *
     * @param root
     * @return
     */
    public static boolean binaryTreeIsMaxHeap(Node root) {
        Queue<Node> queue = new LinkedList();
        queue.add(root);
        Boolean nullInserted = false;

        while (!queue.isEmpty()) {
            Node dequed = queue.poll();

            if (nullInserted && dequed != null) {
                System.out.println("NOT A MIN_HEAP due to Complete Tree Property Failure");
                return false;
            }

            if (!nullInserted) {
                if (dequed == null) {
                    nullInserted = true;
                    continue;
                }
                // if Queue has left child
                if (dequed.left != null) {
                    if (dequed.left.data > dequed.data) {
                        System.out.println("MIN_HEAP LEFT CHILD GREATER THAN ROOT PROPERTY NOT SATISFIED");
                        return false;
                    } else {
                        queue.add(dequed.left);
                    }
                } else {
                    queue.add(dequed.left);
                }

                // if Queue has left child
                if (dequed.right != null) {
                    if (dequed.right.data > dequed.data) {
                        System.out.println("MIN_HEAP RIGHT CHILD GREATER THAN ROOT PROPERTY NOT SATISFIED");
                        return false;
                    } else {
                        queue.add(dequed.right);
                    }
                } else {
                    queue.add(dequed.right);
                }
            }
        }
        return true;
    }
}
