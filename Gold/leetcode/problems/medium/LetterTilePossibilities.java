package com.leetcode.problems.medium;

/**
 * @author neeraj on 05/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LetterTilePossibilities {

    public static void main(String[] args) {
        numTilePossibilities("AAB");
    }

    static int totalPossibilities = 0;

    public static int numTilePossibilities(String tiles) {
        totalPossibilities = 0;
        permuteUtil(tiles, "");
        return totalPossibilities;
    }

    private static void permuteUtil(String prefix, String suffix) {
        if (suffix != "") {
            System.out.println(suffix);
            totalPossibilities++;
        }
        for (int i = 0; i < prefix.length(); i++) {
            char chatAtI = prefix.charAt(i);
            permuteUtil(prefix.substring(i+1), suffix + chatAtI);
        }
    }


}
