package com.geeksforgeeks.string;

public class MinimumNoOfInsertionsToMakePalindrome {

    public static void main(String[] args) {
        System.out.println(getMinNoOfInsertions("AB".toCharArray(),0,1));
        System.out.println(getMinNoOfInsertions("AA".toCharArray(), 0, 1));
        System.out.println(getMinNoOfInsertions("AAA".toCharArray(),0,2));
        System.out.println(getMinNoOfInsertions("ABCD".toCharArray(), 0, 3));
        System.out.println(getMinNoOfInsertions("ABCDA".toCharArray(),0,4));
        System.out.println(getMinNoOfInsertions("ABCDE".toCharArray(),0,4));
        System.out.println(getMinNoOfInsertions("geeks".toCharArray(),0,4));
    }

    public static int getMinNoOfInsertions(char[] arr, int low, int high) {

        if (low > high) return Integer.MAX_VALUE; // Invalid low and high value
        if (low == high) return 0;  // String with 0 length
        if (low == high - 1) return arr[low] == arr[high] ? 0 : 1; // String with length 2

        return arr[low] == arr[high] ? getMinNoOfInsertions(arr, low + 1, high - 1)
                : Math.min(getMinNoOfInsertions(arr, low, high - 1), getMinNoOfInsertions(arr, low + 1, high)) + 1;
    }
}
