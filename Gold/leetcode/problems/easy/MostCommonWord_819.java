package com.leetcode.problems.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author neeraj on 01/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MostCommonWord_819 {

    public static void main(String[] args) {
        System.out.println(mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"}));
//        System.out.println(mostCommonWord("a, a, a, a, b,b,b,c, c", new String[]{"a"}));

        System.out.println("neeraj+jain@gmail.com".indexOf("+"));
        System.out.println("neeraj+jain@gmail.com".indexOf("[A-Za-z]"));

    }

    public static String mostCommonWord(String paragraph, String[] banned) {
        paragraph = paragraph.toLowerCase();
        Map<String, Integer> map = new HashMap<>();
        List<String> bannedWords = Arrays.asList(banned);

        for(String word: paragraph.split(" ")) {
            word = word.trim();
            word = word.replaceAll("\\p{Punct}", "");
            if(!bannedWords.contains(word)) {
                if(map.containsKey(word)) {
                    map.put(word, map.get(word)+1);
                } else {
                    map.put(word, 1);
                }
            }
        }
        String mostCommon = "";
        int freq = Integer.MIN_VALUE;
        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            if(freq < entry.getValue()) {
                mostCommon = entry.getKey();
                freq = entry.getValue();
            }
        }
        return mostCommon;
    }
}
