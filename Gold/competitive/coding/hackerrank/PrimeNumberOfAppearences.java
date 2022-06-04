package com.competitive.coding.hackerrank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by jaine03 on 19/08/17.
 */
public class PrimeNumberOfAppearences {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
//            int totalInputs = Integer.parseInt(br.readLine());
//            int counter = 0;
//            int k = 0; // Minimum K appearance
//            Map<Integer,Integer> frequency = new HashMap<>();
//            Set<Integer> allPrimeOccurrence = new HashSet<>();
            int[] inputArray = new int[]{1, 11, 11, 11, 23, 11, 37, 51, 37, 37};
            //System.out.println(getLargestNumberWithPrimeOccurences(inputArray, 2));

            //System.out.println(getOutcomeOfTheFeat(new int[]{1, 2, 3, 4, 5, 6}, 1));
            System.out.println(equilibrium(new int[]{1,2,3,4,5,6},6));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * Complete the function below.
    */
    static int getLargestNumberWithPrimeOccurences(int[] inputArray, int minOccurence) {
        Map<Integer, Integer> frequency = new HashMap<>();
        Set<Integer> allPrimeOccurrence = new HashSet<>();
        for (int input : inputArray) {
            if (frequency.containsKey(input)) {
                frequency.put(input, frequency.get(input) + 1);
            } else {
                frequency.put(input, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entrySet : frequency.entrySet()) {
            int key = entrySet.getKey();
            int value = entrySet.getValue();

            if (value >= minOccurence && isPrime(value)) {
                allPrimeOccurrence.add(key);
            }
        }
        if (allPrimeOccurrence.size() == 0) {
            return -1;
        } else {
            return Collections.max(allPrimeOccurrence);
        }
    }

    static Boolean isPrime(int number) {
        int m = number / 2;
        for (int i = 2; i <= m; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    static String getOutcomeOfTheFeat(int[] weights, int marginOfError) {
        if(equilibrium(weights,weights.length) != -1){
            return "Perfectly Balanced";
        } else {
            return null;
        }
    }

    static int equilibrium(int arr[], int n) {
        int sum = 0;      // initialize sum of whole array
        int leftsum = 0; // initialize leftsum
        int i;

   /* Find sum of the whole array */
        for (i = 0; i < n; ++i)
            sum += arr[i];

        for (i = 0; i < n; ++i) {
            sum -= arr[i]; // sum is now right sum for index i

            if (leftsum == sum)
                return i;

            leftsum += arr[i]+1;
        }

    /* If no equilibrium index found, then return 0 */
        return -1;
    }
}
