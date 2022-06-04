package com.datastructures.mustDoInterviewQuestions.DP;

public class ZeroOneKnapSack {

    public static int getMaxValue(int[] weight, int[] val, int totalWeight) {
        int[][] T = new int[weight.length+1][totalWeight + 1];

        for (int i = 0; i <= val.length; i++) {
            if(i!=0)
            print(T);
            for (int j = 0; j <= totalWeight; j++) {
                if (i == 0 || j == 0) {
                    T[i][j] = 0;
                    continue;
                }
                if (j - weight[i-1] >= 0) {
                    T[i][j] = Math.max(T[i - 1][j], val[i-1] + T[i - 1][j - weight[i-1]]);
                } else {
                    T[i][j] = T[i - 1][j];
                }
            }
        }
        print(T);
        return T[weight.length][totalWeight];
    }

    public static void main(String[] args) {
        int[] weight = {1, 3, 4, 5};
        int[] value = {1, 4, 5, 7};
        int totalWeight = 7;

//        int value[] = {22, 20, 15, 30, 24, 54, 21, 32, 18, 25};
//        int weight[] = {4, 2, 3, 5, 5, 6, 9, 7, 8, 10};
//        int totalWeight = 30;
        System.out.println(getMaxValue(weight, value, totalWeight));
    }

    public static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("__________________________________");
    }
}
