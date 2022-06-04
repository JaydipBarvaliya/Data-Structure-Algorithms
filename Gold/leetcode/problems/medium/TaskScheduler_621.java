package com.leetcode.problems.medium;

import java.util.*;

/**
 * @author neeraj on 08/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class TaskScheduler_621 {

    public static void main(String[] args) {
        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2));
    }

    public static int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> taskFrequencyMap = new HashMap<>();
        for (char c : tasks) {
            taskFrequencyMap.put(c, taskFrequencyMap.getOrDefault(c, 0) + 1);
        }

        // Now we will process frequently occurred tasks first
        // So we will maintain the MaxHeap for the same
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        maxHeap.addAll(taskFrequencyMap.values());
        int cycles = 0;
        while (!maxHeap.isEmpty()) {
            List<Integer> tasksFrequencyList = new ArrayList<>();
            // Why == n, because we don't want to wait for CPU to cool down if there are tasks to process
            for (int i = 0; i <= n; i++) {
                if (!maxHeap.isEmpty()) {
                    tasksFrequencyList.add(maxHeap.remove());
                }
            }
            for (int i : tasksFrequencyList) {
                if (i - 1 > 0) {
                    maxHeap.add(i-1);
                }
            }

            // So Cycles will be
            // If HEAP is empty that means CPU doesn't have to wait for cool-down cycle
            // and the only time CPU ran is how many tasks it's processed
            // If HEAP is not empty that means CPU have to wait for full cool down period
            // to start next task.

            // For Example [A,A,A,B,B,B] coolDown = 2
            // MAX_HEAP = 3A, 3B
            // Tasks Processed --> A,B,CPU_COOL_DOWN,A,B,CPU_COOL_DOWN,A,B.
            cycles += maxHeap.isEmpty() ? tasksFrequencyList.size() : n + 1;
        }
        return cycles;
    }
}
