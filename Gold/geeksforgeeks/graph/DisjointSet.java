package com.geeksforgeeks.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * Using Union By Rank and Path Compression
 * <p>
 * Opertion ---->
 * <p>
 * MakeSet
 * Union
 * FindSet
 */
public class DisjointSet {

    static Map<Long, Node> dataNodeMap = new HashMap<>();

    static class Node {
        long data;
        int rank;
        Node parent;
    }

    /**
     * Create a Set with only 1 Element
     *
     * @param data
     */
    public static void makeSet(long data) {
        Node node = new Node();
        node.data = data;
        node.rank = 0;
        node.parent = node;
        dataNodeMap.put(data, node);
    }

    /**
     * Find Union of 2 Sets
     * Using Union By Rank
     */
    public static boolean union(long data1, long data2) {
        Node node1 = dataNodeMap.get(data1);
        Node node2 = dataNodeMap.get(data2);

        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);

        // Both Nodes belong from the Same set
        if (parent1.data == parent2.data) {
            return false;
        }

        // Let's check whose rank is bigger and make it the parent
        if (parent1.rank >= parent2.rank) {
            parent2.parent = parent1;

            //increment rank only if both sets have same rank
            parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
        } else {
            parent1.parent = parent2;
        }
        return true;
    }

    /**
     * Find the representative recursively and does path
     * compression as well.
     */
    public static Node findSet(Node node) {
        Node parent = node.parent;

        if (parent == node) {
            return parent;
        }

        // Let's do the path compression
        node.parent = findSet(node.parent);
        return node.parent;
    }

    /**
     * Finds the representative of this set
     */
    public static long findSet(long data) {
        return findSet(dataNodeMap.get(data)).data;
    }

    public static void main(String[] args) {
        DisjointSet disjointSet = new DisjointSet();

        for (int i = 0; i < 7; i++)
            disjointSet.makeSet(i + 1);

        union(1, 2);
        union(2, 3);
        union(4, 5);
        union(6, 7);
        union(5, 6);
        union(3, 7);

        System.out.println(findSet(1));
        System.out.println(findSet(2));
        System.out.println(findSet(3));
        System.out.println(findSet(4));
        System.out.println(findSet(5));
        System.out.println(findSet(6));
        System.out.println(findSet(7));
    }
}
