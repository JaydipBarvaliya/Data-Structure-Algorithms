package com.company.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LineWrap {

    /*
    Given a string that can be a sentence, a paragraph or a whole book, and a line limit, provide a list of text lines that wrap at that limit.

    "   Hello world. This is a        Google interview, good luck !   ", 12

    Result:
    "Hello world."
    "This is a"
    "Google"
    "Interview,"
    "Good luck!"
    */
    public static void main(String[] args) {
        final List<String> input =
                wrapInput(" Hello world.             This is Google big-interview, good luck!", 12);
        input.forEach(System.out::println);
    }

    public static List<String> wrapInput(final String input, final int limit) {

        final String trimmedInput = input.trim();
        final int length = trimmedInput.length();
        if (length == 0 || limit < 1) {
            return Collections.EMPTY_LIST;
        }
        final List<String> result = new ArrayList<>();
        final String[] words = input.split(" ");

        /**
         * We'll split by spaces
         *         Hello
         *         world.
         *         This
         *         is
         *         a
         *         Google
         *         interview,
         *         good
         *         luck!
         */
        for (int i = 0; i < words.length; ) {
            String word = words[i];
            if (word.trim().length() == 0) {
                i++;
                continue;
            }
            int lengthOfLine = word.length();
            if (word.length() > limit) {
                result.add(word.substring(0, limit) + "--word-in-new-line---");
                result.add(word.substring(limit));
                i++;
            } else {
                if (word.length() == limit) {
                    result.add(word.trim());
                    i++;
                } else {
                    // We can add more words under us.
                    final List<String> newWords = new ArrayList<>();
                    int j = i + 1;
                    for (; j < words.length; j++) {
                        if (lengthOfLine + words[i].length() + 1 < limit) {
                            newWords.add(words[j]);
                            lengthOfLine += words[j].length() + 1;
                        } else {
                            break;
                        }
                    }
                    String line = word + " " + String.join(" ", newWords);
                    result.add(line.trim());
                    i = j;
                }
            }
        }
        return result;
    }
}
