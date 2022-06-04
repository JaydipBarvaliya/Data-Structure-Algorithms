package com.company.amazon;

import com.company.amazon.BinaryTree.Node;

import java.util.ArrayList;
import java.util.List;

public class LongestConsecutiveSequenceInBinaryTree {

    public static void main(String[] args) {
        Node root = new Node(6);
        root.right = new Node(9);
        root.right.left = new Node(7);
        root.right.right = new Node(10);
        root.right.right.right = new Node(11);

        findLongestConsecutiveSequence(root);

        root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.right = new Node(4);
        root.right.left = new Node(5);
        root.right.right = new Node(6);
        root.right.right.left = new Node(7);

        findLongestConsecutiveSequence(root);
    }

    public static void findLongestConsecutiveSequence(Node root) {
        List<Integer> inorderList = new ArrayList<>();
        Integer LONGEST_SEQ_LENGTH = 0, START = 0, END = 0;
        inorderTraversal(root, inorderList);
        int low = 0, high = 0;
        while (high < inorderList.size() - 1) {
            if (Math.abs(inorderList.get(high) - inorderList.get(high + 1)) == 1) {
                high++;
                if (high - low > LONGEST_SEQ_LENGTH) {
                    LONGEST_SEQ_LENGTH = high - low;
                    START = low;
                    END = high;
                }
            } else {
                high++;
                low = high;
            }

        }
        System.out.println(inorderList.subList(START, END + 1));
        // TODO Sort if required
    }

    public static void inorderTraversal(Node root, List<Integer> inorderList) {
        if (root == null)
            return;
        inorderTraversal(root.left, inorderList);
        inorderList.add(root.data);
        inorderTraversal(root.right, inorderList);
    }
}
