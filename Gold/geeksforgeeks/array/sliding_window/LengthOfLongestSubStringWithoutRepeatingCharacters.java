package com.geeksforgeeks.array.sliding_window;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LengthOfLongestSubStringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        findLongestSubString("GEEKSFORGEEKS");
        findLongestSubString("ABDEFGABEF");
        findLongestSubString("AAAA");

        findLongestSubStringWithNonRepeatingCharacters("GEEKSFORGEEKS");
        findLongestSubStringWithNonRepeatingCharacters("ABDEFGABEF");
        findLongestSubStringWithNonRepeatingCharacters("AAAA");
    }

    public static void findLongestSubString(String str) {
        char[] arr = str.toCharArray();
        Integer LONGEST_WINDOW_WIDTH = 0;
        Integer longestWindowLeft = 0;
        Integer longestWindowRight = 0;

        Integer wL = 0; // Left Side of Window
        Integer wR = 0; // Right Side of Window
        List<Character> allVisitedCharacters = new ArrayList<>();

        while (wR < arr.length) {

            while (allVisitedCharacters.contains(arr[wR])) {
                allVisitedCharacters.remove(Character.valueOf(arr[wL++]));
            }

            allVisitedCharacters.add(arr[wR++]);
            if (wR - wL > LONGEST_WINDOW_WIDTH) {
                LONGEST_WINDOW_WIDTH = wR - wL;
                longestWindowLeft = wL;
                longestWindowRight = wR;
            }
        }
        char[] longestSubString = Arrays.copyOfRange(arr, longestWindowLeft, longestWindowRight);
        printArr(longestSubString);
    }

    public static void printArr(char[] arr) {
        for (char c : arr) {
            System.out.print(c + ",");
        }
        System.out.println();
    }

    public static void findLongestSubStringWithNonRepeatingCharacters(String str) {
        LogUtil.logIt("findLongestSubStringWithNonRepeatingCharacters in New way 23rd July 2019 inside " + str, true);
        char[] arr = str.toCharArray();
        List<Character> allVisitedCharacters = new ArrayList<>();
        int Left = 0;
        int Right = 0;
        int maximumWindowLeft = 0;
        int maximumWindowRight = 0;
        int MAX_WINDOW_LENGTH = 0;
        char repeatedCharacter;

        while (Right < arr.length) {
            if (!allVisitedCharacters.contains(arr[Right])) {
                allVisitedCharacters.add(arr[Right++]);
            } else {
                // Means Right is standing on a character which is already present in the window.
                // So first let's check our window size
                repeatedCharacter = arr[Right];
                if (MAX_WINDOW_LENGTH < Right - Left) {
                    MAX_WINDOW_LENGTH = Right - Left;
                    maximumWindowLeft = Left;
                    maximumWindowRight = Right;
                }

                // Now Let's traverse leftPointer upto the point  where we find our repeated character
                while (arr[Left] != repeatedCharacter) {
                    allVisitedCharacters.remove((Object) arr[Left]);
                    Left++;
                }
                if (arr[Left] == repeatedCharacter) {
                    allVisitedCharacters.remove((Object) repeatedCharacter);
                    Left++;
                }
            }
        }

        char[] longestSubString = Arrays.copyOfRange(arr, maximumWindowLeft, maximumWindowRight);
        System.out.println(LogUtil.getCharArrayAsString(longestSubString));
    }
}
