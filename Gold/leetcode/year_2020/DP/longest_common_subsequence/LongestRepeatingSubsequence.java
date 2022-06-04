package com.leetcode.year_2020.DP.longest_common_subsequence;

import com.util.LogUtil;

/**
 * Same problem
 * https://leetcode.com/problems/longest-duplicate-substring/
 *
 * @author neeraj on 08/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LongestRepeatingSubsequence {

    public static void main(String[] args) {
        System.out.println(findLengthOfLongestRepeatingSubsequence("AABEBCDD"));
        System.out.println(findLengthOfLongestRepeatingSubsequence("AABABCD"));
        System.out.println(findLengthOfLongestRepeatingSubsequence("axxxy"));
        System.out.println(findLengthOfLongestRepeatingSubsequence("BANANA"));
//        System.out.println(findLengthOfLongestRepeatingSubsequence("moplvidmaagmsiyyrkchbyhivlqwqsjcgtumqscmxrxrvwsnjjvygrelcbjgbpounhuyealllginkitfaiviraqcycjmskrozcdqylbuejrgfnquercvghppljmojfvylcxakyjxnampmakyjbqgwbyokaybcuklkaqzawageypfqhhasetugatdaxpvtevrigynxbqodiyioapgxqkndujeranxgebnpgsukybyowbxhgpkwjfdywfkpufcxzzqiuglkakibbkobonunnzwbjktykebfcbobxdflnyzngheatpcvnhdwkkhnlwnjdnrmjaevqopvinnzgacjkbhvsdsvuuwwhwesgtdzuctshytyfugdqswvxisyxcxoihfgzxnidnfadphwumtgdfmhjkaryjxvfquucltmuoosamjwqqzeleaiplwcbbxjxxvgsnonoivbnmiwbnijkzgoenohqncjqnckxbhpvreasdyvffrolobxzrmrbvwkpdbfvbwwyibydhndmpvqyfmqjwosclwxhgxmwjiksjvsnwupraojuatksjfqkvvfroqxsraskbdbgtppjrnzpfzabmcczlwynwomebvrihxugvjmtrkzdwuafozjcfqacenabmmxzcueyqwvbtslhjeiopgbrbvfbnpmvlnyexopoahgmwplwxnxqzhucdieyvbgtkfmdeocamzenecqlbhqmdfrvpsqyxvkkyfrbyolzvcpcbkdprttijkzcrgciidavsmrczbollxbkytqjwbiupvsorvkorfriajdtsowenhpmdtvamkoqacwwlkqfdzorjtepwlemunyrghwlvjgaxbzawmikfhtaniwviqiaeinbsqidetfsdbgsydkxgwoqyztaqmyeefaihmgrbxzyheoegawthcsyyrpyvnhysynoaikwtvmwathsomddhltxpeuxettpbeftmmyrqclnzwljlpxazrzzdosemwmthcvgwtxtinffopqxbufjwsvhqamxpydcnpekqhsovvqugqhbgweaiheeicmkdtxltkalexbeftuxvwnxmqqjeyourvbdfikqnzdipmmmiltjapovlhkpunxljeutwhenrxyfeufmzipqvergdkwptkilwzdxlydxbjoxjzxwcfmznfqgoaemrrxuwpfkftwejubxkgjlizljoynvidqwxnvhngqakmmehtvykbjwrrrjvwnrteeoxmtygiiygynedvfzwkvmffghuduspyyrnftyvsvjstfohwwyxhmlfmwguxxzgwdzwlnnltpjvnzswhmbzgdwzhvbgkiddhirgljbflgvyksxgnsvztcywpvutqryzdeerlildbzmtsgnebvsjetdnfgikrbsktbrdamfccvcptfaaklmcaqmglneebpdxkvcwwpndrjqnpqgbgihsfeotgggkdbvcdwfjanvafvxsvvhzyncwlmqqsmledzfnxxfyvcmhtjreykqlrfiqlsqzraqgtmocijejneeezqxbtomkwugapwesrinfiaxwxradnuvbyssqkznwwpsbgatlsxfhpcidfgzrc"));
        System.out.println(findLengthOfLongestRepeatingSubsequence("GEEKSFORGEEKS"));
        System.out.println(findLengthOfLongestRepeatingSubsequence("aab"));
        System.out.println(findLengthOfLongestRepeatingSubsequence("aaa"));
        System.out.println(findLengthOfLongestRepeatingSubsequence("aabaabaaba"));
        System.out.println(findLengthOfLongestRepeatingSubsequence("AABCABB"));
    }

    public static int findLengthOfLongestRepeatingSubsequence(String str) {
        /**
         * What is a LRS(Longest Repeating Subsequence).
         * Str ==> A A B E B C D D
         *
         * If you notice carefully A B D is the longest repeating subsequence. (why? we have 2 instances of this and
         * in the same order as well).
         *
         * Now how are we calculating this, if we take LCS of Str with itself LCS(str, str)
         * we will get exactly  [A A B E B  C  D  D] and our LRS will be [A B D] but interesting entries are E and C
         *                            /\   /\
         *                            ||   ||
         *                            ||   ||
         *                            ||   ||
         *                            These both entries appear just once, now they can be a part of just 1 subsequence
         *                            not in both, hence we can't include them in out LRS calculation.
         *
         * OK Mr. Neeraj we got that but how do you plan to not choose them,
         * Neeraj :==> simple we know in both LCS(str, str) they exist in at same location i.e m == n for [E and D]
         * so we will ignore (m == n).
         *
         * Perfect, but hey Mr. Neeraj what about A A B B D D they also come at same location in both matrix,are we saying
         * we will not include them.
         * that's exactly what i am saying, but the twist is A A comes twice once at 0th index and other time at 1th index
         * hence if we are skipping m == n == 0th entry for A, then we are bound to choose (m=0) != (n=1)th entry.
         *
         * So conclusion if a value appear twice it's
         *    0        1
         * 0 [A       A]
         *
         * 0 [A       A]    ====> we will skip same m and n value but A == A can happen when LCS(m, n-1) || LCS(m-1, n)
         *  So both A are contributing to different subsequence not the same as the case was with E and C which we ignored
         *  from LRS.
         */
        return LCSWithRestrictionOfLRSTopDown(str.toCharArray(), str.toCharArray());
    }

    private static int LCSWithRestrictionOfLRSTopDown(char[] X, char[] Y) {
        int[][] dp = new int[X.length + 1][Y.length + 1];

        // Initialization
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (X[i - 1] == Y[j - 1] && i != j) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        String longestRepeatingSubsequence = findLRSFromMatrix(dp, X, Y);
        LogUtil.logIt("Longest Repeating Subsequence is " + longestRepeatingSubsequence);
        LogUtil.printMultiDimensionArray(dp);
        return dp[X.length][Y.length];
    }

    private static String findLRSFromMatrix(int[][] dp, char[] x, char[] y) {
        // Start from Bottom Right corner.
        int i = dp.length - 1;
        int j = dp[0].length - 1;
        StringBuilder builder = new StringBuilder();
        while (i > 0 && j > 0) {
            if (x[i - 1] == y[j - 1] && i != j) {
                builder.append(x[i - 1]);
                i--;
                j--;
            } else {
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }
        return builder.reverse().toString();
    }
}
