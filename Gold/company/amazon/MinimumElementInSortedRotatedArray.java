package com.company.amazon;

public class MinimumElementInSortedRotatedArray {

    public static void main(String[] args) {
        System.out.println(getMaximumElement(new int[]{5, 6, 1, 2, 3, 4}));
        System.out.println(getMaximumElement(new int[]{1, 2, 3, 4}));
        System.out.println(getMaximumElement(new int[]{2, 1}));
    }

    public static int getMaximumElement(int[] sortedArr) {
        int low = 0;
        int high = sortedArr.length - 1;
        if (sortedArr[low] < sortedArr[high]) {
            return sortedArr[low];
        }

        while (low < high) {
            int mid = low + (high - low) / 2;

            //Checking for the case when mid+1 can be the minimum
            if(sortedArr[mid+1] < sortedArr[mid]) {
                return sortedArr[mid+1];
            }

            if (sortedArr[mid] < sortedArr[mid - 1]) {
                return sortedArr[mid];
            }

            if (sortedArr[mid] < sortedArr[high]) { // mid< high, So the min element definitely lies in left half
                high = mid - 1;
            } else { // mid > high, So definitely min element lies in the right side
                low = mid + 1;
            }
        }
        return -1;
    }
}
