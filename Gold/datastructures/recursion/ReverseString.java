package com.datastructures.recursion;

/**
 * @author neeraj on 08/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ReverseString {

    public static void main(String[] args) {
        System.out.println(reverse("POLYGLOT"));
    }

    public static String reverse(String str) {
        if (str == null || str.equals("")) {
            return str;
        }
        char firstChar = str.charAt(0);
        String reversed = reverse(str.substring(1));
        return reversed + firstChar;
    }
}
