package com.datastructures.mustDoInterviewQuestions.array;

/**
 * Created by jaine03 on 31/08/17.
 */
public class MaximumOfAllSubArraysOfSizeK {

    public static void main(String[] args) {
        printMaxOfAllSubArrays(new int[]{1, 3, 2, 1, 5, 4, 2, 3, 6,1},3);
    }

    public static void printMaxOfAllSubArrays(int []arr,int k){
        Integer MAX_VALUE = Integer.MIN_VALUE;
        Boolean allValuesPrinted = false;
        for(int i=0,counter = 1;i<arr.length;i++,counter++){
            allValuesPrinted = false;
            if(arr[i] > MAX_VALUE){
                MAX_VALUE = arr[i];
            }
            if(counter == k){
                System.out.println(MAX_VALUE);
                counter = 0;
                MAX_VALUE = Integer.MIN_VALUE;
                allValuesPrinted = true;
            }
        }

        if(!allValuesPrinted){
            System.out.println(MAX_VALUE);
        }
    }
}
