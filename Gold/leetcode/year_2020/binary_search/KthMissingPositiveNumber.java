package com.leetcode.year_2020.binary_search;

import java.util.HashSet;
import java.util.Set;

public class KthMissingPositiveNumber {

    public static void main(String[] args) {
        System.out.println(findKthPositive(new int[]{2, 3, 4, 7, 11}, 5));
        System.out.println(findKthPositive(new int[]{1, 2, 3, 4}, 2));
        System.out.println(findKthPositive(new int[]{1, 2}, 1));

        System.out.println(findKthPositiveViaBinarySearch(new int[]{2, 3, 4, 7, 11}, 5));
        System.out.println(findKthPositiveViaBinarySearch(new int[]{1, 2, 3, 4}, 2));
        System.out.println(findKthPositiveViaBinarySearch(new int[]{1, 2}, 1));
    }

    public static int findKthPositive(int[] arr, int k) {
        final Set<Integer> set = new HashSet();
        for (int i : arr) set.add(i);
        int runUpto = arr.length + k;
        for (int i = 1; i <= runUpto; i++) {
            if (!set.contains(i)) {
                k--;
            }
            if (k == 0) return i;
        }
        return 0;
    }

    public static int findKthPositiveViaBinarySearch(int[] arr, int k) {
        /**
         * A =======> 2, 3, 4, 7, 11
         *
         * Now inorder to find out how many elements are missing, just calculate (arr[i] - i) why -1? 0 based index.
         *
         * Diff ===> (2-1), (3-2), (4-3), (7-4), (11-5) [ Remember subtracting "i" fromm the loop of 1 to N here]
         *            1      1       1      3      6 =======> What does it represent at every index, how many items are missing till now
         *           ||
         *           ||
         *          When only 2 was present we had '1' as missing item, it's same for when (3) and (4) arrived, but as soon as
         * *                               (7) came we can notice the missing array shows [3], which is the count of [1, 5, 6] missing.
         *                                  and now (11) we got count [6], which is [1, 5, 6, 8, 9, 10]
         *
         * Now in this array we need to find a value >= k, so we can simply do a binary search. Once we have the item,
         * So lets say for sake of this example we wanted to find 5th missing item. what value you think should cover this 5th item
         * last (6), right and [6] from diffArray is between whom [7] and [11] from primary array, and we know till 7 we had 3 items missing
         * so we just need to run loop from 7 upto (k-3) which is (5-3) so 2 loops away from 7 is our answer.
         */

        // Now let me tell you a little secret
        /**
         * You don't need to make the diff array, we can use original array directly while doing BinarySearch :)
         * , hence leaving time complexity O(logN) and space O(1)
         */
        int beg = 0, end = arr.length;
        while (beg < end) {
            int mid = (beg + end) / 2;
            if (arr[mid] - mid - 1 < k) {
                beg = mid + 1;
            } else {
                end = mid;
            }
        }
        return end + k;
    }
}
