package com.datastructures.array;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jaine03 on 08/07/17.
 */
public class ArrayUtil {

    /**
     * @param m No of Rows
     * @param n No of Columns
     */
    public static void printArrayInSpiralForm(int[][] arr, int M, int N) {
        int k = 0; //Index of First Row
        int l = 0; //Index of First Column
        int m = M; //Index of Last Row
        int n = N; //Index of Last Column
        int i = 0;

        while (k < m && l < n) {

            // Print 1st Row
            for (i = k; i < n; i++) {
                System.out.print(arr[k][i] + ",");
            }
            k++;

            // Print last column
            for (i = k; i < m; i++) {
                System.out.print(arr[i][n - 1] + ",");
            }
            n--;

            // Print Last Row
            if (k < m) {
                for (i = n - 1; i >= l; i--) {
                    System.out.print(arr[m - 1][i] + ",");
                }
            }
            m--;

            // Print 1st Column
            if (l < n) {
                for (i = m - 1; i > k; i--) {
                    System.out.print(arr[i][l] + ",");
                }
            }
            l++;
        }

    }

    public static int getGCD(int A, int B) {
        if (A == 0 || B == 0)
            return 0;
        if (A == B)
            return A;

        if (A > B)
            return getGCD(A - B, B);
        else
            return getGCD(A, B - A);
    }

    public static void towerOfHanoi(int n, char fromRod, char toRod, char auxRod) {
        if (n == 1) {
            System.out.println("Move Disk 1 from " + fromRod + " to " + toRod);
            return;
        }
        towerOfHanoi(n - 1, fromRod, auxRod, toRod);
        System.out.println("Move Disk " + n + " from " + fromRod + " to " + toRod);
        towerOfHanoi(n - 1, auxRod, toRod, fromRod);
    }

    public static void main(String[] args) {
//        int M = 4;
//        int N = 4;
//        int a[][] = {{1, 2, 3, 4},
//                {5, 6, 7, 8},
//                {9, 10, 11, 12},
//                {13, 14, 15, 16}
//        };
//        print2DArray(a,M,N);
//        inplanceRotateMatrix(a,4);
//        print2DArray(a,M,N);

        LogUtil.printArray(plusOne(new int[]{9, 9, 9, 9, 9}));
        LogUtil.printArray(plusOne(new int[]{0, 3, 7, 6, 4, 0, 5, 5, 5}));

        //printArrayInSpiralForm(a, M, N);

        //System.out.println("GCD of 98 and 56 is " + getGCD(98, 56));

        //towerOfHanoi(1000, 'A', 'B', 'C');

//        int[] temp = getKLargestElement(new int[]{1, 2, 3, 4, 5}, 3);
//        System.out.println(temp);

//        // Get Total Set Bits.
//        int n = 6;
//        System.out.println("\nTotal Set bits for " + n + " is " + getTotalSetBits(n));
//
//
//        char[][] array =
//                {
//                        {'*', '$', '*'},
//                        {'*', '|', '*'},
//                        {'*', '|', '*'},
//                        {'*', '|', '*'}
//                };
//
//        //print2DArray(array, 4, 3);
//
//        array = turnAnImageBy90Degree(array, 4, 3);
//
//        //print2DArray(array, 3, 4);
//
//        // AmazonPrimeOrdersSorting Case 1
//        int arr[][] =
//                {
//                        {1, 2, 3, 4},
//                        {5, 6, 7, 8},
//                        {9, 10, 11, 12},
//                        {13, 14, 15, 16}
//                };
//
//        print2DArray(arr, 4, 4);
//
//        inplanceRotateMatrix(arr, N);
//
//        print2DArray(arr, 4, 4);

        //System.out.println(getLongestPalindromicSubString("forgeeksskeegfor"));
        //getDistinctPalindrome("forgeeksskeegfor");

//        long startTime = System.currentTimeMillis();
//        System.out.println(getCountUglyNumbers(500));
//        long endTime = System.currentTimeMillis();
//
//        System.out.println(startTime + "::::" + endTime);

    }

    public static int[] getKLargestElement(int[] array, int k) {
        int[] temp = new int[k];
        int minElement, minIndex;

        for (int i = 0; i < k; i++) {
            temp[i] = array[i];
        }

        for (int i = 0; i < array.length; i++) {
            minElement = Integer.MAX_VALUE;
            minIndex = -1;

            for (int j = 0; j < temp.length; j++) {
                if (temp[j] < minElement) {
                    minElement = temp[j];
                    minIndex = j;
                }
            }

            if (array[i] > minElement) {
                temp[minIndex] = array[i];
            }
        }
        return temp;
    }

