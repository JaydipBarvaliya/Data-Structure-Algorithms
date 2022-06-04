package com.geeksforgeeks.dynamicProgramming;

import com.util.LogUtil;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Count Total Unique Binary Search Trees - The nth Catalan Number (Dynamic Programming)
 * <p>
 * <p>
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 * <p>
 * Example:
 * <p>
 * Input: 3
 * Output: 5
 * Explanation:
 * Given n = 3, there are a total of 5 unique BST's:
 * <p>
 * *   1         3     3      2      1
 * *    \       /     /      / \      \
 * *     3     2     1      1   3      2
 * *    /     /       \                 \
 * *   2     1         2                 3
 *
 * @author neeraj on 2019-05-11
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class UniqueBinarySearchTree_CatalanNumber {

    public static void main(String[] args) {
        countUniqueBinarySearchTreeWith(3);
        printPermutation("abc");
        printPermutation("boat");
    }

    public static void countUniqueBinarySearchTreeWith(int number) {
        int[] numberOfUniqueBST = new int[number + 1];

        // Base Case 1
        // When we have no nodes, i.e number = 0;
        numberOfUniqueBST[0] = 1; // We only have 1 unique BST i.e EMPTY_TREE

        // Base Case 2
        // When we have just 1 node.
        numberOfUniqueBST[1] = 1; // With 1 node we can just put it in root and have only 1 unique BST

        // Now starting from Number of Nodes =  2 to the input number, we will calculate unique BSt
        for (int i = 2; i <= number; i++) {

            // Now we can put 1....to...the...number (i) of nodes
            // alternatively on the root and can check unique BST
            // Summation of all the unique ways
            for (int j = 1; j <= i; j++) {
                // G(i) = F(i-1)(Left Tree) * F(number - i)(Right Tree);
                numberOfUniqueBST[i] += numberOfUniqueBST[j - 1] * numberOfUniqueBST[i - j];
            }
        }
        LogUtil.logIt("Number of Unique BST with n = " + number + " is " + numberOfUniqueBST[number]);
    }

    public static void printPermutation(String str) {
        Map<Character, Long> frequencyMap = calculateFrequency(str);
        permuteInternally(frequencyMap, str, 0, new char[str.length()]);
    }

    private static void permuteInternally(Map<Character, Long> frequencyMap, String str, int currentLevel, char[] result) {
        if (currentLevel == str.length()) {
            System.out.println(String.valueOf(result));
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (frequencyMap.get(c) == 0L) {
                continue;
            }
            frequencyMap.put(c, frequencyMap.get(c) - 1l);
            result[currentLevel] = c;
            permuteInternally(frequencyMap, str, currentLevel + 1, result);
            frequencyMap.put(c, frequencyMap.get(c) + 1l);
        }
    }

    private static Map<Character, Long> calculateFrequency(String str) {
        return str
                .chars() // Convert to IntStream
                .mapToObj(character -> (char) character) // Getting all the characters
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // Applying grouping and counting the frequency
    }
}
