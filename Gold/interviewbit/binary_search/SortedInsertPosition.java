package com.interviewbit.binary_search;

import com.util.LogUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * https://www.interviewbit.com/problems/sorted-insert-position/
 * <p>
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * <p>
 * You may assume no duplicates in the array.
 * <p>
 * Here are few examples.
 * <p>
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 *
 * @author neeraj on 2019-07-30
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SortedInsertPosition {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 5, 6));


        Arrays.asList(5, 2, 7, 0).forEach(item ->
                LogUtil.logIt(list + " , " + item + " --> " + searchInsert(list, item)));

        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(3, 4, 18, 19, 20, 27, 28, 31, 36, 42, 44, 71, 72, 75, 82, 86, 88, 97, 100, 103, 105, 107, 110, 116, 118, 119, 121, 122, 140, 141, 142, 155, 157, 166, 176, 184, 190, 199, 201, 210, 212, 220, 225, 234, 235, 236, 238, 244, 259, 265, 266, 280, 283, 285, 293, 299, 309, 312, 317, 335, 341, 352, 354, 360, 365, 368, 370, 379, 386, 391, 400, 405, 410, 414, 416, 428, 433, 437, 438, 445, 453, 457, 458, 472, 476, 480, 485, 489, 491, 493, 501, 502, 505, 510, 511, 520, 526, 535, 557, 574, 593, 595, 604, 605, 612, 629, 632, 633, 634, 642, 647, 653, 654, 656, 658, 686, 689, 690, 691, 709, 716, 717, 737, 738, 746, 759, 765, 775, 778, 783, 786, 787, 791, 797, 801, 806, 815, 820, 822, 823, 832, 839, 841, 847, 859, 873, 877, 880, 886, 904, 909, 911, 917, 919, 937, 946, 948, 951, 961, 971, 979, 980, 986, 993));
        System.out.println(searchInsert(list2, 904));

        System.out.println(pow(71045970, 99046373, 57263970));

//        System.out.println(pow(3, 8, 20));
//        System.out.println(pow(2, 100, 20));
    }

    public static int pow(int x, int n, int d) {
        int tempPower = n;
        if (tempPower % 2 != 0) { // if power is odd, let's subtract one and make it even
            tempPower = tempPower - 1;
        }

        BigDecimal powerResult = BigDecimal.ONE;
        for (int i = 0; i < tempPower / 2; i++) {
            powerResult = powerResult.multiply(new BigDecimal(x).pow(2));
        }
        if (n % 2 != 0) {
            powerResult = powerResult.multiply(new BigDecimal(x));
        }

        if (powerResult.compareTo(BigDecimal.ZERO) == -1) {
            powerResult = powerResult.add(new BigDecimal(d));
        }

        return powerResult.remainder(new BigDecimal(d)).intValue();
    }

    public static int searchInsert(ArrayList<Integer> a, int b) {
        positionToInsert = 0;
        return binarySearch(a, 0, a.size() - 1, b);
    }

    static int positionToInsert = 0;

    public static int binarySearch(ArrayList<Integer> a, int low, int high, int b) {
        if (low <= high) {
            int mid = low + (high - low) / 2;

            if (a.get(mid) == b) {
                return mid;

            } // IF THE ITEM WE ARE SEARCHING IS > MID, THEN DEFINITELY, ITEM WILL BE PLACED AFTER MID,
            // BECAUSE TILL MID EVERYTHING IS WORKING FINE
            else if (a.get(mid) < b) {
                positionToInsert = mid + 1;
                return binarySearch(a, mid + 1, high, b);
            } // // IF THE ITEM WE ARE SEARCHING IS < MID, THEN ITEM CAN BE PLACED ANYWHERE FROM 0 TILL MID, AND EVEN ON MID
            // BECAUSE MID HAS TO LEAVE IT'S PLACE SINCE SOME ITEM IS DEFINITELY GONNA COME AT LEFT SIDE OF MID.
            // HENCE WE ARE JUST KEEPING POSITION TO INSERT AS MID AND NOT MID-1;
            else if (a.get(mid) > b) {
                positionToInsert = mid;
                return binarySearch(a, low, mid - 1, b);
            }
        }
        return positionToInsert;
    }
}
