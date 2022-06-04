package com.leetcode.year_2020.DP.matrix_chain_multiplication;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/scramble-string/
 *
 * @author neeraj on 12/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ScrambleStrings {

    public static void main(String[] args) {
        System.out.println(isScramble("ABC", "CAB"));
        System.out.println(isScramble("great", "rgeat"));
        System.out.println(isScramble("abcde", "caebd"));
    }

    static Map<String, Boolean> memorization;

    public static boolean isScramble(String A, String B) {
        /**
         *     great
         *    /    \
         *   gr    eat
         *  / \    /  \
         * g   r  e   at
         *            / \
         *           a   t
         * Represent the String as a binary tree(exactly two child). Now what question is asking,
         * if they swap non leaf nodes 'gr' we get "rgeat"
         *
         *     rgeat
         *    /    \
         *   rg    eat
         *  / \    /  \
         * r   g  e   at
         *            / \
         *           a   t
         *
         *     rgtae
         *    /    \
         *   rg    tae
         *  / \    /  \
         * r   g  ta  e
         *        / \
         *       t   a
         *
         *
         * So tell us if "rgeat" "rgtae" is a scramble string of "great".
         * Also "great" is also a scramble string of "great" when we choose to not do any swaps.
         * This might not make sense for the whole string but definitely for substring like :
         *
         * "g|reat"       "reat|g"
         * here just "g" is swaped but not "reat".
         *
         * Okay so now we know the problem, let's check some similarity. Since we have to try to represent it on every index
         *          great
         *         /     \
         *        g      reat.... and so on.......
         * Now if we think carefully we do something similar in MCM (where we try all possible grouping at each index).
         * Also there is a constraint in the question that "we may represent it as a binary tree by partitioning it to two
         * "[non-empty]" substrings recursively. Non Empty is of essence here so we can't divide string into non empty on
         * either side of a tree.
         *
         */

        // Generic Base Conditions.
        // If length of input String is not similar they can never be a substring of each other.
        if (B.length() != A.length()) return false;
        if (A.compareTo(B) == 0)
            return true; // Both are already equal, so they are scramble strings without any swap.
        if (A.length() == 0 || B.length() == 0)
            return false; // if either of them is empty they can't be scramble strings.

        // Memorization
        memorization = new HashMap<>();
        // Now at-least input is valid and a testable input.
        return isScrambleStrings(A, B);
    }

    private static boolean isScrambleStrings(String A, String B) {
        String key = A + ":" + B;
        if (memorization.containsKey(key)) return memorization.get(key);
        /**
         * // Smallest valid input, when both input are equal....
         *-----------------------/
         *   Base condition     /
         *---------------------/
         */
        if (A.compareTo(B) == 0) {
            memorization.put(key, true);
            return true;
        }

        /**
         * Okay now we have input which is not equal, so we have to try to divide them at i=0....to i < A.length()-1
         * why <len-1 , since we have to keep at-least 1 child on either side so we will try upto only at the last character.
         */
        boolean scrambleStrings = false; // Initially assume they are not.
        int length = A.length(); // -1 because 0 based index.

        for (int i = 1; i < length; i++) {
            /**
             * Now when we are swapping strings:
             *
             *          G R E A T       ----->   T E A R G
             *          0 1 2 3 4                0 1 2 3 4
             * if I Swap at i = 1
             *
             *             GREAT       ------->  TEARG
             *            /    \                 /    \
             *           GR    EAT             TEA    RG
             *
             *  We have to compare
             *              (GR)EAT     --with-->  TEA(RG)   i.e GR of A is A scramble String of RG in B.
             *  and         GR(EAT)    ---with---> (TEA)RG   i.e. EAT is a Scramble string of TEA.
             */

            /**
             * When we are not swapping the Strings.
             *
             * then at that time we have to compare:
             *             GREAT     ------>      GTEAR
             *
             *             compare (G)REAT ----with---->  (G)TEAR
             *             and compare G(REAT) --with---> G(TEAR).
             */
            if (    // When Doing the Swap.
                    (isScrambleStrings(A.substring(0, i), B.substring(length - i))
                            &&
                            isScrambleStrings(A.substring(i, length), B.substring(0, length - i)))
                            ||
                            // When not doing the swap.
                            (isScrambleStrings(A.substring(0, i), B.substring(0, i))
                                    &&
                                    isScrambleStrings(A.substring(i, length), B.substring(i, length)))) {
                scrambleStrings = true;
                break;
            }
        }
        memorization.put(key, scrambleStrings);
        return scrambleStrings;
    }
}
