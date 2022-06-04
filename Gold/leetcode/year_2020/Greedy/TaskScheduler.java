package com.leetcode.year_2020.Greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * https://leetcode.com/problems/task-scheduler/
 * <p>
 * https://leetcode.com/problems/task-scheduler/discuss/104501/Java-PriorityQueue-solution-Similar-problem-Rearrange-string-K-distance-apart
 *
 * @author neeraj on 23/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class TaskScheduler {

    public static void main(String[] args) {
//        System.out.println(leastIntervalNotSoOptimized(new char[]{
//                'A', 'A', 'A', 'B', 'B', 'B'
//        }, 0));
//
//        System.out.println(leastIntervalNotSoOptimized(new char[]{
//                'A', 'A', 'A', 'B', 'B', 'B'
//        }, 2));
//        System.out.println(leastIntervalNotSoOptimized(new char[]{
//                'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'
//        }, 2));
//        System.out.println(leastIntervalNotSoOptimized(new char[]{
//                'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'
//        }, 1));


        System.out.println(leastIntervalOptimized(new char[]{
                'A', 'A', 'A', 'B', 'B', 'B'
        }, 0));

        System.out.println(leastIntervalOptimized(new char[]{
                'A', 'A', 'A', 'B', 'B', 'B'
        }, 2));
        System.out.println(leastIntervalOptimized(new char[]{
                'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'
        }, 2));
        System.out.println(leastIntervalOptimized(new char[]{
                'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'
        }, 1));
    }

    public static int leastIntervalOptimized(char[] tasks, int n) {
        /**
         * We will follow the same principle of having PriorityQueue and picking up all highest freq task
         */
        final Map<Character, Integer> taskFreq = new HashMap<>();
        for (char ch : tasks) {
            taskFreq.put(ch, taskFreq.getOrDefault(ch, 0) + 1);
        }

        final PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
                (a, b) -> a.getValue() != b.getValue() ? b.getValue() - a.getValue() // Freq not same then descending sort
                        : a.getKey() - b.getKey()); // When freq is same get the lexicographic item first
        maxHeap.addAll(taskFreq.entrySet());

        int count = 0;
        while (!maxHeap.isEmpty()) {
            int K = n + 1; // Will be used to find out idle time
            final List<Map.Entry> tempStorage = new ArrayList<>(); // During 1 cycle of n, will use it for temp storing processed task

            while (K > 0 && !maxHeap.isEmpty()) {
                final Map.Entry<Character, Integer> maxFreqItem = maxHeap.poll();
                maxFreqItem.setValue(maxFreqItem.getValue() - 1);
                tempStorage.add(maxFreqItem);
                K--;
                count++; // Successfully executed task.
            }

            // Check if they can be processed again
            for (Map.Entry<Character, Integer> processedTask : tempStorage) {
                if (processedTask.getValue() > 0) {
                    maxHeap.add(processedTask);
                }
            }

            if (maxHeap.isEmpty()) {
                break; // Done with all tasks
            }

            count += K; // if k > 0, then it means we need to be idle
        }
        return count;
    }

    public static int leastIntervalNotSoOptimized(char[] tasks, int n) {
        Map<Character, Integer> taskFreq = new HashMap<>();
        for (char c : tasks) {
            taskFreq.put(c, taskFreq.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> taskLastProcessedIndex = new HashMap<>();

        // We need a priority queue
        // we want high frequency item on top.
        PriorityQueue<Character> maxHeap = new PriorityQueue<>(Comparator.comparingInt(A -> -taskFreq.get(A)));
        maxHeap.addAll(taskFreq.keySet());

        Stack<Character> stack = new Stack<>();

        int timer = 1;
        while (!maxHeap.isEmpty()) {
            char item = maxHeap.poll();

            // Item was already processed.
            if (taskLastProcessedIndex.containsKey(item)) {
                int diff = timer - taskLastProcessedIndex.get(item);
                if (diff > n) { // diff > allowed difference.
                    addItemToHeapIfRequired(taskFreq, item, maxHeap);
                    taskLastProcessedIndex.put(item, timer);
                } else {
                    while (!maxHeap.isEmpty()) {
                        char itemOnTop = maxHeap.peek();
                        if (taskLastProcessedIndex.containsKey(itemOnTop)) {
                            diff = timer - taskLastProcessedIndex.get(itemOnTop);
                            if (diff > n) {
                                itemOnTop = maxHeap.poll();
                                addItemToHeapIfRequired(taskFreq, itemOnTop, maxHeap);
                                break;
                            } else {
                                stack.push(maxHeap.poll());
                            }
                        } else {
                            itemOnTop = maxHeap.poll();
                            taskLastProcessedIndex.put(itemOnTop, timer);
                            addItemToHeapIfRequired(taskFreq, itemOnTop, maxHeap);
                            break;
                        }
                    }
                    while (!stack.isEmpty()) {
                        maxHeap.add(stack.pop());
                    }
                    maxHeap.add(item);
                }
            } else { // encountering the item for the first time
                taskLastProcessedIndex.put(item, timer);
                addItemToHeapIfRequired(taskFreq, item, maxHeap);
            }
            if (!maxHeap.isEmpty()) {
                timer++;
            }
        }
        return timer;
    }

    private static void addItemToHeapIfRequired(Map<Character, Integer> taskFreq,
                                                char item, PriorityQueue<Character> maxHeap) {
        int itemsFreqLeftToBeProcessed = taskFreq.get(item) - 1;
        if (itemsFreqLeftToBeProcessed > 0) {
            taskFreq.put(item, itemsFreqLeftToBeProcessed);
            maxHeap.add(item);
        }
    }
}