    public static void print2DArray(int[][] array, int rows, int column) {
        System.out.println("========================================================================================");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static char[][] turnAnImageBy90Degree(char[][] array, int rows, int column) {
        char[][] temp = new char[column][rows];

        for (int i = 0, row = 0; i < column; i++, row++) {
            for (int j = rows - 1, col = 0; j >= 0; j--, col++) {
                temp[row][col] = array[j][i];
            }
        }
        return temp;
    }

    public static void inplanceRotateMatrix(int[][] arr, int N) {
        int temp = 0;
        for (int x = 0; x < N / 2; x++) {
            for (int y = x; y < N - 1 - x; y++) {

                //Store Top value to temp
                temp = arr[x][y];

                //Move right values to top
                arr[x][y] = arr[y][N - x - 1];

                //Move bottom values to the right
                arr[y][N - x - 1] = arr[N - x - 1][N - y - 1];

                //Move left values to bottom
                arr[N - x - 1][N - y - 1] = arr[N - 1 - y][x];

                //Move top values to left
                arr[N - 1 - y][x] = temp;
            }
        }
    }

    /**
     * http://www.geeksforgeeks.org/count-total-set-bits-in-all-numbers-from-1-to-n/
     *
     * @param n
     * @return
     */
    public static int getTotalSetBits(int n) {
        int totalSetBits = 0;
        for (int i = 1; i <= n; i++) {
            totalSetBits += countSetBitsFor(i);
        }
        return totalSetBits;
    }

    private static int countSetBitsFor(int number) {
        if (number == 0)
            return 0;
        return (number % 2 == 0 ? 0 : 1) + countSetBitsFor(number / 2);
    }

    public static String getLongestPalindromicSubString(String inputString) {
        char[] input = inputString.toCharArray();
        int low = 0;
        int high = 0;
        int maxLength = 0;
        int startOfPalindromicSubstring = 0;

        for (int i = 1; i < input.length; i++) {
            distinctPalindrome.add(String.valueOf(input[i]));
            // For Even Length
            low = i - 1;
            high = i;

            while (low >= 0 && high < input.length && input[low] == input[high]) {
                if (high - low + 1 > maxLength) {
                    maxLength = high - low + 1;
                    startOfPalindromicSubstring = low;
                }
                low--;
                high++;
            }

            // For Odd Length
            low = i - 1;
            high = i + 1;

            while (low >= 0 && high < input.length && input[low] == input[high]) {
                if (high - low + 1 > maxLength) {
                    maxLength = high - low + 1;
                    startOfPalindromicSubstring = low;
                }
                low--;
                high++;
            }
        }

        return inputString.substring(startOfPalindromicSubstring, startOfPalindromicSubstring + maxLength);
    }

    public static Set<String> distinctPalindrome = new HashSet<>();

    public static void getDistinctPalindrome(String inputString) {
        char[] input = inputString.toCharArray();
        int low = 0;
        int high = 0;
        distinctPalindrome.add(String.valueOf(input[0]));
        for (int i = 1; i < input.length; i++) {
            distinctPalindrome.add(String.valueOf(input[i]));
            // For Even Length
            low = i - 1;
            high = i;

            while (low >= 0 && high < input.length && input[low] == input[high]) {
                distinctPalindrome.add(inputString.substring(low, low + (high - low + 1)));
                low--;
                high++;
            }

            // For Odd Length
            low = i - 1;
            high = i + 1;

            while (low >= 0 && high < input.length && input[low] == input[high]) {
                distinctPalindrome.add(inputString.substring(low, low + (high - low + 1)));
                low--;
                high++;
            }
        }
        System.out.println(distinctPalindrome);
    }

    /**
     * http://www.geeksforgeeks.org/?p=753
     *
     * @param n
     * @return
     */
    public static int getCountUglyNumbers(int n) {
        int i = 1;
        int count = 1;
        while (n > count) {
            i++;
            if (isUgly(i)) {
                count++;
            }
        }
        return i;
    }

    private static Boolean isUgly(int number) {

        number = maxDivide(number, 2);
        number = maxDivide(number, 3);
        number = maxDivide(number, 5);

        return number == 1 ? true : false;
    }

    private static int maxDivide(int a, int b) {
        while (a % b == 0) {
            a = a / b;
        }
        return a;
    }


    public static int[] plusOne(int[] arr) {

        int carry = 1;
        int tempSum = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            tempSum = arr[i] + carry;

            if (tempSum > 9) {
                carry = tempSum / 10;
                arr[i] = tempSum % 10;
            } else {
                carry = 0;
                arr[i] = tempSum;
            }
        }

        if (carry != 0) {
            // check if carry still exist, then we might have to add one more array block
            int[] newArray = new int[arr.length + 1];

            newArray[0] = carry;

            for (int i = 0; i < arr.length; i++) {
                newArray[i + 1] = arr[i];
            }
            return newArray;
        }

        int pointer = 0;
        int totalInsignificantZeros = 0;
        while (arr[pointer++]==0) {
            totalInsignificantZeros++;
        }

        int []tempArr = new int[arr.length-totalInsignificantZeros];
        for(int i=0;i<tempArr.length;i++) {
            tempArr[i] = arr[i+totalInsignificantZeros];
        }

        return tempArr;
    }

}
