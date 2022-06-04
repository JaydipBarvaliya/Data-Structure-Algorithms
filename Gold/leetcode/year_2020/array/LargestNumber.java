package com.leetcode.year_2020.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://www.interviewbit.com/problems/largest-number/
 */
public class LargestNumber {

    public static void main(String[] args) {
        System.out.println(largestNumber(new int[]{3, 30, 34, 5, 9}));
    }

    public static String largestNumber(final int[] A) {
        final List<String> numbers = new ArrayList<>();
        for (int i : A) {
            numbers.add("" + i);
        }
        Collections.sort(numbers, (a, b) -> {
            int val = (b + a).compareTo(a + b);
            System.out.println("Comparing " + (b + a) + " and " + (a + b) + " and result is " + val);
            return val;
        });
        StringBuilder str = new StringBuilder();
        for (String i : numbers) {
            str.append(i);
        }
        return str.toString();
    }
}
