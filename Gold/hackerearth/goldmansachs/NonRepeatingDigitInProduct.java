package com.hackerearth.goldmansachs;

/**
 * @author neeraj on 12/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NonRepeatingDigitInProduct {

    public static void main(String[] args) {
//        System.out.println(nonRepeatingDigitProductCount(2, 10, 15));
        System.out.println(nonRepeatingDigitProductCount(20, 1, 2));
    }

    // Complete the nonRepeatingDigitProductCount function below.
    static int nonRepeatingDigitProductCount(int x, int y, int z) {
        int count = 0;
        int product = 0;
        for (int i = y; i <= z; i++) {
            product = x * i;
            if (isValidProduct(product, i)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isValidProduct(int product, int valueToCheck) {
        String valToCheck = String.valueOf(valueToCheck);
        int remainder = 0;
        while (product > 0) {
            remainder = product % 10;
            if (valToCheck.contains(String.valueOf(remainder))) {
                return false;
            }
            product = product / 10;
        }
        return true; // Valid Product
    }
}
