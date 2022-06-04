package com.geeksforgeeks.dynamicProgramming;

import com.geeksforgeeks.array.Rotate2DMatrix;

import java.util.*;

public class KnapSackProblem {

    public static void main(String[] args) {

        // 0 is added in both weights and values to have a base hypothetical condition

        getMaximumAchievableSum(new int[]{0, 1, 3, 4, 5, 2}, new int[]{0, 1, 4, 5, 7, 4}, 9);
        getMaximumAchievableSum(new int[]{0, 10, 20, 30}, new int[]{0, 60, 100, 120}, 50);
        getMaximumAchievableSum(new int[]{0, 1, 3, 4, 5}, new int[]{0, 1, 4, 5, 7, 4}, 7);
    }

    public static void getMaximumAchievableSum(int[] weights, int[] values, int knapSackCapacity) {

        int[][] calculations = new int[weights.length][knapSackCapacity + 1];
        Map<Integer, Integer> sortedWeights = getSortedWeightsAndValues(weights, values);
        // Sorting weights as we need maximum value with sumOfAllWeights <= knapSackCapacity
        Arrays.sort(weights);

        for (int i = 0; i < weights.length; i++) { // All Weights

            for (int j = 0; j <= knapSackCapacity; j++) {

                if (i == 0 || j == 0) { // Hypothetical 1'st Row and 1st column to have a base condition
                    calculations[i][j] = 0;
                    continue;
                }

                /**
                 * If weight is less the sum of weight then we value will be
                 *
                 * value = max( Value Excluding weight, Value Including weights + Value with Remaining weight)
                 *
                 * value = max ( calculations[i-1][j] , value[current selected weight] + calculations [i - 1][j - current selected weight]
                 */
                if (weights[i] <= j) {
                    int valueWithWeightIncluded = sortedWeights.get(weights[i]);
                    calculations[i][j] = Math.max(calculations[i - 1][j], valueWithWeightIncluded + calculations[i - 1][j - weights[i]]);
                } else {
                    calculations[i][j] = calculations[i - 1][j];
                }
            }
        }
        Rotate2DMatrix.print2DArray(calculations);
        printWeightUsedInMaximumSum(calculations, weights, knapSackCapacity);
    }

    public static void printWeightUsedInMaximumSum(int[][] calculations, int[] weights, int knapSackCapacity) {
        List<Integer> result = new ArrayList<>();
        int i = weights.length - 1;
        for (int j = knapSackCapacity; j > 0; ) {
//                System.out.println("Calculations["+i+"]["+j+"] != Calculations["+(i -1)+"]["+j+"]" + calculations[i][j] +":::"+ calculations[i-1][j] +"::"+ (calculations[i][j] != calculations[i-1][j]));
            if (calculations[i][j] != calculations[i - 1][j]) { // The current weight is included in the maximumSum Calculation
                result.add(weights[i]);
                j = j - weights[i];
                i--;
            } else {
                i--;
            }
        }
        System.out.println("Weights included in the Calculation of Maximum sum is " + result +
                " and maximum sum is " + calculations[weights.length - 1][knapSackCapacity]);

    }

    public static Map<Integer, Integer> getSortedWeightsAndValues(int[] weights, int[] values) {
        Map<Integer, Integer> sortedWeights = new TreeMap<>();
        for (int i = 0; i < weights.length; i++) {
            sortedWeights.put(weights[i], values[i]);
        }
        return sortedWeights;
    }

}
