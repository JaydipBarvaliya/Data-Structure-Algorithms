package com.geeksforgeeks.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrintAllSubString {

    public static void main(String[] args) {
        printAllSubString("ABCD");
    }

    public static void printAllSubString(String str) {
        List<String> sortedList = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                sortedList.add(str.substring(i, j));
            }
        }
        sortedList.sort(Comparator.comparingInt(String::length));
        System.out.println(sortedList);
    }
}