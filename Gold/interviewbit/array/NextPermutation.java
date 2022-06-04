package com.interviewbit.array;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://www.interviewbit.com/problems/next-permutation/
 * <p>
 * 1,2,3 → 1,3,2
 * <p>
 * 3,2,1 → 1,2,3
 * <p>
 * 1,1,5 → 1,5,1
 * <p>
 * 20, 50, 113 → 20, 113, 50
 *
 * @author neeraj on 2019-07-25
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NextPermutation {

    public static void main(String[] args) {
        NextPermutation nextPermutation = new NextPermutation();

        nextPermutation.calculateNextPermutation(Arrays.asList(1, 2, 3));
        nextPermutation.calculateNextPermutation(Arrays.asList(3, 2, 1));
        nextPermutation.calculateNextPermutation(Arrays.asList(1, 1, 5));
        nextPermutation.calculateNextPermutation(Arrays.asList(20, 50, 113));
        nextPermutation.calculateNextPermutation(Arrays.asList(1));
        nextPermutation.calculateNextPermutation(Arrays.asList(444, 994, 508, 72, 125, 299, 181, 238, 354, 223, 691, 249, 838, 890, 758, 675, 424, 199, 201, 788, 609, 582, 979, 259, 901, 371, 766, 759, 983, 728, 220, 16, 158, 822, 515, 488, 846, 321, 908, 469, 84, 460, 961, 285, 417, 142, 952, 626, 916, 247, 116, 975, 202, 734, 128, 312, 499, 274, 213, 208, 472, 265, 315, 335, 205, 784, 708, 681, 160, 448, 365, 165, 190, 693, 606, 226, 351, 241, 526, 311, 164, 98, 422, 363, 103, 747, 507, 669, 153, 856, 701, 319, 695, 52));

        nextPermutation.calculateNextPermutation(Arrays.asList(1));
        nextPermutation.calculateNextPermutation(Arrays.asList(1, 2, 6));
        nextPermutation.calculateNextPermutation(Arrays.asList(626, 436, 819, 100, 382, 173, 817, 581, 220, 95, 814, 660, 397, 852, 15, 532, 564, 715, 179, 872, 236, 790, 223, 379, 83, 924, 454, 846, 742, 730, 689, 112, 110, 516, 85, 149, 228, 202, 988, 212, 69, 602, 887, 445, 327, 527, 347, 906, 54, 460, 517, 376, 395, 494, 827, 448, 919, 799, 133, 879, 709, 184, 812, 514, 976, 700, 156, 568, 453, 267, 547, 8, 951, 326, 652, 772, 213, 714, 706, 972, 318, 768, 506, 59, 854, 422));

        nextPermutation.calculateNextPermutation(Arrays.asList(237, 542));


    }

    public void calculateNextPermutation(List<Integer> values) {
        LogUtil.logIt("Calculating Next Permutation of " + values);
        ArrayList<Integer> items = new ArrayList<>();
        values.forEach(item -> items.add(item));
        nextPermutation(items);
        System.out.println(items);
    }

    public void nextPermutation(ArrayList<Integer> a) {
        int[] input = a.stream().mapToInt(i -> i).toArray();
        int indexOfNumberJustGreaterThanTheNumberBeforeStrictlyDecreasingSequence = 0;


        // First find the increasing sequence
        int counter = input.length - 1;

        while (counter > 0) {
            if (input[counter] > input[counter - 1]) {
                counter--; // This we want to do because we want to be on the value which is just before to the strictly decreasing sequence.
                break;
            }
            counter--;
        }

        // This is the case
        // If such arrangement is not possible, it must be rearranged as the lowest possible order ie, sorted in an ascending order.
        if (counter < 0) {
            Collections.sort(a);
        } else {
            // Find number in strictly decreasing sequence that is just greater than the number at our counter.
            indexOfNumberJustGreaterThanTheNumberBeforeStrictlyDecreasingSequence = findIndexOfNextSmallestGreaterNumber(input, input[counter], counter);
            swap(input, counter, indexOfNumberJustGreaterThanTheNumberBeforeStrictlyDecreasingSequence);

            a.clear();

            for (int i = 0; i <= counter; i++) {
                a.add(input[i]);
            }

            // Now we will print from the last, since it's strictly decreasing sequence and we want the next permutation not the last permutation
            for (int i = input.length - 1; i > counter; i--) {
                a.add(input[i]);
            }
        }
    }

    private int findIndexOfNextSmallestGreaterNumber(int[] input, int numberForWhichIndexIsToBeSearched, int position) {
        int NEXT_SMALLEST_GREATER_NUMBER_INDEX = input.length - 1;
        for (int i = input.length - 1; i > position; i--) {

            // If the current index item is greater than the item for which we are doing search operation
            if (input[i] > numberForWhichIndexIsToBeSearched) {

                // If the currentIndex item is greater than but NEXT_SMALLEST_GREATER_NUMBER_INDEX was never set correctly before, since for the first time we randomly assigned last index as the NEXT_SMALLEST_GREATER_NUMBER
                if (input[NEXT_SMALLEST_GREATER_NUMBER_INDEX] < numberForWhichIndexIsToBeSearched) {
                    NEXT_SMALLEST_GREATER_NUMBER_INDEX = i;
                } else {
                    // If the currentIndex item is greater than {@link item} but smaller than the item at index NEXT_SMALLEST_GREATER_NUMBER_INDEX
                    if (input[NEXT_SMALLEST_GREATER_NUMBER_INDEX] > input[i]) {
                        NEXT_SMALLEST_GREATER_NUMBER_INDEX = i;
                    }
                }
            }
        }
        return NEXT_SMALLEST_GREATER_NUMBER_INDEX;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
