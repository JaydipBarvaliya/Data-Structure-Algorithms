package com.datastructures.mustDoInterviewQuestions.array;

/**
 * Created by jaine03 on 22/07/17.
 */
public class SlidingWindowMaximum {

    public static void main(String[] args) {
        int []arr = new int[]{1, 2, 3, 1, 4, 5, 2, 3, 6};
        int k = 3;
        for(int i=0;i<=(arr.length- (k+1));i++){
            System.out.println(getMax(arr,i,i+k-1));
        }
    }

    public static int getMax(int []arr,int start,int end){
        int maxValue = 0;
        for(int i=start;i<=end;i++){
            if(arr[i]>maxValue){
                maxValue = arr[i];
            }
        }
        return maxValue;
    }
}
