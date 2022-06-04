package com.leetcode.year_2020.DP;

/**
 * 552. Student Attendance Record II
 * https://leetcode.com/problems/student-attendance-record-ii/
 */
public class StudentAttendence {

    public static void main(String[] args) {
        System.out.println(checkRecord(10));
        System.out.println(checkRecord(100));
    }

    static int MOD = 1000000000 + 7;
    static Integer[][][] dp;

    public static int checkRecord(int n) {
        dp = new Integer[n + 1][2][3];
        return possibleAttendence(n, 0, 0);
    }

    // Returns the number of ways to create a string of length n with characters 'A', 'L', 'P' while having <=1 total 'A's and <=2 consecutive 'L's.
    public static int possibleAttendence(int n, int totalAbsent, int continuousLate) {
        if (n == 0) return 1; // There is just 1 way to create string of length 0 that's not selecting anything.
        if (dp[n][totalAbsent][continuousLate] != null) return dp[n][totalAbsent][continuousLate];
        int result = 0;
        if (totalAbsent == 0) {
            // Choosing to be absent on this day
            result += possibleAttendence(n - 1, totalAbsent + 1, 0);
            result %= MOD;
        }

        if (continuousLate < 2) {
            // Choosing to be late on this day, since we are under limit
            result += possibleAttendence(n - 1, totalAbsent, continuousLate + 1);
            result %= MOD;
        }

        // Choosing to be present on this day
        result += possibleAttendence(n - 1, totalAbsent, 0);
        result %= MOD;

        dp[n][totalAbsent][continuousLate] = result;
        return result;
    }
}
