package com.leetcode.year_2020;

import com.leetcode.year_2020.LRUCache.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neeraj on 28/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FirstUniqueNumber {

    /**
     * We will solve this problem similar to LRU cache {@link LRUCache}, using Doubly Linked List and HashMap to point to the nodes in
     * that Doubly Linked List. Now the interesting part is uniqueness, so whenever user adds a number which is already
     * present in the doubly-LinkedList just remove that node, or make it orphan
     * <p>
     * Head of the List will always represents First Unique Number if present.
     * We will discard any new entry which already exist in our system.
     */
    static class FirstUnique {

        DoublyLinkedListNode dummyHead;
        DoublyLinkedListNode dummyTail;
        Map<Integer, DoublyLinkedListNode> itemToNodeMapping = new HashMap<>();

        public FirstUnique(int[] nums) {
            dummyHead = new DoublyLinkedListNode(-1);
            dummyTail = new DoublyLinkedListNode(-1);
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;

            for (int i : nums) {
                add(i);
            }
        }

        public int showFirstUnique() {
            if (dummyHead.next != dummyTail) {
                return dummyHead.next.val;
            } else {
                return -1;
            }
        }

        public void add(int value) {
            if (itemToNodeMapping.containsKey(value)) { // if already exist just discard the item from the list.
                DoublyLinkedListNode existingNode = itemToNodeMapping.get(value);
                if (existingNode.next == null && existingNode.prev == null) return;
                existingNode.next.prev = existingNode.prev;
                existingNode.prev.next = existingNode.next;

                // Make it orphan
                existingNode.next = null;
                existingNode.prev = null;
            } else {
                // This value doesn't exist, just add it to the tail
                DoublyLinkedListNode newNode = new DoublyLinkedListNode(value);

                newNode.prev = dummyTail.prev;
                newNode.next = dummyTail;

                newNode.prev.next = newNode;
                newNode.next.prev = newNode;

                itemToNodeMapping.put(value, newNode);
            }
        }
    }

    public static void main(String[] args) {
        FirstUnique firstUnique = new FirstUnique(new int[]{7, 7, 7, 7});
        System.out.println(firstUnique.showFirstUnique());
        firstUnique.add(7);
        firstUnique.add(3);
        firstUnique.add(3);
        firstUnique.add(7);
        firstUnique.add(17);
        System.out.println(firstUnique.showFirstUnique());
    }


}
