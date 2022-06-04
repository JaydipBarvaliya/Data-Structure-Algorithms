package com.datastructures.mustDoInterviewQuestions.array;

/**
 * Created by jaine03 on 22/07/17.
 */
public class SegregateZeroAndOnes {

    public static void main(String[] args) {
        int []arr = new int[]{0,1,0,0,1,1,1,0,1,1,0,1,0,1};
        printArray(arr);
        segregate0And1(arr);
        System.out.println("After segregation");
        printArray(arr);

    }

    public static void printArray(int []arr){
        for(int i:arr){
            System.out.print(i+"\t");
        }
        System.out.println();
    }

    public static void segregate0And1(int []arr){
        int left = 0;
        int right = arr.length-1;

        while (left < right){
            while (arr[left] == 0 && left < right)
                left++;
            while (arr[right] == 1 && left < right)
                right--;

            if(left < right){
                arr[left] = 0;
                arr[right] = 1;
                left++;
                right--;
            }
        }
    }
}
