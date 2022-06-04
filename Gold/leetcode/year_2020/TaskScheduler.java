package com.leetcode.year_2020;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author neeraj on 11/03/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class TaskScheduler {

    public static void main(String[] args) {
        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2));
    }

    public static int leastInterval(char[] tasks, int n) {
        // We will fetch all the tasks which can be executed during that interval
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : tasks) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Since sorting of task happens based on their frequency, so lets just store frequency only in the heap.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        maxHeap.addAll(freqMap.values());

        List<Integer> scheduledTasks;
        Integer cycles = 0;

        while (!maxHeap.isEmpty()) {
            scheduledTasks = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                if (!maxHeap.isEmpty()) {
                    scheduledTasks.add(maxHeap.remove());
                }
            }

            // Reschedule only those task whose work is left i.e. there is work left for that type of task
            // So let's add them to heap.
            maxHeap.addAll(scheduledTasks.stream().filter(task -> task > 1).map(task -> task - 1).collect(Collectors.toList()));
            cycles += maxHeap.isEmpty() ? scheduledTasks.size() : n + 1;
        }
        return cycles;
    }
}
