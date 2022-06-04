package com.leetcode.year_2020;

/**
 * @author neeraj on 15/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RemovekthToLastElement {

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
//        head.next = new ListNode(3);
        head.next = new ListNode(5);
//        head.next.next.next = new ListNode(6);
//        head.next.next.next.next = new ListNode(7);

        head.printList(head);

        head = removeKthToLast(head, 2);
        head.printList(head);
    }

    public static ListNode removeKthToLast(ListNode head, int k) {
        // We will add the dummy head to handle edge cases.
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode rightNode = dummyHead.next;
        ListNode leftNode = dummyHead; // We will start the leftNode from dummyHead itself.

        while (k-- > 0) {
            rightNode = rightNode.next;
        }

        while (rightNode != null) {
            leftNode = leftNode.next;
            rightNode = rightNode.next;
        }

        leftNode.next = leftNode.next.next;
        return dummyHead.next;
    }
}
