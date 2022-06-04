package com.leetcode.year_2020;

import com.util.LogUtil;

/**
 * @author neeraj on 19/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RabinKarpSubstringSearchPattern {

    public static void main(String[] args) {
        LogUtil.logIt("Pattern exist " + patternExist("abedabc", "abc"));
        LogUtil.logIt("Pattern exist " + patternExist("neeraj", "raj"));
        LogUtil.logIt("Pattern exist " + patternExist("neeraj", "eera"));
        LogUtil.logIt("Pattern exist " + patternExist("neeraj", "sakshi"));
    }

    public static boolean patternExist(String original, String pattern) {
        /**
         * So We need to calculate Rolling patternHash
         */
        int originalLen = original.length();
        int pattenLen = pattern.length();
        int patternHash = calculateHash(pattern, pattern.length());
        int originalSubstringHash = calculateHash(original, pattern.length());

        for (int i = 0; i <= originalLen - pattenLen + 1; i++) {
            if (patternHash == originalSubstringHash &&
                    equals(original, pattern, i, i + pattenLen - 1, 0, pattenLen - 1)) {
                return true;
            }
            if (i < originalLen - pattenLen)
                originalSubstringHash =
                        recalculateHash(originalSubstringHash, original, i, i + pattenLen, pattenLen);
        }
        return false;
    }

    private static int recalculateHash(int oldHash, String original, int oldIndex, int newIndex, int patternLength) {
        int prime = 3;
        int newHash = oldHash - (original.charAt(oldIndex) - 'a' + 1);
        newHash /= prime;
        newHash += (original.charAt(newIndex) - 'a' + 1) * Math.pow(prime, patternLength - 1);
        return newHash;
    }

    private static boolean equals(String s1, String s2, int start1, int end1, int start2, int end2) {
        if (end1 - start1 != end2 - start2) return false; // Length mismatch

        while (start1 <= end1 && start2 <= end2) {
            if (s1.charAt(start1) != s2.charAt(start2)) return false;
            start1++;
            start2++;
        }
        return true;
    }

    private static int calculateHash(String str, int end) {
        String pattern = str.substring(0, end);
        int patternHash = 0, prime = 3, power = 0;
        for (char c : pattern.toCharArray()) {
            patternHash += ((c - 'a') + 1) * Math.pow(prime, power++);
        }
        return patternHash;
    }


}
