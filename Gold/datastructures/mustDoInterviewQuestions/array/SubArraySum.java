package com.datastructures.mustDoInterviewQuestions.array;

/**
 * http://www.geeksforgeeks.org/must-do-coding-questions-for-companies-like-amazon-microsoft-adobe/#arrays
 * Created by jaine03 on 22/07/17.
 */
public class SubArraySum {

    public static void main(String[] args) {
        subArraySumInOnComplexity(new int[]{15, 2, 4, 8, 9, 5, 10, 23},23);
    }

    public static void subArraySum(int[] array, int sum) {
        int totalSum = 0;
        for(int i=0;i<array.length;i++){
            totalSum = array[i];
            for(int j=i+1;j<array.length;j++){
                totalSum += array[j];
                if(totalSum > sum){
                    break;
                }
                if(totalSum == sum){
                    System.out.println("Sum found at "+ i + "::"+j);
                    break;
                }
            }
            if(totalSum == sum){
                break;
            }
        }
    }

    public static void subArraySumInOnComplexity(int []array,int sum){
        int CURR_SUM = array[0];
        int start = 0;

        for(int i=1;i<array.length;i++){

            while (CURR_SUM > sum ){
                CURR_SUM = CURR_SUM - array[start];
                start++;
            }
            if(CURR_SUM == sum){
                System.out.println("Found Sum at "+start+ " :: "+(i-1));
                break;
            }

            CURR_SUM+= array[i];
        }
    }
}
