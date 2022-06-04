package com.datastructures.recursion;

import com.util.LogUtil;

import java.util.Arrays;

/**
 * https://www.youtube.com/watch?v=AZ4jEY_JAVc
 *
 * @author neeraj on 25/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SortAnArray {

    public static void main(String[] args) {
        LogUtil.printArray(sort(new int[]{5, 4, 3, 2, 1}));
        LogUtil.printArray(sort(new int[]{2, 3, 7, 6, 4, 5}));
        LogUtil.printArray(sort(new int[]{100, 40, 30, 55, 55, 70, 30}));
        LogUtil.printArray(sort(new int[]{32, 65, 2, 80, 1}));
    }

    public static int[] sort(int[] arr) {
        if (arr.length == 1) {
            return arr;
        }

        int[] sortedArr = sort(Arrays.copyOfRange(arr, 0, arr.length - 1));
        int lastItem = arr[arr.length - 1];

        // Now we have to put the last item into the correct position of sorted array.
        // we will do this as well with recursion.
        return placeLastItemAtItsSortedPosition(sortedArr, lastItem);
    }

    public static void sort() {
        int[] arr = new int[]{1, 4, 3, 5, 2};
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        for (int i : arr) {
            System.out.print(i + ",");
        }
    }

    private static int[] placeLastItemAtItsSortedPosition(int[] sortedArr, int itemToBePlaced) {
        int[] arrayAfterPlacement = new int[sortedArr.length + 1];
        // base condition
        if (sortedArr.length == 0) {
            return new int[]{itemToBePlaced};
        }
        int lastItemInSortedArray = sortedArr[sortedArr.length - 1];
        if (lastItemInSortedArray < itemToBePlaced) { // if the array is already sorted and lastElement < itemToBePlaced, them simply push.
            int i = 0;
            for (; i < sortedArr.length; i++) {
                arrayAfterPlacement[i] = sortedArr[i];
            }
            arrayAfterPlacement[i] = itemToBePlaced;
            return arrayAfterPlacement;
        }


        // Hypothesis
        int[] partialPlacement = placeLastItemAtItsSortedPosition(Arrays.copyOfRange(sortedArr, 0,
                sortedArr.length - 1),
                itemToBePlaced);

        // Induction
        int i = 0;
        for (; i < partialPlacement.length; i++) {
            arrayAfterPlacement[i] = partialPlacement[i];
        }
//        arrayAfterPlacement[i] = itemToBePlaced;
        arrayAfterPlacement[arrayAfterPlacement.length - 1] = lastItemInSortedArray;

        return arrayAfterPlacement;
    }
}
