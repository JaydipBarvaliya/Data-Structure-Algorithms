package com.company.amazon;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class AllAnagrams {

    public static void main(String[] args) {
        String input = "CAT";

        printAllAnagrams(input.toCharArray(), getFrequencyOfCharactersInInput(input), 0, new char[3]);
    }

    public static Map<Character, Long> getFrequencyOfCharactersInInput(String input) {
        return input.chars() // Int Stream
                .mapToObj(c -> (char) c)// Convert IntStream to Stream<Characters>
                .collect(groupingBy(c -> c, counting()));
    }

    public static void printResult(char[] result) {
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }

    public static void printAllAnagrams(char[] input, Map<Character, Long> frequency, int level, char[] result) {
        if (input.length == level) {
            printResult(result);
            return;
        }
        // Start from Left To Right
        for (int i = 0; i < input.length; i++) {
            // No words left to be added in the result set
            if (frequency.get(input[i]) == 0) {
                continue;
            }
            result[level] = input[i];

            // Since this character is used in this level so at next level it's frequency reduced by 1
            frequency.put(input[i], frequency.get(input[i]) - 1);

            printAllAnagrams(input, frequency, level + 1, result);

            // We have traversed the level so restore the frequency
            frequency.put(input[i], frequency.get(input[i]) + 1);
        }
    }
}
