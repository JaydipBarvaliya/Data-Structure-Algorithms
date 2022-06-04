package com.leetcode.year_2020.binary_search;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-k-closest-elements/
 *
 * @author neeraj on 20/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FindKClosestElements {

    public static void main(String[] args) {
        System.out.println(findClosestElementsUsingBinarySearch(new int[]{1, 2, 3, 4, 5}, 4, 3));
        System.out.println(findClosestElementsUsingBinarySearch(new int[]{1, 2, 3, 4, 5}, 2, 3));
        System.out.println(findClosestElementsUsingBinarySearch(new int[]{1, 2, 3, 4, 5}, 2, -1));


        // Duplicates
        System.out.println(findClosestElementsUsingBinarySearch(new int[]{1, 2, 3, 4, 4, 4, 4, 4, 6, 8, 10}, 2, 9));
    }


    public static List<Integer> findClosestElementsUsingBinarySearch(int[] arr, int k, int x) {
        /**
         ** Okay so we have sorted inputs now if we want k closest it can be a sliding window of size k, which will contain our answers.
         ** Initially the window will be of size (arr(size) - k).
         ** So now our answer can be on following positions
         *
         *   |Start------------------------------------------Size-k|.......Size.
         *                                                    /\
         *                                                    ||
         *                                                    ||
         *   Now our answer will be either starting from      ||   or it can start from Start index.
         *   and if the answer lies in between then the window will shrink, similar to any other binary search problem.
         *
         *   Now the important point whenever we are in a windows mid position.
         *   we check 2 things.
         *   The difference of "x" with "mid item" and " difference of x with mid+k" item.
         *   this will help us know where to move our window.
         **/

        int low = 0;
        int high = arr.length - k;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (x - arr[mid] > arr[mid + k] - x) { // Why not absolute difference, since there can be duplicates in input
                // and absolute difference will give us no clue where to move.
                // Now if the subtraction on left side is greater than the right side of window.
                // this means if we go more on left, the difference will increase and we will not find our closest element.
                // window start. hence in this we will shrink the window
                // Now you might be wondering why (x - arr[mid] > arr[mid + k] -x) rather than (x - arr[mid] > x - arr[mid + k])
                // if we choose the latter option we end up getting wrong result due to negative values
                // Example [1,2,3,4,8,9] k=2, x=1
                // Mid = [3(value)], so (1 - 3) == -2 and if we do (1 - 8) == -7, now according to logic we should have move low = mid+1;
                // but that's wrong since we are looking for 2 points near to 1 and moving away from 3 right side will only increase the difference
                // hence we choose the former option (1 - 3) == -2 and (8 - 1) == 7, now you see A/o logic we need to move high to mid and that's correct.
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        // Now low is at the point of window start.
        List<Integer> result = new ArrayList<>();
        for (int i = low; i < low + k; i++) {
            result.add(arr[i]);
        }
        return result;
    }
}
