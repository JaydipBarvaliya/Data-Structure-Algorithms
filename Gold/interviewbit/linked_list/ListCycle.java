package com.interviewbit.linked_list;

import com.interviewbit.linked_list.LinkedNodeUtil.ListNode;

/**
 * https://www.interviewbit.com/problems/list-cycle/
 * <p>
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * <p>
 * Try solving it using constant additional space.
 * <p>
 * Example :
 * <p>
 * Input :
 * <p>
 * *                     ____
 * *                    |    |
 * *                    \/   |
 * *          1 -> 2 -> 3 -> 4
 * <p>
 * Return the node corresponding to node 3.
 *
 * @author neeraj on 2019-08-11
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ListCycle {

    public static void main(String[] args) {
        LinkedNodeUtil linkedNodeUtil = new LinkedNodeUtil();
//        linkedNodeUtil.addData(Arrays.asList(11, 87797, 23219, 41441, 58396, 48953, 94603, 2721, 95832, 49029, 98448, 65450));

        LinkedNodeUtil.ListNode node1 = new ListNode(1);
        LinkedNodeUtil.ListNode node2 = new ListNode(2);
        LinkedNodeUtil.ListNode node3 = new ListNode(3);
        LinkedNodeUtil.ListNode node4 = new ListNode(4);
        LinkedNodeUtil.ListNode node5 = new ListNode(5);
        LinkedNodeUtil.ListNode node6 = new ListNode(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node2;

        linkedNodeUtil.head = detectCycle(node1);

        if (linkedNodeUtil.head != null) {
            System.out.println(linkedNodeUtil.head.val);
        } else {
            System.out.println("NULL");
        }
    }

    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        Boolean loop = false;
        while (fast != null && slow != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                loop = true;
                break;
            }
        }

        if (loop) {
            slow = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
        return null;
    }

}
