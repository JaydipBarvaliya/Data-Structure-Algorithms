package com.leetcode.year_2020.list;

import com.leetcode.year_2020.ListNode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author neeraj on 05/08/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RemoveDuplicatesFromSortedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next = new ListNode(4);

        head.printList(head);
        head = deleteDuplicates(head);
        head.printList(head);
    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode dummyNode = new ListNode(), slow = dummyNode, fast = head;
        slow.next = fast;

        while (fast != null) {
            while (fast.next != null && fast.next.val == fast.val) {
                fast = fast.next;
            }

            if (slow.next != fast) { // Duplicate detected
                slow.next = fast.next;
                fast = slow.next;
            } else { // No Duplicates increment both pointer by 1
                slow = slow.next;
                fast = fast.next;
            }
        }
        return dummyNode.next;
    }
}
