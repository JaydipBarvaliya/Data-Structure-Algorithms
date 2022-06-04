package com.datastructures.mustDoInterviewQuestions.array;

/**
 * http://www.geeksforgeeks.org/maximum-difference-between-two-elements/
 * Created by jaine03 on 23/07/17.
 */
public class MaximumDifferenceTwoElements {

    public static void main(String[] args) {
        System.out.println(getMaximumDifference(new int[]{2, 3, 10, 6, 4, 8, 1}));
    }

    /**
     * Traverse from the right and keep maintaining MAX_RIGHT_VALUE with MAX_DIFF till now.
     * @param array
     * @return
     */
    public static int getMaximumDifference(int []array){
        int len = array.length-1;
        int MAX_RIGHT_VALUE = array[len];
        int MAX_DIFF = -1;

        for(int i=len;i>=0;i--){
            if(array[i] > MAX_RIGHT_VALUE){
                MAX_RIGHT_VALUE = array[i];
            } else {
                int diff = MAX_RIGHT_VALUE - array[i];
                if(MAX_DIFF < diff)
                    MAX_DIFF = diff;
            }
        }
        return MAX_DIFF;
    }
}
