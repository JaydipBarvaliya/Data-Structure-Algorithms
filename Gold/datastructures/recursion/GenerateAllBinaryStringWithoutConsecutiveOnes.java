package com.datastructures.recursion;

/**
 * @author neeraj on 10/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class GenerateAllBinaryStringWithoutConsecutiveOnes {

    public static void main(String[] args) {
        generateAllStringsUtil(3);
    }

    public static void generateAllStringsUtil(int K) {
        // Generate for 0 as the start index
        generate(K, 1, "0");

        // Generate for 1 as the start index
        generate(K, 1, "1");
    }

    private static void generate(int k, int pointer, String current) {
        // Base Condition
        if (pointer == k) {
            System.out.println(current);
            return;
        }

        // Since we can't have consecutive one's we need to check the previous 1
        if (current.charAt(pointer - 1) == '0') {
            // We have 2 options, both add 1 and add 2.
            generate(k, pointer + 1, current + '1');

            generate(k, pointer + 1, current + '0');
        }

        if (current.charAt(pointer - 1) == '1') {
            // We have only one options to append "0"
            generate(k, pointer + 1, current + '0');
        }

    }
}
