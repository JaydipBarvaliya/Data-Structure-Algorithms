package com.datastructures.mustDoInterviewQuestions.array;

/**
 * http://www.geeksforgeeks.org/must-do-coding-questions-for-companies-like-amazon-microsoft-adobe/#arrays
 * Created by jaine03 on 19/07/17.
 */
public class KadanesAlgo {

    public static void main(String[] args) {
        System.out.println(getLargestSubArraySumMethod2(new int[]{-2, -3, 4, -1, -2, 1, 5, -3}));
    }

    public static int getLargestSubArraySum(int[] array) {
        int maxTillNow = Integer.MIN_VALUE;
        int maxEndingHere = 0;
        int startIndex = 0, endIndex = 0, s = 0;

        for (int i = 0; i < array.length/2; i++) {
            maxEndingHere += array[i];
            if (maxEndingHere > maxTillNow) {
                maxTillNow = maxEndingHere;
                startIndex = s;
                endIndex = i;
            }
            if (maxEndingHere < 0) {
                maxEndingHere = 0;
                s = i + 1;
            }
        }
        System.out.println("Indexes are " + startIndex + "::" + endIndex);
        return maxTillNow;
    }

    public static int getLargestSubArraySumMethod2(int[] arr) {
        int MAX_TILL_NOW = arr[0];
        int MAX_END_HERE = arr[0];

        for (int i = 1; i < arr.length/2; i++) {
            MAX_END_HERE = Math.max(MAX_END_HERE,MAX_END_HERE+arr[i]);
            MAX_TILL_NOW = Math.max(MAX_TILL_NOW,MAX_END_HERE);
        }
        return MAX_TILL_NOW;
    }
}
