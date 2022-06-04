package com.geeksforgeeks.array;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.geeksforgeeks.org/find-top-k-or-most-frequent-numbers-in-a-stream/
 */
public class FindTopKNumbersInStream {

    public static void main(String[] args) {
        findTopKNumbersInStream(new int[]{5, 2, 1, 3, 2});
        System.out.println("=========================================================");
        findTopKNumbersInStream(new int[]{5, 2, 1, 3, 4});
    }

    private static void findTopKNumbersInStream(int[] arr) {

        // Keep track of current Iteration of the array
        StringBuffer current = new StringBuffer();

        // Keep track of whatever has been traversed till now, which is to be used in the current Iteration
        StringBuffer previous = new StringBuffer();

        // To store the actual frequencies of the input value.
        Map<Integer, Integer> freqMap = new HashMap<>();

        // Helps to track whether the newly traversed value is actually inserted in someplace.
        Boolean isNewValueInserted = false;

        for (int i = 0; i < arr.length; i++) {
            isNewValueInserted = false;
            if (freqMap.containsKey(arr[i])) {
                freqMap.put(arr[i], freqMap.get(arr[i]) + 1);
            } else {
                freqMap.put(arr[i], 1);
            }

            for (char c : previous.toString().toCharArray()) {
                int val = c - '0'; // to convert a character into integer.

                // Let's go through the 2 case this "new value" will fit here
                // First if the frequency of this newly traversed item > currentItem in previousArray.
                if (!isNewValueInserted && freqMap.get(arr[i]) > freqMap.get(val)) {
                    current.append(arr[i]);
                    isNewValueInserted = true;
                }

                // Second if the frequency matches freq(arr[i]) === freq(val), in this scenario
                // winner will be decided based on the actual value
                if (!isNewValueInserted && freqMap.get(arr[i]) == freqMap.get(val)) {

                    // If the newly traversed item is actually smaller than the item in previous's Char Array
                    if (arr[i] < val) {
                        current.append(arr[i]);
                        isNewValueInserted = true;
                    } else {
                        // Why we are just comparing if the arr[i] < val, why not arr[i] > val
                        // Reason is previous is storing values based on the descending order of frequencies (in the increasing order of values if frequency is same)
                        // So in that case if the arr[i] > val, it can also be greater than some other value of arr[i], hence skipping it
                        // to find the appropriate position in the next round of iteration.
                    }
                }

                // Now since we have completed both the cases, So let's just dump our "val" from previous.
                // Only if it's not the duplicate entry.
                if (arr[i] != val) {
                    current.append(val);
                }
            }

            // If even after traversing the whole previous array, arr[i] didn't find any position, so let's just add
            // it at the last of current [StringBuffer]
            if (!isNewValueInserted) {
                current.append(arr[i]);
            }

            // Let's just assign current to previous, for next round of iteration, with new integer from array as the next inputs.
            previous = current;
            System.out.println("Top K Numbers upto size of array " + (i + 1) + " : " + current);
            current = new StringBuffer();
        }
    }
}
