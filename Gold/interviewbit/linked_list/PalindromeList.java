package com.interviewbit.linked_list;

/**
 * https://www.interviewbit.com/problems/palindrome-list/
 * <p>
 * Given a singly linked list, determine if its a palindrome. Return 1 or 0 denoting if its a palindrome or not, respectively.
 * <p>
 * Notes:
 * <p>
 * Expected solution is linear in time and constant in space.
 * For example,
 * <p>
 * List 1-->2-->1 is a palindrome.
 * List 1-->2-->3 is not a palindrome.
 *
 * @author neeraj on 2019-08-08
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PalindromeList {

    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);

        System.out.println(isPalindromeList(head));
    }

    /**
     * A) Find Middle of the list
     * B) Reverse Mid+1 to the end of list and keep a pointer to head of reversed list.
     * C) Start iterating from ORIGINAL_HEAD and REVERSE_HEAD together and compare.
     */
    public static int isPalindromeList(ListNode A) {
        ListNode temp = A;
        ListNode slowPointer = temp;
        ListNode fastPointer = temp;
        Boolean isOddSizeLength = false;
        ListNode mid = null;

        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }

        // if Odd Size List, then previousLoop break due to fastPointer.next was null
        if (fastPointer != null) {
            isOddSizeLength = true;
        }
        mid = slowPointer;

        // Now let's reverse the second half
        ListNode headOfReversedList = reverseList(isOddSizeLength ? mid.next : mid);

        slowPointer = A;
        while (headOfReversedList != null) {
            if (slowPointer.val == headOfReversedList.val) {
                slowPointer = slowPointer.next;
                headOfReversedList = headOfReversedList.next;
            } else {
                return 0;
            }
        }
        return 1;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = null;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}

