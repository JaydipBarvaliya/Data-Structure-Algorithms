package com.leetcode.year_2020.Greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/pancake-sorting/
 * 969. Pancake Sorting
 */
public class PancakeSorting {

    public static void main(String[] args) {
        System.out.println(pancakeSort(new int[]{3, 2, 4, 1}));
    }

    public static List<Integer> pancakeSort(int[] arr) {
        /**
         **  Intution : Every time we want to put the biggest element to it's correct position
         **  A) Find Max Element in the Array.... Reverse all elements
         **                                      from 0 -----to----MaxElement Index
         **  B) Now Max Element is in the Start....Reverse the entire array
         **                  This way item will reach to it's correct position
         **  Now after A and B the biggest element is already at it's correct position
         ** so we will perform the same step for n-1 elements and so on.
         **
         */
        final List<Integer> result = new ArrayList<>();
        for (int i = arr.length - 1; i > 0; i--) {
            // At every step we are making sure the remaining max element is at the end
            int maxItemIndex = findMaxItemIndex(arr, i);

            // Reverse from 0 to that item index
            reverseArray(arr, 0, maxItemIndex);

            // Reverse the entire array upto i
            reverseArray(arr, 0, i);

            result.add(maxItemIndex + 1); // Why +1 for it being 1-indexed and array being 0-indexed.
            result.add(i + 1); // next +1 is for reversing the entire array upto i
        }
        return result;
    }

    private static void reverseArray(final int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    private static int findMaxItemIndex(final int[] arr, final int limit) {
        int MAX_INDEX = 0;
        for (int i = 0; i <= limit; i++) {
            MAX_INDEX = arr[MAX_INDEX] < arr[i] ? i : MAX_INDEX;
        }
        return MAX_INDEX;
    }
}
