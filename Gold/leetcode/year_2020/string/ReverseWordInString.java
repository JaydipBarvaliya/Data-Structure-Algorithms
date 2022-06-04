package com.leetcode.year_2020.string;

public class ReverseWordInString {

    public static void main(String[] args) {
        System.out.println(reverseWords("  Bob    Loves  Alice   "));
    }

    public static String reverseWords(String s) {
        s = s.trim();
        final String[] words = s.split(" ");

        int low = 0;
        int high = words.length - 1;
        final StringBuilder str = new StringBuilder();
        System.out.println(words.length);
        while (low <= high) {
            String lowWord = words[low];
            String highWord = words[high];
            int lowWordLength = lowWord.length();
            int highWordLength = highWord.length();

            if (lowWordLength != 0 && highWordLength != 0) {
                String temp = highWord;
                words[high--] = lowWord;
                words[low++] = temp;
            } else {
                if (lowWordLength == 0) {
                    low++;
                }
                if (highWordLength == 0) {
                    high--;
                }
            }
        }
        String result = "";
        int counter = 0;
        for (String word : words) {
            if (!word.equals("")) {
                result += ((++counter == 1) ? "" : " ") + word;
            }
        }
        return result.trim();
    }
}
