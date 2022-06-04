package com.leetcode.problems.medium;

import com.geeksforgeeks.array.Rotate2DMatrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author neeraj on 02/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class KClosestPointToOrigin_973 {

    public static void main(String[] args) {
        Rotate2DMatrix.print2DArray(kClosestUsingHeap(new int[][]{
                {1, 3},
                {-2, 2}
        }, 1));

        Rotate2DMatrix.print2DArray(kClosestUsingHeap(new int[][]{
                {3, 3},
                {5, -1},
                {-2, 4}
        }, 2));

    }

    public static int[][] kClosestUsingHeap(int[][] points, int K) {
        // Maintain a Max Heap
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((left, right) -> getDistance(right) - getDistance(left));

        for (int i = 0; i < points.length; i++) {
            priorityQueue.add(points[i]);
            if (priorityQueue.size() > K) {
                priorityQueue.poll();
            }
        }
        int[][] result = new int[K][2];
        while (K-- > 0) {
            result[K] = priorityQueue.poll();
        }
        return result;
    }

    public static int getDistance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    public static int[][] kClosest(int[][] points, int K) {
        double[] distance = new double[points.length];
        List<Point> pointsList = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            distance[i] = getDistanceFromOrigin(points[i][0], points[i][1]);
            pointsList.add(new Point(points[i][0], points[i][1], distance[i]));
        }

        Collections.sort(pointsList, Comparator.comparingDouble(a -> a.distanceFromOrigin));

        int[][] result = new int[K][2];
        for (int i = 0; i < K; i++) {
            result[i][0] = pointsList.get(i).x;
            result[i][1] = pointsList.get(i).y;
        }
        return result;
    }

    public static double getDistanceFromOrigin(int x, int y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}

class Point {
    int x;
    int y;
    double distanceFromOrigin;

    public Point(int x, int y, double distanceFromOrigin) {
        this.x = x;
        this.y = y;
        this.distanceFromOrigin = distanceFromOrigin;
    }

    public double getDistanceFromOrigin() {
        return distanceFromOrigin;
    }
}
