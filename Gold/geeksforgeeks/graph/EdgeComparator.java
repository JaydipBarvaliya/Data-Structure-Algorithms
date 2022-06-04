package com.geeksforgeeks.graph;

import java.util.Comparator;

public class EdgeComparator implements Comparator<Edge<Integer>> {
    @Override
    public int compare(Edge<Integer> o1, Edge<Integer> o2) {
        if (o1.getWeight() <= o2.getWeight()) {
            return -1;
        } else {
            return 1;
        }
    }
}
