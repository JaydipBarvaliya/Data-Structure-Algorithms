package com.leetcode.problems.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/repeated-dna-sequences/
 *
 * @author neeraj on 08/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RepeatedDNASequence {

    public static void main(String[] args) {
        System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(findRepeatedDnaSequences("AAAAAAAAAAAA"));
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        Set<String> result = new HashSet<>();
        Set<String> seen = new HashSet<>();
        String substr;
        for (int i = 0; i <= s.length() - 10; i++) {
            substr = s.substring(i, i + 10);
            if (!seen.add(substr)) {
                result.add(substr);
            }
        }
        return new ArrayList<>(result);
    }
}
