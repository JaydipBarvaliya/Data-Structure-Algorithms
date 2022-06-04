package com.datastructures.graph;

import java.util.*;

/**
 * Created by jaine03 on 03/08/17.
 */
public class BreadthFirstTraversal {

    private static int totalVertex; // totalVertices
    private static LinkedList<Integer>[] adjacentMatrix; // Stores Graph information

    public BreadthFirstTraversal(int totalVertex) {
        this.totalVertex = totalVertex;
        adjacentMatrix = new LinkedList[totalVertex];
        for (int i = 0; i < totalVertex; i++) {
            adjacentMatrix[i] = new LinkedList<>();
        }
    }

    public void addEdge(int sourceVertex, int destinationVertex) {
        adjacentMatrix[sourceVertex].add(destinationVertex);
    }

//    public static void BFS(int sourceVertex){
//        Set<Integer> visitedVertices = new HashSet<>();
//        Queue<Integer> queue = new LinkedList<>();
//
//        queue.add(sourceVertex);
//        visitedVertices.add(sourceVertex);
//        while (!queue.isEmpty()){
//            int vertexToVisit = queue.poll();
//
//            System.out.print(vertexToVisit+",");
//            for(Integer vertex:adjacentMatrix[vertexToVisit]){
//                if(!visitedVertices.contains(vertex)){
//                    queue.add(vertex);
//                    visitedVertices.add(vertex);
//                }
//            }
//        }
//    }


    public static void DFSUtil(int sourceVertex, Set<Integer> visitedNodes) {
        System.out.print(sourceVertex + ",");
        visitedNodes.add(sourceVertex);

        for (Integer vertex : adjacentMatrix[sourceVertex]) {
            if (!visitedNodes.contains(vertex)) {
                DFSUtil(vertex, visitedNodes);
            }
        }
    }

    public static void DFS(int sourceVertex) {
        DFSUtil(sourceVertex, new HashSet<>());
    }

    public static void BFS(int sourceVertex) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(sourceVertex);
        visited.add(sourceVertex);
        while (queue.isEmpty()) {
            int temp = queue.poll();
            System.out.println(temp);
            for (Integer vertex : adjacentMatrix[temp]) {
                if (!visited.contains(vertex)) {
                    queue.add(vertex);
                    visited.add(vertex);
                }
            }
        }
    }

    public static void main(String[] args) {
        BreadthFirstTraversal bft = new BreadthFirstTraversal(4);

        bft.addEdge(0, 1);
        bft.addEdge(1, 0);
        bft.addEdge(0, 2);
        bft.addEdge(2, 0);
        bft.addEdge(1, 2);
        bft.addEdge(2, 1);
        bft.addEdge(2, 3);
        bft.addEdge(3, 2);
        bft.addEdge(3, 3);

        BFS(2);
        System.out.println();

        DFS(2);
    }
}
