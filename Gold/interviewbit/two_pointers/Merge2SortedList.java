package com.interviewbit.two_pointers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 * <p>
 * Note: You have to modify the array A to contain the merge of A and B. Do not output anything in your code.
 * <p>
 * Example :
 * <p>
 * Input :
 * A : [1 5 8]
 * B : [6 9]
 * <p>
 * Modified A : [1 5 6 8 9]
 *
 * @author neeraj on 2019-08-04
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class Merge2SortedList {

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));
        ArrayList<Integer> b = new ArrayList<>(Arrays.asList(2, 4, 6, 9, 10));
        merge(a, b);
        a.listIterator();
        System.out.println(a);
    }

    public static void merge(ArrayList<Integer> a, ArrayList<Integer> b) {
        int t1 = 0;
        int t2 = 0;
        int N1 = a.size();
        int N2 = b.size();
        int tempArrCounter = 0;

        while (t1 < N1 && t2 < N2) {
            if (a.get(t1) > b.get(t2)) {
                a.add(t1, b.get(t2));
                t1++;
                t2++;
            } else {
                t1++;
            }
        }
        while (t2 < N2) {
            a.add(b.get(t2++));
        }
    }
}
