package com.interviewbit.linked_list;

import com.interviewbit.linked_list.LinkedNodeUtil.ListNode;

import java.util.Arrays;

/**
 * @author neeraj on 2019-08-11
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PartitionList {

    public static void main(String[] args) {
        LinkedNodeUtil linkedNodeUtil = new LinkedNodeUtil();

        linkedNodeUtil.addData(Arrays.asList(1, 4, 3, 2, 5, 2));

        linkedNodeUtil.printList();

        linkedNodeUtil.head = partition(linkedNodeUtil.head, 3);
        linkedNodeUtil.printList();
    }

    public static ListNode partition(ListNode A, int B) {
        int size = getSize(A);

        int[] arr = new int[size];
        ListNode temp = A;
        int counter = 0;
        while (temp != null) {
            arr[counter++] = temp.val;
            temp = temp.next;
        }

        rearrangeToPreserveOrder(arr, 0, size - 1, B);

        A = new ListNode(arr[0]);
        temp = A;
        for (int i = 1; i < arr.length; i++) {
            temp.next = new ListNode(arr[i]);
            temp = temp.next;
        }
        return A;
    }

    public static int getSize(ListNode head) {
        int counter = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            counter++;
        }
        return counter;
    }

    public static void rearrangeToPreserveOrder(int[] arr, int low, int high, int value) {

        if (low < high) {
            int mid = low + (high - low) / 2;

            rearrangeToPreserveOrder(arr, low, mid, value);
            rearrangeToPreserveOrder(arr, mid + 1, high, value);
            modifiedMerge(arr, low, mid, high, value);
        }
    }

    public static void modifiedMerge(int[] arr, int l, int m, int r, int value) {
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
        while (i < n1 && L[i] < value)
            arr[k++] = L[i++];

        // copy negative elements of right subarray
        while (j < n2 && R[j] < value)
            arr[k++] = R[j++];

        // copy positive elements of left subarray
        while (i < n1)
            arr[k++] = L[i++];

        // copy positive elements of right subarray
        while (j < n2)
            arr[k++] = R[j++];
    }
}
