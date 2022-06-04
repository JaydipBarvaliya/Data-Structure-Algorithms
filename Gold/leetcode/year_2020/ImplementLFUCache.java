package com.leetcode.year_2020;

import com.util.LogUtil;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @author neeraj on 08/10/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 * <p>
 * If capacity is 3.
 * <p>
 * A-1, A-2, B-11, C-20
 * <p>
 * Now if we insert D-40 then it should kick out B-11 instead of A-2
 * If we were using LRU cache, then in that scenarion A-2 will be kicked out.
 */
public class ImplementLFUCache {

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(3);
        lfuCache.put(1, 1);
        lfuCache.put(1, 2);
        lfuCache.put(2, 10);
        lfuCache.put(3, 40);

        System.out.println(lfuCache.get(1));
        System.out.println(lfuCache.get(2));
        System.out.println(lfuCache.get(3));
        lfuCache.put(4, 50);
        System.out.println(lfuCache.get(4));
        System.out.println(lfuCache.get(3));
        System.out.println(lfuCache.get(2));
        System.out.println(lfuCache.get(1));

        LogUtil.logIt("Another example.....");
        lfuCache = new LFUCache(2);
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        System.out.println(lfuCache.get(1));
        lfuCache.put(3, 3);
        System.out.println(lfuCache.get(2));
        System.out.println(lfuCache.get(3));
        lfuCache.put(4, 4);
        System.out.println(lfuCache.get(1));
        System.out.println(lfuCache.get(3));
        System.out.println(lfuCache.get(4));
    }

    /**
     * 3 HashMap should solve this.
     */
    static class LFUCache {

        int capacity;
        Map<Integer, Integer> vals; // Store key and respective value
        Map<Integer, Integer> accessCounts; // Store key and it's respective counts
        int minFreq = -1;
        /**
         * ----> Store Frequency and List of Nodes <----
         * 1 --> [B, C]
         * 2 --> [A]
         * 3 --> [X, Y]
         */
        Map<Integer, LinkedHashSet<Integer>> frequencyAndItsRespectiveNodes;


        public LFUCache(int capacity) {
            this.capacity = capacity;
            vals = new HashMap<>();
            accessCounts = new HashMap<>();
            frequencyAndItsRespectiveNodes = new HashMap<>();
            frequencyAndItsRespectiveNodes.put(1, new LinkedHashSet<>()); // Dummy Placeholder
        }

        public int get(int key) {
            if (!vals.containsKey(key)) {
                return -1;
            }
            // If this item exist, then we need to increment it's access count
            int accessCount = accessCounts.get(key);
            accessCounts.put(key, accessCount + 1);
            frequencyAndItsRespectiveNodes.get(accessCount).remove(key);

            if (accessCount == minFreq && frequencyAndItsRespectiveNodes.get(minFreq).size() == 0) {
                minFreq++; // Since we don't have any more items which are at minFreq.
            }
            if (!frequencyAndItsRespectiveNodes.containsKey(accessCount + 1)) {
                frequencyAndItsRespectiveNodes.put(accessCount + 1, new LinkedHashSet<>());
            }
            frequencyAndItsRespectiveNodes.get(accessCount + 1).add(key);
            return vals.get(key);
        }

        public void put(int key, int value) {
            if (this.capacity <= 0) {
                return;
            }
            if (vals.containsKey(key)) {
                vals.put(key, value);
                get(key);  // This will increase the access count.
                return;
            }

            if (vals.size() >= this.capacity) {
                // We need to remove the first item from the frequencyAndItsRespectiveNodes of minFreq till now
                int evict = frequencyAndItsRespectiveNodes.get(minFreq).iterator().next();
                frequencyAndItsRespectiveNodes.get(minFreq).remove(evict);
                vals.remove(evict); // Remove from the vals map as well.
            }
            vals.put(key, value);
            accessCounts.put(key, 1);
            minFreq = 1;
            frequencyAndItsRespectiveNodes.get(1).add(key);
        }
    }
}
