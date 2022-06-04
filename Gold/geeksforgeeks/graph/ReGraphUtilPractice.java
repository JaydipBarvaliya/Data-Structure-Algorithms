package com.geeksforgeeks.graph;

import com.util.LogUtil;

import java.util.*;

import static com.geeksforgeeks.graph.GraphUtil.moveVertex;
import static com.util.LogUtil.logIt;
import static com.util.LogUtil.newLine;

/**
 * @author neeraj on 2019-07-28
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("Duplicates")
public class ReGraphUtilPractice {

    static class Graph {
        int V;
        LinkedList<Integer>[] adjacentListArray;

        public Graph(int v) {
            this.V = v;

            // Initialized place holder for every node/vertex
            adjacentListArray = new LinkedList[V];

            for (int i = 0; i < adjacentListArray.length; i++) {
                adjacentListArray[i] = new LinkedList<>();
            }
        }
    }

    public static void addEdge(Graph graph, int source, int destination, boolean isDirectedGraph) {
        graph.adjacentListArray[source].add(destination);

        if (!isDirectedGraph) {
            graph.adjacentListArray[destination].add(source);
        }
    }

    public static void addEdge(Graph graph, int source, int destination) {
        addEdge(graph, source, destination, false);
    }

    public static void printGraph(Graph graph) {
        for (int i = 0; i < graph.adjacentListArray.length; i++) {
            LogUtil.logInSingleLine("Adjacent Vertices for " + i + " --> ");
            System.out.println(graph.adjacentListArray[i]);
        }
    }

    public static void BreadthFirstTraversal(Graph graph, int source) {
        Set<Integer> visited = new HashSet<>();
        LinkedList<Integer>[] adjacentListArr = graph.adjacentListArray;
        Queue<Integer> queue = new LinkedList<>();

        // We will start traversing from source
        queue.add(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            int popped = queue.poll();
            System.out.print(popped + " ");

            for (int a : adjacentListArr[popped]) {
                if (!visited.contains(a)) {
                    queue.add(a);
                    visited.add(a);
                }
            }
        }
    }

    public static void DepthFirstTraversal(Graph graph, int source) {
        Set<Integer> visited = new HashSet<>();
        LinkedList<Integer>[] adjacentListArr = graph.adjacentListArray;
        Stack<Integer> stack = new Stack<>();

        // We start from source, and push to stack but will not visit it until actually processed.
        // Also in Stack we just process the node and move to next note, unlike BFS where we actually
        // First sweep all neighbours and then proceed further.
        stack.push(source);

        while (!stack.isEmpty()) {
            int popped = stack.pop();
            visited.add(popped);
            System.out.print(popped + " ");

            for (int a : adjacentListArr[popped]) {
                if (!visited.contains(a)) {
                    stack.add(a);
                }
            }
        }
    }

    public static void recursiveDFS(Graph graph, int source, Set<Integer> visited) {
        System.out.print(source + " ");
        visited.add(source);

        for (int node : graph.adjacentListArray[source]) {
            if (!visited.contains(node)) {
                recursiveDFS(graph, node, visited);
            }
        }
    }

    private static void findStronglyConnectedComponents(Graph graph) {
        Stack<Integer> vertexSortedInFinishOrderOfTheirTime = new Stack<>();
        boolean[] visited = new boolean[graph.V];

        // Step 1 : Do DFS and store the node in Stack based on their finish time
        for (int source = 0; source < graph.V; source++) {
            if (!visited[source]) {
                dfsForSCC(source, visited, vertexSortedInFinishOrderOfTheirTime, graph.adjacentListArray, false);
            }
        }

        // STEP 2: Reverse the graph
        reverseGraph(graph);

        // Step 3: Now pop the item from stack and doDFS
        visited = new boolean[graph.V];

        while (!vertexSortedInFinishOrderOfTheirTime.isEmpty()) {
            int popped = vertexSortedInFinishOrderOfTheirTime.pop();
            if (!visited[popped]) {
                dfsForSCC(popped, visited, null, graph.adjacentListArray, true);
                newLine();
            }
        }
    }

    private static void reverseGraph(Graph graph) {
        LinkedList<Integer>[] adjacentListArrNew = new LinkedList[graph.V];
        // Initialize this new array
        for (int i = 0; i < graph.V; i++) {
            adjacentListArrNew[i] = new LinkedList<>();
        }

        // Now let's actually reverse the edges
        for (int i = 0; i < graph.V; i++) {
            // We are taking each vertex from original graph
            // and reversing the edges
            for (int vertex : graph.adjacentListArray[i]) {
                adjacentListArrNew[vertex].add(i);
            }
        }
        graph.adjacentListArray = adjacentListArrNew;
    }


    private static void dfsForSCC(int source, boolean[] visited,
                                  Stack<Integer> vertexSortedInFinishOrderOfTheirTime,
                                  LinkedList<Integer>[] adjacentListArray, Boolean shouldPrint) {
        visited[source] = true;
        if (shouldPrint) {
            System.out.print(source + " ");
        }

        for (int vertex : adjacentListArray[source]) {
            if (!visited[vertex]) {
                dfsForSCC(vertex, visited, vertexSortedInFinishOrderOfTheirTime, adjacentListArray, shouldPrint);
            }
        }

        // All adjacent vertex are traversed;
        if (!shouldPrint) {
            vertexSortedInFinishOrderOfTheirTime.add(source);
        }
    }

    private static void findMotherVertex(Graph graph) {
        int lastVisitedVertex = -1;
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < graph.V; i++) {
            if (!visited.contains(i)) {
                recursiveDFS(graph, i, visited);
                lastVisitedVertex = i;
            }
        }

        // Now do the dfs with just lastVisitedVertex
        // if it again revisit all the nodes then it is mother vertex
        visited = new HashSet<>();
        recursiveDFS(graph, lastVisitedVertex, visited);

        for (int i = 0; i < graph.V; i++) {
            if (!visited.contains(i)) {
                logIt("There is no mother vertex in this graph", true);
            }
        }

        logIt("Mother vertex of this graph is " + lastVisitedVertex);

    }

    private static void checkIfDirectedGraphHasCycleUsingDFS(Graph graph) {
        Set<Integer> notVisited = new HashSet<>();
        Set<Integer> inProcess = new HashSet<>();
        Set<Integer> processed = new HashSet<>();

        // Initially all Nodes are not-visited.
        for (int i = 0; i < graph.V; i++) {
            notVisited.add(i);
        }

        // Let's do DFS on all NonVisited nodes

        while (!notVisited.isEmpty()) {
            Integer currentVertex = notVisited.iterator().next();
            if (findCycleWithDFS(currentVertex, notVisited, inProcess, processed, graph)) {
                System.out.println("Cycle do exist.....");
                return;
            }
        }
        System.out.println("Cycle do not exist.....");

    }

    private static boolean findCycleWithDFS(int currentVertex,
                                            Set<Integer> notVisited,
                                            Set<Integer> inProcess,
                                            Set<Integer> processed,
                                            Graph graph) {
        moveVertex(currentVertex, notVisited, inProcess);

        for (int adjacentVertex : graph.adjacentListArray[currentVertex]) {
            if (processed.contains(adjacentVertex))
                continue;
            if (inProcess.contains(adjacentVertex))
                return true;
            if (findCycleWithDFS(adjacentVertex, notVisited, inProcess, processed, graph))
                return true;
        }
        moveVertex(currentVertex, inProcess, processed);
        return false;
    }

    private static boolean cycleInUnDirectedGraphUsingBFS(Graph graph) {
        /**
         * -1 -->    Waiting-To-Be-Processed
         * 0 -->    Being Processed
         * 1 -->    Already Processed
         */
        int[] statusFlag = new int[graph.V];
        // Initially all vertex are waiting to be processed
        Arrays.fill(statusFlag, -1);

        // Since BFS so we need to use Queue.
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        // Mark this node as being processed
        statusFlag[0] = 0;
        Integer poppedVertex = null;

        while (!queue.isEmpty()) {
            poppedVertex = queue.poll();
            statusFlag[poppedVertex] = 1; // mark this node as visited

            for (int adjacentVertex : graph.adjacentListArray[poppedVertex]) {
                if (statusFlag[adjacentVertex] == -1) { // If waiting to be processed then let's process it.
                    queue.add(adjacentVertex);
                    statusFlag[adjacentVertex] = 0;
                } else if (statusFlag[adjacentVertex] == 0) {
                    System.out.println("Found Cycle");
                    return true;
                }
            }
        }
        System.out.println("No Cycle is present..........");
        return false;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);

        addEdge(graph, 0, 1);
        addEdge(graph, 0, 4);
        addEdge(graph, 1, 2);
        addEdge(graph, 1, 3);
        addEdge(graph, 1, 4);
        addEdge(graph, 2, 3);
        addEdge(graph, 3, 4);

        printGraph(graph);

        logIt("BREADTH FIRST TRAVERSAL OF GRAPH", true);
        BreadthFirstTraversal(graph, 2);

        newLine();
        logIt("BREADTH FIRST TRAVERSAL OF DIRECTED GRAPH", true);
        graph = new Graph(4);

        addEdge(graph, 0, 1, true);
        addEdge(graph, 0, 2, true);
        addEdge(graph, 2, 0, true);
        addEdge(graph, 2, 3, true);
        addEdge(graph, 1, 2, true);
        addEdge(graph, 3, 3, true);
        BreadthFirstTraversal(graph, 2);

        newLine();
        logIt("Directed Graph looks like this ", true);
        printGraph(graph);

        logIt("Depth FIRST TRAVERSAL OF DIRECTED GRAPH", true);
        DepthFirstTraversal(graph, 2);

        newLine();
        logIt("Depth FIRST TRAVERSAL OF DIRECTED GRAPH", true);
        recursiveDFS(graph, 2, new HashSet<>());

        newLine();
        logIt("Find Strongly Connected Components.........");
        graph = new Graph(5);
        addEdge(graph, 1, 0, true);
        addEdge(graph, 0, 2, true);
        addEdge(graph, 2, 1, true);
        addEdge(graph, 0, 3, true);
        addEdge(graph, 3, 4, true);

        findStronglyConnectedComponents(graph);

        newLine();
        logIt("Find Mother Vertex.........");
        graph = new Graph(5);
        addEdge(graph, 1, 0, true);
        addEdge(graph, 0, 2, true);
        addEdge(graph, 2, 1, true);
        addEdge(graph, 0, 3, true);
        addEdge(graph, 3, 4, true);
        findMotherVertex(graph);

        newLine();
        logIt("Detect Cycle in Directed Graph");
        graph = new Graph(6);
        addEdge(graph, 0, 1, true);
        addEdge(graph, 1, 2, true);
        addEdge(graph, 0, 2, true);
        addEdge(graph, 3, 1, true);
        addEdge(graph, 3, 4, true);
        addEdge(graph, 4, 5, true);
        addEdge(graph, 5, 4, true);

        checkIfDirectedGraphHasCycleUsingDFS(graph);

        newLine();
        logIt("Detect Cycle in Undirected Graph using BFS");
        /**
         *      A------B-------C
         *  *          |       |
         *  *          E-------D
         */
        graph = new Graph(5);
        addEdge(graph, 0, 1, false);
        addEdge(graph, 1, 2, false);
        addEdge(graph, 2, 3, false);
        addEdge(graph, 3, 4, false);
        addEdge(graph, 4, 1, false);

        cycleInUnDirectedGraphUsingBFS(graph);

        logIt("Union Set Data Structure, Union by Rank and Path Compression....");
        ReDisjointSet reDisjointSet = new ReDisjointSet();

        for (int i = 0; i < 7; i++)
            reDisjointSet.makeSet(i + 1);

        reDisjointSet.union(1, 2);
        reDisjointSet.union(2, 3);
        reDisjointSet.union(4, 5);
        reDisjointSet.union(6, 7);
        reDisjointSet.union(5, 6);
        reDisjointSet.union(3, 7);

        System.out.println(reDisjointSet.findSet(1));
        System.out.println(reDisjointSet.findSet(2));
        System.out.println(reDisjointSet.findSet(3));
        System.out.println(reDisjointSet.findSet(4));
        System.out.println(reDisjointSet.findSet(5));
        System.out.println(reDisjointSet.findSet(6));
        System.out.println(reDisjointSet.findSet(7));


    }
}

