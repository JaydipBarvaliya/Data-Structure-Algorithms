package com.geeksforgeeks.array;

/**
 * Find the element that appears once
 * iDeserve Youtube good explanation
 */
public class FindElementThatOccursOnlyOnce {

    public static void main(String[] args) {
        System.out.println(getElementOccursOnlyOnce(new int[]{12, 1, 12, 3, 12, 1, 1, 2, 3, 3}, 3, 4));
    }

    /**
     * @param arr
     * @param N:  The number of times other elements have been repeated
     * @return
     */
    public static int getElementOccursOnlyOnce(int[] arr, int N, int sizeOfBinary) {
        int[] binRepresentationSum = new int[sizeOfBinary];
        int[] binRepresentation = new int[sizeOfBinary];

        for (int a : arr) {
            binRepresentation = SubSetOfArray.getBinRepresentation(a, sizeOfBinary, binRepresentationSum);
        }

        // Let's do the modulo N to check which is missing
        for (int i = 0; i < sizeOfBinary; i++) {
            binRepresentationSum[i] = binRepresentationSum[i] % N;
        }

        // Return the final Result
        int sum = 0;
        for (int i = sizeOfBinary - 1, counter = 0; i >= 0; i--, counter++) {
            sum += Math.pow(2, counter) * binRepresentationSum[i];
        }
        return sum;
    }
}
