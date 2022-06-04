package com.leetcode.year_2020.array;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/majority-element-ii/
 *
 * @author neeraj on 30/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MajorityElement2 {

    public static void main(String[] args) {
        majorityElement(new int[]{2, 2, 4, 4, 3, 5, 3, 4, 4, 6, 4, 3, 3, 8}, 4);
        majorityElement(new int[]{1, 1, 1, 3, 3, 2, 2, 2}, 3);
        majorityElement(new int[]{3, 2, 3}, 3);
        majorityElement(new int[]{0, -1, 2, -1}, 3);
    }

    static class Pair {
        Integer data;
        int count;

        public Pair(Integer data, int count) {
            this.data = data;
            this.count = count;
        }
    }

    public static List<Integer> majorityElement(int[] nums, int k) {
        /**
         * This problem we can solve using a generic n/k approach
         * in this problem we have k=3.
         *
         * One important point to note here is that you can never have more than k-1 majority element.
         */
        Pair[] pairs = new Pair[k - 1];
        for (int i = 0; i < pairs.length; i++) {
            pairs[i] = new Pair(null, 0); // Initial state.
        }
        for (int i = 0; i < nums.length; i++) {
            int indexOfElement = findElement(pairs, nums[i]);
            if (indexOfElement == -1) {
                addElementToPairs(pairs, nums[i]);
            } else {
                pairs[indexOfElement].count++;
            }
        }

        // Now we just have to check that the items in pair are actually majority element
        int countForMajority = nums.length / k;
        List<Integer> result = new ArrayList<>();
        for (Pair pair : pairs) {
            Integer item = pair.data;
            if (item != null) {
                int count = 0;
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] == item) count++;
                }
                if (count > countForMajority) {
                    result.add(item);
                }
            }
        }
        System.out.println(result);
        return result;
    }

    private static void addElementToPairs(Pair[] pairs, int num) {
        for (int i = 0; i < pairs.length; i++) {
            if (pairs[i].count == 0) {
                pairs[i].data = num;
                pairs[i].count = 1;
                return;
            }
        }

        // If we reached here that means the array is full and there is no-one with frequency 0
        // so reduce everyone's frequency by 1.
        for (int i = 0; i < pairs.length; i++) {
            pairs[i].count--;
        }
    }

    public static int findElement(Pair[] pairs, int element) {
        for (int i = 0; i < pairs.length; i++) {
            if (pairs[i].data != null && pairs[i].data == element) {
                return i;
            }
        }
        return -1;
    }
}
