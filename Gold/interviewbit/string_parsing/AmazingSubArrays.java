package com.interviewbit.string_parsing;

import com.util.LogUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author neeraj on 2019-07-31
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class AmazingSubArrays {

    static List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

    public static void main(String[] args) {
        solve("ABC");
        solve("ABEC");
        solve("DABCAFG");
    }

    static int totalAmazingSubArrays = 0;

    public static int solve(String A) {
        totalAmazingSubArrays = 0;
        LogUtil.logIt("Amazing Sub-Arrays  of " + A, true);
////        allSubString(A, "");

        totalAmazingSubArrays = IntStream.rangeClosed(0, A.length() - 1)
                .filter(i -> vowels.contains(A.charAt(i)))

                /**
                 * Why this A.length() - i
                 *
                 * So if we create the Substring for the given input(ABEC) we
                 * will get following outputs
                 *
                 * A B E C
                 * 0 1 2 3
                 *
                 * now is the subsets are
                 * 1. A
                 * 2. AB
                 * 3. ABE
                 * 4. ABEC
                 * 5. BE
                 * 6. BEC
                 * 7. EC
                 * 8. C
                 *
                 * Now if you See, for Vowel (A) it is contributing to 4 subtring
                 * A
                 * AB
                 * ABE
                 * ABEC
                 *
                 * and we have to tell the O/P that how many AmazingSubarray is possible
                 * which starts with vowel, so how many are there
                 * InputString.length() - indexOf(I)
                 *
                 * 4 - 0 ==> 4, So we will have 4 sub-arrays which starts with
                 * (A) as vowel, hence in map we are doing (A.length() - i )
                 *
                 * So in this manner we iterated over all the indexes of the string
                 * exactly once but we calculated how many substring it can contribute
                 * upto and taken that into account.
                 */
                .map(i -> A.length() - i)
                .sum();
        System.out.println(totalAmazingSubArrays);
        return totalAmazingSubArrays % 10003;
    }

    public static void allSubString(String prefix, String suffix) {
        if (prefix.length() == 0) {
            System.out.println(suffix);
            if (vowels.contains(suffix.toLowerCase().charAt(0))) {
                totalAmazingSubArrays++;
            }
        }
        for (int i = 0; i < prefix.length(); i++) {
            allSubString(prefix.substring(i + 1),
                    suffix + prefix.charAt(i));
        }
    }
}
