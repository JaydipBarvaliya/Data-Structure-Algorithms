package com.leetcode.year_2020.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neeraj on 29/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FindAllNumbersDisappearedInAnArray {

    public static void main(String[] args) {
        System.out.println(findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int indexToBeReplaced = Math.abs(nums[i]) - 1;
            if (nums[indexToBeReplaced] > 0) { // No One has modified it's value till now
                nums[indexToBeReplaced] = 0 - nums[indexToBeReplaced];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }
}
