package com.leetcode.problems.easy;

import java.util.Stack;

/**
 * @author neeraj on 15/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class BackspaceStringCompare_844 {

    public static void main(String[] args) {
        System.out.println(backspaceCompare("ab#c", "ad#c"));
        System.out.println(backspaceCompare("ab##", "#a#c"));
        System.out.println(backspaceCompare("a##c", "#a#c"));
        System.out.println(backspaceCompare("a#c", "b"));
        System.out.println(backspaceCompare("y#fo##f", "y#f#o##f"));
    }

    public static boolean backspaceCompare(String S, String T) {
        Stack<Character> first = new Stack<>();
        Stack<Character> second = new Stack<>();

        for (char c : S.toCharArray()) {
            if (c != '#') {
                first.push(c);
            } else if (!first.isEmpty()) {
                first.pop();
            }
        }

        for (char c : T.toCharArray()) {
            if (c != '#') {
                second.push(c);
            } else if (!second.isEmpty()) {
                second.pop();
            }
        }

        if (first.size() != second.size()) {
            return false;
        }

        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        while (!first.isEmpty()) {
            sb1.append(first.pop());
            sb2.append(second.pop());
        }
        return sb1.toString().equalsIgnoreCase(sb2.toString());
    }
}
