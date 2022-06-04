package com.geeksforgeeks.heap;

import java.util.*;

@SuppressWarnings({"Duplicates", "SpellCheckingInspection"})
public class MinHeap<T> {

    private int capacity = 10;
    private int size = 0;
    int[] items = new int[capacity];
    private Map<Integer, Integer> itemPositionMap = new HashMap<>();

    public class Node {
        public int weight;
        public T key;

        public Node() {
        }

        public Node(int weight, T key) {
            this.weight = weight;
            this.key = key;
        }

        public String toString() {
            return "Node : [weight : " + weight + ", key : " + key + "]";
        }
    }

    private List<Node> allNodes = new ArrayList<>();
    private Map<T, Integer> nodePositionMap = new HashMap<>();


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

        // We have to swap the position in the MAP
        itemPositionMap.put(items[indexOne], indexTwo);
        itemPositionMap.put(items[indexTwo], indexOne);

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
     * This method will return the current position of item in the heap/(Array representing the heap)
     *
     * @param item
     * @return
     */
    public boolean contains(T item) {
        return nodePositionMap.containsKey(item);
    }


    public Integer getWeight(T vertex) {
        return allNodes.get(nodePositionMap.get(vertex)).weight;
    }

    /**
     * This method will get the root, In case of Min Heap it's the minimum element in heap.
     *
     * @return
     */
    public int peek() {
        if (size == 0)
            throw new IllegalStateException();
        return items[0];
    }

    /**
     * This method will extract the minimum element that is the root,
     * and also re-heapify the heap
     *
     * @return
     */
    public int extractMin() {
        if (size == 0)
            throw new IllegalStateException();

        int item = items[0];
        items[0] = items[size - 1];
        size--;
        nodePositionMap.remove(item);
        heapifyDown();
        return item;
    }

    public void add(int item) {
        ensureExtraCapacity();
        items[size] = item;
        itemPositionMap.put(item, size);
        size++;
        heapifyUp();
    }

    public void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && parent(index) > items[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;

        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);

            if (hasRightChild(index) && rightChild(index) < items[smallerChildIndex]) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (items[index] > items[smallerChildIndex]) {
                swap(smallerChildIndex, index);
                index = smallerChildIndex;
            } else {
                break;
            }
        }
    }

    public Node extractMinimumNode() {
        Node node = allNodes.get(0);
        Node lastNode = allNodes.get(size - 1);
        allNodes.remove(node);
        allNodes.remove(lastNode);
        if ((size - 1) != 0) {
            allNodes.add(0, lastNode);

            size--;
            nodePositionMap.remove(node.key);
            nodePositionMap.put(lastNode.key, 0);
            heapifyNodesDown();
        }
        return node;
    }

    private void heapifyNodesDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && allNodes.get(getRightChildIndex(index)).weight < allNodes.get(smallerChildIndex).weight) {
                smallerChildIndex = getRightChildIndex(index);
            }
            Node node = allNodes.get(index);
            Node smallerChildNode = allNodes.get(smallerChildIndex);
            if (node.weight > smallerChildNode.weight) {
                swapNodes(index, smallerChildIndex);
                index = smallerChildIndex;
            } else {
                break;
            }

        }
    }

    public void addNodeToHeap(int weight, T key) {
        Node node = new Node(weight, key);
        allNodes.add(node);
        nodePositionMap.put(key, size);
        size++;
        heapifyNodesUp(size - 1);
    }

    public void printHeap() {
        for (Node n : allNodes) {
            System.out.println("[Wieght :: " + n.weight + ", KEY :: " + n.key + "]");
        }
    }

    public void heapifyNodesUp(int index) {
        while (hasParent(index) && allNodes.get(getParentIndex(index)).weight > allNodes.get(index).weight) {
            swapNodes(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void swapNodes(int parentIndex, int currentNodeIndex) {
        Node parentNode = allNodes.get(parentIndex);
        Node currentNode = allNodes.get(currentNodeIndex);
        nodePositionMap.put(parentNode.key, currentNodeIndex);
        nodePositionMap.put(currentNode.key, parentIndex);

        int weight = currentNode.weight;
        T key = currentNode.key;

        currentNode.weight = parentNode.weight;
        currentNode.key = parentNode.key;

        parentNode.weight = weight;
        parentNode.key = key;
    }

    public void decreaseNodeWeight(T key, int newWeight) {
        int position = nodePositionMap.get(key);
        allNodes.get(position).weight = newWeight;
        heapifyNodesUp(position);
    }

    public void printPositionMap() {
        System.out.println(nodePositionMap);
    }

    /**
     * Checks with heap is empty or not
     */
    public boolean empty() {
        return allNodes.size() == 0;
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();

        minHeap.add(10);
        minHeap.add(15);
        minHeap.add(20);
        minHeap.add(17);
        minHeap.add(25);
        minHeap.add(5);
        System.out.println("Initial Positions are ::==> " + minHeap.nodePositionMap);

        System.out.println("Extracting the Minimum Element ::--> " + minHeap.extractMin());
        System.out.println("After extracting the mininum element positions are :: ->" + minHeap.nodePositionMap);

        System.out.println("Now Who is the minimum element ::--> " + minHeap.peek());

        minHeap.add(1);
        System.out.println("Now the minimum is ::-->" + minHeap.peek());
        System.out.println("After adding 1 to the heap the positions are :: ->" + minHeap.nodePositionMap);

        minHeap.extractMin();
        System.out.println("Now the minimum is ::-->" + minHeap.peek());
        System.out.println("After removing 1 from the heap the positions are :: ->" + minHeap.nodePositionMap);


        System.out.println("==================== MIN HEAP FOR PRIMS ALGORITHM ======================");
        MinHeap heap = new MinHeap<String>();
        heap.size = 0;
        heap.addNodeToHeap(3, "Tushar");
        heap.addNodeToHeap(4, "Ani");
        heap.addNodeToHeap(8, "Vijay");
        heap.addNodeToHeap(10, "Pramila");
        heap.addNodeToHeap(5, "Roy");
        heap.addNodeToHeap(6, "NTF");
        heap.addNodeToHeap(2, "AFR");
//        heap.printHeap();
        heap.printPositionMap();

        System.out.println("Extracting Minimum Node " + heap.extractMinimumNode());
        heap.printPositionMap();

        heap.decreaseNodeWeight("Vijay", 1);
        heap.printPositionMap();
        System.out.println("Extract Minumum Node after decrease operation " + heap.extractMinimumNode());

    }

}
