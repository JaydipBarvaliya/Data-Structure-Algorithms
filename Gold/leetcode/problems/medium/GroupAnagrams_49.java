package com.leetcode.problems.medium;

import java.util.*;

/**
 * @author neeraj on 28/08/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class GroupAnagrams_49 {

    public static void main(String[] args) {
        String [] str = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        groupAnagrams(str);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        String[] arrayOfSortedWords = sortAllWords(strs);
        Map<String, List<String>> allAnagrams = new HashMap<>();
        List<List<String>> result = new ArrayList<>();

        for (int i = 0; i < arrayOfSortedWords.length; i++) {
            if (allAnagrams.containsKey(arrayOfSortedWords[i])) {
                allAnagrams.get(arrayOfSortedWords[i]).add(strs[i]);
            } else {
                List<String> anagrams = new ArrayList<>();
                anagrams.add(strs[i]);
                allAnagrams.put(arrayOfSortedWords[i], anagrams);
            }
        }
//        System.out.println(allAnagrams);
        allAnagrams.values().forEach(value -> result.add(value));
        System.out.println(result);
        return result;
    }

    public static String[] sortAllWords(String[] strs) {
        String[] arrayOfSortedWords = new String[strs.length];
        int counter = 0;
        for (String str : strs) {
            char[] charArr = str.toCharArray();
            Arrays.sort(charArr);
            arrayOfSortedWords[counter++] = new String(charArr);
        }
        return arrayOfSortedWords;
    }

    public static List<List<String>> groupAnagrams_leetcode(String[] strs) {
        List<List<String>> groupedAnagrams = new ArrayList<>();
        Map<String, List<String>> groupedMap = new HashMap<>();
        String sortedVal = null;
        for(String str: strs) {
            char [] strArr = str.toCharArray();
            Arrays.sort(strArr);
            sortedVal = new String(strArr);
            if(groupedMap.containsKey(sortedVal)) {
                groupedMap.get(sortedVal).add(str);
                groupedMap.put(sortedVal, groupedMap.get(sortedVal));
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                groupedMap.put(sortedVal,list);
            }
        }

        for(List<String> result: groupedMap.values()) {
            groupedAnagrams.add(result);
        }
        return groupedAnagrams;
    }
}
