package com.leetcode.year_2020;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neeraj on 24/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LRUCache {

    private Map<Integer, DoublyLinkedListNode> cache;
    private Integer capacity;
    DoublyLinkedListNode dummyHead;
    DoublyLinkedListNode dummyTail;

    static class DoublyLinkedListNode {
        public DoublyLinkedListNode prev;
        public DoublyLinkedListNode next;
        public int key;
        public int val;

        public DoublyLinkedListNode() {
        }

        /**
         * This will be used only by {@link FirstUniqueNumber}
         */
        public DoublyLinkedListNode(int val) {
            this.val = val;
        }

        public DoublyLinkedListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        dummyHead = new DoublyLinkedListNode();
        dummyTail = new DoublyLinkedListNode();
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;

    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            DoublyLinkedListNode existingEntry = cache.get(key);
            existingEntry.prev.next = existingEntry.next;
            existingEntry.next.prev = existingEntry.prev;

            DoublyLinkedListNode nextOfDummyHead = dummyHead.next;
            dummyHead.next = existingEntry;
            existingEntry.prev = dummyHead;
            existingEntry.next = nextOfDummyHead;
            nextOfDummyHead.prev = existingEntry;
            return cache.get(key).val;
        } else {
            return -1;
        }
    }

    // Move the New/Existing item to the head of Doubly Linked List.
    public void put(int key, int value) {
        DoublyLinkedListNode newEntry = new DoublyLinkedListNode(key, value);
        if (cache.size() < capacity || cache.containsKey(key)) {
            if (cache.containsKey(key)) {
                DoublyLinkedListNode existingEntry = cache.get(key);
                existingEntry.val = value;
                existingEntry.prev.next = existingEntry.next;
                existingEntry.next.prev = existingEntry.prev;

                DoublyLinkedListNode nextOfDummyHead = dummyHead.next;
                dummyHead.next = existingEntry;
                existingEntry.prev = dummyHead;
                existingEntry.next = nextOfDummyHead;
                nextOfDummyHead.prev = existingEntry;
                cache.put(key, existingEntry);
            } else {
                DoublyLinkedListNode nextOfDummyHead = dummyHead.next;
                dummyHead.next = newEntry;
                newEntry.prev = dummyHead;
                newEntry.next = nextOfDummyHead;
                nextOfDummyHead.prev = newEntry;
                cache.put(key, newEntry);
            }
        } else { // Cache is full
            // Remove the last node.
            cache.remove(dummyTail.prev.key);

            dummyTail.prev = dummyTail.prev.prev;
            dummyTail.prev.next = dummyTail;

            // Add the new node to the head.
            DoublyLinkedListNode nextOfDummyHead = dummyHead.next;
            dummyHead.next = newEntry;
            newEntry.prev = dummyHead;
            newEntry.next = nextOfDummyHead;
            nextOfDummyHead.prev = newEntry;
            cache.put(key, newEntry);
        }
    }

    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4

        System.out.println("=========================");
        cache = new LRUCache(1);
        cache.put(2, 1);
        System.out.println(cache.get(2));
        cache.put(3, 2);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));

        System.out.println("==========================");
        cache = new LRUCache(2);
//        ["LRUCache","put","put","get","put","put","get"]
//        [[2],[2,1],[2,2],[2],[1,1],[4,1],[2]]
        cache.put(2, 1);
        cache.put(2, 2);
        System.out.println(cache.get(2));
        cache.put(1, 1);
        cache.put(4, 1);
        System.out.println(cache.get(2));

        System.out.println("==============");
        //["LRUCache","get","put","get","put","put","get","get"]
        //[[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
        cache = new LRUCache(2);
        System.out.println(cache.get(2));
        cache.put(2, 6);
        System.out.println(cache.get(1));
        cache.put(1, 5);
        cache.put(1, 2);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }
}
