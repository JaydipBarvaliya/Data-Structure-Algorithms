package com.leetcode.year_2020.list;

import com.leetcode.year_2020.ListNode;

/**
 * https://leetcode.com/problems/reverse-nodes-in-k-group
 */
public class ReverseNodesInKGroup {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(6);

        listNode.printList(reverseKGroup(listNode, 5));
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;

        ListNode prevSectionHead = null;
        ListNode curr = head;
        ListNode prev;
        int count = 0;
        ListNode sectionHead = null;
        int size = getListSize(head);

        while (curr != null) {
            count = 0;
            prev = null;
            sectionHead = curr;

            if (size >= k) {
                while (count++ < k && curr != null) {
                    size--;
                    ListNode nextOfCurr = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = nextOfCurr;
                }
            } else {
                prev = curr;
                curr = curr.next;
            }

            if (prevSectionHead == null) {
                head = prev;
            } else {
                prevSectionHead.next = prev;
            }

            prevSectionHead = sectionHead;
        }
        return head;
    }

    private static int getListSize(ListNode head) {
        int count = 0;
        ListNode curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }
}
