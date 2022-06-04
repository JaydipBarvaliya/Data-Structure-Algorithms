package com.datastructures.mustDoInterviewQuestions.array;

/**
 * Created by jaine03 on 22/07/17.
 */
public class EquilibriumPoint {

    public static void main(String[] args) {
        System.out.println(getEquibiliriumPoint(new int[]{-7, 1, 5, 2, -4, 3, 0}));
    }

    public static int getEquibiliriumPoint(int[] array) {
        int totalSum = getSum(array);
        int leftSum = 0;

        for(int i=0;i<array.length;i++){

            totalSum = totalSum - array[i];

            if(totalSum == leftSum) {
                System.out.print("Found Equilibrium point at :");
                return i;
            }
            leftSum += array[i];
        }
        return -1;
    }

    public static int getSum(int []array){
        int totalSum = 0;
        for(int a:array)
            totalSum+=a;
        return totalSum;
    }
}
