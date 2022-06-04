package com.geeksforgeeks.dynamicProgramming;

import java.util.*;

/**
 * https://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
 * Input:   str1 = "geek", str2 = "gesek"
 * Output:  1
 * We can convert str1 into str2 by inserting a 's'.
 * <p>
 * Input:   str1 = "cat", str2 = "cut"
 * Output:  1
 * We can convert str1 into str2 by replacing 'a' with 'u'.
 * <p>
 * Input:   str1 = "sunday", str2 = "saturday"
 * Output:  3
 * Last three and first characters are same.  We basically
 * need to convert "un" to "atur".  This can be done using
 * below three operations.
 * Replace 'n' with 'r', insert t, insert a
 */
public class EditDistance {

    public static void main(String[] args) {
        getMinumumOperation("sunday", "saturday");
    }

    public static Map<Character, Integer> getFrequencyMap(String str) {
        Map<Character, Integer> frequency = new HashMap<>();
        for (Character c : str.toCharArray()) {
            if (frequency.containsKey(c)) {
                frequency.put(c, frequency.get(c) + 1);
            } else {
                frequency.put(c, 1);
            }
        }
        return frequency;
    }

    public static int getMinumumOperation(String first, String second) {
        Map<Character, Integer> freq1 = getFrequencyMap(first);
        Map<Character, Integer> freq2 = getFrequencyMap(second);
        Map<Character, Integer> itemsToBeInserted = new HashMap<>();

        if (first.length() < second.length()) {
            Set<Character> keySet = freq2.keySet();

            for (Character c : keySet) {
                Integer frequency2 = freq2.get(c);
                Integer frequency1 = freq1.get(c);

                if (frequency1 == null) {
                    itemsToBeInserted.put(c, frequency2);
                }
                else if (frequency2 == frequency1) { // If frequency of character is same means we need not do anything to it.
                    freq1.remove(c);
                    freq2.remove(c);
                } else if (frequency2 > frequency1) {
                    freq2.put(c, frequency2 - frequency1); // Reduced number of instances of char are present in first string
                    freq1.remove(c);
                    itemsToBeInserted.put(c, frequency2 - frequency1);
                }
            }
            System.out.println(freq1);
            System.out.println(freq2);
        }
        return 0;
    }
}
