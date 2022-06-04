package com.company.amazon;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RearrangeCharactersInStringSuchThatNoTwoAdjacentCharactersAreSame {

    public static void main(String[] args) {

        rearrangeCharacters("AAABC");

    }

    public static void rearrangeCharacters(String str) {
        char[] arr = str.toCharArray();
        Boolean hasMajorityElement = hasMajorityElement(arr);

        if (hasMajorityElement)
            System.out.println("Rearrangement Not possible");
        else {
            char[] res = new char[arr.length];
            Map<Character, Long> freq = str.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
            System.out.println(freq);

        }
    }

    /**
     * Using Moore's Voting Algorithm O(n) and O(1) space
     * <p>
     * Voting Algorithm is not really needed here because anyways we are using HashMap to store frequencies
     * which will be used for the above problem, So just for practice let's code it.
     *
     * Why Moore's Algo work, because if an element occurs > N/2 times then it's frequency can't be reduced to 0, even if the
     * majority element is present in the initial subarray of array.
     *
     * Like here : A A A C C B B C C C B C C
     */
    public static boolean hasMajorityElement(char[] arr) {
        int CANDIDATE = -1;
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (count == 0) {
                CANDIDATE = arr[i];
                count = 1;
            } else {
                if (arr[i] == CANDIDATE) {
                    count++;
                } else {
                    count--;
                }
            }
        }

        if (count != 0) { // We have majority element
            // ReVerify that candidate is actually the Majority element
            int totalCount = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == CANDIDATE) {
                    totalCount++;
                    if (totalCount > Math.ceil(arr.length / 2d)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
