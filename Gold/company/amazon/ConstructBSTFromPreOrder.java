package com.company.amazon;

import com.company.amazon.BinaryTree.Node;

import java.util.Stack;

public class ConstructBSTFromPreOrder {

    public static void main(String[] args) {

        BinaryTree.inorder(constructBST(new int[]{10, 5, 1, 7, 40, 50}), true);
    }

    @SuppressWarnings("Duplicates")
    public static Node constructBST(int[] preorder) {
        Stack<Node> stack = new Stack<>();
        Node head = new Node(preorder[0]);
        stack.push(head);

        for (int i = 1; i < preorder.length; i++) {
            Node temp = null;

            while (!stack.isEmpty() && stack.peek().data < preorder[i]) { // Pop out all Nodes less that current Array value
                temp = stack.pop();
            }

            if (temp != null) { // There was a Node in stack which is actually smaller than current Array Node
                temp.right = new Node(preorder[i]);
                stack.push(temp.right);
            } else { // There is no Node smaller than current Array Value
                temp = stack.peek(); // We will not pop this Node as we need Parent Node in stack have a right pointer on right subtree
                temp.left = new Node(preorder[i]);
                stack.push(temp.left);
            }
        }
        return head;
    }
}
