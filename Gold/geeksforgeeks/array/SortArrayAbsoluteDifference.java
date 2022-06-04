package com.geeksforgeeks.array;

import java.util.*;

import static com.util.LogUtil.getArrayAsString;
import static com.util.LogUtil.logIt;

public class SortArrayAbsoluteDifference {

    public static void main(String[] args) {
//        int arr[] = {10, 5, 3, 9, 2};
        int arr[] = {1, 2, 3, 4, 5};
        sortArrayAsAbsoluteDifference(arr, 6);
        ArrayRotation.printArray(arr);

        arr = new int[]{10, 5, 3, 9, 2};
        sortArrayAsAbsoulteDifferenceWithNoExtraSpace(arr, 7);
    }

    private static void sortArrayAsAbsoluteDifference(int[] arr, int x) {
        Map<Integer, List<Integer>> differenceMap = new TreeMap<>();

        for (int i = 0; i < arr.length; i++) {
            int difference = Math.abs(x - arr[i]);
            if (differenceMap.containsKey(difference)) {
                List<Integer> members = differenceMap.get(difference);
                members.add(arr[i]);
                differenceMap.put(difference, members);
            } else {
                List<Integer> newMembers = new ArrayList<>();
                newMembers.add(arr[i]);
                differenceMap.put(difference, newMembers);
            }
        }
        System.out.println(differenceMap);
        Set<Integer> keys = differenceMap.keySet();
        int counter = 0;
        for (Integer key : keys) {
            List<Integer> members = differenceMap.get(key);
            for (Integer member : members) {
                arr[counter++] = member;
            }
        }
    }

    private static void sortArrayAsAbsoulteDifferenceWithNoExtraSpace(int[] arr, int x) {
        logIt("Sorting Array " + getArrayAsString(arr) + " based on Absolute difference with " + x);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (Math.abs(arr[j] - x) > Math.abs(arr[j + 1] - x)) {
                    QuickSort.swap(arr, j, j + 1);
                }
            }
        }
        logIt("After Sorting Array is " + getArrayAsString(arr));
    }
}
