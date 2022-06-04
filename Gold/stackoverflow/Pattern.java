package com.stackoverflow;

/**
 * 1 2 3 4 5 4 3 2 1
 * 1 2 3 4 3 2 1
 * 1 2 3 2 1
 * 1 2 1
 * 1
 */
public class Pattern {

    public static void main(String[] args) {
        int N = 9;
        int i, j, k;

        for (i = 1; i <= N; i++) {
            for (int space = 0; space < i - 1; space++) {
                System.out.print("\t");
            }
            for (j = 1; j <= N - i; j++) {
                System.out.print(j + "\t");
            }
            for (k = j; k >= 1; k--) {
                System.out.print(k + "\t");
            }
            System.out.println();
        }
    }
}
