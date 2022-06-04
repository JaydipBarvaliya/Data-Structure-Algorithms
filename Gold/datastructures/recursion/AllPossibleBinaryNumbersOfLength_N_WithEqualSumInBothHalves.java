package com.datastructures.recursion;

/**
 * @author neeraj on 08/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class AllPossibleBinaryNumbersOfLength_N_WithEqualSumInBothHalves {

    public static void main(String[] args) {
        combination(4, "", "", 0);
        combination(5, "", "", 0);
    }

    public static void combination(int n, String left, String right, int differenceBetweenLeftAndRightSum) {
        // Base Case

        if (n == 0) {
            if (differenceBetweenLeftAndRightSum == 0) {
                System.out.println(left + right + " ");
            }
            return;
        }

        // When N is odd, so we have to put 1 extra  0 or 1 in between
        if (n == 1) {
            if (differenceBetweenLeftAndRightSum == 0) {
                System.out.println(left + "0" + right + " ");
                System.out.println(left + "1" + right + " ");
            }
            return;
        }


        // We can only add new numbers if we have at-least 2 limit left.
        if (2 * Math.abs(differenceBetweenLeftAndRightSum) <= n) {
            // No Binary numbers can start with 0
            if (left != "") {
                // Just do opposite of what we did for making left heavy
                // Appending "0" to both left and right and since difference will remain same.
                combination(n - 2, left + "0", right + "0", differenceBetweenLeftAndRightSum);

                // Append "0" to left and "1" to right, since right is  now heavy so decrement the difference
                combination(n - 2, left + "0", right + "1", differenceBetweenLeftAndRightSum - 1);
            }

            // Here left can be empty or might not be
            // Append "1" to left and 0 to right, since left is heavy so increment the difference
            combination(n - 2, left + "1", right + "0", differenceBetweenLeftAndRightSum + 1);

            // Appending "1" to both left and right and since difference will remain same.
            combination(n - 2, left + "1", right + "1", differenceBetweenLeftAndRightSum);
        }
    }
}
