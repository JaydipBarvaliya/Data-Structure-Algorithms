package com.interviewbit.linked_list;

import com.interviewbit.linked_list.LinkedNodeUtil.ListNode;

import java.util.Arrays;

import static com.interviewbit.linked_list.LinkedNodeUtil.reverseList;

/**
 * https://www.interviewbit.com/problems/reorder-list/
 * <p>
 * Given a singly linked list
 * <p>
 * L: L0 → L1 → … → Ln-1 → Ln,
 * reorder it to:
 * <p>
 * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 * You must do this in-place without altering the nodes’ values.
 * <p>
 * For example,
 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
 *
 * @author neeraj on 2019-08-11
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ReOrderList {

    public static void main(String[] args) {
        LinkedNodeUtil linkedNodeUtil = new LinkedNodeUtil();
        linkedNodeUtil.addData(Arrays.asList(69, 7, 56, 25, 29, 37, 19, 84, 43, 74, 4, 11, 36, 50, 14, 23, 3, 26, 88, 35, 28, 85, 76, 32, 66, 55, 42, 100, 10, 44, 95, 101, 77, 80, 91, 41));

        linkedNodeUtil.head = reorderList(linkedNodeUtil.head);
        linkedNodeUtil.printList();
    }

    public static ListNode reorderList(ListNode A) {
        ListNode temp = A;
        ListNode nextOfTemp;
        ListNode nextOfMiddle;
        ListNode originalMiddle = null;

        // FInd Middle
        ListNode middleNode = LinkedNodeUtil.findMiddle(A);

        // Reverse from middle Node
        middleNode = reverseList(middleNode);

        // Do the re-Ordering
        while (middleNode != null) {
            nextOfTemp = temp.next;
            nextOfMiddle = middleNode.next;
            temp.next = middleNode;
            temp = nextOfTemp;
            if (temp != middleNode.next) {
                middleNode.next = temp;
            }
            middleNode = nextOfMiddle;
        }

        if(temp != null) {
            temp.next = null;
        }
        return A;
    }
}
