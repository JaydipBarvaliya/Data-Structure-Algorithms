package com.company.amazon;

import java.util.Collections;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Using Priority Queue with Comparator as the reverse Order of Long values
 */
public class MinimumSumOfSquaresOfCharacterCount {

    public static void main(String[] args) {
        getMinimumSumOfSquares("abccc", 1);
        getMinimumSumOfSquares("aaab", 2);
        getMinimumSumOfSquares("abbccc", 3);
    }

    public static void getMinimumSumOfSquares(String str, int K) {
        char[] arr = str.toCharArray();
        Map<Character, Long> freqMap = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        int minSum = getMinimumSum(freqMap, str.toCharArray(), K);
        System.out.println(minSum);
    }

    public static int getMinimumSum(Map<Character, Long> freqMap, char[] arr, int K) {
        PriorityQueue<Long> priorityQueue = new PriorityQueue<Long>(Collections.reverseOrder());
        for (Long val : freqMap.values()) {
            if (val > 0) {
                priorityQueue.add(val);
            }
        }
        // After K characters are removed
        while (K > 0) {
            long temp = priorityQueue.poll();
            priorityQueue.add(--temp);
            K--;
        }

        // Let's calculate SUM
        int SUM = 0;
        while (!priorityQueue.isEmpty()) {
            SUM += Math.pow(priorityQueue.poll(), 2);
        }
        return SUM;
    }
}
