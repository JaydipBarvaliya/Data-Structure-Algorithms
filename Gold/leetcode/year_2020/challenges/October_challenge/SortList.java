package com.leetcode.year_2020.challenges.October_challenge;

import com.leetcode.year_2020.ListNode;

/**
 * @author neeraj on 13/10/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SortList {

    static class Result {
        ListNode head;

        public void append(int data) {
            ListNode newNode = new ListNode(data);
            if (head == null) {
                head = newNode;
                return;
            }
            ListNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        ListNode sorted = sortList(head);
        sorted.printList(sorted);

    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode mid = getMiddle(head);
        ListNode nextOfMid = mid.next;
        mid.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(nextOfMid);

        Result result = new Result();
        merge(result, left, right);
        return result.head;
    }

    public static void merge(Result sortedListHead, ListNode headOfFirst, ListNode headOfSecond) {
        ListNode t1 = headOfFirst;
        ListNode t2 = headOfSecond;

        while (t1 != null && t2 != null) {
            if (t1.val < t2.val) {
                sortedListHead.append(t1.val);
                t1 = t1.next;
            } else {
                sortedListHead.append(t2.val);
                t2 = t2.next;
            }
        }

        while (t1 != null) {
            sortedListHead.append(t1.val);
            t1 = t1.next;
        }

        while (t2 != null) {
            sortedListHead.append(t2.val);
            t2 = t2.next;
        }
    }

    public static ListNode getMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null) {
            fast = fast.next;

            if (fast != null && fast.next != null) {
                fast = fast.next;
                slow = slow.next;
            }
        }
        return slow;
    }
}
