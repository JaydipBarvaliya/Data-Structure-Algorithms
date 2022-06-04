package com.leetcode.year_2020.all_premium.tags.google.sliding_window;

import com.util.LogUtil;

import java.util.TreeSet;

/**
 * https://www.lintcode.com/problem/861/
 */
public class KEmptySlots {

    public static void main(String[] args) {
        System.out.println(kEmptySlots(new int[]{1, 3, 2}, 1));
        System.out.println(kEmptySlots(new int[]{1, 2, 3}, 1));
        System.out.println(kEmptySlots(new int[]{1, 2, 3}, 1));
        System.out.println(kEmptySlots(new int[]{1, 3, 2, 6, 4, 5}, 3));
        System.out.println(kEmptySlots(new int[]{3, 9, 2, 8, 1, 6, 10, 5, 4, 7}, 1));

        LogUtil.logIt("Testing via optimized using treeSet", true);
        System.out.println(kEmptySlotsOptimized(new int[]{1, 3, 2}, 1));
        System.out.println(kEmptySlotsOptimized(new int[]{1, 2, 3}, 1));
        System.out.println(kEmptySlotsOptimized(new int[]{1, 2, 3}, 1));
        System.out.println(kEmptySlotsOptimized(new int[]{1, 6, 4, 3, 2, 5}, 3));
        System.out.println(kEmptySlotsOptimized(new int[]{3, 9, 2, 8, 1, 6, 10, 5, 4, 7}, 1));

        LogUtil.logIt("Testing via best optimized using Sliding window O(N)", true);
        System.out.println(kEmptySlotsBestOptimized(new int[]{1, 3, 2}, 1));
        System.out.println(kEmptySlotsBestOptimized(new int[]{1, 2, 3}, 1));
        System.out.println(kEmptySlotsBestOptimized(new int[]{1, 2, 3}, 1));
        System.out.println(kEmptySlotsBestOptimized(new int[]{1, 6, 4, 3, 2, 5}, 3));
        System.out.println(kEmptySlotsBestOptimized(new int[]{3, 9, 2, 8, 1, 6, 10, 5, 4, 7}, 1));
    }

    public static int kEmptySlotsBestOptimized(int[] flowers, int k) {
        // We will construct the days position array via flowers
        int[] daysPosition = new int[flowers.length];
        for (int i = 0; i < flowers.length; i++) {
            daysPosition[flowers[i] - 1] = i + 1;
        }

        // Okay now we have days position on which flowers will blossom
        // Flowers:                    [1, 6, 4, 3, 2, 5]
        // Index                        0  1  2  3  4  5
        //
        // So our daysPosition will be [1, 5, 4, 3, 6, 2]
        // This dayPosition shows which flower[i] was blown on which date.
        // so daysPosition[1] = 5 tells me, that 2nd flower was blown on 5th day
        /**
         * Now we will use sliding window of k+1 days, and what we need to check that
         * between these 2 windows all the flowers blown after the left and right of window
         *
         * K = 2
         * Assume your left and right are here
         *                  0   1  2  3  4  5 (index)
         *                  [1, 5, 4, 3, 6, 2]
         *                      /\       /\
         *                      |        |
         *                      |        |
         *                    left      right=left+k+1  [i.e 1+2+1 = 4]
         *
         *                    [Invalid window, since the 2nd(0basedIndex) flower was blown before 1st(0-BasedIndex) flower
         *
         *  What we need to check is that all the days between left and right should have greater value than daysPosition[left] and daysPosition[right]
         *  what that actually mean is that  since value is > than daysPosition[left] so this flower must have blown after the flower on left position is blown
         *
         *                  0   1  2  3  4  5 (index)
         *                  [1, 5, 4, 3, 6, 2]
         *                  /\       /\
         *                  |        |
         *                  |        |
         *               left      right=left+k+1  [i.e 0+2+1 = 3]
         *
         *               [Valid window, since all flowers between 1st flower and 4th flower(0 based index) are blown on days after 1st and 4th.
         */
        int left = 0, right = left + k + 1;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; right < daysPosition.length; i++) {
            // Days occurring after left and right, so valid days just increment i and reach to the end of sliding window
            if (daysPosition[i] > daysPosition[left] && daysPosition[i] > daysPosition[right]) {
                continue;
            }

            // i reached to end of window and did not find any flower blown between these days
            if (i == right) {
                // we have to give how many minimum days required to reach this solution
                ans = Math.min(ans, Math.max(daysPosition[left], daysPosition[right]));
            }
            left = i;
            right = left + k + 1;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int kEmptySlotsOptimized(int[] flowers, int k) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        int currDay = 0;
        for (int day : flowers) {
            ++currDay;
            // We will check the difference of current day from either the previous day or the
            // current day
            treeSet.add(day);

            Integer dayBefore = treeSet.lower(day);
            Integer dayAfter = treeSet.higher(day);

            if (dayBefore != null && day - dayBefore - 1 == k
                    || dayAfter != null && dayAfter - day - 1 == k) {
                return currDay;
            }
        }
        return -1;
    }

    /**
     * Will give you TLE
     */
    public static int kEmptySlots(int[] flowers, int k) {
        // 1 means on
        // 0 means off
        int[] currentState = new int[flowers.length + 1]; // Since flowers will blossom from 1 to N
        int beg, end;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < flowers.length; i++) {
            currentState[flowers[i]] = 1;
            if (i > 0) { // So at-least 2 flowers have blossomed
                beg = 1; // Ignore 0, since flowers are from 1 to N
                while (beg < currentState.length) {
                    while (currentState[beg] != 1 && beg < currentState.length) {
                        beg++;
                    }
                    end = beg + 1;
                    while (end < currentState.length && currentState[end] == 0) {
                        end++;
                    }
                    // Now either end is on lastIndex  or at a place where flower is already blossom/turned on
                    if (end < currentState.length && currentState[end] == 1) {
                        if (end - beg - 1 == k) {
                            min = Math.min(i + 1, min);
                        }
                    }
                    beg = end;
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
