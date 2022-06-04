package com.interviewbit.string_parsing;

import com.util.LogUtil;

/**
 * https://www.interviewbit.com/problems/length-of-last-word/
 * <p>
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 * <p>
 * If the last word does not exist, return 0.
 * <p>
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * <p>
 * Example:
 * <p>
 * Given s = "Hello World",
 * <p>
 * return 5 as length("World") = 5.
 * <p>
 * Please make sure you try to solve this problem without using library functions. Make sure you only traverse the string once.
 *
 * @author neeraj on 2019-08-01
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("DuplicatedCode")
public class LengthOfLastWord {

    private final static String WHITESPACE = " ";

    public static void main(String[] args) {
        findLengthOfLastWord("Hello World");  // Should Answer 5
        findLengthOfLastWord("Helloworld");   // Should Answer 10
        findLengthOfLastWord("         ");    // Should Answer 0
        findLengthOfLastWord("         Hello World How are you");    // Should Answer 0
    }

    private static void findLengthOfLastWord(String input) {
        LogUtil.logIt("Length of Last Word in " + input + " is " + lengthOfLastWord(input));
        LogUtil.logIt("After Reversing input " + input + "  word by word : " + reverseWords(input));
    }

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public static int lengthOfLastWord(final String A) {
        boolean alphabetPresent = false;
        int length = 0;
        // We will start from last character of the input and traverse upto the initial char
        for (int i = A.length() - 1; i >= 0; i--) {
            if (A.charAt(i) == ' ') {
                if (alphabetPresent) {
                    break;
                } else {
                    continue;
                }
            } else {
                alphabetPresent = true;
                length++;
            }
        }
        return length;
    }

    public static String reverseWords(String A) {
        StringBuffer reversedString = new StringBuffer();
        boolean alphabetPresent = false;
        int length = 0;
        for (int i = A.length() - 1; i >= 0; i--) {
            if (A.charAt(i) == ' ') {
                if (alphabetPresent) {
                    reversedString.append(A.substring(i + 1, i + 1 + length)).append(WHITESPACE);
                    alphabetPresent = false;
                    length = 0;
                }
            } else {
                alphabetPresent = true;
                length++;
            }
        }
        if (alphabetPresent) {
            reversedString.append(A.substring(0, length));
        }
        return reversedString.toString();
    }
}
