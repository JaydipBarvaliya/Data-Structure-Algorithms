package com.leetcode.year_2020;

import java.util.Stack;

/**
 * @author neeraj on 16/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class AddTwoNumbersRepresentedByList {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(2);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(3);

        ListNode head2 = new ListNode(5);
        head2.next = new ListNode(6);
        head2.next.next = new ListNode(4);


        ListNode result = addTwoNumbers(head1, head2);
        result.printList(result);

        result = addTwoNumbersII(head1, head2);
        result.printList(result);
    }

    /**
     * https://leetcode.com/problems/add-two-numbers-ii/
     */
    public static ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        ListNode dummyHead = new ListNode(0);
        ListNode dummyHeadPointer = dummyHead;
        int carry = 0;
        int sum = 0;
        int first = 0;
        int second = 0;

        while (!s1.isEmpty() || !s2.isEmpty()) {
            first = s1.isEmpty() ? 0 : s1.pop();
            second = s2.isEmpty() ? 0 : s2.pop();
            sum = carry + first + second;
            carry = sum / 10;

            if (dummyHead.next == null) {
                dummyHead.next = new ListNode(sum % 10);
            } else {
                // Append to front.
                ListNode next = dummyHead.next;
                dummyHead.next = new ListNode(sum % 10);
                dummyHead.next.next = next;
            }
        }

        if (carry > 0) {
            ListNode next = dummyHead.next;
            dummyHead.next = new ListNode(1);
            dummyHead.next.next = next;
        }
        return dummyHead.next;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        ListNode dummyHead = new ListNode(0);
        ListNode dummyHeadPointer = dummyHead;
        int carry = 0;
        int sum = 0;
        int first = 0;
        int second = 0;

        while (temp1 != null || temp2 != null) {
            first = temp1 != null ? temp1.val : 0;
            second = temp2 != null ? temp2.val : 0;

            sum = carry + first + second;
            carry = sum / 10;

            dummyHeadPointer.next = new ListNode(sum % 10);
            dummyHeadPointer = dummyHeadPointer.next;

            if (temp1 != null) {
                temp1 = temp1.next;
            }
            if (temp2 != null) {
                temp2 = temp2.next;
            }
        }
        if (carry > 0) {
            dummyHeadPointer.next = new ListNode(1);
        }
        return dummyHead.next;
    }
}
