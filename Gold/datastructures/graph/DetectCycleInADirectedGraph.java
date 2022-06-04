package com.datastructures.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by jaine03 on 12/08/17.
 */
public class DetectCycleInADirectedGraph {

    private static int totalVertex; // totalVertices
    private static LinkedList<Integer>[] adjacentMatrix; // Stores Graph information

    public DetectCycleInADirectedGraph(int totalVertex) {
        this.totalVertex = totalVertex;
        adjacentMatrix = new LinkedList[totalVertex];
        for (int i = 0; i < totalVertex; i++) {
            adjacentMatrix[i] = new LinkedList<>();
        }
    }

    public void addEdge(int sourceVertex, int destinationVertex) {
        adjacentMatrix[sourceVertex].add(destinationVertex);
    }

    public Boolean isCyclicGraph(int sourceVertex, Set<Integer> visited, Set<Integer> recursionStackOfVertices){
        if(!visited.contains(sourceVertex)){
            visited.add(sourceVertex);
            recursionStackOfVertices.add(sourceVertex);
            for(int adjacentVertex: adjacentMatrix[sourceVertex]){
                if(!visited.contains(adjacentVertex) && isCyclicGraph(adjacentVertex,visited,recursionStackOfVertices)){
                    return true;
                }
                else if(recursionStackOfVertices.contains(adjacentVertex)){
                    return true;
                }
            }
        }
        recursionStackOfVertices.remove(sourceVertex);
        return false;
    }

    public Boolean isCyclicGraph() {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recursionStackOfVertices = new HashSet<>();
        for(int i=0;i<totalVertex;i++){
            if(isCyclicGraph(i,visited,recursionStackOfVertices))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {

        DetectCycleInADirectedGraph graph = new DetectCycleInADirectedGraph(3);
//        graph.addEdge(0, 1);
//        graph.addEdge(0, 2);
//        graph.addEdge(1, 2);
//        graph.addEdge(2, 0);
//        graph.addEdge(2, 3);
//        graph.addEdge(3, 3);

        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,2);
        graph.addEdge(2,1);

        System.out.println("Is Graph Cyclic " + graph.isCyclicGraph());
    }
}
