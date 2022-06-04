package com.interviewbit.linked_list;

import static com.util.LogUtil.newLine;

/**
 * https://www.interviewbit.com/problems/remove-duplicates-from-sorted-list/
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * <p>
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 *
 * @author neeraj on 2019-08-09
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("DuplicatedCode")
public class RemoveDuplicatesFromSortedList {

    ListNode head;

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedList obj = new RemoveDuplicatesFromSortedList();
        obj.append(1);
        obj.append(1);
//        obj.head = deleteDuplicates(obj.head);
//        obj.printList();

        obj = new RemoveDuplicatesFromSortedList();
        obj.append(1);
        obj.append(1);
        obj.append(1);
        obj.append(1);
        obj.append(1);
        obj.append(1);
        obj.append(2);
        obj.append(2);
        obj.append(2);
        obj.append(2);
        obj.append(2);
        obj.append(2);
        obj.append(3);
        obj.append(3);
        obj.append(3);
        obj.append(3);
        obj.append(3);
        obj.append(3);
        obj.append(4);
        obj.append(4);
        obj.append(4);
        obj.append(4);
        obj.append(4);
        obj.append(5);
        obj.append(5);
        obj.append(5);
        obj.append(5);
        obj.append(6);
        obj.append(6);
        obj.append(7);
        obj.append(7);
        obj.append(7);
        obj.append(7);
        obj.append(8);
        obj.append(8);
        obj.append(8);
        obj.append(8);
        obj.append(8);
        obj.append(8);
        obj.append(8);
        obj.append(8);
        obj.append(8);
        obj.append(9);
        obj.append(9);
        obj.append(9);
        obj.append(9);
        obj.append(9);
        obj.append(9);
        obj.append(9);
        obj.append(9);
        obj.append(9);
        obj.append(9);
        obj.append(9);
        obj.append(10);
        obj.append(10);
        obj.append(10);
        obj.append(10);
        obj.append(10);
        obj.append(10);
        obj.append(10);
        obj.append(10);
        obj.append(10);
        obj.append(10);
        obj.append(10);
        obj.append(11);
        obj.append(11);
        obj.append(11);
        obj.append(11);
        obj.append(11);
        obj.append(11);
        obj.append(11);


        obj.head = obj.deleteNodesWithDuplicateValues(obj.head);
        obj.printList();
    }

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

    public static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode deleteDuplicates(ListNode A) {
        ListNode temp = A;
        ListNode prevValue = null;

        while (temp != null) {
            if (prevValue != null && prevValue.val == temp.val) {
                while (temp != null && temp.val == prevValue.val) {
                    temp = temp.next;
                }
                prevValue.next = temp;
                if (temp != null) {
                    prevValue = temp;
                    temp = temp.next;
                }
            } else {
                prevValue = temp;
                temp = temp.next;
            }
        }
        return A;
    }

    /**
     * https://www.interviewbit.com/problems/remove-duplicates-from-sorted-list-ii/
     * <p>
     * Remove Duplicates from Sorted List II
     * Asked in: Microsoft VMWare
     * <p>
     * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
     * <p>
     * For example,
     * Given 1->2->3->3->4->4->5, return 1->2->5.
     * Given 1->1->1->2->3, return 2->3.
     *
     * @param A
     * @return
     */
    public ListNode deleteNodesWithDuplicateValues(ListNode A) {
        Integer previousValue = -1;
        ListNode prev = null;
        ListNode temp = A;
        Boolean foundDuplicate = false;
        Integer previousBlockValue = 0;

        while (temp != null) {
            // Check the block
            while (temp != null && temp.next != null && temp.val == temp.next.val) {
                previousBlockValue = temp.val;
                temp = temp.next;
            }
            if (temp.val == previousBlockValue) {
                temp = temp.next;
                if (prev != null) {
                    prev.next = temp;
                } else {
                    A = temp;
                }
            } else {
                prev = temp;
                temp = temp.next;
            }
        }
        return A;
    }
}
