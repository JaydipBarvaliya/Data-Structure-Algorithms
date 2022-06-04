package com.company.amazon;

public class TypeOfArrayAndMax {

    private static Integer MaxValue = 0;

    public static void main(String[] args) {
        printTypeOfArrayAndMax(new int[]{1, 2, 3, 4, 5}); // Asc
        printTypeOfArrayAndMax(new int[]{5, 4, 3, 2, 1}); // Desc
        printTypeOfArrayAndMax(new int[]{4, 5, 1, 2, 3});// Ascending Rotated
        printTypeOfArrayAndMax(new int[]{3, 2, 1, 5, 4});// Descending Rotated
        printTypeOfArrayAndMax(new int[]{2, 1, 5, 4, 3});// Descending Rotated
        printTypeOfArrayAndMax(new int[]{3, 4, 5, 1, 2});// Ascending Rotated


        // Inputs from Geeks for Geeks
        System.out.println("===================== GEEKS FOR GEEKS ================================");
        int arr1[] = {4, 5, 6, 1, 2, 3}; // Ascending rotated
        printType(arr1);

        int arr2[] = {2, 1, 7, 5, 4, 3}; // Descending rotated
        printType(arr2);

        int arr3[] = {1, 2, 3, 4, 5, 8}; // Ascending
        printType(arr3);

        int arr4[] = {9, 5, 4, 3, 2, 1}; // Descending
        printType(arr4);


    }

    // 1) Find if it's in ascending order
    // 2) If not even partially ascending then definitely it's descending or descending rotated
    //      a) Check for Descending if not then it's 2b)
    // 3) If it's partially ascending then just go and find the max value
    public static void printType(int[] arr) {
        int i = 0;
        int n = arr.length;

        while (i < n - 1 && arr[i] < arr[i + 1]) {
            i++;
        }

        // Check for Ascending
        if (i == n - 1) {
            System.out.println("Ascending Order :: " + arr[n - 1]);
            return;
        }

        // MayBe descending or Descending Rotated
        if (i == 0) {

            // Check for Descending
            while (i < n - 1 && arr[i] > arr[i + 1]) {
                i++;
            }
            if (i == n - 1) {
                System.out.println("Descending Order :: " + arr[0]);
                return;
            }
            // If you reach here then it's definitely Descending Rotated, just find a place where arr[i+1] > arr[i]
            while (i < n - 1 && arr[i] > arr[i + 1]) {
                i++;
            }
            System.out.println("Descending Rotated :: " + arr[i + 1]);
            return;
        }

        // It's definitely Ascending Rotated
        while (i < n - 1 && arr[i] < arr[i + 1]) {
            i++;
        }

        System.out.println("Ascending Rotated :: " + arr[i]);
        return;

    }

    public static void printTypeOfArrayAndMax(int[] arr) {
        MaxValue = 0;
        if (isAscending(arr)) {
            System.out.println("Ascending :: " + MaxValue);
            return;
        }
        if (isDescending(arr)) {
            System.out.println("Descending :: " + MaxValue);
            return;
        }
        if (isDescRotated(arr)) {
            System.out.println("Descending Rotated :: " + MaxValue);
            return;
        }
        if (isAscRotated(arr)) {
            System.out.println("Ascending Rotated :: " + MaxValue);
            return;
        }
    }

    private static boolean isDescRotated(int[] arr) {
        boolean isDescRotated = false;
        int maxVal = 0;
        int n = arr.length - 1;
        if (arr[0] < arr[n]) {
            for (int i = 0; i < n - 1; i++) {
                if (arr[i + 1] > arr[i]) {
                    isDescRotated = true;
                    maxVal = arr[i + 1];
                    break;
                }
            }
            if (isDescRotated) {
                MaxValue = maxVal;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean isDescending(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[0]) {
                return false;
            }
        }
        MaxValue = arr[0];
        return true;
    }

    public static boolean isAscending(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[0]) {
                return false;
            }
        }
        MaxValue = arr[arr.length - 1];
        return true;
    }

    public static boolean isAscRotated(int[] arr) {
        boolean isAscRotated = false;
        int maxVal = 0;
        int n = arr.length - 1;

        if (arr[0] > arr[n]) { // 1st Element will always be greater than last element in Ascending Rotated manner
            for (int i = 0; i < n - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    isAscRotated = true;
                    maxVal = arr[i];
                    break;
                }
            }
            if (isAscRotated) {
                MaxValue = maxVal;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
