package com.leetcode.year_2020;

/**
 * @author neeraj on 18/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("DuplicatedCode")
public class SwapListInGroup {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        head.printList(head);
        head = swapInPairs(head, 3);
        head.printList(head);
    }

    public static ListNode swapInPairs(ListNode head, int k) {
        ListNode DummyNode = new ListNode(-1);
        ListNode curr = head;
//        int k = 0;
        int counter = 0;
        ListNode headOfCurrentSegment;
        ListNode headOfPreviousSegment = null;
        ListNode prev = null;
        ListNode next;
        while (curr != null) {
            counter = 0;
            headOfCurrentSegment = curr;

            while (counter++ < k && curr != null) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            if (DummyNode.next == null) {
                DummyNode.next = prev;
            } else {
                headOfPreviousSegment.next = prev;
            }
            prev = null;
            headOfPreviousSegment = headOfCurrentSegment;
        }
        return DummyNode.next;
    }
}
