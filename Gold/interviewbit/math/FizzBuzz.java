package com.interviewbit.math;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * https://www.interviewbit.com/problems/fizzbuzz/
 * <p>
 * Given a positive integer A, return an array of strings with all the integers from 1 to N.
 * But for multiples of 3 the array should have “Fizz” instead of the number.
 * For the multiples of 5, the array should have “Buzz” instead of the number.
 * For numbers which are multiple of 3 and 5 both, the array should have “FizzBuzz” instead of the number.
 * <p>
 * Look at the example for more details.
 * <p>
 * Example
 * <p>
 * A = 5
 * Return: [1 2 Fizz 4 Buzz]
 *
 * @author neeraj on 2019-07-27
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FizzBuzz {

    public static void main(String[] args) {
        System.out.print(fizzBuzz(15));
    }

    public static ArrayList<String> fizzBuzz(int A) {
        String[] arr = new String[A];
        for (int i = 1; i <= A; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                arr[i - 1] = "FizzBuzz";
            } else if (i % 3 == 0) {
                arr[i - 1] = "Fizz";
            } else if (i % 5 == 0) {
                arr[i - 1] = "Buzz";
            } else {
                arr[i - 1] = "" + i;
            }
        }
        return new ArrayList<>(Arrays.asList(arr));
    }
}
