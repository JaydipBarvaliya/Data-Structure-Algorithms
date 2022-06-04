package com.leetcode.year_2020;

/**
 * @author neeraj on 18/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RandomListNode {
    public int val;
    public RandomListNode next, random;

    public RandomListNode(int val) {
        this.val = val;
    }

    public void printList(RandomListNode head) {
        RandomListNode curr = head;
        int next = -1;
        int random = -1;
        System.out.println("NODE |   NEXT  |   RANDOM");
        while (curr != null) {
            next = curr.next != null ? curr.next.val : -1;
            random = curr.random != null ? curr.random.val : -1;
            System.out.print(curr.val + "    |    " + next + "    |    " + random);
            curr = curr.next;
            System.out.println();
        }
    }
}
