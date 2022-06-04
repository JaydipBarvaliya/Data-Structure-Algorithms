package com.leetcode.year_2020.all_premium.tags.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
 */
public class PairOfSongsDivisibleBy60 {

    public static void main(String[] args) {
//        System.out.println(numPairsDivisibleBy60(new int[]{30, 20, 150, 100, 40}));
//        System.out.println(numPairsDivisibleBy60(new int[]{60, 60}));
        System.out.println(numPairsDivisibleBy60(new int[]{60, 60, 60}));
        System.out.println(numPairsDivisibleBy60(new int[]{60, 60, 60, 60}));
    }

    public static int numPairsDivisibleBy60(int[] time) {
        /**
         * So basically we can use the same logic to find pairs where their sum is divisible by k
         *
         * Assume number K = 7
         *
         * First = 44               Second = 19
         *          |                         |     (Let's take remainder by k)
         *       44 % 7 = 2                 19 % 7 = 5
         *
         * As you know already 44+19 = 63 is divisible by K but why is that
         * Since 44 is 7*(6) + 2    and 19 is 7*(2) + 5
         *
         * So did you notice anything 44 is 7*m+2   and 19 is 7*n+5
         *
         * Add them (7*m+7*n+7) all have 7 in common so they are definitely divisible by 7
         *
         *      Taking common out 7*(m+n+1)
         *
         * We can also write 44 is 7*m+2    and 19 is 7*n+(7-2)  -----> Notice 2 is remainder from 44
         *
         * So Neeraj what intuition are you getting with this that
         * 2 pair can only be divisible by k their remainder have to sum upto either 0 or K
         *
         * When can 0 happen assume Pair(14,21) are these divisible by 7
         *
         * 14 is 7*2+0 and 21 is 7*3+0, So sum(0,0) is 0
         *
         * Hence i said sum(remainder) can either be 0 of k
         */
        final Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i : time) {
            int remainder = i % 60;
            map.put(remainder, map.getOrDefault(remainder, 0) + 1);
        }


        /**
         *  Assume having numbers [10, 20, 30, 40, 50, 60, 70, 110, 130]
         *  So remainder 10 -> 3 (1 from actual 10 and another from 70 and 130)
         *               20 -> 1
         *               30 -> 1
         *               40 -> 1
         *               50 -> 2 (1 from actual 50 and another from 110)
         *               0  -> 1
         *
         *  So now (10, 50, 70, 110, 130) can make up-to how many pairs divisible by 60
         *
         *  My wild guess (3*2 = 6), How (10+50), (10+110), (50+70), (50+130), (70+110), (110+130)
         *
         *  Now the special case we have the frequencies how do we handle pair, 10 make pair with 50 and 50 also make pair with 10
         *  so that will be duplicate, so we will only consider freq till 0-30 since after that whatever will be there is just subtraction
         *  from 60.
         *
         *  Also for 0 and 30 remainder from modulo 60 operation this can only happen when they make pair with them self
         *
         *  Asssume           [60, 60, 60, 60]
         *  How many pairs 6, 1st with all remaining
         *                         2nd with all remaining
         *                              3rd with all remaining
         *                                  4th will all remaining
         *  that will be N*(N+1)/2 ==> 4*(3)/2 ===> 6 total pairs
         */
        for (int i = 1; i <= 29; i++) { // Skipping 0 and 29
            int otherHalfOfRemainder = 60 - i;
            count += map.getOrDefault(i, 0) * map.getOrDefault(otherHalfOfRemainder, 0);
        }

        int countOfZeroRemainder = map.getOrDefault(0, 0);
        int countOfThirtyRemainder = map.getOrDefault(30, 0);
        if (countOfZeroRemainder > 1) {
            // Only then it will make up a pair
            count += (countOfZeroRemainder * (countOfZeroRemainder - 1)) / 2;
        }
        if (countOfThirtyRemainder > 1) {
            // Only then it will make up a pair
            count += (countOfThirtyRemainder * (countOfThirtyRemainder - 1)) / 2;
        }
        return count;
    }

}
