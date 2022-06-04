package com.geeksforgeeks.string;

import java.util.ArrayList;
import java.util.List;

public class ReverseWordInString {

    public static void main(String[] args) {
        reverseString("SAGAR IS LEARNING JAVA AND ANDRIOD");
        reverseString("SAGAR IS A GOOD BOY");
        reverseString("JAVA IS AWESOME");
        reverseString("J");
    }

    /**
     * 1) First Count the Spaces in the words
     * 2) Run a loop for no of spaces time
     * 3) Reverse respective words separated by space
     * 4) Now after last space is done we will check that still there is any word left then call reverse for the last time
     *
     * @param str
     */
    public static void reverseString(String str) {
        List<Integer> spacesInString = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                spacesInString.add(i);
            }
        }
        char[] strInArr = str.toCharArray();
        int lowIndex = 0;
        for (Integer spaceIndex : spacesInString) {
            reverse(strInArr, lowIndex, spaceIndex);
            lowIndex = spaceIndex + 1;
        }
        if (lowIndex < strInArr.length) {
            reverse(strInArr, lowIndex, strInArr.length);
        }

        System.out.println("Reverse of [" + str + "] :: " + String.valueOf(strInArr));
    }

    /**
     * This method will reverse words in array ranging from lowIndex to highIndex
     *
     * @param strInArr
     * @param lowIndex
     * @param highIndex
     */
    public static void reverse(char[] strInArr, int lowIndex, int highIndex) {
        int lowCounter = lowIndex;
        int highCounter = highIndex - 1;
        for (int i = lowIndex; i < highIndex - 1; i++) {
            char temp = strInArr[lowCounter];
            strInArr[lowCounter] = strInArr[highCounter];
            strInArr[highCounter] = temp;
            highCounter--;
            lowCounter++;
        }
    }


}
