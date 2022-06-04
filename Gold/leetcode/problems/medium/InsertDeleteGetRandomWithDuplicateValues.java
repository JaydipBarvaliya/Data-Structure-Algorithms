package com.leetcode.problems.medium;

import java.util.*;

/**
 * @author neeraj on 17/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class InsertDeleteGetRandomWithDuplicateValues {

    public static void main(String[] args) {
        RandomizedCollection obj = new RandomizedCollection();
        obj.insert(1);
        obj.insert(1);
        obj.insert(2);
        obj.insert(2);
        obj.insert(2);
        obj.insert(2);
        obj.insert(2);
        obj.insert(2);

        for(int i=0;i<1000;i++) {
            System.out.println(obj.getRandom());
        }
    }

    static class RandomizedCollection {

        /**
         * Initialize your data structure here.
         */
        List<Integer> data;
        Map<Integer, Set<Integer>> dataToIndexMap;
        Random random;

        public RandomizedCollection() {
            random = new Random();
            data = new ArrayList<>();
            dataToIndexMap = new HashMap<>();
        }

        /**
         * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
         */
        public boolean insert(int val) {
            boolean exist = dataToIndexMap.containsKey(val); // We are allowing duplicates.
            data.add(val); // append to last in the list
            // Point to the node in list from hashMap
            if (!exist) {
                dataToIndexMap.put(val, new HashSet<>());
            }
            dataToIndexMap.get(val).add(data.size() - 1); // If duplicate adding multiple indexes.
            return !exist;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {
            if (!dataToIndexMap.containsKey(val)) return false;

            int index = dataToIndexMap.get(val).iterator().next();
            dataToIndexMap.get(val).remove(index);

            if (index < data.size() - 1) {
                int lastItem = data.get(data.size() - 1);
                data.set(index, lastItem); // Removing and putting the last item on this index.

                dataToIndexMap.get(lastItem).remove(data.size() - 1); // Removing the last occurrence from the map for last Item.
                dataToIndexMap.get(lastItem).add(index); // Setting the removed item index in the map as the lastItem index.
            }
            data.remove(data.size() - 1);
            if (dataToIndexMap.get(val).isEmpty()) dataToIndexMap.remove(val);
            return true;
        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            int randomValue = random.nextInt(data.size());
            return data.get(randomValue); // O(1) index based access.
        }
    }
}
