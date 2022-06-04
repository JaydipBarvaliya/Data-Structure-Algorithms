package com.leetcode.year_2020.DP.minimum_maximum_path_to_reach_to_target;

import com.util.LogUtil;

/**
 * https://leetcode.com/problems/2-keys-keyboard/
 *
 * @author neeraj on 06/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class TwoKeysKeyboard {

    public static void main(String[] args) {
        for (int i = 2; i <= 30; i++) {
            LogUtil.logIt("Minimum Steps needed to get [" + i + "] A's in the System is :---> " + minSteps(i));
        }
    }

    public static int minSteps(int n) {
        /**
         * Solution inspired by fun4leetcode
         * https://leetcode.com/problems/2-keys-keyboard/discuss/105932/Java-solutions-from-naive-DP-to-optimized-DP-to-non-DP
         *
         * What facts do we know.
         * 1) last operation can never be copy... (if you keep copying how will occurrence of `A` will increase)
         * 2) For achieving 1 'A' we need 0 step...since we already have 1  occurrence 'A' in the system
         * 3) Can we have just paste operation ? "NO" what will we paste if we don't copy.
         *
         *
         * Now lets assume T[k] represents number of steps needed to achieve k occurrence of 'A'
         * Assume we have i occurrence of 'A's in the clipboard, How many paste operation we need
         * to achieve k occurrence of 'A's.
         *
         * [(k - i)(number of 'A' left to be filled] / i (number of 'A' we already have)
         *
         * So for Example we have i=3 'A's and we have to achieve i=12 'A's
         * How many paste operation is needed to achieve that
         *    (12 - 3)/3 = 3... paste operation. Also we know you can't paste unless you copy
         *
         * So total operation when we have i 'A's to achieve K 'A's is
         *    1(operation to copy those i 'A's) +  (k - i)/i
         *
         *   which simplifies to k/i operation.
         *
         * But question is asking to minimize the steps so not every time you can simply add 1.
         * instead we need minimum steps at ith position as well
         * which makes our minSteps formulae = (T[i] + k/i).
         *
         * and we have to get minimum.
         *
         * So T[k] = Math.min(T[k], T[i] + k/i) for all 1 <= i < k && k % i == 0
         */

        int T[] = new int[n + 1];
        for (int k = 2; k <= n; k++) {
            T[k] = Integer.MAX_VALUE; // since we have to get min steps.

            for (int i = 1; i < k; i++) {
                if (k % i != 0) continue; // because steps can't be fractional
                T[k] = Math.min(T[k], T[i] + k / i);
            }
        }
        return T[n];
    }
}
