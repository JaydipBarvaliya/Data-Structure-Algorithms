package com.geeksforgeeks.graph;

public class Edge<T> {
    private int weight;
    private Vertex<T> vertex1;
    private Vertex<T> vertex2;
    private Boolean isDirected;

    public Edge(Vertex vertex1, Vertex vertex2, Boolean isDirected, int weight) {
        this.weight = weight;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.isDirected = isDirected;
    }

    Edge(Vertex vertex1, Vertex vertex2, boolean isDirected) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.isDirected = isDirected;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Vertex getVertex1() {
        return vertex1;
    }

    public void setVertex1(Vertex vertex1) {
        this.vertex1 = vertex1;
    }

    public Vertex getVertex2() {
        return vertex2;
    }

    public void setVertex2(Vertex vertex2) {
        this.vertex2 = vertex2;
    }

    public Boolean getDirected() {
        return isDirected;
    }

    public void setDirected(Boolean directed) {
        isDirected = directed;
    }

    @Override
    public String toString() {
        return "Edge [isDirected=" + isDirected + ", vertex1=" + vertex1
                + ", vertex2=" + vertex2 + ", weight=" + weight + "]";
    }
}
