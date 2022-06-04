package com.datastructures.tree.binaryTree;

import com.datastructures.linkedlist.DoublyCircularLinkedList;

import java.util.*;

/**
 * Created by jaine03 on 05/07/17.
 */
public class BinaryTreeUtil {

    public Node root = null;

    public static class Node {
        int data;
        Node left;
        Node right;
        Node rightNext;

        Node(int data) {
            this.data = data;
        }

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public void inOrder(Node root) {
        if (root == null)
            return;

        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    public List<Node> inorderNodes = new ArrayList<>();

    public void setInOrder(Node root) {
        if (root == null)
            return;

        inOrder(root.left);
        inorderNodes.add(root);
        inOrder(root.right);
    }

    public void preOrder(Node root) {
        if (root == null)
            return;

        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public void postOrder(Node root) {
        if (root == null)
            return;

        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");

        Set<String> values = new HashSet<String>();
    }

    public int getTreeHeight(Node root) {
        if (root == null)
            return 0;

        int lheight = getTreeHeight(root.left);
        int rheight = getTreeHeight(root.right);

        if (lheight > rheight)
            return lheight + 1;
        else
            return rheight + 1;
    }

    public void levelOrderTraversal(Node root) {
        int heightOfTree = getTreeHeight(root);
        List<Integer> maxWidthOfTree = new ArrayList<>();
        for (int i = 0; i <= heightOfTree; i++) {
            traverseLevel(root, i, 0);
            //traverseLevelAsGeeksAsSpiral(root, i + 1);
            maxWidthOfTree.add(maxWidth);
            maxWidth = 0;
            System.out.println("\n===================");
        }
        System.out.println(maxWidthOfTree);
    }

    private static Integer maxWidth = 0;

    private void traverseLevel(Node root, int level, int counter) {
        if (root == null)
            return;

        traverseLevel(root.left, level, counter + 1);
        if (level == counter) {
            System.out.print(root.data + " ");
            maxWidth++;
        }
        traverseLevel(root.right, level, counter + 1);
    }

    private void traverseLevelAsGeeksAsSpiral(Node root, int level) {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.data + " ");
        if (level % 2 == 0) {
            traverseLevelAsGeeksAsSpiral(root.left, level - 1);
            traverseLevelAsGeeksAsSpiral(root.right, level - 1);
        } else {
            traverseLevelAsGeeksAsSpiral(root.right, level - 1);
            traverseLevelAsGeeksAsSpiral(root.left, level - 1);
        }
    }

    public void levelOrderTraversalWithQueue(Node root) {
        Node tempNode = root;
        Queue<Node> queue = new LinkedList<>();
        while (tempNode != null) {
            System.out.print(tempNode.data + " ");
            if (tempNode.left != null)
                queue.add(tempNode.left);
            if (tempNode.right != null)
                queue.add(tempNode.right);

            tempNode = queue.poll();
            System.out.println();
        }
    }

    public void levelOrderTraversalWithQueueInSeparateLine(Node root) {
        Node tempNode = null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while (!queue.isEmpty()) {

            tempNode = queue.poll();
            if (tempNode == null) {
                if (!queue.isEmpty())
                    queue.add(null);
                System.out.println();
                continue;
            }

            System.out.print(tempNode.data + "\t");

            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }

            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }

    public int getTreeSize(Node root) {
        if (root == null)
            return 0;

        int leftSize = getTreeSize(root.left);
        int rightSize = getTreeSize(root.right);

        return leftSize + 1 + rightSize;
    }

    public void iterativeInorder(Node root) {
        Stack<Node> stack = new Stack<>();
        Node curr = root;

        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }

        while (!stack.isEmpty()) {
            Node temp = stack.pop();
            System.out.println(temp.data);
            if (temp.right != null) {
                temp = temp.right;

                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            }
        }
    }

