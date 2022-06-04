package com.geeksforgeeks.string;

import java.util.*;

public class PrintAllAnagramsTogether {

    public static void main(String[] args) {

        printAnagramTogether(Arrays.asList("cat", "dog", "tac", "god", "act"));
        printAnagramTogether(Arrays.asList("geeksquiz", "geeksforgeeks", "abcd", "forgeeksgeeks", "zuiqkeegs","cat","act","tac"));

    }

    public static void printAnagramTogether(List<String> inputs) {
        List<String> unsortedListWithSortedValues = new ArrayList<>();
        for (String input : inputs) {
            char[] arr = input.toCharArray();
            Arrays.sort(arr);
            unsortedListWithSortedValues.add(String.valueOf(arr));
        }
        System.out.println(unsortedListWithSortedValues);

        // Now all words are sorted in unsorted List, Let's sort unsorted list and keep the index sequence which will help getting the result
        Map<String, ArrayList<Integer>> indexMap = new TreeMap<>();
        for (int i = 0; i < unsortedListWithSortedValues.size(); i++) {
            String item = unsortedListWithSortedValues.get(i);
            if (indexMap.containsKey(item)) {
                ArrayList<Integer> sameItemsIndex = indexMap.get(item);
                sameItemsIndex.add(i);
                indexMap.put(item, sameItemsIndex);
            } else {
                ArrayList<Integer> sameItemIndex = new ArrayList<>();
                sameItemIndex.add(i);
                indexMap.put(item, sameItemIndex);
            }
        }
        System.out.println(indexMap);

        Set<String> keySet = indexMap.keySet();
        for (String item : keySet) {
            List<Integer> sameItemIndex = indexMap.get(item);
            for (Integer i : sameItemIndex) {
                System.out.print(inputs.get(i) + " ");
            }
            System.out.println();
        }
    }

    public static int getAsciiSum(String str) {
        int result = 0;
        for (char c : str.toCharArray()) {
            result += c;
        }
        return result;
    }


    public static Boolean isAnagram(String first, String second) {

        if (first.length() == second.length()) {
            char[] firstArray = first.toCharArray();
            char[] secondArray = second.toCharArray();

            Arrays.sort(firstArray);
            Arrays.sort(secondArray);

            for (int i = 0; i < firstArray.length; i++) {
                if (firstArray[i] != secondArray[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
