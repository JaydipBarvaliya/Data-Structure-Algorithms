package com.datastructures.mustDoInterviewQuestions.string;

/**
 * Created by jaine03 on 24/07/17.
 */
public class MinimumNumberOfInsertionForPalindrome {

    public static void main(String[] args) {
        System.out.println(getMinimumNumberOfInsertions("AB",0,1));
        System.out.println(getMinimumNumberOfInsertions("AA", 0, 1));
        System.out.println(getMinimumNumberOfInsertions("AAA",0,2));
        System.out.println(getMinimumNumberOfInsertions("ABCD",0,3));
        System.out.println(getMinimumNumberOfInsertions("ABCDA",0,4));
        System.out.println(getMinimumNumberOfInsertions("ABCDE",0,4));
    }


    public static int getMinimumNumberOfInsertions(String str, int l, int h) {

        if (l > h) return Integer.MAX_VALUE;
        if (l == h) return 0;
        if (l == h - 1) return str.charAt(l) == str.charAt(h) ? 0 : 1;

        return
                str.charAt(l) == str.charAt(h) ? getMinimumNumberOfInsertions(str, l + 1, h - 1)
                        : (
                        Math.min(
                                getMinimumNumberOfInsertions(str, l, h - 1),
                                getMinimumNumberOfInsertions(str, l + 1, h)
                        )
                ) + 1;
    }
}
