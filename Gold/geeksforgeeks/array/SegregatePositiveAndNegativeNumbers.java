package com.geeksforgeeks.array;

public class SegregatePositiveAndNegativeNumbers {

    public static void main(String[] args) {
        int arr[] = {-1, 2, -3, 4, 5, 6, -7, 8, 9};
        segregate(arr);
        ArrayRotation.printArray(arr);

        arr = new int[]{-12, 11, -13, -5, 6, -7, 5, -3, -6};
        rearrangeToPreserveOrder(arr, 0, 8);
        ArrayRotation.printArray(arr);

        arr = new int[]{-12, 11, -13, -5, 6, -7, 5, -3, -6};
        segregatePositiveAndNegativeNumbers(arr);
        ArrayRotation.printArray(arr);


    }

    public static int segregatePositiveAndNegativeNumbers(int[] arr) {
        int low = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                QuickSort.swap(arr, low++, i);
            }
        }
        return low; // Point where the positive number start
    }

    public static void rearrangeToPreserveOrder(int[] arr, int low, int high) {

        if (low < high) {
            int mid = low + (high - low) / 2;

            rearrangeToPreserveOrder(arr, low, mid);
            rearrangeToPreserveOrder(arr, mid + 1, high);
            modifiedMerge(arr, low, mid, high);
        }
    }

    public static void segregate(int[] arr) {
        int L = 0;
        int R = arr.length - 1;

        while (L < R) {
            while (arr[L] < 0) {
                L++;
            }
            while (arr[R] > 0) {
                R--;
            }
            if(L < R) {
                QuickSort.swap(arr, L, R);
            }
        }
    }

    public static void rearrange(int[] arr) {
        int j = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                j++;
                QuickSort.swap(arr, j, i);
            }
        }

        int pos = j + 1;
        int neg = 0;
        while (pos < arr.length && neg < pos && arr[neg] < 0) {
            QuickSort.swap(arr, neg, pos);
            neg += 2;
            pos++;
        }
    }


    public static void modifiedMerge(int[] arr, int l, int m, int r) {
        int i, j, k;
        int n1 = m - l + 1;
        int n2 = r - m;

        /* create temp arrays */
        int[] L = new int[n1];
        int[] R = new int[n2];

        /* Copy data to temp arrays L[] and R[] */
        for (i = 0; i < n1; i++)
            L[i] = arr[l + i];
        for (j = 0; j < n2; j++)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays back into arr[l..r]*/
        i = 0; // Initial index of first subarray
        j = 0; // Initial index of second subarray
        k = l; // Initial index of merged subarray

        // Note the order of appearance of elements should
        // be maintained - we copy elements of left subarray
        // first followed by that of right subarray

        // copy negative elements of left subarray
        while (i < n1 && L[i] < 0)
            arr[k++] = L[i++];

        // copy negative elements of right subarray
        while (j < n2 && R[j] < 0)
            arr[k++] = R[j++];

        // copy positive elements of left subarray
        while (i < n1)
            arr[k++] = L[i++];

        // copy positive elements of right subarray
        while (j < n2)
            arr[k++] = R[j++];
    }
}
