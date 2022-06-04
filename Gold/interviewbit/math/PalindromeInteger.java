package com.interviewbit.math;

/**
 * @author neeraj on 2019-07-27
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PalindromeInteger {
    public static void main(String[] args) {
        System.out.println(isPalindrome(12121));
        System.out.println(isPalindrome(1234));
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(909090));
    }

    public static int isPalindrome(int A) {
        int temp = A;
        int reverse = 0;
        while (temp >0) {
            reverse = reverse * 10 + temp % 10;
            temp = temp/10;
        }
        System.out.println("Reverse of "+ A + "  is "+ reverse);
        return A == reverse ? 1 : 0;
    }
}
