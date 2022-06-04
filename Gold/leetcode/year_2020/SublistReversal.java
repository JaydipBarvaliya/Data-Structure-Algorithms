package com.leetcode.year_2020;

/**
 * @author neeraj on 19/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("ALL")
public class SublistReversal {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode head_2 = new ListNode(2);
        ListNode head_3 = new ListNode(3);
        ListNode head_4 = new ListNode(4);
        ListNode head_5 = new ListNode(5);

        head.next = head_2;
        head_2.next = head_3;
        head_3.next = head_4;
        head_4.next = head_5;

        head.printList(head);
        head = reverseBetween(head, 2, 4);
        head.printList(head);
    }

    public static ListNode reverseBetween(ListNode head, int start, int end) {
        if (start == end) {
            return head;
        }
        /**
         * Dummy Node is needed to avoid edge cases. In Scenario where the reverse has to start from the first Node itself.
         *
         * Note --> start and end is 1 based Index.
         *
         * 1 --> 2 --> 3 --> 4 --> 5 --> X    (start = 1, end =2)
         * o/p
         * DUMMY-NODE --> 2 --> 1 --> 3 --> 4 --> 5 --> X
         */
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        /**
         * Some Jargon.
         *
         *  1 --> 2 --> 3 --> 4 --> 5 --> X (start = 2, end = 4)
         * NBRS  SBWP  NCTSF
         *
         *
         * NBRS = Node Before Reversed Sublist.
         * SWP = SubList Working Pointer.
         * NCTSF = Node coming to the Front of SubList Front.
         *
         * Since we are keeping the track to NBRS, what we want to do is
         * to push NCTSF to the NBRS next at every step.
         *
         *       Reverse the actual sublist:
         *       start:
         *         dummyHead -> 1 -> 2 -> 3 -> 4 -> 5 -> x
         *                     nbrs swp
         *
         *       1st iteration (start = 2):
         *         1.) Cut out of sublist
         *           dummyHead -> 1 -> 2 -> 3 -> 4 -> 5 -> x
         *                       nbrs swp nctsf
         *
         *           dummyHead -> 1 -> 2 -> 4 -> 5 -> x
         *                       nbrs swp   ^
         *                               3--|
         *                             nctsf
         *         2.) Wire into sublist head
         *           dummyHead -> 1 -> 2 -> 4 -> 5 -> x
         *                       nbrs swp
         *                             ^
         *                           3--|
         *                         nctsf
         *           dummyHead -> 1 -> 3 -> 2 -> 4 -> 5 -> x
         *                       nbrs      swp
         *
         *       2nd iteration (start = 3):
         *         1.) Cut out of sublist
         *           dummyHead -> 1 -> 3 -> 2 -> 4 -> 5 -> x
         *                       nbrs       swp nctsf
         *
         *           dummyHead -> 1 -> 3 -> 2 -> 5 -> x
         *                       nbrs       swp   ^
         *                                     4--|
         *                                   nctsf
         *
         *         2.) Wire into sublist head
         *           dummyHead -> 1 -> 3 -> 2 -> 5 -> x
         *                       nbrs   ^   swp
         *                           4--|
         *                         nctsf
         *           dummyHead -> 1 -> 4 -> 3 -> 2 -> 5 -> x
         *                       nbrs            swp
         *
         *       3rd Iteration (start = 4), break while loop
         *
         */


        // Now we have to reach to the tail before the actual reverse starts;
        ListNode nodeBeforeReversedSublist = dummyHead;
        int pos = 1;
        while (pos++ < start) {
            nodeBeforeReversedSublist = nodeBeforeReversedSublist.next;
        }

        ListNode subListWorkingPointer = nodeBeforeReversedSublist.next;
        while (start++ < end) {

            // 1. Cut Out of SubList.
            ListNode nodeComingToSubListFront = subListWorkingPointer.next;
            subListWorkingPointer.next = nodeComingToSubListFront.next;

            nodeComingToSubListFront.next = nodeBeforeReversedSublist.next;
            nodeBeforeReversedSublist.next = nodeComingToSubListFront;
        }
        return dummyHead.next;
    }
}
