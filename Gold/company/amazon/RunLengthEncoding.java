package com.company.amazon;

public class RunLengthEncoding {

    public static void main(String[] args) throws java.lang.Exception {
        encode("wwwwaaadexxxxxx");
    }

    public static void encode(String str) {
        StringBuffer result = new StringBuffer();
        char[] word = str.toCharArray();
        char traversedWord = word[0];
        int count = 0;
        for (int i = 0; i < word.length; i++) {
            if (word[i] == traversedWord) {
                count++;
                if (i + 1 >= word.length) {
                    result.append(traversedWord).append(count);
                }
            } else {
                result.append(traversedWord).append(count);
                count = 1;
                traversedWord = word[i];
            }
        }
        System.out.println(result);
    }
}