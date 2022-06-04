package com.leetcode.year_2020.graph;

import com.util.LogUtil;

import java.util.*;


/**
 * @author neeraj on 26/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class Graph {

    public int totalVertices;
    public List<GraphVertex>[] adjacencyListArr;

    // Now this will be used in Topological Sort.
    public int[] indegree;  // This will represent number of directed edges pointing to the vertex at ith position.
    public int[] outdegree;

    public Graph(int totalVertices) {
        this.totalVertices = totalVertices;
        adjacencyListArr = new LinkedList[totalVertices];
        indegree = new int[totalVertices];
        outdegree = new int[totalVertices];

        // Initialize all those adjacent list to empty.
        for (int i = 0; i < adjacencyListArr.length; i++) {
            adjacencyListArr[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination, boolean isDirected) {
        GraphVertex destinationGraphVertex = new GraphVertex(destination, GraphVertex.Color.WHITE);
        GraphVertex sourceGraphVertex = new GraphVertex(source, GraphVertex.Color.WHITE);

        this.adjacencyListArr[source].add(destinationGraphVertex);

        if (isDirected) {
            indegree[destination] += 1; // Since source is pointing to destination, so destination's indegree increased by 1.
            outdegree[source] += 1;
        }

        if (!isDirected) { // For Undirected graph.... edge goes both way
            // A----B || B----A
            this.adjacencyListArr[destination].add(sourceGraphVertex);
        }
    }

    /**
     * White -> Unvisited (we can visit this node, it is not on a pending path & hasn't been processed)
     * Gray -> Visiting (currently on the path being traversed)
     * Black -> Visited (do not traverse again)
     **/
    public static class GraphVertex {
        public enum Color {WHITE, GRAY, BLACK}

        public Color color;
        public int value;

        public GraphVertex(int value, Color color) {
            this.color = color;
            this.value = value;
        }
    }

    public void bfs(Graph graph, int source) {
        LogUtil.logIt("Breadth First Search of Graph is ", true);
        Set<Integer> seen = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        seen.add(source);

        while (!queue.isEmpty()) {
            int vertexWeAreCurrentlyOn = queue.poll();
            System.out.print(vertexWeAreCurrentlyOn + " ---> ");
            List<GraphVertex> adjacentVertexes = graph.adjacencyListArr[vertexWeAreCurrentlyOn];

            for (GraphVertex vertex : adjacentVertexes) {
                if (!seen.contains(vertex.value)) {
                    queue.add(vertex.value);
                    seen.add(vertex.value);
                }
            }
        }
        LogUtil.newLine();
    }

    public void dfsUsingStack(Graph graph, int source) {
        LogUtil.logIt("Depth First Search of Graph is ", true);
        Set<Integer> seen = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        seen.add(source);

        while (!stack.isEmpty()) {
            int poppedVertex = stack.pop();
            System.out.print(poppedVertex + " --> ");
            List<GraphVertex> adjacentVertexes = graph.adjacencyListArr[poppedVertex];
            for (GraphVertex vertex : adjacentVertexes) {
                if (!seen.contains(vertex.value)) {
                    stack.push(vertex.value);
                    seen.add(vertex.value);
                }
            }
        }
    }

    public void dfs(Graph graph, int source) {
        LogUtil.logIt("Depth First Search of Graph is ", true);
        Set<Integer> seen = new HashSet<>();
        doDFS(graph.adjacencyListArr, source, seen);
        LogUtil.newLine();
    }

    private void doDFS(List<GraphVertex>[] verticesEdges, int source, Set<Integer> seen) {
        System.out.print(source + " --> ");
        seen.add(source);

        List<GraphVertex> adjacentToSource = verticesEdges[source];
        for (GraphVertex vertex : adjacentToSource) {
            if (!seen.contains(vertex.value)) {
                doDFS(verticesEdges, vertex.value, seen);
            }
        }
    }

    public boolean ifGraphHasCycle(Graph graph, int source) {
        if (graph.adjacencyListArr[source].size() == 0) return false;
        GraphVertex graphVertex = graph.adjacencyListArr[source].get(0);
        if (hasCycleFromVertex(graphVertex, graph.adjacencyListArr)) {
            return true;
        }
        return false;
    }

    public boolean ifGraphHasCycle(Graph graph) {
        for (int i = 1; i < graph.totalVertices; i++) {
            List<GraphVertex> vertexes = graph.adjacencyListArr[i];
            for (GraphVertex vertex : vertexes) {
                if (vertex.color == GraphVertex.Color.WHITE && hasCycleFromVertex(vertex, graph.adjacencyListArr)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasCycleFromVertex(GraphVertex vertex, List<GraphVertex>[] adjacencyListArr) {
        if (vertex.color == GraphVertex.Color.GRAY) {
            // We re-visited a node which was in process to find this vertex.
            // hence there is a cycle
            return true;
        }

        // Let's mark this GRAY as an indication
        // that we are currently processing this vertex
        vertex.color = GraphVertex.Color.GRAY;

        // Now let's run through all the adjacent of this vertex
        List<GraphVertex> adjacent = adjacencyListArr[vertex.value];
        for (GraphVertex adjacentVertex : adjacent) {
            if (adjacentVertex.color != GraphVertex.Color.BLACK) {
                if (hasCycleFromVertex(adjacentVertex, adjacencyListArr)) {
                    return true;
                }
            }
        }

        // Now we have traversed through this complete path started from vertex.
        // so let's put this into set of black or visited nodes
        vertex.color = GraphVertex.Color.BLACK;
        return false;
    }

    private void printTopologicalSortViaDFS(Graph graph) {
        /**
         * We'll do the DFS from any random node
         * and once we reached the end of depth in that path we will add the node and
         * finally reverse the output and we will get out topological sort.
         */
        boolean[] visited = new boolean[graph.totalVertices];
        Stack<Integer> topologicalSortInReverseOrder = new Stack<>(); // This will hold our Topological Sort in reverse order due to LIFO
        for (int i = 0; i < graph.totalVertices; i++) {
            if (!visited[i]) {
                doDFSForTopologicalSort(graph.adjacencyListArr, i, visited, topologicalSortInReverseOrder);
            }
        }
        while (!topologicalSortInReverseOrder.isEmpty()) {
            System.out.print(topologicalSortInReverseOrder.pop() + " ---> ");
        }
    }

    private void doDFSForTopologicalSort(List<GraphVertex>[] adjacencyListArr,
                                         int source, boolean[] visited,
                                         Stack<Integer> topologicalSortInReverseOrder) {
        visited[source] = true;

        for (GraphVertex adjacent : adjacencyListArr[source]) {
            if (!visited[adjacent.value]) {
                doDFSForTopologicalSort(adjacencyListArr, adjacent.value, visited, topologicalSortInReverseOrder);
            }
        }


        // Now we have explored all other neighbours and nothing's left to explore
        topologicalSortInReverseOrder.add(source);
    }

    private void printTopologicalSortUsingIndegree(Graph graph) {
        Queue<Integer> queue = new LinkedList<>();
        // We will only put those items in the queue whose indegree is 0 means
        // they don't rely on anyone to be finished.
        int[] indegree = graph.indegree;
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> topologicalSort = new ArrayList<>();

        /**
         * Now we will pop item from the queue until it's empty and put them into our topological sort.
         * Also since this popped task is completed we can reduce the indegree of task which were dependent on it.
         */
        while (!queue.isEmpty()) {
            int task = queue.poll();
            topologicalSort.add(task);
            for (GraphVertex adjacentTask : graph.adjacencyListArr[task]) {
                indegree[adjacentTask.value] -= 1;
                if (indegree[adjacentTask.value] == 0) {
                    queue.add(adjacentTask.value);
                }
            }
        }
        System.out.println(topologicalSort);
    }

    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, true);
        graph.addEdge(0, 3, true);
        graph.addEdge(1, 2, true);
        graph.addEdge(2, 3, true);

        graph.printTopologicalSortViaDFS(graph);

        /**
         * For Graph mentioned here  shorturl.at/qvNR2  the Topological Sort is
         */
        LogUtil.logIt("Topological Sort using Indegree");
        graph = new Graph(7);
        graph.addEdge(0, 1, true);
        graph.addEdge(0, 2, true);
        graph.addEdge(1, 2, true);
        graph.addEdge(1, 5, true);
        graph.addEdge(2, 3, true);
        graph.addEdge(5, 3, true);
        graph.addEdge(5, 4, true);
        graph.addEdge(6, 1, true);
        graph.addEdge(6, 5, true);
        graph.printTopologicalSortUsingIndegree(graph);
    }
}
