package com.leetcode.tree;

import com.leetcode.problems.medium.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import static com.util.LogUtil.logIt;
import static com.util.LogUtil.newLine;
import static com.util.LogUtil.printList;

/**
 * @author neeraj on 2019-06-22
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class BinarySearchTreeUtil {

    Node root;

    class Node {
        Node left;
        Node right;
        int data;

        Node(int data) {
            this.data = data;
        }
    }

    Node insertDataIntoBST(Node root, List<Integer> data) {
        logIt("Inserting List of data in BST ", true);
        printList(data);

        for (int i = 0; i < data.size(); i++) {
            root = insertIntoBSTWithoutRecursion(root, data.get(i));
        }
        return root;
    }

    private Node insertIntoBST(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }

        if (root.data > data) {
            root.left = insertIntoBST(root.left, data);
        } else {
            root.right = insertIntoBST(root.right, data);
        }
        return root;
    }

    private Node insertIntoBSTWithoutRecursion(Node root, int data) {
        if (root == null)
            return new Node(data);
        Node current = root;

        while (true) {
            if (current.data > data) {
                if (current.left == null) {
                    current.left = new Node(data);
                    break;
                } else {
                    current = current.left;
                }
            } else {
                if (current.right == null) {
                    current.right = new Node(data);
                    break;
                } else {
                    current = current.right;
                }
            }
        }
        return root;
    }

    public void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " , ");
            inorder(root.right);
        }
    }

    public Node searchDataInBST(Node root, int data) {
        logIt("Searching " + data + " in BST ");

        Node searchedNode = searchData(root, data);
        if (searchedNode != null) {
            logIt("Data found", true);
        } else {
            logIt("Data not available in BST", true);
        }
        return searchedNode;
    }

    private Node searchData(Node root, int data) {
        if (root == null) {
            return null;
        }
        if (root.data == data) {
            return root;
        }
        if (root.data > data) {
            return searchData(root.left, data);
        } else {
            return searchData(root.right, data);
        }
    }

    public void deleteNodeFromBST(Node root, int data) {
        logIt("Deleting Node from BST" + data, true);
        Node treeWithNodeDeleted = deleteNode(root, data);

        logIt("After deletion traversal is ");
        inorder(treeWithNodeDeleted);
        newLine();
    }

    public Node deleteNode(Node root, int data) {
        if (root == null) {
            return null;
        }
        if (root.data > data) {
            root.left = deleteNode(root.left, data);
        } else if (root.data < data) {
            root.right = deleteNode(root.right, data);
        } else { // Data is on the root
            // Now we have to figure out how many child this node has.

            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else { // This node has 2 children.

                Node inorderSuccessor = findInorderSuccessor(root);
                root.data = inorderSuccessor.data;

                deleteNode(root.right, inorderSuccessor.data);
            }
        }
        return root;
    }

    private Node findInorderSuccessor(Node root) {
        root = root.right;
        while (root != null && root.left != null) {
            root = root.left;
        }
        return root;
    }

    public Node constructBSTFromPreOrderTraversalRecursively(int[] preOrderTraversal) {
        if (preOrderTraversal.length < 1) {
            return null;
        }

        Node root = new Node(preOrderTraversal[0]);

        int indexOfNextHigherNode = 1;
        for (; indexOfNextHigherNode < preOrderTraversal.length; indexOfNextHigherNode++) {
            if (preOrderTraversal[indexOfNextHigherNode] > root.data) {
                break;
            }
        }

        root.left = constructBSTFromPreOrderTraversalRecursively(Arrays.copyOfRange(preOrderTraversal, 1, indexOfNextHigherNode));
        root.right = constructBSTFromPreOrderTraversalRecursively(Arrays.copyOfRange(preOrderTraversal, indexOfNextHigherNode, preOrderTraversal.length));

        return root;
    }

    static int preIndex = 0;

    public static TreeNode bstFromPreorder(int[] preorder) {
        // In a BST we know for every root Node all the nodes in the left
        // should have max value of the root
        //  and similarly all the nodes in the right should be greater than the rootNode
        return bstFromPreOrder(preorder, Integer.MAX_VALUE);
    }

    private static TreeNode bstFromPreOrder(int[] preorder, int upperBound) {
        // why not checking lowerBound for right nodes
        // because the given sequence is guaranteed to be valid, we don't need to validate if it s a binary search tree or not. We just need to test again parent value to decide a node should be left or right child.
        if (preIndex == preorder.length || preorder[preIndex] > upperBound) return null;
        TreeNode root = new TreeNode(preorder[preIndex++]);
        root.left = bstFromPreOrder(preorder, root.val); // all in left should be less than root
        root.right = bstFromPreOrder(preorder, upperBound);
        return root;
    }

    public void constructBSTFromPreOrderIteratively(int[] preOrder) {
        // Stack will be used for construction
        Node root = new Node(preOrder[0]);
        Stack<Node> stack = new Stack<>();
        Node temp = null;

        stack.push(root);

        // Since root node is already present at 0th index, so we start traversal from nextNode
        for (int i = 1; i < preOrder.length; i++) {

            // Pop Out the item until the top of the stack is greater than the preOrder at index[i] or the stack is empty.
            while (!stack.isEmpty() && stack.peek().data < preOrder[i]) {
                temp = stack.pop();
            }

            // We found some elements which are smaller than the preOrder[i], hence making preOrder[i] as the right child
            if (temp != null) {
                Node newNode = new Node(preOrder[i]);
                temp.right = newNode;
                stack.push(newNode);
                temp = null;
            } else { // We did not find any element in the stack which is smaller than the preOrder[i], hence make this newNode as the left chid of the topNode.
                Node newNode = new Node(preOrder[i]);
                stack.peek().left = newNode;
                stack.push(newNode);
            }
        }
        logIt("Construction Done, let's print inorder");
        inorder(root);
        newLine();
    }

    public Node findInorderSuccessorInBSTUsingParentMap(Node root, Node nodeInQuestion) {
        final Map<Node, Node> parentMap = new HashMap<>();
        doInorderAndPrepareMap(root, null, parentMap);
        Node inorderSuccessor = null;

        if (nodeInQuestion.right != null) {
            inorderSuccessor = nodeInQuestion.right;
            while (inorderSuccessor.left != null) {
                inorderSuccessor = inorderSuccessor.left;
            }
        } else {
            Node curr = nodeInQuestion;
            while (true) {
                Node parent = parentMap.get(curr);
                if (parent == null || parent.data > nodeInQuestion.data) {
                    if (parent != null) {
                        inorderSuccessor = parent;
                    }
                    break;
                }
                curr = parent;
            }
        }
        return inorderSuccessor;
    }

    private void doInorderAndPrepareMap(Node root, Node parent, final Map<Node, Node> parentMap) {
        if (root == null) return;
        parentMap.put(root, parent);
        doInorderAndPrepareMap(root.left, root, parentMap);
        doInorderAndPrepareMap(root.right, root, parentMap);
    }


    public void findInorderSuccessorInBST(Node root, int data) {
        Node inorderSuccessorOfWhom = searchData(root, data);
        int successor;
        if (inorderSuccessorOfWhom != null) {
            // If Right  Subtree is present then it's simple
            if (inorderSuccessorOfWhom.right != null) {
                Node inorderSuccessor = findInorderSuccessor(inorderSuccessorOfWhom);
                logIt("Inorder Successor of " + data + " in Tree is " + inorderSuccessor.data);
            } else { // If Right Subtree is not present then it inorder successor can be present in somewhere between
                // root to the current node path
                successor = -1;
                Node temp = root;
                while (temp.data != data) {
                    if (temp.data > data) {
                        successor = temp.data;
                        temp = temp.left;
                    } else {
                        temp = temp.right;
                    }
                }
                if (successor != -1) {
                    logIt("Inorder Successor of " + data + " in Tree is " + successor);
                } else {
                    logIt("This is the largest node is BST " + data + ", So no Inorder Successor is available.....");
                }
            }
        } else {
            logIt("Node not present for which Inorder Successor is searched for");
        }
    }

    public Node constructBSTFromLevelOrderTraversal(int[] inorderTraversal) {
        logIt("Constructing BST From Level Order Traversal ", true);
        Node root = null;

        for (int i : inorderTraversal) {
            root = insertIntoBST(root, i);
        }
        inorder(root);
        return root;
    }

    public void findInorderPredecessor(Node root, List<Integer> dataList) {
        dataList.stream().forEach(data -> logIt("Inorder Predecessor of " + data + " is ==> " + inorderPredecessor(root, data).data));
    }

    private Node inorderPredecessor(Node root, Integer data) {
        if (root == null) {
            return new Node(-1);
        }
        Node nodeForWhichPredecessorIsToBeFound = searchData(root, data);

        if (nodeForWhichPredecessorIsToBeFound != null) {
            // If Node left subtree is not null then predecessor is the rightmost child in left.
            if (nodeForWhichPredecessorIsToBeFound.left != null) {
                Node predecessor = nodeForWhichPredecessorIsToBeFound.left;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                return predecessor;
            } else { // Node's left is null, So we must track from Root to this node and check for the node which is just smaller than this node.
                Node predecessor = root;
                while (root.data != data) {
                    if (root.data < data) {
                        predecessor = root;
                    }
                    if (root.data > data) {
                        root = root.left;
                    } else {
                        root = root.right;
                    }
                }
                return predecessor;
            }
        } else {
            return new Node(-1);
        }
    }

    public static void main(String[] args) {
        BinarySearchTreeUtil bst = new BinarySearchTreeUtil();

        bst.root = bst.insertDataIntoBST(bst.root, Arrays.asList(8, 3, 10, 1, 6, 4, 7, 14, 13));

        logIt("Inorder Traversal of BST ", true);
        bst.inorder(bst.root);
        newLine();

        bst.searchDataInBST(bst.root, 14);
        bst.searchDataInBST(bst.root, 100);

        bst.root = bst.insertDataIntoBST(null, Arrays.asList(15, 12, 10, 14, 13, 17, 21, 19, 24));
        bst.inorder(bst.root);
        newLine();

        bst.deleteNodeFromBST(bst.root, 17);

        logIt("Construct BST from PreOrder TRaversal Recursively", true);
        int[] preOrderTraversal = new int[]{10, 5, 1, 7, 40, 50};

        bst.root = bst.constructBSTFromPreOrderTraversalRecursively(preOrderTraversal);

        logIt("Inorder Traversal of newly constructed BST");
        bst.inorder(bst.root);
        newLine();

        // This is using the upperBound technique
        bstFromPreorder(preOrderTraversal);

        logIt("Construct BST from PreOrder TRaversal Iteratively", true);
        bst.constructBSTFromPreOrderIteratively(preOrderTraversal);


        logIt("Finding Inorder Successor In BST", true);
        bst.root = bst.insertDataIntoBST(null, Arrays.asList(20, 8, 4, 12, 10, 14, 22));
        bst.inorder(bst.root);
        newLine();

        bst.findInorderSuccessorInBST(bst.root, 14);

        bst.findInorderSuccessorInBST(bst.root, 10);

        bst.findInorderSuccessorInBST(bst.root, 8);

        bst.findInorderSuccessorInBST(bst.root, 4);

        bst.findInorderSuccessorInBST(bst.root, 20);

        bst.findInorderSuccessorInBST(bst.root, 22);

        bst.constructBSTFromLevelOrderTraversal(new int[]{20, 8, 22, 4, 12, 10, 14});

        Node root = bst.constructBSTFromLevelOrderTraversal(new int[]{50, 16, 90, 14, 40, 78, 100, 10, 15, 35, 45, 75, 82, 5, 32, 36, 81, 85, 37, 79, 87});

        newLine();
        bst.findInorderPredecessor(root, Arrays.asList(50, 16, 90, 14, 40, 78, 100, 10, 15, 35, 45, 75, 82, 5, 32, 36, 81, 85, 37, 79, 87));


        bst.root = bst.insertDataIntoBST(null, Arrays.asList(2, 1));
        Node inorderSuccessor = bst.findInorderSuccessorInBSTUsingParentMap(bst.root, bst.root.left);
        System.out.println(inorderSuccessor != null ? inorderSuccessor.data : "NOT FOUND");

        bst.root = bst.insertDataIntoBST(null, Arrays.asList(2, 1, 3));
        inorderSuccessor = bst.findInorderSuccessorInBSTUsingParentMap(bst.root, bst.root);
        System.out.println(inorderSuccessor != null ? inorderSuccessor.data : "NOT FOUND");
    }
}
