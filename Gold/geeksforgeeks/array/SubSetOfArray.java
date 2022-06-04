package com.geeksforgeeks.array;

import java.util.ArrayList;
import java.util.List;

public class SubSetOfArray {

    public static void main(String[] args) {
        printAllSubSet(new int[]{2, 5, 5});
    }

    public static void printAllSubSet(int[] arr) {
        for (int i = 0; i <= Math.pow(2, arr.length); i++) {
            int[] binaryRepresentation = getBinRepresentation(i, (int) Math.pow(2, arr.length), null);
//            ArrayRotation.printArray(binaryRepresentation);
            List<Integer> subset = new ArrayList<>();

            // We will start the loop from
            // Total Bin Representation length - Actual Array length +1 to bring in optimization
            for (int j = binaryRepresentation.length - arr.length, arrCounter = 0; j < binaryRepresentation.length; j++, arrCounter++) {
                if (binaryRepresentation[j] == 1) {
                    subset.add(arr[arrCounter]);
                }
            }
            System.out.println(subset);
        }
    }

    public static int[] getBinRepresentation(int i, int maxSizeOfBinArray, int[] sumOfBinaryRepresentation) {
        int[] binRepresentation = new int[maxSizeOfBinArray];
        int num = i;
        int counter = binRepresentation.length - 1;
        List<Integer> list = new ArrayList<>();
        while (num > 0) {
            binRepresentation[counter] = num % 2;
            if (sumOfBinaryRepresentation != null) {
                sumOfBinaryRepresentation[counter] += binRepresentation[counter];
            }
            counter--;
            num = num / 2;
        }
        return binRepresentation;
    }
}
