package com.datastructures.mustDoInterviewQuestions.DP;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String first = "abcdaf";
        String second = "acbcf";
        System.out.println(getLengthOfLongestCommonSubsequence(first.toCharArray(), second.toCharArray()));
    }

    public static int getLengthOfLongestCommonSubsequence(char[] first, char[] second) {
        int [][] T = new int[first.length+1][second.length+1];

        for(int i=1;i<=first.length;i++){
            for(int j=1;j<=second.length;j++){
                if(first[i-1] == second[j-1]){
                 T[i][j] = T[i-1][j-1] + 1;
                } else {
                    T[i][j] = Math.max(T[i-1][j],T[i][j-1]);
                }
            }
        }
        print(T);
        return T[first.length][second.length];
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
