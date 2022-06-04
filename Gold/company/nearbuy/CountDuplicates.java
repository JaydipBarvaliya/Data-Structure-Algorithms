package com.company.nearbuy;

import java.util.HashMap;
import java.util.Map;

/**
 * A number is duplicate if repeated more than once, and we will calculate all occurrences as only 1
 * <p>
 * Input: {1, 3, 1, 4, 5, 6, 3, 2}
 * Output: 2
 * <p>
 * Input:  {1, 2, 2, 3, 3, 3, 4, 4}
 * Output: 3
 */
public class CountDuplicates {

    public static void main(String[] args) {
        System.out.println(countDuplicates(new int[]{1, 3, 1, 4, 5, 6, 3, 2}));
        System.out.println(countDuplicates(new int[]{1, 2, 2, 3, 3, 3, 4, 4}));
    }

    // Complete the countDuplicates function below.
    static int countDuplicates(int[] numbers) {
        Map<Integer, Integer> duplicates = new HashMap<>();
        int totalDuplicates = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (duplicates.containsKey(numbers[i])) {
                duplicates.put(numbers[i], duplicates.get(numbers[i]) + 1);
            } else {
                duplicates.put(numbers[i], 1);
            }
        }
        for (Integer key : duplicates.keySet()) {
            if (duplicates.get(key) > 1) {
                totalDuplicates++;
            }
        }
        return totalDuplicates;
    }
}
