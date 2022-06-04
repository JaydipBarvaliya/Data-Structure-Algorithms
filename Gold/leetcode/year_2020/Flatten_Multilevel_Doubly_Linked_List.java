package com.leetcode.year_2020;

/**
 * @author neeraj on 19/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 * https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
 */
public class Flatten_Multilevel_Doubly_Linked_List {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode head_2 = new ListNode(2);
        ListNode head_3 = new ListNode(3);
        ListNode head_4 = new ListNode(4);
        ListNode head_5 = new ListNode(5);
        ListNode head_6 = new ListNode(6);
        ListNode head_7 = new ListNode(7);
        ListNode head_8 = new ListNode(8);
        ListNode head_9 = new ListNode(9);
        ListNode head_10 = new ListNode(10);
        ListNode head_11 = new ListNode(11);
        ListNode head_12 = new ListNode(12);

        /**
         * MultiLevel Doubly Linked List
         *   1 <--> 2 <--> 3 <--> 4 <--> 5 <--> 6
         *                /\
         *                ||
         *                \/
         *                7 <--> 8 <--> 9 <--> 10
         *                      /\
         *                      ||
         *                      \/
         *                      11 <--> 12
         *
         */

        // Level 1
        head.next = head_2;
        head_2.next = head_3;
        head_3.next = head_4;
        head_4.next = head_5;
        head_5.next = head_6;

        // Level 2
        head_7.next = head_8;
        head_8.next = head_9;
        head_9.next = head_10;

        // Level 3;
        head_11.next = head_12;

        head_3.child = head_7;
        head_8.child = head_11;

        head = flatten(head);
        head.printList(head);
    }

    public static ListNode flatten(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode curr = head;
        while (curr != null) {
            ListNode nextOfCurr = curr.next;
            if (curr.child != null) { // Here we will flatten the list
                ListNode headOfFlattenList = flatten(curr.child);

                curr.next = headOfFlattenList;
                headOfFlattenList.prev = curr;
                curr.child = null;

                // Now let's connect tail of Flatten List to NextNode if available
                if (nextOfCurr != null) {
                    ListNode tailOfFlattenList = getTailOfFlattenList(headOfFlattenList);
                    tailOfFlattenList.next = nextOfCurr;
                    nextOfCurr.prev = tailOfFlattenList;
                }
            }
            curr = nextOfCurr;
        }
        return head;
    }

    private static ListNode getTailOfFlattenList(ListNode headOfFlattenList) {
        if (headOfFlattenList == null) return null;
        ListNode curr = headOfFlattenList;
        while (curr.next != null) {
            curr = curr.next;
        }
        return curr;
    }
}
