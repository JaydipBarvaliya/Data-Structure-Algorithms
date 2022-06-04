package com.company.nearbuy;

/**
 * Classic String Similarity Problem
 * <p>
 * Create SubString of given string and check for the similarity of given prefixes
 * <p>
 * <p>
 * Input: ababaa
 * Output: 11 (6 + 0 + 3 + 0 + 1 + 1).
 * <p>
 * For the first case, the suffixes of the string are "ababaa", "babaa", "abaa", "baa", "aa" and "a".
 * The similarities of these strings with the string "ababaa" are 6,0,3,0,1, & 1 respectively.
 * Thus, the answer is 6 + 0 + 3 + 0 + 1 + 1 = 11.
 */
public class UserNameDisparity {

    public static void main(String[] args) {

        int[] result = usernameDisparity(new String[]{"ababaa", "ababa"});
        for (int res : result) {
            System.out.println(res);
        }
    }

    // Complete the usernameDisparity function below.
    static int[] usernameDisparity(String[] inputs) {
        int[] results = new int[inputs.length];
        int inputCounter = 0;
        for (String str : inputs) {
            int sum = 0;
            for (int i = 0; i < str.length(); i++) {
                String subStr = str.substring(i);
                int count = 0;
                // Check for Similarity
                for (int j = 0; j < subStr.length(); j++) {
                    if (str.charAt(j) == subStr.charAt(j)) {
                        count++;
                    } else {
                        break;
                    }
                }
                sum += count;
            }
            results[inputCounter++] = sum;
        }
        return results;
    }
}
