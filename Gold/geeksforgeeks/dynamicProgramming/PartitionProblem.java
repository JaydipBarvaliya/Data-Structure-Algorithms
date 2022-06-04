package com.geeksforgeeks.dynamicProgramming;

/**
 * https://www.geeksforgeeks.org/dynamic-programming-set-18-partition-problem/
 * <p>
 * arr[] = {1, 5, 11, 5}
 * Output: true
 * The array can be partitioned as {1, 5, 5} and {11}
 * <p>
 * arr[] = {1, 5, 3}
 * Output: false
 * The array cannot be partitioned into equal sum sets.
 * <p>
 * Solution: If Sum of Elements of Array is odd you can not have 2 subset which contribute to the sum
 * if Sum of Elements of Array is even then we have to find a subset which sums upto Sum_Of_Elements_Of_Array / 2;
 * <p>
 * So In even case it becomes Subset Sum Problem
 */
public class PartitionProblem {

    public static void main(String[] args) {
        System.out.println("Is Partition Possible " + isPartitionPossible(new int[]{1, 5, 11, 5}));
        System.out.println("Is Partition Possible " + isPartitionPossible(new int[]{1, 5, 3}));
        System.out.println("Is Partition Possible " + isPartitionPossible(new int[]{10,10,10,320}));
    }

    public static Boolean isPartitionPossible(int[] arr) {
        int Sum_Of_Elements_Of_Array = getSum(arr);
        Boolean isPartitionPossible;

        if (Sum_Of_Elements_Of_Array % 2 == 0) {
            isPartitionPossible = SubsetSumProblem.isSubsetPresent(arr, Sum_Of_Elements_Of_Array / 2);
        } else {
            isPartitionPossible = false;
        }
        return isPartitionPossible;
    }

    public static int getSum(int[] values) {
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        return sum;
    }
}
