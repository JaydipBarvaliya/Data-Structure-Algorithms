package com.interviewbit.linked_list;

import com.interviewbit.linked_list.LinkedNodeUtil.ListNode;

import java.util.Arrays;

/**
 * @author neeraj on 2019-08-11
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RotateList {

    public static void main(String[] args) {
        LinkedNodeUtil util = new LinkedNodeUtil();
        util.addData(Arrays.asList(1, 2, 3, 4, 5));

        util.printList();

        util.head = rotateRight(util.head, 8);
        util.printList();
    }

    public static ListNode rotateRight(ListNode A, int B) {
        int size = LinkedNodeUtil.getSize(A);
        int rotationInRange = B % size;
        ListNode slowPointer = A;
        ListNode fastPointer = A;
        ListNode head = A;
        ListNode temp = A;
        ListNode prev = null;
        int counter = 0;

        if(rotationInRange == 0) {
            return A;
        }
        while (counter++ < rotationInRange) {
            fastPointer = fastPointer.next;
        }

        while (fastPointer != null) {
            prev = slowPointer;
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next;
        }

        A = slowPointer;
        prev.next = null;
        temp = A;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = head;
        return A;
    }
}
