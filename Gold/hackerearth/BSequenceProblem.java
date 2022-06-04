package com.hackerearth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class BSequenceProblem {

    public static void main(String[] args) {
        List<Long> sequenceList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            long sizeOfSequence = Long.parseLong(br.readLine());
            String[] inputSequence = br.readLine().split(" ");

            for (String s : inputSequence) {
                sequenceList.add(Long.parseLong(s));
            }
            Map<Long, Long> frequencyMap = getFrequency(sequenceList);
            Long maxValue = Collections.max(sequenceList);

            long noOfOperations = Long.parseLong(br.readLine());
            long count = 0;
            while (count++ < noOfOperations) {

                long operation = Long.parseLong(br.readLine());

                // Handling the Maximum Value case specially
                if (operation >= maxValue) {
                    System.out.println(sequenceList.size());
                    continue;
                }
                Long frequency = frequencyMap.get(operation);
                // If the value is already there in increasing and decreasing sequence as well
                if (frequency != null && frequency == 2) {
                    System.out.println(sequenceList.size());
                    continue;
                } else if (frequency != null && frequency == 1) {
                    sequenceList.add(getIndexInDecreasingSide(sequenceList, operation, maxValue), operation);
                    frequencyMap.put(operation, 2L);
                    System.out.println(sequenceList.size());
                    continue;
                } else if (frequency == null) {
                    sequenceList.add(getIndexInIncreasingSide(sequenceList, operation, maxValue), operation);
                    frequencyMap.put(operation, 1L);
                    System.out.println(sequenceList.size());
                    continue;
                }
                System.out.println(sequenceList.size());
            }
            printList(sequenceList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getIndexInIncreasingSide(List<Long> list, long number, long maxVal) {
        int i = 0;
        for (; i <= list.indexOf(maxVal); i++) {
            if (number < list.get(i)) {
                break;
            }
        }
        return i;
    }

    private static int getIndexInDecreasingSide(List<Long> list, long number, long maxVal) {
        int i = list.indexOf(maxVal) + 1;
        for (; i < list.size(); i++) {
            if (number > list.get(i)) {
                break;
            }
        }
        return i;
    }

    private static void printList(List<Long> list) {
        for (Long i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static Map<Long, Long> getFrequency(List<Long> list) {
        return list.stream().collect(groupingBy(Function.identity(), counting()));
    }
}
