package com.datastructures.mustDoInterviewQuestions.DP;

/**
 * http://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
 */
public class JumpTheArray {

    public static void main(String[] args) {
        int [] input = {1, 3, 6, 1, 0, 9};
        System.out.println(getMinimumNumberOfJumps(input));
    }

    public static int getMinimumNumberOfJumps (int [] input){
        int [] T = new int[6];

        for(int i=0;i<T.length;i++){
            T[i] = -1;
        }

        T[0] = 0;
        for(int i=1;i<input.length;i++){
            for(int j=0;j<input.length;j++){
                if(i > j + input[i]){
                    T[i] = Math.min(T[i], T[j] + 1);
                }
            }
        }
        return T[5];
    }
}
