package com.interviewbit.two_pointers;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://www.interviewbit.com/problems/container-with-most-water/
 * <p>
 * <p>
 * Given n non-negative integers a1, a2, ..., an,
 * where each represents a point at coordinate (i, ai).
 * 'n' vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * <p>
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * <p>
 * Your program should return an integer which corresponds to the maximum area of water that can be contained ( Yes, we know maximum area instead of maximum volume sounds weird. But this is 2D plane we are working with for simplicity ).
 * <p>
 * Note: You may not slant the container.
 * Example :
 * <p>
 * Input : [1, 5, 4, 3]
 * Output : 6
 * <p>
 * Explanation : 5 and 3 are distance 2 apart. So size of the base = 2. Height of container = min(5, 3) = 3.
 * So total area = 3 * 2 = 6
 *
 * @author neeraj on 2019-08-07
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ContainerWithMostWaters {

    public static void main(String[] args) {
        solveProblem(Arrays.asList(1, 5, 4, 3));
    }

    public static void solveProblem(List<Integer> input) {
        ArrayList<Integer> A = new ArrayList<>(input);
        LogUtil.logIt("Most Water QTY for " + input + " \n \t\t ==> is " + maxArea(A));
    }

    public static int maxArea(ArrayList<Integer> A) {
        if (A.size() <= 1) {
            return 0;
        }
        int L = 0;
        int R = A.size() - 1;
        int maxArea = Integer.MIN_VALUE;
        while (L < R) {
            int volume = (R - L) * Math.min(A.get(L), A.get(R));
            maxArea = Math.max(maxArea, volume);
            if(A.get(L) > A.get(R)) {
                R--;
            } else {
                L++;
            }
        }
        return maxArea;
    }
}
