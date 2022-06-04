package com.interviewbit.two_pointers;

import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Collections.swap;

/**
 * https://www.interviewbit.com/problems/remove-element-from-array/
 * <p>
 * Remove Element
 * <p>
 * Given an array and a value, remove all the instances of that value in the array.
 * Also return the number of elements left in the array after the operation.
 * It does not matter what is left beyond the expected length.
 * <p>
 * Example:
 * If array A is [4, 1, 1, 2, 1, 3]
 * and value elem is 1,
 * then new length is 3, and A is now [4, 2, 3]
 * Try to do it in less than linear additional space complexity.
 *
 * @author neeraj on 2019-08-06
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RemoveElementFromArray {

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(1, 1, 3));
        System.out.println(removeElement(a, 1));

        a = new ArrayList<>(Arrays.asList(4, 1, 1, 2, 1, 3));
        System.out.println(removeElement(a, 1));

        a = new ArrayList<>(Arrays.asList(2, 0, 1, 2, 0, 3, 2, 2, 2, 1, 0, 0, 0, 1, 0, 0, 2, 2, 2, 3, 2, 3, 1, 2, 1, 2, 2, 3, 2, 3, 0, 3, 0, 2, 1, 2, 0, 0, 3, 2, 3, 0, 3, 0, 2, 3, 2, 2, 3, 1, 3, 3, 0, 3, 3, 0, 3, 3, 2, 0, 0, 0, 0, 1, 3, 0, 3, 1, 3, 1, 0, 2, 3, 3, 3, 2, 3, 3, 2, 2, 3, 3, 3, 1, 3, 2, 1, 0, 0, 0, 1, 0, 3, 2, 1, 0, 2, 3, 0, 2, 1, 1, 3, 2, 0, 1, 1, 3, 3, 0, 1, 2, 1, 2, 2, 3, 1, 1, 3, 0, 2, 2, 2, 2, 1, 0, 2, 2, 2, 1, 3, 1, 3, 1, 1, 0, 2, 2, 0, 2, 3, 0, 1, 2, 1, 1, 3, 0, 2, 3, 2, 3, 2, 0, 2, 2, 3, 2, 2, 0, 2, 1, 3, 0, 2, 0, 2, 1, 3, 1, 1, 0, 0, 3, 0, 1, 2, 2, 1, 2, 0, 1, 0, 0, 0, 1, 1, 0, 3, 2, 3, 0, 1, 3, 0, 0, 1, 0, 1, 0, 0, 0, 0, 3, 2, 2, 0, 0, 1, 2, 0, 3, 0, 3, 3, 3, 0, 3, 3, 1, 0, 1, 2, 1, 0, 0, 2, 3, 1, 1, 3, 2));
        System.out.println(removeElement(a, 2));
    }

    public static int removeElement(ArrayList<Integer> a, int b) {

        // We can use Dutch National flag and can segregate all elements to the left of b
        int L = 0;
        int R = 0;

        while (R < a.size()) {
            if (a.get(R) != b) {
                swap(a, L++, R++);
            } else {
                R++;
            }
        }
        a.subList(L, a.size()).clear();
        System.out.println("After Removing " + a);
        return a.size();


    }
}
