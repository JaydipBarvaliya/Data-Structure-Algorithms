package com.interviewbit.two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.interviewbit.com/problems/intersection-of-sorted-arrays/
 * <p>
 * Find the intersection of two sorted arrays.
 * OR in other words,
 * Given 2 sorted arrays, find all the elements which occur in both the arrays.
 * <p>
 * Example :
 * <p>
 * Input :
 * A : [1 2 3 3 4 5 6]
 * B : [3 3 5]
 * <p>
 * Output : [3 3 5]
 * <p>
 * Input :
 * A : [1 2 3 3 4 5 6]
 * B : [3 5]
 * <p>
 * Output : [3 5]
 *
 * @author neeraj on 2019-08-04
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class Intersection_of_SortedArrays {

    public static void main(String[] args) {
        System.out.println(intersect(Arrays.asList(1, 2, 3, 3, 4, 5, 6), Arrays.asList(3, 3, 5)));
        System.out.println(intersect(Arrays.asList(1, 2, 3, 3, 4, 5, 6), Arrays.asList(3, 5)));
    }

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public static ArrayList<Integer> intersect(final List<Integer> A, final List<Integer> B) {
        ArrayList<Integer> result = new ArrayList<>();
        if (A == null || A.isEmpty() || B == null || B.isEmpty()) {
            return result;
        }
        if (A.size() < B.size()) { // A list should always be bigger
            return intersect(B, A);
        }
        int t1 = 0;
        int t2 = 0;


        while (t1 < A.size() && t2 < B.size()) {
            if (A.get(t1) < B.get(t2)) {
                t1++;
            } else if (A.get(t1) > B.get(t2)) {
                t2++;
            } else {
                result.add(A.get(t1));
                t1++;
                t2++;
            }
        }
        return result;
    }
}
