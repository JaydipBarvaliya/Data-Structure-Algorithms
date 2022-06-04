package com.geeksforgeeks.dynamicProgramming;

import com.util.LogUtil;

import java.util.*;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 * <p>
 * Example 1:
 * <p>
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 * <p>
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 * @author neeraj on 2019-05-12
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class TotalWaysToDecodeAString {

    private static List<String> decodedValues = new ArrayList<>();

    public static void main(String[] args) {
        getNumberOfWaysDigitCanBeDecoded("12");
        getNumberOfWaysDigitCanBeDecoded("123");
        getNumberOfWaysDigitCanBeDecoded("226");
        getNumberOfWaysDigitCanBeDecoded("1234");
        getNumberOfWaysDigitCanBeDecoded("01");
    }

    public static void getNumberOfWaysDigitCanBeDecoded(String digit) {
        // Initialize the cache which will keep the count of ways for each decodePointer
        // Decode Pointer (Moving from left to right)
        //   \/               \/
        //   1 2 3          1 2 3
        int[] cache = new int[digit.length()];
        int decodePointer = 0;
        Arrays.fill(cache, -1);

        int noOfWays = getNumberOfWays(digit, decodePointer, cache, new StringBuffer());
        LogUtil.logIt("Number of ways " + digit + " can be decoded " + noOfWays + " and the decoded values are ");
        LogUtil.printList(decodedValues);

        // Cleaning up the values
        decodedValues = new ArrayList<>();
    }

    private static int getNumberOfWays(String digit, int decodePointer, int[] previousAnswer, StringBuffer currentDecodedItem) {

        // Base Case 1
        // If decode pointer reached at the end we have a valid decoding, hence return 1
        if (decodePointer == digit.length()) {
            // Adding that decoded string
            decodedValues.add(String.valueOf(currentDecodedItem));
            return 1;
        }

        // Base Case 2
        // If we have previously stored value at the decodePointer in the previousAnswer lets just return it.
        if (previousAnswer[decodePointer] != -1) {
            decodedValues.add(String.valueOf(currentDecodedItem));
//            currentDecodedItem.delete(0, currentDecodedItem.length());
            return previousAnswer[decodePointer];
        }

         /*
            We don't already know the answer to this sub-problem, calculate it
            by taking the sum of the total ways for a single character decoding
            or 2 character decoding.
        */
        int totalWaysFromHere = 0;

        // Now Since we know there are total 26 alphabets, so we can encode between 1<=char<=2
        // Single character encoding
        if (decodePointer + 1 <= digit.length()) {
            String singleDigitChar = digit.substring(decodePointer, decodePointer + 1);
            if (isValidDecoding(singleDigitChar)) {
                currentDecodedItem.append((char) (Integer.parseInt(singleDigitChar) + 64));
                totalWaysFromHere += getNumberOfWays(digit, decodePointer + 1, previousAnswer, currentDecodedItem);
                if (currentDecodedItem.length() > 0)
                    currentDecodedItem.deleteCharAt(currentDecodedItem.length() - 1);
            }
        }

        // Double Character
        if (decodePointer + 2 <= digit.length()) {
            String doubleDigitChar = digit.substring(decodePointer, decodePointer + 2);
            if (isValidDecoding(doubleDigitChar)) {
                currentDecodedItem.append((char) (Integer.parseInt(doubleDigitChar) + 64));
                totalWaysFromHere += getNumberOfWays(digit, decodePointer + 2, previousAnswer, currentDecodedItem);
                if (currentDecodedItem.length() > 1) {
                    currentDecodedItem.delete(currentDecodedItem.length() - 2, currentDecodedItem.length());
                }
            }
        }
        previousAnswer[decodePointer] = totalWaysFromHere;
        return previousAnswer[decodePointer]; // Answer to the sub-problem
    }

    /**
     * This method will tell, if the digit can be decoded into an alphabet
     */
    private static boolean isValidDecoding(String singleDigitChar) {
        if (singleDigitChar.length() == 0) {
            return false;
        }
        if (singleDigitChar.charAt(0) == '0') {
            return false;
        }

        Integer digit = Integer.parseInt(singleDigitChar);
        return digit > 0 && digit <= 26;
    }


}
