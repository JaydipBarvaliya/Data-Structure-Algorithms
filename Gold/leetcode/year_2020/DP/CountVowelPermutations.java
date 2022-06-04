package com.leetcode.year_2020.DP;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/count-vowels-permutation/
 */
public class CountVowelPermutations {

    public static void main(String[] args) {
        System.out.println(countVowelPermutation(10));
    }

    static int MOD = 1000000000 + 7;
    static Map<String, Long> dp;

    public static int countVowelPermutation(int n) {
        dp = new HashMap<>();
        long a = solve(n, 'a');
        long e = solve(n, 'e');
        long i = solve(n, 'i');
        long o = solve(n, 'o');
        long u = solve(n, 'u');

        return (int) ((a + e + i + o + u) % MOD);
    }

    public static long solve(int n, char endsWithCharacter) {
        String key = n + "::" + endsWithCharacter;
        if (dp.containsKey(key)) return dp.get(key);
        if (n == 1) {
            // if only 1 length, and it has to end with  `endsWithCharacter` then we have just 1 way
            return 1;
        }
        long result = 0;
        if (endsWithCharacter == 'a') {
            result = solve(n - 1, 'e') % MOD; // Each vowel 'a' may only be followed by an 'e'.
        } else if (endsWithCharacter == 'e') {
            result = (solve(n - 1, 'a') + solve(n - 1, 'i')) % MOD; // Each vowel 'e' may only be followed by an 'a' or an 'i'.
        } else if (endsWithCharacter == 'i') {
            result = (solve(n - 1, 'e') + solve(n - 1, 'o') + solve(n - 1, 'u') + solve(n - 1, 'a')) % MOD;
            // Each vowel 'i' may not be followed by another 'i'.
        } else if (endsWithCharacter == 'o') {
            // Each vowel 'o' may only be followed by an 'i' or a 'u'.
            result = (solve(n - 1, 'i') + solve(n - 1, 'u')) % MOD;
        } else {
            // Each vowel 'u' may only be followed by an 'a'.
            result = solve(n - 1, 'a') % MOD;
        }
        dp.put(key, result);
        return dp.get(key);
    }
}
