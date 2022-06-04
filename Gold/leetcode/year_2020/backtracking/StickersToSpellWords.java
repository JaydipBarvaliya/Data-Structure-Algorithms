package com.leetcode.year_2020.backtracking;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/stickers-to-spell-word/
 * <p>
 * This solution is wrong.
 */
public class StickersToSpellWords {

    public static void main(String[] args) {
        System.out.println(minStickers(new String[]{"with", "example", "science"}, "thehat"));
        System.out.println(minStickers(new String[]{"notice", "possible"}, "basicbasic"));
        System.out.println(minStickers(new String[]{"travel", "quotient", "nose", "wrote"}, "lastwest"));
    }

    public static int minStickers(String[] stickers, String target) {
        int[] targetFreq = new int[26];
        for (char ch : target.toCharArray()) {
            targetFreq[ch - 'a']++;
        }
        int res = dfs(stickers, targetFreq);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    static int[] allZerosArr = new int[26];
    final static String allZeros = Arrays.toString(allZerosArr);

    private static int dfs(final String[] stickers, final int[] targetFreq) {
        // Base Condition
        if (allZeros.equals(Arrays.toString(targetFreq))) return 0; // You don't need any stickers

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < stickers.length; i++) {
            int[] freqCopy = Arrays.copyOf(targetFreq, targetFreq.length);
            int count = 0;
            for (char ch : stickers[i].toCharArray()) {
                if (freqCopy[ch - 'a'] > 0) {
                    freqCopy[ch - 'a']--;
                    count++;
                }
            }

            if (count > 0) {
                int required = dfs(stickers, freqCopy);
                if (required != Integer.MAX_VALUE) {
                    // So if we found out that we were able to finish up all target
                    // and we know this current ith word contributed (since count>0) so we should add this contribution
                    // to required.
                    result = Math.min(result, required + 1);
                }
            }
        }
        return result;
    }
}