    public void morrisTraversal(Node root) {
        Node curr = root;
        Node leftOfCurr;
        Node prevNodeInInorder = null;

        while (curr != null) {
            if (curr.left == null) {
                System.out.println(curr.data);
                if (prevNodeInInorder != null) {
                    System.out.println("PrevNode in Inorder is " + prevNodeInInorder);
                }
                prevNodeInInorder = curr;
                curr = curr.right;
            } else {
                //Inorder predecessor of current.
                leftOfCurr = curr.left;

                while (leftOfCurr.right != null && leftOfCurr.right != curr) {
                    leftOfCurr = leftOfCurr.right;
                }

                if (leftOfCurr.right == null) {
                    leftOfCurr.right = curr;
                    curr = curr.left;
                } else { //fix the right child of predecessor
                    leftOfCurr.right = null;
                    if (prevNodeInInorder != null) {
                        System.out.println("PrevNode in Inorder is " + prevNodeInInorder);
                    }
                    prevNodeInInorder = curr;
                    System.out.println(curr.data);
                    curr = curr.right;
                }
            }
        }
    }

    public Node Mirror(Node root) {
        if (root == null) {
            return root;
        }
        Node leftSubtree = Mirror(root.left);
        Node rightSubtree = Mirror(root.right);

        root.right = leftSubtree;
        root.left = rightSubtree;
        return root;
    }

    public boolean isLeafNode(Node root) {
        return root != null && root.left == null && root.right == null ? true : false;
    }

    public void printAllPaths(Node root, int[] path, int pathLen) {
        if (root == null) {
            return;
        }
        path[++pathLen] = root.data;

        if (isLeafNode(root)) {
            printPath(path, pathLen);
            pathLen--;
        }

        printAllPaths(root.left, path, pathLen);
        printAllPaths(root.right, path, pathLen);
    }

    public List<List<Integer>> allPaths = new ArrayList<>();

    public void getPathToLeafNode(Node root, int[] path, int pathLen) {
        if (root == null) {
            return;
        }
        path[++pathLen] = root.data;
        if (isLeafNode(root)) {
            allPaths.add(getPathAsList(path, pathLen));
            pathLen--;
        }

        getPathToLeafNode(root.left, path, pathLen);
        getPathToLeafNode(root.right, path, pathLen);
    }

