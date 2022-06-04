package com.datastructures.recursion;

import com.util.LogUtil;

/**
 * https://www.geeksforgeeks.org/print-n-bit-binary-numbers-1s-0s-prefixes/
 *
 * @author neeraj on 27/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class Print_N_BinaryNumbers_HavingOneGreaterThanOrEqualToZero {

    public static void main(String[] args) {
        printNBinaryNumbers(2);
        printNBinaryNumbers(3);
        printNBinaryNumbers(4);
    }

    public static void printNBinaryNumbers(int n) {
        LogUtil.logIt("All Binary Numbers for n =====> " + n + " is ");
        print("", 0, 0, n);
    }

    private static void print(String output, int noOfOnesUsed, int noOfZerosUsed, int n) {
        if (n == 0) {
            System.out.println(output);
            return;
        }

        print(output + "1", noOfOnesUsed + 1, noOfZerosUsed, n - 1);
        if (noOfZerosUsed < noOfOnesUsed) {
            print(output + "0", noOfOnesUsed, noOfZerosUsed + 1, n - 1);
        }
    }
}
