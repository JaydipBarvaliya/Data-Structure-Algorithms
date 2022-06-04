package com.company.google;

import com.util.LogUtil;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * https://leetcode.com/discuss/interview-question/1371222/Google-Questions
 * <p>
 * <p>
 * sararomero's avatar
 * sararomero
 * 4
 * July 30, 2021 6:20 AM
 * <p>
 * 708 VIEWS
 * <p>
 * You are given an array A representing heights of students. All the students are asked to stand in rows.
 * The students arrive by one, sequentially (as their heights appear in A). For the i-th student,
 * if there is a row in which all the students are taller than A[i], the student will stand in one of such rows.
 * If there is no such row, the student will create a new row. Your task is to find the minimum number of rows created.
 * <p>
 * Write a function that, given a non-empty array A containing N integers, denoting the heights of the students,
 * returns the minimum number of rows created.
 * <p>
 * For example, given A = [5, 4, 3, 6, 1], the function should return 2.
 * <p>
 * Students will arrive in sequential order from A[0] to A[N−1]. So, the first student will have height = 5,
 * the second student will have height = 4, and so on.
 * <p>
 * For the first student, there is no row, so the student will create a new row.
 * <p>
 * Row1 = [5]
 * <p>
 * For the second student, all the students in Row1 have height greater than 4. So, the student will stand in Row1.
 * <p>
 * Row1 = [5, 4]
 * <p>
 * Similarly, for the third student, all the students in Row1 have height greater than 3. So, the student will stand in Row1.
 * <p>
 * Row1 = [5, 4, 3]
 * <p>
 * For the fourth student, there is no row in which all the students have height greater than 6. So, the student will create a new row.
 * <p>
 * Row1 = [5, 4, 3]
 * <p>
 * Row2 = [6]
 * <p>
 * For the fifth student, all the students in Row1 and Row2 have height greater than 1. So, the student can stand in either of the two rows.
 * <p>
 * Row1 = [5, 4, 3, 1]
 * <p>
 * Row2 = [6]
 * <p>
 * Since two rows are created, the function should return 2.
 * <p>
 * Assume that:
 * <p>
 * N is an integer within the range [1..1,000]
 * <p>
 * each element of array A is an integer within the range [1..10,000]
 */
public class FindMinimumRowInAssembly {

    public static void main(String[] args) {
        LogUtil.logIt("Using O (N * N)", true);
        System.out.println(findMinimumRow_O_N_Square(new int[]{5, 6, 7, 8, 9}));
        System.out.println(findMinimumRow_O_N_Square(new int[]{5, 4, 3, 6, 1}));
        System.out.println(findMinimumRow_O_N_Square(new int[]{5, 4, 3, 6, 8, 7, 1, 2, 1, 2, 1, 2}));
        System.out.println(getMinimumRowsFormed(new int[]{7, 7, 7, 3, 3, 5}));

        LogUtil.logIt("Using O (N LOG N)", true);
        System.out.println(getMinimumRowsFormed(new int[]{5, 6, 7, 8, 9}));
        System.out.println(getMinimumRowsFormed(new int[]{5, 4, 3, 6, 1}));
        System.out.println(getMinimumRowsFormed(new int[]{5, 4, 3, 6, 8, 7, 1, 2, 1, 2, 1, 2}));
        System.out.println(getMinimumRowsFormed(new int[]{7, 7, 7, 3, 3, 5}));
    }

    public static int getMinimumRowsFormed(int[] students) {
        TreeMap<Integer, Integer> heightFreq = new TreeMap<>();
        for (int height : students) {
            heightFreq.put(height, heightFreq.getOrDefault(height, 0) + 1);

            Integer studentWithGreaterHeight = heightFreq.higherKey(height);
            if (studentWithGreaterHeight != null) {
                // Since this smaller height will be placed in the row so this taller guy should be kicked out
                if (heightFreq.get(studentWithGreaterHeight) == 1) {
                    heightFreq.remove(studentWithGreaterHeight);
                } else {
                    heightFreq.put(studentWithGreaterHeight, heightFreq.get(studentWithGreaterHeight) - 1);
                }
            }
        }

        // Sum all values
        return heightFreq.values().stream().reduce(0, Integer::sum);
    }


    public static int findMinimumRow_O_N_Square(int[] heights) {
        /**
         * O(N^2) solution
         *
         * We know that maximum we will be needing length(height) rows in case all the elements are in increasing heights
         * A = [5, 6, 7, 8, 9]
         *
         * For other case we can check like iterating through rows for every incoming student
         * A = [5, 4, 3, 6, 1],
         */
        int[] rows = new int[heights.length];
        Arrays.fill(rows, Integer.MAX_VALUE);
        for (int h : heights) {
            for (int i = 0; i < rows.length; i++) {
                if (rows[i] > h) {
                    rows[i] = h;
                    break;
                }
            }
        }
        int minRowCount = 0;
        for (int i : rows) {
            if (i == Integer.MAX_VALUE) break;
            minRowCount++;
        }
        return minRowCount;
    }
}
