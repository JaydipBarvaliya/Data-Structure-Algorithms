package com.datastructures.mustDoInterviewQuestions.array;

/**
 * Created by jaine03 on 22/07/17.
 */
public class SortZeroOneAndTwo {

    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        sortZeroOneAndTwo(arr);
        SegregateZeroAndOnes.printArray(arr);
        sortZeroOneAndTwo(arr);
        SegregateZeroAndOnes.printArray(arr);

        int [] arr1 = new int[]{0,1,0,1,0,1,0};
        segregate0And1(arr1);
        SegregateZeroAndOnes.printArray(arr1);
    }

    public static void sortZeroOneAndTwo(int[] arr) {
        int low = 0;
        int mid = 0;
        int high = arr.length - 1;

        while (mid <= high) {
            switch (arr[mid]) {
                case 0: {
                    int temp = arr[mid];
                    arr[mid] = arr[low];
                    arr[low] = temp;
                    mid++;
                    low++;
                    break;
                }
                case 1:
                    mid++;
                    break;
                case 2: {
                    int temp = arr[mid];
                    arr[mid] = arr[high];
                    arr[high] = temp;
                    high--;
                    break;
                }
            }
        }
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
