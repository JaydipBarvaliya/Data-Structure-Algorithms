package com.interviewbit.string_parsing;

import java.util.stream.Collectors;

/**
 * @author neeraj on 2019-07-31
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PalindromeString {

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));
        System.out.println(isPalindrome("123ABCCBA321"));
    }

    public static boolean isPalindrome(String A) {
        A = A.toLowerCase();
        String filteredData = A.chars()
                .mapToObj(i -> (char) i)
                .filter(str -> String.valueOf(str).matches("[a-z0-9]+"))
                .map(Object::toString)
                .collect(Collectors.joining());

        System.out.println("Original String ==> "+ A);
        System.out.println("Filtered Data ==> "+ filteredData);
        return new StringBuffer(filteredData).reverse().toString().equalsIgnoreCase(filteredData);
    }
}
