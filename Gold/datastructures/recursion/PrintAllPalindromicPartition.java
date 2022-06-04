package com.datastructures.recursion;


import com.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string, print all possible palindromic partitions
 * <p>
 * https://www.geeksforgeeks.org/given-a-string-print-all-possible-palindromic-partition/
 * <p>
 * Input : NITIN
 * Output :     N I T I N
 * * *         N ITI  N
 * * *        NITIN
 *
 * @author neeraj on 2019-05-04
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PrintAllPalindromicPartition {

    public static void main(String[] args) {
        String input = "NITIN";
//        printAllPalindromicPartition(input);

        System.out.println(getAllPartition(input));
    }

    public static void printAllPalindromicPartition(String input) {
        Boolean addRemainingPartition = false;
        Boolean logAdded = false;
        for (int i = 0; i <= input.length(); i++) {
            addRemainingPartition = false;
            logAdded = false;
            for (int j = 0; j <= input.length() - i; j++) {
                StringBuffer substring = new StringBuffer(input.substring(j, j + i));
                if (!substring.toString().equalsIgnoreCase("") && substring.toString().equalsIgnoreCase(substring.reverse().toString())) {
                    if (!logAdded) {
                        LogUtil.logIt("Length of palindrome available " + i);
                        logAdded = true;
                        if (i > 1) {
                            addRemainingPartition = true;
                        }
                    }
                    if (addRemainingPartition) {

                    }
                    System.out.print(substring + "\t");
                }
            }
            System.out.println();
        }
    }

    public static List<List<String>> getAllPartition(String inputStr) {
        LogUtil.logIt("All Palindromic Partitions", true);
        List<List<String>> validDecomposition = new ArrayList<>(); // This will keep all valid palindromic partition
        List<String> decompositionInProcess = new ArrayList<>(); // This will contain current palindromic partition in process around which recursion revolve.

        decomposeString(inputStr, 0, validDecomposition, decompositionInProcess);
        return validDecomposition;

    }

    private static void decomposeString(String inputStr, int buildPointer, List<List<String>> validDecomposition, List<String> decompositionInProcess) {

        // If our build pointer reached to the end for the input string
        if (inputStr.length() == buildPointer) {
            validDecomposition.add(new ArrayList<>(decompositionInProcess));
        } else {

            // Let's start from buildPointer to the end of inputString
            for (int i = buildPointer; i < inputStr.length(); i++) {

                if (isPalindrome(inputStr, buildPointer, i)) {

                    // So now we have a Substring which is a palindrome, so let's add it to inProcessDecomposition list
                    String palindrome = inputStr.substring(buildPointer, i + 1);

                    decompositionInProcess.add(palindrome);

                    // Let's recurse on this
                    // Since from buildPointer to i is already being used in the palindrome, so
                    // let's directly jump to i + 1 as the buildPointer
                    decomposeString(inputStr, i + 1, validDecomposition, decompositionInProcess);

                    // Since we have recursively traverse all the palindromic pattern of current iteration
                    // So let's backtrack and remove this entry
                    decompositionInProcess.remove(decompositionInProcess.size() - 1);
                }
            }
        }
    }

    private static boolean isPalindrome(String inputStr, int initialIndex, int endIndex) {
        char[] input = inputStr.toCharArray();

        while (initialIndex < endIndex) {
            if (input[initialIndex++] != input[endIndex--]) {
                return false;
            }
        }
        return true;
    }
}
