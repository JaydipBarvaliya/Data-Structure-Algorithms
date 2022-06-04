package com.interviewbit.binary_search;

import com.util.LogUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.interviewbit.com/problems/matrix-search/
 * <p>
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * <p>
 * This matrix has the following properties:
 * <p>
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than or equal to the last integer of the previous row.
 * Example:
 * <p>
 * Consider the following matrix:
 * <p>
 * [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * Given target = 3, return 1 ( 1 corresponds to true )
 * <p>
 * Return 0 / 1 ( 0 if the element is not present, 1 if the element is present ) for this problem
 *
 * @author neeraj on 2019-07-29
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MatrixSearch {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        ArrayList<Integer> row1 = new ArrayList<>(Arrays.asList(1, 3, 5, 7));
        ArrayList<Integer> row2 = new ArrayList<>(Arrays.asList(10, 11, 16, 20));
        ArrayList<Integer> row3 = new ArrayList<>(Arrays.asList(23, 30, 34, 50));

        a.add(row1);
        a.add(row2);
        a.add(row3);

//        System.out.println(searchMatrix(a, 1));
//        System.out.println(searchMatrix(a, 3));
//        System.out.println(searchMatrix(a, 5));
//        System.out.println(searchMatrix(a, 7));
//        System.out.println(searchMatrix(a, 10));
//        System.out.println(searchMatrix(a, 11));
//        System.out.println(searchMatrix(a, 16));
//        System.out.println(searchMatrix(a, 20));
//        System.out.println(searchMatrix(a, 23));
//        System.out.println(searchMatrix(a, 30));
//        System.out.println(searchMatrix(a, 34));
//        System.out.println(searchMatrix(a, 50));
//        System.out.println(searchMatrix(a, 51));


        ArrayList<ArrayList<Integer>> b = new ArrayList<>();
        ArrayList<Integer> rowB1 = new ArrayList<>(Arrays.asList(1,   4,  7, 11, 15));
        ArrayList<Integer> rowB2 = new ArrayList<>(Arrays.asList(2,   5,  8, 12, 19));
        ArrayList<Integer> rowB3 = new ArrayList<>(Arrays.asList(3,   6,  9, 16, 22));
        ArrayList<Integer> rowB4 = new ArrayList<>(Arrays.asList(10, 13, 14, 17, 24));
        ArrayList<Integer> rowB5 = new ArrayList<>(Arrays.asList(18, 21, 23, 26, 30));

        b.add(rowB1);
        b.add(rowB2);
        b.add(rowB3);
        b.add(rowB4);
        b.add(rowB5);

        System.out.println(searchMatrix(b, 5));
    }

    public static int searchMatrix(ArrayList<ArrayList<Integer>> a, int b) {
        /**
         * A) First identify the row in which value might be present
         *      a) this we can do in O(Log(N)) time by performing binary search based on the first index of each row
         * B) Once we have the row, it's again O(Log(N)) time to go through that row and find out the item
         */
        int possibleRow = findPossibleRowContainingTheValue(a, b, 0, a.size()-1);
        if (possibleRow != -1) {
            LogUtil.logIt("Possible Row is "+ possibleRow);
            return binarySearch(a.get(possibleRow), 0, a.get(possibleRow).size()-1, b);
        } else {
            return 0;
        }
    }

    public static int binarySearch(ArrayList<Integer> arr, int low, int high, int value) {
        if (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr.get(mid) == value) {
                return 1;
            } else if (arr.get(mid) > value) {
                return binarySearch(arr, low, mid - 1, value);
            } else {
                return binarySearch(arr, mid + 1, high, value);
            }
        } else {
            return 0;
        }
    }

    public static int findPossibleRowContainingTheValue(ArrayList<ArrayList<Integer>> arr, int valueWeAreSearching, int low, int high) {
        if (low <= high) {
            if (low == high) {
                return low;
            }
            int mid = low + (high - low) / 2;
            List<Integer> middleRow = arr.get(mid);
            if (middleRow.get(0) <= valueWeAreSearching) {
                if (middleRow.get(middleRow.size() - 1) >= valueWeAreSearching) {
                    return mid;
                } else {
                    return findPossibleRowContainingTheValue(arr, valueWeAreSearching, mid + 1, high);
                }
            } else if (middleRow.get(0) > valueWeAreSearching) {
                return findPossibleRowContainingTheValue(arr, valueWeAreSearching, low, mid - 1);
            }
        }
        return -1;
    }
}
