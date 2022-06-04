package com.datastructures.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by jaine03 on 12/08/17.
 */
public class ConnectedComponentsInUndirectedGraph {

    private static int totalVertex; // totalVertices
    private static LinkedList<Integer>[] adjacentMatrix; // Stores Graph information

    public ConnectedComponentsInUndirectedGraph(int totalVertex) {
        this.totalVertex = totalVertex;
        adjacentMatrix = new LinkedList[totalVertex];
        for (int i = 0; i < totalVertex; i++) {
            adjacentMatrix[i] = new LinkedList<>();
        }
    }

    public void addEdge(int sourceVertex, int destinationVertex) {
        adjacentMatrix[sourceVertex].add(destinationVertex);
    }

    public void printConnectedComponents(int sourceVertex, Set<Integer> visited) {
        System.out.print(sourceVertex+",");
        visited.add(sourceVertex);

        for(Integer adjacentVertex: adjacentMatrix[sourceVertex]){
            if(!visited.contains(adjacentVertex))
                printConnectedComponents(adjacentVertex,visited);
        }
    }

    public static void main(String[] args) {
        ConnectedComponentsInUndirectedGraph graph = new ConnectedComponentsInUndirectedGraph(5);
        Set<Integer> visited = new HashSet<>();

        graph.addEdge(0,1);
        graph.addEdge(3,4);
        graph.addEdge(1,2);

        for (int i = 0; i < totalVertex; i++) {
            if (!visited.contains(i)) {
                graph.printConnectedComponents(i,visited);
                System.out.println();
            }
        }
    }
}
