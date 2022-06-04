package com.leetcode.year_2020.graph.disjoint_set_union_find;

import com.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/number-of-operations-to-make-network-connected/
 *
 * @author neeraj on 27/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NumberofOperationstoMakeNetworkConnected {

    public static void main(String[] args) {
        System.out.println(makeConnected(4, new int[][]{
                {0, 1}, {0, 2}, {1, 2}
        }));

        System.out.println(makeConnected(6, new int[][]{
                {0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}
        }));

        System.out.println(makeConnected(6, new int[][]{
                {0, 1}, {0, 2}, {0, 3}, {1, 2}
        }));

        System.out.println(makeConnected(5, new int[][]{
                {0, 1}, {0, 2}, {3, 4}, {2, 3}
        }));
        System.out.println(makeConnected(11, new int[][]{
                {1, 4}, {0, 3}, {1, 3}, {3, 7}, {2, 7}, {0, 1}, {2, 4}, {3, 6}, {5, 6}, {6, 7}, {4, 7}, {0, 7}, {5, 7}
        }));

        LogUtil.newLine();

        System.out.println(makeConnectedWithJustFindOperation(4, new int[][]{
                {0, 1}, {0, 2}, {1, 2}
        }));

        System.out.println(makeConnectedWithJustFindOperation(6, new int[][]{
                {0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}
        }));

        System.out.println(makeConnectedWithJustFindOperation(6, new int[][]{
                {0, 1}, {0, 2}, {0, 3}, {1, 2}
        }));

        System.out.println(makeConnectedWithJustFindOperation(5, new int[][]{
                {0, 1}, {0, 2}, {3, 4}, {2, 3}
        }));
        System.out.println(makeConnectedWithJustFindOperation(11, new int[][]{
                {1, 4}, {0, 3}, {1, 3}, {3, 7}, {2, 7}, {0, 1}, {2, 4}, {3, 6}, {5, 6}, {6, 7}, {4, 7}, {0, 7}, {5, 7}
        }));
    }

    public static int makeConnectedWithJustFindOperation(int totalComputers, int[][] connections) {
        /**
         * This is again a Disjoint Set technique, but we just need a find operation to figure out
         * connected computer groups.
         */
        int[] leader = new int[totalComputers];
        for (int i = 0; i < totalComputers; i++) leader[i] = i; // Initially every vertex/computer's leader is itself.

        int EXTRA_WIRES = 0;

        for (int[] connection : connections) {
            // Each connection is telling us that they can be grouped
            int leaderOfA = findLeader(leader, connection[0]);
            int leaderOfB = findLeader(leader, connection[1]);

            if (leaderOfA == leaderOfB) EXTRA_WIRES++; // They both already belong to same group.
            else leader[leaderOfA] = leaderOfB;
        }

        // Now let's count number of connected components/computers
        int connectedComponents = 0;
        for (int i = 0; i < totalComputers; i++) {
            // Connected Components is represented by the leader only.
            // So all those nodes who are leaders will be the total number of connected components.
            if (leader[i] == i) connectedComponents++;
        }
        return (EXTRA_WIRES >= connectedComponents - 1) ? connectedComponents - 1 : -1;
    }

    public static int findLeader(int[] leader, int computer) {
        if (leader[computer] == computer) return computer;

        leader[computer] = findLeader(leader, leader[computer]);
        return leader[computer];
    }

    public static int makeConnected(int totalComputers, int[][] connections) {
        /**
         * https://leetcode.com/problems/number-of-operations-to-make-network-connected/
         *
         * Okay so this problem boils down to find out total number of Connected Components.
         * If we have N computers we need just N-1 Wires to connect them.
         * So if number of connections is < totalComputers -1 we can simply not connect all of them
         * Now lets talk about the scenario when connections >= totalComputers-1.
         *
         * To find the number of connected components we can use Disjoint Set Union Data Structures
         */
        DisjointSetOfComputers disjointSet = new DisjointSetOfComputers();
        for (int i = 0; i < totalComputers; i++) {
            disjointSet.makeSet(i);
        }


        // Iterate through all connections and find number of connected components.
        for (int[] connection : connections) {
            disjointSet.unionByRanks(connection[0], connection[1]);
        }

        // Now let's count number of connected components
        int connectedComponents = 0;
        for (int i = 0; i < totalComputers; i++) {
            // Connected Components is represented by the leader only.
            // So all those nodes who are leaders will be the total number of connected components.
            if (disjointSet.findLeader(i) == i) connectedComponents++;
        }

        // Since for N computers we need N-1 wires only.
        return (disjointSet.EXTRA_WIRES >= connectedComponents - 1) ? connectedComponents - 1 : -1;
    }

    static class DisjointSetOfComputers {
        Map<Long, Node> dataToNodeMap = new HashMap<>();
        public Long EXTRA_WIRES = 0l;

        class Node {
            long data;
            long rank;
            Node parent;

            public Node(long data) {
                this.data = data;
            }
        }

        public void makeSet(long data) {
            Node node = new Node(data);
            node.rank = 0; // Initially No child Nodes in the set hence rank is 0;
            node.parent = node; // Leader for single node is always the same node (self-loop).

            dataToNodeMap.put(data, node); // Use this map to refer nodes when external user query with just Long.
        }

        public long findLeader(long value) {
            Node node = dataToNodeMap.get(value);
            Node parent = findLeader(node);
            return parent.data;
        }

        private Node findLeader(Node node) {
            Node parent = node.parent;
            if (parent == node) {
                return parent;
            }

            // So this node is not the leader, let's recursively find out it's parent and also
            // do the path compression so the tree height is minimum and we can find the leader in
            // minimal steps.
            node.parent = findLeader(parent);
            return node.parent;
        }

        public boolean unionByRanks(long a, long b) {
            Node node1 = dataToNodeMap.get(a);
            Node node2 = dataToNodeMap.get(b);

            Node parentA = findLeader(node1);
            Node parentB = findLeader(node2);

            if (parentA == parentB) {
                // Both A and B belongs to the same group
                // and is connected by same parent so this wire between a and b is an extra wire
                // and we can use it to join some other disconnected computers in the network.
                EXTRA_WIRES++;
                return false;  // Why false since Union didn't happen.
            }

            if (parentA.rank >= parentB.rank) {
                parentB.parent = parentA;

                // We will only increase the rank of A when we find other rank also similar.
                parentA.rank = parentA.rank == parentB.rank ? parentA.rank + 1 : parentA.rank;
            } else {
                parentB.parent = parentA;
            }
            return true;
        }
    }
}
