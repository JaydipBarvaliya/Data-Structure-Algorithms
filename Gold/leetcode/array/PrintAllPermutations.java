package com.leetcode.array;

import com.util.LogUtil;

/**
 * @author neeraj on 16/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 * <p>
 * CS 106B Lecture: Backtracking (permute a string)
 * https://www.youtube.com/watch?v=78t_yHuGg-0
 */
public class PrintAllPermutations {

    public static void main(String[] args) {
        /**
         * ABC ==>  ABC, ACB, BAC, BCA, CAB, CBA
         */
        LogUtil.logIt("Permutation using Backtracking.....");
        permuteString("ABC");

        LogUtil.logIt("Permutation using Prefix and Suffix...........");
        permute("", "ABC");
    }

    private static void permute(String prefix, String suffix) {
        if (suffix.length() == 0) {
            System.out.println(prefix);
        }
        for (int i = 0; i < suffix.length(); i++) {
            permute(prefix + suffix.charAt(i), suffix.substring(0, i) + suffix.substring(i + 1));
        }
    }

    private static void permuteString(String str) {
        permuteUtil(str, "");
    }

    private static void permuteUtil(String str, String choosen) {
        if (str.length() == 0) {
            System.out.println(choosen);
        }
        for (int i = 0; i < str.length(); i++) {
            char charAtI = str.charAt(i);
            str = new StringBuffer(str).deleteCharAt(i).toString();
            choosen = new StringBuffer(choosen).append(charAtI).toString();

            permuteUtil(str, choosen);

            // Now unchoose
            str = charAtI + str;
            choosen = new StringBuffer(choosen).deleteCharAt(choosen.length() - 1).toString();
        }
    }
}
