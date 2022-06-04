package com.datastructures.array;

/**
 * Created by jaine03 on 08/06/17.
 */
public class MaxIncreasingDecreasing {

    public static void main(String[] args) {
        //int []sample = new int[]{8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1};
        //int []sample = new int[] {1, 3, 50, 10, 9, 7, 6};
        int []sample = new int[]{10, 20, 30, 40, 50};
        //int []sample = new int[] {120, 100, 80, 20, 0};
        System.out.println(getMaxIncreasedValueUsingBinarySearch(sample));
    }


    // Solution Using Linear Search
    public static int getMaxIncreasedValue(int [] sample){
        int maxValue = sample[0];
        for(int i=1 ;i < sample.length ;i++)
        {
            if(sample[i] > maxValue)
            {
                maxValue = sample[i];
            }
        }
        return maxValue;
    }

    // Solution using Binary Search
    public static int getMaxIncreasedValueUsingBinarySearch(int []sample){
        int left = 0;
        int right = sample.length-1;
        int midValue = 0;

        while (left < right)
        {
            int midPoint = left + (right - left)/2;
            midValue = sample[midPoint];
            if(midValue > sample[midPoint+1] && midValue > sample[midPoint-1])
            {
                return midValue;
            }

            else if(midValue > sample[midPoint+1] &&  midValue < sample[midPoint-1])
            {
                right = midPoint-1;
            }

            else if(midValue < sample[midPoint+1] &&  midValue > sample[midPoint-1])
            {
                left = midPoint+1;
            }
        }
        return sample[right];
    }
}
