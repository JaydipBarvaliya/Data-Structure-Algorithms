package com.leetcode.problems.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neeraj on 01/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RobotReturnToOrigin_657 {

    static Map<Character, Integer> characterIndexMap = new HashMap<>();
    static int[] rowIdx = new int[]{-1, 0, 1, 0};
    static int[] colIdx = new int[]{0, 1, 0, -1};

    static {
        characterIndexMap.put('U', 0);
        characterIndexMap.put('R', 1);
        characterIndexMap.put('D', 2);
        characterIndexMap.put('L', 3);
    }

    public static void main(String[] args) {
        System.out.println(judgeCircle("UD"));
        System.out.println(judgeCircle("LL"));
    }

    public static boolean judgeCircle(String moves) {
        int row = 0;
        int col = 0;
        for (char c : moves.toCharArray()) {
            row += rowIdx[characterIndexMap.get(c)];
            col += colIdx[characterIndexMap.get(c)];
        }
        if (row == 0 && col == 0)
            return true;
        return false;
    }

    public static boolean judgeCircle2(String moves) {
        int upDown = 0;
        int leftRight = 0;

        for(char c: moves.toCharArray()) {
            if(c == 'U') {
                upDown++;
            } else if(c == 'L') {
                leftRight--;
            } else if (c == 'D') {
                upDown--;
            } else if(c == 'R') {
                leftRight++;
            }
        }
        return leftRight == 0 && upDown == 0;
    }
}
