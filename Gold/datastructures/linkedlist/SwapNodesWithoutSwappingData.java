package com.datastructures.linkedlist;

/**
 * Created by jaine03 on 18/04/17.
 */
public class SwapNodesWithoutSwappingData {

    public static void main(String[] args) {

        LinkedListUtil linkedListUtil = new LinkedListUtil();

        linkedListUtil.append(10);
        linkedListUtil.append(15);
        linkedListUtil.append(12);
        linkedListUtil.append(13);
        linkedListUtil.append(20);
        linkedListUtil.append(14);

        linkedListUtil.traverseList();

        System.out.println("Swapping Nodes 10 and 14");

        swapNodes(14, 10, linkedListUtil);
        System.out.println("After Swapping");

        linkedListUtil.traverseList();

    }

    private static void swapNodes(int A, int B, LinkedListUtil linkedListUtil) {

        LinkedListUtil.Node nodeA = linkedListUtil.head;
        LinkedListUtil.Node nodeB = linkedListUtil.head;
        LinkedListUtil.Node prevA = null;
        LinkedListUtil.Node prevB = null;
        Boolean nodeAPresent = false;
        Boolean nodeBPresent = false;

        while (nodeA != null && nodeB != null) {
            if (!nodeAPresent) {
                if (nodeA.data == A) {
                    nodeAPresent = true;
                } else {
                    prevA = nodeA;
                    nodeA = nodeA.next;
                }
            }

            if (!nodeBPresent) {
                if (nodeB.data == B) {
                    nodeBPresent = true;
                } else {
                    prevB = nodeB;
                    nodeB = nodeB.next;
                }
            }

            if (nodeAPresent && nodeBPresent) {
                break;
            }
        }

        if (nodeAPresent && nodeBPresent) {
            if (prevA == null) {
                linkedListUtil.head = nodeB;
            } else {
                prevA.next = nodeB;
            }
            LinkedListUtil.Node temp = nodeB.next;
            if (nodeA.next == nodeB) // If Adjacent Nodes
            {
                nodeB.next = nodeA;
                nodeA.next = temp;
            } else {
                nodeB.next = nodeA.next;
                prevB.next = nodeA;
                nodeA.next = temp;
            }
        } else {
            System.out.println("Either NodeA or NodeB is missing from the list");
        }
    }


}
