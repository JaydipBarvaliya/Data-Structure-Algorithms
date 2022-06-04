package com.interviewbit.linked_list;

import static com.util.LogUtil.newLine;

/**
 * https://www.interviewbit.com/problems/remove-nth-node-from-list-end/
 * <p>
 * Given a linked list, remove the nth node from the end of list and return its head.
 * <p>
 * For example,
 * Given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * <p>
 * Note:
 * If n is greater than the size of the list, remove the first node of the list.
 * Try doing it using constant additional space.
 *
 * @author neeraj on 2019-08-10
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RemoveNthNodeFromListEnd {
    ListNode head;

    public void printList() {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + " , ");
            temp = temp.next;
        }
        newLine();
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

    public class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        RemoveNthNodeFromListEnd obj = new RemoveNthNodeFromListEnd();
        obj.append(1);
        obj.append(2);
        obj.append(3);
        obj.append(4);
        obj.append(5);

        obj.head = obj.removeNthFromEnd(obj.head, 3);
        obj.printList();

    }

    public ListNode removeNthFromEnd(ListNode A, int B) {
        ListNode slowPointer = A;
        ListNode fastPointer = A;
        ListNode previousPointer = null;
        int counter = 0;
        while (counter++ < B) {
            fastPointer = fastPointer.next;

            // Condition where the B value is greater than the original size of the list.
            if (fastPointer == null) {
                A = A.next;
                return A;
            }
        }

        while (fastPointer != null) {
            previousPointer = slowPointer;
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next;
        }
        previousPointer.next = slowPointer.next;
        return A;
    }
}
