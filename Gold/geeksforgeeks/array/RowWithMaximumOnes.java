package com.geeksforgeeks.array;

public class RowWithMaximumOnes {

    public static void main(String[] args) {
        int mat[][] = {{0, 0, 0, 1},
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 0, 0, 0}};

        System.out.println(getRowWithMaximumOnesOptimized(mat));
    }

    public static int getRowWithMaximumOnes(int[][] data) {
        int rowWithMaxOnes = 0;
        int maxOnes = 0;
        for (int i = 0; i < data.length; i++) {

            int indexOfFirstOne = indexOfFirstOne(data[i], 0, data.length - 1);
            if (indexOfFirstOne != -1 && maxOnes < (data.length - indexOfFirstOne)) {
                maxOnes = data.length - indexOfFirstOne;
                rowWithMaxOnes = i;
            }
        }
        return rowWithMaxOnes;
    }

    public static int getRowWithMaximumOnesOptimized(int[][] data) {
        int rowWithMaximumOnes = 0;
        int indexOfFirstOne = indexOfFirstOne(data[0], 0, data.length - 1);
        int maxOnes = data.length - indexOfFirstOne;

        for (int i = 1; i < data.length; i++) {

            if (indexOfFirstOne != -1 && data[i][indexOfFirstOne] == 1) {
                indexOfFirstOne = indexOfFirstOne(data[i], 0, data.length - 1);

                if (indexOfFirstOne != -1 && maxOnes < data.length - indexOfFirstOne) {
                    maxOnes = data.length - indexOfFirstOne;
                    rowWithMaximumOnes = i;
                }
            }
        }
        return rowWithMaximumOnes;
    }

    public static int indexOfFirstOne(int[] data, int low, int high) {
        if (low <= high) {

            int mid = (low + high) / 2;

            if ((mid == 0 || data[mid - 1] == 0) && data[mid] == 1) {
                return mid;
            } else if (data[mid] == 0) {
                return indexOfFirstOne(data, mid + 1, high);
            } else {
                return indexOfFirstOne(data, low, mid - 1);
            }
        }
        return -1;
    }
}
