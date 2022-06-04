package com.datastructures.mustDoInterviewQuestions.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * https://backtobackswe.com/platform/content/word-subsets
 * <p>
 * Input:
 * A = ["orange", "room", "more"]
 * B = ["rm", "oo"]
 * <p>
 * Output: ["room"]
 * Explanation:
 * "orange" is missing an "m" so it is not a superset of "rm". It also only has one "o" so it is not a superset of "oo".
 * <p>
 * "room" is a superset of "rm" and "oo". The is all strings in B so "room" is universal.
 * <p>
 * "more" is a superset of "rm" since it has an "m" and an "r". It only has one "o" so it is not a superset of "oo".
 */
public class WordSubsets {

    public static void main(String[] args) {
        System.out.println(wordSubsets(Arrays.asList("orange", "room", "more"), Arrays.asList("rm", "oo")));
        System.out.println(wordSubsets(Arrays.asList("padding", "css", "randomcs"), Arrays.asList("cs", "c")));
    }

    public static List<String> wordSubsets(List<String> A, List<String> B) {
        Map<String, Long>[] frequencyMapArrOfA = new HashMap[A.size()];

        // So i have combined all of B list of words into a single unique word and if all characters of this unique word
        // exist in any of A's word that becomes our universal word
        final HashSet<String> uniqueB = new HashSet<>();
        for (String wordB : B) {
            for (char ch : wordB.toCharArray()) {
                uniqueB.add(ch + "");
            }
        }
        final String uniqueWord = String.join("", uniqueB);

        final Map<String, Long> frequencyOfUniqueWord = Arrays.stream(uniqueWord.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        populateFrequency(A, frequencyMapArrOfA);
        final List<String> result = new ArrayList<>();

        for (int i = 0; i < A.size(); i++) {
            if (isUniversal(frequencyMapArrOfA[i], frequencyOfUniqueWord)) {
                result.add(A.get(i));
            }
        }
        return result;
    }

    private static boolean isUniversal(final Map<String, Long> frequencyOfWordA, final Map<String, Long> frequencyOfUniqueWord) {
        for (Map.Entry<String, Long> entry : frequencyOfUniqueWord.entrySet()) {
            final String key = entry.getKey();
            final Long value = entry.getValue();
            if (!frequencyOfWordA.containsKey(key) || frequencyOfWordA.get(key) < value) return false;
        }
        return true;
    }

    private static void populateFrequency(final List<String> words, final Map<String, Long>[] frequencyMapArr) {
        int counter = 0;
        for (String word : words) {
            final Map<String, Long> frequency = Arrays.stream(word.split(""))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            frequencyMapArr[counter++] = frequency;
        }
    }
}
