package com.datastructures.algorithms;

/**
 * Created by jaine03 on 18/04/17.
 */
public class BinarySearch {

    public static void main(String[] args) {
        int [] arr = new int[]{1,2,3,4,5,6,7,8,9,10};
        search(arr,2,0,9);
    }

    /**
     * Search the element using binary search
     * @param arr
     * @param data
     * @return
     */
    public static void search(int [] arr,int data,int low, int high){
        while (low <= high){
            int mid = (high + low)/2;
            System.out.println("Middle is "+mid);
            if(arr[mid] == data){
                System.out.println("Data found at location "+(mid));
                System.exit(0);
            }
            else if(arr[mid] > data){
                high = mid;
            }
            else if(arr[mid] < data) {
                low = mid+1;
            }
        }
    }
}
