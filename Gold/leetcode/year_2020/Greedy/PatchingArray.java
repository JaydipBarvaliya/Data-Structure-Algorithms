package com.leetcode.year_2020.Greedy;

import com.leetcode.year_2020.DP.zero_one_knapsack.MaximumNumberOfConsecutiveValuesYouCanMake;

public class PatchingArray {

    public static void main(String[] args) {
        System.out.println(minPatches(new int[]{1, 5, 10}, 20));
    }

    public static int minPatches(int[] nums, int n) {
        /**
         * nums = [1,5,10], n = 20
         *
         * Result : You  need 2 additions to reach to n = 20; patches will be 2 and 4
         *
         * So basically we need to check how many additions should be done to get all elements
         * upto n (if we add up some number in nums), Given that array is sorted
         *
         * https://www.youtube.com/watch?v=N-tcCOCNSZY
         *
         * This is somewhat similar to {@link MaximumNumberOfConsecutiveValuesYouCanMake}
         *
         * okay so we basically keep 3 things
         *
         *     ELEMENTS        |     REACH      | NEW ADDITION
         *                     |                |
         *                     |                |
         *                     |                |
         *                     |                |
         *
         * Until our reach is < n we keep adding the current item, and if the currentItem > reach
         * then we should add smallest missing items
         */
        int patches = 0; // our result
        long reach = 0; // initially we can't reach anywhere
        int index = 0;

        while (reach < n) {
            if (index >= nums.length) {
                // Our array is finished but we didn't reach to n, so we will add all items from reach+1 untill we reach to n
                reach += reach + 1;
                patches++;
                continue;
            }
            if (nums[index] <= reach + 1) {
                reach += nums[index];
                index++; // Only increment when you have used the current Item
            } else {
                // Add the missing element
                reach += reach + 1;
                patches++;

                // Not incrementing index since we didn't use the item
            }
        }
        return patches;
    }
}
