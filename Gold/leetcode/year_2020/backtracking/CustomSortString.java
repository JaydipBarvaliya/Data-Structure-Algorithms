package com.leetcode.year_2020.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/custom-sort-string/
 */
public class CustomSortString {

    public static void main(String[] args) {
        System.out.println(customSortString("cba", "abcd"));
        System.out.println(customSortString("cbafg", "abcd"));
        System.out.println(customSortString("kqep", "pekeq"));

        System.out.println(customSortStringOptimized("cba", "abcd"));
        System.out.println(customSortStringOptimized("cbafg", "abcd"));
        System.out.println(customSortStringOptimized("kqep", "pekeq"));
    }

    public static String customSortStringOptimized(String order, String S) {
        // First append only 'S' characters to the output and then the normal ones
        int[] bucket = new int[26];
        for (char ch : S.toCharArray()) {
            bucket[ch - 'a']++;
        }

        final StringBuilder str = new StringBuilder();
        // Only choose ones from order first
        for (char ch : order.toCharArray()) {
            for (int i = 0; i < bucket[ch - 'a']; i++) {
                str.append(ch);
            }
            bucket[ch - 'a'] = 0;
        }

        // Final step adding remaining which were not present in order
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < bucket[i]; j++) { // These items are not present in 'Order' input
                str.append((char) (i + 'a'));
            }
        }
        return str.toString();
    }


    /**
     * Again this will give you TLE use {@link CustomSortString#customSortStringOptimized(String, String)} instead
     */
    public static String customSortString(String order, String s) {
        boolean[] used = new boolean[s.length()];
        List<String> allPermutations = new ArrayList<>();
        permute(s, used, getOrderPosition(order), "", allPermutations);
        return allPermutations.size() > 0 ? allPermutations.get(0) : "";
    }

    public static int[] getOrderPosition(final String order) {
        int[] position = new int[128];
        int counter = 1;
        for (char ch : order.toCharArray()) {
            position[ch] = counter++;
        }
        return position;
    }

    private static void permute(final String s, final boolean[] used, final int[] orderPosition, final String current, final List<String> allPermutations) {
        if (current.length() == s.length()) {
            allPermutations.add(current);
        }

        for (int i = 0; i < s.length(); i++) {
            if (used[i] || orderPosition[s.charAt(i)] > 0 && !isPlacementValid(current, orderPosition, s.charAt(i)))
                continue;
            used[i] = true;
            permute(s, used, orderPosition, current + s.charAt(i), allPermutations);
            used[i] = false;
        }
    }

    private static boolean isPlacementValid(final String current, final int[] orderPosition, final char charAt) {
        for (int i = current.length() - 1; i >= 0; i--) {
            if (orderPosition[current.charAt(i)] > 0 && orderPosition[charAt] < orderPosition[current.charAt(i)]) {
                return false;
            }
        }
        return true;
    }
}
