package com.geeksforgeeks.string;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PrintAllSubsequences {

    public static void main(String[] args) {
//        combinations("", "ABCD");
        combinations("", "AAB");

//        subsequences.sort(Comparator.comparingInt(String::length));
        System.out.println(subsequences);
    }

    private static List<String> subsequences = new ArrayList<>();

    public static void combinations(String suffix, String prefix) {
        if (prefix.length() < 0) {
            return;
        }
//        System.out.println(suffix);
        subsequences.add(suffix);
        for (int i = 0; i < prefix.length(); i++) {
            combinations(suffix + prefix.charAt(i), prefix.substring(i + 1));
        }
    }
}
