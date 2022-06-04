package com.leetcode.year_2020.backtracking;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.geeksforgeeks.org/match-a-pattern-and-string-without-using-regular-expressions/
 *
 * @author neeraj on 08/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MatchAPatternAndStringWithoutRegularExpression {

    public static void main(String[] args) {
//        matchPattern("GRAPHTREEGRAPH", "ABA");
//        matchPattern("GRAPHGRAPHGRAPH", "AAA");
//        matchPattern("NJGMNJ", "GfG");
//        matchPattern("GeeksforGeeks", "GG");
//        matchPattern("EatSleep", "XY");
//        matchPattern("gogopowerrangergogopowerranger", "xxyxxy");
//        matchPattern("AABCC", "xyx");
//        matchPattern("AABCA", "xyx");
        matchPattern("XYXX", "ABA");

    }

    public static Map<Character, String> matchPattern(String input, String pattern) {
        Map<Character, String> matches = new HashMap<>();
        backtrack(input, 0, 0, pattern, matches);
        return matches;
    }

    private static void backtrack(String input, int pointer, int patternCounter, String pattern, Map<Character, String> matches) {
        if (pointer == input.length() && patternCounter == pattern.length()) {
            System.out.println(matches + " for Input " + input);
            return;
        }

        if (patternCounter >= pattern.length()) return;

        for (int i = pointer; i < input.length(); i++) {
            boolean isRepeat = false;
            String substring = input.substring(pointer, i + 1);
            Character patternAtThisStage = pattern.charAt(patternCounter);
            if (!matches.containsKey(patternAtThisStage)) {
                // This is the first time we are encountering this type of pattern.
                matches.put(patternAtThisStage, substring);
            } else {
                isRepeat = true;
                // This pattern already exist.
                String existingMatch = matches.get(patternAtThisStage);
                if (substring.length() > existingMatch.length()) break;
                if (!existingMatch.equals(substring)) {
                    continue; // Means there is an issue.
                }
            }
            backtrack(input, i + 1, patternCounter + 1, pattern, matches);
            if (!isRepeat) { // Only remove from map if this was a duplicate pattern.
                /**
                 * New Detailed Analysis
                 * ONLY delete if this recursion was made from original patternPointer element,
                 * not some repeated similar pattern.
                 * DO DRY RUN with XYXX, Pattern: ABA, you will understand it.
                 */
                matches.remove(patternAtThisStage);
            }

        }

    }
}
