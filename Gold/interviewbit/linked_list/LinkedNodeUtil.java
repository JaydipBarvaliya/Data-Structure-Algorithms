package com.interviewbit.linked_list;

import java.util.List;

import static com.util.LogUtil.newLine;

/**
 * @author neeraj on 2019-08-11
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LinkedNodeUtil {

    ListNode head;

    public static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public void append(int data) {
        if (head == null) {
            head = new ListNode(data);
            return;
        }
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new ListNode(data);
    }

    public void addData(List<Integer> list) {
        for (Integer itr : list) {
            append(itr);
        }
    }

    public void printList() {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + " , ");
            temp = temp.next;
        }
        newLine();
    }

    public static int getSize(ListNode head) {
        int counter = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            counter++;
        }
        return counter;
    }

    public static ListNode findMiddle(ListNode head) {
        ListNode temp = head;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode temp = head;
        ListNode prev = null;
        ListNode next = null;

        while (temp != null) {
            next = temp.next;
            temp.next = prev;
            prev = temp;
            temp = next;
        }
        return prev;
    }
}
