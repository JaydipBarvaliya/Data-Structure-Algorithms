package com.company.amazon;

import java.util.HashMap;
import java.util.Map;

public class FindNumberOfIteration {

    private static Map<Integer, Integer> frequencyMap = new HashMap<>();

    public static void main(String[] args) {
        int[] arr = new int[]{1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1};
        solveNumberOfIterationProblem(arr);

        arr = new int[]{0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1};
        solveNumberOfIterationProblem(arr);

        arr = new int[]{0, 0, 0, 0};
        solveNumberOfIterationProblem(arr);
    }

    public static void solveNumberOfIterationProblem(int[] arr) {
        frequencyMap = new HashMap<>();
        updateFrequencies(arr);
        System.out.println(frequencyMap);
        Integer beforeIteration = 0;
        Integer afterIteration = 0;
        Integer totalIteration = 0;
        while (frequencyMap.get(0) > 0) {
            beforeIteration = frequencyMap.get(0);
            getNumberOfIteration(arr);
            afterIteration = frequencyMap.get(0);
            if (beforeIteration == afterIteration) {
                System.out.println("Filling with 1's is not possible");
                System.exit(0);
            }
            totalIteration++;
        }
        System.out.println("Total Iteration required " + totalIteration);
    }

    public static void updateFrequencies(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (frequencyMap.containsKey(arr[i])) {
                frequencyMap.put(arr[i], frequencyMap.get(arr[i]) + 1);
            } else {
                frequencyMap.put(arr[i], 1);
            }
        }
    }

    public static void getNumberOfIteration(int[] arr) {
        for (int i = 0; i < arr.length; ) {
            if (arr[i] == 1) {
                if (hasZeroValueInLeft(arr, i)) {
                    arr[i - 1] = 1;
                    frequencyMap.put(0, frequencyMap.get(0) - 1);
                }
                if (hasZeroValueInRight(arr, i)) {
                    arr[i + 1] = 1;
                    frequencyMap.put(0, frequencyMap.get(0) - 1);
                    i += 2;
                } else {
                    i++;
                }
            } else {
                i++;
            }
        }
    }

    private static boolean hasZeroValueInRight(int[] arr, int index) {
        if (index < arr.length - 1 && arr[index + 1] == 0) {
            return true;
        }
        return false;
    }

    private static boolean hasZeroValueInLeft(int[] arr, int index) {
        if (index > 0 && arr[index - 1] == 0) {
            return true;
        }
        return false;
    }
}
