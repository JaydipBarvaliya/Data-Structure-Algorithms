package com.leetcode.year_2020;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neeraj on 02/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ImplementAHashTable {

    public static void main(String[] args) {
        HashTable<Integer, String> map = new HashTable<>();
        map.put(1, "Neeraj");
        map.put(2, "Sakshi");
        map.put(3, "Ayushi");

        System.out.println(map.isEmpty());
        System.out.println(map.size());

        System.out.println(map.get(2));
        System.out.println(map.get(3));
        System.out.println(map.get(1));

        // Override the key.
        map.put(2, "Jitu");
        map.put(3, "Sagar");

        System.out.println(map.get(2));
        System.out.println(map.get(3));
    }

    /*
     * We will use generics so that the keys and values can be whatever the
     * caller would like. We can implement the buckets with a plain array, but
     * here we use an ArrayList (which uses an array underneath) for convenience
     */
    public static class HashTable<K, V> {
        private List<Entry<K, V>> buckets;
        int capacity = 20;
        int size = 0;

        public HashTable() {
            this.buckets = new ArrayList<>();

            // Initialize all of the buckets to empty
            for (int i = 0; i < capacity; i++) {
                buckets.add(null);
            }
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size() == 0;
        }

        public V get(K key) {
            int bucketId = getBucketId(key);

            Entry<K, V> head = buckets.get(bucketId);
            head = getReferenceIfExist(head, key);
            return head == null ? null : head.value;
        }

        public Entry<K, V> getReferenceIfExist(Entry<K, V> head, K key) {
            while (head != null && head.key != key) {
                head = head.next;
            }
            return head;
        }

        public void put(K key, V value) {
            int bucketId = getBucketId(key);
            Entry<K, V> headOfBucket = buckets.get(bucketId);

            Entry<K, V> bucketEntry = getReferenceIfExist(headOfBucket, key);

            if (bucketEntry == null) { // There is no existing value
                Entry<K, V> newEntry = new Entry<>(key, value);
                newEntry.next = headOfBucket;
                buckets.set(bucketId, newEntry);
                size++;
            } else { // Key was present.
                bucketEntry.value = value;
            }
        }

        private int getBucketId(K key) {
            // Default implementation of HashCode
            int hashCode = key.hashCode();
            int bucketId = hashCode % capacity;
            return bucketId;
        }
    }

    /**
     * These are the entries in our implementation of Hashtable.
     * These node represent key, value and pointer to next node in the bucket
     * of Hashtable.
     */
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next; // Link to the next item in LinkedList.

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
