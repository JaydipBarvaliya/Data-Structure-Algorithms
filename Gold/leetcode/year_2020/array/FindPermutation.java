package com.leetcode.year_2020.array;

import java.util.ArrayList;
import java.util.Stack;

public class FindPermutation {

    public static void main(String[] args) {
        System.out.println(findPerm("ID", 3));
        System.out.println(findPerm("IDID", 5));
        System.out.println(findPerm("IDDDIIID", 8));
    }

    public static ArrayList<Integer> findPerm(final String A, int B) {
        final ArrayList<Integer> result = new ArrayList<>();
        final Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < B; i++) {
            stack.push(i + 1);
            if (i < A.length() && A.charAt(i) == 'I') {
                while (!stack.isEmpty()) {
                    result.add(stack.pop());
                }
            }
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }
}
