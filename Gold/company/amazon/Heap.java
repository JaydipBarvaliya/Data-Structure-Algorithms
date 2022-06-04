package com.company.amazon;

import com.geeksforgeeks.array.QuickSort;

import java.util.Arrays;

public class Heap {

  int capacity = 10;
  int[] items = new int[capacity];
  int size = 0;

  private int leftChildIndex(int parentIndex) {
    return parentIndex * 2 + 1;
  }

  private int rightChildIndex(int parentIndex) {
    return parentIndex * 2 + 2;
  }

  private int parentIndex(int childIndex) {
    return (childIndex - 1) / 2;
  }

  private int parent(int childIndex) {
    return items[parentIndex(childIndex)];
  }

  private int leftChild(int parentIndex) {
    return items[leftChildIndex(parentIndex)];
  }

  private int rightChild(int parentIndex) {
    return items[rightChildIndex(parentIndex)];
  }

  private boolean hasParent(int childIndex) {
    return parentIndex(childIndex) >= 0;
  }

  private boolean hasLeftChild(int parentIndex) {
    return leftChildIndex(parentIndex) < size;
  }

  private boolean hasRightChild(int parentIndex) {
    return rightChildIndex(parentIndex) < size;
  }

  private void ensureExtraCapacity() {
    if (size >= capacity) {
      items = Arrays.copyOf(items, capacity * 2);
      capacity *= 2;
    }
  }

  public void insert(int item) {
    ensureExtraCapacity();
    items[size++] = item;
    heapifyUp();
  }

  public int extractMin() {
    int min = items[0];
    items[0] = items[size - 1];
    size--;
    heapifyDown();
    return min;
  }

  public void heapifyDown() {
    int index = 0;
    while (hasLeftChild(index)) {
      int minChildIndex = leftChildIndex(index);
      if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
        minChildIndex = rightChildIndex(index);
      }

      if (items[minChildIndex] < items[index]) {
        QuickSort.swap(items, index, minChildIndex);
        index = minChildIndex;
      } else {
        break;
      }
    }
  }

  public void heapifyUp() {
    int index = size - 1; // Currently newly added item is at this index

    while (hasParent(index) && items[index] < parent(index)) {
      QuickSort.swap(items, index, parentIndex(index));
      index = parentIndex(index);
    }
  }


  public static void main(String[] args) {
    Heap minHeap = new Heap();

    minHeap.insert(10);
    minHeap.insert(20);
    minHeap.insert(30);
    minHeap.insert(100);
    minHeap.insert(5);

    System.out.println(minHeap.extractMin());
    System.out.println(minHeap.extractMin());
    System.out.println(minHeap.extractMin());
    System.out.println(minHeap.extractMin());
    System.out.println(minHeap.extractMin());
  }
}
