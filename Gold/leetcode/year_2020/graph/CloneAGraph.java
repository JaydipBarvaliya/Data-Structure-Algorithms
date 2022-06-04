package com.leetcode.year_2020.graph;

import com.leetcode.year_2020.CloneALinkedListWithRandomPointers;
import com.util.LogUtil;

import java.util.*;

/**
 * https://leetcode.com/problems/clone-graph/
 *
 * @author neeraj on 04/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CloneAGraph {

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

        node1.neighbors.addAll(Arrays.asList(node2, node4));
        node2.neighbors.addAll(Arrays.asList(node1, node3));
        node3.neighbors.addAll(Arrays.asList(node2, node4));
        node4.neighbors.addAll(Arrays.asList(node1, node3));

        cloneGraph(node1);
    }

    public static Node cloneGraph(Node node) {
        /**
         * Create a Map which will keep originalNode to ClonedNode mapping.
         * This is similar to {@link CloneALinkedListWithRandomPointers}
         */
        printDFS(node, new HashSet<>()); // DFS for Original
        Map<Node, Node> originalNodeToClonedNodeMap = new HashMap<>();

        // Now let's do a DFS from starting node.
        Set<Node> visited = new HashSet<>();

        // Since it's connected graph we just have to do a DFS from 1 node.
        doDFSAndCloneGraph(node, originalNodeToClonedNodeMap, visited);


        LogUtil.newLine();
        printDFS(originalNodeToClonedNodeMap.get(node), new HashSet<>()); // DFS for Cloned.

        return originalNodeToClonedNodeMap.get(node);
    }

    private static void doDFSAndCloneGraph(Node sourceVertex, Map<Node, Node> originalNodeToClonedNodeMap, Set<Node> visited) {
        visited.add(sourceVertex);
        if (!originalNodeToClonedNodeMap.containsKey(sourceVertex)) {
            // Creating a new cloned node.
            originalNodeToClonedNodeMap.put(sourceVertex, new Node(sourceVertex.val));
        }

        for (Node neighbourVertex : sourceVertex.neighbors) {
            originalNodeToClonedNodeMap.putIfAbsent(neighbourVertex, new Node(neighbourVertex.val));

            // Now add the edge in the Cloned version.
            originalNodeToClonedNodeMap.get(sourceVertex).
                    neighbors.add(originalNodeToClonedNodeMap.get(neighbourVertex));
            if (!visited.contains(neighbourVertex)) {
                doDFSAndCloneGraph(neighbourVertex, originalNodeToClonedNodeMap, visited);
            }
        }
    }

    private static void printDFS(Node sourceVertex, Set<Node> visited) {
        visited.add(sourceVertex);
        System.out.print(sourceVertex.val + " ----> ");

        for (Node adjacent : sourceVertex.neighbors) {
            if (!visited.contains(adjacent)) {
                printDFS(adjacent, visited);
            }
        }
    }
}