class ReDisjointSet {
    Map<Integer, UnionNode> map = new HashMap<>();

    class UnionNode {
        int value;
        int rank;
        UnionNode parent;
    }

    public void makeSet(int data) {
        UnionNode node = new UnionNode();
        node.value = data;
        node.rank = 0;
        node.parent = node;
        map.put(data, node);
    }

    public boolean union(int data1, int data2) {
        // First find in which set both these nodes belong to.
        UnionNode node1 = map.get(data1);
        UnionNode node2 = map.get(data2);

        // Check who their parents are
        UnionNode parent1 = findSet(node1);
        UnionNode parent2 = findSet(node2);

        // both nodes belong to the same clan
        if (parent1 == parent2) {
            return false;
        }

        //else whoever's rank is higher becomes parent of other
        if (parent1.rank >= parent2.rank) {
            parent2.parent = parent1;

            // Increment the rank only when both parent have same ranks
            // else the rank of greater wins
            parent1.rank = (parent2.rank == parent1.rank) ? parent1.rank + 1 : parent1.rank;
        } else {
            parent1.parent = parent2;
        }
        return true;
    }

    public int findSet(int value) {
        return findSet(map.get(value)).value;
    }

    private UnionNode findSet(UnionNode node1) {
        UnionNode parent = node1.parent;
        if (node1 == parent) {
            return node1;
        }
        node1.parent = findSet(parent);
        return node1.parent;
    }
}
