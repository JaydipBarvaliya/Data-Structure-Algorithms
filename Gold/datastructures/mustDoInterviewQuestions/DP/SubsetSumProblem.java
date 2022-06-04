package com.datastructures.mustDoInterviewQuestions.DP;

/**
 * https://www.youtube.com/watch?v=s6FhG--P7z0&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr&index=4
 */
public class SubsetSumProblem {

    public static void main(String[] args) {
        int [] set = {2,3,7,8,10};
        System.out.println(isSubsetAvailable(set,11));
    }

    public static Boolean isSubsetAvailable(int[] set, int totalSum) {
        boolean[][] T = new boolean[set.length + 1][totalSum + 1];
        for (int i = 0; i <= set.length; i++)
            T[i][0] = true;
        print(T);
        for (int i = 1; i <= set.length; i++) {
            for (int j = 1; j <= totalSum; j++) {
                if (j >= set[i-1]){
                    T[i][j] = T[i-1][j] || T[i-1][j-set[i-1]];
                } else {
                    T[i][j] = T[i-1][j];
                }
            }
        }
        print(T);
        return T[set.length][totalSum];
    }

    public static void print(boolean[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("__________________________________");
    }
}
