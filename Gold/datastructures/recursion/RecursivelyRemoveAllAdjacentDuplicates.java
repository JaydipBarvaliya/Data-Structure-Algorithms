package com.datastructures.recursion;

/**
 * @author neeraj on 25/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RecursivelyRemoveAllAdjacentDuplicates {

    public static void main(String[] args) {
        System.out.println(removeAdjacentDuplicates("AZXXZY"));
        System.out.println(removeAdjacentDuplicates("geeksforgeeg"));

        System.out.println(removeAdjacentDuplicates("caaabbbaacdddd"));

        System.out.println(removeAdjacentDuplicates("acaaabbbacdddd"));
    }

    public static String removeAdjacentDuplicates(String s) {
        //base condition
        if (s.length() <= 1) return s; // There can't be any duplicate in single letter or no letter.

        // Hypothesis
        String uniqueItems = removeAdjacentDuplicates(s.substring(1));

        // Induction
        int index = uniqueItems.indexOf(s.charAt(0));

        if (index > -1) {
            StringBuilder sb = new StringBuilder(uniqueItems);
            sb.deleteCharAt(index);
            return s.charAt(0)+"";
        } else {
            return s.charAt(0) + uniqueItems;
        }
    }

}
