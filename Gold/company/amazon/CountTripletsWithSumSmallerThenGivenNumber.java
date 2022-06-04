package com.company.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountTripletsWithSumSmallerThenGivenNumber {

    public static void main(String[] args) {
//        int[] arr = {5, 1, 3, 4, 7};
        int[] arr = {-2, 0, 1, 3};
        System.out.println(getTripletsCount(arr, 2));
    }

    public static int getTripletsCount(int[] arr, int sum) {
        Arrays.sort(arr);
        List<List<Integer>> triplets = new ArrayList<>();
        int answer = 0;
        for (int i = 0; i < arr.length - 2; i++) { // (Num-2) because we are finding triplet
            // Pointers to help us find triplet like finding a pair for a particular sum
            int left = i + 1, right = arr.length - 1;

            // Meet in the middle technique
            while (left < right) {
                if ((arr[i] + arr[left] + arr[right]) >= sum) {
                    right--;
                } else {
                    answer += (right - left);
                    left++;
                }
            }
        }
        return answer;
    }
}
