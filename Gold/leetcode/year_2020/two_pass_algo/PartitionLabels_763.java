package com.leetcode.year_2020.two_pass_algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/partition-labels/
 * <p>
 * A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
 * <p>
 * Example 1:
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 * Note:
 * <p>
 * S will have length in range [1, 500].
 * S will consist of lowercase letters ('a' to 'z') only.
 *
 * @author neeraj on 2019-08-05
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PartitionLabels_763 {

    public static void main(String[] args) {
        System.out.println(partitionLabel("ababcbacadefegdehijhklij"));
        System.out.println(simpleApproachForPartitionLabels("ababcbacadefegdehijhklij"));
        System.out.println(partitionLabel("eaaaabaaec"));
        System.out.println(simpleApproachForPartitionLabels("eaaaabaaec"));
    }

    public static List<Integer> simpleApproachForPartitionLabels(String s) {
        // Find the last occurrence of each character in this partition.
        // Let's do 1 pass of the input string and find out that.
        Map<Character, Integer> characterLastPositionMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            characterLastPositionMap.put(s.charAt(i), i);
        }
        List<Integer> result = new ArrayList<>();
        int counter = 0;
        int firstPosition = 0;
        int lastIndexOfThisPartition = 0;
        while (counter < s.length()) {
            lastIndexOfThisPartition = Math.max(lastIndexOfThisPartition, characterLastPositionMap.get(s.charAt(counter)));
            if (lastIndexOfThisPartition == counter) {
                result.add(lastIndexOfThisPartition - firstPosition + 1);
                firstPosition = lastIndexOfThisPartition + 1;
            }
            counter++;
        }
        return result;
    }

    public static List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();
        int L = 0, R = 1, lastIndexOf = 0;
        int lengthOfStr = S.length();
        StringBuffer partition = new StringBuffer();
        partition.append(S.charAt(0));

        while (lastIndexOf < lengthOfStr && R < S.length()) {
            if (partition.toString().contains(String.valueOf(S.charAt(R)))) {
                lastIndexOf = R;
                partition = new StringBuffer(S.substring(L, lastIndexOf + 1));
            }
            R++;
            if (R == S.length()) {
                result.add(lastIndexOf - L + 1);
                lastIndexOf++;
                L = lastIndexOf;
                R = L;
                if (lastIndexOf < S.length()) {
                    partition = new StringBuffer();
                    partition.append(S.charAt(L));
                }
            }
        }
        return result;
    }

    public static List<Integer> partitionLabel(String s) {
        List<Integer> result = new ArrayList<>();
        Map<Character, Integer> characterEndingPositionMap = new HashMap<>();

        int counter = 0;
        for (char c : s.toCharArray()) {
            characterEndingPositionMap.put(c, counter++);
        }

        counter = 0;
        int size = 1;
        int endOfThisPartition = characterEndingPositionMap.get(s.charAt(0));
        while (counter < s.length()) {
            int endPositionOfThisCharacter = characterEndingPositionMap.get(s.charAt(counter));
            endOfThisPartition = Math.max(endPositionOfThisCharacter, endOfThisPartition);
            if (counter == endOfThisPartition) {
                result.add(size);
                size = 0;
            }
            counter++;
            size++;
        }
//        System.out.println(characterEndingPositionMap);
        return result;
    }
}
