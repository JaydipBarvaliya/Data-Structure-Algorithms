package com.datastructures.mustDoInterviewQuestions.array;

/**
 * http://www.geeksforgeeks.org/must-do-coding-questions-for-companies-like-amazon-microsoft-adobe/#arrays
 * Created by jaine03 on 22/07/17.
 */
public class LeaderIntheArray {

    public static void main(String[] args) {
        printAllLeaders(new int[]{16, 17, 4, 3, 5, 2});
    }

    public static void printAllLeaders(int []array){
        int maxTillNow = 0;
        int n = array.length-1;
        for(int i=n ;i >=0; i--){
            if(array[i] > maxTillNow){
                System.out.print(array[i]+",");
                maxTillNow = array[i];
            }
        }
        System.out.println();
    }
}
