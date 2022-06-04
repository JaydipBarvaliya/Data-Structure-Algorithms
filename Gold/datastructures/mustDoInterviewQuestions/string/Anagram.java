package com.datastructures.mustDoInterviewQuestions.string;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jaine03 on 24/07/17.
 */
public class Anagram {

    public static void main(String[] args) {
        String first = "listen";
        String second = "silent";
        System.out.println("Are " + first + " and " + second + " anagram " + isAnagram(first, second));
    }

    private static int[] numofLetterNeedtoChange(String a[], String b[]) {
        int[] answers = new int[a.length];

        //Assuming Length of A and B is same
        for (int i = 0; i < a.length && i < b.length; i++) {
            String first = a[i];
            String second = b[i];

            if (first.length() != second.length()) {
                answers[i] = -1;
            }
        }
        return null;
    }

    public static Boolean isAnagram(String first, String second) {

        Map<Character, Long> frequencyCountMap1 = first.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        Map<Character, Long> frequencyCountMap2 = second.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));


        for (Map.Entry<Character, Long> entry : frequencyCountMap1.entrySet()) {
            Character key = entry.getKey();
            Long value = entry.getValue();
            if (frequencyCountMap2.get(key) != value) {
                return false;
            }
        }
        return true;
    }
}
