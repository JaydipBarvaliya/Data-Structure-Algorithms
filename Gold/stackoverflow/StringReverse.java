package com.stackoverflow;

public class StringReverse {

    public static void main(String[] args) {
        String s = "sagar is android developer";
        int first = 0, second = 0, len = s.length();
        for (int i = 0; i < len; i++) {
            second = s.indexOf(" ", first + 1);
            if (second == -1) {
                second = len - 1;
                System.out.print(" ");
            }
            for (int j = second; j >= first; j--) {
                System.out.print(s.charAt(j));
            }
            first = second;
        }
    }
}
