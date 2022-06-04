package com.interviewbit.string_parsing;

import com.util.LogUtil;

/**
 * https://www.interviewbit.com/problems/count-and-say/
 * <p>
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * <p>
 * 1, 11, 21, 1211, 111221, ...
 * 1 is read off as one 1 or 11.
 * 11 is read off as two 1s or 21.
 * <p>
 * 21 is read off as one 2, then one 1 or 1211.
 * <p>
 * Given an integer n, generate the nth sequence.
 * <p>
 * Note: The sequence of integers will be represented as a string.
 * <p>
 * Example:
 * <p>
 * if n = 2,
 * the sequence is 11.
 *
 * @author neeraj on 2019-08-01
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CountAndSay {

    public static void main(String[] args) {
        solveThisProblem(10);

        System.out.println(strStr("HelloWorld", "llo"));
    }

    public static void solveThisProblem(int A) {
        LogUtil.logIt(" \n Sequence at " + A + "th Index ==> \n " + countAndSay(A));
    }

    public static String countAndSay(int A) {
        String initialSequence = "1";
        StringBuffer afterReplacement = new StringBuffer();
        // We have to construct the Sequence
        for (int i = 1; i < A; i++) {
            for (int j = 0; j < initialSequence.length(); ) {
                char charAtJ = initialSequence.charAt(j);
                int frequencyOfThisChar = findFrequencyOfCharStartingAt(j, charAtJ, initialSequence);
                j += frequencyOfThisChar;
                afterReplacement.append(frequencyOfThisChar).append(charAtJ);
            }
            System.out.print(afterReplacement + ",");
            initialSequence = afterReplacement.toString();
            afterReplacement = new StringBuffer();
        }
        return initialSequence;
    }

    private static int findFrequencyOfCharStartingAt(int startIndex, char charAtI, String initialSequence) {
        int count = 0;
        for (int i = startIndex; i < initialSequence.length(); i++) {
            if (charAtI == initialSequence.charAt(i)) {
                count++;
                continue;
            }
            break;
        }
        return count;
    }

    public static int strStr(final String A, final String B) {
        if (A.length() == 0 || B.length() == 0) {
            return -1;
        }

        int indexOfSubString = -1;
        int subStringCounter = 0;
        for (int i = 0; i < A.length(); i++) {
            // Find First Digit
            if (A.charAt(i) == B.charAt(0)) {
                StringBuffer subStringInA = new StringBuffer();
                subStringInA.append(B.charAt(0));
                subStringCounter = 1;
                // Since first character is available so let's just move forward
                // and check if other items are available
                for (int j = subStringCounter; j < B.length(); j++) {
                    if (A.charAt(i + j) == B.charAt(j)) {
                        subStringInA.append(B.charAt(j));
                        continue;
                    }
                    // Didn't match
                    break;
                }
                if (subStringInA.toString().equalsIgnoreCase(B)) {
                    indexOfSubString = i;
                    break;
                }
            } else {
                i++;
            }
        }
        return indexOfSubString;
    }
}
