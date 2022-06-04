package com.company.amazon;

import com.company.amazon.BinaryTree.Node;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeIsHeap {

    public static void main(String[] args) {
        Node root = new Node(100);
        root.left = new Node(25);
        root.right = new Node(20);
        root.left.left = new Node(17);

        if (binaryTreeIsMaxHeap(root)) {
            System.out.println("Yes it's a Max heap");
        }
    }

    public static boolean binaryTreeIsMaxHeap(Node root) {
        Queue<Node> queue = new LinkedList<>();
        Boolean isNullInserted = false;
        queue.add(root);

        while (!queue.isEmpty()) {
            Node dequeued = queue.poll();

            if (isNullInserted && dequeued != null) {
                System.out.println("Binary Tree doesn't satisfy Complete Tree property");
                return false;
            } else if (!isNullInserted) {
                if (dequeued == null) {
                    isNullInserted = true;
                    continue;
                }
                if (dequeued.left != null) {
                    if (dequeued.left.data > dequeued.data) {
                        System.out.println("MAX heap left child should be small property doesn't satisfy");
                        return false;
                    } else {
                        queue.add(dequeued.left);
                    }
                } else {
                    queue.add(dequeued.left);
                }

                if (dequeued.right != null) {
                    if (dequeued.right.data > dequeued.data) {
                        System.out.println("MAX heap right child should be small property doesn't satisfy");
                        return false;
                    } else {
                        queue.add(dequeued.right);
                    }
                } else {
                    queue.add(dequeued.right);
                }
            }
        }
        return true;
    }
}