    public List<Integer> getPathAsList(int[] path, int pathLen) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= pathLen; i++) {
            list.add(path[i]);
        }
        return list;
    }

    public int getDiameterOfTree(Node root) {
        if (root == null)
            return 0;

        int leftSubtreeHeight = getTreeHeight(root.left);
        int rightSubtreeHeight = getTreeHeight(root.right);

        int leftSubtreeDiameter = getDiameterOfTree(root.left);
        int rightSubtreeDiameter = getDiameterOfTree(root.right);

        return (Math.max(leftSubtreeHeight + rightSubtreeHeight + 1, Math.max(leftSubtreeDiameter, rightSubtreeDiameter)));
    }

    public void printPath(int[] path, int pathLen) {
        int sum = 0;
        for (int i = 0; i <= pathLen; i++) {
            System.out.print(path[i] + " --> ");
            sum += path[i];
        }
        System.out.println();
//        System.out.println(sum);
    }

    public void convertBinaryTreeIntoCircularDoublyLinkedList(Node root) {
        DoublyCircularLinkedList list = new DoublyCircularLinkedList();
        convertBinaryTreeIntoCircularDoublyLinkedList(root, list);
        list.traverseList();

    }

    private void convertBinaryTreeIntoCircularDoublyLinkedList(Node root, DoublyCircularLinkedList list) {
        if (root == null) {
            return;
        }

        convertBinaryTreeIntoCircularDoublyLinkedList(root.left, list);
        list.append(new DoublyCircularLinkedList.Node(root.data));
        System.out.println(root.data);
        convertBinaryTreeIntoCircularDoublyLinkedList(root.right, list);
    }

    static Node head;
    static Node prev;

    public void inplaceBinaryTreeToCircularDLL(Node root) {
        if (root == null)
            return;

        inplaceBinaryTreeToCircularDLL(root.left);

        if (head == null) {
            head = root;
            prev = root;
        } else {
            prev.right = root;
            root.left = prev;
            prev = root;
        }
        inplaceBinaryTreeToCircularDLL(root.right);
    }

    public void traverseInorder() {
        Node curr = head;
        while (curr.right != head) {
            System.out.println(curr.data);
            curr = curr.right;
        }
    }

    /**
     * Do a reverse Inorder Traversal and keep an eye on the Max Sum.
     * Add to the root if it's not null
     *
     * @param root
     * @return
     */
    private static int maxSum = 0;

    public void addAllGreaterValuesToAllNode(Node root) {
        if (root == null) {
            return;
        }
        addAllGreaterValuesToAllNode(root.right);
        root.data += maxSum;
        maxSum = root.data;
        addAllGreaterValuesToAllNode(root.left);
    }

    public static Boolean isChildrenSumProperty = true;

    public void childrenSumProperty(Node root) {
        if (root == null) {
            return;
        } else {
            if (!isLeafNode(root)) {
                if (root.data != (root.left != null ? root.left.data : 0) + (root.right != null ? root.right.data : 0)) {
                    isChildrenSumProperty = false;
                    return;
                }
            }
        }
        childrenSumProperty(root.left);
        childrenSumProperty(root.right);
    }


    public Boolean isTreeHeightBalanced(Node root) {
        if (root == null) {
            return true;
        }

        int leftHeight = getTreeHeight(root.left);
        int rightHeight = getTreeHeight(root.right);

        return Math.abs(leftHeight - rightHeight) <= 1 && isTreeHeightBalanced(root.left) && isTreeHeightBalanced(root.right);
    }

    public Boolean printAncestor(Node root, int ancestorForWhich) {

        if (root == null) {
            return false;
        }

        if (root.data == ancestorForWhich) {
            return true;
        }

        if (printAncestor(root.left, ancestorForWhich) || printAncestor(root.right, ancestorForWhich)) {
            System.out.println(root.data);
            return true;
        } else {
            return false;
        }
    }

    public static int preIndex = 0;

    public Node buildBinaryTreeFromInorderAndPreorder(int[] preOrder, int[] inorder, int inStart, int endIndex) {

        if (inStart > endIndex)
            return null;

        Node tempNode = new Node(preOrder[preIndex++]);

        // If Leaf Node
        if (inStart == endIndex) {
            return tempNode;
        }

        int searchIndex = searchInorder(inorder, inStart, endIndex, tempNode.data);

        tempNode.left = buildBinaryTreeFromInorderAndPreorder(preOrder, inorder, inStart, searchIndex - 1);
        tempNode.right = buildBinaryTreeFromInorderAndPreorder(preOrder, inorder, searchIndex + 1, endIndex);

        return tempNode;
    }

    public int searchInorder(int[] inOrder, int inIndex, int endIndex, int data) {
        for (int i = inIndex; i <= endIndex; i++) {
            if (inOrder[i] == data) {
                return i;
            }
        }
        return -1;
    }

    public Boolean rootToLeafPathExistForSum(Node root, int sum) {
        if (root == null)
            return false;
        if (isLeafNode(root)) {
            return root.data == sum;
        }
        if (rootToLeafPathExistForSum(root.left, sum - root.data)) {
            return true;
        }
        if (rootToLeafPathExistForSum(root.right, sum - root.data)) {
            return true;
        }
        return false;
    }

    public Node lowestCommonAncestor(Node root, int a, int b) {
        if (root == null)
            return null;

        if (root.data == a || root.data == b) {
            return root;
        }

        Node leftLCA = lowestCommonAncestor(root.left, a, b);
        Node rightLCA = lowestCommonAncestor(root.right, a, b);

        if (leftLCA != null && rightLCA != null)
            return root;

        return leftLCA != null ? leftLCA : rightLCA;
    }

    public Boolean hasOnlyOneChild(Node root) {
        if (!isLeafNode(root)) {
            if (root.left != null && root.right != null) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public Node deleteFromBST(Node root, int dataToBeDeleted) {
        if (root == null)
            return null;

        if (root.data == dataToBeDeleted) {

            if (isLeafNode(root)) {
                return null;
            } else if (hasOnlyOneChild(root)) {
                if (root.right != null) {
                    root = root.right;
                } else {
                    root = root.left;
                }
                return root;
            } else {
                setInOrder(root);
                int indexOfNodeToBeDeleted = inorderNodes.indexOf(root);
                if (inorderNodes.size() - indexOfNodeToBeDeleted > 0)
                    root = inorderNodes.get(indexOfNodeToBeDeleted + 1);
                return root;
            }
        }

        root.left = deleteFromBST(root.left, dataToBeDeleted);
        root.right = deleteFromBST(root.right, dataToBeDeleted);

        return root;
    }

    public void spiralTraversal(Node root) {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        stack1.push(root);

        while (!stack1.isEmpty() || !stack2.isEmpty()) {

            while (!stack1.isEmpty()) {
                Node temp = stack1.pop();
                System.out.println(temp.data);

                if (temp.right != null)
                    stack2.push(temp.right);
                if (temp.left != null)
                    stack2.push(temp.left);
            }

            while (!stack2.isEmpty()) {
                Node temp = stack2.pop();
                System.out.println(temp.data);

                if (temp.left != null)
                    stack1.push(temp.left);
                if (temp.right != null)
                    stack1.push(temp.right);
            }
        }
    }


    /**
     * Level Order traversal with printing only the left most element in every level
     *
     * @param root
     */
    public void printLeftView(Node root) {
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        nodes.add(null);
        Boolean firstNodePrinted = false;
        while (!nodes.isEmpty()) {

            if (nodes.peek() == null) {
                nodes.poll();
                if (!nodes.isEmpty()) {
                    nodes.add(null);
                    firstNodePrinted = false;
                }
                continue;
            }
            Node temp = nodes.poll();
            if (temp.left != null) {
                nodes.add(temp.left);
            }
            if (temp.right != null) {
                nodes.add(temp.right);
            }
            if (!firstNodePrinted) {
                System.out.println(temp.data);
                firstNodePrinted = true;
            }
        }
    }

    public Boolean isBST(Node root) {
        Stack<Node> stack = new Stack<>();
        Integer prevValue = Integer.MIN_VALUE;
        Node curr = root;
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
        while (!stack.isEmpty()) {
            Node temp = stack.pop();
            if (temp.data > prevValue)
                prevValue = temp.data;
            else
                return false;
            if (temp.right != null)
                stack.push(temp.right);
        }
        return true;
    }

    /************************************** Vertical ORDER in O(Nˆ2) complexity ******************************/
    /**
     * http://www.geeksforgeeks.org/print-binary-tree-vertical-order/
     */
    public static class HorizontalDistance {
        int min;
        int max;
    }

    public static void findAndSetMinAndMaxHorizontalDistance(Node root, HorizontalDistance distance, int horizontalDistance) {
        if (root == null)
            return;

        if (distance.min > horizontalDistance) {
            distance.min = horizontalDistance;
        } else if (distance.max < horizontalDistance) {
            distance.max = horizontalDistance;
        }

        findAndSetMinAndMaxHorizontalDistance(root.left, distance, horizontalDistance - 1);
        findAndSetMinAndMaxHorizontalDistance(root.right, distance, horizontalDistance + 1);
    }

    public static void printVerticalLines(Node root, int lineNo, int horizontalDistance, boolean isCurrentHorizontalDistancePrinted) {
        if (root == null)
            return;
        if (lineNo == horizontalDistance && !isCurrentHorizontalDistancePrinted) {
            isCurrentHorizontalDistancePrinted = true;
            System.out.print(root.data + " ");
        }

        printVerticalLines(root.left, lineNo, horizontalDistance - 1, isCurrentHorizontalDistancePrinted);
        printVerticalLines(root.right, lineNo, horizontalDistance + 1, isCurrentHorizontalDistancePrinted);
    }

    public static void printVerticalOrder(Node root) {
        HorizontalDistance distance = new HorizontalDistance();
        findAndSetMinAndMaxHorizontalDistance(root, distance, 0);
        boolean isCurrentHorizontalDistancePrinted = false;
        for (int i = distance.min; i <= distance.max; i++) {
            isCurrentHorizontalDistancePrinted = false;
            printVerticalLines(root, i, 0, isCurrentHorizontalDistancePrinted);
            System.out.println();
        }
    }

    /************************************** Vertical ORDER in O(Nˆ2) complexity [ENDS] ******************************/


    /************************************** Vertical ORDER in O(N) complexity ******************************/

    public static void setNodesAtHorizontalDistance(Node root, HashMap<Integer, List<Integer>> allNodesAtParticularHorizontalDistance, int horizontalDistance) {
        if (root == null)
            return;
        if (allNodesAtParticularHorizontalDistance.containsKey(horizontalDistance)) {

            List<Integer> existingValues = allNodesAtParticularHorizontalDistance.get(horizontalDistance);
            existingValues.add(root.data);
        } else {
            allNodesAtParticularHorizontalDistance.put(horizontalDistance, new ArrayList<>(Arrays.asList(root.data)));
        }

        setNodesAtHorizontalDistance(root.left, allNodesAtParticularHorizontalDistance, horizontalDistance - 1);
        setNodesAtHorizontalDistance(root.right, allNodesAtParticularHorizontalDistance, horizontalDistance + 1);
    }

    public static void verticalOrderTraversal(Node root) {
        HashMap<Integer, List<Integer>> allNodesAtParticularHorizontalDistance = new HashMap<>();
        setNodesAtHorizontalDistance(root, allNodesAtParticularHorizontalDistance, 0);
        System.out.println(allNodesAtParticularHorizontalDistance);
    }


    /************************************** Vertical ORDER in O(N) complexity [ENDS] ******************************/

    /************************************** Diagonal ORDER in O(N) complexity ******************************/

    public static void setNodesAtDiagonalDistance(Node root, HashMap<Integer, List<Integer>> allNodesAtParticularHorizontalDistance, int horizontalDistance) {
        if (root == null)
            return;
        if (allNodesAtParticularHorizontalDistance.containsKey(horizontalDistance)) {

            List<Integer> existingValues = allNodesAtParticularHorizontalDistance.get(horizontalDistance);
            existingValues.add(root.data);
        } else {
            allNodesAtParticularHorizontalDistance.put(horizontalDistance, new ArrayList<>(Arrays.asList(root.data)));
        }

        setNodesAtDiagonalDistance(root.left, allNodesAtParticularHorizontalDistance, horizontalDistance + 1);

        // We do not increase horizontal distance on the right because on right all nodes will be at the same horizontal level at root
        setNodesAtDiagonalDistance(root.right, allNodesAtParticularHorizontalDistance, horizontalDistance);
    }

    public static void diagonalOrderTraversal(Node root) {
        HashMap<Integer, List<Integer>> allNodesAtParticularHorizontalDistance = new HashMap<>();
        setNodesAtDiagonalDistance(root, allNodesAtParticularHorizontalDistance, 0);
        System.out.println(allNodesAtParticularHorizontalDistance);
    }


    /************************************** Top View  ORDER in O(N) complexity ******************************/
    public class TopViewNode {
        Node node;
        int horizontalDistance;

        TopViewNode(Node node, int horizontalDistance) {
            this.node = node;
            this.horizontalDistance = horizontalDistance;
        }
    }

    public void printTopView(Node root) {
        Queue<TopViewNode> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.add(new TopViewNode(root, 0));
        while (!queue.isEmpty()) {
            TopViewNode temp = queue.poll();

            if (!set.contains(temp.horizontalDistance)) {
                set.add(temp.horizontalDistance);
                System.out.println(temp.node.data);
            }
            if (temp.node.left != null)
                queue.add(new TopViewNode(temp.node.left, temp.horizontalDistance - 1));
            if (temp.node.right != null)
                queue.add(new TopViewNode(temp.node.right, temp.horizontalDistance + 1));

        }
    }

    /************************************** Top View  ORDER in O(N) complexity [ENDS] ******************************/


    /************************************** Bottom View  ORDER in O(N) complexity ***********************************/

    public void printBottomView(Node root) {
        Queue<TopViewNode> queue = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int hd = 0;

        queue.add(new TopViewNode(root, hd));

        while (!queue.isEmpty()) {
            TopViewNode temp = queue.poll();

            map.put(temp.horizontalDistance, temp.node.data);

            if (temp.node.left != null) {
                queue.add(new TopViewNode(temp.node.left, temp.horizontalDistance - 1));
            }
            if (temp.node.right != null) {
                queue.add(new TopViewNode(temp.node.right, temp.horizontalDistance + 1));
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    /************************************** Bottom View  ORDER in O(N) complexity [ENDS] ******************************/


    public void connectNodesAtSameLevel(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        Node prev = null;
        while (!queue.isEmpty()) {

            Node temp = queue.poll();
            if (temp == null) {
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
                prev = null;
                continue;
            }
            System.out.println(temp.data);
            if (prev == null) {
                prev = temp;
            } else {
                prev.rightNext = temp;
                prev = temp;
            }

            if (temp.left != null)
                queue.add(temp.left);
            if (temp.right != null)
                queue.add(temp.right);
        }
        System.out.println("Not done.......");
    }


    public void printAllPathFromRootToLeaf(Node root, int[] path, int pathLen) {
        if (root == null)
            return;

        if (isLeafNode(root)) {
            ++pathLen;
            path[pathLen] = root.data;
            printPath(path, pathLen);
            pathLen--;
        } else {
            path[++pathLen] = root.data;
        }

        printAllPathFromRootToLeaf(root.left, path, pathLen);
        printAllPathFromRootToLeaf(root.right, path, pathLen);
    }

    public BinaryTreeUtil getConnectNodesAtSameLevelSampleData() {
        BinaryTreeUtil tree = new BinaryTreeUtil();
        tree.root = new Node(10);
        tree.root.left = new Node(8);
        tree.root.right = new Node(2);
        tree.root.left.left = new Node(3);

        return tree;
    }

    public Node getBSTData() {
        Node root = new Node(100);
        root.left = new Node(50);
        root.left.left = new Node(30);
        root.left.left.right = new Node(40);
        root.left.left.left = new Node(20);
        root.left.right = new Node(80);
        root.right = new Node(200);

        return root;
    }

    public BinaryTreeUtil getVerticalOrderSampleData() {
        BinaryTreeUtil tree = new BinaryTreeUtil();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);
        tree.root.right.left.right = new Node(8);
        tree.root.right.right.right = new Node(9);

        return tree;
    }

    public Node getSampleData() {
//        Node root = new Node(1);
//        root.left = new Node(2);
//        root.right = new Node(3);
//        //root.right.right = new Node(8);
//        //root.right.right.left = new Node(9);
//        //root.right.right.right = new Node(10);
//        root.left.left = new Node(4);
//        //root.left.left.left = new Node(9);
//        root.left.right = new Node(5);
//        root.right.left = new Node(6);
//        root.right.right = new Node(7);
//        root.left.left.right = new Node(8);
//        root.right.left = new Node(6);
//        root.right.right = new Node(7);

//        Node root = new Node(50);
//        root.left = new Node(30);
//        root.right = new Node(70);
//        root.left.left = new Node(20);
//        root.left.right = new Node(40);
//        root.right.left = new Node(60);
//        root.right.right = new Node(80);

//        Node root = new Node(10);
//        root.left = new Node(8);
//        root.right = new Node(2);
//        root.left.left = new Node(3);
//        root.left.right = new Node(5);
//        root.right.left = new Node(2);

//        Node root = new Node(10);
//        root.left = new Node(-2);
//        root.right = new Node(7);
//        root.left.left = new Node(8);
//        root.left.right = new Node(-4);

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.left = new Node(6);
        root.right.right = new Node(7);

        return root;
    }

}
