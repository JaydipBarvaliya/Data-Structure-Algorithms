package com.leetcode.heap;

import com.leetcode.problems.medium.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * https://www.lintcode.com/problem/901/description
 */
public class ClosestBinarySearchTree2 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        System.out.println(closestKValues(root, 0.275000, 2));
        System.out.println(closestKValuesViaStackAndQueue(root, 0.275000, 2));

        root = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.left.left = new TreeNode(1);

        System.out.println(closestKValues(root, 3.714286, 3));
        System.out.println(closestKValuesViaStackAndQueue(root, 3.714286, 3));
    }

    public static List<Integer> closestKValuesViaStackAndQueue(TreeNode root, double target, int k) {
        Stack<Integer> stack = new Stack<>(); // For Storing all values < target
        Queue<Integer> queue = new LinkedList<>(); // For Storing all values > target
        /**
         * * Target = 3.714286
         * *                4
         * *              /   \
         * *             2     5
         * *           /  \
         * *          1    3
         * *
         * * Do inorder and populate stack and queue
         * *
         * *   |  3 |           --------------------------
         * *   |  2 |           (Front) 4   5          (Rear)
         * *   |  1 |           -------------------------
         * *   ------
         * *
         * *   Now we just have to compare these 2 till we have our k closest items
         * *
         */
        inorderAndPopulate(root, stack, queue, target);
        List<Integer> closestValues = new ArrayList<>();
        while (k-- > 0) {
            if (stack.isEmpty()) {
                closestValues.add(queue.remove());
            } else if (queue.isEmpty()) {
                closestValues.add(stack.pop());
            } else {
                // Now compare both top values
                double distanceOfStackTop = getDistanceFromTarget(stack.peek(), target);
                double distanceOfQueueFront = getDistanceFromTarget(queue.peek(), target);
                if (distanceOfStackTop < distanceOfQueueFront) {
                    closestValues.add(stack.pop());
                } else {
                    closestValues.add(queue.remove());
                }
            }
        }
        return closestValues;
    }

    private static void inorderAndPopulate(final TreeNode root, final Stack<Integer> stack,
                                           final Queue<Integer> queue, final double target) {
        if (root == null) return;
        inorderAndPopulate(root.left, stack, queue, target);
        if (root.val < target) {
            stack.push(root.val);
        } else {
            queue.add(root.val);
        }
        inorderAndPopulate(root.right, stack, queue, target);
    }


    public static List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> closestValues = new ArrayList<>();
        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>((a, b) -> (int) (getDistanceFromTarget(b, target) - getDistanceFromTarget(a, target)));

        inorder(root, maxHeap, k);
        while (!maxHeap.isEmpty()) {
            closestValues.add(maxHeap.poll());
        }
        return closestValues;
    }

    private static void inorder(final TreeNode root, final PriorityQueue<Integer> maxHeap, final int k) {
        if (root == null) return;
        maxHeap.add(root.val);
        if (maxHeap.size() > k) maxHeap.poll();
        inorder(root.left, maxHeap, k);
        inorder(root.right, maxHeap, k);
    }

    public static double getDistanceFromTarget(int a, double target) {
        return Math.abs(a - target);
    }
}
