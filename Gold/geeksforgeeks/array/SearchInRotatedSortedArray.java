package com.geeksforgeeks.array;

public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        search(new int[]{3, 4, 5, 1, 2, 6}, 0, 5, 6);
    }

    public static void search(int[] arr, int low, int high, int data) {
        if (low > high)
            return;

        int mid = (low + high) / 2;

        if (arr[mid] == data) {
            System.out.println("Data Found at position " + mid);
            return;
        } else if (arr[low] <= arr[mid]) {
            if (arr[low] <= data && arr[mid] >= data) {
                search(arr, low, mid, data);
            } else {
                search(arr, mid + 1, high, data);
            }
        } else {
            if (arr[mid] <= data && arr[high] >= data) {
                search(arr, mid + 1, high, data);
            } else {
                search(arr, low, mid, data);
            }
        }

    }
}
