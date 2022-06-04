package com.interviewbit.linked_list;

import com.interviewbit.linked_list.LinkedNodeUtil.ListNode;

import java.util.Arrays;

/**
 * @author neeraj on 2019-08-11
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ReverseListInPlace {

    public static void main(String[] args) {
        LinkedNodeUtil util = new LinkedNodeUtil();
        util.addData(Arrays.asList(1, 2, 3));
        util.printList();

        util.head = reverseBetween(util.head, 1, 2);
        util.printList();

        util = new LinkedNodeUtil();
        util.addData(Arrays.asList(1, 2, 3, 4, 5));
        util.head = reverseListInGroups(util.head, 3);
        util.printList();
    }



    public static ListNode reverseBetween(ListNode A, int from, int to) {
        int counter = 1;
        ListNode temp = A;
        ListNode prev = null;
        ListNode tailOfPreviousBlock = null;
        ListNode nextOfTemp = null;
        ListNode startOfBlock = null;
        while (counter++ < from) {
            tailOfPreviousBlock = temp;
            temp = temp.next;
        }

        counter = from - 1;
        startOfBlock = temp;
        while (counter++ < to) {
            nextOfTemp = temp.next;
            temp.next = prev;
            prev = temp;
            temp = nextOfTemp;
        }

        if (tailOfPreviousBlock != null) {
            tailOfPreviousBlock.next = prev;
        } else {
            A = prev;
        }

        startOfBlock.next = temp;
        return A;
    }

    public static ListNode reverseListInGroups(ListNode list, int groupSize) {
        ListNode temp = list;
        ListNode head = list;
        ListNode prev = null;
        ListNode next = null;
        ListNode thisHead = null;
        ListNode prevHead = null;
        int count;
        while (temp != null) {
            thisHead = temp;
            count = 0;
            prev = null;
            while (count++ < groupSize && temp != null) {
                next = temp.next;
                temp.next = prev;
                prev = temp;
                temp = next;
            }

            if (prevHead == null) {
                head = prev;
            } else {
                prevHead.next = prev;
            }
            prevHead = thisHead;
        }
        return head;
    }
}
