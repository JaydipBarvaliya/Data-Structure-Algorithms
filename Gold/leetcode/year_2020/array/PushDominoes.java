package com.leetcode.year_2020.array;

public class PushDominoes {

    public static void main(String[] args) {
        System.out.println(pushDominoes("RR.L"));
        System.out.println(pushDominoes(".L.R...LR..L.."));
        System.out.println(pushDominoes("..R.."));
    }

    /**
     * In this approach, you just need to find sections like this
     * X .   .   .   . X
     * i               j
     * Where X can be 'R' or 'L' and in between there can be as many dots
     * Now,
     * - you know the length of mid-part
     * - If char[i] == char[j] == 'R', means all go towards right (R)
     * -  char[i]  == char[j] == 'L', means all go towards Left (L)
     * -  If char[i] = 'L' and char[j] = 'R', means middle part is not affected so they remain '.'
     * -  If char[i] = 'R' and char[j] = 'L', then it will affect the middle part.
     * The middle_part/2 close to i will be affected by 'R' and middle_part/2 close to j will be
     * effected by 'L'  and the last mid point (middle_part%2) will be unaffected due to equal
     * force from left and right so it remains '.'
     */
    public static String pushDominoes(String dominoes) {
        dominoes = 'L' + dominoes + 'R'; // Dummy force with no effect
        final StringBuilder result = new StringBuilder();

        for (int i = 0, j = 1; j < dominoes.length(); ++j) {
            if (dominoes.charAt(j) == '.') continue;

            int elementsBetweenIAndJ = j - i - 1;

            if (i > 0) { // Apart from the dummy force, we will always add the actual starting ith item
                result.append(dominoes.charAt(i));
            }

            if (dominoes.charAt(i) == dominoes.charAt(j)) {
                // Now all the elements in between will either fall on the left or right
                for (int k = 0; k < elementsBetweenIAndJ; k++) {
                    result.append(dominoes.charAt(i));
                }
            } else if (dominoes.charAt(i) == 'L' && dominoes.charAt(j) == 'R') {
                // No force both ends facing outside
                for (int k = 0; k < elementsBetweenIAndJ; k++) {
                    result.append(".");
                }
            } else {
                // Now the only condition left is leftBoundary pushing to the right and right boundary pushing to the left
                // So fill leftHalf to the right + if odd (then item in the middle will stand straight) + fill right half to the left
                for (int k = 0; k < elementsBetweenIAndJ / 2; k++) {
                    result.append("R");
                }
                if (elementsBetweenIAndJ % 2 == 1) {
                    result.append(".");
                }
                for (int k = 0; k < elementsBetweenIAndJ / 2; k++) {
                    result.append("L");
                }
            }
            i = j; // Starting the left half where j left us
        }
        return result.toString();
    }
}
