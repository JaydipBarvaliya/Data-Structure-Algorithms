package com.geeksforgeeks.graph;

import com.datastructures.array.ArrayUtil;
import com.geeksforgeeks.heap.MinHeap;

import java.util.*;

public class GraphUtil {

    static class Graph {
        int V;
        int[] inDegree; //integer array to store the indegree of nodes
        LinkedList<Integer>[] adjacentListArr;

        public Graph(int totalVertices) {
            V = totalVertices;

            // Define size of array as the no of vertices
            adjacentListArr = new LinkedList[totalVertices];

            // To Store Indegree
            inDegree = new int[totalVertices];

            // Creating New List for each vertex
            // Such that all adjacent nodes can be stored
            for (int i = 0; i < adjacentListArr.length; i++) {
                adjacentListArr[i] = new LinkedList<>();
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);

        addEdge(graph, 0, 1, false);
        addEdge(graph, 0, 4, false);
        addEdge(graph, 1, 2, false);
        addEdge(graph, 1, 3, false);
        addEdge(graph, 1, 4, false);
        addEdge(graph, 2, 3, false);
        addEdge(graph, 3, 4, false);

        printGraph(graph);

        letsDo("BREADTH FIRST TRAVERSAL OF GRAPH");
        graph = new Graph(4);

        addEdge(graph, 0, 1, true);
        addEdge(graph, 0, 2, true);
        addEdge(graph, 1, 2, true);
        addEdge(graph, 2, 0, true);
        addEdge(graph, 2, 3, true);
        addEdge(graph, 3, 3, true);

        BreadthFirstTraversal(graph, 2);
        newLine();

        letsDo("DEPTH FIRST TRAVERSALS");
        DepthFirstTraversal(graph, 2);
        newLine();

        letsDo("Recursive DEPTH FIRST TRAVERSAL");
        recursiveDFS(graph, 2);
        newLine();

        letsDo("Recursive DFT again");
        graph = new Graph(5);
        addEdge(graph, 1, 0, true);
        addEdge(graph, 0, 2, true);
        addEdge(graph, 2, 1, true);
        addEdge(graph, 0, 3, true);
        addEdge(graph, 3, 4, true);
        recursiveDFS(graph, 0);
        newLine();

        letsDo("Find Out WTF is Strongly Connected Component ( KOSARAJUS STRONGLY COMPONENT ALGORITHM )");
        printStronglyConnectedComponents(graph);
        newLine();

        letsDo("Find Mother Vertex");
        findMotherVertex(graph);
        newLine();

        letsDo("Find Transitive Closure using Floyd Warshall Algorithm (Shortest Path from Vertex to Vertex)");
        int[][] distanceMatrix = {
                {0, 3, 6, 15},
                {INF, 0, -2, INF},
                {INF, INF, 0, 2},
                {1, INF, INF, 0}
        };

        floydWarshallAllPairShortestPathAlgo(distanceMatrix);


        letsDo("Find Transitive Closure using DFS which is O(V^2) algo better than Flyod Warshall");
        graph = new Graph(4);

        addEdge(graph, 0, 1, true);
        addEdge(graph, 0, 2, true);
        addEdge(graph, 1, 2, true);
        addEdge(graph, 2, 0, true);
        addEdge(graph, 2, 3, true);

        transitiveClosureUsingDFS(graph);
        newLine();


        letsDo("Let's Check if Directed Graph Has Cycle");
//        graph = new Graph(7);
//        addEdge(graph, 1, 2, true);
//        addEdge(graph, 2, 3, true);
//        addEdge(graph, 1, 3, true);
//
//        addEdge(graph, 4, 1, true);
//        addEdge(graph, 4, 5, true);
//        addEdge(graph, 5, 6, true);
//        addEdge(graph, 6, 4, true);

        graph = new Graph(4);

        addEdge(graph, 0, 1, true);
        addEdge(graph, 0, 2, true);
        addEdge(graph, 1, 2, true);
        addEdge(graph, 2, 0, true);
        addEdge(graph, 2, 3, true);
        addEdge(graph, 3, 3, true);

        System.out.println(directedGraphHasCycle(graph));

        letsDo("Let's Check if Undirected Graph has Cycle");
        // Even though we are doing this check for Undirected graph
        // But we still pass isDirectedGraph as true in addEdge method
        // Because here we are interested in getting unique edges
        // So A-->B will suffice we don't need B-->A
        // Hope I remember when i read it 2-3 months/year later.
        graph = new Graph(4);
        addEdge(graph, 0, 1, true);
        addEdge(graph, 0, 2, true);
        addEdge(graph, 1, 2, true);
        addEdge(graph, 2, 3, true);

        System.out.println(undirectedGraphHasCycle(graph));
        newLine();

        letsDo("Let's Check if Undirected Graph has Cycle using Graph");
        System.out.println(undirectedGraphHasCycleUsingDFS(graph));
        newLine();

        letsDo("Topological Sort");
        graph = new Graph(8);
        addEdge(graph, 1, 3, true);
        addEdge(graph, 2, 3, true);
        addEdge(graph, 2, 5, true);
        addEdge(graph, 3, 4, true);
        addEdge(graph, 4, 6, true);
        addEdge(graph, 5, 6, true);
        addEdge(graph, 6, 7, true);
        doTopologicalSort(graph);


//        printAllTopologicalSort(graph);

        letsDo("Count the Number of Island in the Graph");

        int[][] landAndWaterMatrix = {
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1}
        };
        countNoOfIsland(landAndWaterMatrix);
        newLine();


        letsDo("Find No. of Triangles in a Graph");
        int undirectedGraphMatrix[][] = {
                {0, 1, 1, 0},
                {1, 0, 1, 1},
                {1, 1, 0, 1},
                {0, 1, 1, 0}
        };
        findNoOfTriangles(undirectedGraphMatrix);
        newLine();


        letsDo("Find Minimum Spanning Tree using Kruskal Algorithm");
        GraphUsingDisjointSetDataStructures<Integer> krushkalGraph = new GraphUsingDisjointSetDataStructures(false);
        krushkalGraph.addEdge(1, 2, 3);
        krushkalGraph.addEdge(1, 4, 1);
        krushkalGraph.addEdge(2, 4, 3);
        krushkalGraph.addEdge(2, 3, 1);
        krushkalGraph.addEdge(4, 3, 1);
        krushkalGraph.addEdge(4, 5, 6);
        krushkalGraph.addEdge(3, 5, 5);
        krushkalGraph.addEdge(3, 6, 4);
        krushkalGraph.addEdge(6, 5, 2);

