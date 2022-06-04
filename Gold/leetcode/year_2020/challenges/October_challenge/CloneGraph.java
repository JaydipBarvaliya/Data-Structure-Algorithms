package com.leetcode.year_2020.challenges.October_challenge;

import java.util.*;

/**
 * @author neeraj on 20/10/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CloneGraph {

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors = Arrays.asList(node2, node4);
        node2.neighbors = Arrays.asList(node1, node3);
        node3.neighbors = Arrays.asList(node4, node2);
        node4.neighbors = Arrays.asList(node1, node3);

        Node newNode = cloneGraph(node1);
        System.out.println(newNode);
    }

    public static Node cloneGraph(Node node) {
        // we can do either BFS or DFS
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        Set<Integer> visited = new HashSet<>();
        visited.add(node.val);
        Map<Integer, Node> graph = new HashMap<>();

        while (!queue.isEmpty()) {
            Node popped = queue.poll();
            if (!graph.containsKey(popped.val)) {
                graph.put(popped.val, new Node(popped.val));
            }
            for (Node adjacent : popped.neighbors) {
                graph.get(popped.val).neighbors.add(new Node(adjacent.val));
                if (!visited.contains(adjacent.val)) {
                    visited.add(adjacent.val);
                    queue.add(adjacent);
                }
            }
        }
        return graph.get(node.val);
    }
}
