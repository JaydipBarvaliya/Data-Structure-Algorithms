package com.interviewbit.two_pointers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Remove duplicates from Sorted Array
 * Given a sorted array, remove the duplicates in place such that each element appears only once and return the new length.
 * <p>
 * Note that even though we want you to return the new length, make sure to change the original array as well in place
 * <p>
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * <p>
 * Example:
 * Given input array A = [1,1,2],
 * Your function should return length = 2, and A is now [1,2].
 *
 * @author neeraj on 2019-08-06
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("DuplicatedCode")
public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(1, 1, 3));
        System.out.println(removeDuplicates(a));

        a = new ArrayList<>(Arrays.asList(1, 1, 3));
        System.out.println(removeDuplicatesAndLeaveDuplicateAtMostTwice(a));

        a = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 5, 5, 5, 6));
        System.out.println(removeDuplicates(a));

        a = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 5, 5, 5, 6));
        System.out.println(removeDuplicatesAndLeaveDuplicateAtMostTwice(a));

        a = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3));
        System.out.println(removeDuplicatesAndLeaveDuplicateAtMostTwice(a));

    }

    public static int removeDuplicates(ArrayList<Integer> a) {
        // Base Case
        if (a.size() <= 1) {
            return 1;
        }
        int lastPositionPointer = 1;
        int currentVal = a.get(0);
        int pointer = 1;

        while (pointer < a.size()) {
            if (a.get(pointer) == currentVal) {
                pointer++;
            } else {
                a.set(lastPositionPointer++, a.get(pointer));
                currentVal = a.get(pointer);
                pointer++;
            }
        }

        // Let's delete the left element cozz they were duplicates
        a.subList(lastPositionPointer, a.size()).clear();

        System.out.println("List after removing duplicates -=>" + a);
        return lastPositionPointer;
    }

    public static int removeDuplicatesAndLeaveDuplicateAtMostTwice(ArrayList<Integer> a) {
        // Base Case
        if (a.size() <= 1) {
            return 1;
        }
        int lastPositionPointer = -1;
        int currentVal = a.get(0);
        int pointer = 1;
        int countOfDuplicate = 1;
        while (pointer < a.size()) {
            if (a.get(pointer) == currentVal) {
                countOfDuplicate++;
                pointer++;
            } else { // currentVal is not similar to the previousOne
                // Find which position to replace, if there are more than 2 duplicates
                currentVal = a.get(pointer);
                if (countOfDuplicate > 2) {
                    int positionToReplace = pointer - countOfDuplicate + 2;
                    a.subList(positionToReplace, pointer).clear();
                    pointer = positionToReplace + 1;
                } else {
                    pointer++;
                }
                countOfDuplicate = 1;
            }
        }
        if (countOfDuplicate > 2) {
            a.subList(a.indexOf(currentVal) + 2, a.size()).clear();
        }
        System.out.println("List after removing duplicates and keeping at-most twice -=>" + a);
        return a.size();
    }
}
