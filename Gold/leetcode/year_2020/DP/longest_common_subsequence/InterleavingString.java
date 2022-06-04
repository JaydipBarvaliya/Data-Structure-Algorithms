package com.leetcode.year_2020.DP.longest_common_subsequence;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neeraj on 23/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class InterleavingString {

    public static void main(String[] args) {
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        System.out.println(isInterleave("aa", "aa", "aa"));
        System.out.println(isInterleave("", "", ""));
    }

    static Map<String, Boolean> T;

    public static boolean isInterleave(String s1, String s2, String s3) {
        T = new HashMap<>();
        if(s1.length() + s2.length() != s3.length()) return false;
        return isInterleave(s1, s2, s3, 0, 0, 0);
    }

    /**
     * This is O(2^(m+n)) time complexity, where m = len(s1) and n = len(s2)
     * <p>
     * Since at every step we have 2 options (2 branches, we can either choose from S1 or choose from S2)
     * <p>
     * In Order to fix this we can memorize the result.
     */
    private static boolean isInterleave(String s1, String s2, String s3, int i, int j, int k) {
        final String key = i + "*" + j + "*" + k;
        if (k == s3.length())
            return i == s1.length() && j == s2.length();

        if (T.containsKey(key)) return T.get(key);

        if (i == s1.length()) {
            // If s1 is finished
            T.put(key, isMatching(s2, s3, j, k) && isInterleave(s1, s2, s3, i, j + 1, k + 1));
            return T.get(key);
        }
        if (j == s2.length()) {
            // If s2 is finished
            T.put(key, isMatching(s1, s3, i, k) && isInterleave(s1, s2, s3, i + 1, j, k + 1));
            return T.get(key);
        }

        boolean choosingS1 = isMatching(s1, s3, i, k) && isInterleave(s1, s2, s3, i + 1, j, k + 1);
        boolean choosingS2 = isMatching(s2, s3, j, k) && isInterleave(s1, s2, s3, i, j + 1, k + 1);

        T.put(key, choosingS1 || choosingS2);
        return T.get(key);
    }

    private static boolean isMatching(String A, String B, int i, int j) {
        return A.charAt(i) == B.charAt(j);
    }

}
