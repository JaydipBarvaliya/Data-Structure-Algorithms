package com.geeksforgeeks.array;

public class RearrangeMaximumAndMinimumWithoutExtraSpace {

    public static void main(String[] args) {
        rearrangeMaxAndMinWithoutExtraSpace(new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    public static void rearrangeMaxAndMinWithoutExtraSpace(int[] sortedArr) {

        int leftIndex = 0;
        int rightIndex = sortedArr.length - 1;
        int maxElement = sortedArr[rightIndex] + 1;

        for (int i = 0; i < sortedArr.length; i++) {

            if (i % 2 == 0) { // Maximum at Even Position
                sortedArr[i] += (sortedArr[rightIndex--] % maxElement) * maxElement;
            } else { // Minimum at Odd Position
                sortedArr[i] += (sortedArr[leftIndex++] % maxElement) * maxElement;
            }
        }
        for (int i = 0; i < sortedArr.length; i++) {
            sortedArr[i] = sortedArr[i] / maxElement;
        }
        ArrayRotation.printArray(sortedArr);
    }
}
