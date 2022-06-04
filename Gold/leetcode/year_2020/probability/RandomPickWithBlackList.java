package com.leetcode.year_2020.probability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * https://leetcode.com/problems/random-pick-with-blacklist/
 * <p>
 * 710. Random Pick with Blacklist
 * You are given an integer n and an array of unique integers blacklist. Design an algorithm to pick a random integer in the range [0, n - 1] that is not in blacklist. Any integer that is in the mentioned range and not in blacklist should be equally likely returned.
 * <p>
 * Optimize your algorithm such that it minimizes the call to the built-in random function of your language.
 * <p>
 * Implement the Solution class:
 * <p>
 * Solution(int n, int[] blacklist) Initializes the object with the integer n and the blacklisted integers blacklist.
 * int pick() Returns a random integer in the range [0, n - 1] and not in blacklist. All the possible integers should be equally likely returned.
 */
public class RandomPickWithBlackList {

    public static void main(String[] args) {

        Solution solution = new Solution(4, new int[]{0, 2, 3});
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());

        solution = new Solution(6, new int[]{0, 2, 3});
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());


        solution = new Solution(7, new int[]{2, 3, 5});
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());
    }

    /**
     * https://www.youtube.com/watch?v=6QIbXSZ1s3Q
     * <p>
     * B = Size of BlackList array
     * N = Total Size
     * <p>
     * Example : N = 6 and blackList[]={0,2,3};
     * <p>
     * So items = [0, 1, 2,    |  3, 4, 5],
     * *              ||       |     ||
     * *              ||       |     ||
     * *            0-to-(N-B) |  (N-B to N-1) Items
     * <p>
     * Now We create TempWhitelist set which will contain only whitelisted items but we should select only items from
     * [N-B, N],
     * So initially items in the tempWhiteList[]={3,4,5}
     * <p>
     * Now we will remove all black list items from this list, tempWhiteList = {4,5} (since 3 was blackList])
     */
    static class Solution {

        final Map<Integer, Integer> remapped;
        final Random random;
        final Integer randomBound;

        public Solution(int N, int[] blacklist) {
            // First Phase (Prepare tempWhiteList)
            List<Integer> tempWhiteList = new ArrayList<>();
            for (int i = N - blacklist.length; i < N; i++) {
                tempWhiteList.add(i);
            }

            // Second Phase (Removal of blackList)
            for (Integer i : blacklist) {
                tempWhiteList.remove(i);
            }

            // Now the final phase, remapping
            remapped = new HashMap<>();
            int counter = tempWhiteList.size() - 1;
            for (int i = 0; i < blacklist.length; i++) {
                if (blacklist[i] < N - blacklist.length) {
                    remapped.put(blacklist[i], tempWhiteList.get(counter--));
                }
            }
            random = new Random();
            randomBound = N - blacklist.length;
        }

        public int pick() {
            int randomValue = random.nextInt(randomBound);
            return remapped.containsKey(randomValue) ? remapped.get(randomValue) : randomValue;
        }
    }

}
