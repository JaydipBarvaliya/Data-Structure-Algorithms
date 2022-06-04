package com.leetcode.problems.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neeraj on 28/08/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class Subsets_78 {

    public static void main(String[] args) {
        subsets(new int[]{1, 2, -3});
        subsets(new int[]{-1, 2, 3});
        subsets(new int[]{10, 20, 30});

        subSetOfString("abc");
        subSetOfString("abcd");
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        findSubsets(0, nums, new ArrayList<>(), subsets);
        System.out.println(subsets);
        return subsets;
    }

    public static void subSetOfString(String inputs) {
        List<List<String>> subsets = new ArrayList<>();
        findSubSetOfString(0, inputs.split(""), new ArrayList<>(), subsets);
        System.out.println(subsets);
    }

    public static void findSubsets(int index, int[] nums, List<Integer> current, List<List<Integer>> subsets) {
        subsets.add(new ArrayList<>(current));

        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);
            findSubsets(i + 1, nums, current, subsets);
            current.remove(current.size() - 1);
        }
    }

    public static void findSubSetOfString(int index, String[] inputs, List<String> current, List<List<String>> subsets) {
        subsets.add(new ArrayList<>(current));
        for (int i = index; i < inputs.length; i++) {
            current.add(inputs[i]);
            findSubSetOfString(i + 1, inputs, current, subsets);
            current.remove(current.size() - 1);
        }
    }
}
