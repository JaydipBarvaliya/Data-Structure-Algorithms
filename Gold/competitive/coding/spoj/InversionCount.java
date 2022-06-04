package com.competitive.coding.spoj;

import java.util.Scanner;

/**
 * #graph-theory #number-theory #shortest-path #sorting #bitmasks
 *
 * Created by jaine03 on 21/04/17.
 *
 * Problem Statement at : http://www.spoj.com/problems/INVCNT/
 */
public class InversionCount {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int noOfTestCases = sc.nextInt();
        int testCaseCounter = 0;

        while (testCaseCounter++ < noOfTestCases){
            int sizeOfArray = sc.nextInt();
            long [] sample = new long[sizeOfArray];

            for(int i=0;i<sample.length;i++){
                sample[i]=sc.nextLong();
            }
            System.out.println(getMaxInversionCount(sample));

            long []sortedArray = new long[sizeOfArray];

            System.out.println("Before Sorting");
            printArr(sample);

            mergeSort(sample,sortedArray,0,sample.length-1);

//            System.out.println(mergeSort(sample,sortedArray,0,sample.length-1));

            System.out.println("After Sorting");
            printArr(sample);
        }
    }

    public static void printArr(long []sample){
        for(long i:sample){
            System.out.print(i+",");
        }
        System.out.println();
    }

    public static Integer getMaxInversionCount(int []sample){
        Integer maxInversionCount = 0;
        for(int i=0;i<sample.length;i++){
            for(int j=i+1;j<sample.length;j++){
                if(sample[i] > sample[j]){
                    maxInversionCount++;
                }
            }
        }
        return maxInversionCount;
    }

    public static Integer getMaxInversionCount(long []sample){
        Integer maxInversionCount = 0;
        for(int i=0;i<sample.length;i++){
            for(int j=i+1;j<sample.length;j++){
                if(sample[i] > sample[j]){
                    maxInversionCount++;
                }
            }
        }
        return maxInversionCount;
    }

    public static Integer mergeSort(long []sample,long []sortedArray,int low,int high){
        //System.out.println("Low is ::"+low+" and high is "+high);
        int totalInversion = 0;
        if(low < high){
            int mid = (low+high)/2;
            totalInversion = mergeSort(sample,sortedArray, low,mid);
            totalInversion+= mergeSort(sample,sortedArray, mid+1,high);
            totalInversion+= merge(sample,sortedArray,low,mid+1,high);
            //printArr(sample);
        }
        return totalInversion;
    }

    public static Integer merge(long []sample,long []sortedArray,int low,int mid,int high){
        int l1 = low;
        int l2 = mid;
        int mergeArrayCounter = l1;
        int totalInversion = 0;
        while (l1 <= mid-1 && l2 <= high){
            if(sample[l1] <=sample[l2]){
                sortedArray[mergeArrayCounter] = sample[l1];
                l1++;
            }
            else{
                totalInversion = totalInversion + (mid - l1);
                sortedArray[mergeArrayCounter] = sample[l2];
                l2++;
            }
            mergeArrayCounter++;
        }

        while (l1 <= mid-1){
            sortedArray[mergeArrayCounter++] = sample[l1++];
        }
        while (l2 <= high){
            sortedArray[mergeArrayCounter++] = sample[l2++];
        }

        // Replacing original array with sorted Contents
        for(int i=low;i<=high;i++){
            sample[i] = sortedArray[i];
        }

        return totalInversion;
    }
}
