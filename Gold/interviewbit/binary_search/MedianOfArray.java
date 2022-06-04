package com.interviewbit.binary_search;

import com.util.LogUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @author neeraj on 2019-07-30
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MedianOfArray {

    public static double findMedian(List<Integer> listA, List<Integer> listB) {
        LogUtil.logIt("Finding Median of " + listA + " and \n " + listB);
        double median = median(listA, listB, 0, listA.size() - 1, 0, listB.size() - 1);
        System.out.println(median);
        return median;
    }

    public static void main(String[] args) {
//        findMedian(Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(10, 20, 30, 40, 50));
//        findMedian(Arrays.asList(1, 4, 5), Arrays.asList(2, 3));
//        findMedian(Arrays.asList(), Arrays.asList(20));
        findMedian(Arrays.asList(-50, -41, -40, -19, 5, 21, 28), Arrays.asList(-50, -21, -10));
    }

    public static double median(List<Integer> listA, List<Integer> listB, int start_a, int end_a, int start_b, int end_b) {

        if (listA.isEmpty()) {
            return listB.get(listB.size() / 2);
        } else if (listB.isEmpty()) {
            return listA.get(listA.size() / 2);
        }

        // If there are 2 members in both first and second list.
        if (end_a - start_a == 1 && end_b - start_b == 1) {
            return (Math.max(listA.get(start_a), listA.get(end_a)) + Math.min(listB.get(start_b), listB.get(end_b))) / 2;
        } else {
            int mid1 = start_a + (end_a - start_a) / 2;
            int mid2 = start_b + (end_b - start_b) / 2;
            int medianA = listA.get(mid1);
            int medianB = listB.get(mid2);

            if (medianA == medianB) {
                return medianA;
            }

            if (medianA < medianB) {
                return median(listA, listB, mid1, end_a, start_b, mid2);
            } else {
                return median(listA, listB, mid1, end_a, mid2, end_b);
            }
        }
    }
}
