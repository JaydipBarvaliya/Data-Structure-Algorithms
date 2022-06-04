package com.leetcode.year_2020.backtracking;

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
 * <p>
 * 1239. Maximum Length of a Concatenated String with Unique Characters
 *
 * @author neeraj on 22/03/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaximumLengthOfConcatenatedStringWithUniqueCharacters {

    public static void main(String[] args) {
        testIt(Arrays.asList("un", "iq", "ue"));
        testIt(Arrays.asList("cha", "r", "act", "ers"));
        testIt(Arrays.asList("abcdefghijklmnopqrstuvwxyz"));
        testIt(Arrays.asList("yy", "bkhwmpbiisbldzknpm"));
        testIt(Arrays.asList("jnfbyktlrqumowxd", "mvhgcpxnjzrdei"));
        testIt(Arrays.asList("a", "abc", "d", "de", "def"));
    }

    private static void testIt(List<String> arr) {
        System.out.println("Max Length of " + arr);
        System.out.println(maxLength(arr));
    }

    static Integer MAX_LENGTH = 0;

    public static int maxLength(List<String> arr) {
        MAX_LENGTH = 0;
        backtrack("", 0, arr);
        return MAX_LENGTH;
    }

    private static void backtrack(String temp, int index, List<String> arr) {
        MAX_LENGTH = Math.max(temp.length(), MAX_LENGTH);
        for (int i = index; i < arr.size(); i++) {
            if (!containDuplicateCharacters(temp + arr.get(i))) {
                backtrack(temp + arr.get(i), i + 1, arr);
            }
        }
    }

    private static boolean containDuplicateCharacters(String temp) {
        int count[] = new int[26];
        for (char c : temp.toCharArray()) {
            if (count[c - 'a'] == 1) {
                return true;
            } else {
                count[c - 'a'] = 1;
            }
        }
        return false;
    }
}
