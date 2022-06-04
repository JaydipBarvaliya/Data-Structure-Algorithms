package com.leetcode.year_2020.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neeraj on 10/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PalindromicPartitioning {

    public static void main(String[] args) {
        System.out.println(partition("aab"));
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        palindromicSubset(s, 0, new ArrayList<>(), result);
        return result;
    }

    private static void palindromicSubset(String s, int pointer, ArrayList<String> subset, List<List<String>> result) {
        if (pointer == s.length()) {
            System.out.println(subset);
            result.add(new ArrayList<>(subset));
            return;
        }
        for (int i = pointer; i < s.length(); i++) {
            if (isPalindrome(s.substring(pointer, i + 1))) {
                subset.add(s.substring(pointer, i + 1));
                palindromicSubset(s, i + 1, subset, result);
                subset.remove(subset.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String str) {
        String temp = str;
        return new StringBuffer(str).reverse().toString().equals(temp);
    }
}
