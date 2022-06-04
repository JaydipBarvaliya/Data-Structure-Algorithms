package com.interviewbit.linked_list;

import static com.util.LogUtil.newLine;

/**
 * https://www.interviewbit.com/problems/merge-two-sorted-lists/
 * <p>
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists, and should also be sorted.
 * <p>
 * For example, given following linked lists :
 * <p>
 * 5 -> 8 -> 20
 * 4 -> 11 -> 15
 * The merged list should be :
 * <p>
 * 4 -> 5 -> 8 -> 11 -> 15 -> 20
 *
 * @author neeraj on 2019-08-08
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MergeTwoSortedList {

    ListNode head;

    public static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
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

    public ListNode mergeTwoListRecursively(ListNode A, ListNode B) {
        if (A == null) return B;
        if (B == null) return A;

        if (A.val < B.val) {
            A.next = mergeTwoListRecursively(A.next, B);
            return A;
        } else {
            B.next = mergeTwoListRecursively(A, B.next);
            return B;
        }
    }

    public ListNode mergeTwoLists(ListNode A, ListNode B) {
        MergeTwoSortedList result = new MergeTwoSortedList();
        ListNode temp1 = A;
        ListNode temp2 = B;
        int smallerData = 0;

        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                smallerData = temp1.val;
                temp1 = temp1.next;
            } else {
                smallerData = temp2.val;
                temp2 = temp2.next;
            }
            result.append(smallerData);
        }

        while (temp1 != null) {
            result.append(temp1.val);
            temp1 = temp1.next;
        }

        while (temp2 != null) {
            result.append(temp2.val);
            temp2 = temp2.next;
        }
        return result.head;
    }

    public static void main(String[] args) {
        MergeTwoSortedList obj = new MergeTwoSortedList();
        obj.append(1);
        obj.append(3);
        obj.append(5);

        MergeTwoSortedList obj2 = new MergeTwoSortedList();
        obj2.append(2);
        obj2.append(4);
        obj2.append(6);
        obj2.append(8);
        obj2.append(10);

        obj.head = obj.mergeTwoListRecursively(obj.head, obj2.head);

        obj.printList();
    }
}
