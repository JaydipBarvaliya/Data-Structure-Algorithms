package com.datastructures.heap;

import java.util.Arrays;

public class MinHeap {

    private int capacity = 10;
    private int size = 0;

    int[] items = new int[capacity];

    private int getLeftChildIndex(int parentIndex) {
        return parentIndex * 2 + 1;
    }
    private int getRightChildIndex(int parentIndex) {
        return parentIndex * 2 + 2;
    }
    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private Boolean hasLeftChild(int parentIndex) {
        return getLeftChildIndex(parentIndex) < size;
    }
    private Boolean hasRightChild(int parentIndex) {
        return getRightChildIndex(parentIndex) < size;
    }
    private Boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    private int leftChild(int index) {
        return items[getLeftChildIndex(index)];
    }
    private int rightChild(int index) {
        return items[getRightChildIndex(index)];
    }
    private int parent(int index) {
        return items[getParentIndex(index)];
    }

    private void swap(int indexOne, int indexTwo){
        int temp = items[indexOne];
        items[indexOne] = items[indexTwo];
        items[indexTwo] = temp;
    }

    private void ensureExtraCapacity(){
        if(size == capacity){
            items = Arrays.copyOf(items,capacity * 2);
            capacity *= 2;
        }
    }

    public int peek(){
        if(size == 0) throw new IllegalStateException();
        return items[0];
    }

    /**
     * Returns the minimum element from the heap, i.e element from the root because that is the minimum of all
     * But this is not that easy, after removing put the last element of heap to root and then call heapifyDown() method
     * @return
     */
    public int poll(){
        if(size == 0) throw new IllegalStateException();
        int item = items[0];
        items[0] = items[size-1];
        size--;
        heapifyDown();
        return item;
    }

    /**
     * This method will rearrange the heap to satisfy MIN_HEAP property
     */
    public void heapifyDown(){
        int index = 0;

        while (hasLeftChild(index)){
            int smallerChildIndex = getLeftChildIndex(index);
            if(hasRightChild(index) && rightChild(index) < leftChild(index)){
                smallerChildIndex = getRightChildIndex(index);
            }

            if(items[index] < items[smallerChildIndex]){
                break;
            } else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }


    /**
     * This method will add the element at the last of the heap and then we have to call heapifyUp() method to
     * make our heap satisfy heap property
     */
    public void add(int item){
        ensureExtraCapacity();
        items[size] = item;
        size++;
        heapifyUp();
    }

    public void heapifyUp(){
        int index = size - 1;
        while (hasParent(index) && parent(index) > items[index]){
            swap(index,getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    public void print(){
        for(int i=0;i<size;i++)
            System.out.print(items[i]+",");
        System.out.println();
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        minHeap.add(10);
        minHeap.add(15);
        minHeap.add(20);
        minHeap.add(17);
        minHeap.add(25);

        minHeap.print();

        minHeap.poll();

        minHeap.print();

        minHeap.add(10);

        minHeap.print();
    }
}
