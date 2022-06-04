package com.leetcode.year_2020.backtracking;

/**
 * Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9,
 * count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.
 * <p>
 * Rules for a valid pattern:
 * Each pattern must connect at least m keys and at most n keys.
 * All the keys must be distinct.
 * If the line connecting two consecutive keys in the pattern passes through any other keys,
 * the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
 * The order of keys used matters.
 * <p>
 * ![](/assets/android.png)
 * <p>
 * Explanation:
 * | 1 | 2 | 3 |
 * | 4 | 5 | 6 |
 * | 7 | 8 | 9 |
 * Invalid move: 4 - 1 - 3 - 6
 * Line 1 - 3 passes through key 2 which had not been selected in the pattern.
 * <p>
 * Invalid move: 4 - 1 - 9 - 2
 * Line 1 - 9 passes through key 5 which had not been selected in the pattern.
 * <p>
 * Valid move: 2 - 4 - 1 - 3 - 6
 * Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern
 * <p>
 * Valid move: 6 - 5 - 4 - 1 - 9 - 2
 * Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.
 * <p>
 * Example:
 * Given m = 1, n = 1, return 9.
 * <p>
 * https://medium.com/@rebeccahezhang/leetcode-351-android-unlock-patterns-d9bae4a8a958
 */
public class AndroidUnlockPattern {

    public static void main(String[] args) {
        System.out.println(numberOfPatterns(3, 3));
    }

    public static int numberOfPatterns(int m, int n) {
        /**
         * So first kick out special cases which are not allowed and in-order for them to be allowed
         * certain criteria should met.
         * So invalid patterns
         *  | 1 | 2 | 3 |
         *  | 4 | 5 | 6 |
         *  | 7 | 8 | 9 |
         */
        int[][] invalidPatterns = new int[10][10];
        invalidPatterns[1][3] = 2; // Meaning if you are planning to jump 1 to 3, 2 will auto-select as per movement
        // of finger hence if n=1 then your this move 1 to 3 is not valid
        invalidPatterns[3][1] = 2;
        invalidPatterns[1][7] = 4;
        invalidPatterns[7][1] = 4;
        invalidPatterns[1][9] = 5;
        invalidPatterns[9][1] = 5;
        invalidPatterns[2][8] = 5;
        invalidPatterns[8][2] = 5;
        invalidPatterns[3][9] = 6;
        invalidPatterns[9][3] = 6;
        invalidPatterns[3][7] = 5;
        invalidPatterns[7][3] = 5;
        invalidPatterns[4][6] = 5;
        invalidPatterns[6][4] = 5;
        invalidPatterns[7][9] = 8;
        invalidPatterns[9][7] = 8;

        /**
         * Now we have all invalid patterns,
         * We sum all the valid patterns when using m keys, m+1keys, … n keys together to get the result.
         * In each case, we use DFS to count the number of valid paths from the current number (1–9)to the remaining numbers.
         * To optimize, calling DFS less than 9 times, we can use the symmetry of the 3 by 3 matrix.
         * If we start from 1 or 3 or 7 or 9, the valid paths number should be the same.
         * If we start from 2 or 4 or 6 or 8, the valid paths number are also the same.
         * Start from 5 is the third case. So the total is :
         *
         * return value of DFS (start from 1) * 4 +
         * return value of DFS (start from 2) * 4 +
         * return value of DFS (start from 5)
         */

        boolean[] visited = new boolean[10];
        int res = 0;
        for (int remainingSteps = m; remainingSteps <= n; remainingSteps++) {
            res += dfs(visited, invalidPatterns, 1, remainingSteps - 1) * 4;
            res += dfs(visited, invalidPatterns, 2, remainingSteps - 1) * 4;
            res += dfs(visited, invalidPatterns, 5, remainingSteps - 1);
        }
        return res;
    }

    private static int dfs(final boolean[] visited, final int[][] invalidPatterns, final int current, final int remaining) {
        if (remaining < 0) {
            return 0;
        }
        if (remaining == 0) {
            return 1; // We reached our limit.
        }

        visited[current] = true;
        int res = 0;
        for (int i = 1; i <= 9; i++) {
            if (visited[i] || invalidPatterns[current][i] != 0 && !visited[invalidPatterns[current][i]]) {
                continue;
            }
            res += dfs(visited, invalidPatterns, i, remaining - 1);
        }
        visited[current] = false;
        return res;
    }
}
