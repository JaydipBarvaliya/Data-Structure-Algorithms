package com.leetcode.year_2020;

/**
 * @author neeraj on 03/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FooBarGoogleChallenge {

    /**
     * I Love Lance & Janice
     * =====================
     * <p>
     * You've caught two of your fellow minions passing coded notes back and forth - while they're on duty, no less! Worse, you're pretty sure it's not job-related - they're both huge fans of the space soap opera ""Lance & Janice"". You know how much Commander Lambda hates waste, so if you can prove that these minions are wasting her time passing non-job-related notes, it'll put you that much closer to a promotion.
     * <p>
     * Fortunately for you, the minions aren't exactly advanced cryptographers. In their code, every lowercase letter [a..z] is replaced with the corresponding one in [z..a], while every other character (including uppercase letters and punctuation) is left untouched.  That is, 'a' becomes 'z', 'b' becomes 'y', 'c' becomes 'x', etc.  For instance, the word ""vmxibkgrlm"", when decoded, would become ""encryption"".
     * <p>
     * Write a function called solution(s) which takes in a string and returns the deciphered string so you can show the commander proof that these minions are talking about ""Lance & Janice"" instead of doing their jobs.
     * <p>
     * Languages
     * =========
     * <p>
     * To provide a Python solution, edit solution.py
     * To provide a Java solution, edit Solution.java
     * <p>
     * Test cases
     * ==========
     * Your code should pass the following test cases.
     * Note that it may also be run against hidden test cases not shown here.
     * <p>
     * -- Python cases --
     * Input:
     * solution.solution("wrw blf hvv ozhg mrtsg'h vkrhlwv?")
     * Output:
     * did you see last night's episode?
     * <p>
     * Input:
     * solution.solution("Yvzs! I xzm'g yvorvev Lzmxv olhg srh qly zg gsv xlolmb!!")
     * Output:
     * Yeah! I can't believe Lance lost his job at the colony!!
     * <p>
     * -- Java cases --
     * Input:
     * Solution.solution("Yvzs! I xzm'g yvorvev Lzmxv olhg srh qly zg gsv xlolmb!!")
     * Output:
     * Yeah! I can't believe Lance lost his job at the colony!!
     * <p>
     * Input:
     * Solution.solution("wrw blf hvv ozhg mrtsg'h vkrhlwv?")
     * Output:
     * did you see last night's episode?
     * <p>
     * Use verify [file] to test your solution and see how it does. When you are finished editing your code, use submit [file] to submit your answer. If your solution passes the test cases, it will be removed from your home folder.
     */
    public static void main(String[] args) {
        System.out.println(solution("wrw blf hvv ozhg mrtsg'h vkrhlwv?"));
        System.out.println(solution("Yvzs! I xzm'g yvorvev Lzmxv olhg srh qly zg gsv xlolmb!!"));
        System.out.println(solution("Yvzs! I xzm'g yvorvev Lzmxv olhg srh qly zg gsv xlolmb!!"));
    }

    public static String solution(String x) {
        /**
         * Decoding is simple all small letters are coded with their reverse
         * a == z
         * b == y
         * and so on....
         */
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : x.toCharArray()) {
            if (Character.isLetter(c) && c >= 97) {
                // Let's bring the Ascii code to 26 alphabet window.
                // and since 0 based index hence using 25 to decode.
                stringBuilder.append((char) ((25 - (c - 'a')) + 'a'));
            } else { // In case
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

}
