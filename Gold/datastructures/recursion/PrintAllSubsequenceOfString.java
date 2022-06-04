package com.datastructures.recursion;

import com.util.LogUtil;

/**
 * https://www.geeksforgeeks.org/print-subsequences-string/
 * <p>
 * Input : abc
 * Output : a, b, c, ab, bc, ac, abc
 * <p>
 * Input : aaa
 * Output : a, aa, aaa
 *
 * @author neeraj on 2019-05-04
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PrintAllSubsequenceOfString {
    public static void main(String[] args) {
        printAllSubsequence("abcd", "");
        printAllSubsequence("123", "");
//        printAllSubString("nitin");
    }

    public static void printAllSubsequence(String prefix, String suffix) {
        if (prefix.length() < 0) {
            return;
        }

        System.out.println(suffix);
        for (int i = 0; i < prefix.length(); i++) {
            printAllSubsequence(prefix.substring(i + 1), suffix + prefix.charAt(i));
        }
    }

    public static void printAllSubString(String str) {
        LogUtil.logIt("All SubStrings ", true);
        for (int i = 1; i <= str.length(); i++) {
            for (int j = 0; j <= str.length() - i; j++) {
                System.out.println(str.substring(j, j + i));
            }
        }
    }
}
