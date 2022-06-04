package com.geeksforgeeks.graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex<T> {
    long id;
    int data;
    List<Edge<T>> edges = new ArrayList<>();
    List<Vertex<T>> adjacentVertex = new ArrayList<>();

    public Vertex(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge<T>> edges) {
        this.edges = edges;
    }

    public List<Vertex<T>> getAdjacentVertex() {
        return adjacentVertex;
    }

    public void addAdjacentVertex(Edge e, Vertex v) {
        edges.add(e);
        adjacentVertex.add(v);
    }

    public int getDegree() {
        return edges.size();
    }

    public String toString(){
        return String.valueOf(id);
    }
}
