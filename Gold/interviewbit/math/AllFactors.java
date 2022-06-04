package com.interviewbit.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author neeraj on 2019-07-27
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class AllFactors {

    public static void main(String[] args) {
        System.out.println(allFactors(6));
        System.out.println(allFactors(38808));

        System.out.print(squareSum(25));
    }

    public static ArrayList<Integer> allFactors(int A) {
        ArrayList<Integer> answer = new ArrayList<>();

        if (A < 2 && A > 0) {
            answer.add(1);
        } else {
            double sqrtA = Math.sqrt(A);
            for (int i = 1; i <= sqrtA; i++) {
                if (A % i == 0) {
                    answer.add(i);
                    if (i != sqrtA) {
                        answer.add(A / i);
                    }
                }
            }
        }
        Collections.sort(answer);
        return answer;
    }

    public static ArrayList<ArrayList<Integer>> squareSum(int A) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        List<String> traversedPairs = new ArrayList<>();

        for (int a = 0; a * a < A; a++) {
            for (int b = 0; b * b < A; b++) {
                if (a * a + b * b == A && !traversedPairs.contains(a + "" + b)) {
                    ArrayList<Integer> newEntry = new ArrayList<>();
                    newEntry.add(a);
                    newEntry.add(b);
                    traversedPairs.add(b + "" + a);
                    ans.add(newEntry);
                }
            }
        }
        return ans;
    }
}
