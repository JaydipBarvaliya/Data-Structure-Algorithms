package com.geeksforgeeks.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings({"SpellCheckingInspection", "Duplicates"})
public class MaxHeap {

    private int capacity = 10;
    private int size = 0;

    int[] items = new int[capacity];


    public int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    public int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    public int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    public boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    public boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    public boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    public int leftChild(int index) {
        return items[getLeftChildIndex(index)];
    }

    public int rightChild(int index) {
        return items[getRightChildIndex(index)];
    }

    public int parent(int index) {
        return items[getParentIndex(index)];
    }

    public void swap(int indexOne, int indexTwo) {
        int temp;
        temp = items[indexOne];
        items[indexOne] = items[indexTwo];
        items[indexTwo] = temp;
    }

    public void ensureExtraCapacity() {
        if (size == capacity) {
            items = Arrays.copyOf(items, capacity * 2);
            capacity *= 2;
        }
    }

    /**
     * This method will get the root, In case of Max Heap it's the maximum element in heap.
     *
     * @return
     */
    public int peek() {
        if (size == 0)
            throw new IllegalStateException();
        return items[0];
    }

    public int extractMax() {
        if (size == 0)
            throw new IllegalStateException();
        int item = items[0];
        items[0] = items[size - 1];
        size--;

        heapifyDown();
        return item;
    }

    public void add(int item) {
        ensureExtraCapacity();

        items[size] = item;
        size++;

        heapifyUp();
    }

    public void heapifyDown() {
        int index = 0;

        while (hasLeftChild(index)) {
            int biggerChildIndex = getLeftChildIndex(index);

            if (hasRightChild(index) && items[getRightChildIndex(index)] > items[index]) {
                biggerChildIndex = getRightChildIndex(index);
            }

            if (items[biggerChildIndex] < items[index]) {
                break;
            } else {
                swap(biggerChildIndex, index);
                index = biggerChildIndex;
            }
        }
    }

    public void heapifyUp() {
        int index = size - 1;

        while (hasParent(index) && items[getParentIndex(index)] < items[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }


    private static List<Integer> findKLargestElement(int[] arr, int k) {
        List<Integer> kLargestElement = new ArrayList<>();
        MaxHeap maxHeap = new MaxHeap();

        for (int i = 0; i < arr.length; i++) {
            maxHeap.add(arr[i]);
        }

        // Find k largest element
        for (int i = 0; i < k; i++) {
            kLargestElement.add(maxHeap.extractMax());
        }
        return kLargestElement;
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();

        maxHeap.add(10);
        maxHeap.add(15);
        maxHeap.add(20);
        maxHeap.add(17);
        maxHeap.add(25);


        System.out.println("Extracting the Maximum Element ::--> " + maxHeap.extractMax());
        System.out.println("Now Who is the maximum element ::--> " + maxHeap.peek());

        maxHeap.add(100);
        System.out.println("Now the maximum is ::-->" + maxHeap.peek());
        maxHeap.extractMax();
        System.out.println("Now the maximum is ::-->" + maxHeap.peek());


        // Max Heap Also solves the K largest Elements Problem
        // The Problem is given and unsorted array, find the k largest element
        //For example, if given array is [1, 23, 12, 9, 30, 2, 50] and
        // you are asked for the largest 3 elements i.e., k = 3 then your program should print 50, 30 and 23.

        List<Integer> kLargestElement = findKLargestElement(new int[]{1, 23, 12, 9, 30, 2, 50}, 3);
        System.out.print("3 Largest Elements are ::::---> ");
        System.out.println(kLargestElement);

    }

}
