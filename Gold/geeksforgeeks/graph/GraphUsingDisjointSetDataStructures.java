package com.geeksforgeeks.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphUsingDisjointSetDataStructures<T> {

    private List<Edge<T>> allEdges;
    private Map<Long, Vertex<T>> allVertex;
    boolean isDirected = false;

    public GraphUsingDisjointSetDataStructures(boolean isDirected) {
        allEdges = new ArrayList<>();
        allVertex = new HashMap<>();
        this.isDirected = isDirected;
    }

    public void addEdge(long l1, long l2) {
        addEdge(l1, l2, 0);
    }

    public void addEdge(long l1, long l2, int weight) {
        allVertex.putIfAbsent(l1, new Vertex<>(l1));
        allVertex.putIfAbsent(l2, new Vertex<>(l2));
        Vertex vertex1 = allVertex.get(l1);
        Vertex vertex2 = allVertex.get(l2);

        Edge edge = new Edge(vertex1, vertex2, isDirected, weight);

        allEdges.add(edge);
        vertex1.addAdjacentVertex(edge, vertex2);
        if (!isDirected) {
            vertex2.addAdjacentVertex(edge, vertex1);
        }
    }

    public Vertex<T> getVertex(long id) {
        return allVertex.get(id);
    }

    public List<Edge<T>> getAllEdges() {
        return allEdges;
    }

    public Collection<Vertex<T>> getAllVertex() {
        return allVertex.values();
    }
}