        findMinimumSpanningTree(krushkalGraph);

        letsDo("Find Minimum Spanning Tree using Prim's Algorithm");
        // Reusing the same graph of Kruskal Algo.
        findMinimumSpanningTreeUsingPrimsAlgo(krushkalGraph);


        letsDo("Find Shortest Path from Source Node using Dijkstra Algorithm");
        GraphUsingDisjointSetDataStructures<Integer> dijkstraGraph = new GraphUsingDisjointSetDataStructures(false);
        dijkstraGraph.addEdge(1, 2, 5);
        dijkstraGraph.addEdge(2, 3, 2);
        dijkstraGraph.addEdge(3, 4, 3);
        dijkstraGraph.addEdge(1, 4, 9);
        dijkstraGraph.addEdge(1, 5, 2);
        dijkstraGraph.addEdge(5, 6, 3);
        dijkstraGraph.addEdge(6, 4, 2);
        dijkstraAlgorithmFormShortestPathFromSource(dijkstraGraph, dijkstraGraph.getVertex(1));


        letsDo("BellMan Ford Algorithm to find the Shortest path from source to all other vertices");
        GraphUsingDisjointSetDataStructures<Integer> bellmanGraph = new GraphUsingDisjointSetDataStructures<>(true);
        bellmanGraph.addEdge(0, 3, 8);
        bellmanGraph.addEdge(0, 1, 4);
        bellmanGraph.addEdge(0, 2, 5);
        bellmanGraph.addEdge(1, 2, -3);
        bellmanGraph.addEdge(2, 4, 4);
        bellmanGraph.addEdge(3, 4, 2);
        bellmanGraph.addEdge(4, 3, 1);

        bellManFordAlgorithmFormShortestPathFromSource(bellmanGraph, bellmanGraph.getVertex(0));
        newLine();

        letsDo("TOPIC:: Connectivity in Graph ");

        letsDo("CONNECTIVITY (1) :: Find if there is a path between two vertices in a directed graph");
        graph = new Graph(4);
        addEdge(graph, 0, 1, true);
        addEdge(graph, 0, 2, true);
        addEdge(graph, 1, 2, true);
        addEdge(graph, 2, 0, true);
        addEdge(graph, 2, 3, true);
        addEdge(graph, 3, 3, true);
        isPathExistBetween2Vertices(graph, 1, 3);
        isPathExistBetween2Vertices(graph, 0, 3);
        isPathExistBetween2Vertices(graph, 2, 1);
        isPathExistBetween2Vertices(graph, 3, 2);

        letsDo("CONNECTIVITY (2) :: Print All Path between two vertices in a directed graph");
        graph = new Graph(4);
        addEdge(graph, 0, 1, true);
        addEdge(graph, 0, 2, true);
        addEdge(graph, 0, 3, true);
        addEdge(graph, 2, 0, true);
        addEdge(graph, 2, 1, true);
        addEdge(graph, 1, 3, true);

        printAllPathBetween2Vertices(graph, 2, 3);


        letsDo("SOLVE BACKTRACKING PROBLEMS");
        letsDo("Find if there is a path of more than k length from a source");

        isPathExistForMoreThanKLength(getUndirectedGraph(), 58, 0);
        isPathExistForMoreThanKLength(getUndirectedGraph(), 62, 0);

        letsDo("Solve NQueen Problem");
        solveNQueenProblem(4);

        letsDo("Solve Rat in a Maze Problem");
        solveRatInAMaze(getMazeForRatProblem());

        letsDo("Solve Snake nd Ladder Problem");
        System.out.println("Minimum no of dice throws " + solveSnakeNdLadderProblem(getSnakeAndLadderPuzzle(30)));


        letsDo("Minimize Cash-flow Problem - SPLITWISE");
        int cashFlow[][] = {
                {0, 1000, 2000},
                {0, 0, 5000},
                {0, 0, 0}
        };

        minCashFlowSplitwise(cashFlow, 3);


    }

    public static int[] getSnakeAndLadderPuzzle(int gameSize) {
        int[] moves = new int[gameSize];

        // Put moves as -1 for all the vertex assuming there is not snake and ladders in the game
        for (int i = 0; i < gameSize; i++) {
            moves[i] = -1;
        }

        // Ladders
        moves[2] = 21;
        moves[4] = 7;
        moves[10] = 25;
        moves[19] = 28;

        // Snakes
        moves[26] = 0;
        moves[20] = 8;
        moves[16] = 3;
        moves[18] = 6;

        return moves;
    }

    public static int[][] getMazeForRatProblem() {
        int maze[][] = {{1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}
        };
        return maze;
    }

    public static int[][] getUndirectedGraph() {
        int[][] graph = new int[9][9];
        graph[0][1] = 4;
        graph[1][0] = 4;
        graph[1][2] = 8;
        graph[2][1] = 8;
        graph[0][7] = 8;
        graph[7][0] = 8;
        graph[7][8] = 7;
        graph[8][7] = 7;
        graph[1][7] = 11;
        graph[7][1] = 11;
        graph[2][8] = 2;
        graph[8][2] = 2;
        graph[8][6] = 6;
        graph[6][8] = 6;
        graph[5][6] = 2;
        graph[6][5] = 2;
        graph[2][5] = 4;
        graph[5][2] = 4;
        graph[2][3] = 7;
        graph[3][2] = 7;
        graph[3][5] = 14;
        graph[5][3] = 14;
        graph[3][4] = 9;
        graph[4][3] = 9;
        graph[5][4] = 10;
        graph[4][5] = 10;
        return graph;
    }

    public static void letsDo(String task) {
        System.out.println("==================" + task + "======================");
    }

    public static void newLine() {
        System.out.println();
    }


    public static void addEdge(Graph graph, int source, int destination, Boolean isDirectedGraph) {
        // Adding an edge from Source to Destination
        graph.adjacentListArr[source].add(destination);

        // increasing indegree of destination by 1;
        graph.inDegree[destination] = graph.inDegree[destination] + 1;

        if (!isDirectedGraph)
        //Since It's an undirected graph so we have to have edge from both source-destination and vice versa
        {
            graph.adjacentListArr[destination].add(source);
        }
    }

