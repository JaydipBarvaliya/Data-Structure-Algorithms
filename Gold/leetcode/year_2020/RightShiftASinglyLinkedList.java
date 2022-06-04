package com.leetcode.year_2020;

/**
 * @author neeraj on 16/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RightShiftASinglyLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);


        head.printList(rotateRight(head, 6));
    }

    public static ListNode rotateRight(ListNode head, int k) {
        // 1) Get Size
        // 2) Create a Cycle by connecting tail to the head
        // 3) We have to make the cut at size - (k %size)

        // We will do step 1 and step 2 simultaneously
        ListNode temp = head;
        int size = 1;
        while (temp.next != null) {
            temp = temp.next;
            size++;
        }
        temp.next = head; // Create cycle.

        int iterateUpto = size - (k % size);
        temp = head;
        for (int i = 0; i < iterateUpto - 1; i++) {
            temp = temp.next;
        }
        head = temp.next;
        temp.next = null; // break the cycle.
        return head;
    }

}
