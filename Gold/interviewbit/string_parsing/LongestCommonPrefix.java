package com.interviewbit.string_parsing;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author neeraj on 2019-07-31
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LongestCommonPrefix {

    static class MinLength {
        String input;
        int index;
        int length = Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        solve(Arrays.asList("abcdefgh", "aefghijk", "abcefgh"));
        solve(Arrays.asList("abab", "ab", "abcd"));
        solve(Arrays.asList("ABCD"));
    }

    public static void solve(List<String> inputs) {
        ArrayList<String> inputList = new ArrayList<>(inputs);
        LogUtil.logIt("Longest Common Prefix in " +
                inputs + " is ===> " + longestCommonPrefix(inputList));
    }

    public static String longestCommonPrefix(ArrayList<String> inputList) {
        final MinLength minLength = new MinLength();

        // First find out the String of minimum length in the inputList.
        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i).length() < minLength.length) {
                minLength.length = inputList.get(i).length();
                minLength.input = inputList.get(i);
                minLength.index = i;
            }
        }

        int index = 1;
        String prefix = "";
        String result = "";
        while (true && index <= minLength.length) {
            prefix = minLength.input.substring(0, index++);
            if (isCommonPrefixForAllInput(inputList, prefix)) {
                result = prefix;
                continue;
            } else {
                break;
            }
        }
        return result;
    }

    public static boolean isCommonPrefixForAllInput(ArrayList<String> inputList, String prefix) {
        return inputList
                .parallelStream()
                .allMatch(input -> input.startsWith(prefix));
    }
}
