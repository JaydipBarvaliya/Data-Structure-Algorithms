package com.leetcode.year_2020.DP.longest_common_subsequence;

import com.util.LogUtil;

/**
 * @author neeraj on 07/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumNumberOfInsertionsAndDeletionToTransformOneStringToAnother {

    public static void main(String[] args) {
        findMinimumNumberOfInsertionsAndDeletion("HEAP", "PEA");
        findMinimumNumberOfInsertionsAndDeletion("geeksforgeeks", "geeks");
    }

    public static void findMinimumNumberOfInsertionsAndDeletion(String X, String Y) {
        /**
         * The Idea behind this is
         * X ==> H E A P     Y ==> PEA
         *
         * We need 1 Insertions and 2 deletions in X to convert to Y
         * How?
         * "H" is not present in Y hence we have to "delete" it,
         *
         * [E A] is common in both so let's leave it.
         *
         * "P" it is present in Y as well but the issue is it's not at the correct position,
         * if we choose to Keep it, X will become :===> E A P , where as we want P E A
         *
         * So we have to delete P as well from X ====which leads to ====>  [E A]. now we can add "P" so it will become
         * [P E A]
         *
         * Now if you notice what amount of work we don't have to do at all, it's that Longest Common Subsequence.
         * we know at-least this much is common so let's not touch or disrupt it.
         *
         * So Algorithm is
         *  Min Deletions = Length(X) - Length of LCS .  [This is because whatever is left after LCS in X is not useful for Y]
         *  Min Additions = Length(Y) - Length of LCS .  [This is because apart from LCS X can't provide any additional information]
         *
         *  Visual Representation
         *
         *  -----> [What we want to achieve]
         *  =====> [Represents current transformation]
         *
         *     X ---------------------> Y
         *      \\    First Convert    /\
         *       \\    X to LCS       //
         *        \\    then LCS     //
         *         \\    to Y       //
         *          \\==========> LCS
         *
         * With Example : Converting HEAP to EA (LCS) 2 deletions, then finally converting EA(LCS) to Y using 1 addition (PEA)
         *     HEAP ------------------>PEA
         *      \\    First Convert    /\
         *       \\    HEAP to LCS EA //
         *        \\    then LCS EA  //
         *         \\    to PEA     //
         *          \\==========> EA
         *
         */
        int lcsLength = LengthOfLongestCommonSubsequence.findLengthOfLCS(X, Y);
        LogUtil.logIt("Min Deletions " + (X.length() - lcsLength));
        LogUtil.logIt("Min Additions " + (Y.length() - lcsLength));
    }
}
