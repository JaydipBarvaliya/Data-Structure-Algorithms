package com.leetcode.year_2020.category_unknown;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.geeksforgeeks.org/group-shifted-string/
 * <p>
 * Given a string, we can "shift" each of its letter to its successive letter (meaning a shifted to b and b shifted to c and c shifted to d on same index)
 * <p>
 * , for example: "abc" -> "bcd". We can keep "shifting"
 * <p>
 * "abc" -> "bcd" -> "cde" -> "def" -> "efg" -> "fgh" ... -> "xyz"
 */
public class GroupedShiftedStrings {

    public static void main(String[] args) {
        groupShiftedStrings(new String[]{"acd", "dfg", "wyz", "yab", "mop", "bdfh", "a", "x", "moqs"});
        groupShiftedStrings(new String[]{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"});

        playTheGame("fik", "adf");
    }

    /**
     * We are jumping 1 letter in each column
     * *    a   b   c
     * *    b   c   d
     * *    c   d   e
     * *    d   e   f
     * <p>
     * Now we know that we are jumping 1-1 letter each, so we can only reach to a particular word if the columnDifference
     * between all letters is same as the destination
     * <p>
     * Another example
     * *  f  I  K
     * *  G  J  L
     * *  H  K  M
     * <p>
     * Now ask yourself this question can, this shifting ever lead to (A D F) ? Yes it can Why check column difference
     * <p>
     * (F) G H (I) J (K)
     * <p>
     * (I)-(F) = 3 and (K) - (I) = 2
     * <p>
     * Check this for A D F
     * <p>
     * (A) B C (D) E (F)
     * (D) - (A) = 3 and (F) - (D) = 2
     * <p>
     * So they are shifted words, still don't trust me i have made a program for you which will shift words 1 by one and reach to destination
     * and if you try to put something random it will break.
     * <p>
     * playTheGame(source, destination)
     */

    public static List<List<String>> groupShiftedStrings(final String[] words) {
        final Map<String, List<String>> consecutiveLetterDifferenceMap = new HashMap<>();

        for (String word : words) {
            final String difference = consecutiveLetterDifference(word);
            consecutiveLetterDifferenceMap.putIfAbsent(difference, new ArrayList<>());
            consecutiveLetterDifferenceMap.get(difference).add(word);
        }
        System.out.println(consecutiveLetterDifferenceMap);
        return null;
    }

    public static String consecutiveLetterDifference(final String A) {
        if (A.length() == 0 || A.length() == 1) return "0";
        StringBuilder difference = new StringBuilder();
        for (int i = 1; i < A.length(); i++) {
            int diff = (A.charAt(i) - A.charAt(i - 1)) % 26;
            difference.append(diff);
        }
        return difference.toString();
    }

    public static void playTheGame(final String source, final String destination) {
        String sourceClone = source; // will be required to check if we reached back to source while shifting and we will break if we never found our destination

        while (true) {
            System.out.println(sourceClone);
            sourceClone = incrementOne(sourceClone);
            if (sourceClone.equals(destination)) {
                System.out.println(sourceClone + "<========================== Destination");
                break;
            } else if (sourceClone.equals(source)) {
                System.out.println("Nahhhhh Bad luck, not found");
                break;
            }
        }
    }

    private static String incrementOne(final String sourceClone) {
        final StringBuilder result = new StringBuilder();
        for (char c : sourceClone.toCharArray()) {
            int val = (c - 'a' + 1) % 26;
            char ch = (char) (val + 'a');
            result.append(ch);
        }
        return result.toString();
    }
}