    public static void printGraph(Graph graph) {
        LinkedList<Integer>[] adjacentListArr = graph.adjacentListArr;
        for (int i = 0; i < adjacentListArr.length; i++) {
            System.out.println("Adjacency List of " + i);
            System.out.println(adjacentListArr[i]);
        }
    }

    public static void BreadthFirstTraversal(Graph graph, int source) {
        LinkedList<Integer>[] adjacentListArr = graph.adjacentListArr;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.V];
        queue.add(source);
        visited[source] = true;
        Integer temp;

        while (!queue.isEmpty()) {
            temp = queue.poll();
            System.out.print(temp + "\t");

            LinkedList<Integer> adjacentList = adjacentListArr[temp];

            for (Integer i : adjacentList) {
                if (visited[i] == false) {
                    visited[i] = true;
                    queue.add(i);
                }
            }

        }
    }

    public static void DepthFirstTraversal(Graph graph, int source) {
        LinkedList<Integer>[] adjacentListArr = graph.adjacentListArr;
        boolean[] visited = new boolean[graph.V];
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        Integer popped;

        while (!stack.isEmpty()) {
            popped = stack.pop();

            if (!visited[popped]) {
                System.out.print(popped + "\t");
                visited[popped] = true;
            }
            LinkedList<Integer> adjacentVertex = adjacentListArr[popped];

            for (Integer i : adjacentVertex) {
                if (!visited[i]) {
                    stack.push(i);
                }
            }
        }
    }

    public static void recursiveDFS(Graph graph, int source) {
        LinkedList<Integer>[] adjacentListArr = graph.adjacentListArr;
        boolean[] visited = new boolean[graph.V];
        recursiveDFSUtil(adjacentListArr, source, visited);
    }

    public static void recursiveDFSUtil(LinkedList<Integer>[] adjacentListArr, int source, boolean[] visited) {
        visited[source] = true;
        System.out.print(source + "\t");

        LinkedList<Integer> adjacentList = adjacentListArr[source];
        for (Integer i : adjacentList) {
            if (!visited[i]) {
                recursiveDFSUtil(adjacentListArr, i, visited);
            }
        }
    }

    public static void printStronglyConnectedComponents(Graph graph) {
        boolean[] visited = new boolean[graph.V];

        Stack<Integer> vertexOrderedByFinishTimeInDecreasingOrder = new Stack<>();

        // Pass 1 Do DFS for all vertices
        for (int source = 0; source < graph.V; source++) {
            if (!visited[source]) {
                recursiveDFSUtilForSCC(graph.adjacentListArr, source, visited, vertexOrderedByFinishTimeInDecreasingOrder);
            }
        }
        newLine();
        // Before Pass 2, we have to change the direction of each edge, into reverse direction
        graph = reverseGraphEdges(graph);


        // Now 2nd Pass While Stack is not empty
        // Clear your visited array

        visited = new boolean[graph.V];
        System.out.println("Strongly Connected Components are ");
        while (!vertexOrderedByFinishTimeInDecreasingOrder.isEmpty()) {
            Integer popped = vertexOrderedByFinishTimeInDecreasingOrder.pop();
            if (!visited[popped]) {
                recursiveDFSUtil(graph.adjacentListArr, popped, visited);
                newLine();
            }
        }
    }

    public static Graph reverseGraphEdges(Graph graph) {
        Graph outputGraph = new Graph(graph.V);
        for (int i = 0; i < graph.V; i++) {
            LinkedList<Integer> adjacentList = graph.adjacentListArr[i];
            for (Integer vertex : adjacentList) {
                outputGraph.adjacentListArr[vertex].add(i);
            }
        }
        return outputGraph;
    }

    public static void recursiveDFSUtilForSCC(LinkedList<Integer>[] adjacentListArr, int source, boolean[] visited, Stack<Integer> stack) {
        visited[source] = true;
        LinkedList<Integer> adjacentList = adjacentListArr[source];
        for (Integer i : adjacentList) {
            if (!visited[i]) {
                recursiveDFSUtilForSCC(adjacentListArr, i, visited, stack);
            }
        }
        // After Successfully explored the child of this vertex, Store it into the stack
        stack.push(source);
    }

    public static void findMotherVertex(Graph graph) {
        boolean[] visited = new boolean[graph.V];
        int lastVisitedNode = -1;
        for (int i = 0; i < graph.V; i++) {
            if (!visited[i]) {
                doDFSForMotherVertexProblem(graph.adjacentListArr, i, visited);
                lastVisitedNode = i;
            }
        }

        // Now we have last Visited Node, Let's do the DFS now.
        visited = new boolean[graph.V];

        doDFSForMotherVertexProblem(graph.adjacentListArr, lastVisitedNode, visited);

        // Now Traverse the visited array and check if all of them are visited
        for (boolean value : visited) {
            if (!value) {
                System.out.println("No Mother Vertex Present");
                return;
            }
        }
        System.out.println(lastVisitedNode + " is the mother vertex");
    }

    public static void doDFSForMotherVertexProblem(LinkedList<Integer>[] adjacentListArr, int source, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        Integer popped;

        while (!stack.isEmpty()) {
            popped = stack.pop();

            if (!visited[popped]) {
                visited[popped] = true;
            }

            LinkedList<Integer> adjacentNodes = adjacentListArr[popped];
            for (Integer node : adjacentNodes) {
                if (!visited[node]) {
                    stack.push(node);
                }
            }
        }
    }

    /**
     * Tansitive Closure of A Graph, using Floyd Warshall Algorithm
     * INF means there is no path between nodes and it will take infinity distance to reach to that Node
     *
     * @param distanceMatrix
     */
    public static int INF = 1000000;

    public static void floydWarshallAllPairShortestPathAlgo(int[][] distanceMatrix) {
        int[][] distance = new int[distanceMatrix.length][distanceMatrix.length];
        int[][] path = new int[distanceMatrix.length][distanceMatrix.length];


        // Let's populate distance and path matrix
        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[i].length; j++) {
                distance[i][j] = distanceMatrix[i][j];

                if (distanceMatrix[i][j] != INF && i != j) {
                    path[i][j] = i;
                } else {
                    path[i][j] = -1;
                }
            }
        }

        // Let's Populate the distance and Path matrix with shortest path
        for (int k = 0; k < distance.length; k++) {
            for (int i = 0; i < distance.length; i++) {
                for (int j = 0; j < distance.length; j++) {
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }
        letsDo("Before Finding Shortest Path");
        ArrayUtil.print2DArray(distanceMatrix, distanceMatrix.length, distanceMatrix.length);
        newLine();
        letsDo("After Finding Shortest Path");
        ArrayUtil.print2DArray(distance, distance.length, distance.length);
        newLine();
    }

    public static int[][] transitiveClosure;

    public static void transitiveClosureUsingDFS(Graph graph) {
        transitiveClosure = new int[graph.V][graph.V];

        for (int i = 0; i < graph.V; i++) {
            doDFSForTransitiveClosure(graph, i, i);
        }

        ArrayUtil.print2DArray(transitiveClosure, graph.V, graph.V);
    }

    public static void doDFSForTransitiveClosure(Graph graph, int source, int destination) {
        transitiveClosure[source][destination] = 1;

        for (Integer adjacentNode : graph.adjacentListArr[destination]) {
            if (transitiveClosure[source][adjacentNode] == 0) {
                doDFSForTransitiveClosure(graph, source, adjacentNode);
            }
        }
    }

    public static Boolean directedGraphHasCycle(Graph graph) {
        // Set of Unvisited Vertex
        Set<Integer> whiteSet = new HashSet<>();

        // Set of currently Visiting Vertex
        Set<Integer> graySet = new HashSet<>();

        // Set of completely visited Vertex
        Set<Integer> blackSet = new HashSet<>();

        // Let's put all unvisited vertex in whiteSet
        for (int i = 1; i < graph.V; i++) {
            whiteSet.add(i);
        }

        while (!whiteSet.isEmpty()) {
            Integer current = whiteSet.iterator().next();

            if (dfsForIdentifyingGraphCycle(graph, current, whiteSet, graySet, blackSet)) {
                return true;
            }
        }
        return false;
    }

    public static boolean dfsForIdentifyingGraphCycle(Graph graph, Integer current, Set<Integer> whiteSet, Set<Integer> graySet, Set<Integer> blackSet) {

        // Move Vertex from White Set(unVisited) to Gray Set(currently Visiting);
        moveVertex(current, whiteSet, graySet);

        LinkedList<Integer> adjacentVertices = graph.adjacentListArr[current];
        for (Integer adjacent : adjacentVertices) {

            if (blackSet.contains(adjacent)) { // Already visited node So skip
                continue;
            }

            if (graySet.contains(adjacent)) { // Found a Node whose child is being explored, i.e Cycle
                return true;
            }

            if (dfsForIdentifyingGraphCycle(graph, adjacent, whiteSet, graySet, blackSet)) {
                return true;
            }
        }

        // Current Node processing is done, Let's move it to Black Node
        moveVertex(current, graySet, blackSet);

        return false;
    }

    public static void moveVertex(Integer vertexToBeMoved, Set<Integer> sourceSet, Set<Integer> destinationSet) {
        sourceSet.remove(vertexToBeMoved);
        destinationSet.add(vertexToBeMoved);
    }

    public static boolean undirectedGraphHasCycle(Graph graph) {
        DisjointSet disjointSet = new DisjointSet();

        for (int i = 0; i < graph.V; i++) {
            disjointSet.makeSet(i);
        }

        for (int source = 0; source < graph.V; source++) {

            LinkedList<Integer> adjacentList = graph.adjacentListArr[source];

            for (Integer adjacentVertex : adjacentList) {
                long parent1 = disjointSet.findSet(source);
                long parent2 = disjointSet.findSet(adjacentVertex);

                if (parent1 == parent2) {
                    return true;
                }
                disjointSet.union(source, adjacentVertex);
            }
        }
        return false;
    }

    public static boolean undirectedGraphHasCycleUsingDFS(Graph graph) {
        boolean[] visited = new boolean[graph.V];

        for (int source = 0; source < graph.V; source++) {
            if (!visited[source]) {
                if (undirectedGraphHasCycleUtil(graph, source, visited, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean undirectedGraphHasCycleUtil(Graph graph, int source, boolean[] visited, int parent) {

        // Marking popped vertex as visited
        visited[source] = true;

        for (Integer adjacent : graph.adjacentListArr[source]) {

            // If Adjacent vertex not visited, let's explore this vertex and it's child
            if (!visited[adjacent]) {
                if (undirectedGraphHasCycleUtil(graph, adjacent, visited, source)) {
                    return true;
                }
            }

            // If Adjacent is already visited and not the parent of current Source vertex
            // Then there is a cycle
            else if (adjacent != parent) {
                return true;
            }
        }
        return false;
    }


    public static void doTopologicalSort(Graph graph) {
        boolean[] visited = new boolean[graph.V];
        Stack<Integer> stack = new Stack<>();

        for (int i = 1; i < graph.V; i++) {
            if (!visited[i]) {
                doDFSForTopologicalSort(graph, i, visited, stack);
            }
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + ",");
        }
        newLine();
    }

    public static void doDFSForTopologicalSort(Graph graph, Integer source, boolean[] visited, Stack<Integer> stack) {

        visited[source] = true;

        for (Integer adjacent : graph.adjacentListArr[source]) {
            if (!visited[adjacent]) {
                doDFSForTopologicalSort(graph, adjacent, visited, stack);
            }
        }
        // Explored all Adjacent Nodes of this vertex, So let's push into Stack of Topological Sorted vertices
        stack.push(source);
    }


    public static List<Integer> topologicalSortList = new ArrayList<>();

    public static void printAllTopologicalSort(Graph graph) {
        boolean[] visited = new boolean[graph.V];

        allTopologicalSort(graph, visited);
    }

    public static void allTopologicalSort(Graph graph, boolean[] visited) {

        // To indicate whether all topological are found or not
        boolean flag = false;

        for (int i = 0; i < graph.V; i++) {

            // If indegree is 0 and not yet visited then
            // only choose that vertex
            if (!visited[i] && graph.inDegree[i] == 0) {
                visited[i] = true;
                for (Integer adjacent : graph.adjacentListArr[i]) {
                    graph.inDegree[adjacent]--;
                }

                topologicalSortList.add(i);
                allTopologicalSort(graph, visited);

                visited[i] = false;
                for (Integer adjacent : graph.adjacentListArr[i]) {
                    graph.inDegree[adjacent]++;
                }
                topologicalSortList.remove(topologicalSortList.indexOf(i));
                flag = true;
            }
        }

        // We reach here if all vertices are visited.
        // So we print the solution here
        if (!flag) {
            for (int w = 0; w < graph.V; w++) {
                System.out.print(topologicalSortList.get(w) + " ");
            }
            System.out.print("\n");
        }
    }

    public static int ROWS = 0;
    public static int COLS = 0;

    public static void countNoOfIsland(int[][] landAndWaterMatrix) {

        ROWS = landAndWaterMatrix.length;
        COLS = landAndWaterMatrix[0].length;

        boolean[][] visited = new boolean[ROWS][COLS];
        int noOfIsland = 0;

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (landAndWaterMatrix[row][col] == 1 && !visited[row][col]) {
                    DFS(landAndWaterMatrix, row, col, visited);
                    ++noOfIsland;
                }
            }
        }

        System.out.print("Total No of Island is " + noOfIsland);
    }

    /**
     * DFS for No Of Island Problem
     */
    public static void DFS(int[][] landAndWaterMatrix, int row, int col, boolean[][] visited) {

        // Let's calculate the directions of 8 Neighbours
        int[] rowNums = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colNums = {-1, 0, 1, -1, 1, -1, 0, 1};

        // Make current vertex as visited
        visited[row][col] = true;

        // Let's recursively traverse the 8 neighbours
        for (int k = 0; k < 8; k++) {
            if (isSafe(landAndWaterMatrix, row + rowNums[k], col + colNums[k], visited)) {
                DFS(landAndWaterMatrix, row + rowNums[k], col + colNums[k], visited);
            }
        }

    }

    // Method to check whether going to this coordinates will not throw any exception
    // and we are moving to unvisited Rocks/Land only
    public static boolean isSafe(int[][] landAndWaterMatrix, int row, int col, boolean[][] visited) {

        return (row >= 0) && (row < ROWS) &&
                (col >= 0) && (col < COLS) &&
                (landAndWaterMatrix[row][col] == 1) && (!visited[row][col]);

    }

    /**
     * Find A^3 Then Find Trace (Sum of Diagonal Elements in A^3) and return Trace/ (3 * 2)
     *
     * @param undirectedGraphMatrix
     */
    public static void findNoOfTriangles(int[][] undirectedGraphMatrix) {
        int V = undirectedGraphMatrix.length;
        // To Store graph^2
        int[][] aux2 = new int[V][V];

        // To Store graph^3
        int[][] aux3 = new int[V][V];

        // Initialising aux matrices
        // with 0
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                aux2[i][j] = aux3[i][j] = 0;
            }
        }

        // aux2 is graph^2 now
        // printMatrix(aux2)
        multiply(undirectedGraphMatrix, undirectedGraphMatrix, aux2, V);

        multiply(undirectedGraphMatrix, aux2, aux3, V);


        System.out.println("No Of Triangles is " + getTrace(aux3, V) / 6);
    }

    public static void multiply(int[][] A, int[][] B, int[][] C, int V) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                C[i][j] = 0;
                for (int k = 0; k < V; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }

    // Utility function to calculate
    // trace of a matrix (sum of
    // diagonal elements)
    static int getTrace(int graph[][], int V) {
        int trace = 0;

        for (int i = 0; i < V; i++) {
            trace += graph[i][i];
        }
        return trace;
    }


    public static void findMinimumSpanningTree(GraphUsingDisjointSetDataStructures<Integer> krushkalGraph) {
        List<Edge<Integer>> allEdges = krushkalGraph.getAllEdges();
        List<Edge<Integer>> result = new ArrayList<>();

        // Sort Edges based on increasing weight
        Collections.sort(allEdges, Comparator.comparingInt(Edge::getWeight));


        DisjointSet disjointSet = new DisjointSet();

        // Make set for all vertex
        for (Vertex<Integer> vertex : krushkalGraph.getAllVertex()) {
            disjointSet.makeSet(vertex.id);
        }

        for (Edge<Integer> edge : allEdges) {

            Long representative1 = disjointSet.findSet(edge.getVertex1().id);
            Long representative2 = disjointSet.findSet(edge.getVertex2().id);

            // Means both belong to same set, So no need to add them in minimum Spanning tree
            if (representative1 == representative2) {
                continue;
            }

            // We can avoid this above representative check
            // Since union operation is taking care of this.
            if (disjointSet.union(edge.getVertex1().id, edge.getVertex2().id)) {
                result.add(edge);
            }
        }

        // Edges in Minimum Spanning Tree
        for (Edge edge : result) {
            System.out.println(edge);
        }
    }

    public static void findMinimumSpanningTreeUsingPrimsAlgo(GraphUsingDisjointSetDataStructures<Integer> primsGraph) {

        // Heap + Map data structure to maintain and extract the minimum distance
        MinHeap<Vertex<Integer>> minHeap = new MinHeap<>();

        // Map to tell from which edge we reached this vertex
        // This will help us to get the edges in the result
        Map<Vertex<Integer>, Edge<Integer>> vertexToEdgeMap = new HashMap<>();


        //stores final result
        List<Edge<Integer>> result = new ArrayList<>();


        // Insert all vertices in the Map with distance/weight as Infinity
        for (Vertex<Integer> vertex : primsGraph.getAllVertex()) {
            minHeap.addNodeToHeap(Integer.MAX_VALUE, vertex);
        }

        // Let's Start Finding the Minimum Spanning Tree
        //1) Start with let's say 1st node

        Vertex<Integer> startVertex = primsGraph.getAllVertex().iterator().next();

        //2) Marking startVertex weight as 0, because to reach start Vertex no distance needs to be travelled
        minHeap.decreaseNodeWeight(startVertex, 0);

        while (!minHeap.empty()) {

            // Extract the minimum Vertex from MinHeap
            MinHeap<Vertex<Integer>>.Node minHeapNode = minHeap.extractMinimumNode();
            Vertex<Integer> current = minHeapNode.key;

            //Let's check vertexToEdge map, if this is the first vertex
            // Or we are coming to this vertex from any edge, So add them to the result
            if (vertexToEdgeMap.containsKey(current)) {
                result.add(vertexToEdgeMap.get(current));
            }

            // Now get all the adjacent neighbours of current Vertex
            for (Edge<Integer> adjacentEdge : current.getEdges()) {
                Vertex<Integer> adjacentVertex = getVertexFromEdge(current, adjacentEdge);

                // If the weight in the Map is greater than the actual edge we need to decrease the weight
                // in the MinHeap
                if (minHeap.contains(adjacentVertex) && minHeap.getWeight(adjacentVertex) > adjacentEdge.getWeight()) {
                    minHeap.decreaseNodeWeight(adjacentVertex, adjacentEdge.getWeight());

                    // Add the Vertex to the VertexToEdgeMap
                    vertexToEdgeMap.put(adjacentVertex, adjacentEdge);
                }
            }
        }

        printMinimumSpanningTree(result);

    }

    public static void printMinimumSpanningTree(List<Edge<Integer>> edges) {
        for (Edge<Integer> edge : edges) {
            System.out.println(edge.getVertex1() + "----->" + edge.getVertex2() + " :: Weight (" + edge.getWeight() + ")");
        }
    }

    /**
     * This method basically gives the other end of vertex from an Edge
     * <p>
     * If getVertexFromEdge called from vertex1 then return vertex2 and vice-versa
     *
     * @param current
     * @param adjacentEdge
     * @return
     */
    public static Vertex<Integer> getVertexFromEdge(Vertex<Integer> current, Edge<Integer> adjacentEdge) {
        return adjacentEdge.getVertex1() == current ? adjacentEdge.getVertex2() : adjacentEdge.getVertex1();
    }

    public static void dijkstraAlgorithmFormShortestPathFromSource(GraphUsingDisjointSetDataStructures<Integer> dijkstraGraph, Vertex<Integer> sourceVertex) {

        MinHeap<Vertex<Integer>> minHeap = new MinHeap<>();

        // Map that will store the distance of each vertex from the Source Vertex
        Map<Vertex<Integer>, Integer> distanceMap = new HashMap<>();

        // Map that will store Vertex and It's parent means from which vertex we reached to this vertex
        Map<Vertex<Integer>, Vertex<Integer>> pathMap = new HashMap<>();

        // Let's put all the vertices into the min Heap
        for (Vertex<Integer> vertex : dijkstraGraph.getAllVertex()) {
            minHeap.addNodeToHeap(Integer.MAX_VALUE, vertex);
        }

        // Distance required to reach sourceVertex from sourceVertex is 0
        minHeap.decreaseNodeWeight(sourceVertex, 0);

        // Adding the source Vertex to distance map as well
        distanceMap.put(sourceVertex, 0);

        // and also to the vertexParent/Path Map, for source vertex Parent vertex will be null;
        pathMap.put(sourceVertex, null);

        while (!minHeap.empty()) {
            MinHeap<Vertex<Integer>>.Node minheapNode = minHeap.extractMinimumNode();
            Vertex<Integer> current = minheapNode.key;
            distanceMap.put(current, minheapNode.weight);

            // Now let's traverse it's edges
            for (Edge<Integer> edge : current.getEdges()) {
                Vertex<Integer> adjacent = getVertexFromEdge(current, edge);

                if (!minHeap.contains(adjacent)) {
                    continue;
                }

                int newWeight = distanceMap.get(current) + edge.getWeight();
                if (minHeap.getWeight(adjacent) > newWeight) {
                    minHeap.decreaseNodeWeight(adjacent, newWeight);
                    pathMap.put(adjacent, current);
                }
            }
        }

        System.out.println("====================>Shortest distance Distance from Source Vertex is below<===========================");
        distanceMap.entrySet().stream()
                .filter(p -> p.getKey().id != sourceVertex.id) // We don't want to print the source vertex hence filtering it
                .forEach((entry) -> System.out.println("Distance from " + sourceVertex.id + " ----> " + entry.getKey().id + " is " + entry.getValue()));

        System.out.println("====================>How we reached to a particular vertex<===========================");
        pathMap.entrySet().stream().filter(p -> p.getValue() != null)
                .forEach((entry) -> System.out.println("We reached " + entry.getKey().id + " from " + entry.getValue().id));

        printShortestPathBetweenTwoVertex(pathMap, 1, 4, dijkstraGraph);
        printShortestPathBetweenTwoVertex(pathMap, 1, 3, dijkstraGraph);

    }

    public static void printShortestPathBetweenTwoVertex(Map<Vertex<Integer>, Vertex<Integer>> pathMap, long firstVertex, long secondVertex, GraphUsingDisjointSetDataStructures<Integer> dijkstraGraph) {
        Stack<Long> shortestPath = new Stack<>();

        Vertex<Integer> secondVertexObj = dijkstraGraph.getVertex(secondVertex);
        Vertex<Integer> firstVertexObj = dijkstraGraph.getVertex(firstVertex);

        shortestPath.push(secondVertexObj.id);

        while (true) {
            Vertex<Integer> parent = pathMap.get(secondVertexObj);
            if (parent == firstVertexObj) {
                shortestPath.push(parent.id);
                break;
            }
            shortestPath.push(parent.id);
            secondVertexObj = parent;
        }
        newLine();
        letsDo("Path from " + firstVertex + " to " + secondVertex + " is ");
        while (!shortestPath.isEmpty()) {
            System.out.print(shortestPath.pop() + "--->");
        }
        newLine();
    }

    public static void bellManFordAlgorithmFormShortestPathFromSource(GraphUsingDisjointSetDataStructures<Integer> bellmanGraph, Vertex<Integer> sourceVertex) {

        // Util Maps required to store the distance and the parent
        Map<Vertex<Integer>, Integer> distanceMap = new HashMap<>();
        Map<Vertex<Integer>, Vertex<Integer>> parentMap = new HashMap<>();

        // Let's mark all vertices with the distance and parent as INFINITY and NUll respectively
        for (Vertex<Integer> vertex : bellmanGraph.getAllVertex()) {
            distanceMap.put(vertex, Integer.MAX_VALUE);
            parentMap.put(vertex, null);
        }

        // For Source vertex the minimum distance to reach it is 0
        distanceMap.put(sourceVertex, 0);

        //Now let's relax edges repeatedly V - 1 times
        for (int i = 0; i < bellmanGraph.getAllVertex().size() - 1; i++) {

            // Now let's pick random edges 1 by 1
            for (Edge<Integer> edge : bellmanGraph.getAllEdges()) {
                Vertex<Integer> v1 = edge.getVertex1();
                Vertex<Integer> v2 = edge.getVertex2();

                // So relax has only 1 rule
                // relax (u, v)
                // if( distance [v] > distance [u] + weightOfEdge(u,v))
                //    then distance[v] = distance[u] + weightOFEdge(u,v);
                //    parent[v] = u;
                if (distanceMap.get(v2) > edge.getWeight() + distanceMap.get(v1)) {
                    distanceMap.put(v2, edge.getWeight() + distanceMap.get(v1));
                    parentMap.put(v2, v1);
                }
            }
        }

        //relax all edges again. If we still get lesser distance it means
        //there is negative weight cycle in the graph. Throw exception in that
        //case
        for (Edge<Integer> edge : bellmanGraph.getAllEdges()) {
            Vertex<Integer> v1 = edge.getVertex1();
            Vertex<Integer> v2 = edge.getVertex2();

            if (distanceMap.get(v2) > edge.getWeight() + distanceMap.get(v1)) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Negative Cycle Found, throwing Exception");
                return;
            }
        }

        // If No negative cycle, let's print shortest distance
        System.out.println("====================>Shortest distance Distance from Source Vertex is below<===========================");
        distanceMap.entrySet().stream()
                .filter(p -> p.getKey().id != sourceVertex.id) // We don't want to print the source vertex hence filtering it
                .forEach((entry) -> System.out.println("Distance from " + sourceVertex.id + " ----> " + entry.getKey().id + " is " + entry.getValue()));
    }

    public static void isPathExistBetween2Vertices(Graph graph, int sourceVertex, int destinationVertex) {
        boolean[] visited = new boolean[graph.V];
        pathExist = false;
        Stack<Integer> path = new Stack<>();
        doDFSForCheckingIfPathExistBetween2Vertices(graph, sourceVertex, destinationVertex, visited, path);

        if (!pathExist) {
            System.out.println("Path doesn't exist for " + sourceVertex + " and " + destinationVertex);
        }
    }

    public static boolean pathExist = false;

    public static void doDFSForCheckingIfPathExistBetween2Vertices(Graph graph, int sourceVertex, int destinationVertex, boolean[] visited, Stack<Integer> path) {
        visited[sourceVertex] = true;
        path.push(sourceVertex);
        if (sourceVertex == destinationVertex) {
            letsDo("Yes Path Exist between " + sourceVertex + " vertex and " + destinationVertex + " vertex");
            System.out.println(path);
            pathExist = true;
        }

        for (Integer adjacentVertex : graph.adjacentListArr[sourceVertex]) {
            if (!visited[adjacentVertex]) {
                doDFSForCheckingIfPathExistBetween2Vertices(graph, adjacentVertex, destinationVertex, visited, path);
            }
        }
        path.pop();
    }

    public static void printAllPathBetween2Vertices(Graph graph, int sourceVertex, int destinationVertex) {
        boolean[] visited = new boolean[graph.V];
        dfsForAllPathBetween2Vertices(graph, sourceVertex, destinationVertex, visited, new Stack<>());
    }

    //Only special care to print all path is just mark the vertex after exploring it's depth as false so that
    // It can be picked up in next route
    public static void dfsForAllPathBetween2Vertices(Graph graph, int sourceVertex, int destinationVertex, boolean[] visited, Stack<Integer> path) {
        visited[sourceVertex] = true;
        path.push(sourceVertex);
        if (sourceVertex == destinationVertex) {
            System.out.println(path);
        }
        for (Integer adjacentVertex : graph.adjacentListArr[sourceVertex]) {
            if (!visited[adjacentVertex]) {
                dfsForAllPathBetween2Vertices(graph, adjacentVertex, destinationVertex, visited, path);
            }
        }
        path.pop();
        visited[sourceVertex] = false;
    }

    /**
     * Method to check if there exist a path starting from source to any vertex having total weight > K
     *
     * @param backtrackingGraph
     * @param vertex
     * @param i
     */
    public static void isPathExistForMoreThanKLength(int[][] graph, int K, int sourceVertex) {
        boolean[] visited = new boolean[graph.length];
        int totalWeight = 0;
        System.out.println("Has Path greater than " + K + " ? " + doDFSForPathExistMoreThanKLength(sourceVertex, graph, K, totalWeight, visited));
    }

    public static boolean doDFSForPathExistMoreThanKLength(int source, int[][] graph, int k, int totalWeight, boolean[] visited) {
        // Visit the popped vertex
        visited[source] = true;

        // We found a path having weight more than K
        if (totalWeight > k) {
            return true;
        }

        for (int i = 0; i < graph[source].length; i++) {
            if (graph[source][i] != 0 && !visited[i]) { // Checking if there is some weight to the edge and also this vertex is not visited
                if (doDFSForPathExistMoreThanKLength(i, graph, k, totalWeight + graph[source][i], visited)) {
                    return true;
                } else {
                    visited[i] = false; // This path doesn't contribute to the sum greater than K so mark it unvisited
                }
            }
        }
        return false;
    }

    static class Position {
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Queen --> " + row + " placed at ===> " + col + " position";
        }
    }

    /**
     * Remember simple rule, Queen will attack same row, same column and both diagonal (left diagonal == row - col ) and right diagonal == (row + col)
     * Put your new queen in a safe box only
     *
     * @param noOfQueens
     */
    public static void solveNQueenProblem(int noOfQueens) {
        Position[] positions = new Position[noOfQueens];
        // Let's start from
        if (solveNQueenProblemUtil(0, noOfQueens, positions)) {
            letsDo("It's possible to place " + noOfQueens + " queen");
            newLine();
            for (Position position : positions) {
                System.out.println(position);
            }
        } else {
            letsDo("It's not  possible to place " + noOfQueens + " queen");
        }
    }

    public static boolean solveNQueenProblemUtil(int row, int noOfQueens, Position[] positions) {

        // noOfQueen is 1 indexed and row is 0 indexed, So that means row came which is greater than no of queens
        if (row >= noOfQueens) {
            return true;
        }
        for (int col = 0; col < noOfQueens; col++) {
            boolean isSafePlaceFound = true;

            // Check if this square is safe for our queen
            // Validate this for all previous queens
            for (int queen = 0; queen < row; queen++) {

                // Checking for Same Column and Both Left (row-col) and Right (row+col) diagonal
                if (positions[queen].col == col || positions[queen].row - positions[queen].col == row - col || positions[queen].row + positions[queen].col == row + col) {
                    isSafePlaceFound = false;
                    break;
                }
            }

            if (isSafePlaceFound) {
                positions[row] = new Position(row, col);
                if (solveNQueenProblemUtil(row + 1, noOfQueens, positions)) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void solveRatInAMaze(int[][] maze) {
        int[][] solution = new int[maze.length][maze.length];
        if (solveMaze(maze, 0, 0, solution)) {
            letsDo("Hurray RAT solved puzzle!!");
            ArrayUtil.print2DArray(solution, solution.length, solution.length);
        } else {
            letsDo("RAT can't get out of this maze");
        }
    }

    public static boolean solveMaze(int[][] maze, int x, int y, int[][] solution) {

        // If RAT has reached to the end of maze
        if (x == maze.length - 1 && y == maze[0].length - 1) {
            solution[x][y] = 1;
            return true;
        }

        if (isSafeCellForRat(maze, x, y)) {
            solution[x][y] = 1; // Adding this route to the solution

            if (solveMaze(maze, x + 1, y, solution)) { // Moving Down
                return true;
            }

            if (solveMaze(maze, x, y + 1, solution)) { // Moving Forward
                return true;
            }

            /* If none of the above movements work then
               BACKTRACK: unmark x,y as part of solution
               path */
            solution[x][y] = 0; // Means rat can't go via this route
            return false;
        }
        return false;
    }

    /**
     * Check whether RAT is not going out of the puzzle and also he is only moving to the cell with having 1(land) as value not 0(water)
     *
     * @param maze
     * @param x
     * @param y
     * @return
     */
    public static boolean isSafeCellForRat(int[][] maze, int x, int y) {
        return x >= 0 && x < maze.length
                && y >= 0 && y < maze[0].length
                && maze[x][y] == 1;

    }

    private static int solveSnakeNdLadderProblem(int[] snakeAndLadderPuzzle) {
        class SnakeNdLadder {
            int V; // Vertex
            int distance; // Distance from Source to this Vertex

            public SnakeNdLadder() {
            }

            public SnakeNdLadder(int v, int distance) {
                V = v;
                this.distance = distance;
            }
        }
        Queue<SnakeNdLadder> queue = new LinkedList<>();
        int gameSize = snakeAndLadderPuzzle.length;

        boolean[] visited = new boolean[gameSize]; // To Mark visited vertex so that during BFS we don't end up visited already visited vertex
        SnakeNdLadder queueEntry = new SnakeNdLadder(0, 0);

        queue.add(queueEntry);
        visited[0] = true;

        while (!queue.isEmpty()) {

            queueEntry = queue.poll();
            int Vertex = queueEntry.V;

            if (Vertex == gameSize - 1) { // We reached to the end
                break;
            }

            // If Not let's roll the dice
            for (int j = (Vertex + 1); j <= (Vertex + 6) && j < gameSize; j++) {

                if (!visited[j]) {
                    SnakeNdLadder temp = new SnakeNdLadder();

                    temp.distance = queueEntry.distance + 1;
                    visited[j] = true;

                    // Check for Snake and Ladder
                    if (snakeAndLadderPuzzle[j] != -1) {
                        temp.V = snakeAndLadderPuzzle[j];
                    } else {
                        temp.V = j;
                    }

                    queue.add(temp);
                }
            }
        }
        return queueEntry.distance; // Minimum no of dice throws
    }

    static Map<Integer, String> friendsMap = new HashMap<>();

    private static void minCashFlowSplitwise(int[][] cashFlow, int noOfFriends) {

        friendsMap.put(0, "JITU");
        friendsMap.put(1, "SAGAR");
        friendsMap.put(2, "NEERAJ");

        int[] netAmount = new int[noOfFriends];
        // First find the net amount of all friends

        for (int i = 0; i < noOfFriends; i++) {
            for (int j = 0; j < noOfFriends; j++) {
                // Sum of All Credit - Sum of All Debit for all person
                netAmount[i] += cashFlow[j][i] - cashFlow[i][j];
            }
        }

        minCashFlowSplitwiseRecursively(netAmount);
    }

    private static void minCashFlowSplitwiseRecursively(int[] netAmount) {
        int PC = getMaxCreditor(netAmount);
        int PD = getMaxDebtor(netAmount);

        // if credit and debit is 0 then we are settled
        if (netAmount[PC] == 0 && netAmount[PD] == 0) {
            return;
        }

        // Find the Minimum Net Amount of Pc(Max Creditor) and Pd(Max Debtor)
        // Since netAmount is Sum_OF_CREDIT - SUM_OF_DEBIT
        // So if we have to find min of netAmount of Max Creditor and max debtor
        // We need to add negative sign(in netAmount of Pd) so as to get the correct minimum amount
        int minNetAmount = Math.min(-netAmount[PD], netAmount[PC]);

        netAmount[PD] += minNetAmount; // if someone pays the debt their SUM_OF_DEBIT increases, hence the netAmount increases
        netAmount[PC] -= minNetAmount; // If someone receives the debt their SUM_OF_CREDIT decreases, hence the netAmount decreases

        letsDo(friendsMap.get(PD) + " pays " + minNetAmount + " to " + friendsMap.get(PC));

        minCashFlowSplitwiseRecursively(netAmount);
    }

    public static int getMaxCreditor(int[] netAmount) {
        int Pc = -1, maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < netAmount.length; i++) {
            if (maxValue < netAmount[i]) {
                maxValue = netAmount[i];
                Pc = i;
            }
        }
        return Pc;
    }

    public static int getMaxDebtor(int[] netAmount) {
        int Pd = -1, minValue = Integer.MAX_VALUE;
        for (int i = 0; i < netAmount.length; i++) {
            if (minValue > netAmount[i]) {
                minValue = netAmount[i];
                Pd = i;
            }
        }
        return Pd;
    }
}
