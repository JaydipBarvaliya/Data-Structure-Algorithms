package com.company.amazon;

import java.util.HashMap;
import java.util.Map;

public class SmallestWindowInAStringContainingAllCharactersOfOtherString {

    private static Map<Character, Integer> shouldHaveFrequencyMap = new HashMap<>();
    private static Map<Character, Integer> whatIsFrequencyNowMap = new HashMap<>();
    private static Map<Character, Integer> patternIndexMap = new HashMap<>();
    private static Integer LENGTH_OF_SMALLEST_WINDOW = Integer.MAX_VALUE;
    private static Integer COUNT_OF_PATTERN_MATCHED_LENGTH = 0;
    private static String ACTUAL_SUBSTRING_IN_WINDOW = null;

    public static void main(String[] args) {

        findSmallestWindow("THIS IS A TEST STRING", "TSTI");
        findSmallestWindow("geeksforgeeks", "ork");

    }

    private static void findSmallestWindow(String str, String pattern) {
        logIt("TEST CASE START", true);

        resetStaticData();

        // These MAPS will help in solving the problem in later stage
        populateMaps(pattern);

        // Let's update index of Pattern in Main Array's index Array
        int[] arrOfStr = new int[str.length()];

        for (int i = 0; i < arrOfStr.length; i++) {
            char value = str.charAt(i);

            // Check that this value is actually a part of pattern
            if (patternIndexMap.containsKey(value)) {

                // Update the index
                arrOfStr[i] = patternIndexMap.get(value);

                // Till Now current character's frequency doesn't match the one in Pattern
                if (whatIsFrequencyNowMap.get(value) < shouldHaveFrequencyMap.get(value)) {
                    whatIsFrequencyNowMap.put(value, whatIsFrequencyNowMap.get(value) + 1);
                    COUNT_OF_PATTERN_MATCHED_LENGTH++;
                } else { // Simply Remove the previous occurence
                    removePreviousOccurrence(str, arrOfStr, value);
                }

                // If Count reached to the length of the Input Pattern, let's find the window.
                if (COUNT_OF_PATTERN_MATCHED_LENGTH >= pattern.length()) {
                    COUNT_OF_PATTERN_MATCHED_LENGTH = pattern.length(); // Resetting it to the original pattern length
                    checkSmallestWindowSize(arrOfStr, str);
                }
            }
        }

        logIt("Length of Smallest window for pattern (" + pattern + ") in string (" + str + ") is : " + LENGTH_OF_SMALLEST_WINDOW, false);
        logIt("And Actual Substring is [" + ACTUAL_SUBSTRING_IN_WINDOW + "]", false);
        logIt("TEST CASE END", true);
    }

    private static void resetStaticData() {
        shouldHaveFrequencyMap = new HashMap<>();
        whatIsFrequencyNowMap = new HashMap<>();
        patternIndexMap = new HashMap<>();
        LENGTH_OF_SMALLEST_WINDOW = Integer.MAX_VALUE;
        COUNT_OF_PATTERN_MATCHED_LENGTH = 0;
        ACTUAL_SUBSTRING_IN_WINDOW = null;
    }

    private static void removePreviousOccurrence(String str, int[] arrOfStr, char value) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == value && arrOfStr[i] > 0) {
                arrOfStr[i] = 0;
                return;
            }
        }
    }

    private static void checkSmallestWindowSize(int[] arrOfStr, String str) {
        int low = 0;
        int high = arrOfStr.length - 1;

        while (arrOfStr[low] == 0) {
            low++;
        }
        while (arrOfStr[high] == 0) {
            high--;
        }

        if (LENGTH_OF_SMALLEST_WINDOW > (high - low) + 1) {
            LENGTH_OF_SMALLEST_WINDOW = high - low + 1;
            ACTUAL_SUBSTRING_IN_WINDOW = str.substring(low, high + 1);
        }
    }

    private static void populateMaps(String pattern) {
        for (int i = 0; i < pattern.length(); i++) {
            char charAtI = pattern.charAt(i);

            //First Populate the Index Map and WhatIsFreq Map
            // Since here we need not worry about keys being repeated as we aren't interested in that
            if (!patternIndexMap.containsKey(charAtI)) {
                patternIndexMap.put(charAtI, i + 1);
                whatIsFrequencyNowMap.put(charAtI, 0);
                shouldHaveFrequencyMap.put(charAtI, 1);
            } else {
                shouldHaveFrequencyMap.put(charAtI, shouldHaveFrequencyMap.get(charAtI) + 1);
            }
        }
    }

    private static void logIt(String str, Boolean shouldAddLog) {
        System.out.println((shouldAddLog == true ? "============== " : "") + str + (shouldAddLog == true ? " ==============" : ""));
    }
}
