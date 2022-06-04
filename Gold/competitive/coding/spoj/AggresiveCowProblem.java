package com.competitive.coding.spoj;

import java.util.*;

/**
 * Created by jaine03 on 19/04/17.
 * Problem Statement at : http://www.spoj.com/problems/AGGRCOW/
 *
 * This problem is being solved with the help of Binary Search
 *
 * Good Tutorial on aspects of Binary Search in Real World : https://www.topcoder.com/community/data-science/data-science-tutorials/binary-search/
 */
public class AggresiveCowProblem {

    static Integer noOfCows = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Integer> result = new ArrayList<>();

        int testCases = sc.nextInt();
        int testCaseCounter = 0;

        while (testCaseCounter++ < testCases){
            int noOfStalls = sc.nextInt();
            noOfCows = sc.nextInt();

            int [] stalls = new int[noOfStalls];

            int stallCounter = 0;
            while (stallCounter < noOfStalls){
                stalls[stallCounter] = sc.nextInt();
                stallCounter++;
            }

            Arrays.sort(stalls);

            int lowBound = 0;
            int highBound = stalls[stalls.length -1];

            result.add(binarySearch(lowBound,highBound,stalls));
        }

        Iterator<Integer> iterator = result.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public static int binarySearch(int low,int high,int []stalls){

        while (low < high){
            int mid = low+(high-low)/2;

            if(predicate(stalls,mid) == 1){
                low = mid+1;
            }else{
                high = mid;
            }
        }

        return low-1; // Minimum Largest Distance
    }

    /**
     * This Predicate will return 1 when all cows were accumulated with X difference
     * and 0 if all cows were not accumulated
     * @param x -- > Mid(value) which will be calculated in Binary Search
     * @return
     */
    public static int predicate(int []stalls,int x){

        int prev = stalls[0];
        int reqCows = 1;

        for(int i=1;i<stalls.length;i++){

            if(stalls[i] - prev >= x){
                reqCows++;

                if(reqCows == noOfCows) {
                    return 1;
                }
                prev = stalls[i];
            }
        }
        return 0;
    }
}
