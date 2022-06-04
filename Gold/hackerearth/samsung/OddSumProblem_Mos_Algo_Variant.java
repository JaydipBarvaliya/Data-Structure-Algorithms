package com.hackerearth.samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OddSumProblem_Mos_Algo_Variant {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int arrLength = readInt(br);
        long[] arr = new long[arrLength];

        String input = br.readLine();
        String[] splitted = input.split("\\s+");
        for (int i = 0; i < splitted.length; i++) {
            arr[i] = Long.parseLong(splitted[i]);
        }

        int queriesLength = readInt(br);
        int counter = 0;
        while (counter++ < queriesLength) {
            input = br.readLine();
            splitted = input.split("\\s+");

            int lowRange = Integer.parseInt(splitted[0]);
            int highRange = Integer.parseInt(splitted[1]);
            System.out.println(calculateOddSumInRange(arr, lowRange - 1, highRange - 1));
        }
    }

    private static long calculateOddSumInRange(long[] arr, int lowRange, int highRange) {
        Map<Long, Long> frequencyCount = getFrequencyCountInRanges(arr, lowRange, highRange);
        Set<Long> keys = frequencyCount.keySet();
        Long sum = 0l;
        for (Long i : keys) {
            if (frequencyCount.get(i) % 2 != 0) {
                sum += i * frequencyCount.get(i);
            }
        }
        return sum;
    }

    private static Map<Long, Long> getFrequencyCountInRanges(long[] arr, int lowRange, int highRange) {
        Map<Long, Long> frequencyCount = new HashMap<>();

        for (int i = lowRange; i <= highRange; i++) {
            if (frequencyCount.containsKey(arr[i])) {
                frequencyCount.put(arr[i], frequencyCount.get(arr[i]) + 1);
            } else {
                frequencyCount.put(arr[i], 1l);
            }
        }
        return frequencyCount;
    }

    public static Integer readInt(BufferedReader br) throws Exception {
        return Integer.parseInt(br.readLine());
    }
}
