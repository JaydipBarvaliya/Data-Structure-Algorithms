package com.company.facebook;

import java.util.Random;

/**
 * https://leetcode.com/problems/random-pick-index/
 * <p>
 * Probability.
 */
public class RandomPickIndex {

    static class Solution {

        private Random random;
        private int[] nums;

        public Solution(int[] nums) {
            random = new Random();
            this.nums = nums;
        }

        /**
         * https://www.youtube.com/watch?v=1rDRyxPcmvY
         * <p>
         * https://leetcode.com/problems/random-pick-index/discuss/88072/Simple-Reservoir-Sampling-solution/580888
         *
         * @param target
         * @return
         */
        public int pick(int target) {
            int res = 0, count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != target) continue;
                if (random.nextInt(++count) == 0) {
                    res = i;
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution(new int[]{1, 2, 3, 3, 3});
        System.out.println(solution.pick(3));
        System.out.println(solution.pick(1));
        System.out.println(solution.pick(3));
        System.out.println(solution.pick(3));
    }
}
