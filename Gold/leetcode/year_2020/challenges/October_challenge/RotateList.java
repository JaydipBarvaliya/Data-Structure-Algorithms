package com.leetcode.year_2020.challenges.October_challenge;

import com.leetcode.year_2020.ListNode;

/**
 * @author neeraj on 07/10/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RotateList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        head.printList(head);

        head = rotateRight(head, 31);
        head.printList(head);
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        int size = getSize(head);
        k = k % size; // Normalize the K under the total length.
        System.out.println("Size is " + size + " and k is " + k);
        if (k == 0) return head;

        // Get kth Node from last
        ListNode kthNodeFromLast = getKthNodeFromLast(head, k);
        ListNode resHead = kthNodeFromLast;
        ListNode initialHead = head;
        while (head.next != kthNodeFromLast) {
            head = head.next;
        }
        head.next = null;
        while (kthNodeFromLast != null && kthNodeFromLast.next != null) {
            kthNodeFromLast = kthNodeFromLast.next;
        }
        kthNodeFromLast.next = initialHead;
        return resHead;
    }

    public static ListNode getKthNodeFromLast(ListNode head, int k) {

        // 1 2 3 4 5
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        int counter = 0;
        while (counter++ < k) {
            fastPointer = fastPointer.next;
        }

        while (fastPointer.next != null) {
            fastPointer = fastPointer.next;
            slowPointer = slowPointer.next;
        }
        return slowPointer.next;
    }

    public static int getSize(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }
}
