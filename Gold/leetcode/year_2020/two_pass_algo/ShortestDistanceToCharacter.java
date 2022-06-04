package com.leetcode.year_2020.two_pass_algo;

import com.util.LogUtil;

/**
 * @author neeraj on 15/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ShortestDistanceToCharacter {

    public static void main(String[] args) {
        LogUtil.printArray(shortestToChar("loveleetcode", 'e'));
    }

    public static int[] shortestToChar(String S, char C) {
        int[] nearestCInLeft = new int[S.length()];
        int[] nearestCInRight = new int[S.length()];
        int len = S.length();

        nearestCInLeft[0] = S.charAt(0) == C ? 0 : Integer.MAX_VALUE;
        for (int i = 1; i < len; i++) {
            if (S.charAt(i) != C) {
                nearestCInLeft[i] = nearestCInLeft[i - 1];
            } else {
                nearestCInLeft[i] = i;
            }
        }

        nearestCInRight[len - 1] = S.charAt(len - 1) == C ? len - 1 : Integer.MAX_VALUE;
        for (int i = len - 2; i >= 0; i--) {
            if (S.charAt(i) != C) {
                nearestCInRight[i] = nearestCInRight[i + 1];
            } else {
                nearestCInRight[i] = i;
            }
        }

        // Now compare both and get the smallest
        int[] nearestToC = new int[len];
        for (int i = 0; i < len; i++) {
            nearestToC[i] = Math.min(Math.abs(nearestCInRight[i] - i), Math.abs(nearestCInLeft[i] - i));
        }
        return nearestToC;
    }
}
