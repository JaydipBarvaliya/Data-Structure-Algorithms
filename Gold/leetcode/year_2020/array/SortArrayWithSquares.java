package com.leetcode.year_2020.array;

import com.util.LogUtil;

public class SortArrayWithSquares {

    public static void main(String[] args) {
        LogUtil.printArray(solve(new int[]{-6, -3, -1, 2, 4, 5}));
        LogUtil.printArray(solve(new int[]{1, 4, 9, 16, 25, 36}));
    }

    public static int[] solve(int[] A) {
        int[] result = new int[A.length];
        int t1 = 0, t2 = 0, counter = 0;
        for (int i = 0; i < A.length; i++) {
            result[counter++] = A[i];
        }
        if (A[0] < 0) {
            int lastNegative = 0, firstPositive = 0;
            for (int i = 0; i < A.length; i++) {
                if (A[i] < 0) {
                    lastNegative = i;
                }
                if (A[i] >= 0) {
                    firstPositive = i;
                    break;
                }
            }
            t1 = lastNegative;
            t2 = firstPositive;
            counter=0;
            while (t1 >= 0 && t2 < A.length) {
                if (Math.abs(A[t1]) < A[t2]) {
                    result[counter++] = A[t1];
                    t1--;
                } else {
                    result[counter++] = A[t2];
                    t2++;
                }
            }

            while (t1 >= 0) {
                result[counter++] = A[t1--];
            }

            while (t2 < A.length) {
                result[counter++] = A[t2++];
            }
        }
        squareNumbers(result);
        return result;
    }

    private static void squareNumbers(final int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = a[i] * a[i];
        }
    }
}
