package com.leetcode.year_2020;

/**
 * @author neeraj on 15/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode prev;
    public ListNode child;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public void printList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + "  --> ");
            temp = temp.next;
        }
        System.out.println();
    }
}
