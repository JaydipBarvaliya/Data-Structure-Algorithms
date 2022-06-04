package com.interviewbit.two_pointers;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.interviewbit.com/problems/max-continuous-series-of-1s/
 * <p>
 * You are given with an array of 1s and 0s. And you are given with an integer M, which signifies number of flips allowed.
 * Find the position of zeros which when flipped will produce maximum continuous series of 1s.
 * <p>
 * For this problem, return the indices of maximum continuous series of 1s in order.
 * <p>
 * Example:
 * <p>
 * Input :
 * Array = {1 1 0 1 1 0 0 1 1 1 }
 * M = 1
 * <p>
 * Output :
 * [0, 1, 2, 3, 4]
 * <p>
 * If there are multiple possible solutions, return the sequence which has the minimum start index.
 *
 * @author neeraj on 2019-08-07
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaximumContinuousSeriesOfOnes {

    static int maxOneWindowSize = Integer.MIN_VALUE;

    public static void main(String[] args) {
        solveProblem(Arrays.asList(1, 1, 0, 1, 1, 0, 0, 1, 1, 1), 1);
        solveProblem(Arrays.asList(0, 1, 1, 1), 0);
        solveProblem(Arrays.asList(1, 1, 0), 2);
        solveProblem(Arrays.asList(1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1), 7);
    }

    public static void solveProblem(List<Integer> input, int maxFlip) {
        ArrayList<Integer> A = new ArrayList<>(input);
        LogUtil.logIt("Maximum Ones in Input " + input + " \n \t\t ==> is" + maxone(A, maxFlip));
    }

    public static ArrayList<Integer> maxone(ArrayList<Integer> A, int B) {
        maxOneWindowSize = Integer.MIN_VALUE;
        ArrayList<Integer> result = new ArrayList<>();

        int L = 0;
        int R = 0;
        int totalFlipsTillNow = 0;

        while (R < A.size()) {
            if (A.get(R) == 1) {
                R++;
            } else {
                if (totalFlipsTillNow < B) {
                    totalFlipsTillNow++;
                    R++;
                } else {
                    recalculateMaxWindowOfOnes(L, R, result);
                    while (A.get(L) != 0) {
                        L++;
                    }
                    if (A.get(L) == 0) {
                        totalFlipsTillNow = totalFlipsTillNow == 0 ? 0 : totalFlipsTillNow - 1;
                    }
                    L++;
                    if (L >= R) {
                        R = L;
                    }
                }
            }
        }
        recalculateMaxWindowOfOnes(L, R, result);
        return result;
    }

    private static void recalculateMaxWindowOfOnes(int L, int R, List<Integer> result) {
        // Calculate the length of this sequence
        if (R - L - 1 > maxOneWindowSize) {
            maxOneWindowSize = (R - L - 1);
            result.clear();
            for (int i = L; i < R; i++) {
                result.add(i);
            }
        }
    }
}
