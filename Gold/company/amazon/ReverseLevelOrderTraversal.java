package com.company.amazon;

import com.company.amazon.BinaryTree.Node;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Problem : 174 Amazon Interview Question
 */
public class ReverseLevelOrderTraversal {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right = new Node(3);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.right.left = new Node(8);
        root.right.right.right = new Node(9);

        reverseLevelOrderTraversal(root);
    }

    public static void reverseLevelOrderTraversal(Node root) {
        Stack<Integer> stack = new Stack<>(); // This will only contain data(_NUMBER_) to be printed.
        Queue<Node> queue = new LinkedList<>();

        queue.add(root); // Entry Point

        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            stack.push(temp.data);

            if (temp.right != null) {
                queue.add(temp.right);
            }

            if (temp.left != null) {
                queue.add(temp.left);
            }
        }

        // Now let's print the each level in reverse order
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + ",");
        }
    }
}
