package com.leetcode.year_2020;

import java.util.PriorityQueue;

/**
 * @author neeraj on 28/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FindMedianfromDataStream {

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
//        medianFinder.addNum(1);
//        medianFinder.addNum(2);
//        System.out.println(medianFinder.findMedian());
//        medianFinder.addNum(3);
//        System.out.println(medianFinder.findMedian());


        // ["MedianFinder","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian"]
        //[[],[6],[],[10],[],[2],[],[6],[],[5],[],[0],[],[6],[],[3],[],[1],[],[0],[],[0],[]]


        medianFinder = new MedianFinder();
        medianFinder.addNum(6);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(10);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(6);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(5);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(0);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(6);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(1);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(0);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(0);
    }

    static class MedianFinder {

        /**
         * [ 19,  20,  21,  27,  28]
         * ________    ____________
         * Left Half   Right Half
         * <p>
         * So for this odd list the median relies in right half.
         * <p>
         * [ 1, 2 , 3,   4, 5, 6]
         * __________   ____________
         * Left Half   Right Half
         * <p>
         * Here the Median relies in both halves since list is even sized.
         */
        PriorityQueue<Integer> leftHalf;
        PriorityQueue<Integer> rightHalf;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
            leftHalf = new PriorityQueue<>((a, b) -> b - a); // Max Heap
            rightHalf = new PriorityQueue<>(); // Min Heap
        }

        public void addNum(int num) {
            /**
             * 1) The first item.
             * 2)  Item belongs to the right half.
             * 3) Item belongs to the left half.
             */
            if (rightHalf.isEmpty()) {
                rightHalf.add(num);
            } else { // Since rightHalf is a MinHeap, so the first item in this is the smallest on right side.
                if (num < rightHalf.peek()) {
                    leftHalf.add(num);
                } else {
                    rightHalf.add(num);
                }
            }
            rebalance();
        }

        private void rebalance() {
            // We have to rebalance the left and right half in the following scenario.
            /**
             * 1) RightHeap.size() - leftHeap.size >= 2;  ... Scenario : Input [6, 10] : These all will be added to right half, we need to rebalance.
             * 2) LeftHeap.size() >= RightHeap.size + 1;  .. LeftHeap has more elements than the right side.
             */
            if (rightHalf.size() - leftHalf.size() >= 2) {
                leftHalf.add(rightHalf.poll());
            } else if (leftHalf.size() >= rightHalf.size() + 1) {
                rightHalf.add(leftHalf.poll());
            }
        }

        public double findMedian() {
            if (leftHalf.size() == rightHalf.size()) {
                return 0.5 * (leftHalf.peek() + rightHalf.peek());
            } else {
                return rightHalf.peek();
            }
        }
    }
}
