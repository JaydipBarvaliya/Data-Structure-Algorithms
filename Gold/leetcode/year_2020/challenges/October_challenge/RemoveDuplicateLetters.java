package com.leetcode.year_2020.challenges.October_challenge;

import java.util.Stack;

/**
 * @author neeraj on 12/10/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RemoveDuplicateLetters {

    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("cbacdcbc"));
    }

    public static String removeDuplicateLetters(String s) {
        int[] freq = new int[26];
        Stack<Character> stack = new Stack<>();
        boolean[] visited = new boolean[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++; // Store the frequency count of String.
        }

        for (char c : s.toCharArray()) {
            int index = c - 'a';

            freq[index]--;

            if (visited[index]) // If Already visited, then let's not bother.
                continue;

            while (!stack.isEmpty() && c < stack.peek() && freq[stack.peek() - 'a'] != 0) {
                visited[stack.pop() - 'a'] = false; // Remove that item which is smaller than current and also is present again in the string after this item.
            }

            stack.push(c);
            visited[index] = true;
        }

        StringBuilder st = new StringBuilder();
        while (!stack.isEmpty()) {
            st.insert(0, stack.pop());
        }
        return st.toString();
    }
}
