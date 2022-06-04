package com.leetcode.year_2020.tree;

import com.leetcode.year_2020.SerializeAndDeserializeBinaryTree;
import com.leetcode.year_2020.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * NOTE NOTE NOTE -=======> DFS will not work, See last 2 test cases they will fail
 * ONly BFS will work
 * <p>
 * /**         This will fail with dfs
 * *                    3
 * *                  /   \
 * *                9      8
 * *              /  \    /  \
 * *             4    0 1     7
 * *                  / \
 * *                /    \
 * *               5      2
 * * You can notice it's 0'th right child is 2 and 1's left child is 5
 * * Our version: 3,9,4,X,X,0,X,2,X,X,8,1,5,X,X,X,7,X,X
 * * On Lintcode: {3,9,8,4,0,1,7,#,#,#,2,5}
 * *
 * * {3,9,8,#,0,1,#,6,7,#}
 */

public class VerticalOrderTraversal {

    public static void main(String[] args) {
        /**
         *          1
         *         /
         *        2
         *         \
         *          3
         * Our serializer ==> Root + DELIMETER + serialize(left) + serialize(right)
         * 1,2,X,3,X,X,X
         */
        TreeNode root = SerializeAndDeserializeBinaryTree.deserialize("1,2,X,3,X,X,X");
        System.out.println(verticalOrderAndThisIsWrongSolution(root));

        /**
         *                    3
         *                  /   \
         *                9       20
         *                      /   \
         *                    15     7
         * 3,9,X,X,20,15,X,X,7,X,X
         *
         * On Lintcode: {3,9,20,#,#,15,7}
         * {3,9,8,4,0,1,7}
         * {1,2,#,#,3}
         * {3,9,8,4,0,1,7,#,#,#,2,5}
         * {3,9,8,#,0,1,#,6,7,#}
         */
        root = SerializeAndDeserializeBinaryTree.deserialize("3,9,X,X,20,15,X,X,7,X,X");
        System.out.println(verticalOrderAndThisIsWrongSolution(root));

        /**
         *                    3
         *                  /   \
         *                9      8
         *              /  \    /   \
         *             4    0  1     7
         *
         *
         * Our version: 3,9,4,X,X,0,X,X,8,1,X,X,7,X,X
         * On Lintcode:{3,9,8,4,0,1,7}
         * {3,9,8,4,0,1,7,#,#,#,2,5}
         * {3,9,8,#,0,1,#,6,7,#}
         */
        root = SerializeAndDeserializeBinaryTree.deserialize("3,9,4,X,X,0,X,X,8,1,X,X,7,X,X");
        System.out.println(verticalOrderAndThisIsWrongSolution(root));


        /**
         *                    3
         *                  /   \
         *                9      8
         *              /  \    /  \
         *             4    0 1     7
         *                  / \
         *                /    \
         *               5      2
         * You can notice it's 0'th right child is 2 and 1's left child is 5
         * Our version: 3,9,4,X,X,0,X,2,X,X,8,1,5,X,X,X,7,X,X
         * On Lintcode: {3,9,8,4,0,1,7,#,#,#,2,5}
         *
         * {3,9,8,#,0,1,#,6,7,#}
         */
        root = SerializeAndDeserializeBinaryTree.deserialize("3,9,4,X,X,0,X,2,X,X,8,1,5,X,X,X,7,X,X");
        System.out.println(verticalOrderAndThisIsWrongSolution(root));
        System.out.println(verticalOrderWithBFS(root));


    }

    static class Pair {
        TreeNode node;
        int distance;

        public Pair(final TreeNode node, final int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static List<List<Integer>> verticalOrderWithBFS(TreeNode root) {
        if (root == null) return Collections.EMPTY_LIST;
        final TreeMap<Integer, LinkedList<Integer>> verticalOrder = new TreeMap<>();
        final Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));
        int queueSize = 1;

        while (!queue.isEmpty()) {
            queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                Pair pair = queue.remove();
                int distance = pair.distance;
                verticalOrder.putIfAbsent(distance, new LinkedList<>());
                verticalOrder.get(distance).add(pair.node.val);
                if (pair.node.left != null) {
                    queue.add(new Pair(pair.node.left, pair.distance - 1));
                }
                if (pair.node.right != null) {
                    queue.add(new Pair(pair.node.right, pair.distance + 1));
                }
            }
        }

        final List<List<Integer>> result = new ArrayList<>();
        for (Map.Entry<Integer, LinkedList<Integer>> order : verticalOrder.entrySet()) {
            result.add(order.getValue());
        }
        return result;
    }

    static class Distance {
        int left = 0;
        int right = 0;
    }

    public static List<List<Integer>> verticalOrderAndThisIsWrongSolution(TreeNode root) {
        if (root == null) return Collections.EMPTY_LIST;
        final Distance boundary = new Distance();
        final Map<Integer, LinkedList<Integer>> verticalOrder = new HashMap<>();
        final List<List<Integer>> result = new ArrayList<>();

        preorder(root, 0, verticalOrder, boundary);

        for (int i = boundary.left; i <= boundary.right; i++) {
            result.add(verticalOrder.get(i));
        }
        return result;
    }

    public static void preorder(TreeNode root, int distance,
                                final Map<Integer, LinkedList<Integer>> verticalOrder,
                                Distance boundary) {
        if (root == null) {
            return;
        }

        boundary.left = Math.min(boundary.left, distance);
        boundary.right = Math.max(boundary.right, distance);
        verticalOrder.putIfAbsent(distance, new LinkedList<>());
        verticalOrder.get(distance).add(root.val);

        preorder(root.left, distance - 1, verticalOrder, boundary);

        preorder(root.right, distance + 1, verticalOrder, boundary);


    }
}
